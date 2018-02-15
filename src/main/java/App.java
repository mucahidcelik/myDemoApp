public class App{
	public static void main(String[] args) {
		int[] a={1,2,3,4,5};
		int[] b={4,5};
		System.out.println(containsAll(a,b,1,8));

	}
	public static int containsAll(int[] bigArray,int[] smallArray,int minSize,int maxSize){
		if(bigArray.length<=smallArray.length||maxSize<bigArray.length||minSize>smallArray.length||smallArray.length<1)
			return -1;

		boolean flag=true;
		int i;
		for( i=0;i<(bigArray.length-smallArray.length+1);i++){
			flag=true;
			for (int j=0;j<smallArray.length; j++) {
				if(bigArray[i+j]!=smallArray[j])
					flag=false;
			}
			if(flag)
				return i;
		}
		return -1;
	}
}