package tp;

public class Point {
	private int x;
	private int y;

	private final int INIT_X = 25;
	private final int INIT_Y = 25;

	public Point() {
		x = INIT_X;
		y = INIT_Y;
	}

	public Point(int a, int b) {
		x = a;
		y = b;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void affiche() {
		System.out.println(this.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

//TP13
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public double distance(Point p) {
		return Math.sqrt(Math.pow((x - p.getX()), 2)) + (Math.pow((y - p.getX()), 2));
	}

	public double distanceOrigine() {
		return Math.sqrt(Math.pow((x - this.INIT_X), 2)) + (Math.pow((y - this.INIT_Y), 2));
	}

}
