package tp31;


public class Ecrit implements Runnable{

		private String texte;
		private int nombre;
		private int duree;
	
		public Ecrit(String txt, int i, int du) {
			texte = txt;
			nombre = i;
			duree = du;
		}
		public void run() {
			for(int i = 0; i<nombre; i++) {
				System.out.println(texte);
				try {
					Thread.sleep(duree);
				}catch (InterruptedException e) {
					System.out.println("\n Fin du Thread : " + e.getMessage() + "\n");
				}
			}
		}
		public void interrupt() {
			// TODO Auto-generated method stub
			
		}


		
}

