package boj.p4;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class P4_05467_Type_Printer {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		class TrieNode {
			private Map<Character, TrieNode> childNode = new HashMap<>();
			private boolean isLastNode;
			private int depth = -1;
			
			public Map<Character, TrieNode> getChildNode() {
				return childNode;
			}

			public boolean isLastNode() {
				return isLastNode;
			}

			public void setLastNode(boolean isLastNode) {
				this.isLastNode = isLastNode;
			}
			
			public int getDepth() {
				return depth;
			}
			
			public void setDepth(int depth) {
				this.depth = depth;
			}
		}
		
		class Trie {
			private TrieNode root;
			private String lastWord = "";
			
			public Trie() {
				root = new TrieNode();
			}
			
			public void insert(String s) {
				if(s.length() > lastWord.length()) {
					lastWord = s;
				}
				
				TrieNode thisNode = root;
				
				for(int i = 0, d = 0; i < s.length(); i++, d++) {
					thisNode = thisNode.getChildNode().computeIfAbsent(s.charAt(i), c -> new TrieNode());
					thisNode.setDepth(d);
				}
				thisNode.setLastNode(true);
			}
			
			public void print(TrieNode curNode) {
				if(curNode.isLastNode()) {
					sb.append("P\n");
				}
				
				if(curNode.getChildNode().isEmpty()) {
					return;
				}
				
				char pendChar = ' ';
				TrieNode pendNode = null;
				
				for(Entry<Character, TrieNode> entry: curNode.getChildNode().entrySet()) {
					if(lastWord.charAt(entry.getValue().getDepth()) == entry.getKey()) {
						pendChar = entry.getKey();
						pendNode = entry.getValue();
						continue;
					}
					
					sb.append(entry.getKey() + "\n");
					print(entry.getValue());
					sb.append("-\n");
				}
				
				if(pendChar != ' ') {
					sb.append(pendChar + "\n");
				}
				if(pendNode != null) {
					print(pendNode);
					sb.append("-\n");
				}
			}
		}
		
		Reader in = new Reader();
		
		Trie trie = new Trie();
		int N = in.nextInt();
		for(int i = 0; i < N; i++) {
			trie.insert(in.next());
		}
		
		trie.print(trie.root);
		sb.setLength(sb.length() - 2 * trie.lastWord.length());
		sb.insert(0, sb.length() / 2 + "\n");
		System.out.println(sb);
	}
}

class Reader {
	final int SIZE = 1 << 13;
	byte[] buffer = new byte[SIZE];
	int idx, size;
	
	boolean isAlpha(byte b) {
		return (64 < b && b < 91) || (96 < b && b < 123);
	}
	
	byte read() throws Exception {
		if(idx == size) {
			size = System.in.read(buffer, idx = 0, SIZE);
			if (size < 0) {
				buffer[0] = -1;
			}
		}
		return buffer[idx++];
	}
	
	String next() throws Exception {
		byte[] temp = new byte[30];
		int idx = 0;
		byte b;
		while((b = read()) <= 32);
		do temp[idx++] = b;
		while (isAlpha(b = read()));
		return new String(temp, 0, idx);
	}
	
	int nextInt() throws Exception {
        int n = 0;
        byte b;
        while ((b = read()) <= 32);
        do n = (n << 3) + (n << 1) + (b & 15);
        while (isNumber(b = read()));
        return n;
    }
	
    boolean isNumber(byte b) {
        return 47 < b && b < 58;
    }
}