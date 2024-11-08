public class Planet{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  
  public Planet(double xxPos,double yyPos, double xxVel, double yyVel, double mass, String imgFileName){
    this.xxPos = xxPos;
    this.yyPos = yyPos;
    this.xxVel = xxVel;
    this.yyVel = yyVel;
    this.mass = mass;
    this.imgFileName = imgFileName;
    }

    public Planet(Planet p){
    this.xxPos = p.xxPos;
    this.yyPos = p.yyPos;
    this.xxVel = p.xxVel;
    this.yyVel = p.yyVel;
    this.mass = p.mass;
    this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
      double dx = p.xxPos - this.xxPos;
      double dy = p.yyPos - this.yyPos;
      //System.out.println("dx: " + dx + ", dy: " + dy);
      double r = Math.sqrt(dx*dx + dy*dy);
      return r;
    }
    
    private static final double  G = 6.67e-11;//private漏加
    public double calcForceExertedBy(Planet p){
      double m1 = this.mass;
      double m2 = p.mass;
      double r = this.calcDistance(p);
      double F = G*m1*m2/(r*r);
      return F;
    }

    public double calcForceExertedByX(Planet p){
      double dx = p.xxPos - this.xxPos;
      double Fx = this.calcForceExertedBy(p) *  (dx/this.calcDistance(p));
      return Fx;
    }

    public double calcForceExertedByY(Planet p){
      double dy = p.yyPos - this.yyPos;
      double Fy = this.calcForceExertedBy(p) *  (dy/this.calcDistance(p));
      return Fy;
    }

    public double calcNetForceExertedByX(Planet[] planets){
      int i; 
      double Fnetx = 0;
       for(i = 0; i<planets.length; ++i){
        Planet p = planets[i];
        if(this != p){
        Fnetx += this.calcForceExertedByX(p);
        }
      } 
      return Fnetx;
    }

    public double calcNetForceExertedByY(Planet[] planets){
      int i; 
      double Fnety = 0;
       for(i = 0; i<planets.length; ++i){
        Planet p = planets[i];
        if(this != p){
        Fnety += this.calcForceExertedByY(p);
        }
      } 
      return Fnety;
    }

    /* public class update{
      public double dt;
      public double fx;
      public double fy;
    } */
    public void update(double dt, double fx, double fy){
    /* this.dt = dt;
      this.fx = fx;
      this.fy = fy; */
      double ax = fx/this.mass;
      double ay = fy/this.mass;
       this.xxVel = this.xxVel + ax*dt;
       this.yyVel = this.yyVel + ay*dt;
       this.xxPos = this.xxPos + xxVel*dt;
       this.yyPos = this.yyPos + yyVel*dt;
    }
     
    public void draw(){
       StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
     }
}