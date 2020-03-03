# Testing and HoFs

In this project, use the deques from project 1A to solve a real world problem. Along the way, also write tests of the application to make sure that everything works correctly.

`Palindrome.java`  `Deque.java` `CharacterComparator.java` `Sub Class` `Test doc`

## Getting Started
<img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/proj%201b.png width="700">

**`junit test`** - **`sub class`** - **`interface`** - **`main class`**  


### Prerequisites
```
JUnit 4.12, and other libraries
```

### Coding 

**`main class`**

```
Palindrome.java
             * wordToDeque - store word into data structure (LinkedList or Array)
             * isPalindrome(String word) - check palindrome
             * isPalindrome(String word, CharacterComparator cc) - check palindrome by diff 1 & N
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

* H
