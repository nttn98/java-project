package CSE306;

import java.io.*;
import java.net.*;

import javax.sound.sampled.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class DownloadAndPlay {

	public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

//		String link = "C:\\Users\\nguye\\Desktop\\sample1.wav";
//		Playsound(link);
//
		String link = "http://ice10.outlaw.fm/KIEV2";
//		Download(link);
//		DownloadFirstAndPlay(link);
		PlayOnline(link);

	}

	static void Playsound(String filePath) {
		File file = new File(filePath);

		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();

			clip.open(ais);
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 1000);

			clip.close();
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

	private static final int BUFFER_SIZE = 4096;

	static void DownAndPlay(String link) {
		try {
			URL url = new URL(link);

			InputStream iS = url.openStream();
			iS = new BufferedInputStream(iS);

			AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
			AudioFormat audioFormat = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();
			byte[] bufferBytes = new byte[BUFFER_SIZE];
			int readBytes = -1;
			while ((readBytes = audioStream.read(bufferBytes)) != -1) {
				sourceDataLine.write(bufferBytes, 0, readBytes);
			}
			sourceDataLine.drain();
			sourceDataLine.close();
			audioStream.close();

		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
			System.out.println(e);
		}
	}

	static void PlayOnline(String link) {
		try {
			URL url = new URL(link);
			InputStream iS = url.openStream();
			iS = new BufferedInputStream(iS);
			Player mp3Player = new Player(iS);
			mp3Player.play();

		} catch (IOException | JavaLayerException e) {
			System.out.println(e);
		}
	}
}
