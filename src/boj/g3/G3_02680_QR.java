package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G3_02680_QR {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		
		int P = Integer.parseInt(br.readLine());
		
		while(P-- > 0) {
			StringBuilder sb = new StringBuilder();
			
			char[] datablocks = br.readLine().toCharArray();
			int[] bits = new int[38*4];
			
			int idx = 0, ans = 0;
			
			for(int i = 0; i < 38; i++) {
				char c = datablocks[i];
				int n = 0, j = 0;
				
				if(47 < c && c < 58) n = c - '0';
				else n = c - 'A' + 10;
				
				while(n > 0) {
					bits[idx+3-j] = n % 2;
					n /= 2;
					j++;
				}
				
				idx += 4;
			}
			
			idx = 0;
			
			while(true) {
				if(idx > 152) break;
				
				int mode = readBits(bits, idx, 4);
				idx += 4;
				
				if(mode == 0) break;
				
				if(mode == 1) {
					int count = readBits(bits, idx, 10);
					idx += 10;
					ans += count;
					
					while(count > 0) {
						if(count >= 3) {
							int num = readBits(bits, idx, 10);
							idx += 10;
							
							String temp = Integer.toString(num);
							
							if(temp.length() < 3) {
								for(int i = 0; i < 3-temp.length(); i++) {
									sb.append("0");
								}
							}
							
							sb.append(temp);
							
							count -= 3;
						}
						else if(count == 2) {
							int num = readBits(bits, idx, 7);
							idx += 7;
							
							String temp = Integer.toString(num);
							
							if(temp.length() < 2) {
								for(int i = 0; i < 2-temp.length(); i++) {
									sb.append("0");
								}
							}
							
							sb.append(temp);
							
							count -= 2;
						}
						else {
							int num = readBits(bits, idx, 4);
							idx += 4;
							
							sb.append(Integer.toString(num)); 
							
							count--;
						}
					}
				}
				else if(mode == 2) {
					int count = readBits(bits, idx, 9);
					idx += 9;
					ans += count;
					
					while(count > 0) {
						if(count >= 2) {
							int num = readBits(bits, idx, 11);
							idx += 11;
							
							int a1 = num / 45;
							int a2 = num % 45;
							
							sb.append(convertAlphaNumeric(a1));
							sb.append(convertAlphaNumeric(a2));
							
							count -= 2;
						}
						else {
							int num = readBits(bits, idx, 6);
							idx += 6;
							
							sb.append(convertAlphaNumeric(num));
							
							count--;
						}
					}
				}
				else if(mode == 4) {
					int count = readBits(bits, idx, 8);
					idx += 8;
					ans += count;
					
					while(count-- > 0) {
						int num = readBits(bits, idx, 8);
						idx += 8;
						
						if(num < 32 || num > 126) {
							sb.append(String.format("\\%02X", num));
						}
						else if(num == 92) {
							sb.append("\\");
						}
						else if(num == 35) {
							sb.append("\\#");
						}
						else {
							sb.append((char) num);
						}
					}
				}
				else if(mode == 8) {
					int count = readBits(bits, idx, 8);
					idx += 8;
					ans += count;
					
					while(count-- > 0) {
						int num = readBits(bits, idx, 13);
						idx += 13;
						
						if(num < 32 || num > 126) {
							sb.append(String.format("#%04X", num));
						}
						else if(num == 92) {
							sb.append("\\");
						}
						else if(num == 35) {
							sb.append("\\#");
						}
						else {
							sb.append((char) num);
						}
					}
				}
			}
			
			output.append(ans + " ").append(sb).append("\n");
		}
		
		System.out.println(output);
	}
	
	static int readBits(int[] bits, int idx, int len) {
		if(idx+len-1 > 152) return -1;
		
		int res = 0;
		int n = 1;
		
		for(int i = idx+len-1; i >= idx; i--) {
			res += n * bits[i];
			n *= 2;
		}
		
		return res;
	}
	
	static char convertAlphaNumeric(int n) {
		char res = ' ';
		
		if(n < 10) res = (char) (n + 48);
		else if(n < 36) res = (char) (n + 55);
		else if(n == 36) res = ' ';
		else if(n == 37) res = '$';
		else if(n == 38) res = '%';
		else if(n == 39) res = '*';
		else if(n == 40) res = '+';
		else if(n == 41) res = '-';
		else if(n == 42) res = '.';
		else if(n == 43) res = '/';
		else if(n == 44) res = ':';
		
		return res;
	}
}
