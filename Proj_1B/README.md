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

**`interface`**
```
Deque.java
         * add, remove, get, isEmpty, size....all the same method name in Array and LinkedList
         
CharacterComparator.java
         * equalChars() - check equal in special conditions (like diff 1 or N)
```

**`sub class`**
```
Yellow File
         * implements
         * @Override
         * methods exist in differents sub classes but have same method name and return type, they can be recalled through interface.
```
**`junit test`**
```
green File
         * @Test
         * assertEquals, assertTrue, assertFalse
```

## Running the tests
```
IDE RUN
```

## Authors

* **Kaicheng Jia** - *Initial work* - [Kaicheng1995](https://github.com/Kaicheng1995)

## Acknowledgments

* H
