package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S3_02992_크면서_작은_수 {
	static int[] X, result, ans, num;
	static boolean[] visited;
	static boolean flag, fin;
	static int len;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		len = str.length();
		
		X = new int[len];
		result = new int[len];
		visited = new boolean[len];
		ans = new int[] {0};
		
		for(int i = 0; i < len; i++) {
			X[i] = str.charAt(i) - '0';
		}
		
		num = X.clone();
		
		Arrays.sort(X);
		
		perm(0);
		
		for(int i = 0; i < ans.length; i++) {
			sb.append(ans[i]);
		}
		
		System.out.println(sb);
	}
	
	static void perm(int nth) {
		if(fin) return;
		
		if(nth == len) {
			if(flag) {
				ans = result.clone();
				fin = true;
				return;
			}
			
			if(isSame()) flag = true;
			
			return;
		}
		
		int prev = -1;
		for(int i = 0; i < len; i++) {
			if(!visited[i] && X[i] != prev) {
				result[nth] = prev = X[i];
				visited[i] = true;
				perm(nth+1);
				visited[i] = false;
			}
		}
	}
	
	static boolean isSame() {
		for(int i = 0; i < len; i++) {
			if(num[i] != result[i]) return false;
		}
		
		return true;
	}
}
