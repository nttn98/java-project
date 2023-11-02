package CSE306;

import java.io.*;
import java.net.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class DownloadAndPlay {

	public static void main(String[] args) {
		
//		String link = "C:\\Users\\nguye\\Desktop\\sample1.wav";
//		Playsound(link);
//
//		String link = "https://www.tanbinhtech.com:8443/sample1.wav";
//		Download(link);
//		DownloadFirstAndPlay(link);
//		PlayOnline(link);

	}

	static void Playsound(String filePath) {
		File file = new File(filePath);

		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();

			Thread.sleep(clip.getMicrosecondLength() / 1000);

		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
			System.out.println(e);
		}
	}

	static String Download(String link) {
		try {
			URL url = new URL(link);
			InputStream iS = url.openStream();
			String[] nameOfFile = url.toString().split("/");

			String destination = "src/main/java/download/" + nameOfFile[nameOfFile.length - 1];
			OutputStream oS = new FileOutputStream(new File(destination));

			int c;
			while ((c = iS.read()) != -1) {
				oS.write(c);
			}

			oS.close();
			iS.close();

			return destination;

		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	static void DownloadFirstAndPlay(String link) {
		String filePathText = Download(link);
		Playsound(filePathText);
	}

	static void DownAndPlay(String link) {

	}

	static void PlayOnline(String link) {
		try {
			URL url = new URL(link);
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();

			Thread.sleep(clip.getMicrosecondLength() / 1000);

		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
