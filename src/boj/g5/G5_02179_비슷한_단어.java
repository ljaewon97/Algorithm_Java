package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class G5_02179_비슷한_단어 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 110);
		
		int N = Integer.parseInt(br.readLine());
		
		String[] words = new String[N];
		List<Word> list = new ArrayList<>();
		Set<String> set = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			String word = br.readLine();
			words[i] = word;
			if(!set.contains(word)) {
				set.add(word);
				list.add(new Word(i, word));
			}
		}
		
		Collections.sort(list);
		
		int[] lens = new int[N];
		int max = 0;
		
		for(int i = 0; i < list.size()-1; i++) {
			int len = 0;
			Word a = list.get(i);
			Word b = list.get(i+1);
			
			for(int j = 0; j < Math.min(a.str.length(), b.str.length()); j++) {
				if(a.str.charAt(j) == b.str.charAt(j)) len++;
				else break;
			}
			
			max = Math.max(max, len);
			lens[a.idx] = Math.max(lens[a.idx], len);
			lens[b.idx] = Math.max(lens[b.idx], len);
		}
		
		String S = null, T = null, prefix = null;
		
		for(int i = 0; i < N; i++) {
			if(S == null) {
				if(lens[i] == max) {
					S = words[i];
					prefix = S.substring(0, max);
				}
			}
			else {
				if(lens[i] == max && words[i].substring(0, max).equals(prefix)) {
					T = words[i];
					break;
				}
			}
		}
		
		System.out.println(S);
		System.out.println(T);
	}
	
	static class Word implements Comparable<Word> {
		int idx;
		String str;
		
		public Word(int idx, String str) {
			this.idx = idx;
			this.str = str;
		}

		@Override
		public int compareTo(Word o) {
			return this.str.compareTo(o.str);
		}
	}
}
