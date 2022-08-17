package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_01541_잃어버린_괄호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int ans = 0;
		
		if(!line.contains("-")) {
			String[] temp = line.split("\\+");
			int sum = 0;
			for(int j = 0; j < temp.length; j++) {
				sum += Integer.parseInt(temp[j]);
			}
			
			ans = sum;
		}
		else {
			String[] str = line.split("-");
	
			for(int i = 0; i < str.length; i++) {
				if(str[i].contains("+")) {
					String[] temp = str[i].split("\\+");
					int sum = 0;
					for(int j = 0; j < temp.length; j++) {
						sum += Integer.parseInt(temp[j]);
					}
					
					if(i == 0) ans += sum;
					else ans -= sum;
				}
				else {
					if(i == 0) ans += Integer.parseInt(str[i]);
					else ans -= Integer.parseInt(str[i]);
				}
			}
		}
		
		System.out.println(ans);
	}
}
