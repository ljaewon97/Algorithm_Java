package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S5_01064_평행사변형 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		
		if((x1 == x2 && x2 == x3) || ((x2-x1)*(y3-y2) == (x3-x2)*(y2-y1))) {
			System.out.println(-1);
			return;
		}
		
		double[] len = new double[3];
		
		len[0] = calcLen(x1, y1, x2, y2);
		len[1] = calcLen(x1, y1, x3, y3);
		len[2] = calcLen(x2, y2, x3, y3);
		
		double[] len2 = new double[3];
		
		len2[0] = len[0] + len[1];
		len2[1] = len[0] + len[2];
		len2[2] = len[1] + len[2];
		
		Arrays.sort(len2);
		
		System.out.println(2 * Math.abs(len2[2] - len2[0]));
	}
	
	static double calcLen(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
	}
}
