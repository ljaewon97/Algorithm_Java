package boj.g5;

public class G5_02467_용액 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		if(N == 2) {
			System.out.println(arr[0] + " " + arr[1]);
			return;
		}
		
		int l = 0, r = arr.length - 1;
		int la = 0, ra = arr.length - 1;
		int v = arr[l] + arr[r];
		int min = Math.abs(v);
		
		if(v == 0) {
			System.out.println(arr[l] + " " + arr[r]);
			return;
		}
		
		while(r - l > 1) {
			if(v > 0) {
				r--;
				v = arr[l] + arr[r];
				if(Math.abs(v) < min) {
					min = Math.abs(v);
					la = l;
					ra = r;
				}
			}
			else if(v == 0) {
				System.out.println(arr[l] + " " + arr[r]);
				return;
			}
			else {
				l++;
				v = arr[l] + arr[r];
				if(Math.abs(v) < min) {
					min = Math.abs(v);
					la = l;
					ra = r;
				}
			}
		}
		
		System.out.println(arr[la] + " " + arr[ra]);
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
