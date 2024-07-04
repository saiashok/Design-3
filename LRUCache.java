// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : Yes, had to learn

/*
 * 146. LRU Cache
 * Least Recent Used cache...
 * 
 * We need to maintain a doublelly maintained linked list, and two dummy nodes.. Head & Tail
 * Doublely maintained linkedlist will help maintain the LRU as a Queue, and we use a Map of key&Node to get to the node. (Map so we can get the Node O(1))
 */

import java.util.*;

class LRUCache {

    class Node {
        Node next;
        Node prev;
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    Map<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    private void removeNode(Node node) { // only two pointers
        node.next.prev = node.prev;
        node.prev.next = node.next;

    }

    private void addNodeToHead(Node node) { // have to update 4 pointers when we add.
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;

    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node currNode = map.get(key);
        removeNode(currNode);
        addNodeToHead(currNode);
        return currNode.value;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {
            Node temp = map.get(key);
            temp.value = value;
            removeNode(temp);
            addNodeToHead(temp);

        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);// Remove from map
            }
            Node newNode = new Node(key, value);
            addNodeToHead(newNode);
            map.put(key, newNode); // add to map
        }

    }
}
