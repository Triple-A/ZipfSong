/**Kattio found from https://spc11.contest.scrool.se/doc/src/Kattio.java
 * 
 * @author   Emily Lin
 * @date	January 7, 2013
 */
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ZipfSong {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		
		String name = "";
		int n = 0, m = 0; //n songs in album, m best songs
		double quality, played = 0;
		Song[] songs; 
		
		n = io.getInt();
		m = io.getInt();
		
		songs = new Song[n];
		for (int i = 0; i < n; i++) {
			played = io.getDouble();
			name = io.getWord();
			
			quality = played * (i + 1); //Zipf's Law: q = f * i
			songs[i] = new Song(quality, name, i); //puts song in array
		}
		Arrays.sort(songs);
		
		for (int i = songs.length - 1; i >= n - m; i--) 
			io.println(songs[i].name); //prints first m best songs
		io.close(); 
	}
	
	static class Song implements Comparable<Song> {
		double quality;
		String name;
		int num;
		
		Song (double q, String n, int i) {
			quality = q;
			name = n;
			num = i;
		}

		public int compareTo(Song s) {
			if(quality > s.quality) //puts higher quality first
				return 1;
			else if (quality == s.quality) //if quality is the same
				return (num < s.num) ? 1 : -1; //puts lower album num first
			else
				return -1;
		}
	}
	
	static class Kattio extends PrintWriter {
		public Kattio(InputStream i) {
			super(new BufferedOutputStream(System.out));
			r = new BufferedReader(new InputStreamReader(i));
		}

		public Kattio(InputStream i, OutputStream o) {
			super(new BufferedOutputStream(o));
			r = new BufferedReader(new InputStreamReader(i));
		}

		public boolean hasMoreTokens() {
			return peekToken() != null;
		}

		public int getInt() {
			return Integer.parseInt(nextToken());
		}

		public double getDouble() {
			return Double.parseDouble(nextToken());
		}

		public long getLong() {
			return Long.parseLong(nextToken());
		}

		public String getWord() {
			return nextToken();
		}

		private BufferedReader r;
		private String line;
		private StringTokenizer st;
		private String token;

		private String peekToken() {
			if (token == null)
				try {
					while (st == null || !st.hasMoreTokens()) {
						line = r.readLine();
						if (line == null)
							return null;
						st = new StringTokenizer(line);
					}
					token = st.nextToken();
				} catch (IOException e) {
				}
			return token;
		}

		private String nextToken() {
			String ans = peekToken();
			token = null;
			return ans;
		}
	}
}
