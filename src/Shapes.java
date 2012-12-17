import java.awt.Color;
import java.awt.Paint;


public abstract class Shapes {
	protected int spExp;
	protected Vec3 amCol, difCol, spCol;
	
	public Shapes(Vec3 amCol, Vec3 difCol, Vec3 spCol, int spExp){
//vona
//		this.amCol = amCol;
//		this.difCol = difCol;
//		this.spCol = spCol;
		this.amCol = new Vec3(amCol);
		this.difCol = new Vec3(difCol);
		this.spCol = new Vec3(spCol);
		this.spExp = spExp;
	}
	
	//return the nearest intersection
	public abstract double intersectsAt(Ray ray, double t0, double t1);
	
	//return the position of the shape
	public abstract Vec3 getPos();
	
	//accessor methods
	public Vec3 getAmCol(){
		return amCol;
	}
	
	public Vec3 getDifCol(){
		return difCol;
	}
	
	public Vec3 getSpCol(){
		return spCol;
	}
	
	public int getSpExp(){
		return spExp;
	}

	//return the ambient color vector as a color
	public Paint amColconvert() {
		return new Color((float) amCol.getX(), (float) amCol.getY(), (float) amCol.getZ());
	}

	//get the normal vector at this point
	public abstract Vec3 getNormal(Vec3 v);
}
