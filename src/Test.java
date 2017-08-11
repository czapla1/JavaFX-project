
public class Test {

	public static void main(String[] args) {

		
		String a="wyraz";
		/*StringBuffer b=new StringBuffer();
		
		for(int i=0; i<a.length(); i++){
			b.append(a.charAt(i));
		}
		
		System.out.println(b.reverse());*/
		
		String []table= new String [a.length()];
		
		for(int i=0; i<a.length();i++){
			table[i]=a.substring(i,i+1);
		}
		String reverse="";
		for(int i=table.length-1; i>=0; i--){
			reverse+=table[i];
		}
		System.out.println(reverse);
		
	}

}
