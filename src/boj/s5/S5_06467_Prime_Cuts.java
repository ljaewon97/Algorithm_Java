package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S5_06467_Prime_Cuts {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		boolean[] arr = new boolean[1001];
		int limit = (int) Math.sqrt(1000) + 1;
		
		for(int i = 2; i <= limit; i++) {
			for(int j = 2*i; j <= 1000; j += i) {
				arr[j] = true;
			}
		}
		
		String line = null;
		boolean isFirstLine = true;
		
		while((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			if(isFirstLine) isFirstLine = false;
			else sb.append("\n");
			
			sb.append(String.format("%d %d:", N, C));
			
			List<Integer> list = new ArrayList<>();
			
			for(int i = 1; i <= N; i++) {
				if(!arr[i]) list.add(i);
			}
			
			int len = list.size();
			int mid = len / 2;
			int left = -1, right = -1;
			
			if(len % 2 == 0) {
				left = Math.max(0, mid-C);
				right = Math.min(len-1, mid-1+C);
			}
			else {
				int temp = C-1;
				left = Math.max(0, mid-temp);
				right = Math.min(len-1, mid+temp);
			}
			
			for(int i = left; i <= right; i++) {
				sb.append(" ").append(list.get(i));
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
