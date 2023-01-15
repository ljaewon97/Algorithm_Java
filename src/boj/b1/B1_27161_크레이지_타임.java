package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_27161_크레이지_타임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int hour = 12;
		boolean dir = true;
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int h = Integer.parseInt(st.nextToken());
			
			hour = dir ? (hour == 12 ? 1 : hour+1) : (hour == 1 ? 12 : hour-1);
			String act = hour == h ? "YES" : "NO";
			
			if(type.equals("HOURGLASS")) {
				if(act.equals("YES")) {
					act = "NO";
				}
				else {
					dir = !dir;
				}
			}
			
			sb.append(hour).append(" ").append(act).append("\n");
		}
		
		System.out.println(sb);
	}
}
