package wordPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	@Override
	public void writeToFile(String str, String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.OUTPUTFILE, true));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.getMessage();
			System.out.println(e);
		}

	}

}
