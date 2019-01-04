package tp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Figure implements Comparable<Figure> {

	private static int idx = 0;
	protected Couleur couleur;

	public abstract String toString();

	public void affiche() {
		System.out.println(toString()+ couleur.name());
	}

	public abstract Collection<Point> getPoints();

	public abstract boolean couvre(Point points);

	public double distanceOrigine() {
		Collection<Point> points = this.getPoints();
		List<Double> distance = new ArrayList<>();
		for (Point p : points) {
			distance.add(p.distanceOrigine());
		}
		return Collections.min(distance);
	}

	@Override
	public int compareTo(Figure o) {
		if (this.distanceOrigine() > o.distanceOrigine()) {
			return 1;
		} else if (this.distanceOrigine() < o.distanceOrigine()) {
			return -1;
		}
		return 0;
	}

	public String getId() {
		idx++;
		return this.getClass().getName() + idx;
	}

	public Couleur getCouleur() {
		return couleur;
	}
	

}