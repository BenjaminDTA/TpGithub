package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import tp.Carre;
import tp.Couleur;
import tp.Dessin;
import tp.Figure;
import tp.ImpressionHorsLimiteException;
import tp.Point;
import tp.Rectangle;
import tp.Rond;
import tp.Segment;
import tp.Surfacable;

public class FigureUtil {

	private static final int MIN = 0;
	private static final int MAX = 50;
	private static final int LIMITE_X = 50;
	private static final int LIMITE_Y = 50;

	private static Map<String, Figure> figures = new HashMap<>();

	public static Point getRandomPoint() {

		return new Point(ThreadLocalRandom.current().nextInt(MIN, MAX), ThreadLocalRandom.current().nextInt(MIN, MAX));
	}

	public static Rond getRandomRond(Couleur couleur) {
		Rond rond = new Rond(getRandomPoint(), ThreadLocalRandom.current().nextInt(MIN, MAX), couleur);
		figures.put(rond.getId(), rond);
		return rond;
	}

	public static Rectangle getRandomRectangle(Couleur couleur) {
		Rectangle rectangle = new Rectangle(getRandomPoint(), ThreadLocalRandom.current().nextInt(MIN, MAX),
				ThreadLocalRandom.current().nextInt(MIN, MAX), couleur);
		figures.put(rectangle.getId(), rectangle);

		return rectangle;
	}

	public static Carre getRandomCarre(Couleur couleur) {
		Carre carre = new Carre(getRandomPoint(), ThreadLocalRandom.current().nextInt(MIN, MAX), couleur);
		figures.put(carre.getId(), carre);
		return carre;
	}

	public static Segment getRandomSegment(Couleur couleur) {
		Segment segment;
		int nb = ThreadLocalRandom.current().nextInt(MIN, MAX);
		if (nb % 2 == 0) {
			segment = new Segment(getRandomPoint(), nb, true, couleur);
		} else {
			segment = new Segment(getRandomPoint(), nb, false, couleur);
		}
		figures.put(segment.getId(), segment);
		return segment;
	}

	public static Figure getRandomFigure() {
		int chx = ThreadLocalRandom.current().nextInt(0, 4);
		switch (chx) {
		case 0:
			return getRandomCarre(Couleur.getRandomCouleur());
		case 1:
			return getRandomRectangle(Couleur.getRandomCouleur());
		case 2:
			return getRandomRond(Couleur.getRandomCouleur());
		default:
			return getRandomSegment(Couleur.getRandomCouleur());
		}
	}

	public static Surfacable getRandomSurfacable() {
		int chx = ThreadLocalRandom.current().nextInt(0, 3);
		switch (chx) {
		case 0:
			return getRandomCarre(Couleur.getRandomCouleur());
		case 1:
			return getRandomRectangle(Couleur.getRandomCouleur());
		case 2:
		default:
			return getRandomRond(Couleur.getRandomCouleur());
		}
	}

//	public static Collection<Point> getPoints(Figure[] figures){
//		List<Point> points = new ArrayList<>();
//		for(int i = 0 ; i< figures.length ; i++) {
//			points.addAll(figures[i].getPoints());
//		}
//		return points;
//		
//	}

	public static Collection<Point> getPoints(Figure... figures) {
		List<Point> points = new ArrayList<>();
		for (int i = 0; i < figures.length; i++) {
			points.addAll(figures[i].getPoints());
		}
		return points;
	}

	public static Collection<Figure> genere(int n) {
		List<Figure> figures = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			figures.add(getRandomFigure());
		}
		return figures;
	}

	public static Figure getFigureEn(Point p, Dessin d) {

//		Iterator<Figure> iter = d.getFigures().iterator();
//		while(iter.hasNext()) {
//			Figure f = iter.next();
//			if(f.couvre(p)) {
//				return f;
//			}
//		}
//		return null;

		for (Figure f : d.getFigures()) {
			if (f.couvre(p)) {
				return f;
			}
		}
		return null;
	}

	public static List<Figure> trieProcheOrigine(Dessin dessin) {
		return dessin.getFigures().stream().sorted().collect(Collectors.toList());

//		ArrayList<Figure> sort = new ArrayList<>(dessin.getFigures())
//		Collections.sort(sort);
//		return sort;
	}

	public static List<Figure> trieSurface(Dessin dessin) {
//JAVA 8 utilisation du Lambda l.153
		return dessin.getFigures().stream().filter(f -> f instanceof Surfacable).sorted((f1, f2) -> {
			double s1 = ((Surfacable) f1).surface();
			double s2 = ((Surfacable) f2).surface();
			if (s1 > s2) {
				return 1;
			} else if (s1 < s2) {
				return -1;
			}
			return 0;
		}).collect(Collectors.toList());

//JAVA PRE8
//		ArrayList<Figure> sort = new ArrayList<>(dessin.getFigures());
//		ArrayList<Figure> surfacable = new ArrayList<>();
//		for(Figure test : sort)  {
//			if(test instanceof Surfacable) {
//				surfacable.add(test);
//			}
//		}
//		Collections.sort(surfacable, new Comparator<Figure>() {
//			@Override
//			public int compare(Figure o1, Figure o2) {
//				Surfacable s1 = (Surfacable) o1;
//				Surfacable s2 = (Surfacable) o2;
//				if (s1.surface() > s2.surface()) {
//					return 1;
//				} else if (s1.surface() < s2.surface()) {
//					return -1;
//				}
//				return 0;
//			}
//		});
//		return surfacable;
	}

	public static Figure getFigureMap(String id) {
		return figures.get(id);
	}

	public static String imprim(Dessin d) throws ImpressionHorsLimiteException {
		List<Figure> figuresAImprimer = new ArrayList<>();
		StringBuilder str = new StringBuilder();
		for (Figure entry : d.getFigures()) {
			boolean horsLimite = false;
			for (Point p : entry.getPoints()) {
				if (p.getX() < 0 || p.getX() >= LIMITE_X || p.getY() < 0 || p.getY() >= LIMITE_Y) {
					horsLimite = true;
				}
			}
			if (!horsLimite) {
				figuresAImprimer.add(entry);
			}
		}

		for (Figure f : figuresAImprimer) {
			str.append(f + "\r\n");
		}
		str.append("=================================================================\r\n");
		char tab[][] = new char[LIMITE_X + 1][LIMITE_Y + 1];
		for (int y = 0; y < LIMITE_Y + 1; y++) {
			for (int x = 0; x < LIMITE_X + 1; x++) {
				tab[x][y] = ' ';
			}
		}
	//	figuresAImprimer.stream().forEach(x -> x.getPoints().stream().forEach(y -> tab[y.getX()][y.getY()] = x.getCouleur().Abreviation()));
		for (Figure f : figuresAImprimer) {
			for (Point p : f.getPoints()) {
				tab[p.getX()][p.getY()] = f.getCouleur().Abreviation();
			}
		}
		for (int y = 0; y < LIMITE_Y + 1; y++) {
			for (int x = 0; x < LIMITE_X + 1; x++) {
				str.append(tab[x][y]);
			}
			str.append("\r\n");
		}
		return str.toString();
	}

	public static void sauvegarde(String str) throws FileNotFoundException {
		File fichier = new File("C:\\Users\\formation\\eclipse-workspace\\Tp10\\src\\tp\\test.txt");
		PrintWriter writer = new PrintWriter(fichier);
		writer.println(str.toString());
		writer.close();

	}

	public static void imprim() throws ImpressionHorsLimiteException {
		for (Figure f : figures.values()) {
			for (Point p : f.getPoints()) {
				if (p.getX() < 0 || p.getX() > 99 || p.getY() < 0 || p.getY() > 99)
					throw new ImpressionHorsLimiteException("Le point est hors limite");
			}
		}
	}

	public static void charger(File fichier) throws IOException {
		FileReader reader = new FileReader(fichier);
		BufferedReader bf = new BufferedReader(reader);
		System.out.println("LECTURE");
		boolean separator = false;
		char tab[][] = new char[LIMITE_X][LIMITE_Y];
		int idxY = 0;
		while (true) {
			String line = bf.readLine();
			if (line == null) {
				break;
			}
			if (line.startsWith("=")) {
				separator = true;
				continue;
			}
			if (separator) {
				for (int i = 0; i < LIMITE_X; i++) {
					tab[idxY][i] = line.charAt(i);
				}
				idxY++;
				if (idxY >= LIMITE_Y) {
					break;
				}
			}
		}
		System.out.println("FIN LECTURE");
		bf.close();
		reader.close();
	}
}
