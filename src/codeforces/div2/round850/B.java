package codeforces.div2.round850;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			int[] cake = new int[n];
			int[] disp = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; ++i) {
				cake[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; ++i) {
				disp[i] = Integer.parseInt(st.nextToken());
			}
			
			boolean ans = true;
			int sdiff = disp[0]-h-cake[0]+w;
			int ediff = disp[0]+h-cake[0]-w;
			
			int min = Math.min(sdiff, ediff);
			int max = Math.max(sdiff, ediff);
			
			for(int i = 1; i < n; ++i) {
				sdiff = disp[i]-h-cake[i]+w;
				ediff = disp[i]+h-cake[i]-w;
				
				int tmin = Math.min(sdiff, ediff);
				int tmax = Math.max(sdiff, ediff);
				
				if(tmin > max || tmax < min) {
					ans = false;
					break;
				}
				
				min = Math.max(min, tmin);
				max = Math.min(max, tmax);
			}
			
			sb.append(ans ? "YES\n" : "NO\n");
		}
		
		System.out.println(sb);
	}
}
