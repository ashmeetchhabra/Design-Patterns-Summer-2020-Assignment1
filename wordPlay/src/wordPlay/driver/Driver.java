package wordPlay.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

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
		System.out.println("Hello World! Lets get started with the assignment");

		try {
			File myObj = new File("input.txt");
			Scanner myReader = new Scanner(myObj);
			int i = 0;
			// I Loop for reading a sentence
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				data = data.substring(0, data.length() - 1);
				i++;
				System.out.println(i + " Line: " + data);
				System.out.print("::Tokenize the String::");
				StringTokenizer st = new StringTokenizer(data);
				int j = 0;
				// II Loop for reading a sentence
				while (st.hasMoreTokens()) {
					j++;
					System.out.println(" :" + j);
					String token = st.nextToken();

					if (token.length() >= j) {
						String substr1 = token.substring(0, token.length() - j);
						String substr2 = token.substring(token.length() - j);
						// System.out.println("subs2:"+substr2);
						// System.out.println("Substr1: "+substr1+" Substr2: "+substr2);
						String token_final = substr2 + substr1;

						System.out.println("Final String: " + token_final);
						System.out.println("LENGTH:" + token.length());
					} else {

						int k = j % token.length();
						String substr1 = token.substring(0, token.length() - k);
						String substr2 = token.substring(token.length() - k);
						// System.out.println("Substr1: "+substr1+" Substr2: "+substr2);
						String token_final = substr2 + substr1;
						System.out.println("Final String: " + token_final);
						System.out.println("LENGTH:" + token.length());
					}
				}
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred:File not Found.");
		}

	}
}
