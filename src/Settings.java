
public class Settings {
	String d, s, a;
	double i;
	int m;
	
	public Settings(String d, String s, String a, double m, double i){
		if(d.equals("d"))
			this.d = d;
		else
			this.d = "x";
		
		if(s.equals("s"))
			this.s = s;
		else
			this.s = "x";
		
		if(a.equals("a"))
			this.a = a;
		else
			this.a = "x";
		
		this.m = (int) m;
		
		if(i < 0)
			this.i = 0.0;
		else if(i > 1)
			this.i = 1.0;
		else
			this.i = i;
	}
	
	//accessor methods
	public String getD(){
		return this.d;
	}
	
	public String getS(){
		return this.s;
	}
	
	public String getA(){
		return this.a;
	}
	
	public int getM(){
		return this.m;
	}
	
	public double getI(){
		return this.i;
	}
	//end accessor
	
	//checks if diffuse shading is on
	public boolean isD(){
		return d.equals("d");
	}
	
	//checks if specular highlighting is on
	public boolean isS(){
		return s.equals("s");
	}
	
	//checks if shadows is on
	public boolean isA(){
		return a.equals("a");
	}
	
	//overide toString method from object class
	public String toString(){
		return d + " " + s + " " + a + " " + m + " " + i; 
	}
}
