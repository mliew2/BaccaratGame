import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread{

	Socket socketClient;

	ObjectOutputStream out;
	ObjectInputStream in;

	private String ip;
	private int port;
	private Consumer<Serializable> callback;

	Client(Consumer<Serializable> callback, String ip, int port){
		this.callback = callback;
		this.ip = ip;
		this.port = port;
	}

	public void run() {
		try {
			socketClient = new Socket(ip, port);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
			callback.accept("Connected!");
		}
		catch(Exception e) {
			callback.accept("Error! Could not connect to server.");
			return;
		}

		while(true) {
			try {
				BaccaratInfo info = (BaccaratInfo) in.readObject();
				callback.accept(info);
			}
			catch(Exception e) {
				callback.accept("Error! Server disconnected.");
				break;
			}
		}

	}

	public void send(BaccaratInfo info) {
		try {
			out.writeObject(info);
		} catch (Exception e) {
			callback.accept("Error! Could not send data.");
		}
	}
}