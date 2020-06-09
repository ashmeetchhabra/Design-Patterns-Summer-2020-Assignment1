package wordPlay.handler;

public class WordRotator {
	public String rotate(int index, String word) {
		int k = index % word.length();
		String substr1 = word.substring(0, word.length() - k);
		String substr2 = word.substring(word.length() - k);
		return substr2 + substr1;
	}
}