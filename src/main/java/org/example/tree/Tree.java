package org.example.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {
    // 100. Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();

        q1.add(p);
        q2.add(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();
            if ((node2 == null && node1 != null) || (node1 == null && node2 != null)) return false;

            if (node1 == null && node2 == null) continue;

            if (node1.val != node2.val) return false;

            if ((node2.left == null && node1.left != null) || (node1.left == null && node2.left != null)) return false;
            q1.add(node1.left);
            q2.add(node2.left);

            if ((node2.right == null && node1.right != null) || (node1.right == null && node2.right != null))
                return false;

            q1.add(node1.right);
            q2.add(node2.right);
        }
        return true;
    }

    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        if (q == null && p == null) return true;
        if (q == null || p == null) return false;
        if (q.val != p.val) return false;
        return isSameTreeRecursive(p.left, q.left) && isSameTreeRecursive(p.right, q.right);
    }

    // 111. Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int count = 0;
        while (!q.isEmpty()) {
            count++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left == null && node.right == null) return count;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return count;
    }
}
