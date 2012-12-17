
public class HitRecord {
	Shapes shape;
	double time;
	
	public HitRecord(Shapes shape, double time){
		this.shape = shape;
		this.time = time;
	}
	
	public HitRecord() {
		
	}
	
	//mutator method
	public void setShapeTime(Shapes s, double t) {
		this.shape = s;
		this.time = t;
	}
	
	//accessor methods
	public double getTime(){
		return time;
	}	
	
	public Shapes getShape(){
		return shape;
	}	
	//end accessor methods
}
