import java.util.ArrayList;


public class Data {
	protected static ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	protected static ArrayList<Shapes> shapes = new ArrayList<Shapes>();
	protected static ArrayList<Lights> lights = new ArrayList<Lights>();
	protected static Settings settings = new Settings("d", "s", "a", 1.0, 1.0);
	protected static int camera = 0;

	//camera basis vectors
	protected static Vec3 xc; 
	protected static Vec3 yc;
	protected static Vec3 zc;

	//hit detection variables
	protected static boolean hit = false;
	protected static Shapes hitshape = null;
	protected static double t = Double.POSITIVE_INFINITY;


	public Data(){}

	//accessor methods
	public static Vertex getVertex(int i){
		return vertices.get(i);
	}

	public static ArrayList<Shapes> getShapes(){
		return shapes;
	}

	public static ArrayList<Lights> getLights(){
		return lights;
	}

	public static Settings getSettings(){
		return settings;
	}

	public static int getCamera(){
		return camera;
	}

	public static Vec3 getXc(){
		return xc;
	}

	public static Vec3 getYc(){
		return yc;
	}

	public static Vec3 getZc(){
		return zc;
	}

	public static Shapes getHitShape(){
		return hitshape;
	}
	//end accessor methods

	//add a vertex to the list of vertices
	public static void addVertex(Vertex v){
		vertices.add(v);
	}

	//add a shape to the list of shapes
	public static void addShape(Shapes s){
		shapes.add(s);
	}

	//add a light to the list of lights
	public static void addLight(Lights l){
		lights.add(l);
	}

	//replace current settings with new settings
	public static void changeSettings(Settings s){
		settings = s;
	}

	//replace current camera with new camera
	public static void changeCamera(int i){
		camera = i;
	}

	//get the camera position
	public static Vec3 getCameraVec() {
		return new Vec3(vertices.get(camera).getX(),
				vertices.get(camera).getY(),
				vertices.get(camera).getZ());
	}

	//get the camera frame vectors
	public static void setCameraBasis(){
		Vec3 d = new Vec3(getVertex(camera).getDx(),
				getVertex(camera).getDy(),
				getVertex(camera).getDz());  
		zc = d.normalize().negate();
		xc = d.crossProduct(new Vec3(0,1,0)).normalize();
		yc = zc.crossProduct(xc);

		//System.out.println(zc + "\n" + xc + "\n" + yc);
	}

	//check if given ray intersects with any geometry
	public static boolean hasIntersection(Ray ray, double t0, double t1, HitRecord rec){
		hit = false;
		for(Shapes s : shapes){
			t = s.intersectsAt(ray, t0, t1);
			if(t >= t0 && t < t1){
				hit = true;
				t1 = t;
				rec.setShapeTime(s, t);
			}	
		}
		return hit;
	}

	//calculate the lambertian shading values
	public static Vec3 getShading2(Ray ray, double t0, double t1, int mirRef) {
		HitRecord rec = new HitRecord();
		Vec3 d = ray.getDir();
		Vec3 e = ray.getStart();
		if(hasIntersection(ray, t0, t1, rec)){
			Vec3 p = ray.intersectPt(rec.getTime());//e.addVec(d.multScalar(rec.getTime()));
			Vec3 cs = rec.getShape().getAmCol().multScalar(Data.settings.getI());

			Vec3 n = rec.getShape().getNormal(p);
			for(Lights l : lights){
				cs = cs.addVec(l.getColor(p, n, d, rec.getShape().getDifCol(), rec.getShape().getSpCol(), rec.getShape().getSpExp()));
			}

			if(mirRef > 0){
				Vec3 r = d.subVec(n.multScalar(2* d.dotProduct(n)));
				Ray nextRay = new Ray(p, r);
				return cs.addVec(rec.getShape().getSpCol().multVec(getShading2(nextRay, .0001, Double.POSITIVE_INFINITY, mirRef-1)));

			}
			else{
				return cs;
			}


			//return cs;
		}		
		return new Vec3(0, 0, 0);
	}
	
	//Override toString method for debugging purposes
	public String toString(){
		String acc = "";
		for(Vertex v : vertices){
			acc = acc + v.toString() + "\n";
		}
		for(Shapes s : shapes){
			acc = acc + s.toString() + "\n";
		}
		for(Lights l : lights){
			acc = acc + l.toString() + "\n";
		}
		acc = acc + settings.toString() + "\n";
		acc = acc + camera + "\n";
		return acc;
	}
}
