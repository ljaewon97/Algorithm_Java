package boj.g5;

import java.util.Arrays;

public class G5_02294_동전_2 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int K = in.nextInt();
		
		int[] dp = new int[K+1];
		
		Arrays.fill(dp, 10001);
		dp[0] = 0;
		
		for(int i = 0; i < N; i++) {
			int c = in.nextInt();
			
			for(int j = 0; j <= K-c; j++) {
				dp[j+c] = Math.min(dp[j+c], dp[j] + 1);
			}
		}
		
		System.out.println(dp[K] == 10001 ? -1 : dp[K]);
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
