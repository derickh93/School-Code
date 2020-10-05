
public class Testing {
	
	public static void main(String[] args) {
		if(args.length != 0) {
			for(int i = 0; i < args.length;i++)
				System.out.println("Argument # " + i + ":  " +  args[i]);
		}
		else
			System.out.println("No Parameters");
	
	
	int first = Integer.parseInt(args[0]);
	int second = Integer.parseInt(args[1]);
	System.out.println(first & second);
	System.out.println(first | second);
	System.out.println(first ^ second);
	System.out.println(first >> 1);
	System.out.println(first << 1);
	}
}
