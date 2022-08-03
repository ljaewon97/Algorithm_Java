package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class G4_05052_전화번호_목록 {
	static String ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		class TrieNode {
			private Map<Character, TrieNode> childNode = new HashMap<>();
			private boolean isLastNode;
			
			public Map<Character, TrieNode> getChildNode() {
				return childNode;
			}
			
			public boolean isLastNode() {
				return isLastNode;
			}
			
			public void setLastNode(boolean isLastNode) {
				this.isLastNode = isLastNode;
			}
		}
		
		class Trie {
			private TrieNode root;
			
			public Trie() {
				root = new TrieNode();
			}
			
			public void insert(String s) {
				TrieNode thisNode = root;
				
				for(int i = 0; i < s.length(); i++) {
					char cur = s.charAt(i);
					
					if(!thisNode.getChildNode().containsKey(cur)) {
						thisNode.getChildNode().put(cur, new TrieNode());
						thisNode = thisNode.getChildNode().get(cur);
					}
					else {
						if(i == s.length() - 1) {
							ans = "NO";
						}
						thisNode = thisNode.getChildNode().get(cur);
					}
					
					if(thisNode.isLastNode()) {
						ans = "NO";
					}
				}
				thisNode.setLastNode(true);
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			ans = "YES";
			Trie trie = new Trie();
			int N = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				trie.insert(str);
			}
			
			sb.append(ans + "\n");
		}
		
		System.out.println(sb);
	}
}
