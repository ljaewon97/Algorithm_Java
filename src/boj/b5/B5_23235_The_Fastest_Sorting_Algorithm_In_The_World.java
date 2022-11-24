package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5_23235_The_Fastest_Sorting_Algorithm_In_The_World {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = 1;
		
		while(true) {
			String line = br.readLine();
			
			if(line.equals("0")) break;
			
			sb.append(String.format("Case %d: Sorting... done!\n", t));
			t++;
		}
		
		System.out.println(sb);
	}
}
