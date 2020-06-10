package wordPlay.util;

/**
 * Constants is a utility to be used to define all the enums used in this Java
 * project
 * 
 * @author Ashmeet Kaur Chhabra
 */

public enum Constants {

	LIMIT("."), NEWLINE(System.lineSeparator()), SPACE(" "),
	AVG_NUM_WORDS_PER_SENTENCE("AVG_NUM_WORDS_PER_SENTENCE - "), AVG_WORD_LENGTH("AVG_WORD_LENGTH - "),;

	private String value;

	Constants(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
