package CSE306;

import java.io.*;
import java.net.*;

public class SourceViewer {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://www.tanbinhtech.com/july.webp");

			InputStream iS = url.openStream();

			int c;
			while ((c = iS.read()) != -1) {
				System.out.print(c);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
