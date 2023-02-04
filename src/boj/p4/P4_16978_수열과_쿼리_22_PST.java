package boj.p4;

public class P4_16978_수열과_쿼리_22_PST {
	static Reader in = new Reader();
	static Node[] root;
	static int[] arr;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();

		arr = new int[N];
		
		for(int i = 0; i < N; ++i) {
			arr[i] = in.nextInt();
		}
		
		M = in.nextInt();
		
		root = new Node[M+1];
		
		build(root[0] = new Node(), 0, N-1);
		int cnt = 0;
		
		while(M-- > 0) {
			int t = in.nextInt();
			
			if(t == 1) {
				int i = in.nextInt()-1;
				int v = in.nextInt();
				
				add(i, v, root[cnt], root[++cnt] = new Node(), 0, N-1);
			}
			else {
				int k = in.nextInt();
				int l = in.nextInt()-1;
				int r = in.nextInt()-1;
				
				sb.append(query(l, r, root[k], 0, N-1)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void build(Node node, int nl, int nr) {
		if(nl == nr) {
			node.v = arr[nl];
			return;
		}
		
		int mid = (nl + nr) >> 1;
		node.l = new Node();
		node.r = new Node();
		
		build(node.l, nl, mid);
		build(node.r, mid+1, nr);
		
		node.v = node.l.v + node.r.v;
	}
	
	static void add(int i, int v, Node prev, Node cur, int nl, int nr) {
		if(nl == nr) {
			cur.v = v;
			return;
		}
		
		int mid = (nl + nr) >> 1;
		
		if(i <= mid) {
			cur.l = new Node();
			cur.r = prev.r;
			add(i, v, prev.l, cur.l, nl, mid);
		}
		else {
			cur.l = prev.l;
			cur.r = new Node();
			add(i, v, prev.r, cur.r, mid+1, nr);
		}
		
		cur.v = cur.l.v + cur.r.v;
	}
	
	static long query(int l, int r, Node node, int nl, int nr) {
		if(r < nl || nr < l) return 0;
		
		if(l <= nl && nr <= r) return node.v;
		
		int mid = (nl + nr) >> 1;
		return query(l, r, node.l, nl, mid) + query(l, r, node.r, mid+1, nr);
	}
	
	static class Node {
		Node l, r;
		long v;
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
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
