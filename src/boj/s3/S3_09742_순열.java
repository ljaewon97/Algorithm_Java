package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3_09742_순열 {
	static char[] input, output;
	static boolean[] visited;
	static int N, target, CNT;
	static boolean fin;
	static String str;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		int[] fact = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800};
		
		while((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			str = st.nextToken();
			target = Integer.parseInt(st.nextToken());
			input = str.toCharArray();
			Arrays.sort(input);
			
			N = input.length;
			CNT = 0;
			fin = false;
			output = new char[N];
			visited = new boolean[N];
			
			sb.append(String.format("%s %d = ", str, target));
			if(target > fact[N]) {
				sb.append("No permutation\n");
				continue;
			}
			
			perm(0);
		}
		
		System.out.println(sb);
	}
	
	static void perm(int cnt) {
		if(cnt == N) {
			CNT++;
			if(CNT == target) {
				String cur = new String(output, 0, N);
				sb.append(cur + "\n");
				fin = true;
			}
			return;
		}
		
		if(fin) return;
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			output[cnt] = input[i];
			visited[i] = true;
			perm(cnt + 1);
			visited[i] = false;
		}
	}
}
