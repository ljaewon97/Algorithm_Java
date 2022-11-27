package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_26075_곰곰아_선넘지마 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		long sum = 0;
		
		int[] loc = new int[M];
		
		String a = br.readLine();
		int idx = 0;
		
		for(int i = 0; i < N+M; i++) {
			if(a.charAt(i) == '1') loc[idx++] = i;
		}
		
		String b = br.readLine();
		idx = 0;
		
		for(int i = 0; i < N+M; i++) {
			if(b.charAt(i) == '1') sum += Math.abs(loc[idx++]-i);
		}
		
		long X = sum / 2;
		long Y = sum - X;
		
		System.out.println(X*X + Y*Y);
	}
}
