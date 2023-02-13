package boj.d5;

public class D5_18185_라면_사기_Small {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; ++i) {
			arr[i] = in.nextInt();
		}
		
		int ans = 0;
		int[] prev = new int[4];
		
		prev[1] = arr[0];
		
		for(int i = 1; i < N; ++i) {
			int x = arr[i];
			int n2 = Math.min(prev[1], x);
			x -= n2;
			int n3 = Math.min(prev[2], x);
			x -= n3;
			int n1 = x;
			
			ans += 3 * (prev[1]-n2);
			ans += 5 * (prev[2]-n3);
			ans += 7 * n3;
			
			prev[1] = n1;
			prev[2] = n2;
			prev[3] = n3;
			
		}
		
		ans += 3 * prev[1];
		ans += 5 * prev[2];
		
		System.out.println(ans);
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
