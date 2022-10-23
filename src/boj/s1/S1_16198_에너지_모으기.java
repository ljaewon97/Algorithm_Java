package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_16198_에너지_모으기 {
	static int[][] side;
	static int[] arr;
	static boolean[] removed;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		side = new int[N][2];
		arr = new int[N];
		removed = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i != 0) side[i][0] = i-1;
			if(i != N-1) side[i][1] = i+1;
		}
		
		solve(0, 0);
		
		System.out.println(ans);
	}
	
	static void solve(int nth, int energy) {
		if(nth == N-2) {
			ans = Math.max(ans, energy);
			return;
		}
		
		for(int i = 1; i < N-1; i++) {
			if(!removed[i]) {
				removed[i] = true;
				int l = side[i][0];
				int r = side[i][1];
				int t1 = side[l][1];
				int t2 = side[r][0];
				side[l][1] = r;
				side[r][0] = l;
				solve(nth+1, energy+arr[l]*arr[r]);
				side[l][1] = t1;
				side[r][0] = t2;
				removed[i] = false;
			}
		}
	}
}
