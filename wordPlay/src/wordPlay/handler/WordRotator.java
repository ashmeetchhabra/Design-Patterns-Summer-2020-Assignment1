package wordPlay.handler;

public class WordRotator {
	/**
	 * Rotates the word with index as offset
	 * 
	 * @param index The offset by which the word is rotated
	 * @param word  String which is to be rotated
	 * @return String The rotated word
	 */

	public String rotate(int index, String word) {
		int k = index % word.length(); // Ideal case When the word length is less than the offset need to TODO: place
										// proper comment
		String substr1 = word.substring(0, word.length() - k);
		String substr2 = word.substring(word.length() - k);
		return substr2 + substr1;
	}
}