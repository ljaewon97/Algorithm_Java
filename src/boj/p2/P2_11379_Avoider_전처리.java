package boj.p2;

public class P2_11379_Avoider_전처리 {
	static boolean[][] visited = new boolean[29][29];
	static long[] count;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		count = new long[29];
		
		visited[0][0] = visited[0][1] = true;
		
		dfs(0, 1, 1);
		
		sb.append("{");
		
		for(int i = 0; i < 28; i++) {
			sb.append(count[i]).append(", ");
		}
		
		sb.append(count[28]).append("}");
		
		System.out.println(sb);
	}
	
	static void dfs(int r, int c, int d) {
		count[d]++;
		
		if(d == 28) return;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, d+1);
				visited[nr][nc] = false;
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0;
	}
}
