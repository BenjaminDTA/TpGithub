package tp;

public class Carre extends Rectangle {

	public Carre(Point point, int x) {
		super(point, x, x);
		couleur = Couleur.getCouleurDefault();
	}
	//blablabla

	public Carre(Point point, int x, Couleur couleur) {
		this(point, x);
		this.couleur = couleur;
	}

	@Override
	public String toString() {
		return getType() + "[getPointBasGauche()=" + getPointBasGauche() + ", getPointBasDroit()=" + getPointBasDroit()
				+ ", getPointHautGauche()=" + getPointHautGauche() + ", getPointHautDroit()=" + getPointHautDroit()
				+ "]";
	}// facultatif du � l'h�ritage !!!

	protected String getType() {
		return "CARRE";
	}

}
