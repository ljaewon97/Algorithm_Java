package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_5644_무선_충전 {
	static int[] dr = {0,-1,0,1,0};
	static int[] dc = {0,0,1,0,-1};
	static boolean[][][] inAP;
	static int[] APPower;
	static int ans;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			
			int[] aInfo = new int[M];
			int[] bInfo = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				aInfo[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				bInfo[i] = Integer.parseInt(st.nextToken());
			}
			
			inAP = new boolean[A][10][10];
			APPower = new int[A];
			
			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				int C = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				
				APPower[i] = P;
				setInAP(i, r, c, C);
			}
			
			int r1 = 0, c1 = 0, r2 = 9, c2 = 9;
			for(int i = 0; i <= M; i++) {
				Set<Integer> apA = new HashSet<>();
				Set<Integer> apB = new HashSet<>();
				
				for(int j = 0; j < A; j++) {
					if(inAP[j][r1][c1]) apA.add(j);
					if(inAP[j][r2][c2]) apB.add(j);
				}
				
				int max = 0;
				
				if(apA.isEmpty()) {
					for(int curB: apB) {
						int temp = APPower[curB];
						max = Math.max(max, temp);
					}
				}
				else if(apB.isEmpty()) {
					for(int curA: apA) {
						int temp = APPower[curA];
						max = Math.max(max, temp);
					}
				}
				else {
					for(int curA: apA) {
						for(int curB: apB) {
							int temp = APPower[curA];
							if(curA != curB) {
								temp += APPower[curB];
							}
							max = Math.max(max, temp);
						}
					}
				}
				
				ans += max;
				
				if(i == M) break;
				
				r1 += dr[aInfo[i]];
				c1 += dc[aInfo[i]];
				r2 += dr[bInfo[i]];
				c2 += dc[bInfo[i]];
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void setInAP(int num, int sr, int sc, int C) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sr, sc, 0});
		inAP[num][sr][sc] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int dist = cur[2];
			
			if(dist == C) continue;
			
			for(int i = 1; i < 5; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !inAP[num][nr][nc]) {
					inAP[num][nr][nc] = true;
					deque.add(new int[] {nr, nc, dist+1});
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 10 && 0 <= c && c < 10;
	}
}
