package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_14391_종이_조각 {
	static int[][] paper, bit = new int[5][];
	static boolean[][] used;
	static int[] result;
	static int N, M, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		result = new int[N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				paper[i][j] = line.charAt(j) - '0';
			}
		}
		
		bit[1] = new int[] {0,1};
		bit[2] = new int[] {0,1,2,3};
		bit[3] = new int[] {0,1,2,4,3,6,7};
		bit[4] = new int[] {0,1,2,4,8,3,6,12,7,14,15};
		
		solve(0);
		
		System.out.println(ans);
	}
	
	static void solve(int row) {
		if(row == N) {
			used = new boolean[N][M];
			int sum = 0;
			
			for(int i = 0; i < N; i++) {
				int x = 0, j = 0;
				int n = result[i];
				
				while(n > 0) {
					if(n%2 == 1) {
						x = x*10 + paper[i][j];
						used[i][j] = true;
					}
					
					n /= 2;
					j++;
				}
				
				sum += x;
			}
			
			for(int i = 0; i < M; i++) {
				int cur = 0;
				
				for(int j = 0; j < N; j++) {
					if(used[j][i]) {
						sum += cur;
						cur = 0;
					}
					else {
						cur = cur*10 + paper[j][i];
					}
				}
				
				sum += cur;
			}
			
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i = 0; i < bit[M].length; i++) {
			result[row] = bit[M][i];
			solve(row+1);
		}
	}
}
