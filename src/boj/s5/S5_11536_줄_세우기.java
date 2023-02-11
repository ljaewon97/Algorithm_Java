package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_11536_줄_세우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean asc = true, desc = true;
		
		int N = Integer.parseInt(br.readLine());
		
		String[] names = new String[N];
		
		for(int i = 0; i < N; ++i) {
			names[i] = br.readLine();
		}
		
		for(int i = 0; i < N-1; ++i) {
			if(names[i].compareTo(names[i+1]) > 0) asc = false;
			if(names[i].compareTo(names[i+1]) < 0) desc = false;
		}
		
		if(asc) System.out.println("INCREASING");
		else if(desc) System.out.println("DECREASING");
		else System.out.println("NEITHER");
	}
}
