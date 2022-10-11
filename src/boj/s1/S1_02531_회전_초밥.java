package boj.s1;

public class S1_02531_회전_초밥 {
	static Reader in = new Reader();
	static int[] sushi, taken;
	static int N, d, k, c;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		d = in.nextInt();
		k = in.nextInt();
		c = in.nextInt();
		
		sushi = new int[N+k-1];
		taken = new int[d+1];
		
		for(int i = 0; i < N; i++) {
			sushi[i] = in.nextInt();
		}
		
		for(int i = N; i < N+k-1; i++) {
			sushi[i] = sushi[i-N];
		}
		
		int cnt = 0;
		
		for(int i = 0; i < k; i++) {
			if(taken[sushi[i]]++ == 0) cnt++;
		}
		
		int max = cnt;
		
		for(int i = 0; i < N-1; i++) {
			max = Math.max(max, cnt+(taken[c]==0?1:0));
			if(--taken[sushi[i]] == 0) cnt--;
			if(taken[sushi[i+k]]++ == 0) cnt++;
		}
		
		System.out.println(max);
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
