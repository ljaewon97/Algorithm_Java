package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_25825_빠른_무작위_메시지_전달 {
	static int[][] time = new int[12][12];
	static int ans = Integer.MAX_VALUE;
	static int[] result = new int[6];
	static boolean[] visited = new boolean[6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int i = 0; i < 12; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 12; j++) {
				time[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve(0);
		
		int temp = 0;
		
		for(int i = 0; i < 12; i += 2) {
			temp += time[i][i+1];
		}
		
		System.out.println(ans + temp);
	}
	
	static void solve(int nth) {
		if(nth == 6) {
			int sum = 0;
			int prev = result[0] + (result[0] % 2 == 1 ? -1 : 1);
			
			for(int i = 1; i < 6; i++) {
				int cur = result[i];
				sum += time[prev][cur];
				prev = cur + (cur % 2 == 1 ? -1 : 1);
			}
			
			ans = Math.min(ans, sum);
			return;
		}
		
		for(int i = 0; i < 6; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[nth] = 2 * i;
				solve(nth+1);
				result[nth] = 2 * i + 1;
				solve(nth+1);
				visited[i] = false;
			}
		}
	}
}
