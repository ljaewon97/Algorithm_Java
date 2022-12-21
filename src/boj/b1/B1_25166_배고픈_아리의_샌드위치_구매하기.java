package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_25166_배고픈_아리의_샌드위치_구매하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		if(S <= 1023) System.out.println("No thanks");
		else {
			int money = S - 1023;
			
			for(int c = 512; c >= 1; c /= 2) {
				if(M >= c) {
					M -= c;
					if(money >= c) {
						money -= c;
					}
				}
			}
			
			if(money == 0) System.out.println("Thanks");
			else System.out.println("Impossible");
		}
	}
}
