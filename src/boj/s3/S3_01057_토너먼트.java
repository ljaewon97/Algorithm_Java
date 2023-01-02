package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_01057_토너먼트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int I = Integer.parseInt(st.nextToken());
		
		int round = 1;
		
		while(true) {
			if((K+1)/2 == (I+1)/2) break;
			
			K = (K+1)/2;
			I = (I+1)/2;
			
			round++;
		}
		
		System.out.println(round);
	}
}
