package A5;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1;i<6;i++)
			System.out.printf("i=%d, f(%d)=%.2f\n",i,i,Q1.recursiveSum(i));
	}
	
	public static double recursiveSum(int n){

		if(n==1){
			return 0.5;
		}
			
		else
			return (1.0/(2*n))+recursiveSum(n-1);			
	}

}
