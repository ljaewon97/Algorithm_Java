package boj.g4;

public class G4_02344_거울 {
	static Reader in = new Reader();
	static int[][] box;
	static int[] ans;
	static int N, M, start, end;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		box = new int[N+2][M+2];
		ans = new int[2*N+2*M+1];
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= M; c++) {
				box[r][c] = -in.nextInt();
			}
		}
		
		int no = 1;
		
		for(int r = 1; r <= N; r++) box[r][0] = no++;
		for(int c = 1; c <= M; c++) box[N+1][c] = no++;
		for(int r = N; r >= 1; r--) box[r][M+1] = no++;
		for(int c = M; c >= 1; c--) box[0][c] = no++;
		
		for(int r = 1; r <= N; r++) {
			start = box[r][0];
			light(r, 1, 1);
			ans[start] = end;
			ans[end] = start;
		}
		
		for(int c = 1; c <= M; c++) {
			start = box[N+1][c];
			light(N, c, 0);
			ans[start] = end;
			ans[end] = start;
		}
		
		for(int i = 1; i <= 2*N+2*M; i++) {
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void light(int r, int c, int d) {
		if(box[r][c] > 0) {
			end = box[r][c];
			return;
		}
		
		if(box[r][c] == -1) {
			int nd = d % 2 == 0 ? d+1 : d-1;
			light(r+dr[nd], c+dc[nd], nd);
		}
		else light(r+dr[d], c+dc[d], d);
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
