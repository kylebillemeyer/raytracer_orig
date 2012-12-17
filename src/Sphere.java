
public class Sphere extends Shapes{
	protected int index;
	
	public Sphere(int center, Vec3 amCol, Vec3 difCol, Vec3 spCol, int spExp){
		super(amCol, difCol, spCol, spExp);
		this.index = center;
	}

	public Vec3 getPos(){
		return new Vec3(Data.getVertex(index).getX(),
				Data.getVertex(index).getY(),
				Data.getVertex(index).getZ());
	}
	
	//get the normal vector at this point
	public Vec3 getNormal(Vec3 v){
		return v.subVec(getPos()).normalize();
	}
	
	//return the nearest intersection
	public double intersectsAt(Ray ray, double t0, double t1){
		
		Vertex v = Data.getVertex(index);
		Vec3 c = new Vec3(v.getX(), v.getY(), v.getZ());
		double r = new Vec3(v.getDx(), v.getDy(), v.getDz()).length();
	
		double b = 2.0*(ray.getDir().dotProduct(ray.getStart().subVec(c)));
		double a = ray.getDir().dotProduct(ray.getDir());

		double C = ray.getStart().subVec(c).dotProduct(ray.getStart().subVec(c)) - r * r;

		double disc = b*b - 4*a*C;

		if(disc < 0){
			return Double.POSITIVE_INFINITY;
		}
		
    disc = Math.sqrt(disc);

    double tx = (-b + disc) / (2.0*a);
    double ty = (-b - disc) / (2.0*a);
	
    return Math.min(tx, ty);
		
	}
	
	//override toString
	public String toString(){
		return "" + index;
	}
}
