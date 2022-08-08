package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_D3_1228_암호문_1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			List<Integer> list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				String I = st.nextToken();
				int idx = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				
				List<Integer> temp = new ArrayList<>();
				
				for(int i = 0; i < num; i++) {
					temp.add(Integer.parseInt(st.nextToken()));
				}
				
				list.addAll(idx, temp);
			}
			
			sb.append(String.format("#%d ", t));
			
			for(int i = 0; i < 10; i++) {
				sb.append(String.format("%d ", list.get(i)));
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
