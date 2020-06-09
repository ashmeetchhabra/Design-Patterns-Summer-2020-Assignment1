package wordPlay.driver;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.Constants;
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
		// FileWriter writer = null;
		Results results= new Results();
		MetricsCalculator calc= new MetricsCalculator();
		double noOfSentance=0,noOfWords=0,totalLengthOfWords=0;
		try {
			FileProcessor fp = new FileProcessor(Constants.INPUTFILE);
			String word;
			int index = 0;
			boolean isEndOfSentence = false;
			FileWriter flushOutputFile = new FileWriter(Constants.OUTPUTFILE);
			flushOutputFile.write("");
			flushOutputFile.close();
			FileWriter flushMetricFile = new FileWriter(Constants.METRICFILE);
			flushMetricFile.write("");
			flushMetricFile.close();
			// BufferedWriter writer = new BufferedWriter(new
			// FileWriter(Constants.OUTPUTFILE, true));

			while ((word = fp.poll()) != null) {
				noOfWords++;
				totalLengthOfWords=totalLengthOfWords+word.length();
				index++;
				if (word.charAt(word.length() - 1) == Constants.LIMIT.charAt(0)) {
					word = word.substring(0, word.length() - 1);
					isEndOfSentence = true;
				}
				WordRotator wordRotator = new WordRotator();
				String wordAfterRotate = wordRotator.rotate(index, word);
				results.writeToFile(wordAfterRotate, Constants.OUTPUTFILE);

				// writer.write(wordAfterRotate);

				if (isEndOfSentence) {
					results.writeToFile(Constants.LIMIT+Constants.NEWLINE, Constants.OUTPUTFILE);
					noOfSentance++;
					index = 0;
					isEndOfSentence = false;
				} else
					results.writeToFile(Constants.SPACE, Constants.OUTPUTFILE);
				// writer.append(' ');
			}
			
			double AVG_NUM_WORDS_PER_SENTENCE = calc.calculate(noOfWords, noOfSentance);
			results.writeToFile(Constants.AVG_NUM_WORDS_PER_SENTENCE, Constants.METRICFILE);
			results.writeToFile(String.valueOf(AVG_NUM_WORDS_PER_SENTENCE), Constants.METRICFILE);
			
			results.writeToFile(Constants.NEWLINE, Constants.METRICFILE);
			
			double AVG_WORD_LENGTH = calc.calculate(totalLengthOfWords, noOfWords);
			results.writeToFile(Constants.AVG_WORD_LENGTH, Constants.METRICFILE);
			results.writeToFile(String.valueOf(AVG_WORD_LENGTH), Constants.METRICFILE);
			
//			writer.close();
		} catch (InvalidPathException e) {
			e.getMessage();
			results.printToConsole(e);
		} catch (SecurityException e) {
			e.getMessage();
			results.printToConsole(e);
		} catch (FileNotFoundException e) {
			e.getMessage();
			results.printToConsole(e);
		} catch (IOException e) {
			e.getMessage();
			results.printToConsole(e);
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
