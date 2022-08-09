package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_02961_도영이가_만든_맛있는_음식 {
	static int[][] food;
	static boolean[] result;
	static int N, ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		food = new int[N][2];
		result = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			food[i][0] = s;
			food[i][1] = b;
		}
		
		powerSet(0);
		
		System.out.println(ans);
	}
	
	static void powerSet(int n) {
		if(n == N) {
			int sum = 0;
			int multi = 1;
			int cnt = 0;
			
			for(int i = 0; i < N; i++) {
				if(result[i]) {
					multi *= food[i][0];
					sum += food[i][1];
					cnt++;
				}
			}
			
			if(cnt > 0 && Math.abs(sum - multi) < ans) {
				ans = Math.abs(sum - multi);
			}
			return;
		}
		
		result[n] = true;
		powerSet(n+1);
		
		result[n] = false;
		powerSet(n+1);
	}
}
