package boj.p2;

import java.util.Arrays;

public class P2_11012_Egg {
	static Reader in = new Reader();
	static Point[] points;
	static Node[] root;
	static int N, M, ans;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		while(T-- > 0) {
			N = in.nextInt();
			M = in.nextInt();
			ans = 0;
			
			points = new Point[N];
			root = new Node[N+1];
			
			int ymax = 0;
			
			for(int i = 0; i < N; ++i) {
				int x = in.nextInt();
				int y = in.nextInt();
				
				points[i] = new Point(x, y);
				ymax = Math.max(ymax, y);
			}
			
			Arrays.sort(points);
			
			build(root[0] = new Node(), 0, ymax);
			
			for(int i = 0; i < N; i++) {
				add(points[i].y, root[i], root[i+1] = new Node(), 0, ymax);
			}
			
			while(M-- > 0) {
				int l = in.nextInt();
				int r = in.nextInt();
				int b = in.nextInt();
				int t = in.nextInt();
				
				int k1 = lowerbound(l);
				int k2 = upperbound(r);
				
				ans += query(b, t, root[k2], 0, ymax) - query(b, t, root[k1], 0, ymax);
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void build(Node node, int nl, int nr) {
		if(nl == nr) return;
		
		node.l = new Node();
		node.r = new Node();
		
		int mid = (nl + nr) >> 1;
		build(node.l, nl, mid);
		build(node.r, mid+1, nr);
	}
	
	static void add(int y, Node prev, Node now, int nl, int nr) {
		if(nl == nr) {
			now.v = prev.v+1;
			return;
		}
		
		int mid = (nl + nr) >> 1;
		
		if(y <= mid) {
			now.l = new Node();
			now.r = prev.r;
			add(y, prev.l, now.l, nl, mid);
		}
		else {
			now.l = prev.l;
			now.r = new Node();
			add(y, prev.r, now.r, mid+1, nr);
		}
		
		now.v = now.l.v + now.r.v;
	}
	
	static int query(int l, int r, Node node, int nl, int nr) {
		if(r < nl || nr < l) return 0;
		if(l <= nl && nr <= r) return node.v;
		
		int mid = (nl + nr) >> 1;
		return query(l, r, node.l, nl, mid) + query(l, r, node.r, mid+1, nr);
	}
	
	static int lowerbound(int target) {
		int l = 0;
		int r = N;
		
		while(l < r) {
			int m = (l + r) >> 1;
		
			if(points[m].x >= target) r = m;
			else l = m + 1;
		}
		
		return r;
	}
	
	static int upperbound(int target) {
		int l = 0;
		int r = N;
		
		while(l < r) {
			int m = (l + r) >> 1;
		
			if(points[m].x > target) r = m;
			else l = m + 1;
		}
		
		return r;
	}
	
	static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}
	}
	
	static class Node {
		Node l, r;
		int v;
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
