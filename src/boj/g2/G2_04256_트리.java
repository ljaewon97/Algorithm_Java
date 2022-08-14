package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_04256_트리 {
	static StringBuilder sb = new StringBuilder();
	static int[][] tree;
	static int[] preorder, preIndex, inIndex;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			tree = new int[N+1][2];
			preorder = new int[N];
			preIndex = new int[N+1];
			inIndex = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				preorder[i] = Integer.parseInt(st.nextToken());
				preIndex[preorder[i]] = i;
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				inIndex[Integer.parseInt(st.nextToken())] = i;
			}
			
			int root = preorder[0];
			makeTree(root, 0, N-1);
			postorder(root);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void makeTree(int node, int start, int end) {
		int left = inIndex[node] - start;
		int right = end - inIndex[node];
		
		if(left > 0) {
			int leftChild = preorder[preIndex[node] + 1];
			tree[node][0] = leftChild;
			makeTree(leftChild, start, inIndex[node]-1);
		}
		
		if(right > 0) {
			int rightChild = preorder[preIndex[node] + left + 1];
			tree[node][1] = rightChild;
			makeTree(rightChild, inIndex[node]+1, end);
		}
	}
	
	static void postorder(int node) {
		if(tree[node][0] != 0) {
			postorder(tree[node][0]);
		}
		if(tree[node][1] != 0) {
			postorder(tree[node][1]);
		}
		sb.append(node).append(" ");
	}
}
