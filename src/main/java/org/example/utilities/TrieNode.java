package org.example.utilities;

public class TrieNode {
    public int ind;
    public boolean isEnd;
    public TrieNode[] child;

    public TrieNode(int idx) {
        ind = idx;
        child = new TrieNode[26];
    }

    public TrieNode() {
        child = new TrieNode[26];
        isEnd = false;
    }
}
