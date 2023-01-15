package boj.p3;

public class P3_14959_Slot_Machines {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int n = in.nextInt();
		
		int[] T = new int[n];
		
		for(int i = n-1; i >= 0; i--) {
			T[i] = in.nextInt();
		}
		
		int[] pi = new int[n];
		int j = 0;
		
		for(int i = 1; i < n; i++) {
			while(j > 0 && T[i] != T[j]) j = pi[j-1];
			
			if(T[i] == T[j]) j++;
			
			pi[i] = j;
		}
		
		int min = Integer.MAX_VALUE;
		int k = 0, p = 0;
		
		for(int i = 0; i < n; i++) {
			int tk = n-i-1;
			int tp = i+1-pi[i];
			
			if(tk+tp < min) {
				min = tk+tp;
				k = tk;
				p = tp;
			}
		}
		
		System.out.println(k + " " + p);
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
