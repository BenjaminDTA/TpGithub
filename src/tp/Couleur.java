package tp;

import java.util.concurrent.ThreadLocalRandom;

public enum Couleur {
	ROUGE('R'), VERT('V'), BLEU('B'), JAUNE('J'), NOIR('N');

	private char valeur;

	private Couleur(char valeur) {
		this.valeur = valeur;
	}

	public static Couleur getCouleurDefault() {
		return Couleur.NOIR;
	}

	public static Couleur getRandomCouleur() {
		return Couleur.values()[ThreadLocalRandom.current().nextInt(Couleur.values().length)];
	}

	public char getValeur() {
		return valeur;
	}
	
	public char Abreviation() {
		return valeur;
	}

}
