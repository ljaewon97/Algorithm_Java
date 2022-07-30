import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_Hashing_15829 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		String str = br.readLine();
		long hash = 0L;
		
		for(int i = L-1; i >= 0; i--) {
			int c = str.charAt(i) - 'a' + 1;
			hash = ((hash * 31) + c) % 1234567891;
		}
		
		System.out.println(hash);
	}
}
