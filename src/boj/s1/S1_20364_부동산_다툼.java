package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class S1_20364_부동산_다툼 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		Stack<Integer> stack = new Stack<>();
		boolean[] ground = new boolean[N+1];
		
		while(Q-- > 0) {
			int x = Integer.parseInt(br.readLine());
			
			while(x > 0) {
				stack.push(x&1);
				x>>=1;
			}
			
			int loc = 0;
			
			while(!stack.isEmpty()) {
				if(stack.pop() == 1) loc = (loc<<1) | 1;
				else loc <<= 1;
				
				if(ground[loc]) {
					sb.append(loc).append("\n");
					break;
				}
			}
			
			if(stack.isEmpty()) {
				if(!ground[loc]) {
					ground[loc] = true;
					sb.append("0\n");
				}
			}
			else stack.clear();
		}
		
		System.out.println(sb);
	}
}
