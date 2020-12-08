import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashMap<K, V> implements Map61BL<K, V> {

    /* TODO: Instance variables here */
    private static final int default_size = 16;
    private static final double load_factor = 0.75;
    private static int size;
    private static int threshold;
    private Entry<K, V>[] buckets;




    public HashMap() {
        // TODO: YOUR CODE HERE
        buckets = new Entry[default_size];
        threshold = (int) (default_size * load_factor);
        size = 0;
    }

    public HashMap(int initialCapacity) {
        buckets = new Entry[initialCapacity];
        threshold = (int) (initialCapacity * load_factor);
        size = 0;
    }

    public HashMap(int initialCapacity, double loadFactor) {
        buckets = new Entry[initialCapacity];
        threshold = (int) (initialCapacity * loadFactor);
        size = 0;
    }

    /* Returns the number of items contained in this map. */
    public int size() {
        // TODO: YOUR CODE HERE
        return size;
    }

    public int capacity() {
        // TODO: YOUR CODE HERE
        return buckets.length;
    }

    /* Returns true if the map contains the KEY. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (get(key) == null) {
            return false;
        } else {
            return true;
        }
    }

    /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
    @Override
    public V get(K key) {
        // TODO: YOUR CODE HERE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int h = hash(key, buckets.length);
        Entry<K, V> entity = get(h, key);
        return entity == null ? null : entity.getValue();
    }

    /* Helper func */
    private Entry<K, V> get(int h, K k) {
        Entry e = buckets[h];
        while (e != null) {
            if (e.getHashCode() == h && e.getKey().equals(k)) {
                return e;
            }
            e = e.getNext();
        }
        return null;
    }

    /* Puts a (KEY, VALUE) pair into this map. If the KEY already exists in the
       SimpleNameMap, replace the current corresponding value with VALUE. */
    @Override
    public void put(K key, V value) {
        // TODO: YOUR CODE HERE
        int h = hash(key, buckets.length);
        Entry<K, V> e = buckets[h];
        while (e != null) {
            if (e.getHashCode() == h && e.getKey().equals(key)) {
                e.setValue(value);
                return;
            }
            e = e.getNext();
        }
        put(h, key, value);
    }

    /* Helper func */
    private void put(int h, K key, V value) {
        Entry<K, V> e = new Entry(key, value, buckets[h], h);
        buckets[h] = e;
        size += 1;
        if (size > threshold) {
            resize(buckets.length * 2);
        }
    }

    /* Resize func */
    private void resize(int capacity) {
        Entry[] R = new Entry[capacity];
        for (Entry bucket : buckets) {
            Entry<K, V> e = bucket;
            while (e != null) {
                Entry<K, V> Pre = e.getNext();
                int newHashCode = hash(e.getKey(), R.length);
                e.setNext(R[newHashCode]);
                e.setHashCode(newHashCode);
                R[newHashCode] = e;
                e = Pre;
            }
        }
        buckets = R;
        threshold = (int) (buckets.length * load_factor);
    }



    /* Helper func */
    private int hash(K key, int length) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return (key.hashCode() & 0x7fffffff) % length;
    }


    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */
    @Override
    public V remove(K key) {
        // TODO: YOUR CODE HERE
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int h = hash(key, buckets.length);
        return remove(h, key);
    }

    /* Helper func */
    private V remove(int hashCode, K key) {
        Entry e = buckets[hashCode];
        Entry<K, V> nextE = e.getNext();
        if (e.getKey().equals(key)) {
            V toRemove = (V) e.getValue();
            buckets[hashCode] = nextE;
            size -= 1;
            return toRemove;
        } else {
            while (!nextE.getKey().equals(key)) {
                e = e.getNext();
                nextE = nextE.getNext();
            }
            V toRemove = nextE.getValue();
            e.setNext(nextE.getNext());
            size -= 1;
            return toRemove;
        }
    }

    @Override
    public boolean remove(K key, V value) {
        return false;
    }



    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        buckets = new Entry[buckets.length];
        size = 0;
    }


    /* Iterator helper func */
    public Set<K> keySet() {
        Set<K> all = new HashSet<>();
        for (Entry bucket : buckets) {
            Entry e = bucket;
            while (e != null) {
                all.add((K) e.getKey());
                e = e.getNext();
            }
        }
        return all;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }



    private class Entry<K, V> {

        private final K key;
        private V value;
        private Entry<K, V> next;
        private int hashCode;

        Entry(K k, V v, Entry<K, V> n, int h) {
            this.key = k;
            this.value = v;
            this.next = n;
            this.hashCode = h;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        public int getHashCode() {
            return hashCode;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }

        public void setHashCode(int h) {
            this.hashCode = h;
        }
    }
}