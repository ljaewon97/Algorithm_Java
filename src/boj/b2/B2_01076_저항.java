package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B2_01076_저항 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<String, Integer> res = new HashMap<>();
		res.put("black", 0);
		res.put("brown", 1);
		res.put("red", 2);
		res.put("orange", 3);
		res.put("yellow", 4);
		res.put("green", 5);
		res.put("blue", 6);
		res.put("violet", 7);
		res.put("grey", 8);
		res.put("white", 9);
		
		long ans = 0;
		
		for(int i = 0; i < 2; i++) {
			String color = br.readLine();
			ans = ans * 10 + res.get(color);
		}
		
		String color = br.readLine();
		ans = ans * (long) Math.pow(10, res.get(color));
		
		System.out.println(ans);
	}
}
