package boj.p3;

public class P3_16903_수열과_쿼리_20 {
	static Reader in = new Reader();
	static Trie trie;
	static int M;
	
	public static void main(String[] args) throws Exception {
		 StringBuilder sb = new StringBuilder();
		 
		 trie = new Trie();
		 trie.add(0);
		 
		 M = in.nextInt();
		 
		 while(M-- > 0) {
			 int type = in.nextInt();
			 
			 if(type == 1) {
				 trie.add(in.nextInt());
			 }
			 else if(type == 2) {
				 trie.remove(in.nextInt());
			 }
			 else {
				 sb.append(trie.maxXor(in.nextInt())).append("\n");
			 }
		 }
		 
		 System.out.println(sb);
	}
	
	static int[] bitArr(int x) {
		int[] ret = new int[30];
		
		for(int i = 29; i >= 0; --i) {
			ret[i] = x & 1;
			x >>= 1;
		}
		
		return ret;
	}
	
	static class Trie {
		Node root;
		
		Trie() {
			root = new Node();
		}
		
		void add(int x) {
			int[] bit = bitArr(x);
			Node node = root;
			
			for(int i = 0; i < 30; ++i) {
				if(bit[i] == 1) {
					if(node.c1 == null) node.c1 = new Node();
					++node.c1.cnt;
					node = node.c1;
				}
				else {
					if(node.c0 == null) node.c0 = new Node();
					++node.c0.cnt;
					node = node.c0;
				}
			}
		}
		
		void remove(int x) {
			int[] bit = bitArr(x);
			Node node = root;
			
			for(int i = 0; i < 30; ++i) {
				if(bit[i] == 1) {
					--node.c1.cnt;
					node = node.c1;
				}
				else {
					--node.c0.cnt;
					node = node.c0;
				}
			}
		}
		
		int maxXor(int x) {
			int[] bit = bitArr(x);
			Node node = root;
			int ret = 0;
			
			for(int i = 0, pow = 1 << 29; i < 30; ++i, pow >>= 1) {
				if(bit[i] == 1) {
					if(node.c0 != null && node.c0.cnt != 0) {
						ret += pow;
						node = node.c0;
					}
					else {
						if(node.c1 == null) node.c1 = new Node();
						node = node.c1;
					}
				}
				else {
					if(node.c1 != null && node.c1.cnt != 0) {
						ret += pow;
						node = node.c1;
					}
					else {
						if(node.c0 == null) node.c0 = new Node();
						node = node.c0;
					}
				}
			}
			
			return ret;
		}
	}
	
	static class Node {
		int cnt;
		Node c0, c1;
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
