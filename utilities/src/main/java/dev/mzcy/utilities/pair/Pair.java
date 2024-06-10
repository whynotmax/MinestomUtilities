package dev.mzcy.utilities.pair;

import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Pair<K, V> {

    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    public void key(K key) {
        this.key = key;
    }

    public void value(V value) {
        this.value = value;
    }

}
