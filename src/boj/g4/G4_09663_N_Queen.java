package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_09663_N_Queen {
	static int N, ans = 0;
	static int[] result;
	static int len = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	
		result = new int[N];
		
		recur(0);
		
		System.out.println(ans);
	}
	
	static void recur(int nth) {
		if(nth == N) {
			ans++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			boolean check = true;
			
			for(int j = 0; j < len; j++) {
				if(nth == j || i == result[j] || Math.abs(nth - j) == Math.abs(i - result[j])) {
					check = false;
					break;
				}
			}
			
			if(check) {
				result[nth] = i;
				len++;
				recur(nth+1);
				len--;
			}
		}
	}
}
