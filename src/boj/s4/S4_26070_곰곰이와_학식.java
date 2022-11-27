package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_26070_곰곰이와_학식 {
	static int a, b, c, x, y, z;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		z = Integer.parseInt(st.nextToken());
		
		eat();
		
		while(true) {
			if(!cycle()) break;
		}
		
		System.out.println(ans);
	}
	
	static boolean cycle() {
		long prev = ans;
		
		for(int i = 0; i < 3; i++) {
			change();
			eat();
		}
		
		long after = ans;
		
		return prev != after;
	}
	
	static void eat() {
		int t = Math.min(a, x);
		a -= t;
		x -= t;
		ans += t;
		
		t = Math.min(b, y);
		b -= t;
		y -= t;
		ans += t;
		
		t = Math.min(c, z);
		c -= t;
		z -= t;
		ans += t;
	}
	
	static void change() {
		int p = x/3;
		int q = y/3;
		int r = z/3;
		
		x -= p*3;
		y += p;
		
		y -= q*3;
		z += q;
		
		z -= r*3;
		x += r;
	}
}
