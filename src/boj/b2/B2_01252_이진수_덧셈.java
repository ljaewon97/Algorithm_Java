package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_01252_이진수_덧셈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str1 = st.nextToken();
		String str2 = st.nextToken();
		
		int[] A = new int[str1.length()];
		int[] B = new int[str2.length()];
		int[] res = new int[Math.max(A.length, B.length)+1];
		int[] temp = new int[Math.max(A.length, B.length)+1];
		
		for(int i = 0; i < str1.length(); i++) A[i] = str1.charAt(i)-'0';
		for(int i = 0; i < str2.length(); i++) B[i] = str2.charAt(i)-'0';
		
		for(int i = 1; i <= Math.min(A.length, B.length); i++) {
			int sum = A[A.length-i] + B[B.length-i] + temp[temp.length-i];
			temp[temp.length-i-1] = sum / 2;
			res[res.length-i] = sum % 2;
		}
		
		for(int i = Math.min(A.length, B.length)+1; i <= Math.max(A.length, B.length); i++) {
			int sum = temp[temp.length-i];
			if(A.length > B.length) sum += A[A.length-i];
			else sum += B[B.length-i];
			temp[temp.length-i-1] = sum / 2;
			res[res.length-i] = sum % 2;
		}
		
		if(temp[0] == 1) res[0] = 1;
		
		System.out.println(removeLeadingZero(res));
	}
	
	static String removeLeadingZero(int[] arr) {
		StringBuilder ret = new StringBuilder();
		boolean valid = false;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 1) valid = true;
			if(valid) ret.append(arr[i]);
		}
		
		return valid ? ret.toString() : "0";
	}
}
