package wordPlay.util;

public class EmptyFileException extends RuntimeException {

	/**
	 * EmptyFileException is a utility to be used to define the Exceptions used in
	 * this Java project
	 * 
	 * @author Ashmeet Kaur Chhabra
	 */

	public EmptyFileException(String filename) {
		super("File [" + filename + "] is empty");
	}

}
