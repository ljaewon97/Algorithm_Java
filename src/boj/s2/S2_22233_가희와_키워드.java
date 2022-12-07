package boj.s2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S2_22233_가희와_키워드 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> keywords = new HashSet<>();
		
		while(N-- > 0) {
			keywords.add(in.readLine());
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(in.readLine(), ",");
			while(st.hasMoreTokens()) {
				keywords.remove(st.nextToken());
			}
			
			sb.append(keywords.size()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[150]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if(c == '\r') {
					read(); break;
				}
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}
	}
}
