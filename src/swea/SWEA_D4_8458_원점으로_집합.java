package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_8458_원점으로_집합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int max = 0;
			boolean even = false, odd = false;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
				
				if(num % 2 == 0) even = true;
				else odd = true;
				
				max = Math.max(max, num);
			}
			
			if(even && odd) {
				sb.append(String.format("#%d -1\n", t));
				continue;
			}
			
			long step = (long) ((-1+Math.sqrt(1+(long)8*max))/2);
			if(step*(step+1)/2 == max) {
				sb.append(String.format("#%d %d\n", t, step));
				continue;
			}
			else step++;
			
			long diff = step*(step+1)/2 - max;
			long ans = diff % 2 == 0 ? step : (step % 2 == 0 ? step+1 : step+2);
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
}
