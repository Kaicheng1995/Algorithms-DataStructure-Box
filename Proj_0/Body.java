/** Body class can represent a planet*/
public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** Add 2 constructors that can initalize an instance of the Body class,
    and it's like creating an access to place INPUT for the Body class
    IN SHORT: CREATE INPUT!!!! */
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    };
    /* why? 是为了可以引用this吗？*/
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    };

    /**  Calculate DISTANCE between 2 Body
     */
    public double calcDistance(Body b){
        double dxsquare = Math.pow((this.xxPos - b.xxPos),2);
        double dysquare = Math.pow((this.yyPos - b.yyPos),2);
        double distance = Math.sqrt(dxsquare + dysquare);
        return distance;
    }

    /**  Calculate FORCE exerted by another planet
     */
    public double calcForceExertedBy(Body b){
        double G = 6.67e-11;
        double Force = (G * this.mass * b.mass) / Math.pow(this.calcDistance(b),2);
        return Force;
    }


    /**  Calculate FORCE exerted by another planet in X and Y DIRECTIONS
     */
    public double calcForceExertedByX(Body b){
        double ForceX = this.calcForceExertedBy(b) * (b.xxPos-this.xxPos) / this.calcDistance(b);
        return ForceX;
    }
    public double calcForceExertedByY(Body b){
        double ForceY = this.calcForceExertedBy(b) * (b.yyPos-this.yyPos) / this.calcDistance(b);
        return ForceY;
    }

    /**  Calculate NET-FORCE exerted by ALL other planet in X and Y DIRECTIONS
     */
    public double calcNetForceExertedByX(Body[] allBodys){
        double NetForceX = 0;
        int N = allBodys.length;
        for(int i=0; i<N; i++){
            if(this.equals(allBodys[i])){
                continue;
            }
            else {
                NetForceX = NetForceX +
                        this.calcForceExertedBy(allBodys[i]) * (allBodys[i].xxPos-this.xxPos)
                                / this.calcDistance(allBodys[i]);
            }
        }
        return NetForceX;
    }
    public double calcNetForceExertedByY(Body[] allBodys){
        double NetForceY = 0;
        int N = allBodys.length;
        for(int i=0; i<N; i++){
            if(this.equals(allBodys[i])){
                continue;
            }
            else {
                NetForceY = NetForceY +
                        this.calcForceExertedBy(allBodys[i]) * (allBodys[i].yyPos-this.yyPos)
                                / this.calcDistance(allBodys[i]);
            }
        }
        return NetForceY;
    }
}
