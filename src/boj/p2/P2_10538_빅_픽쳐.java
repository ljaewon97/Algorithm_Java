package boj.p2;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class P2_10538_빅_픽쳐 {
	static Reader in = new Reader();
	static final int MOD = 1000000007;
	static long[][] rowHash, hash;
	static int hp, wp, hm, wm;
	
	public static void main(String[] args) throws IOException {
		hp = in.nextInt();
		wp = in.nextInt();
		hm = in.nextInt();
		wm = in.nextInt();
		
		rowHash = new long[hm][wm];
		hash = new long[hm][wm];
		
		for(int i = 0; i < hp; i++) {
			rowHashing(in.readLine().trim(), wp, i);
		}
		
		colHashing(hp, wp, hp, wp);
		
		long pictureHash = hash[0][0];
		
		for(int i = 0; i < hm; i++) {
			rowHashing(in.readLine().trim(), wp, i);
		}
		
		colHashing(hp, wp, hm, wm);
		
		int ans = 0;
		
		for(int i = 0; i <= hm-hp; i++) {
			for(int j = 0; j <= wm-wp; j++) {
				if(hash[i][j] == pictureHash) ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	static void rowHashing(String S, int m, int r) {
		int n = S.length();
		long temp = 1, value = 0;
		
		for(int i = 0; i <= n-m; i++) {
			if(i == 0) {
				for(int j = 0; j < m; j++) {
					value = mod(mod(value * 31) + S.charAt(i+j));
					if(j != m-1) temp = mod(temp * 31);
				}
			}
			else {
				value = mod(mod(31 * mod(value - mod(S.charAt(i-1) * temp))) + S.charAt(i+m-1));
			}
			
			rowHash[r][i] = value;
		}
	}
	
	static void colHashing(int r1, int c1, int r2, int c2) {
		for(int i = 0; i <= c2-c1; i++) {
			long temp = 1, value = 0;
			
			for(int j = 0; j <= r2-r1; j++) {
				if(j == 0) {
					for(int k = 0; k < r1; k++) {
						value = mod(value + mod(rowHash[r1-k-1][i] * temp));
						if(k != r1-1) temp = mod(temp * 5831);
					}
				}
				else {
					value = mod(mod(5831 * mod(value - mod(rowHash[j-1][i] * temp))) + rowHash[j+r1-1][i]);
				}
				
				hash[j][i] = value;
			}
		}
	}
	
	static long mod(long x) {
		if(x >= 0) return x % MOD;
		else return ((-x / MOD + 1) * MOD + x) % MOD;
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
			byte[] buf = new byte[2001]; // line length
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
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
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
}
