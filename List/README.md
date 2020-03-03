### `IntList`
<img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/List/Naive%20IntList.png width="400">


### `SLList` (with sentinel)
* Nested class：enable creating other methods, more strong structure
* Sentinel：avoid error like p.next when p is null
<img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/List/SLList.png width="400">



### `DLList` (with sentinel) = circularly
* Double pointer：better accessing items
* Circular sentinel：omit the last sentinel, the front is also the end
<img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/List/DLList%20-%20circularly.png width="400">
<img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/List/DLList%20-%20circularly%202.png width="400">



### `AList`
* Array: easy to access item, but hard to resize and insert
<img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/List/Naive%20Alist.png width="400">


### `ArrayDeque` (with 2 sentinel) = circularly
* 2 sentinel: mark the front and back index of the array, making it's easy to insert, and resize
* Efficency: to avoid waste memory and reduce running time, implement other methods to solve this issue
<img src=https://github.com/Kaicheng1995/DataStructure_Algorithm/blob/master/img_folder/List/ArrayDeque.png width="500">



