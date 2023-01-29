package boj.g3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class G3_09470_Strahler_순서 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static int[] indeg, order;
	static boolean[] over;
	static int K, M, P;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		while(T-- > 0) {
			K = in.nextInt();
			M = in.nextInt();
			P = in.nextInt();
			
			graph = new ArrayList[M+1];
			indeg = new int[M+1];
			order = new int[M+1];
			over = new boolean[M+1];
			
			for(int i = 1; i <= M; i++) {
				graph[i] = new ArrayList<>();
			}
			
			while(P-- > 0) {
				int A = in.nextInt();
				int B = in.nextInt();
				
				graph[A].add(B);
				indeg[B]++;
			}
			
			ts();
			
			sb.append(String.format("%d %d\n", K, order[M]));
		}
		
		System.out.println(sb);
	}
	
	static void ts() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i <= M; i++) {
			if(indeg[i] == 0) {
				queue.add(i);
				order[i] = 1;
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int next: graph[cur]) {
				--indeg[next];
				
				if(order[cur] > order[next]) {
					over[next] = false;
					order[next] = order[cur];
				}
				else if(order[cur] == order[next]) {
					over[next] = true;
				}
				
				if(indeg[next] == 0) {
					if(over[next]) ++order[next];
					queue.add(next);
				}
			}
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}
