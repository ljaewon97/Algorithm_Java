package boj.g4;

import java.util.Arrays;

public class G4_02073_수도배관공사 {
	static Reader in = new Reader();
	static boolean[] con;
	static Pipe[] pipes;
	static int D, P;
	
	public static void main(String[] args) throws Exception {
		D = in.nextInt();
		P = in.nextInt();
		
		con = new boolean[D+1];
		pipes = new Pipe[P];
		
		for(int i = 0; i < P; ++i) {
			pipes[i] = new Pipe(in.nextInt(), in.nextInt());
		}
		
		Arrays.sort(pipes);
		
		con[0] = true;
		
		for(int i = 0; i < P; ++i) {
			for(int j = D; j >= pipes[i].l; --j) {
				con[j] |= con[j-pipes[i].l];
			}
			if(con[D]) {
				System.out.println(pipes[i].c);
				return;
			}
		}
	}
	
	static class Pipe implements Comparable<Pipe> {
		int l, c;
		
		public Pipe(int l, int c) {
			this.l = l;
			this.c = c;
		}

		public int compareTo(Pipe o) {
			return o.c - this.c;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}
