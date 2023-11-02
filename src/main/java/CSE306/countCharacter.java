package CSE306;

import java.io.*;

public class countCharacter {

	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\nguye\\Desktop\\doc1.txt";
		Reader reader = new InputStreamReader(new FileInputStream(new File(file)), "UTF-8");

		String fileWri = "C:\\Users\\nguye\\Desktop\\doc2.txt";
		Writer writer = new OutputStreamWriter(new FileOutputStream(new File(fileWri)), "UTF-8");

		int c;

		while ((c = reader.read()) > -1) {
			writer.write((char) c);
		}

		reader.close();
		writer.close();

	}

}