package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class G5_01107_리모컨 {
	static Set<Integer> broken = new HashSet<>();
	static int N, M, len, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String line = br.readLine();
		len = line.length();
		N = Integer.parseInt(line);
		M = Integer.parseInt(br.readLine());
		ans = Math.abs(N - 100);
		
		if(M == 0) {
			System.out.println(Math.min(ans, len));
			return;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			broken.add(Integer.parseInt(st.nextToken()));
		}
		
		recur(0, 0);
		
		System.out.println(ans);
	}
	
	static void recur(int l, int num) {
		if(l != 0) ans = Math.min(ans, Math.abs(N-num) + l);
		
		if(l == len+1) return;
		
		for(int i = 0; i < 10; i++) {
			if(!broken.contains(i)) {
				recur(l+1, num*10+i);
			}
		}
	}
}
