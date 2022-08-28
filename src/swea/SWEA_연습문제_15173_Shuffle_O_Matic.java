package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_연습문제_15173_Shuffle_O_Matic {
	static int[] cards, result, temp;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			
			cards = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}		
			
			if(check(cards)) {
				sb.append(String.format("#%d %d\n", t, 0));
				continue;
			}
			
			result = new int[5];
			
			recur(0);
			
			sb.append(String.format("#%d %d\n", t, ans == Integer.MAX_VALUE ? -1 : ans));
		}
		
		System.out.println(sb);
	}
	
	static void recur(int nth) {
		if(nth >= ans) return;
		
		temp = cards.clone();
		
		for(int i = 1; i <= nth; i++) {
			shuffle(result[i-1]);
			if(check(temp)) {
				ans = Math.min(ans, nth);
				return;
			}
		}
		
		if(nth == 5) return;
		
		for(int i = 1; i < N; i++) {
			if(nth > 0 && result[nth-1] == N-1 && i == N-1) continue;
			result[nth] = i;
			recur(nth+1);
		}
	}
	
	static void shuffle(int x) {
		int[] arr = temp.clone();
		int idx = 0, l = 0, half = arr.length / 2, r = half;
		
		if(x < r) {
			for(int i = 0; i < half - 1 - x; i++) {
				temp[idx++] = arr[l++];
			}
			
			while(l < half && r < arr.length) {
				temp[idx++] = arr[l++];
				temp[idx++] = arr[r++];
			}
			
			while(r < arr.length) {
				temp[idx++] = arr[r++];
			}
		}
		else {
			for(int i = 0; i < x - half; i++) {
				temp[idx++] = arr[r++];
			}
			
			while(l < half && r < arr.length) {
				temp[idx++] = arr[r++];
				temp[idx++] = arr[l++];
			}
			
			while(l < half) {
				temp[idx++] = arr[l++];
			}
		}
	}
	
	static boolean check(int[] arr) {
		boolean flagAsc = true, flagDesc = true;
		
		for(int i = 0; i < arr.length-1; i++) {
			if(arr[i] > arr[i+1]) flagAsc = false;
			if(arr[i] < arr[i+1]) flagDesc = false;
			if(!flagAsc && !flagDesc) return false;
		}
		
		return true;
	}
}
