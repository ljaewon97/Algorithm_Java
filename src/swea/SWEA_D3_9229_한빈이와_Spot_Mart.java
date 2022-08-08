package swea;

import java.util.Arrays;

public class SWEA_D3_9229_한빈이와_Spot_Mart {
	static Reader in = new Reader();
	static int[] snacks;
	static int N, M, ans;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		for(int t = 1; t <= T; t++) {
			N = in.nextInt();
			M = in.nextInt();
			ans = -1;
			
			snacks = new int[N];
			
			for(int i = 0; i < N; i++) {
				snacks[i] = in.nextInt();
			}
			
			Arrays.sort(snacks);
			
			outer: for(int i = 0; i < N-1; i++) {
				for(int j = i+1; j < N; j++) {
					int sum = snacks[i] + snacks[j];
					
					if(sum < M) {
						if(sum > ans) {
							ans = sum;
						}
					}
					else if(sum == M) {
						ans = sum;
						break outer;
					}
					else {
						break;
					}
				}
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
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

		long nextLong() throws Exception {
			long n = 0;
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
