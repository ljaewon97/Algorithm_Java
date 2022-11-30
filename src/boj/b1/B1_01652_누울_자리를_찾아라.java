package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_01652_누울_자리를_찾아라 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		char[][] map2 = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = map2[j][i] = line.charAt(j);
			}
		}
		
		System.out.println(count(map) + " " + count(map2));
	}
	
	static int count(char[][] arr) {
		int N = arr.length;
		int ret = 0;
		
		for(int i = 0; i < N; i++) {
			int s = 0;
			
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 'X') {
					if(s >= 2) ret++;
					s = 0;
				}
				else s++;
			}
			
			if(s >= 2) ret++;
		}
		
		return ret;
	}
}
