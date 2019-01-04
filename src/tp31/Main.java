package tp31;

public class Main {
	public static void main(String[] args) {
		Ecrit e1 = new Ecrit ("bonjour", 10, 1000) ;
		Ecrit e2 = new Ecrit ("bonsoir", 12, 1500) ;
		Ecrit e3 = new Ecrit ("\n", 5, 500) ;

		Thread t1 = new Thread(e1);
		t1.start();
		
		Thread t2 = new Thread(e2);
		t2.start();
		
		Thread t3 = new Thread(e3);
		t3.start();
	
		e3.interrupt();
	}
	
}
