package boj.p4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Trie trie = new Trie();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			trie.insert(br.readLine());
		}
		
		trie.print(trie.root);
		sb.setLength(sb.length() - 2 * trie.lastWord.length());
		sb.insert(0, sb.length() / 2 + "\n");
		System.out.println(sb);
	}
}