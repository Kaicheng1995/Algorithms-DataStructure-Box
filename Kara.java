/* 
[1]Simple Multiplication
https://yordigital.com/blog/big-o-notation-traditional-grade-school-algorithms-addition-multiplication/
Above link shows the Big-O notation value of simple multification. For each input "N", we have mostly "2N" operations in every 
Line, and "N" lines in every rows. Also notice that the last step is to add all the results, but here the operations are only 
constant. Thus, the Big-O of simple multification is "N²".
(Note: One operation sometimes can be calculated wrongly because of personal habits)
*/

/* 
[2]Karatsuba Multiplication
Recall: x*y = 10^n(ab) + 10^2/n(ad+bc) + bd
Operation1: recursively compute ac
Operation2: recursively conmpute bd
Operation3: recursively conmpute (a+b)*(c+d)
Gauss's trick: 3-1-2 = ad + bc
Upshot: only need 3 recursively multiplication and some additions (Subtraction)
*/

public int kara(int x, int y){
  int N = 
}
© 2020 GitHub, Inc.
