package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class S4_25955_APC는_쉬운_난이도_순일까_아닐까 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		Map<Character, Integer> map = new HashMap<>();
		
		map.put('B', 0);
		map.put('S', 1000);
		map.put('G', 2000);
		map.put('P', 3000);
		map.put('D', 4000);
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			String s = st.nextToken();
			arr[i] = map.get(s.charAt(0)) + 1000 - Integer.parseInt(s.substring(1));
		}
		
		List<Integer> loc = new ArrayList<>();
		
		for(int i = 0; i < N-1; i++) {
			if(arr[i] > arr[i+1]) loc.add(i);
		}
		
		if(loc.size() == 0) {
			System.out.println("OK");
		}
		else if(loc.size() == 1) {
			int idx = loc.get(0);
			sb.append("KO\n");
			sb.append(convert(arr[idx+1])).append(" ").append(convert(arr[idx]));
			System.out.println(sb);
		}
		else if(loc.size() == 2) {
			sb.append("KO\n");
			sb.append(convert(arr[loc.get(1)+1])).append(" ").append(convert(arr[loc.get(0)]));
			System.out.println(sb);
		}
	}
	
	static String convert(int n) {
		StringBuilder ret = new StringBuilder();
		int m = n / 1000;
		if(m == 0) ret.append("B");
		else if(m == 1) ret.append("S");
		else if(m == 2) ret.append("G");
		else if(m == 3) ret.append("P");
		else if(m == 4) ret.append("D");
		ret.append(1000 - n%1000);
		return ret.toString();
	}
}
