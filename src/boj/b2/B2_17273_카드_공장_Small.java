package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_17273_카드_공장_Small {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] card = new int[2];
		
		st = new StringTokenizer(br.readLine());
		card[0] = Integer.parseInt(st.nextToken());
		card[1] = Integer.parseInt(st.nextToken());
		
		int cur = 0;
		
		while(M-- > 0) {
			int cmd = Integer.parseInt(br.readLine());
			if(card[cur] <= cmd) cur = 1 - cur;
		}
		
		System.out.println(card[cur]);
	}
}
