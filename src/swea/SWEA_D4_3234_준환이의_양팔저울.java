package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D4_3234_준환이의_양팔저울 {
	static int[] weights, fact;
	static boolean[] visited;
	static int N, ans, left, right, sum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		fact = new int[10];
		fact[1] = 1;
		for(int i = 2; i < 10; i++) {
			fact[i] = fact[i-1] * i;
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			ans = 0; left = 0; right = 0; sum = 0;
			
			weights = new int[N];
			visited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
				sum += weights[i];
			}
			
			recur(0, 0, 0);
			//recur2(0, new boolean[N], new int[N], new int[N]);
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void recur(int nth, int left, int right) {
		if(right > left) return;
		
		if(nth == N) {
			ans++;
			return;
		}
		
		if(left >= (sum % 2 == 1 ? sum + 1 : sum) / 2) {
			int k = N - nth;
			ans += (int) Math.pow(2, k) * fact[k];
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				recur(nth+1, left+weights[i], right);
				recur(nth+1, left, right+weights[i]);
				visited[i] = false;
			}
		}
	}
	
	static void recur2(int nth, boolean[] visited, int[] left, int[] right) {
		if(nth == N) {
			ans++;
			System.out.printf("case #%d\n", ans);
			System.out.println("left: "+Arrays.toString(left));
			System.out.println("right: "+Arrays.toString(right));
			System.out.println();
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				left[nth] = weights[i];
				recur2(nth+1, visited, left, right);
				left[nth] = 0;
				right[nth] = weights[i];
				recur2(nth+1, visited, left, right);
				right[nth] = 0;
				visited[i] = false;
			}
		}
	}
}
