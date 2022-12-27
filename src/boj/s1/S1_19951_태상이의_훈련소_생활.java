package boj.s1;

public class S1_19951_태상이의_훈련소_생활 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int M = in.nextInt();
		
		long[] H = new long[N];
		long[] sum = new long[N+1];
		
		for(int i = 0; i < N; i++) {
			H[i] = in.nextInt();
		}
		
		while(M-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			int k = in.nextInt();
			
			sum[a-1] += k;
			sum[b] -= k;
		}
		
		sb.append(H[0]+sum[0]).append(" ");
		
		for(int i = 1; i < N; i++) {
			sum[i] += sum[i-1];
			sb.append(H[i]+sum[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
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
			return neg ? -n : n;
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
