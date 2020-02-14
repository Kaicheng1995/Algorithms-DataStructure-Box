# TERMS AND DEFINITIONS
### CATALOGUE
* Big-O Notation

***
***Big-O Notation:***

    T(n) = O(f(n)):
    
    If and only if there exist constants c, n0 > 0, such that:
    T(n) <= cf(n), for all n>=n0
> Notice: constant c actually doesn't matter, n0 is the intersection point value. (UPPER BOUND)

***Big-Omega Notation:***

    T(n) = Ω(f(n)):
    
    If and only if there exist constants c, n0 > 0, such that:
    T(n) >= cf(n), for all n>=n0

***Theta Notation:***

    T(n) = Θ(f(n)):
    
    If and only if there exist constants c1, c2, n0 > 0, such that:
    c1f(n) => T(n) >= c2f(n), for all n>=n0
