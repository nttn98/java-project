package CSE306;

import java.io.*;
import java.net.*;

public class DownloadImage {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://file-examples.com/storage/fe1134defc6538ed39b8efa/2017/11/file_example_MP3_700KB.mp3");
			InputStream iS = url.openStream();
			String[] test = url.toString().split("/");

			String downloadAddress = "src/main/java/download/" + test[test.length - 1];
			OutputStream oS = new FileOutputStream(new File(downloadAddress));

			int c;
			while ((c = iS.read()) != -1) {
				oS.write(c);
			}
			System.out.println(downloadAddress);

			iS.close();
			oS.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
