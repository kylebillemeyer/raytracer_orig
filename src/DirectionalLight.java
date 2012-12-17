
public class DirectionalLight extends Lights{

	public DirectionalLight(int index, double I){
		super(index, I);
	}
	
	//get the direction vector
	public Vec3 getDir(){
		return new Vec3(Data.getVertex(index).getDx(),
				Data.getVertex(index).getDy(),
				Data.getVertex(index).getDz());
	}

	//get the color value at the given point
	public Vec3 getColor(Vec3 p, Vec3 n, Vec3 d, Vec3 difCol, Vec3 spCol, int exp) {
		Vec3 color = new Vec3(0, 0, 0);
		Vec3 l = getDir().negate();
		Ray newRay = new Ray(p, l);
		HitRecord srec = new HitRecord();
		
		if(Data.getSettings().isA()){
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
