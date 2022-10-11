package codeforces.div3.round820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class C_Jumping_on_Tiles {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			String s = br.readLine();
			int cost = 0, m = 1;
			
			if(s.length() == 2) {
				sb.append(Math.abs(s.charAt(0) - s.charAt(1))).append(" ").append(2).append("\n");
				sb.append("1 2\n");
				continue;
			}
			
			int[] count = new int[27];
			List<Integer>[] idxs = new ArrayList[27];
			int len = s.length();
			
			for(int i = 1; i <= 26; i++) {
				idxs[i] = new ArrayList<>();
			}
			
			for(int i = 1; i < len; i++) {
				int n = s.charAt(i) - 'a' + 1;
				count[n]++;
				if(i != len-1) idxs[n].add(i);
			}
			
			int start = s.charAt(0) - 'a' + 1;
			int end = s.charAt(len-1) - 'a' + 1;
			
			if(start == end) {
				sb.append("0 ").append(count[start]+1).append("\n");
				sb.append("1 ");
				for(int idx: idxs[start]) {
					sb.append(idx+1).append(" ");
				}
				sb.append(len).append("\n");
			}
			else {
				List<Integer> indexes = new ArrayList<>();
				int d = start > end ? -1 : 1;
				int prev = start;
				
				for(int i = start; i != end+d; i += d) {
					if(count[i] != 0) {
						indexes.add(i);
						cost += Math.abs(prev - i);
						m += count[i];
						prev = i;
					}
				}
				
				sb.append(cost).append(" ").append(m).append("\n");
				sb.append(1).append(" ");
				
				for(int index: indexes) {
					for(int idx: idxs[index]) {
						sb.append(idx+1).append(" ");
					}
				}
				
				sb.append(len).append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
