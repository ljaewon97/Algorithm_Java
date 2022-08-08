package boj.s3;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class S3_11478_서로_다른_부분_문자열의_개수 {
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
