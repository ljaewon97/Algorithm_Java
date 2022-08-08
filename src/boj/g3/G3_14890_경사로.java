package boj.g3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_14890_경사로 {
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가로 방향
		for(int r = 0; r < N; r++) {
			boolean canPass = true;
			int same = 1;
			for(int c = 1; c < N; c++) {
				if(map[r][c-1] + 1 == map[r][c]) {
					if(same < L) {
						canPass = false;
						break;
					}
				}
				else if(map[r][c-1] == map[r][c] + 1) {
					for(int i = 1; i < L; i++) {
						int nc = c + i;
						if(nc < 0 || nc >= N || map[r][nc] != map[r][c]) {
							canPass = false;
							break;
						}
					}
					c += L - 1;
					same = 0;
					continue;
				}
				else if(Math.abs(map[r][c] - map[r][c-1]) > 1) {
					canPass = false;
					break;
				}
				
				if(map[r][c-1] == map[r][c]) {
					same++;
				}
				else {
					same = 1;
				}
			}
			
			if(canPass) {
				ans++;
			}
		}
		
		// 세로 방향
		for(int c = 0; c < N; c++) {
			boolean canPass = true;
			int same = 1;
			for(int r = 1; r < N; r++) {
				if(map[r-1][c] + 1 == map[r][c]) {
					if(same < L) {
						canPass = false;
						break;
					}
				}
				else if(map[r-1][c] == map[r][c] + 1) {
					for(int i = 1; i < L; i++) {
						int nr = r + i;
						if(nr < 0 || nr >= N || map[nr][c] != map[r][c]) {
							canPass = false;
							break;
						}
					}
					r += L - 1;
					same = 0;
					continue;
				}
				else if(Math.abs(map[r][c] - map[r-1][c]) > 1) {
					canPass = false;
					break;
				}
				
				if(map[r-1][c] == map[r][c]) {
					same++;
				}
				else {
					same = 1;
				}
			}
			
			if(canPass) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
