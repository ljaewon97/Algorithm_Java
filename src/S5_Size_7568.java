

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_Size_7568 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		int N = Integer.parseInt(br.readLine());
		int[][] sizes = new int[N][2];
		int[] ranks = new int[N];
		
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			sizes[i][0] = Integer.parseInt(tokens.nextToken());
			sizes[i][1] = Integer.parseInt(tokens.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			ranks[i] = getRank(sizes, i);
			System.out.printf("%d ", ranks[i]);
		}
	}
	
	static int getRank(int[][] sizes, int idx) {
		int temp = 1;
		for(int i = 0; i < sizes.length; i++) {
			if(idx == i) continue;
			if(sizes[i][0] > sizes[idx][0] && sizes[i][1] > sizes[idx][1]) temp++;
		}
		return temp;
	}
}