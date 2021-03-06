package Minor.P3.Client;
import Minor.P3.DS.Compare2D;
import Minor.P3.DS.Direction;

public class Point implements Compare2D<Point> {

	private long xcoord;
	private long ycoord;
	
	public Point() {
		xcoord = 0;
		ycoord = 0;
	}

	public Point(long x, long y) {
		xcoord = x;
		ycoord = y;
	}

	public long getX() {
		return xcoord;
	}

	public long getY() {
		return ycoord;
	}
	
	public Direction directionFrom(long X, long Y) { 
		if ( xcoord <= X && ycoord > Y){
			return Direction.NW;
		}
		else if(xcoord > X && ycoord >= Y){
			return Direction.NE;
		}
		else if(xcoord < X && ycoord <= Y){
			return Direction.SW;
		}
		else if(xcoord >= X && ycoord < Y){
			return Direction.SE;
		}
        return Direction.NOQUADRANT;
   }
	
	public Direction inQuadrant(double xLo, double xHi, 
                               double yLo, double yHi) { 
		//if xcoord or ycoord is outside of the rectangle
		if ( !inBox(xLo, xHi, yLo, yHi)){
			return Direction.NOQUADRANT;
		}
		long xCenter = (long) ((xLo + xHi)/2);
		long yCenter = (long) ((yLo + yHi)/2);
		
		Direction dir = directionFrom(xCenter, yCenter);
		
		if(dir == Direction.NOQUADRANT){
			return Direction.NE;
		}
		return dir;
   }

	public boolean   inBox(double xLo, double xHi, 
                          double yLo, double yHi) { 
		if ( xcoord < xLo || xcoord > xHi || ycoord < yLo || ycoord > yHi){
			return false;
		}
		return true;
   }

	public String toString() {
      return "X:" + xcoord + " Y:" +  ycoord;
   }
	
	public boolean equals(Object o) {
		// check if the Object o is an Point class
		if ( !o.getClass().equals(this.getClass())){
    		return false;
    	}
		// it is equal if the directionFrom returns an NOQUADRANT
		if (this.getClass().cast(o).directionFrom(xcoord, ycoord) == Direction.NOQUADRANT){
			return true;
		}
		return false;
   }
}
