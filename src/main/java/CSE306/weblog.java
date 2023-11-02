package CSE306;

import java.io.*;
import java.net.*;

public class weblog {

	public static void main(String[] args) {
		try {
			String file = "C:\\Users\\nguye\\Desktop\\weblog.txt";
			Reader reader = new InputStreamReader(new FileInputStream(new File(file)), "UTF-8");
			BufferedReader bfr = new BufferedReader(reader);
			String entry = "";

			while ((entry = bfr.readLine()) != null) {

				int index = entry.indexOf(" ");
				String ip = entry.substring(0, index);

				InetAddress ni = InetAddress.getByName(ip);
				System.out.println(ni.getHostName() + entry.substring(index));
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
