package CSE306;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;

public class DayTimeClient {

	public static void main(String[] args) {

		String host = args.length > 0 ? args[0] : "time.nist.gov";

		try {
			Socket s = new Socket(host, 13);
			InputStream in = s.getInputStream();
			StringBuffer sb = new StringBuffer();

			int c;
			while ((c = in.read()) != -1) {
				sb.append((char) c);
			}

	
			System.out.println(sb);
			s.close();
		} catch (UnknownHostException e) {

			System.err.println(e);

		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
