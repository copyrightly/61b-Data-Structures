/* Tree234Node.java */

package dict;

/**
 *  A Tree234Node is a node in a 2-3-4 tree (Tree234 class).
 *
 *  DO NOT CHANGE ANYTHING IN THIS FILE.
 *  You may add helper methods and additional constructors, though.
 **/
class Tree234Node {

    /**
     *  keys is the number of keys in this node.  Always 1, 2, or 3.
     *  key1 through key3 are the keys of this node.  If keys == 1, the value
     *    of key2 doesn't matter.  If keys < 3, the value of key3 doesn't matter.
     *  parent is this node's parent; null if this is the root.
     *  child1 through child4 are the children of this node.  If this is a leaf
     *    node, they must all be set to null.  If this node has no third and/or
     *    fourth child, child3 and/or child4 must be set to null.
     **/
    int keys;
    int key1;
    int key2;
    int key3;
    Tree234Node parent;
    Tree234Node child1;
    Tree234Node child2;
    Tree234Node child3;
    Tree234Node child4;

    Tree234Node(Tree234Node p, int key) {
        keys = 1;
        key1 = key;
        parent = p;
        child1 = null;
        child2 = null;
        child3 = null;
        child4 = null;
    }

    Tree234Node(int k1, Tree234Node c1, Tree234Node c2) {
        keys = 1;
        key1 = k1;
        parent = null;
        child1 = c1;
        child2 = c2;
        child3 = null;
    }

    public void changeOne(int key, Tree234Node c1, Tree234Node c2) {
        keys = 2;
        if (key < key1) {
            key2 = key1;
            key1 = key;
            child3 = child2;
            child1 = c1;
            child2 = c2;
            child3.parent = this;
        } else {
            key2 = key;
            child3 = c2;
            child2 = c1;
            child1.parent = this;
        }
        c1.parent = this;
        c2.parent = this;
    }

    public void changeTwo(int key, Tree234Node c1, Tree234Node c2) {
        keys = 3;
        if (key < key1) {
            key3 = key2;
            key2 = key1;
            key1 = key;
            child4 = child3;
            child3 = child2;
            child1 = c1;
            child2 = c2;
            child4.parent = this;
            child3.parent = this;
        } else if (key < key2) {
            key3 = key2;
            key2 = key;
            child4 = child3;
            child3 = c2;
            child2 = c1;
            child1.parent = this;
            child4.parent = this;
        } else {
            key3 = key;
            child3 = c1;
            child4 = c2;
            child1.parent = this;
            child2.parent = this;
        }
        c1.parent = this;
        c2.parent = this;
    }

    public Tree234Node moveMiddle() {
        Tree234Node newChild1 = new Tree234Node(key1, child1, child2);
        Tree234Node newChild2 = new Tree234Node(key3, child3, child4);
        if (child1 != null) {
            child1.parent = newChild1;
            child2.parent = newChild1;
            child3.parent = newChild2;
            child4.parent = newChild2;
        }
        if (parent == null) {
            keys = 1;
            key1 = key2;
            child1 = newChild1;
            child2 = newChild2;
            child3 = null;
            child4 = null;
            newChild1.parent = this;
            newChild2.parent = this;
        } else {
            if (parent.keys == 1) {
                parent.changeOne(key2, newChild1, newChild2);
            } else {
                parent.changeTwo(key2, newChild1, newChild2);
            }
        }
        return  newChild1;
    }



    /**
     *  toString() recursively prints this Tree234Node and its descendants as
     *  a String.  Each node is printed in the form such as (for a 3-key node)
     *
     *      (child1)key1(child2)key2(child3)key3(child4)
     *
     *  where each child is a recursive call to toString, and null children
     *  are printed as a space with no parentheses.  Here's an example.
     *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
     *
     *  DO NOT CHANGE THIS METHOD.
     **/
    public String toString() {
        String s = "";

        if (child1 != null) {
            s = "(" + child1.toString() + ")";
        }
        s = s + key1;
        if (child2 != null) {
            s = s + "(" + child2.toString() + ")";
        } else if (keys > 1) {
            s = s + " ";
        }
        if (keys > 1) {
            s = s + key2;
            if (child3 != null) {
                s = s + "(" + child3.toString() + ")";
            } else if (keys > 2) {
                s = s + " ";
            }
        }
        if (keys > 2) {
            s = s + key3;
            if (child4 != null) {
                s = s + "(" + child4.toString() + ")";
            }
        }
        return s;
    }

    /**
     *  printSubtree() recursively prints this Tree234Node and its descendants as
     *  a tree (albeit sideways).
     *
     *  You're welcome to change this method if you like.  It won't be tested.
     **/
    public void printSubtree(int spaces) {
        if (child4 != null) {
            child4.printSubtree(spaces + 5);
        }
        if (keys == 3) {
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            System.out.println(key3);
        }

        if (child3 != null) {
            child3.printSubtree(spaces + 5);
        }
        if (keys > 1) {
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            System.out.println(key2);
        }

        if (child2 != null) {
            child2.printSubtree(spaces + 5);
        }
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
        System.out.println(key1);

        if (child1 != null) {
            child1.printSubtree(spaces + 5);
        }
    }
}