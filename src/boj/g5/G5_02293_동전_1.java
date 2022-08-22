package boj.g5;

public class G5_02293_동전_1 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int K = in.nextInt();
		
		int[] dp = new int[K+1];
		dp[0] = 1;
		
		for(int i = 0; i < N; i++) {
			int coin = in.nextInt();
			
			for(int j = 1; j <= K; j++) {
				if(j >= coin) {
					dp[j] += dp[j-coin];
				}
			}
		}
		
		System.out.println(dp[K]);
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
