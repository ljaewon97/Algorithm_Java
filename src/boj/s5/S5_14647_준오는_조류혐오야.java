package boj.s5;

public class S5_14647_준오는_조류혐오야 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int n = in.nextInt();
		int m = in.nextInt();
		
		int[] rCnt = new int[n];
		int[] cCnt = new int[m];
		int sum = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				int cnt = in.count9();
				sum += cnt;
				rCnt[i] += cnt;
				cCnt[j] += cnt;
			}
		}
		
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			if(rCnt[i] > max) {
				max = rCnt[i];
			}
		}
		
		for(int i = 0; i < m; i++) {
			if(cCnt[i] > max) {
				max = cCnt[i];
			}
		}
		
		System.out.println(sum - max);
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int count9() throws Exception {
			int ret = 0;
			byte c;
			while ((c = read()) <= 32);
			do if(c == 57) ret++;
			while (isNumber(c = read()));
			return ret;
		}
		
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
