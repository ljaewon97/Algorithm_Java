package boj.g4;

import java.util.Arrays;

public class G4_02110_공유기_설치 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		int C = in.nextInt();
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = in.nextInt();
		}
		
		Arrays.sort(arr);
		
		int l = 0;
		int r = arr[N-1] - arr[0];
		
		while(l <= r) {
			int m = (l + r) >> 1;
			boolean flag = false;
			int prev = arr[0];
			int cnt = 1;
			
			for(int i = 1; i < N; i++) {
				if(arr[i] - prev >= m) {
					cnt++;
					prev = arr[i];
					if(cnt >= C) {
						flag = true;
						break;
					}
				}
			}
			
			if(flag) l = m + 1;
			else r = m - 1;
		}
		
		System.out.println(r);
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
