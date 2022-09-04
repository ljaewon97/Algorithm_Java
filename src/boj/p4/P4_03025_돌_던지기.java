package boj.p4;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

public class P4_03025_돌_던지기 {
	static Reader in = new Reader();
	static Stack<Point>[] routes;
	static char[][] map;
	static int R, C;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		R = in.nextInt();
		C = in.nextInt();
		
		routes = new Stack[C];
		map = new char[R][];
		
		for(int c = 0; c < C; c++) {
			routes[c] = new Stack<>();
		}
		
		for(int r = 0; r < R; r++) {
			map[r] = in.readLine().trim().toCharArray();
		}
		
		int N = in.nextInt();
		
		for(int i = 0; i < N; i++) {
			int c = in.nextInt() - 1;
			
			while(!routes[c].isEmpty()) {
				Point cur = routes[c].peek();
				
				if(map[cur.r][cur.c] == '.') break;
				
				routes[c].pop();
			}
			
			if(!routes[c].isEmpty()) {
				Point cur = routes[c].pop();
				
				fall(cur.r, cur.c, c);
			}
			else {
				fall(0, c, c);
			}
			
			Point cur = routes[c].pop();
			map[cur.r][cur.c]= 'O'; 
		}
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void fall(int cr, int cc, int c) {
		while(true) {
			routes[c].push(new Point(cr, cc));
			
			if(cr+1 == R || map[cr+1][cc] == 'X') {
				map[cr][cc] = 'O';
				return;
			}
			
			if(map[cr+1][cc] == 'O') {
				if(cc-1 >= 0 && map[cr][cc-1] == '.' && map[cr+1][cc-1] == '.') cc--;
				else if(cc+1 < C && map[cr][cc+1] == '.' && map[cr+1][cc+1] == '.') cc++;
				else {
					map[cr][cc] = 'O';
					return;
				}
			}
			
			cr++;
		}
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

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64];
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') {
					if (cnt != 0) {
						break;
					} else {
						continue;
					}
				}
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ') {
				c = read();
			}
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

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
	
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}