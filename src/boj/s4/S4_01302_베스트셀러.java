package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S4_01302_베스트셀러 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<String, Integer> bookIdx = new HashMap<>();
		List<Record> records = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			String name = br.readLine();
			if(!bookIdx.containsKey(name)) {
				bookIdx.put(name, records.size());
				records.add(new Record(name, 0));
			}
			records.get(bookIdx.get(name)).cnt++;
		}
		
		Collections.sort(records);
		
		System.out.println(records.get(0).name);
	}
	
	static class Record implements Comparable<Record> {
		String name;
		int cnt;
		
		public Record(String name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
		
		public int compareTo(Record o) {
			if(this.cnt != o.cnt) return o.cnt - this.cnt;
			return this.name.compareTo(o.name);
		}
	}
}
