package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_04375_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while((line = br.readLine()) != null) {
			int N = Integer.parseInt(line);
			int num = 0;
			
			for(int i = 1; i <= N; i++) {
				num = num * 10 + 1;
				num %= N;
				if(num == 0) {
					sb.append(i).append("\n");
					break;
				}
			}
		}
		
		System.out.println(sb);
	}
}
