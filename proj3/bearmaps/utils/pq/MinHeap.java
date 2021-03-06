package bearmaps.utils.pq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;
    // TODO: YOUR CODE HERE (no code should be needed here if not
    private HashMap<E, Integer> locations = new HashMap<>();
    // implementing the more optimized version)


    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);

    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
        locations.put(element, index);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        // TODO: YOUR CODE HERE
        return 2 * index;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        // TODO: YOUR CODE HERE
        return 2 * index + 1;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        // TODO: YOUR CODE HERE
        return index / 2;
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. If the elements are equal, return either index. */
    private int min(int index1, int index2) {
        // TODO: YOUR CODE HERE
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        if (element1 == null && element2 != null) {
            return index2;
        } else if (element2 == null && element1 != null) {
            return index1;
        } else {
            if (element1.compareTo(element2) > 0) {
                return index2;
            } else {
                return index1;
            }
        }
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E findMin() {
        // TODO: YOUR CODE HERE
        return getElement(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        // TODO: YOUR CODE HERE
        if (index == 1) {
            return;
        } else {
            if (min(getParentOf(index), index) == index) {
                swap(index, getParentOf(index));
                bubbleUp(getParentOf(index));
            }
        }
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        // TODO: YOUR CODE HERE
        if (getElement(getLeftOf(index)) == null && getElement(getRightOf(index)) == null) {
            return;
        } else if (getElement(getLeftOf(index)) == null && getElement(getRightOf(index)) != null) {
            int indexOfSmallerChild = min(index, getRightOf(index));
            if (indexOfSmallerChild != index) {
                swap(indexOfSmallerChild, index);
                bubbleDown(indexOfSmallerChild);
            }
        } else if (getElement(getLeftOf(index)) != null && getElement(getRightOf(index)) == null) {
            int indexOfSmallerChild = min(index, getLeftOf(index));
            if (indexOfSmallerChild != index) {
                swap(indexOfSmallerChild, index);
                bubbleDown(indexOfSmallerChild);
            }
        } else {
            int indexOfSmallerChild = min(getLeftOf(index), getRightOf(index));
            if (indexOfSmallerChild == getLeftOf(index)) {
                if (min(index, indexOfSmallerChild) != index) {
                    swap(indexOfSmallerChild, index);
                    bubbleDown(indexOfSmallerChild);
                }
            }
            if (indexOfSmallerChild == getRightOf(index)) {
                if (min(index, indexOfSmallerChild) != index) {
                    swap(indexOfSmallerChild, index);
                    bubbleDown(indexOfSmallerChild);
                }
            }

        }

    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        // TODO: YOUR CODE HERE
        return size;
    }

    /* Inserts ELEMENT into the MinHeap. If ELEMENT is already in the MinHeap,
       throw an IllegalArgumentException.*/
    public void insert(E element) {
        // TODO: YOUR CODE HERE
        if (contains(element)) {
            throw new IllegalArgumentException();
        } else {
            size += 1;
            setElement(size, element);
            bubbleUp(size);
        }
    }

    /* Returns and removes the smallest element in the MinHeap. */
    public E removeMin() {
        // TODO: YOUR CODE HERE
        E toReturn = findMin();
        swap(1, size);
        locations.remove(contents.get(size));
        contents.remove(size);
        bubbleDown(1);
        size -= 1;
        return toReturn;
    }

    /* Replaces and updates the position of ELEMENT inside the MinHeap, which
       may have been mutated since the initial insert. If a copy of ELEMENT does
       not exist in the MinHeap, throw a NoSuchElementException. Item equality
       should be checked using .equals(), not ==. */
    public void update(E element) {
        // TODO: YOUR CODE HERE
        if (!contains(element)) {
            throw new NoSuchElementException();
        } else {
            int indexOfElement = locations.get(element);
            E oldElement = contents.get(indexOfElement);
            if (oldElement.compareTo(element) < 0) {
                setElement(indexOfElement, element);
                locations.remove(oldElement);
                locations.put(element, indexOfElement);
                bubbleDown(indexOfElement);
            } else {
                setElement(indexOfElement,element);
                locations.remove(oldElement);
                locations.put(element, indexOfElement);
                bubbleUp(indexOfElement);
            }
        }
    }

    /* Returns true if ELEMENT is contained in the MinHeap. Item equality should
       be checked using .equals(), not ==. */
    public boolean contains(E element) {
        // TODO: YOUR CODE HERE
        return locations.containsKey(element);
    }
}
