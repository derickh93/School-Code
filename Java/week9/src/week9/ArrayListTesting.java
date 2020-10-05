package week9;

public class ArrayListTesting {
	public static boolean isAlpha(char s[]) {
		for(int i = 0; s[i] != '\0'; i++) {
			if((s[i] < 65 || s[i] > 122) || (s[i] > 90 && s[i] < 97))
				return false;
		}
		return true;
	}

	public static void convertCharToBinary(char c, int binary[]) {
		int pos = 7;
		int num = c;
		while(pos >= 0) {
			if(num % 2 == 0)
				binary[pos] = 0;
			else
				binary[pos] = 1;
			num = num / 2;
			pos--;
		}

	}

	public static int countones(int binary[]) {
		int num = 0;
		for(int i = 0; binary[i] < 8;i++) {
			if(binary[i] == 1){
				num++;
			}
		}
		return num;
	}
	
	public static void main (String[] args) {
		int [] arr = null;
		convertCharToBinary('g',arr);
	}	
}
