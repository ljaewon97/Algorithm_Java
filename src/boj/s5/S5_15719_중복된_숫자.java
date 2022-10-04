package boj.s5;

public class S5_15719_중복된_숫자 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		long sum = 0;
		
		for(int i = 0; i < N; i++) {
			sum += in.nextInt();
		}
		
		System.out.println(sum - ((long) N * (N-1)) / 2);
	}
	
	static class Reader {
		final int SIZE = 1 << 17;
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
