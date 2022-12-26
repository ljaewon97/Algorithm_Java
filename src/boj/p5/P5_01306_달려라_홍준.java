package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5_01306_달려라_홍준 {
	static int[] arr, tree;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		tree = new int[4*N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, 0, N-1);
		
		for(int l = 0; l < N-2*M+2; l++) {
			sb.append(query(l, l+2*M-2, 1, 0, N-1)).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static int init(int n, int nl, int nr) {
		if(nl == nr) return tree[n] = arr[nl];
		
		int mid = (nl + nr) / 2;
		return tree[n] = Math.max(init(2*n, nl, mid), init(2*n+1, mid+1, nr));
	}
	
	static int query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) return 0;
		
		if(l <= nl && nr <= r) return tree[n];
		
		int mid = (nl + nr) / 2;
		return Math.max(query(l, r, 2*n, nl, mid), query(l, r, 2*n+1, mid+1, nr));
	}
}
