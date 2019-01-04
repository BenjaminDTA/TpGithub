package tp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Dessin {

	List<Figure> figures;

	public Dessin() {
		figures = new ArrayList<>();
	}

	public boolean add(Figure figure) {
		return figures.add(figure);
	}

	public Collection<Figure> getFigures() {
		return figures;
	}

}
