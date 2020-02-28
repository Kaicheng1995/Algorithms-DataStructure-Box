public class NBody{

    /** READ UNIVERSE RADIUS FROM THE FILE */
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();                              //read and return the next int
        double Universe_R = in.readDouble();       //read and return the next double
        return Universe_R;
    }

    /** READ UNIVERSE PLANETS FROM THE FILE */
    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int Num = in.readInt();
        in.readDouble();
        Body[] Planets = new Body[Num];

        for(int i=0; i<Num; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planets[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return Planets;

//      为何下面不对？
//      for(int i=0; i<Num; i++) {
//            Planets[i].xxPos = in.readDouble();
//            Planets[i].yyPos = in.readDouble();
//            Planets[i].xxVel = in.readDouble();
//            Planets[i].yyVel = in.readDouble();
//            Planets[i].mass = in.readDouble();
//            Planets[i].imgFileName = in.readString();
//        }
//        return Planets;
    }

    /** MAIN FUNCTION
     * 1. READ FILE
     * 2. DRAW BACKGROUND
     * 3. DRAW PLANET
     * 4. CREATE ANIMATION
     * 5. PRINT OUT
     */
    public static void main(String[] args){

        //READ FILE
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Universe_R = readRadius(filename);
        Body[] Planets = readBodies(filename);

        //DRAW BACKGROUND
        StdDraw.setScale(-Universe_R, Universe_R);
        StdDraw.clear();                                 //Clears the screen to the default color (white)
        StdDraw.picture(0, 0, "images/starfield.jpg");   //Position to place background picture

        //DRAW PLANETS
        for (Body planet : Planets) {
            planet.draw();                               //still use StdDraw.picture(), draw() is tailor-made in Body Class.
        }

        //CREATE ANIMATION
        StdDraw.enableDoubleBuffering();                      //Prevent flickery

        for(double time = 0; time <= T; time += dt){            //Until time reaches T
            double[] xForces = new double[Planets.length];
            double[] yForces = new double[Planets.length];

            for(int i = 0; i < Planets.length; i++){                      //Calculate netforce for each planet and update their new positions
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
                Planets[i].update(dt, xForces[i], yForces[i]);
            }
            for(int i = 0; i < Planets.length; i++) {
                Planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");     //make sure background won't be covered by newly updated positions

            for (Body planet : Planets) {                 //redraw
                planet.draw();
            }
            StdDraw.show();                               //show the offscreen buffer
            StdDraw.pause(10);                            //Pause the animation for 10 milliseconds(because planets moves so relatively fast)
        }

        //PRINT OUT
        StdOut.printf("%d\n", Planets.length);            //when stops, print out
        StdOut.printf("%.2e\n", Universe_R);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                    Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
        }
    }
}
