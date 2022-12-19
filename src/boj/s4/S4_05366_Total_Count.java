package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class S4_05366_Total_Count {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Map<String, Integer> map = new LinkedHashMap<>();
		int cnt = 0;
		
		while(true) {
			String f = br.readLine();
			
			if(f.equals("0")) break;
			
			if(!map.containsKey(f)) map.put(f, 1);
			else map.put(f, map.get(f)+1);
			
			cnt++;
		}
		
		for(Entry<String, Integer> e: map.entrySet()) {
			sb.append(String.format("%s: %d\n", e.getKey(), e.getValue()));
		}
		
		sb.append(String.format("Grand Total: %d\n", cnt));
		
		System.out.println(sb);
	}
}
