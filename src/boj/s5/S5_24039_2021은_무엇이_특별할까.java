package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S5_24039_2021은_무엇이_특별할까 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] prime = new boolean[120];
		
		for(int i = 2; i < 120; i++) {
			if(prime[i]) continue;
			for(int j = 2*i; j < 120; j += i) {
				prime[j] = true;
			}
		}
		
		List<Integer> list = new ArrayList<>();
		
		for(int i = 2; i < 120; i++) {
			if(!prime[i]) list.add(i);
		}
		
		boolean[] ans = new boolean[12000];
		
		for(int i = 0; i < list.size()-1; i++) {
			int x = list.get(i) * list.get(i+1);
			if(x <= 12000) ans[x] = true;
		}
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = N+1; i < 12000; i++) {
			if(ans[i]) {
				System.out.println(i);
				return;
			}
		}
	}
}
