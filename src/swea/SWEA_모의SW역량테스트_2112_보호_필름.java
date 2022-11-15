package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의SW역량테스트_2112_보호_필름 {
	static char[][] film, temp;
	static int[] result;
	static boolean[] drug;
	static int D, W, K, N, ans;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			flag = false;
			
			film = new char[D][W];
			temp = new char[D][W];
			
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					film[i][j] = temp[i][j] = st.nextToken().charAt(0);
				}
			}
			
			if(pass()) {
				sb.append(String.format("#%d %d\n", t, 0));
				continue;
			}
			
			for(N = 1; N <= D; N++) {
				result = new int[N];
				drug = new boolean[N];
				comb(0, 0);
				if(flag) {
					ans = N;
					break;
				}
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void comb(int nth, int start) {
		if(flag) return;
		if(nth == N) {
			subset(0);
			return;
		}
		
		for(int i = start; i < D; i++) {
			result[nth] = i;
			comb(nth+1, i+1);
		}
	}
	
	static void subset(int nth) {
		if(flag) return;
		if(nth == N) {
			copy();
			for(int i = 0; i < N; i++) {
				change(result[i], drug[i] ? '1' : '0');
			}
			if(pass()) flag = true;
			return;
		}
		
		drug[nth] = true;
		subset(nth+1);
		drug[nth] = false;
		subset(nth+1);
	}
	
	static void change(int r, char c) {
		for(int i = 0; i < W; i++) {
			temp[r][i] = c;
		}
	}
	
	static boolean pass() {
		for(int c = 0; c < W; c++) {
			int max = 0;
			char prev = temp[0][c];
			int con = 1;
			for(int r = 1; r < D; r++) {
				if(temp[r][c] != prev) {
					max = Math.max(max, con);
					con = 1;
					prev = temp[r][c];
				}
				else {
					con++;
				}
			}
			max = Math.max(max, con);
			if(max < K) return false;
		}
		
		return true;
	}
	
	static void copy() {
		for(int r = 0; r < D; r++) {
			for(int c = 0; c < W; c++) {
				temp[r][c] = film[r][c];
			}
		}
	}
}
