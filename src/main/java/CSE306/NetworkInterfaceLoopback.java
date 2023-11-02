package CSE306;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetworkInterfaceLoopback {

	public static void main(String[] args) {
		try {

			InetAddress local = InetAddress.getByName("127.0.0.1");
			NetworkInterface ni = NetworkInterface.getByInetAddress(local);
			
			if (ni == null) {
				System.err.println("That's weird. No local loopback address.");
			} else {
				System.out.println(ni);
			}
			
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			
			while (interfaces.hasMoreElements()) {

				NetworkInterface ni2 = interfaces.nextElement();
				System.out.println(ni2);
			}
		} catch (SocketException e) {
			System.err.println("Could not list network interfaces.");
		} catch (UnknownHostException e) {
			System.err.println("That's weird. Could not lookup 127.0.0.1");
		}
	}
}
