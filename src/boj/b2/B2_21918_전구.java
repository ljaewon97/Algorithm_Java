package boj.b2;

public class B2_21918_전구 {
	static Reader in = new Reader();
	static boolean[] bulbs;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		bulbs = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			if(in.nextInt() == 1) bulbs[i] = true;
		}
		
		while(M-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			if(a == 1) {
				if(c == 1) bulbs[b] = true;
				else bulbs[b] = false;
			}
			else if(a == 2) {
				for(int i = b; i <= c; i++) {
					if(bulbs[i]) bulbs[i] = false;
					else bulbs[i] = true;
				}
			}
			else if(a == 3) {
				for(int i = b; i <= c; i++) {
					bulbs[i] = false;
				}
			}
			else if(a == 4) {
				for(int i = b; i <= c; i++) {
					bulbs[i] = true;
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			sb.append(bulbs[i] ? 1 : 0).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if (neg)
				return -n;
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
