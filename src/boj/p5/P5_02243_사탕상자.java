package boj.p5;

public class P5_02243_사탕상자 {
	static Reader in = new Reader();
	static int[] tree;
	static int N = 1000001;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		tree = new int[4*N];
		
		int n = in.nextInt();
		
		while(n-- > 0) {
			int a = in.nextInt();
			
			if(a == 1) {
				int b = in.nextInt();
				
				sb.append(query(b, 1, 0, N-1)).append("\n");
			}
			else {
				int b = in.nextInt();
				int c = in.nextInt();
				
				update(b, c, 1, 0, N-1);
			}
		}
		
		System.out.println(sb);
	}
	
	static int update(int i, int v, int n, int nl, int nr) {
		if(nr < i || i < nl) return tree[n];
		if(nl == nr) return tree[n] += v;
		
		int mid = (nl + nr) / 2;
		return tree[n] = update(i, v, 2*n, nl, mid) + update(i, v, 2*n+1, mid+1, nr);
	}
	
	static int query(int o, int n, int nl, int nr) {
		tree[n]--;
		
		if(nl == nr) return nl;
		
		int mid = (nl + nr) / 2;
		
		if(o <= tree[2*n]) return query(o, 2*n, nl, mid);
		else return query(o-tree[2*n], 2*n+1, mid+1, nr);
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return neg ? -n : n;
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
