package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_PrintStars10_2447 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static StringBuilder[] output;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		output = new StringBuilder[N];
		for(int i = 0; i < output.length; i++) {
			output[i] = new StringBuilder("");
		}
		StringBuilder sb = new StringBuilder();
		printStars(0, N);
		for(int i = 0; i < N; i++) {
			sb.append(output[i]).append("\n");
		}
		System.out.println(sb);
	}
	
	static void printBlanks(int a, int b) {
		StringBuilder temp = new StringBuilder();
		for(int i = 0; i < b-a; i++) {
			temp.append(" ");
		}
		for(int i = a; i < b; i++) {
			output[i].append(temp);
		}
	}
	
	static void printStars(int a, int b) {
		if(b - a == 3) {
			output[a].append("***");
			output[a+1].append("* *");
			output[a+2].append("***");
		}
		else {
			int gap = (b - a) / 3;
			for(int i = 0; i < 3; i++) {
				printStars(a, a+gap);
			}
			printStars(a+gap, a+2*gap);
			printBlanks(a+gap, a+2*gap);
			printStars(a+gap, a+2*gap);
			for(int i = 0; i < 3; i++) {
				printStars(a+2*gap, b);
			}
		}
	}
}
