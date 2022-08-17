package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_01759_암호_만들기 {
	static StringBuilder sb = new StringBuilder();
	static char[] arr, result;
	static int L, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		result = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		
		comb(0, 0, 0, 0);
		
		System.out.println(sb);
	}
	
	static void comb(int nth, int start, int ja, int mo) {
		if(nth == L) {
			if(ja >= 2 && mo >= 1) {
				sb.append(new String(result)).append("\n");
			}
			return;
		}
		
		for(int i = start; i < C; i++) {
			result[nth] = arr[i];
			if(aeiou(arr[i])) {
				comb(nth+1, i+1, ja, mo+1);
			}
			else {
				comb(nth+1, i+1, ja+1, mo);
			}
		}
	}
	
	static boolean aeiou(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
