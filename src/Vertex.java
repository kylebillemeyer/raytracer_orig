
public class Vertex {
	double x, y, z, dx, dy, dz;
	
	public Vertex(double x, double y, double z, double dx, double dy, double dz){
		this.x = x;
		this.y = y;
		this.z = z;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}
	
	//accessor methods
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getZ(){
		return this.z;
	}
	
	public double getDx(){
		return this.dx;
	}
	
	public double getDy(){
		return this.dy;
	}
	
	public double getDz(){
		return this.dz;
	}
	//end accessor methods
	
	//overide toString 
	public String toString(){
		return x + " " + y + " " + z + " " +
				dx + " " + dy + " " + dz;
	}
}
