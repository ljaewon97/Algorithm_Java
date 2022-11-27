package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_26073_외로운_곰곰이는_친구가_있어요 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int X = Math.abs(Integer.parseInt(st.nextToken()));
			int Y = Math.abs(Integer.parseInt(st.nextToken()));
			
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			int[] A = new int[K];
			
			for(int i = 0; i < K; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			boolean flagX = false, flagY = false;
			
			int dist = A[0];
			
			for(int i = 0; i < K; i++) {
				dist = gcd(dist, A[i]);
				
				if(X % dist == 0) flagX = true;
				if(Y % dist == 0) flagY = true;
				
				if(flagX && flagY) break;
			}
			
			if(flagX && flagY) sb.append("Ta-da\n");
			else sb.append("Gave up\n");
		}
		
		System.out.println(sb);
	}
	
	static int gcd(int a, int b) {
		if(a % b == 0) return b;
		return gcd(b, a % b);
	}
}
