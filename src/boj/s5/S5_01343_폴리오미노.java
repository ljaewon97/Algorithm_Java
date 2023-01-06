package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_01343_폴리오미노 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String board = br.readLine();
		
		int piece = 0;
		
		for(int i = 0; i < board.length(); i++) {
			if(board.charAt(i) == 'X') {
				piece++;
			}
			else {
				if(piece > 0) {
					if(piece % 2 == 1) {
						System.out.println(-1);
						return;
					}
					else {
						for(int j = 0; j < piece/4; j++) {
							sb.append("AAAA");
						}
						
						piece %= 4;
						
						if(piece == 2) sb.append("BB");
						
						piece = 0;
					}
				}
				
				sb.append(".");
			}
		}
		
		if(piece > 0) {
			if(piece % 2 == 1) {
				System.out.println(-1);
				return;
			}
			
			for(int i = 0; i < piece/4; i++) {
				sb.append("AAAA");
			}
			
			piece %= 4;
			
			if(piece == 2) sb.append("BB");
			
			piece = 0;
		}
		
		System.out.println(sb);
	}
}
