
public class PointLight extends Lights{

	public PointLight(int index, double I){
		super(index, I);
		//System.out.println(this.getPos());
	}

	public Vec3 getPos(){
		return new Vec3(Data.getVertex(index).getX(),
				Data.getVertex(index).getY(),
				Data.getVertex(index).getZ());
	}

	////get the color value at the given point
	public Vec3 getColor(Vec3 p, Vec3 n, Vec3 d, Vec3 difCol, Vec3 spCol, int exp) {
		Vec3 color = new Vec3(0, 0, 0);
		Vec3 l = getPos().subVec(p);
		Ray newRay = new Ray(p, l);
		HitRecord srec = new HitRecord();
		
		if(Data.getSettings().isA()){
			//Double.POSITIVE_INFINITY should technically be 1 but something is wrong
			if(Data.hasIntersection(newRay, 0.001, Double.POSITIVE_INFINITY, srec)){
				//System.out.println("In shadow");
				return color;
			}
			else{
				l = l.normalize();
				
				if(Data.getSettings().isD()){
					double nDotl = n.dotProduct(l);
					color = color.addVec(difCol.multScalar(i * Math.max(0, nDotl)));
				}
				
				if(Data.getSettings().isS()){
					Vec3 h = l.subVec(d).normalize();
					double nDotH = n.dotProduct(h);
					color = color.addVec(spCol.multScalar(i * Math.pow(Math.max(0, nDotH), exp)));
				}
			}
		}
		else{
			l = l.normalize();
			
			if(Data.getSettings().isD()){
				double nDotl = n.dotProduct(l);
				color = color.addVec(difCol.multScalar(i * Math.max(0, nDotl)));
			}
			
			if(Data.getSettings().isS()){
				Vec3 h = l.subVec(d).normalize();
				double nDotH = n.dotProduct(h);
				color = color.addVec(spCol.multScalar(i * Math.pow(Math.max(0, nDotH), exp)));
			}
		}

		return color;
	}
}
