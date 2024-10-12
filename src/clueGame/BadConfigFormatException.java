package clueGame;

public class BadConfigFormatException extends Exception {
	public BadConfigFormatException() {
		super("Congiguration file format is incorrect");
	}
	
	public BadConfigFormatException(String message) {
		super(message);
	}
	
}


