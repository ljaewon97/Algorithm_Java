import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class S3_UniqueSubstring_11478 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		Set<String> set = new HashSet<>();
		
		for(int l = 1; l <= s.length(); l++) {
			for(int i = 0; i + l <= s.length(); i++) {
				set.add(s.substring(i, i+l));
			}
		}
		
		System.out.println(set.size());
		sc.close();
	}
}
