package boj.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3_01168_요세푸스_문제_2 {
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		tree = new int[4*N];
		
		build(1, 1, N);
		
		int order = K;
		sb.append("<");
		
		for(int i = 0; i < N-1; i++) {
			sb.append(query(order, 1, 1, N)).append(", ");
			order = (order+K-1) % tree[1] == 0 ? tree[1] : (order+K-1) % tree[1];
		}
		
		sb.append(query(1, 1, 1, N)).append(">");
		
		System.out.println(sb);
	}
	
	static int build(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = 1;
		}
		
		int mid = (nl + nr) / 2;
		return tree[n] = build(2*n, nl, mid) + build(2*n+1, mid+1, nr);
	}
	
	static int query(int o, int n, int nl, int nr) {
		tree[n]--;
		
		if(nl == nr) return nl;
		
		int mid = (nl + nr) / 2;
		
		if(o <= tree[2*n]) return query(o, 2*n, nl, mid);
		else return query(o-tree[2*n], 2*n+1, mid+1, nr);
	}
}
