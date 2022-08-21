package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_16637_괄호_추가하기 {
	static char[] arr;
	static int N, ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = br.readLine().toCharArray();
		
		recur(0, arr[0] - '0');
		
		System.out.println(ans);
	}
	
	static void recur(int nth, int res) {
		if(nth == N-1) {
			ans = Math.max(ans, res);
			return;
		}
		
		if(nth <= N-3) {
			int b = arr[nth+2] - '0';
			int n1 = calc(res, arr[nth+1], b);
			
			recur(nth+2, n1);
		}
		
		if(nth <= N-5) {
			int b = arr[nth+2] - '0';
			int c = arr[nth+4] - '0';
			int n2 = calc(res, arr[nth+1], calc(b, arr[nth+3], c));
			
			recur(nth+4, n2);
		}
	}
	
	static int calc(int a, char o, int b) {
		if(o == '+') return a + b;
		else if(o == '-') return a - b;
		else return a * b;
	}
}
