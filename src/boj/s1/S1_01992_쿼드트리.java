package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_01992_쿼드트리 {
	static StringBuilder sb = new StringBuilder();
	static char[][] image;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		image = new char[N][];
		
		for(int i = 0; i < N; i++) {
			image[i] = br.readLine().toCharArray();
		}
		
		recur(0, 0, N, N);
		
		System.out.println(sb);
	}
	
	static void recur(int sr, int sc, int er, int ec) {
		if(er - sr == 1) {
			sb.append(image[sr][sc]);
			return;
		}
		
		char dot = image[sr][sc];
		boolean same = true;
		
		outer: for(int r = sr; r < er; r++) {
			for(int c = sc; c < ec; c++) {
				if(image[r][c] != dot) {
					same = false;
					break outer;
				}
			}
		}
		
		if(same) {
			sb.append(dot);
		}
		else {
			int len = (er - sr) / 2;
			
			sb.append("(");
			recur(sr, sc, sr+len, sc+len);
			recur(sr, sc+len, sr+len, ec);
			recur(sr+len, sc, er, sc+len);
			recur(sr+len, sc+len, er, ec);
			sb.append(")");
		}
	}
}
