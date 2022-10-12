package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_02755_이번학기_평점은_몇점 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		double sum = 0, timesum = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int time = Integer.parseInt(st.nextToken());
			String grade = st.nextToken();
			double gp = 0;
			
			gp += 69 - grade.charAt(0);
			
			if(gp == -1) {
				timesum += time;
				continue;
			}
			
			if(grade.charAt(1) == '+') gp += 0.3;
			else if(grade.charAt(1) == '-') gp -= 0.3;
			
			sum += gp * time;
			timesum += time;
		}
		
		System.out.printf("%.2f\n", sum / timesum);
	}
}
