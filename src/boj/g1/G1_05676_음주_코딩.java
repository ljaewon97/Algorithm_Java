package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1_05676_음주_코딩 {
	static int[] arr, tree;
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String line;
		
		while((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			tree = new int[4*N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			buildTree(1, 0, N-1);
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				String type = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(type.equals("C")) {
					update(a-1, b, 1, 0, N-1);
				}
				else {
					int res = query(a-1, b-1, 1, 0, N-1);
					sb.append(res == 1 ? "+" : res == 0 ? "0" : "-");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int buildTree(int n, int nl, int nr) {
		if(nl == nr) {
			return tree[n] = arr[nl] > 0 ? 1 : arr[nl] == 0 ? 0 : -1;
		}
		
		int mid = (nl + nr) / 2;
		int lv = buildTree(2*n, nl, mid);
		int rv = buildTree(2*n+1, mid+1, nr);
		
		return tree[n] = lv * rv;
	}
	
	static int query(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) {
			return 1;
		}
		
		if(l <= nl && nr <= r) {
			return tree[n];
		}
		
		int mid = (nl + nr) / 2;
		return query(l, r, 2*n, nl, mid) * query(l, r, 2*n+1, mid+1, nr);
	}
	
	static int update(int idx, int v, int n, int nl, int nr) {
		if(nr < idx || idx < nl) {
			return tree[n];
		}
		
		if(nl == nr) {
			return tree[n] = v > 0 ? 1 : v == 0 ? 0 : -1;
		}
		
		int mid = (nl + nr) / 2;
		int lv = update(idx, v, 2*n, nl, mid);
		int rv = update(idx, v, 2*n+1, mid+1, nr);
		
		return tree[n] = lv * rv;
	}
}
