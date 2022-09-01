package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2_16985_Maaaaaaaaaze {
	static List<int[]> orders = new LinkedList<>();
	static int[][][][] map = new int[5][4][5][5];
	static boolean[][][] visited;
	static int[] result = new int[5];
	static int[] temp = new int[5];
	static boolean[] vtemp = new boolean[5];
	static int[] dr = {-1,1,0,0,0,0};
	static int[] dc = {0,0,-1,1,0,0};
	static int[] dh = {0,0,0,0,-1,1};
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 5; k++) {
					map[i][0][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		for(int i = 1; i < 4; i++) {
			for(int j = 0; j < 5; j++) {
				map[j][i] = rotate(map[j][i-1]);
			}
		}
		
		perm(0);
		recur(1);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void perm(int nth) {
		if(nth == 5) {
			int[] arr = new int[5];
			for(int i = 0; i < 5; i++) {
				arr[i] = temp[i];
			}
			
			orders.add(arr);
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			if(!vtemp[i]) {
				vtemp[i] = true;
				temp[nth] = i;
				perm(nth+1);
				vtemp[i] = false;
			}
		}
	}
	
	static void recur(int nth) {
		if(nth == 5) {
			for(int[] order: orders) {
				if(map[order[0]][0][0][0] == 1 && map[order[4]][result[4]][4][4] == 1) bfs(order, 0, 0, 4, 4);
				if(map[order[0]][0][0][4] == 1 && map[order[4]][result[4]][4][0] == 1) bfs(order, 0, 4, 4, 0);
				if(map[order[0]][0][4][0] == 1 && map[order[4]][result[4]][0][4] == 1) bfs(order, 4, 0, 0, 4);
				if(map[order[0]][0][4][4] == 1 && map[order[4]][result[4]][0][0] == 1) bfs(order, 4, 4, 0, 0);
			}
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			result[nth] = i;
			recur(nth+1);
		}
	}
	
	static void bfs(int[] order, int sr, int sc, int er, int ec) {
		visited = new boolean[5][5][5];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0, sr, sc, 0});
		visited[0][sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int h = cur[0];
			int r = cur[1];
			int c = cur[2];
			int d = cur[3];
			
			if(d >= ans) return;
			
			if(h == 4 && r == er && c == ec) {
				ans = Math.min(ans, d);
				return;
			}
			
			for(int i = 0; i < 6; i++) {
				int nh = h + dh[i];
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isOut(nr, nc, nh)) continue;
				
				if(!visited[nh][nr][nc] && map[order[nh]][result[nh]][nr][nc] == 1) {
					visited[nh][nr][nc] = true;
					queue.add(new int[] {nh, nr, nc, d+1});
				}
			}
		}
	}
	
	static int[][] rotate(int[][] arr) {
		int[][] res = new int[5][5];
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				res[i][j] = arr[4-j][i];
			}
		}
		
		return res;
	}
	
	static boolean isOut(int r, int c, int h) {
		return r < 0 || r >= 5 || c < 0 || c >= 5 || h < 0 || h >= 5;
	}
}
