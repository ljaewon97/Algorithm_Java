package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_12869_뮤탈리스크 {
	static List<int[]> dmgs = new ArrayList<>();
	static int[] damages = {9,3,1};
	static int[] choosed = new int[3];
	static boolean[] visited = new boolean[3];
	static int[][][] hp = new int[61][61][61];
	static int N, ans = 50;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[] scv = new int[3];
		
		perm(0);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		
		attack(scv[0], scv[1], scv[2], 0);
		
		System.out.println(ans);
	}
	
	static void attack(int hp1, int hp2, int hp3, int cnt) {
		if(hp1 <= 0 && hp2 <= 0 && hp3 <= 0) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		hp1 = Math.max(hp1, 0);
		hp2 = Math.max(hp2, 0);
		hp3 = Math.max(hp3, 0);
		
		if(hp[hp1][hp2][hp3] != 0 && cnt >= hp[hp1][hp2][hp3]) return;
		
		hp[hp1][hp2][hp3] = cnt;
		
		for(int[] dmg: dmgs) {
			attack(hp1-dmg[0], hp2-dmg[1], hp3-dmg[2], cnt+1);
		}
	}
	
	static void perm(int nth) {
		if(nth == 3) {
			int[] temp = choosed.clone();
			dmgs.add(temp);
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = damages[i];
				perm(nth+1);
				visited[i] = false;
			}
		}
	}
}
