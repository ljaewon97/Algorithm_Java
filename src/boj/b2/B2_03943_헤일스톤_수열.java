package boj.b2;

import java.util.HashMap;
import java.util.Map;

public class B2_03943_헤일스톤_수열 {
	static Reader in = new Reader();
	static Map<Integer, Integer> memo = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		for(int t = 0; t < T; t++) {
			int n = in.nextInt();
			
			if(memo.containsKey(n)) {
				sb.append(memo.get(n)).append("\n");
				continue;
			}
			
			if(n == 1 || n == 2 || n == 4) {
				sb.append(n).append("\n");
				memo.put(n, n);
				continue;
			}
			
			sb.append(recur(n)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int recur(int n) {
		if(memo.containsKey(n)) return memo.get(n);
		
		if(n % 2 == 0) {
			int max = Math.max(n, recur(n/2));
			memo.put(n, max);
			return max;
		}
		else {
			int max = Math.max(n, recur(3*n+1));
			memo.put(n, max);
			return max;
		}
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
