import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server{

	int clientNo = 1;
	int clientCount = 0;
	ServerThread server;
	private int port;
	private Consumer<Serializable> callback;

	Server(Consumer<Serializable> callback, int port){
		this.callback = callback;
		this.port = port;
		server = new ServerThread();
		server.start();
	}

	public class ServerThread extends Thread{	
		public void run() {
			try(ServerSocket socket = new ServerSocket(port);) {
				callback.accept("Server started!");
				while(true) {
					ClientThread client = new ClientThread(socket.accept(), clientNo);
					clientCount++;
					callback.accept("New client has connected to server.\nClient# " + clientNo);
					callback.accept(clientCount);
					client.start();
					clientNo++;
				}
			} catch(Exception e) {
				callback.accept("Server socket did not launch");
			}
		}
	}

	class ClientThread extends Thread{
		Socket connection;
		int count, playCount;
		ObjectInputStream in;
		ObjectOutputStream out;
		BaccaratGame game;

		ClientThread(Socket s, int count){
			this.connection = s;
			this.count = count;
			playCount = 0;
		}

		public void run(){
			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);	
			}
			catch(Exception e) {
				callback.accept("Streams not open");
			}

			while(true) {
				try {
					BaccaratInfo info = (BaccaratInfo) in.readObject();
					if(playCount > 0) {
						callback.accept("Client: " + count + " wants to play another game.");
					}
					callback.accept("Client " + count + " bet $" + info.getInitialBet() + " on " + info.getWhoToBet());
					game = new BaccaratGame(info.getInitialBet(), info.getWhoToBet());
					info = game.playGame();
					callback.accept(info);
					ClientThread t = this;
					t.out.writeObject(info);
					playCount++;
				}
				catch(Exception e) {
					clientCount--;
					callback.accept("Client " + count + " left.");
					callback.accept(clientCount);
					break;
				}
			}
		}
	}
}