package wordPlay.metrics;

public class MetricsCalculator {

	double noOfSentance = 0, noOfWords = 0, totalLengthOfWords = 0;

	public double getNoOfSentance() {
		return noOfSentance;
	}

	public void setNoOfSentance(double noOfSentance) {
		this.noOfSentance = noOfSentance;
	}

	public double getNoOfWords() {
		return noOfWords;
	}

	public void setNoOfWords(double noOfWords) {
		this.noOfWords = noOfWords;
	}

	public double getTotalLengthOfWords() {
		return totalLengthOfWords;
	}

	public void setTotalLengthOfWords(double totalLengthOfWords) {
		this.totalLengthOfWords = totalLengthOfWords;
	}

	/**
	 * Calculate the average word length and round it to 2 decimal places
	 * 
	 * @return double The quotient which is rounded off by 2 places
	 */

	public double calculateAvgWordLength() {
		return (double) Math.round((this.totalLengthOfWords / this.noOfWords) * 100.0) / 100.0;
	}

	/**
	 * Calculate the average number of words per sentence and round it to 2 decimal
	 * places
	 * 
	 * @return double The quotient which is rounded off by 2 places
	 */

	public double calculateAvgNumWordsPerSentance() {
		return (double) Math.round((this.noOfWords / this.noOfSentance) * 100.0) / 100.0;

	}

}
