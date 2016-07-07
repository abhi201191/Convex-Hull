/*-------------------------------------------------------------
Name               : Abhishek Singh
Roll Number        : CS1423
Date of Submission : 29-sep-2014
Deadline date	   : 29-sep-2014 
Program description: Implement convex hull
Acknowledgements   : Took help from Introduction to Algorithm by T.H. Cormen and for sorting using collection from Arpan.
--------------------------------------------------------------*/

package cs1423.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import cs1423.dsutils.Stack;

public class ConvexHull {

	/* ArrayList to store the points on the boundary of convex hull */
	ArrayList<Point> boundaryPoints;
	/* Point to store bottomLeftPoint */
	static Point bottomLeftPoint;

	/* Function to compute the ConvexHull from set of points */
	public static ConvexHull computeConvexHull(Collection<Point> points) {
		/* ArrayList to store the Points in two dimensional plane */
		ArrayList<Point> arrayOfPoints = new ArrayList<Point>(points);
		ConvexHull convexHull = new ConvexHull();
		System.out.println("Points in two-Dimensional plane: ");

		/* Function call to prints the points in plane */
		convexHull.printArray(arrayOfPoints);

		/* Instantiate the boundaryPoints ArrayList */
		convexHull.boundaryPoints = new ArrayList<Point>();

		/* Object of stack class */
		Stack stackOfPoints = new Stack();

		/* Function call to find the bottomLeftPoint in set of points */
		convexHull.findMin(arrayOfPoints);

		/* Swap the BottomLeftPoint with point at 1 index of ArrayList */
		arrayOfPoints = convexHull.swapPoints(arrayOfPoints);

		/* Stores the size of ArrayList arrayOfPoints */
		int sizeOfArray = arrayOfPoints.size();
		PointComparator pointComparator = new PointComparator();

		/* Sort the points on the basis of their polar angles */
		Collections.sort(arrayOfPoints.subList(1, sizeOfArray), pointComparator);

		/* Push first three points in stack */
		stackOfPoints.push(bottomLeftPoint);
		stackOfPoints.push(arrayOfPoints.get(1));
		stackOfPoints.push(arrayOfPoints.get(2));

		/* Iterate over the remaining elements in arrayOfPoints ArrayList */
		for (Point point : arrayOfPoints.subList(3, sizeOfArray)) {

			/*
			 * Removing the Points from stack untill the three Points making
			 * clockwise rotation
			 */
			while (ConvexHull.orientation(stackOfPoints.nextElement(),
					stackOfPoints.topElement(), point) != -1)
				stackOfPoints.pop();

			/* Push the point in stack */
			stackOfPoints.push(point);
		}

		/* Transfer the elements from stack to boundayPoints ArrayList */
		Point p = stackOfPoints.pop();
		while (p != null) {
			convexHull.boundaryPoints.add(p);
			p = stackOfPoints.pop();
		}

		System.out.println();
		System.out.println("Points on boundary of Convex Hull:");
		convexHull.printArray(convexHull.boundaryPoints);
		System.out.println();
		System.out.println("Area of Convex Hull: " + convexHull.area());

		return convexHull;
	}

	/* Function print the elements of the ArrayList */
	private void printArray(ArrayList<Point> arrayOfPoints) {
		for (Point point : arrayOfPoints) {
			System.out.println("(" + point.getX() + " , " + point.getY() + ")");
		}
	}

	/*
	 * Function to find whether the point is inside, outside or on the convex
	 * hull. It is obtained by comparing the area of actual Convex hull with the
	 * area of the polygon formed by this point and boundary points of
	 * ConvexHull.
	 */
	public int findPositionOfPoint(Point point) {
		int index = 1;
		double area = 0;
		/* Iterate the ArrayList boundaryPoints to last element */
		for (Point p1 : boundaryPoints.subList(0, boundaryPoints.size())) {
			/*
			 * Get the next element from ArrayList in point p2 and increment the
			 * index
			 */
			Point p2 = boundaryPoints.get(index);
			index++;
			/*
			 * If index is same as size of boundaryPoint ArrayList then set it
			 * to 0 to get the first element from ArrayList
			 */
			if (index == boundaryPoints.size())
				index = 0;
			/*
			 * Compute the area of triangle formed by three points with respect
			 * to bottomLeftPoint
			 */
			double d = (point.getX() * (p1.getY() - p2.getY()) + p1.getX()
					* (p2.getY() - point.getY()) + p2.getX()
					* (point.getY() - p1.getY())) / 2;

			/*
			 * If any of the triangle area is 0 then we can say that point is on
			 * the Convex Hull
			 */
			if (d == 0)
				return 0;
			/* Add all the areas of triangles formed */
			area += Math.abs((d));
		}

		/*
		 * If area obtained is same as that of area of ConvexHull, then point is
		 * inside the ConvexHull and return 1
		 */
		if (area == area())
			return 1;
		
		/* Otherwise point is outside the ConvexHull */
		else
			return -1;
	}

	/* Function to calculate the area of convex Hull */
	public double area() {
		/* Set index variable to 1 to get the next element from ArrayList */
		int index = 1;
		double areaOfConvexHull = 0;
		/* Iterate the ArrayList boundaryPoints to second last element */
		for (Point p1 : boundaryPoints.subList(0, boundaryPoints.size() - 2)) {
			/*
			 * Get the next element from ArrayList in point p2 and increment the
			 * index
			 */
			Point p2 = boundaryPoints.get(index);
			index++;
			/*
			 * Compute the area of triangle formed by three points with respect
			 * to bottomLeftPoint
			 */
			double d = (bottomLeftPoint.getX() * (p1.getY() - p2.getY())
					+ p1.getX() * (p2.getY() - bottomLeftPoint.getY()) + p2
					.getX() * (bottomLeftPoint.getY() - p1.getY())) / 2;
			/* Add all the areas of triangle formed in convex hull */
			areaOfConvexHull += Math.abs((d));
		}
		return areaOfConvexHull;
	}

	/* Find the bottom left point in convex hull */
	private void findMin(ArrayList<Point> arrayOfPoints) {
		bottomLeftPoint = arrayOfPoints.get(0);
		/*
		 * Iterate over the arrayOfPoints ArrayList and find the bottomLeft
		 * point according to minimum Y coordinate and if two points have same Y
		 * coordinate then select Point with minimum X coordinate value
		 */
		for (Point point : arrayOfPoints) {
			if (point.getY() < bottomLeftPoint.getY()
					|| (point.getY() == bottomLeftPoint.getY() && point.getX() < bottomLeftPoint
							.getX()))
				bottomLeftPoint = point;
		}
	}

	/* Swap two points in ArrayList */
	private ArrayList<Point> swapPoints(ArrayList<Point> arrayOfPoints) {
		int index = arrayOfPoints.indexOf(bottomLeftPoint);
		arrayOfPoints.set(index, arrayOfPoints.get(0));
		arrayOfPoints.set(0, bottomLeftPoint);
		return arrayOfPoints;
	}

	/*
	 * Function to find whether three points are colinear or clockwise or
	 * anticlockwise
	 */
	static int orientation(Point p0, Point p1, Point p2) {
		/* Calculate the difference between slopes of two lines */
		double slope = ((p2.getY() - p1.getY()) * (p1.getX() - p0.getX()) - (p1
				.getY() - p0.getY()) * (p2.getX() - p1.getX()));
		/* If difference of slope is 0 then three points are colinear */
		if (slope == 0)
			return 0;
		else
			/*
			 * -1: anticlockwise 1: clockwise
			 */
			return (slope > 0) ? -1 : 1;
	}
}

/*
 * PointComparator class implementing Comparator interface to Compare two Points
 * on basis of their polar angle
 */
class PointComparator implements Comparator<Point> {
	public int compare(Point p1, Point p2) {
		/*
		 * Computes the orientation between three points i.e colinear,
		 * anticlockwise, clockwise
		 */
		int value = ConvexHull.orientation(ConvexHull.bottomLeftPoint, p1, p2);
		/*
		 * If points are Colinear then compute the distance of point p1 and p2
		 * from bottomLeftPoint and return the point with less distance
		 */
		if (value == 0)
			return (p1.distanceTo(ConvexHull.bottomLeftPoint) >= p2
					.distanceTo(ConvexHull.bottomLeftPoint)) ? 1 : -1;
		else
			return (value == 1) ? 1 : -1;
	}
}
