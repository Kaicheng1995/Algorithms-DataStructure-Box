public interface Deque<T> {

    void addFirst(T x);
    void addLast(T x);
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
    default boolean isEmpty() {
        return size() == 0;
    }
}