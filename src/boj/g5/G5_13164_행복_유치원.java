package boj.g5;

import java.util.Arrays;

public class G5_13164_행복_유치원 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int K = in.nextInt();
		
		int[] arr = new int[N];
		int[] diff = new int[N-1];
		
		arr[0] = in.nextInt();
		for(int i = 1; i < N; i++) {
			arr[i] = in.nextInt();
			diff[i-1] = arr[i] - arr[i-1];
		}
		
		Arrays.sort(diff);
		
		int ans = 0;
		
		for(int i = 0; i < N-K; i++) {
			ans += diff[i];
		}
		
		System.out.println(ans);
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
