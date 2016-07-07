/*-------------------------------------------------------------
Name               : Abhishek Singh
Roll Number        : CS1423
Date of Submission : 29-sep-2014
Deadline date	   : 29-sep-2014 
Program description: Implement convex hull
Acknowledgements   : Took help from Introduction to Algorithm by T.H. Cormen and for sorting using collection from Arpan.
--------------------------------------------------------------*/

package cs1423.dsutils;

import cs1423.geometry.Point;
import java.util.ArrayList;

public class Stack {

	/* ArrayList used as a stack */
	ArrayList<Point> stack = new ArrayList<Point>();
	private int top = -1;

	/* Function to push the point in stack */
	public void push(Point p) {
		top++;
		stack.add(top, p);
	}

	/* Function to Pop the point from stack */
	public Point pop() {
		/* If stack is not empty */
		if (!isEmpty()) {
			/* Remove the point from stack and store in temp point variable and decrement the top pointer*/
			Point temp = stack.remove(top);
			top--;
			return temp;
		} else {
			return null;
		}
	}

	/* Function to check whether stack is empty or not */ 
	public boolean isEmpty() {
		if (top == -1)
			return true;
		else
			return false;
	}

	/* Function to print the element of stack */
	public void printStack() {
		for (Point point : stack) {
			System.out.println("(" + point.getX() + " , " + point.getY() + ")");
		}
	}

	/* Function to return the top element of the stack */
	public Point topElement() {
		if (top != -1) {
			Point point = stack.get(top);
			return point;
		} else
			return null;
	}

	/* Function to return the next to top element from the stack */
	public Point nextElement() {
		/* If there are more than one element in stack then return the next to top element */
		if (top >= 1) {
			int tos = top;
			Point nextToTopElement = stack.get(--tos);
			return nextToTopElement;
		} else
			return null;
	}
}
