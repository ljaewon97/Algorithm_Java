package boj.b2;

import java.util.HashSet;
import java.util.Set;

public class B2_06500_랜덤_숫자_만들기 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int N = in.nextInt();
			int cnt = 1;
			
			if(N == 0) break;
			
			Set<Integer> set = new HashSet<>();
			
			while(true) {
				set.add(N);
				
				N = N * N;
				N /= 100;
				N = N % 10000;
				
				if(set.contains(N)) break;
				cnt++;
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
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
