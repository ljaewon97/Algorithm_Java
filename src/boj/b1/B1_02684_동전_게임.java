package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_02684_동전_게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int P = Integer.parseInt(br.readLine());
		int bit = 3;
		
		while(P-- > 0) {
			char[] coins = br.readLine().toCharArray();
			int[] ans = new int[8];
			
			int coin = 0;
			for(int i = 0; i < 3; i++) {
				coin *= 2;
				if(coins[i] == 'H') coin++;
			}
			ans[coin]++;
			
			for(int i = 3; i < 40; i++) {
				coin &= bit;
				coin *= 2;
				if(coins[i] == 'H') coin++;
				ans[coin]++;
			}
			
			for(int i = 0; i < 8; i++) {
				sb.append(ans[i]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
