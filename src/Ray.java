
public class Ray {
	protected Vec3 start;
	protected Vec3 dir;
	
	public Ray(Vec3 start, Vec3 dir){
		this.start = start;
		this.dir = dir.normalize();
	}
	
	//accessor methods
	public Vec3 getStart(){
		return start;
	}
	
	public Vec3 getDir(){
		return dir;
	}
	//end accessor methods
	
	//override toString
	public String toString(){
		return start.toString() + " " + dir.toString();
	}

	//get point along the ray at time t
	public Vec3 intersectPt(double t) {
		return start.addVec(dir.multScalar(t));
	}
}
