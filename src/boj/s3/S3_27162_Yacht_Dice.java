package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_27162_Yacht_Dice {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String ops = br.readLine();
		int[] d = new int[3];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 3; i++) {
			d[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		
		boolean[] check = new boolean[7];
		int cnt = 0;
		
		for(int i = 0; i < 3; i++) {
			if(!check[d[i]]) {
				check[d[i]] = true;
				cnt++;
			}
		}
		
		// Ones ~ Sixes
		for(int i = 0; i < 6; i++) {
			if(ops.charAt(i) == 'Y') {
				int c = 0;
				
				for(int j = 0; j < 3; j++) {
					if(d[j] == i+1) c++;
				}
				
				ans = Math.max(ans, (i+1)*(c+2));
			}
		}
		
		// Four of a Kind
		if(ops.charAt(6) == 'Y') {
			int[] count = new int[7];
			
			for(int i = 0; i < 3; i++) {
				count[d[i]]++;
				if(count[d[i]] == 2) {
					ans = Math.max(ans, d[i]*4);
				}
			}
		}
		
		// Full House
		if(ops.charAt(7) == 'Y') {
			if(cnt == 1) {
				int x = -1;
				for(int i = 1; i <= 6; i++) {
					if(check[i]) x = i;
				}
				
				if(x == 6) ans = Math.max(ans, 28);
				else ans = Math.max(ans, x*3+12);
			}
			else if(cnt == 2) {
				int a = -1, b = -1;
				boolean det = false;
				
				for(int i = 1; i <= 6; i++) {
					if(check[i]) {
						if(!det) {
							a = i;
							det = true;
						}
						else b = i;
					}
				}
				
				ans = Math.max(ans, 2*a+3*b);
				ans = Math.max(ans, 3*a+2*b);
			}
		}
		
		// Little Straight
		if(ops.charAt(8) == 'Y') {
			if(cnt == 3 && !check[6]) ans = Math.max(ans, 30); 
		}
		
		// Big Straight
		if(ops.charAt(9) == 'Y') {
			if(cnt == 3 && !check[1]) ans = Math.max(ans, 30);
		}
		
		// Yacht
		if(ops.charAt(10) == 'Y') {
			if(cnt == 1) {
				for(int i = 1; i <= 6; i++) {
					if(check[i]) ans = Math.max(ans, 50);
				}
			}
		}
		
		// Choice
		if(ops.charAt(11) == 'Y') {
			int sum = 0;
			
			for(int i = 0; i < 3; i++) {
				sum += d[i];
			}
			
			ans = Math.max(ans, sum+12);
		}
		
		System.out.println(ans);
	}
}
