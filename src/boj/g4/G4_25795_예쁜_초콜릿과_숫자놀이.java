package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_25795_예쁜_초콜릿과_숫자놀이 {
	static boolean[] choco;
	static long ans;
	static int N, a, b, c, N2;
	static final int MOD = 100000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		N2 = 2 * N;
		
		choco = new boolean[N2];
		
		if(N == 1) {
			System.out.println((long)(a+b)*c%MOD);
			return;
		}
		
		solve(0, 0, 0, a);
		
		System.out.println(ans);
	}
	
	static void solve(int nth, int white, int dark, long score) {
		if(nth == N2) {
			ans = Math.max(ans, score);
			return;
		}
		
		if(white == dark) {
			choco[nth] = true;
			solve(nth+1, white+1, dark, (score+b)%MOD);
		}
		else {
			if(white != N) {
				choco[nth] = true;
				solve(nth+1, white+1, dark, (score+b)%MOD);
				choco[nth] = false;
				solve(nth+1, white, dark+1, score*c%MOD);
			}
			else {
				choco[nth] = false;
				solve(nth+1, white, dark+1, score*c%MOD);
			}
		}
	}
}
