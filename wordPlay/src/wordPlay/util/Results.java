package wordPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	BufferedWriter write;

	/**
	 * Constructor to open the file in append mode
	 * 
	 * @param filename The file in which data is to be written
	 * @throws IOException
	 */
	public Results(String filename) throws IOException {
		write = new BufferedWriter(new FileWriter(filename));

	}

	/**
	 * Writes to a file in append mode
	 * 
	 * @param str The string to be written in file
	 * @throws IOException
	 */

	@Override
	public void writeToFile(String str) throws IOException {
		write.write(str);
	}

	/**
	 * Prints different number of arguments to the console
	 * 
	 * @param obj Varargs used to print on console
	 */

	@Override
	public void printToConsole(Object... obj) {
		for (int i = 0; i < obj.length; i++) {
			System.out.println(obj[i]);
		}
	}

	/**
	 * Close the file
	 * 
	 * @throws IOException
	 */


	public void closeFile() throws IOException {
		write.close();

	}

}
