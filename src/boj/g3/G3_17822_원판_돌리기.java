package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G3_17822_원판_돌리기 {
	static int N, M, T, CNT, SUM = 0;
	static int[][] circles;
	static int[] spin, dr, dc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		circles = new int[N+1][M];
		spin = new int[N+1];
		CNT = N * M;
		dr = new int[] {-1,1,0,0};
		dc = new int[] {0,0,M-1,1};
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				circles[i][j] = Integer.parseInt(st.nextToken());
				SUM += circles[i][j];
			}
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			for(int j = x; j <= N; j += x) {
				rotate(j, d, k);
			}
			
			if(CNT > 0) {
				boolean[][] visited = new boolean[N+1][M];
				int removeCnt = 0;
				
				for(int p = 1; p <= N; p++) {
					for(int q = 0; q < M; q++) {
						if(!visited[p][q] && circles[p][q] != 0) {
							removeCnt += remove(p, q, circles[p][q], visited);
						}
					}
				}
				
				if(removeCnt == 0) {
					averagePlusMinus();
				}
			}
		}
		
		System.out.println(SUM);
	}
	
	static void rotate(int num, int dir, int step) {
		if(dir == 0) {
			spin[num] += (M - 1) * step;
		}
		else {
			spin[num] += 1 * step;
		}
	}
	
	static int remove(int n, int m, int num, boolean[][] visited) {
		Deque<int[]> deque = new LinkedList<>();
		Deque<int[]> pend = new LinkedList<>();
		int cnt = 0;
		
		deque.add(new int[] {n, m});
		visited[n][m] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			
			pend.add(new int[] {r, c});
			cnt++;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				if(nr < 1 || nr > N) continue;
				int nc = (c + spin[nr]%M + M - spin[r]%M + dc[i]) % M;
				
				if(circles[nr][nc] == num && !visited[nr][nc]) {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc});
				}
			}
		}
		
		if(cnt > 1) {
			while(!pend.isEmpty()) {
				int[] cur = pend.poll();
				SUM -= circles[cur[0]][cur[1]];
				circles[cur[0]][cur[1]] = 0;
			}
			
			CNT -= cnt;
			return cnt;
		}
		
		return 0;
	}
	
	static void averagePlusMinus() {
		double avg = 1.0 * SUM / CNT;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(circles[i][j] != 0) {
					
					if(circles[i][j] < avg) {
						circles[i][j]++;
						SUM++;
					}
					else if(circles[i][j] > avg) {
						circles[i][j]--;
						SUM--;
					}
				}
			}
		}
	}
}
