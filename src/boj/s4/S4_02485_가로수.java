package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class S4_02485_가로수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		Set<Integer> gaps = new HashSet<>();
		Map<Integer, Integer> count = new HashMap<>();
		
		for(int i = 0; i < N-1; i++) {
			int gap = arr[i+1]-arr[i];
			gaps.add(gap);
			if(!count.containsKey(gap)) count.put(gap, 1);
			else count.put(gap, count.get(gap)+1);
		}
		
		if(gaps.size() == 1) {
			System.out.println(0);
			return;
		}
		else {
			int idx = 0;
			int g = 0;
			
			for(int gap: gaps) {
				if(idx == 0) g = gap;
				g = gcd(g, gap);
				idx++;
			}
			
			long ans = 0;
			
			for(int gap: gaps) {
				ans += ((long) (gap/g) - 1) * count.get(gap);
			}
			
			System.out.println(ans);
		}
	}
	
	static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a%b);
	}
}
