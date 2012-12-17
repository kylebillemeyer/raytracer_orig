import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class InputReader {
    protected static ArrayList<Vertex> indices = new ArrayList<Vertex>();
    protected static ArrayList<Shapes> shapes = new ArrayList<Shapes>();
    protected static ArrayList<Lights> lights = new ArrayList<Lights>();
    protected static Vec3 amCol = new Vec3(.2, .2, .2);
    protected static Vec3 difCol = new Vec3(1.0, 1.0, 1.0);
    protected static Vec3 spCol = new Vec3(1.0, 1.0, 1.0);
    protected static int spExp = 64;
    protected static int camera = 0;
    
    //read given file and extract the data
    public static void readInput(BufferedReader br){
        try{
            String line;
            while ((line = br.readLine()) != null)   {
                //send each line to a tokenizer to extract
                //the triangle data
                if(!line.startsWith("##")){
                    if (line.startsWith("vv")){
                    	extractVertex(line);
                    }
                    else if (line.startsWith("am")){
                    	extractAmMat(line);
                    }
                    else if (line.startsWith("dm")){
                    	extractDifMat(line);
                    }
                    else if(line.startsWith("sm")){
                    	extractSpMat(line);
  
                    }
                    else if (line.startsWith("ts")){
                    	extractTri(line);
                    }
                    else if (line.startsWith("ss")){
                    	extractSphere(line);
                    }
                    
                    else if (line.startsWith("ps")){
                    	extractPlane(line);
                    }
                    else if (line.startsWith("se")){
                    	extractSettings(line);
                    }
                    else if (line.startsWith("pl")){
                    	extractPLight(line);
                    }
                    else if (line.startsWith("dl")){
                    	extractDLight(line);
                    }
                    else if (line.startsWith("cc")){
                    	extractCamera(line);
                    }
                }      
            }           
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    //extract vertex data from the given line
	protected static void extractVertex(String s){
    	try{
			StringTokenizer st = new StringTokenizer(s, " ");
			st.nextToken(); //skip the label
	        double[] temp = new double[6];
	        int i = 0;
	        while(st.hasMoreTokens()){
	            temp[i] = Double.parseDouble(st.nextToken());
	            i++;
	        }
	        
	        Vertex tempVert = new Vertex(temp[0], temp[1], temp[2], 
	        		temp[3], temp[4], temp[5]);
	        Data.addVertex(tempVert);
	        
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for vertex");
    	}
    }
	
	//extract ambient material data from the given line
    protected static void extractAmMat(String s) {
    	try{
	    	StringTokenizer st = new StringTokenizer(s, " ");
	    	st.nextToken(); //skip the label
	        double[] temp = new double[3];
	        int i = 0;
	        while(st.hasMoreTokens()){
	            temp[i] = clampColor(Double.parseDouble(st.nextToken()));
	            i++;
	        }
	        
			amCol = new Vec3(temp[0], temp[1], temp[2]);
			
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for ambient material");
    	}
	}
    	
    //extract diffuse material data from the given line
    private static void extractDifMat(String s) {
    	try{
	    	StringTokenizer st = new StringTokenizer(s, " ");
	    	st.nextToken(); //skip the label
	        double[] temp = new double[3];
	        int i = 0;
	        while(st.hasMoreTokens()){
	            temp[i] = clampColor(Double.parseDouble(st.nextToken()));
	            i++;
	        }
	        
			difCol = new Vec3(temp[0], temp[1], temp[2]);
			
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for diffuse material");
    	}
	}
    
    //extract specular material data from the given line
    private static void extractSpMat(String s) {
    	try{
	    	StringTokenizer st = new StringTokenizer(s, " ");
	    	st.nextToken(); //skip the label
	        double[] temp = new double[4];
	        int i = 0;
	        while(st.hasMoreTokens()){
	        	if(i != 3)
	            	temp[i] = clampColor(Double.parseDouble(st.nextToken()));
	        	else
	        		temp[i] = Double.parseDouble(st.nextToken());
	            i++;
	        }
	        
			spCol = new Vec3(temp[0], temp[1], temp[2]);
			if(temp[3] > 128)
				temp[3] = 128;
			else if(temp[3] < 1)
				temp[3] = 1;
			spExp = (int) temp[3];
			
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for specular material");
    	}
	}
    
    //extract triangle data from the given line
    private static void extractTri(String s) {
    	try{
	    	StringTokenizer st = new StringTokenizer(s, " ");
	    	st.nextToken(); //skip the label
	        int[] temp = new int[3];
	        int i = 0;
	        while(st.hasMoreTokens()){
	            temp[i] = Integer.parseInt(st.nextToken());
	            i++;
	        }
	        
			Triangle tempTri = new Triangle(temp[0], temp[1], temp[2],
					amCol, difCol, spCol, spExp);
			Data.addShape(tempTri);
			
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for triangle");
    	}
	}
    
    //extract sphere data from the given line
    private static void extractSphere(String s) {
    	try{
	    	StringTokenizer st = new StringTokenizer(s, " ");
	    	st.nextToken(); //skip the label
	        int i = Integer.parseInt(st.nextToken());

			Sphere tempSphere = new Sphere(i, amCol, difCol, spCol, spExp);
			Data.addShape(tempSphere);
			
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for sphere");
    	}
	}
    
    //extract plane data from the given line
    private static void extractPlane(String s) {
    	try{
	    	StringTokenizer st = new StringTokenizer(s, " ");
	    	st.nextToken(); //skip the label
	        int i = Integer.parseInt(st.nextToken());

			Plane tempPlane = new Plane(i, amCol, difCol, spCol, spExp);
			Data.addShape(tempPlane);
			
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for plane");
    	}
	}
    
    //extract settings data from the given line
    private static void extractSettings(String s) {
    	try{
    		StringTokenizer st = new StringTokenizer(s, " ");
    		st.nextToken(); //skip the label
	        String[] temp = new String[3];
	        double d, m;
	        int i = 0;
	        while(i <= 2){
	            temp[i] = st.nextToken();
	            i++;
	        }
	        m = Double.parseDouble(st.nextToken());
	        d = Double.parseDouble(st.nextToken());
	        
	        Data.changeSettings(new Settings(temp[0], temp[1], temp[2], m, d));

    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for settings");
    	}
	}
    
    //extract point light data fromt he given line
    private static void extractPLight(String s){
    	try{
			StringTokenizer st = new StringTokenizer(s, " ");
			st.nextToken(); //skip the label
	        double[] temp = new double[2];
	        int i = 0;
	        while(st.hasMoreTokens()){
	            temp[i] = Double.parseDouble(st.nextToken());
	            i++;
	        }
	        
	        PointLight tempPlight = new PointLight((int) temp[0], temp[1]);
	        Data.addLight(tempPlight);
	        
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for point light");
    	}
    }
    
    //extract directional light data from the given line
    private static void extractDLight(String s){
    	try{
			StringTokenizer st = new StringTokenizer(s, " ");
			st.nextToken(); //skip the label
	        double[] temp = new double[2];
	        int i = 0;
	        while(st.hasMoreTokens()){
	            temp[i] = Double.parseDouble(st.nextToken());
	            i++;
	        }
	        
	        DirectionalLight tempDlight = new DirectionalLight((int) temp[0], temp[1]);
	        Data.addLight(tempDlight);
	        
    	} catch(Exception e){
    		throw new IllegalArgumentException("Incorrect syntax for directional light");
    	}
    }
    
    //extract camera data from the given line
    private static void extractCamera(String s){
    	try{
	    	StringTokenizer st = new StringTokenizer(s, " ");
	    	st.nextToken(); //skip the label
	        Data.changeCamera(Integer.parseInt(st.nextToken()));
			
    	} catch(Exception e){
    		System.out.println(e);
    		throw new IllegalArgumentException("Incorrect syntax for camera");
    	}
    }
    
    
    //clamp color values to be between 0 and 1
    public static double clampColor(double d){
    	if(d > 1){
    		return 1.0;
    	}
    	else if(d < 0)
    		return 0.0;
    	else
    		return d;
    }
}
