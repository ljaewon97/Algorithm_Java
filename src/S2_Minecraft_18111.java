import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_Minecraft_18111 {
	static int[][] map;
	static int minTime = Integer.MAX_VALUE;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int maxHeight = -1;
		int minHeight = 257;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] < minHeight) {
					minHeight = map[i][j];
				}
				if(map[i][j] > maxHeight) {
					maxHeight = map[i][j];
				}
			}
		}
		
		for(int h = minHeight; h <= maxHeight; h++) {
			int time = 0;
			long inven = (long) B;
			boolean isValid = true;
			
			outer: for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] > h) {
						int diff = map[i][j] - h;
						time += diff * 2;
						inven += diff;
					}
					else if(map[i][j] < h) {
						int diff = h - map[i][j];
						time += diff;
						inven -= diff;
					}
					
					if(time > minTime) {
						isValid = false;
						break outer;
					}
				}
			}
			
			if(!isValid || inven < 0) {
				continue;
			}
			
			if(time < minTime) {
				minTime = time;
				ans = h;
			}
			else if(time == minTime && h > ans) {
				ans = h;
			}
		}
		
		System.out.println(minTime + " " + ans);
	}
}
