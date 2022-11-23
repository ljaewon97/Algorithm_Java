package boj.s5;

public class S5_11637_인기_투표 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		while(T-- > 0) {
			int n = in.nextInt();
			int sum = 0, max = 0, win = 0, winCnt = 0;
			
			for(int i = 1; i <= n; i++) {
				int v = in.nextInt();
				sum += v;
				
				if(v > max) {
					max = v;
					win = i;
					winCnt = 1;
				}
				else if(v == max) {
					winCnt++;
				}
			}
			
			if(winCnt > 1) {
				sb.append("no winner\n");
			}
			else {
				if(max > sum/2) {
					sb.append("majority winner ").append(win).append("\n");
				}
				else {
					sb.append("minority winner ").append(win).append("\n");
				}
			}
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
