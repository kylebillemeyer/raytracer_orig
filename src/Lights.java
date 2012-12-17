import java.awt.Color;


public abstract class Lights {
	protected int index;
	protected double i;
	
	public Lights(int index, double i){
		this.index = index;
		this.i = i;
	}
	
	//accessor methods
	public int getIndex(){
		return this.index;
	}
	
	public double getI(){
		return this.i;
	}
	//end accessor methods

	//get the color value at the given point
	public abstract Vec3 getColor(Vec3 p, Vec3 n, Vec3 d, Vec3 difCol, Vec3 spCol, int exp);
}
