package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_02160_그림_비교 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[][][] pics = new char[N+1][5][];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < 5; j++) {
				pics[i][j] = br.readLine().toCharArray();
			}
		}
		
		int diff = Integer.MAX_VALUE;
		int p1 = -1, p2 = -1;
		
		for(int i = 1; i <= N; i++) {
			for(int j = i+1; j <= N; j++) {
				int cnt = 0;
				
				for(int p = 0; p < 5; p++) {
					for(int q = 0; q < 7; q++) {
						if(pics[i][p][q] != pics[j][p][q]) cnt++;
					}
				}
				
				if(cnt < diff) {
					diff = cnt;
					p1 = i;
					p2 = j;
				}
			}
		}
		
		System.out.println(p1 + " " + p2);
	}
}
