package autom.common;

public class runTest18 {

	public static void main ( String [ ] args) {
		RegularTC nonAdTC = new RegularTC();
		if(nonAdTC.ADRun){
			nonAdTC=null;
			System.gc();
			ADTest.main(null);
			System.exit(0);
		}
		System.exit(0);
	}
}