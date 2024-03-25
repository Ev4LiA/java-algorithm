package org.example.utilities;

public class TrieNode {
    public int ind;
    public TrieNode[] child;

    public TrieNode(int idx) {
        ind = idx;
        child = new TrieNode[26];
    }
}
