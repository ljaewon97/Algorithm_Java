package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B1_27065_2022년이_아름다웠던_이유 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		List<Integer>[] list = new ArrayList[5001];
		boolean[] g = new boolean[5001];
		
		list[1] = new ArrayList<>();
		
		for(int i = 2; i <= 5000; i++) {
			list[i] = new ArrayList<>();
			list[i].add(1);
			int sum = 1;
			
			for(int j = 2; j*j <= i; j++) {
				if(j*j == i) {
					list[i].add(j);
					sum += j;
				}
				else if(i % j == 0) {
					list[i].add(j);
					list[i].add(i/j);
					sum += j + i/j;
				}
			}
			
			if(sum > i) g[i] = true;
		}
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			boolean flag = false;
			
			if(g[n]) {
				flag = true;
				for(int x: list[n]) {
					if(g[x]) {
						flag = false;
						break;
					}
				}
			}
			
			sb.append(flag ? "Good Bye\n" : "BOJ 2022\n");
		}
		
		System.out.println(sb);
	}
}
