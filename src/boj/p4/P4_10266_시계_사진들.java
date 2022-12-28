package boj.p4;

public class P4_10266_시계_사진들 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int n = in.nextInt();
		
		boolean[] clock1 = new boolean[720000];
		boolean[] clock2 = new boolean[360000];
		
		for(int i = 0; i < n; i++) {
			int a = in.nextInt();
			clock1[a] = clock1[a+360000] = true;
		}
		
		for(int i = 0; i < n; i++) {
			clock2[in.nextInt()] = true;
		}
		
		int[] pi = new int[360000];
		int j = 0;
		
		for(int i = 1; i < 360000; i++) {
			while(j > 0 && clock2[i] != clock2[j]) j = pi[j-1];
			
			if(clock2[i] == clock2[j]) j++;
			
			pi[i] = j;
		}
		
		boolean ans = false;
		j = 0;
		
		for(int i = 0; i < 720000; i++) {
			while(j > 0 && clock1[i] != clock2[j]) j = pi[j-1];
			
			if(clock1[i] == clock2[j]) {
				if(j == 359999) {
					ans = true;
					break;
				}
				else j++;
			}
		}
		
		System.out.println(ans ? "possible" : "impossible");
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
