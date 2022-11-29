package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S4_01015_수열_정렬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		Element[] arr = new Element[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = new Element(i, Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(arr, (o1, o2) -> o1.value - o2.value);
		
		int[] ans = new int[N];
		
		for(int i = 0; i < N; i++) {
			ans[arr[i].idx] = i;
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static class Element {
		int idx, value;
		
		public Element(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}
}
