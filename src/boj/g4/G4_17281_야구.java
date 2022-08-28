package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_17281_야구 {
	static final int MASK = 7;
	static int[][] players;
	static int[] result;
	static boolean[] visited;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		players = new int[N][9];
		visited = new boolean[9];
		result = new int[9];
		
		visited[0] = true;
		result[3] = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0);
		
		System.out.println(ans);
	}
	
	static void perm(int nth) {
		if(nth == 9) {
			play();
			return;
		}
		
		for(int i = 1; i < 9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[nth] = i;
				perm(nth + (nth == 2 ? 2 : 1));
				visited[i] = false;
			}
		}
	}
	
	static void play() {
		int score = 0;
		int hitter = 0;
		
		for(int i = 0; i < N; i++) {
			int base = 0;
			int outcount = 0;
			
			while(outcount != 3) {
				int cur = players[i][result[hitter]];
				
				if(cur == 1) {
					if((base & 1 << 2) != 0) score++;
					base <<= 1;
					base &= MASK;
					base |= 1 << 0;
					hitter = (hitter + 1) % 9;
				}
				else if(cur == 2) {
					if((base & 1 << 2) != 0) score++;
					if((base & 1 << 1) != 0) score++;
					base <<= 2;
					base &= MASK;
					base |= 1 << 1;
					hitter = (hitter + 1) % 9;
				}
				else if(cur == 3) {
					if((base & 1 << 2) != 0) score++;
					if((base & 1 << 1) != 0) score++;
					if((base & 1 << 0) != 0) score++;
					base = 1 << 2;
					hitter = (hitter + 1) % 9;
				}
				else if(cur == 4) {
					int cnt = 0;
					
					for(int j = 0; j < 3; j++) {
						if((base & 1 << j) != 0) {
							cnt++;
						}
					}
					
					base = 0;
					score += cnt + 1;
					hitter = (hitter + 1) % 9;
				}
				else if(cur == 0) {
					outcount++;
					hitter = (hitter + 1) % 9;
				}	
			}
		}
		
		ans = Math.max(ans, score);
	}
}
