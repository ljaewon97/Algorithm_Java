package boj.s5;

public class S5_23253_자료구조는_정말_최고야 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		in.nextInt();
		int M = in.nextInt();
		
		boolean flag = true;
		
		while(M-- > 0) {
			int k = in.nextInt();
			int prev = 1000000;
			
			while(k-- > 0) {
				int b = in.nextInt();
				if(b > prev) flag = false;
				prev = b;
			}
		}
		
		System.out.println(flag ? "Yes" : "No");
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
