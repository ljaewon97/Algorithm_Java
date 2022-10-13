package boj.s2;

public class S2_04105_유클리드 {
	static Reader in = new Reader();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

		while(true) {
			double[] c = new double[12];
			boolean end = true;
			
			for(int i = 0; i < 12; i++) {
				c[i] = in.nextDouble();
				if(c[i] != 0) end = false;
			}
			
			if(end) break;
			
			double areaDEF = calcArea(c[6], c[7], c[8], c[9], c[10], c[11]) / 2;
			
			double[] vecAC = {c[4]-c[0], c[5]-c[1]};
			double S = calcArea(c[0], c[1], c[2], c[3], c[4], c[5]);
			
			double x = areaDEF / S;
			double[] vecAH = {vecAC[0]*x, vecAC[1]*x};
			
			sb.append(String.format("%.3f %.3f %.3f %.3f\n", vecAH[0]+c[2], vecAH[1]+c[3], vecAH[0]+c[0], vecAH[1]+c[1]));
		}
		
		System.out.println(sb);
	}
	
	static double calcArea(double x1, double y1, double x2, double y2, double x3, double y3) {
		return Math.abs(x1*y2-x2*y1+x2*y3-x3*y2+x3*y1-x1*y3);
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;
		
		public double nextDouble() throws Exception
    	{
    		double ret = 0, div = 1;
    		byte c = read();
    		while (c <= ' ')
    			c = read();
    		boolean neg = (c == '-');
    		if (neg)
    			c = read();

    		do {
    			ret = ret * 10 + c - '0';
    		}
    		while ((c = read()) >= '0' && c <= '9');

    		if (c == '.')
    		{
    			while ((c = read()) >= '0' && c <= '9')
    			{
    				ret += (c - '0') / (div *= 10);
    			}
    		}

    		if (neg)
    			return -ret;
    		return ret;
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
}
