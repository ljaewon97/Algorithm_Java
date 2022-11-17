package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class B2_14954_Happy_Number {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Set<Integer> set = new HashSet<>();
		set.add(n);
		boolean happy = false;
		
		while(true) {
			n = f(n);
			
			if(n == 1) {
				happy = true;
				break;
			}
			
			if(set.contains(n)) {
				happy = false;
				break;
			}
			
			set.add(n);
		}
		
		System.out.println(happy ? "HAPPY" : "UNHAPPY");
	}
	
	static int f(int x) {
		int ret = 0;
		
		while(x > 0) {
			int t = x % 10;
			ret += t * t;
			x /= 10;
		}
		
		return ret;
	}
}
