package tp;

import java.util.ArrayList;
import java.util.Collection;

public class Segment extends Figure {
	private Point p1;
	private Point p2;
	private boolean horizontal;

	public Segment(Point d, int longueur, boolean horizontal) {
		this.horizontal = horizontal;
		if (horizontal) {
			p1 = d;
			p2 = new Point(d.getX() + longueur, d.getY());
		} else {
			p1 = d;
			p2 = new Point(d.getX(), longueur + d.getY());
		}
		couleur = Couleur.getCouleurDefault();
	}
	public Segment(Point d, int longueur, boolean horizontal, Couleur couleur) {
		this.horizontal = horizontal;
		if (horizontal) {
			p1 = d;
			p2 = new Point(d.getX() + longueur, d.getY());
		} else {
			p1 = d;
			p2 = new Point(d.getX(), longueur + d.getY());
		}
		this.couleur= couleur;
	}
	@Override
	public String toString() {
		return "[Debut" + p1 + "]" + "à  [Fin" + p2 + "]";
	}

	public Collection<Point> getPoints() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(p1);
		points.add(p2);
		return points;
	}

	public boolean couvre(Point point) {
		return (horizontal && point.getY() == p1.getY() && point.getX() >= p1.getX() && point.getX() <= p2.getX())
				|| (!horizontal && point.getX() == p2.getX() && point.getY() >= p1.getY() && point.getY() <= p2.getY());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segment other = (Segment) obj;
		if (horizontal != other.horizontal)
			return false;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}

	

}
