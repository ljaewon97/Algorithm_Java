package boj.g4;

import java.util.ArrayList;
import java.util.List;

public class G4_17406_배열_돌리기_4 {
	static Reader in = new Reader();
	static List<int[]> list = new ArrayList<>();
	static boolean[] visited;
	static int[] result;
	static int[][] arr, temp;
	static int N, M, K;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		
		int[][] opers = new int[K][];
		
		for(int i = 0; i < K; i++) {
			int r = in.nextInt() - 1;
			int c = in.nextInt() - 1;
			int s = in.nextInt();
			
			opers[i] = new int[] {r, c, s};
		}
		
		result = new int[K];
		visited = new boolean[K];
		int ans = Integer.MAX_VALUE;
		perm(0);
		
		for(int[] comb: list) {
			int value = Integer.MAX_VALUE;
			temp = new int[N][];
			for(int i = 0; i < N; i++) {
				temp[i] = arr[i].clone();
			}

			for(int oper: comb) {
				rotate(opers[oper][0], opers[oper][1], opers[oper][2]);
			}
				
			for(int r = 0; r < N; r++) {
				int sum = 0;
				for(int c = 0; c < M; c++) {
					sum += temp[r][c];
				}
				if(sum < value) {
					value = sum;
				}
			}
			
			if(value < ans) {
				ans = value;
			}
		}
		
		System.out.println(ans);
	}
	
	static void perm(int nth) {
		if(nth == K) {
			list.add(result.clone());
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(!visited[i]) {
				result[nth] = i;
				visited[i] = true;
				perm(nth+1);
				visited[i] = false;
			}
		}
	}
	
	static void rotate(int R, int C, int S) {
		int layer = S;
		for(int i = 1; i <= layer; i++) {
			int t = temp[R-i][C-i];
			
			for(int r = R-i; r < R+i; r++) {
				temp[r][C-i] = temp[r+1][C-i];
			}
			
			for(int c = C-i; c < C+i; c++) {
				temp[R+i][c] = temp[R+i][c+1];
			}
			
			for(int r = R+i; r > R-i; r--) {
				temp[r][C+i] = temp[r-1][C+i];
			}
			
			for(int c = C+i; c > C-i+1; c--) {
				temp[R-i][c] = temp[R-i][c-1];
			}
			
			temp[R-i][C-i+1] = t;
		}
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
