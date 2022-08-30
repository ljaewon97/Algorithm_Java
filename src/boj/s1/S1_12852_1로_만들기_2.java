package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class S1_12852_1로_만들기_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		boolean[] visited = new boolean[N+1];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cur-1 > 0 && !visited[cur-1]) {
				visited[cur-1] = true;
				dp[cur-1] = cur;
				queue.add(cur-1);
				
				if(cur-1 == 1) break;
			}
			
			for(int i = 2; i <= 3; i++) {
				if(cur % i == 0) {
					int num = cur / i;
					
					if(!visited[num]) {
						visited[num] = true;
						dp[num] = cur;
						queue.add(num);
						
						if(num == 1) break;
					}
				}
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		int num = 1;
		
		while(true) {
			stack.push(num);
			if(num == N) break;
			num = dp[num];
		}
		
		System.out.println(stack.size() - 1);
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb);
	}
}
