
public class Triangle extends Shapes{
	int vert0, vert1, vert2;
	Vec3 amCol, difCol, spCol;
	double alpha, beta, gamma;
	int spExp;
	
	public Triangle(int vert0, int vert1, int vert2, Vec3 amCol, Vec3 difCol, Vec3 spCol, int spExp){
		super(amCol, difCol, spCol, spExp);
		this.vert0 = vert0;
		this.vert1 = vert1;
		this.vert2 = vert2;
	}
	
	//get the vector coordinates at the given vertex
	protected Vec3 getVertPos(int vert){
		return new Vec3(Data.getVertex(vert).getX(),
				Data.getVertex(vert).getY(),
				Data.getVertex(vert).getZ());
	}
	
	//get the normal vector at the given vertex
	public Vec3 getVertNorm(int vert){
		return new Vec3(Data.getVertex(vert).getDx(),
				Data.getVertex(vert).getDy(),
				Data.getVertex(vert).getDz()).normalize();
	}
	
	//accessor methods
	public Vec3 getX(){
		return getVertPos(vert0);
	}
	
	public Vec3 getY(){
		return getVertPos(vert1);
	}
	
	public Vec3 getZ(){
		return getVertPos(vert2);
	}
	
	public Vec3 getXNorm(){
		return getVertNorm(vert0);
	}
	
	public Vec3 getYNorm(){
		return getVertNorm(vert1);
	}
	
	public Vec3 getZNorm(){
		return getVertNorm(vert2);
	}
	//end accessor methods
	
	//return the nearest intersection
	public double intersectsAt(Ray ray, double t0, double t1){
		double a = getX().getX() - getY().getX();
		double b = getX().getY() - getY().getY();
		double c = getX().getZ() - getY().getZ();		
		
		double d = getX().getX() - getZ().getX();
		double e = getX().getY() - getZ().getY();
		double f = getX().getZ() - getZ().getZ();
		
		double g = ray.getDir().getX();
		double h = ray.getDir().getY();
		double i = ray.getDir().getZ();
		
		double j = getX().getX() - ray.getStart().getX();
		double k = getX().getY() - ray.getStart().getY();
		double l = getX().getZ() - ray.getStart().getZ();
		
		double ei_minus_hf = e * i - h * f;
		double gf_minus_di = g * f - d * i;
		double dh_minus_eg = d * h - e * g;
		double ak_minus_jb = a * k - j * b;
		double jc_minus_al = j * c - a * l;
		double bl_minus_kc = b * l - k * c;
		
		double m = a * ei_minus_hf + b * gf_minus_di + c * dh_minus_eg;
		
		double t = f * ak_minus_jb + e * jc_minus_al + d * bl_minus_kc;
		t = t / -m;
		
		if(t < t0 || t > t1)
			return Double.POSITIVE_INFINITY;
		
		gamma = i * ak_minus_jb + h * jc_minus_al + g * bl_minus_kc;
		gamma = gamma / m;
		
		if( gamma < 0 || gamma > 1)
			return Double.POSITIVE_INFINITY;
		
		beta = j * ei_minus_hf + k * gf_minus_di + l * dh_minus_eg;
		beta = beta / m;
		
		if (beta < 0 || beta > 1 - gamma)
			return Double.POSITIVE_INFINITY;
		
		alpha = 1 - gamma - beta;
		
		return t;	
	}

	//override getNormal
	public Vec3 getNormal(Vec3 v) {
		return getXNorm().multScalar(alpha).addVec(getYNorm().multScalar(beta)).addVec(getZNorm().multScalar(gamma));
	}

	//override getPos
	//not used for triangles
	public Vec3 getPos() {
		// TODO Auto-generated method stub
		return null;
	}
}
