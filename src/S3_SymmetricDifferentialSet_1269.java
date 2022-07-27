import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_SymmetricDifferentialSet_1269 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int arr[] = new int[100000001];
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < a; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num > max) {
				max = num;
			}
			arr[num]++;
		}
		
		int inter = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < b; i++) {
			if(arr[Integer.parseInt(st.nextToken())] == 1) {
				inter++;
			}
		}
		
		System.out.println(a + b - 2 * inter);
	}
}
