package boj.g1;

public class G1_13977_이항_계수와_쿼리 {
	static Reader in = new Reader();
	static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		long[] fact = new long[4000001];
		fact[0] = 1;
		
		for(int i = 1; i <= 4000000; i++) {
			fact[i] = i * fact[i-1] % MOD;
		}
		
		int M = in.nextInt();
		
		while(M-- > 0) {
			int N = in.nextInt();
			int K = in.nextInt();
			
			long res = (fact[N] % MOD) * pow(fact[K] * fact[N-K] % MOD, MOD-2) % MOD;
			
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static long pow(long a, long b) {
		if(b == 0) return 1;
		if(b == 1) return a;
		if(b % 2 == 0) {
			long temp = pow(a, b/2);
			return temp * temp % MOD;
		}
		else {
			long temp = pow(a, b-1);
			return temp * a % MOD;
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
