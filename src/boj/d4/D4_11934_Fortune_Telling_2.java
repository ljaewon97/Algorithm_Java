package boj.d4;

import java.util.Arrays;

public class D4_11934_Fortune_Telling_2 {
	static Reader in = new Reader();
	static int[][] cards;
	static Cmd[][] tree;
	static Cmd[] cmds;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		cards = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			cards[i][0] = in.nextInt();
			cards[i][1] = in.nextInt();
		}
		
		cmds = new Cmd[M];
		tree = new Cmd[4*M][];
		
		for(int i = 0; i < M; i++) {
			cmds[i] = new Cmd(i, in.nextInt());
		}
		
		Arrays.sort(cmds);
		
		init(1, 0, M-1);
		
		long ans = 0;
		
		for(int i = 0; i < N; i++) {
			int a = Math.min(cards[i][0], cards[i][1]);
			int b = Math.max(cards[i][0], cards[i][1]);
			
			int l = lowerBoundCmds(a);
			int r = lowerBoundCmds(b) - 1;
			
			if(l <= r) {
				int last = getOrder(l, r, 1, 0, M-1);
				int cnt = getCount(r+1, M-1, last+1, 1, 0, M-1);
				ans += cnt % 2 == 0 ? b : a;
			}
			else {
				int cnt = getCount(r+1, M-1, 0, 1, 0, M-1);
				ans += cnt % 2 == 0 ? cards[i][0] : cards[i][1];
			}
		}
		
		System.out.println(ans);
	}
	
	static Cmd[] init(int n, int nl, int nr) {
		if(nl == nr) return tree[n] = new Cmd[] {cmds[nl]};
		
		int mid = (nl + nr) / 2;
		return tree[n] = merge(init(2*n, nl, mid), init(2*n+1, mid+1, nr));
	}
	
	static int getOrder(int l, int r, int n, int nl, int nr) {
		if(nr < l || r < nl) return 0;
		
		if(l <= nl && nr <= r) return tree[n][tree[n].length-1].o;
		
		int mid = (nl + nr) / 2;
		return Math.max(getOrder(l, r, 2*n, nl, mid), getOrder(l, r, 2*n+1, mid+1, nr));
	}
	
	static int getCount(int l, int r, int t, int n, int nl, int nr) {
		if(nr < l || r < nl) return 0;
		
		if(l <= nl && nr <= r) return tree[n].length - lowerBound(tree[n], t);
		
		int mid = (nl + nr) / 2;
		return getCount(l, r, t, 2*n, nl, mid) + getCount(l, r, t, 2*n+1, mid+1, nr);
	}
	
	static Cmd[] merge(Cmd[] la, Cmd[] ra) {
		int ll = la.length;
		int rl = ra.length;
		Cmd[] ret = new Cmd[ll+rl];
		int i = 0, li = 0, ri = 0;
		
		while(li < ll && ri < rl) {
			if(la[li].o < ra[ri].o) ret[i++] = la[li++];
			else ret[i++] = ra[ri++];
		}
		
		while(li < ll) {
			ret[i++] = la[li++];
		}
		
		while(ri < rl) {
			ret[i++] = ra[ri++];
		}
		
		return ret;
	}
	
	static int lowerBoundCmds(int target) {
		int l = 0;
		int r = M;
		
		while(l < r) {
			int m = (l + r) / 2;
			
			if(cmds[m].n >= target) r = m;
			else l = m + 1;
		}
		
		return r;
	}
	
	static int lowerBound(Cmd[] arr, int target) {
		int l = 0;
		int r = arr.length;
		
		while(l < r) {
			int m = (l + r) / 2;
			
			if(arr[m].o >= target) r = m;
			else l = m + 1;
		}
		
		return r;
	}
	
	static class Cmd implements Comparable<Cmd> {
		int o, n;
		
		public Cmd(int o, int n) {
			this.o = o;
			this.n = n;
		}

		@Override
		public int compareTo(Cmd o) {
			return Integer.compare(this.n, o.n);
		}
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
