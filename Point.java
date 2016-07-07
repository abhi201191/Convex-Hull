/*-------------------------------------------------------------
Name               : Abhishek Singh
Roll Number        : CS1423
Date of Submission : 29-sep-2014
Deadline date	   : 29-sep-2014 
Program description: Implement convex hull
Acknowledgements   : Took help from Introduction to Algorithm by T.H. Cormen and for sorting using collection from Arpan.
--------------------------------------------------------------*/

package cs1423.geometry;

import java.lang.Math;

public class Point {
	private double x;
	private double y;
	
	public Point() {
	}
	
	/* Constructor which initialise the cordinates of the point */ 
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/* Returns the X coordinate of point */
	public double getX() {
		return x;
	}
	
	/* Returns the Y coordinate of point */
	public double getY() {
		return y;
	}
	
	/* Function to find the distance between two points */
	public double distanceTo(Point point) {
		double distance = Math.sqrt(Math.pow((this.x - point.x),2) + Math.pow((this.y - point.y),2));
		return distance;
	}
}