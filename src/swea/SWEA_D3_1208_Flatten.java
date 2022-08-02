package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_1208_Flatten {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t = 1; t <= 10; t++) {
			int[] boxes = new int[101];
			int min = 101, max = 0;
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 100; i++) {
				int h = Integer.parseInt(st.nextToken());
				boxes[h]++;
				if(h > max) {
					max = h;
				}
				if(h < min) {
					min = h;
				}
			}
			
			while(N > 0 && min != max) {
				boxes[min]--;
				boxes[min+1]++;
				if(boxes[min] == 0) {
					min++;
				}
				
				boxes[max]--;
				boxes[max-1]++;
				if(boxes[max] == 0) {
					max--;
				}
				
				N--;
				
			}
			
			sb.append(String.format("#%d %d\n", t, max - min));
		}
		
		System.out.println(sb);
	}
}
