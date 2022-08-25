package boj.s1;

public class S1_02156_포도주_시식 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[] wines = new int[N];
		
		for(int i = 0; i < N; i++) {
			wines[i] = in.nextInt();
		}
		
		int[][] dp = new int[N][3];
		
		dp[0][0] = wines[0];
		dp[0][1] = wines[0];
		dp[0][2] = wines[0];
		
		if(N == 1) {
			System.out.println(wines[0]);
			return;
		}
		
		dp[1][0] = wines[1] + wines[0];
		dp[1][1] = wines[1];
		dp[1][2] = wines[1];
		
		if(N == 2) {
			System.out.println(wines[0] + wines[1]);
			return;
		}
		
		dp[2][0] = wines[2] + wines[1];
		dp[2][1] = wines[2] + wines[0];
		dp[2][2] = wines[2];
		
		for(int i = 3; i < N; i++) {
			dp[i][0] = wines[i] + Math.max(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = wines[i] + Math.max(dp[i-2][0], Math.max(dp[i-2][1], dp[i-2][2]));
			dp[i][2] = wines[i] + Math.max(dp[i-3][0], Math.max(dp[i-3][1], dp[i-3][2]));
		}
		
		int m1 = Math.max(dp[N-2][0], Math.max(dp[N-2][1], dp[N-2][2]));
		int m2 = Math.max(dp[N-1][0], Math.max(dp[N-1][1], dp[N-1][2]));
		
		System.out.println(Math.max(m1, m2));
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
