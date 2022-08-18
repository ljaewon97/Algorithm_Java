package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_01405_미친_로봇 {
	static boolean[][] visited;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static int[] prob;
	static int N;
	static double allCase, complCase;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		prob = new int[4];
		allCase = 100 * (1 << 2*N);
		
		for(int i = 0; i < 4; i++) {
			prob[i] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[2*N+1][2*N+1];
		visited[N][N] = true;
		dfs(N, N, 0, allCase);
		
		System.out.println((allCase - complCase) / allCase);
	}
	
	static void dfs(int r, int c, int d, double caseNum) {
		if(d == N) return;
		
		for(int i = 0; i < 4; i++) {
			if(prob[i] == 0) continue;
			
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc, d+1, prob[i] * caseNum / 100);
				visited[nr][nc] = false;
			}
			else {
				complCase += prob[i] * caseNum / 100;
			}
		}
	}
}
