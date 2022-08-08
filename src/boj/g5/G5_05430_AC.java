package boj.g5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_05430_AC {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			char[] comms = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			int dir = 0, left = 0, right;
			String[] arr = null;
			if(str.equals("[]")) {
				right = 0;
			}
			else {
				arr = str.substring(1, str.length()-1).split(",");
				right = arr.length;
			}
			
			boolean isError = false;
			
			for(char comm: comms) {
				if(comm == 'R') {
					dir = (dir + 1) % 2;
				}
				
				if(comm == 'D') {
					if(right - left == 0) {
						isError = true;
						break;
					}
					else if(dir == 0) {
						left++;
					}
					else if(dir == 1) {
						right--;
					}
				}
				
				if(left > right) {
					isError = true;
					break;
				}
			}
			
			if(isError) {
				sb.append("error\n");
				continue;
			}
			
			sb.append("[");
			if(dir == 0) {
				for(int i = left; i < right; i++) {
					if(i == right - 1) {
						sb.append(arr[i]);
					}
					else {
						sb.append(arr[i] + ",");
					}
					
				}
			}
			else {
				for(int i = right-1; i >= left; i--) {
					if(i == left) {
						sb.append(arr[i]);
					}
					else {
						sb.append(arr[i] + ",");
					}
				}
			}
			sb.append("]\n");
		}
		
		System.out.println(sb);
	}
}
