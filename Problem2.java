// LRU Cache
// extra space of O(n)
// time complexity O(1) for get and put operations


class LRUCache {

    class Node {
        int key;
        int value;
        Node prev, next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity; 
    HashMap<Integer,Node> map;
    private Node head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;

        map = new HashMap<Integer,Node>();
         head = new Node(0,0);
         tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            insertToFront(node);
            return node.value;
        }

        return -1;
        
    }
    
    public void put(int key, int value) {

        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertToFront(node);

        } else {
            if(map.size() == capacity){
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
               }
                  Node node = new Node(key,value);
            
        insertToFront(node);
        map.put(key,node);
        }

     



    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insertToFront(Node node){
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
