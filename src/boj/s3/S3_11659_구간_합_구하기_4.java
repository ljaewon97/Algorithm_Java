package boj.s3;

public class S3_11659_구간_합_구하기_4 {
	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int M = in.nextInt();
		int[] sum = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			sum[i] = sum[i-1] + in.nextInt();
		}
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			sb.append(sum[b] - sum[a-1] + "\n");
		}
		
		System.out.println(sb);
	}
}

class Reader {
	final int SIZE = 1 << 13;
	byte[] buffer = new byte[SIZE];
	int index, size;

	int nextInt() throws Exception {
		int n = 0;
		byte c;
		while ((c = read()) <= 32)
			;
		do
			n = (n << 3) + (n << 1) + (c & 15);
		while (isNumber(c = read()));
		return n;
	}

	boolean isNumber(byte c) {
		return 47 < c && c < 58;
	}

	byte read() throws Exception {
		if (index == size) {
			size = System.in.read(buffer, index = 0, SIZE);
			if (size < 0)
				buffer[0] = -1;
		}
		return buffer[index++];
	}
}