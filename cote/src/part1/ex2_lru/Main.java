package part1.ex2_lru;

// 수업자료 91페이지: LRU Cache
// https://leetcode.com/problems/lru-cache/description/

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static void main(String[] args) throws IOException {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}

class LRUCache {
    private final int capacity;
    private final Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        try {
            return map.getOrDefault(key, -1);
        } finally {
            System.out.println(map);
        }
    }

    public void put(int key, int value) {
        try {
            if (map.size() >= capacity && !map.containsKey(key)) {

            }

            map.put(key, value);
        }finally {
            System.out.println(map);
        }
    }
}