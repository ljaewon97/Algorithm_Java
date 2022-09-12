package boj.p5;

public class P5_11658_구간_합_구하기_3 {
	static Reader in = new Reader();
	static int[][] arr, tree;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		arr = new int[N+1][N+1];
		tree = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				update(r, c, arr[r][c]);
			}
		}
		
		for(int i = 0; i < M; i++) {
			int w = in.nextInt();
			
			if(w == 0) {
				int x = in.nextInt();
				int y = in.nextInt();
				int c = in.nextInt();
				
				update(x, y, c-arr[x][y]);
				arr[x][y] = c;
			}
			else {
				int x1 = in.nextInt();
				int y1 = in.nextInt();
				int x2 = in.nextInt();
				int y2 = in.nextInt();
				
				int res = sum(x2, y2) - sum(x1-1, y2) - sum(x2, y1-1) + sum(x1-1, y1-1);
				sb.append(res).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void update(int r, int c, int diff) {
		while(r <= N) {
			int temp = c;
			
			while(temp <= N) {
				tree[r][temp] += diff;
				temp += (temp & -temp);
			}
			
			r += (r & -r);
		}
	}
	
	static int sum(int r, int c) {
		int res = 0;
		
		while(r > 0) {
			int temp = c;
			
			while(temp > 0) {
				res += tree[r][temp];
				temp -= (temp & -temp);
			}
			
			r -= (r & -r);
		}
		
		return res;
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
