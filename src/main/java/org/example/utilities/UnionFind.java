package org.example.utilities;

public class UnionFind {
    int[] component;
    int distinctComponents;

    public UnionFind(int n) {
        component = new int[n+1];
        for (int i = 0; i <= n; i++) {
            component[i] = i;
        }
        distinctComponents = n;
    }

    /* unite.
        For example, if previously we have component {0, 4, 4, 4, 4, 6, 7, 7},
        then invoke this method with a=1, b=5, then after invoke, {0, 4, 4, 4, 5, 7, 7, 7}
     */
    public boolean unite(int a, int b) {
        if (findComponent(a) != findComponent(b)) {
            component[findComponent(a)] = b;
            distinctComponents--;
            return true;
        }

        return false;
    }

    // find and change component
    // for example, if previously we have component:{0, 2, 3, 4, 4, 6, 7, 7}, then after invoke this method with a=1, the component become {0, 4, 4, 4, 4, 6, 7, 7}
    public int findComponent(int a) {
        if (component[a] != a) {
            component[a] = findComponent(component[a]);
        }
        return component[a];
    }

    public boolean united() {
        return distinctComponents == 1;
    }
}
