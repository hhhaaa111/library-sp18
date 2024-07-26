public class NBody{
  
  public static double readRadius(String path){
    In in = new In(path);
    in.readInt();
    double r = in.readDouble();
    return r;
   }

   public static Planet[] readPlanets(String path){
    In in = new In(path);
    int num = in.readInt();
    double r = in.readDouble();
   
    int i;
    Planet[] planets = new Planet[num];
    for(i=0; i<num; ++i){
        double xxPos = in.readDouble(); 
        double yyPos = in.readDouble();
        double xxVel = in.readDouble();
        double yyVel = in.readDouble();
        double mass = in.readDouble();
        String imgFileName = in.readString();
        planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
    }
    return planets;
   }

   //public static String imagebeijing = "images/starfield.jpg";
   public static void main(String[] args){
   
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double r = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    int num = planets.length;
    
    StdDraw.enableDoubleBuffering();//启用双重缓冲调用
   
    StdDraw.setXscale(-r,r);
    StdDraw.setYscale(-r,r);
    //StdDraw.enableDoubleBuffering();//启用双重缓冲调用
    //draw beckgrounds
    StdDraw.picture(0, 0, "images/starfield.jpg");
    //draw all the planets
    //for(Planet planet : planets)
    for(int i = 0; i<num; ++i){
      Planet planet = planets[i];
      planet.draw();
    }

    double t = 0;
    //int num = planets.length;
    /* double[] xForces = new double[num];
    double[] yForces = new double[num]; */
    while(t<=T){
      double[] xForces = new double[num];
      double[] yForces = new double[num];
      for(int i =0; i<num; ++i){
      xForces[i] = planets[i].calcNetForceExertedByX(planets);
      yForces[i] = planets[i].calcNetForceExertedByY(planets);
      //planets[i].update(dt,xForces[i],yForces[i]);
      }
      for(int i = 0; i<num; ++i){
        planets[i].update(dt,xForces[i],yForces[i]);
      }

        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Planet planet : planets){
          planet.draw();     //这个不是写一遍就行的，draw得一遍遍地用
        }
      StdDraw.show();
      StdDraw.pause(10);//以毫秒为单位
      t += 1;
      }

      StdOut.printf("%d\n", planets.length);
      StdOut.printf("%.2e\n", r);
      for (int i = 0; i < planets.length; i++) {
          StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
  }

  

