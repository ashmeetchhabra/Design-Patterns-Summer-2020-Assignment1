package wordPlay.util;

import java.io.IOException;

public interface FileDisplayInterface {
	void writeToFile(String str) throws IOException;

	void closeFile() throws IOException;
}
