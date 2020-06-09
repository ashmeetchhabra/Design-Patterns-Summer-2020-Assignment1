package wordPlay.driver;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.InvalidPathException;

import wordPlay.handler.WordRotator;
import wordPlay.util.Constants;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

//import sun.security.util.Length;

/**
 * @author John Doe
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}"))
				|| (args[2].equals("${metrics}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}
		// FileWriter writer = null;
		try {
			FileProcessor fp = new FileProcessor(Constants.INPUTFILE);
			String word;
			int index = 0;
			boolean isEndOfSentence = false;
			FileWriter fw = new FileWriter(Constants.OUTPUTFILE);
			fw.write("");
			fw.close();
			// BufferedWriter writer = new BufferedWriter(new
			// FileWriter(Constants.OUTPUTFILE, true));

			while ((word = fp.poll()) != null) {
				index++;
				if (word.charAt(word.length() - 1) == '.') {
					word = word.substring(0, word.length() - 1);
					isEndOfSentence = true;
				}
				WordRotator wordRotator = new WordRotator();
				String wordAfterRotate = wordRotator.rotate(index, word);
				Results results = new Results();
				results.writeToFile(wordAfterRotate, Constants.OUTPUTFILE);

				// writer.write(wordAfterRotate);

				if (isEndOfSentence) {
					results.writeToFile(".\n", Constants.OUTPUTFILE);
					// writer.append(".\n");
					index = 0;
					isEndOfSentence = false;
				} else
					results.writeToFile(" ", Constants.OUTPUTFILE);
				// writer.append(' ');
			}
//			writer.close();
		} catch (InvalidPathException e) {
			e.getMessage();
			System.out.println(e);
		} catch (SecurityException e) {
			e.getMessage();
			System.out.println(e);
		} catch (FileNotFoundException e) {
			e.getMessage();
			System.out.println(e);
		} catch (IOException e) {
			e.getMessage();
			System.out.println(e);
		}
//		finally {
//			try {
//				writer.close();
//			} catch (IOException e) {
//				e.getMessage();
//				System.out.println(e);
//			}
//		}

	}
}
