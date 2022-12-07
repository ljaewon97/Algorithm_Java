package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class G4_14658_하늘에서_별똥별이_빗발친다 {
	static Set<Integer> X = new HashSet<>();
	static Set<Integer> Y = new HashSet<>();
	static int[][] stars;
	static int N, M, L, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		stars = new int[K][2];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Integer.parseInt(st.nextToken());
			stars[i][1] = Integer.parseInt(st.nextToken());
			
			X.add(stars[i][0]);
			Y.add(stars[i][1]);
		}
		
		int max = 0;
		
		for(int sx: X) {
			for(int sy: Y) {
				max = Math.max(max, countStars(sx, sy));
			}
		}
		
		System.out.println(K - max);
	}
	
	static int countStars(int sx, int sy) {
		int ret = 0;
		int ex = sx + L, ey = sy + L;
		
		for(int i = 0; i < K; i++) {
			if(sx <= stars[i][0] && stars[i][0] <= ex && sy <= stars[i][1] && stars[i][1] <= ey) ret++;
		}
		
		return ret;
	}
}
