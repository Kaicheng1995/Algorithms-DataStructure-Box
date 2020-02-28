# NBody Simulation

The goal for this project is to write a program simulating the motion of N objects in a plane, accounting for the gravitational forces mutually affecting each object as demonstrated by Sir Isaac Newton’s Law of Universal Gravitation.

`Body.java`  `NBody.java`  `StdDraw.java`  `In.java` `StdDraw.java` `images`  `data`

## Getting Started
<img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/result.png width="200"> <img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/result.png width="200">

### Prerequisites
```
Null
```

### Coding

```
Body.java
         * Planet Initialization - (location, velocity, mass, name)
         * Planet Constructor
         * Planet Distance
         * Planet Force - （by 1)
         * Planet Force XY
         * Planet Net Force XY - (by all)
         * Planet Update
         * Planet Draw - (use API)
```

```
NBody.java
         * Universe Radius
         * Universe Planets - (an array)
         * Main Function
                 * Read file
                 * Draw background
                 * Draw Planets
                 * Create animation - (time loop + planet update loop)
                 * Print out
```
```
StdDraw.java
         * setScale(input);
         * clear(); - (clear screen to default color white)
         * enableDoubleBuffering(); - (prevent flickery)
         * show(); - (show the offscreen buffer
         * pause(); - (reduce velocity)
         * picture(input); - (draw picture)         
```
```
In.java
         * In in = new In(filename); 
         * readDouble();
         * readString();
         * readInt();
```


## Running the tests
```
javac NBody.java
java NBody 157788000.0 25000.0 data/planets.txt
```

## Authors

* **Kaicheng Jia** - *Initial work* - [Kaicheng1995](https://github.com/Kaicheng1995)

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
