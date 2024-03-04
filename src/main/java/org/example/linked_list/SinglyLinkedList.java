package org.example.linked_list;

public class SinglyLinkedList {
    // 19. Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode cur = start;
        ListNode slow = start;

        for (int i = 0; i < n + 1; i++) {
            cur = cur.next;
        }

        while (cur != null) {
            cur = cur.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return start.next;
    }
}
