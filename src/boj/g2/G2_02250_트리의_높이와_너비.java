package boj.g2;

import java.util.Deque;
import java.util.LinkedList;

public class G2_02250_트리의_높이와_너비 {
	static Reader in = new Reader();
	static int[][] tree, levelInfo;
	static int[] x;
	static boolean[] parent;
	static int N, ansLevel, ansWidth, idx = 1, depth = 1;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		tree = new int[N+1][2];
		x = new int[N+1];
		parent = new boolean[N+1];
		
		for(int i = 0; i < N; i++) {
			int node = in.nextInt();
			int left = in.nextInt();
			int right = in.nextInt();
			
			tree[node][0] = left;
			tree[node][1] = right;
			if(left != -1) parent[left] = true;
			if(right != -1) parent[right] = true;
		}
		
		int root = -1;
		for(int i = 1; i <= N; i++) {
			if(!parent[i]) {
				root = i;
				break;
			}
		}
		
		inorder(root, 1);
		
		levelInfo = new int[depth+1][2];
		for(int i = 1; i <= depth; i++) {
			levelInfo[i][0] = Integer.MAX_VALUE;
			levelInfo[i][1] = Integer.MIN_VALUE;
		}
		
		bfs(root);
		
		for(int i = 1; i <= depth; i++) {
			int width = levelInfo[i][1] - levelInfo[i][0] + 1;
			if(width > ansWidth) {
				ansLevel = i;
				ansWidth = width;
			}
		}
		
		System.out.println(ansLevel + " " + ansWidth);
	}
	
	static void inorder(int node, int level) {
		if(tree[node][0] != -1) {
			inorder(tree[node][0], level+1);
		}
		
		x[node] = idx++;
		if(level > depth) {
			depth = level;
		}
		
		if(tree[node][1] != -1) {
			inorder(tree[node][1], level+1);
		}
	}
	
	static void bfs(int root) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {root, 1});
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int node = cur[0];
			int level = cur[1];
			
			if(x[node] > levelInfo[level][1]) {
				levelInfo[level][1] = x[node];
			}
			
			if(x[node] < levelInfo[level][0]) {
				levelInfo[level][0] = x[node];
			}
			
			for(int i = 0; i < 2; i++) {
				if(tree[node][i] != -1) {
					deque.add(new int[] {tree[node][i], level+1});
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
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if (neg)
				return -n;
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
