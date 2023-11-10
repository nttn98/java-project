package CSE306;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TelehackClient {

	public static final String SERVER = "telehack.com";
	public static final int PORT = 23;
	public static final int TIMEOUT = 15000;

	public static void main(String[] args) {

		Socket socket = null;

		try {

			socket = new Socket(SERVER, PORT);
			socket.setSoTimeout(TIMEOUT);

			OutputStream out = socket.getOutputStream();
			Writer writer = new OutputStreamWriter(out, "UTF-8");
			writer = new BufferedWriter(writer);

			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

			int c;
			char prev = 'a';
			while ((c = in.read()) != -1) {
				System.out.print((char) c);
				if ((char) c == '.' && prev == '\n') {
					break;
				}
				prev = (char) c;
			}

			writer.write("eliza\r\n");
			writer.flush();
			readFirst(writer, reader, "");

			while (true) {
				String d = rd.readLine();
				if (d.equals("quit")) {
					break;
				}
				readEliza(writer, reader, d);
				System.out.println(" Eliza");
				System.out.println();
			}
		} catch (IOException ex) {
			System.err.println(ex);
		} finally { // dispose
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException ex) {
					// ignore
				}
			}
		}
	}

	private static void readFirst(Writer writer, BufferedReader reader, String word) {
		try {
			writer.write(word + "\r\n");
			writer.flush();

			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				if (line.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static void readEliza(Writer writer, BufferedReader reader, String word) {
		try {
			int count = 0;
			int c;

			writer.write(word + "\r\n");
			writer.flush();

			while ((c = reader.read()) != -1) {
				if ((char) c == '\r') {
					if (count == 3) {
						// System.out.println("<END>");
						break;
					} else {
						count++;
					}
				}
				if (count == 2) {
					System.out.print((char) c);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
