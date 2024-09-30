package org.example.utilities;

public class TwoWayNode {
    public int val;
    public TwoWayNode prev;
    public TwoWayNode next;

    public TwoWayNode(int val, TwoWayNode next, TwoWayNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}
