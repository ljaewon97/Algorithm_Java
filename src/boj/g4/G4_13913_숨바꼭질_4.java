package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class G4_13913_숨바꼭질_4 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deque = new LinkedList<>();
		int[] visited = new int[100001];
		
		deque.add(N);
		visited[N] = -1;
		
		while(!deque.isEmpty()) {
			int x = deque.poll();
			
			if(x == K) {
				break;
			}
			
			if(isIn(x-1) && visited[x-1] == 0) {
				visited[x-1] = 1;
				deque.add(x-1);
			}
			if(isIn(x+1) && visited[x+1] == 0) {
				visited[x+1] = 2;
				deque.add(x+1);
			}
			if(isIn(2*x) && visited[2*x] == 0) {
				visited[2*x] = 3;
				deque.add(2*x);
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		int time = 0;
		int cur = K;
		stack.push(cur);
		
		while(cur != N) {
			if(visited[cur] == 1) {
				cur++;
			}
			else if(visited[cur] == 2) {
				cur--;
			}
			else if(visited[cur] == 3) {
				cur /= 2;
			}
			
			stack.push(cur);
			time++;
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(time);
		System.out.println(sb);
	}
	
	static boolean isIn(int n) {
		return 0 <= n && n <= 100000;
	}
}
