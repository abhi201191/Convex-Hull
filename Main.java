/*-------------------------------------------------------------
Name               : Abhishek Singh
Roll Number        : CS1423
Date of Submission : 29-sep-2014
Deadline date	   : 29-sep-2014 
Program description: Implement convex hull
Acknowledgements   : Took help from Introduction to Algorithm by T.H. Cormen and for sorting using collection from Arpan.
--------------------------------------------------------------*/

package cs1423.main;

import java.util.ArrayList;
import cs1423.geometry.*;

public class Main {

	public static void main(String[] args) {
		/* Creates the objects of points in two dimensional plane */ 
		Point p1 = new Point(0,3.0);
		Point p2 = new Point(2.0,0.0);
		Point p3 = new Point(3.0,3.0);
		Point p4 = new Point(4.0,2.0);
		Point p5 = new Point(6.0,6.0);
		Point p6 = new Point(0,0);
		Point p7 = new Point(1,2);
		Point p8 = new Point(1,5);
		Point p9 = new Point(2,5);
		
		/* Stores the point in ArrayList arrayOfPoints */
		ArrayList<Point> arrayOfPoints = new ArrayList<Point>();
		arrayOfPoints.add(p1);
		arrayOfPoints.add(p2);
		arrayOfPoints.add(p3);
		arrayOfPoints.add(p4);
		arrayOfPoints.add(p5);
		arrayOfPoints.add(p6);
		arrayOfPoints.add(p7);
		arrayOfPoints.add(p8);
		arrayOfPoints.add(p9);
		
		/* Function call to compute the convexHull on set of points */
		ConvexHull conHull = ConvexHull.computeConvexHull(arrayOfPoints);
		
		Point p = new Point(4,6);
		/* Function call to check whether the point is inside, outside or on the convexHull */ 
		int position = conHull.findPositionOfPoint(p);
		if(position == 1)
			System.out.println("\nPoint (" + p.getX() + "," + p.getY() + ") is inside the convex hull");
		else if(position == 0)
			System.out.println("\nPoint (" + p.getX() + "," + p.getY() + ") is on the convex hull");
		else
			System.out.println("\nPoint (" + p.getX() + "," + p.getY() + ") is outside the convex hull");
	}
}
