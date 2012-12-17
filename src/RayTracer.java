import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;


public class RayTracer extends JPanel{
	protected int frameX;
	protected int frameY;
	protected Color bgc = Color.black;
	protected Shapes hitShape = null;
	protected Vec3 p, pWorld;
	protected Ray ray;

	public RayTracer(int frameX, int frameY){
		this.setName("frame");
		this.frameX = frameX;
		this.frameY = frameY;
	}

	//paint the scene
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		RenderingHints rh = g2d.getRenderingHints ();
		rh.put (RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		
		Data.setCameraBasis();

		for(int y = 0; y < frameY; y++){
			for(int x = 0; x < frameX; x++){
				hitShape = null;
				p = canvasToImagePlane(x, y);
				pWorld = p.ImagePlaneToWorld(Data.getXc(), Data.getYc(), Data.getZc());
				ray = new Ray(Data.getCameraVec(), pWorld);

				Vec3 cs;

				cs = Data.getShading2(ray, 0, Double.POSITIVE_INFINITY, Data.getSettings().getM());

				cs.clamp();
				g2d.setPaint(cs.toColor());
				g2d.drawLine(x,y, x, y);
			}
		}
	}

	//convert canvas point to image plane
	public Vec3 canvasToImagePlane(double x, double y){
		return new Vec3(x / frameX - .5, 
				-(frameY / frameX) * (y / frameY - .5),
				-Math.sqrt(3) / 2);
	}

}
