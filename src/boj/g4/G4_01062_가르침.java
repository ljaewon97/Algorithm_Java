package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_01062_가르침 {
	static int[] words;
	static int N, K, wbit, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		words = new int[N];
		
		for(int i = 0; i < N; i++) {
			String word = br.readLine();
			int bit = 0;
			
			for(int j = 4; j < word.length()-4; j++) {
				bit |= 1 << (word.charAt(j)-'a');
			}
			
			words[i] = bit;
			wbit |= words[i];
		}
		
		if(K < 5) {
			System.out.println(0);
			return;
		}
		
		String basic = "antic";
		int bit = 0;
		
		for(int i = 0; i < 5; i++) {
			bit |= 1 << (basic.charAt(i)-'a');
		}
		
		if(K >= countBit(wbit | bit)) {
			System.out.println(N);
			return;
		}
		
		K -= 5;
		
		solve(0, 0, bit);
		
		System.out.println(ans);
	}
	
	static void solve(int start, int cnt, int bit) {
		if(cnt == K) {
			int temp = 0;
			
			for(int i = 0; i < N; i++) {
				if((bit & words[i]) == words[i]) temp++;
			}
			
			ans = Math.max(ans, temp);
			return;
		}
		
		for(int i = start; i < 26; i++) {
			if((1<<i & bit) != 0) continue;
			solve(i+1, cnt+1, bit|(1<<i));
		}
	}
	
	static int countBit(int bit) {
		int ret = 0;
		
		while(bit > 0) {
			if(bit % 2 == 1) ret++;
			bit >>= 1;
		}
		
		return ret;
	}
}
