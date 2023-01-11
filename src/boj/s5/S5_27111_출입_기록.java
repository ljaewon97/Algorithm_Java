package boj.s5;

import java.util.HashSet;
import java.util.Set;

public class S5_27111_출입_기록 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		int N = in.nextInt();
		
		Set<Integer> set = new HashSet<>();
		int ans = 0;
		
		while(N-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			if(b == 1) {
				if(set.contains(a)) ans++;
				else set.add(a);
			}
			else {
				if(set.contains(a)) set.remove(a);
				else ans++;
			}
		}
		
		ans += set.size();
		
		System.out.println(ans);
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
