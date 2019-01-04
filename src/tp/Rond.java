package tp;

import java.util.ArrayList;
import java.util.Collection;

public class Rond extends Figure implements Surfacable {

	private int r;
	private Point p;

	public Rond(Point p, int r) {
		super();
		this.r = r;
		this.p = p;
		couleur = Couleur.getCouleurDefault();
	}

	public Rond(Point p, int r, Couleur couleur) {
		this(p, r);
		this.couleur = couleur;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "Rond [r=" + r + ", p=" + p + "]";
	}

	@Override
	public double surface() {
		return Math.PI * Math.pow(r, 2.0);
	}

	public Collection<Point> getPoints() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(p);
		return points;
	}

	public boolean couvre(Point point) {
		return Math.sqrt(Math.pow((point.getX() - this.p.getX()), 2))
				+ (Math.pow((point.getY() - this.p.getY()), 2)) <= r;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rond other = (Rond) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (r != other.r)
			return false;
		return true;
	}

}
