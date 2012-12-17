import java.awt.Color;


public class Vec3 {
    protected double x;
    protected double y;
    protected double z;
    
    public Vec3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
   
    public Vec3(Vec3 v) {
    	this.x = v.x;
    	this.y = v.y;
    	this.z = v.z;
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
    //end accessor methods
    
    //mutator methods
    public void changeX(double d){
    	this.x = d;
    }
    
    //mutator methods
    public void changeY(double d){
    	this.y = d;
    }
    
    //mutator methods
    public void changeZ(double d){
    	this.z = d;
    }
    
    //calculate an image plane to world frame transformation
    public Vec3 ImagePlaneToWorld(Vec3 xc, Vec3 yc, Vec3 zc){
    	return  xc.multScalar(x).addVec(yc.multScalar(y).addVec(zc.multScalar(z)));
    }
    
    //multiply a vector by a scalar
    public Vec3 multScalar(double s){
    	return new Vec3(x * s, y * s, z * s);
    }
    
    //add this vector and the given
    public Vec3 addVec(Vec3 v){
    	return new Vec3(x + v.getX(), y + v.getY(), z + v.getZ());
    }
    
    //subtract this vector and the given
    public Vec3 subVec(Vec3 v){
    	return new Vec3(x - v.getX(), y - v.getY(), z - v.getZ());
    }
    
    //calculate the cross product
    public Vec3 crossProduct(Vec3 v){
    	double cx = y * v.getZ() - z * v.getY();
    	double cy = z * v.getX() - x * v.getZ();
    	double cz = x * v.getY() - y * v.getX();
    	
    	return new Vec3(cx, cy, cz);
    }
    
    //compute the dot product
    public double dotProduct(Vec3 v) {
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
    
    //get the length of this vector
    public double length(){
    	return Math.sqrt(x * x + y * y + z * z);
    }
    
    //negate the vector
	public Vec3 negate() {
		return new Vec3(-x, -y, -z);
	}
    
    //normalize the vector
	public Vec3 normalize() {
		double mag = length();
		return new Vec3(x / mag, y / mag, z / mag);
	}
	
	//multiply element by element
	public Vec3 multVec(Vec3 v) {
		return new Vec3(x * v.getX(), y * v.getY(), z * v.getZ());
	}
	
	//clamp values of vector to be between 0 and 1
	public void clamp() {
		if(x > 1)
			x = 1.0;
		if(x < 0)
			x = 0.0;
		if(y > 1)
			y = 1.0;
		if(y < 0)
			y = 0.0;
		if(z > 1)
			z = 1.0;
		if(z < 0)
			z = 0.0;
	}
	
	//convert this vec3 to a color
	public Color toColor(){
		return new Color((float) x, (float) y, (float) z);
	}
    
    //override toString method from object class
    public String toString(){
    	return x + " " + y + " " + z;
    }

    
}
