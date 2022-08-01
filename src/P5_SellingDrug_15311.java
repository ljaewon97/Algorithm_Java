import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5_SellingDrug_15311 {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		int N = Integer.parseInt(br.readLine());
//		int start = 0, end = 0, startValue = 1, endValue = 1, sum = 1;
//		
//		while(N != sum) {
//			if(sum < N) {
//				end++;
//				if(end % 2 == 0) {
//					endValue--;
//				}
//				else {
//					endValue += 2;
//				}
//				sum += endValue;
//			}
//			else {
//				sum -= startValue;
//				start++;
//				if(start % 2 == 0) {
//					startValue--;
//				}
//				else {
//					startValue += 2;
//				}
//			}
//		}
//		
//		sb.append(end - start + 1).append("\n");
//		for(int i = start, n = startValue; i <= end; i++) {
//			sb.append(n + " ");
//			if(i % 2 == 0) {
//				n += 2;
//			}
//			else {
//				n--;
//			}
//		}
//		
//		System.out.println(sb);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("2000\n");
		for(int i = 0; i < 1000; i++) {
			sb.append("1 ");
		}
		for(int i = 0; i < 1000; i++) {
			sb.append("1000 ");
		}
		
		System.out.println(sb);
	}
}
