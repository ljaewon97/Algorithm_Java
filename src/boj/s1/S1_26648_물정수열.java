package boj.s1;

public class S1_26648_물정수열 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[] P = new int[N];
		int[] I = new int[N];
		int[] M = new int[N];
		
		for(int i = 0; i < N; i++) P[i] = in.nextInt();
		for(int i = 0; i < N; i++) I[i] = in.nextInt();
		for(int i = 0; i < N; i++) M[i] = in.nextInt();
		
		boolean ans = true;
		int prev = min(P[0], I[0], M[0]);
		
		for(int i = 1; i < N; i++) {
			int mi = min(P[i], I[i], M[i]);
			int mx = max(P[i], I[i], M[i]);
			if(mi > prev) {
				prev = mi;
			}
			else if(prev >= mx) {
				ans = false;
				break;
			}
			else {
				prev++;
			}
		}
		
		System.out.println(ans ? "YES" : "NO");
	}
	
	static int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
	
	static int max(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
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
