package boj.g2;

public class G2_12738_가장_긴_증가하는_부분_수열_3 {
	static Reader in = new Reader();
	static int[] arr, C;
	static int N, size;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		arr = new int[N];
		C = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			int l = 0;
			int r = size - 1;
			
			while(l <= r) {
				int mid = (l + r) >> 1;
				int val = C[mid];
				
				if(val < arr[i]) l = mid + 1;
				else if(val > arr[i]) r = mid - 1;
				else {
					l = -1;
					break;
				}
			}
			
			if(l == -1) continue;

			C[l] = arr[i];
			if(l == size) size++;
		}
		
		System.out.println(size);
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
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
