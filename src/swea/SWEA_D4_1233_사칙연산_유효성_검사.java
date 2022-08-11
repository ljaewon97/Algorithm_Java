package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_D4_1233_사칙연산_유효성_검사 {
	static String[] tree;
	static boolean valid;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			tree = new String[N+1];
			valid = true;
			
			for(int i = 1; i <= N; i++) {
				String[] line = br.readLine().split(" ");
				tree[i] = line[1];
			}
			
			if(N % 2 == 0) {
				sb.append(String.format("#%d %d\n", t, 0));
				continue;
			}
			
			inorder(1);
			
			sb.append(String.format("#%d %d\n", t, (valid ? 1 : 0)));
		}
		
		System.out.println(sb);
	}
	
	static void inorder(int cur) {
		if(2*cur > N) {
			if(isOper(tree[cur])) {
				valid = false;
			}
			return;
		}
		
		inorder(2*cur);
		inorder(2*cur+1);
	}
	
	static boolean isOper(String str) {
		return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
	}
	
	static boolean isNum(String str) {
		return !isOper(str);
	}
}
