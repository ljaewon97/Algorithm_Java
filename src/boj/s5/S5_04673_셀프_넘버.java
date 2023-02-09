package boj.s5;

public class S5_04673_셀프_넘버 {
	 public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		boolean[] arr = new boolean[10001];
		
		for(int i = 1; i <= 10000; ++i) {
			int num = i, x = i;
			while(x > 0) {
				num += x%10;
				x /= 10;
			}
			if(num <= 10000) arr[num] = true;
		}
		
		for(int i = 1; i <= 10000; ++i) {
			if(!arr[i]) sb.append(i).append("\n");
		}
		
		System.out.println(sb);
	}
}
