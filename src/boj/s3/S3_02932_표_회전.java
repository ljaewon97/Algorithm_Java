package boj.s3;

import java.util.LinkedList;
import java.util.Queue;

public class S3_02932_표_회전 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int K = in.nextInt();
		
		Queue<Cell> queue = new LinkedList<>();
		
		while(K-- > 0) {
			int num = in.nextInt() - 1;
			int r = num / N;
			int c = num % N;
			int tr = in.nextInt() - 1;
			int tc = in.nextInt() - 1;
			
			queue.add(new Cell(r, c, tr, tc));
		}
		
		while(!queue.isEmpty()) {
			Cell cell = queue.poll();
			int cnt = 0;
			
			int cmove = (cell.tc + N - cell.c) % N;
			cnt += cmove;
			
			for(Cell cur: queue) {
				if(cur.r == cell.r) cur.c = (cur.c + cmove) % N;
			}
			
			int rmove = (cell.tr + N - cell.r) % N;
			cnt += rmove;
			
			for(Cell cur: queue) {
				if(cur.c == cell.tc) cur.r = (cur.r + rmove) % N;
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static class Cell {
		int r, c, tr, tc;
		
		public Cell(int r, int c, int tr, int tc) {
			this.r = r;
			this.c = c;
			this.tr = tr;
			this.tc = tc;
		}
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
