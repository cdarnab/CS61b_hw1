package nbody;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NBody {
	
    public static void main(String[] args) {
    	int T = Integer.parseInt(args[0]);
    	double dt = Double.parseDouble(args[1]);
    	String filename = args[2];
    	
    	In in = new In(filename);
        int numPlanets = in.readInt();
        double universeRadius = in.readDouble();

        Planet[] planets = new Planet[numPlanets];
        
        for(int i = 0; i < numPlanets; i++){
        	Planet p = NBody.getPlanet(in);
        	planets[i] = p;
        	//System.out.println(p.x + " " + p.y + " " + p.xVelocity + " " + p.yVelocity + " " + p.mass + " " + p.img);
        }
        
        //create canvas, set scale
        StdDraw.setCanvasSize();
        StdDraw.setScale(-universeRadius, universeRadius);
        
        
        for(int i = 0; i < T; i += dt){
            StdDraw.picture(0, 0, "images/starfield.jpg");
        	
        	for(Planet p : planets){
            	p.setNetForce(planets);
            	p.update(dt);  
            	p.draw();
            	//System.out.println(p.img.charAt(7) + " | " + p.xAccel + " | " + p.yAccel);
            }
        	
        	StdDraw.show(10);
        }
        
        NumberFormat formatter = new DecimalFormat("0.####E0");
        
        System.out.println(numPlanets);
        System.out.println(universeRadius);
        for(Planet p : planets){
        	System.out.println(formatter.format(p.x) + " " 
        				     + formatter.format(p.y) + " " 
        				     + formatter.format(p.xVelocity) + " " 
        				     + formatter.format(p.yVelocity) + " " 
        				     + formatter.format(p.mass) + " " 
        				     + p.img.substring(7));
        }
        
         
    }
	
	public static Planet getPlanet(In in){

		Planet p = new Planet(0, 0, 0, 0, 0, "");
		p.x = in.readDouble();
		p.y = in.readDouble();
		p.xVelocity = in.readDouble();
		p.yVelocity = in.readDouble();
		p.mass = in.readDouble();
		p.img = "images/" + in.readString();		
		
		/*System.out.println("x: " + p.x);
		System.out.println("y: " + p.y);
		System.out.println("xV: " + p.xVelocity);
		System.out.println("xV: " + p.yVelocity);
		System.out.println("m: " + p.mass);
		System.out.println("img: " + p.img);*/
		
		return p;
	}
}
