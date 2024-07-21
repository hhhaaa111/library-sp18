public class Planet{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  //构造函数
  public Planet(double xxpos,double yyPos, double xxVel, double yyVel, double mass, String imgFileName){
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

    public double calcForceExertedBy(Planet p){
      double dx = p.xxPos - this.xxPos;
      double dy = p.yyPos - this.yyPos;
      double r = Math.sqrt(dx*dx + dy*dy);
      return r;
    }
}