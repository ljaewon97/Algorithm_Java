package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_14501_퇴사 {
	static int[][] couns;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		couns = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			couns[i][0] = Integer.parseInt(st.nextToken());
			couns[i][1] = Integer.parseInt(st.nextToken());
		}
		
		recur(1, 0);
		
		System.out.println(ans);
	}
	
	static void recur(int day, int profit) {
		if(day == N+1) {
			ans = Math.max(ans, profit);
			return;
		}
		
		if(day + couns[day][0] <= N+1) {
			recur(day + couns[day][0], profit + couns[day][1]);
		}
		
		recur(day + 1, profit);
	}
}
