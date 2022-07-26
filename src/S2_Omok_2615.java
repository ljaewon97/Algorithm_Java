

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_Omok_2615 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] plate = new int[19][19];
		int[] dr = {-1,-1,0,1,1,1,0,-1};
		int[] dc = {0,1,1,1,0,-1,-1,-1};
		int winner = 0;
		int[][] winnerCord = new int[5][2];
		boolean isFin = false;
		
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				plate[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 0; r < 19; r++) {
			for(int c = 0; c < 19; c++) {
				if(plate[r][c] == 1 || plate[r][c] == 2) {
					for(int i = 0; i < 4; i++) {
						int nr = r, nc = c, cnt = 1, cur = plate[r][c];
						while(true) {
							nr += dr[i];
							nc += dc[i];
							if(nr < 0 || nr >= 19 || nc < 0 || nc >= 19 || plate[nr][nc] != cur) {
								break;
							}
							else {
								cnt++;
							}
						}
						nr = r;
						nc = c;
						while(true) {
							nr += dr[i+4];
							nc += dc[i+4];
							if(nr < 0 || nr >= 19 || nc < 0 || nc >= 19 || plate[nr][nc] != cur) {
								break;
							}
							else {
								cnt++;
							}
						}
						if(cnt == 5) {
							isFin = true;
							winner = cur;
							for(int j = 0; j < 5; j++) {
								nr -= dr[i+4];
								nc -= dc[i+4];
								winnerCord[j][0] = nr;
								winnerCord[j][1] = nc;
							}
							break;
						}
					}
				}
				if(isFin) break;
			}
			if(isFin) break;
		}
		if(winner == 0) {
			System.out.println(winner);
		}
		else {
			System.out.println(winner);
			Arrays.sort(winnerCord, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
			System.out.printf("%d %d\n", winnerCord[0][0]+1, winnerCord[0][1]+1);
		}
	}
}