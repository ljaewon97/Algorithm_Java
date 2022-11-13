package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_03019_테트리스 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		int[] h = new int[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][][] blocks = {{{0}, {0,0,0,0}}, {{0,0}}, {{1,1,0}, {0,1}}, {{0,1,1}, {1,0}},
				{{0,0,0}, {0,1,0}, {1,0}, {0,1}}, {{0,0,0}, {0,0}, {1,0,0}, {0,2}}, {{0,0,0}, {0,0}, {2,0}, {0,0,1}}};
		
		int[][] block = blocks[P-1];
		int ans = 0;
		
		for(int[] shape: block) {
			if(C < shape.length) continue;
			
			for(int i = 0; i < C-shape.length+1; i++) {
				int temp = h[i] + shape[0];
				boolean fit = true;
				
				for(int j = 1; j < shape.length; j++) {
					if(temp != h[i+j] + shape[j]) {
						fit = false;
						break;
					}
				}
				
				if(fit) ans++;
			}
		}
		
		System.out.println(ans);
	}
}
