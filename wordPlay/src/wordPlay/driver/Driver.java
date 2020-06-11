package wordPlay.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.Constants;
import wordPlay.util.EmptyFileException;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

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

		// Initialization

		MetricsCalculator calc = new MetricsCalculator();
		WordRotator wordRotator = new WordRotator();
		String word;
		int index = 0;
		double noOfWords;
		double totalLengthOfWords;
		double noOfSentance;
		boolean isEndOfSentence = false;
		boolean checkForEmptyInputFile = true;
		try {
			Results result_output = new Results(args[1]);
			Results result_metric = new Results(args[2]);
			FileProcessor fp = new FileProcessor(args[0]);

			// Read input, word by word
			while ((word = fp.poll()) != null) {

				if (word.isEmpty())
					throw new RuntimeException("Found Empty Line in input file"); // Boundary Check IV: Empty line in input file
				else {
					if (word.matches("[^a-zA-Z0-9.]")) // Boundary Check III:Words contain characters other than
														// [a-zA-Z0-9]
						throw new RuntimeException("Special Character in input file");

					if (word == "")
						continue;
					checkForEmptyInputFile = false;
					noOfWords = calc.getNoOfWords();
					noOfWords++;
					calc.setNoOfWords(noOfWords);
					totalLengthOfWords = calc.getTotalLengthOfWords();
					calc.setTotalLengthOfWords(totalLengthOfWords + word.length());
					// calculate the noOfWords and totalLengthOfWords for metric calculations

					index++;
					if (word.charAt(word.length() - 1) == Constants.LIMIT.getValue().charAt(0)) {
						word = word.substring(0, word.length() - 1); // remove period from the last word of the sentence
						isEndOfSentence = true; // boolean flag is used to reset the index again to 0 when period is
												// encountered
					}
					word = wordRotator.rotate(index, word); // rotation of word
					result_output.writeToFile(word); // write to a output file after rotation

					if (isEndOfSentence) {
						result_output.writeToFile(Constants.LIMIT.getValue() + Constants.NEWLINE.getValue()); // Add the
																												// separators
																												// in
																												// the
																												// output
																												// file
						noOfSentance = calc.getNoOfSentance();
						noOfSentance++; // Calculate the noOfSentance
						calc.setNoOfSentance(noOfSentance);

						index = 0;
						isEndOfSentence = false;
					} else
						result_output.writeToFile(Constants.SPACE.getValue());
				}
			}

			// Boundary Check I: Empty input file
			if (checkForEmptyInputFile)
				throw new EmptyFileException(args[0]);

			// Metrics calculation and insert in metric txt file

			result_metric.writeToFile(Constants.AVG_NUM_WORDS_PER_SENTENCE.getValue());
			result_metric.writeToFile(String.valueOf(calc.calculateAvgNumWordsPerSentance()));
			result_metric.writeToFile(Constants.NEWLINE.getValue());
			result_metric.writeToFile(Constants.AVG_WORD_LENGTH.getValue());
			result_metric.writeToFile(String.valueOf(calc.calculateAvgWordLength()));

			result_metric.closeFile();
			result_output.closeFile();

		} catch (InvalidPathException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) { // Boundary Check II:Missing input file
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EmptyFileException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
		}
	}
}
