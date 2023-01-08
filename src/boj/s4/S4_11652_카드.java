package boj.s4;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class S4_11652_카드 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		long N = in.nextLong();
		
		Map<Long, Integer> count = new HashMap<>();
		
		while(N-- > 0) {
			long x = in.nextLong();
			
			if(!count.containsKey(x)) count.put(x, 1);
			else count.put(x, count.get(x)+1);
		}
		
		int max = 0;
		long ans = 0;
		
		for(Entry<Long, Integer> e: count.entrySet()) {
			if(e.getValue() > max) {
				max = e.getValue();
				ans = e.getKey();
			}
			else if(e.getValue() == max && e.getKey() < ans) {
				ans = e.getKey();
			}
		}
		
		System.out.println(ans);
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		long nextLong() throws Exception {
			long n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return neg ? -n : n;
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
