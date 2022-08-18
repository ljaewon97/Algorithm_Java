package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D5_1247_최적_경로 {
	static int[][] cords;
	static int[] result;
	static boolean[] visited;
	static int N, cr, cc, hr, hc, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			cords = new int[N][2];
			result = new int[N];
			visited = new boolean[N];
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			cr = Integer.parseInt(st.nextToken());
			cc = Integer.parseInt(st.nextToken());
			hr = Integer.parseInt(st.nextToken());
			hc = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				cords[i][0] = Integer.parseInt(st.nextToken());
				cords[i][1] = Integer.parseInt(st.nextToken());
			}
			
			perm(0);
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void perm(int nth) {
		if(nth == N) {
			int temp = 0;
			
			temp += distance(cr, cc, cords[result[0]][0], cords[result[0]][1]);
			for(int i = 0; i < N-1; i++) {
				temp += distance(cords[result[i]][0], cords[result[i]][1], cords[result[i+1]][0], cords[result[i+1]][1]);
			}
			temp += distance(hr, hc, cords[result[N-1]][0], cords[result[N-1]][1]);
			
			ans = Math.min(ans, temp);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				result[nth] = i;
				visited[i] = true;
				perm(nth+1);
				visited[i] = false;
			}
		}
	}
	
	static int distance(int r1, int c1, int r2, int c2) {
		return Math.abs(r2 - r1) + Math.abs(c2 - c1);
	}
}
