package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_27445_Gorani_Command {
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] rdist = new int[N];
		int[] cdist = new int[M];
		
		for(int i = 0; i < N-1; ++i) {
			rdist[i] = Integer.parseInt(br.readLine());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; ++i) {
			cdist[i] = Integer.parseInt(st.nextToken());
		}
		
		rdist[N-1] = cdist[0];
		
		int min = Integer.MAX_VALUE;
		int r = -1;
		
		for(int i = 0; i < N; ++i) {
			if(rdist[i] < min) {
				min = rdist[i];
				r = i+1;
			}
		}
		
		min = Integer.MAX_VALUE;
		int c = -1;
		
		for(int i = 0; i < M; ++i) {
			if(cdist[i] < min) {
				min = cdist[i];
				c = i+1;
			}
		}
		
		System.out.println(r + " " + c);
	}
}
