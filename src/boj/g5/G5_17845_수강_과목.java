package boj.g5;

public class G5_17845_수강_과목 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int K = in.nextInt();
		
		int[] dp = new int[N+1];
		
		while(K-- > 0) {
			int I = in.nextInt();
			int T = in.nextInt();
			
			for(int i = N; i >= T; i--) {
				dp[i] = Math.max(dp[i], dp[i-T]+I);
			}
		}
		
		System.out.println(dp[N]);
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
