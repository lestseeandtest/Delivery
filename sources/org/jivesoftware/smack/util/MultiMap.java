package org.jivesoftware.smack.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MultiMap<K, V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_MAP_SIZE = 6;
    private static final int ENTRY_SET_SIZE = 3;
    private final Map<K, Set<V>> map;

    private static class SimpleMapEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        private SimpleMapEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    public MultiMap() {
        this(6);
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        for (Set contains : this.map.values()) {
            if (contains.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public Set<Entry<K, V>> entrySet() {
        LinkedHashSet linkedHashSet = new LinkedHashSet(size());
        for (Entry entry : this.map.entrySet()) {
            Object key = entry.getKey();
            for (Object simpleMapEntry : (Set) entry.getValue()) {
                linkedHashSet.add(new SimpleMapEntry(key, simpleMapEntry));
            }
        }
        return linkedHashSet;
    }

    public Set<V> getAll(Object obj) {
        Set<V> set = (Set) this.map.get(obj);
        return set == null ? Collections.emptySet() : set;
    }

    public V getFirst(Object obj) {
        Set all = getAll(obj);
        if (all.isEmpty()) {
            return null;
        }
        return all.iterator().next();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Set<K> keySet() {
        return this.map.keySet();
    }

    public boolean put(K k, V v) {
        Set set = (Set) this.map.get(k);
        if (set == null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(3);
            linkedHashSet.add(v);
            this.map.put(k, linkedHashSet);
            return false;
        }
        set.add(v);
        return true;
    }

    public void putAll(Map<? extends K, ? extends V> map2) {
        for (Entry entry : map2.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public V remove(Object obj) {
        Set set = (Set) this.map.remove(obj);
        if (set == null) {
            return null;
        }
        return set.iterator().next();
    }

    public boolean removeOne(Object obj, V v) {
        Set set = (Set) this.map.get(obj);
        if (set == null) {
            return false;
        }
        boolean remove = set.remove(v);
        if (set.isEmpty()) {
            this.map.remove(obj);
        }
        return remove;
    }

    public int size() {
        int i = 0;
        for (Set size : this.map.values()) {
            i += size.size();
        }
        return i;
    }

    public List<V> values() {
        ArrayList arrayList = new ArrayList(size());
        for (Set addAll : this.map.values()) {
            arrayList.addAll(addAll);
        }
        return arrayList;
    }

    public MultiMap(int i) {
        this.map = new LinkedHashMap(i);
    }
}
