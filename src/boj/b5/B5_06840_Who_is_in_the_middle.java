package boj.b5;

import java.util.*;
import java.io.*;

public class B5_06840_Who_is_in_the_middle {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] bears = new int[3];
		
		for(int i = 0; i < 3; i++) {
			bears[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(bears);
		
		System.out.println(bears[1]);
	}
}
