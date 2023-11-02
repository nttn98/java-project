package CSE306;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OReillyByName {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("www.eiu.edu.vn");
			System.out.println(address);
		} catch (UnknownHostException e) {
			System.out.println("Could not find www.eiu.edu.vn");
		}
	}
}
