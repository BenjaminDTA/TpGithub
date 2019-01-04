package tp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import utils.FigureUtil;

public class Main {

	public static void main(String[] args) {


		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("Date et heure courante : " + currentTime);

		Point p = new Point(6, 4);
		p.toString();
		System.out.println(p.toString());

		Rond r = new Rond(p, 2);
		r.affiche();

		Rectangle rect = new Rectangle(p, 2, 2);
		rect.affiche();

		FigureUtil.getRandomRectangle(Couleur.getRandomCouleur()).affiche();
		FigureUtil.getRandomRond(Couleur.getRandomCouleur()).affiche();
		FigureUtil.getRandomPoint().affiche();
		FigureUtil.getRandomFigure().affiche();

		Carre c = new Carre(p, 4);
		c.affiche();
		Point p1 = new Point(2, 5);
		Point p2 = new Point(4, 8);
		System.out.println(p1.equals(p2));

		Surfacable s = FigureUtil.getRandomSurfacable();
		System.out.println(s.getClass() + " " + s.surface());

//		Figure[] figureTab = new Figure[1];
//		figureTab[0] = FigureUtil.getRandomFigure();
//		Collection<Point> points = FigureUtil.getPoints(figureTab);
		System.out.println("\n\n");
		Collection<Point> points = FigureUtil.getPoints(FigureUtil.getRandomFigure(), FigureUtil.getRandomFigure());
		for (Point pp : points) {
			System.out.println("test" + pp);
			pp.affiche();
			System.out.println("\nzfeze\n");
		}
		System.out.println("\n\n");

//		Collection<Figure> figures = FigureUtil.genere(10);
//		for (Figure ff : figures) {
//			ff.affiche();
//		}

		Dessin d = new Dessin();
		Collection<Figure> fs = FigureUtil.genere(100);
		for (Figure f : fs) {
			d.add(f);
		}
		// System.out.println("Figure en : " + FigureUtil.getFigureEn(new Point(10,
		// 100), d));

		try {
			String resultat = FigureUtil.imprim(d);
			FigureUtil.sauvegarde(resultat);
		} catch (IOException ie) {
			System.out.println("Error 404 not found");
		} catch (ImpressionHorsLimiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Figure fto : FigureUtil.trieProcheOrigine(d)) {
			System.out.println("distance : " + fto.distanceOrigine());
		}

		for (Figure fto : FigureUtil.trieSurface(d)) {
			System.out.println("surface : " + ((Surfacable) fto).surface());
		}

		try {
			FigureUtil.imprim();
		} catch (ImpressionHorsLimiteException e) {
			// System.out.println("Impression hors limite : " + e.getMessage());
			e.printStackTrace();
		}

		File fichier = new File("C:\\Users\\formation\\eclipse-workspace\\Tp10\\src\\tp\\test.txt");
		try {
			FigureUtil.charger(fichier);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDateTime date = currentTime.withDayOfMonth(25).withYear(2019).withMonth(12);
		System.out.println("Date modifiée : " + date);     
		
		// afficher une date et la convertir au format souhaité
		LocalDateTime ldtParsed = LocalDateTime.of(2018, 12, 25, 17, 35);
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		System.out.println("Date de Noel= " + formatter.format(ldtParsed));
	}

}
