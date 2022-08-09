package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_6808_규영이와_인영이의_카드게임 {
	static int[] a, b, result;
	static boolean[] vis;
	static int  a1, a2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			a1 = 0; a2 = 0;
			a = new int[9];
			b = new int[9];
			result = new int[9];
			vis = new boolean[9];
			boolean[] visited = new boolean[19];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 9; i++) {
				a[i] = Integer.parseInt(st.nextToken());
				visited[a[i]] = true;
			}
			
			for(int i = 1, idx = 0; i <= 18; i++) {
				if(!visited[i]) {
					b[idx++] = i;
				}
			}
			
			perm(0, 0);
			sb.append(String.format("#%d %d %d\n", t, a1, a2));
		}
		
		System.out.println(sb);
	}
	
	static void perm(int nth, int start) {
		if(nth == 9) {
			int s1 = 0, s2 = 0;
			for(int i = 0; i < 9; i++) {
				if(a[i] > result[i]) {
					s1 += a[i] + result[i];
				}
				else {
					s2 += a[i] + result[i];
				}
			}
			
			if(s1 > s2) {
				a1++;
			}
			else if(s1 < s2) {
				a2++;
			}
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if(!vis[i]) {
				result[nth] = b[i];
				vis[i] = true;
				perm(nth+1, i+1);
				vis[i] = false;
			}
			
		}
	}
}
