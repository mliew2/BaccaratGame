
public class Card {
	String suite;
	int value;

	Card(String theSuite, int theValue) {
		setSuite(theSuite);
		setValue(theValue);
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
