
public class Plane extends Shapes{
	int index;
	int spExp;
	Vec3 amCol, difCol, spCol;
	
	public Plane(int pt, Vec3 amCol, Vec3 difCol, Vec3 spCol, int spExp){
		super(amCol, difCol, spCol, spExp);
		this.index = pt;
		//System.out.println("plane: " + spExp);
	}

	//return the nearest intersection
	public double intersectsAt(Ray ray, double t0, double t1){
		
		double a = getNormal(null).dotProduct(ray.getDir());
		if(Math.abs(a) > t0){
			double  x = getPos().dotProduct(getNormal(null));
			double y = ray.getStart().dotProduct(getNormal(null));
			double t = (x - y) / a;
			
			return t;
		}
		
		return Double.POSITIVE_INFINITY;
	}

	//get the normal vector at this point
	public Vec3 getNormal(Vec3 v) {
		return new Vec3(Data.getVertex(index).getDx(),
					Data.getVertex(index).getDy(),
					Data.getVertex(index).getDz()).normalize();
		
	}
	
	//return the position of the shape
	public Vec3 getPos(){
		return new Vec3(Data.getVertex(index).getX(),
				Data.getVertex(index).getY(),
				Data.getVertex(index).getZ());
	}
}
