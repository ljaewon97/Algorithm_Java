package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class P5_16496_큰_수_만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		boolean allzero = true;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = st.nextToken();
			if(allzero && !arr[i].equals("0")) allzero = false;
		}
		
		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String o1, String o2) {
				int l1 = o1.length();
				int l2 = o2.length();
				int i = 0;
				
				while(i < l1 + l2) {
					char v1 = i < l1 ? o1.charAt(i) : o2.charAt(i-l1);
					char v2 = i < l2 ? o2.charAt(i) : o1.charAt(i-l2);
					
					if(v1 != v2) {
						return v2 - v1;
					}
					
					i++;
				}
				
				return 0;
			}
		});
		
		for(int i = 0; i < N; i++) {
			sb.append(arr[i]);
		}
		
		if(allzero) {
			System.out.println(0);
		}
		else {
			System.out.println(sb);
		}
	}
}
