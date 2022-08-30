package boj.g4;

public class G4_01806_부분합 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int S = in.nextInt();
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		int l = 0, r = 0, sum = arr[0];
		int ans = Integer.MAX_VALUE;
		
		if(sum >= S) ans = Math.min(ans, r-l+1);
		
		while(l <= r && l < N && r < N) {
			if(sum > S) {
				sum -= arr[l];
				l++;
				
				if(l >= N) break;
				
				if(sum >= S) ans = Math.min(ans, r-l+1);
			}
			else {
				r++;
				
				if(r >= N) break;
				
				sum += arr[r];
				
				if(sum >= S) ans = Math.min(ans, r-l+1);
			}
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
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
			if (neg)
				return -n;
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
