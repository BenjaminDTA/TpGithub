package tp;

import java.util.ArrayList;
import java.util.Collection;

public class Rectangle extends Figure implements Surfacable {

	private Point pointBasGauche;
	private Point pointBasDroit;
	private Point pointHautGauche;
	private Point pointHautDroit;

	public Rectangle(Point point, int w, int h) {
		pointBasGauche = point;
		pointBasDroit = new Point(point.getX() + w, point.getY());
		pointHautGauche = new Point(point.getX(), point.getY() + h);
		pointHautDroit = new Point(point.getX() + w, point.getY() + h);
		couleur = Couleur.getCouleurDefault();
	}

	public Rectangle(Point point, int w, int h, Couleur couleur) {
		this(point, w, h);
		this.couleur = couleur;
	}

	public boolean couvre(Point point) {
		int xMin;
		int xMax;
		int yMin;
		int yMax;
		if (pointBasGauche.getX() < pointHautDroit.getX()) {
			xMin = pointBasGauche.getX();
			xMax = pointHautDroit.getX();
		} else {
			xMax = pointBasGauche.getX();
			xMin = pointHautDroit.getX();
		}
		if (pointBasGauche.getY() < pointHautDroit.getY()) {
			yMin = pointBasGauche.getY();
			yMax = pointHautDroit.getY();
		} else {
			yMax = pointBasGauche.getY();
			yMin = pointHautDroit.getY();
		}
		if ((point.getX() >= xMin && point.getX() <= xMax) && (point.getY() > yMin && point.getY() <= yMax)) {
			return true;
		}
		return false;
	}

	@Override
	public double surface() {
		return Math.abs(pointBasDroit.getX() - pointBasGauche.getX())
				* Math.abs(pointHautDroit.getY() - pointBasDroit.getY());
	}

	public Point getPointBasGauche() {
		return pointBasGauche;
	}

	public Point getPointBasDroit() {
		return pointBasDroit;
	}

	public Point getPointHautGauche() {
		return pointHautGauche;
	}

	public Point getPointHautDroit() {
		return pointHautDroit;
	}

	protected String getType() {
		return "RECT";
	}

	@Override
	public Collection<Point> getPoints() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(pointBasGauche);
		points.add(pointBasDroit);
		points.add(pointHautGauche);
		points.add(pointHautDroit);
		return points;
	}

	@Override
	public String toString() {
		return getType() + "[pointBasGauche=" + pointBasGauche + ", pointBasDroit=" + pointBasDroit
				+ ", pointHautGauche=" + pointHautGauche + ", pointHautDroit=" + pointHautDroit + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Rectangle other = (Rectangle) obj;
		if (pointBasDroit == null) {
			if (other.pointBasDroit != null)
				return false;
		} else if (!pointBasDroit.equals(other.pointBasDroit))
			return false;
		if (pointBasGauche == null) {
			if (other.pointBasGauche != null)
				return false;
		} else if (!pointBasGauche.equals(other.pointBasGauche))
			return false;
		if (pointHautDroit == null) {
			if (other.pointHautDroit != null)
				return false;
		} else if (!pointHautDroit.equals(other.pointHautDroit))
			return false;
		if (pointHautGauche == null) {
			if (other.pointHautGauche != null)
				return false;
		} else if (!pointHautGauche.equals(other.pointHautGauche))
			return false;
		return true;
	}
}
