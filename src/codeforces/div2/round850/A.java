package codeforces.div2.round850;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine())-1;
			boolean white = false;
			int t = 0;
			int i = 2;
			int aw = 1;
			int ab = 0;
			int bw = 0;
			int bb = 0;
			
			while(true) {
				int w = 0;
				int b = 0;
				
				int temp = Math.min(i++, n);
				
				if(temp % 2 == 0) {
					if(t == 1) {
						aw += temp/2;
						ab += temp/2;
					}
					else {
						bw += temp/2;
						bb += temp/2;
					}
					
					n -= temp;
				}
				else {
					if(white) {
						if(t == 1) {
							aw += temp/2+1;
							ab += temp/2;
						}
						else {
							bw += temp/2+1;
							bb += temp/2;
						}
					}
					else {
						if(t == 1) {
							aw += temp/2;
							ab += temp/2+1;
						}
						else {
							bw += temp/2;
							bb += temp/2+1;
						}
					}
					
					n -= temp;
					white = !white;
				}
				
				if(n == 0) break;
				
				temp = Math.min(i++, n);

				if(temp % 2 == 0) {
					if(t == 1) {
						aw += temp/2;
						ab += temp/2;
					}
					else {
						bw += temp/2;
						bb += temp/2;
					}
					
					n -= temp;
				}
				else {
					if(white) {
						if(t == 1) {
							aw += temp/2+1;
							ab += temp/2;
						}
						else {
							bw += temp/2+1;
							bb += temp/2;
						}
					}
					else {
						if(t == 1) {
							aw += temp/2;
							ab += temp/2+1;
						}
						else {
							bw += temp/2;
							bb += temp/2+1;
						}
					}
					
					n -= temp;
					white = !white;
				}
				
				if(n == 0) break;
				
				t = 1-t;
			}
			
			sb.append(aw).append(" ").append(ab).append(" ").append(bw).append(" ").append(bb).append("\n");
		}
		
		System.out.println(sb);
	}
}
