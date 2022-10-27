package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_15973_두_박스 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		int x4 = Integer.parseInt(st.nextToken());
		int y4 = Integer.parseInt(st.nextToken());
		
		if(x2 < x3 || x4 < x1 || y2 < y3 || y4 < y1) {
			System.out.println("NULL");
			return;
		}
		
		if((x2 == x3 || x1 == x4) && (y2 == y3 || y1 == y4)) {
			System.out.println("POINT");
			return;
		}
		else if((x2 == x3 || x1 == x4) || (y2 == y3 || y1 == y4)) {
			System.out.println("LINE");
			return;
		}
		
		System.out.println("FACE");
	}
}
