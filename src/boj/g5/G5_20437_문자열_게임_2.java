package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_20437_문자열_게임_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			char[] W = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());
			int len3 = 10001, len4 = -1;
			int[] count = new int[26];
			int[] prevK = new int[26];
			int[] prev = new int[26];
			int[] nxt = new int[W.length];
			
			for(int i = 0; i < W.length; i++) {
				int c = W[i] - 'a';
				count[c]++;
				
				if(count[c] == 1) prevK[c] = i;
				if(count[c] >= 2) nxt[prev[c]] = i;
				prev[c] = i;
				
				if(count[c] == K) {
					len3 = Math.min(len3, i-prevK[c]+1);
					len4 = Math.max(len4, i-prevK[c]+1);
				}
				else if(count[c] > K) {
					prevK[c] = nxt[prevK[c]];
					len3 = Math.min(len3, i-prevK[c]+1);
					len4 = Math.max(len4, i-prevK[c]+1);
				}
			}
			
			if(len3 == 10001) sb.append(-1).append("\n");
			else sb.append(len3).append(" ").append(len4).append("\n");
		}
		
		System.out.println(sb);
	}
}
