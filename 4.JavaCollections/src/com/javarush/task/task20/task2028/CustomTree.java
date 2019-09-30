package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Serializable, Cloneable {
    Entry<String> root = new Entry<>("root");

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)){
            throw new UnsupportedOperationException();
        }
        Queue<Entry> queue = new LinkedList<>();
        Entry<String> leaf = root;
        do{

            if (!leaf.availableToAddLeftChildren)
                queue.add(leaf.leftChild);
            if (!leaf.availableToAddRightChildren)
                queue.add(leaf.rightChild);
            if (!queue.isEmpty()) {
                leaf = queue.poll();
            }
            if (leaf.elementName.equals(o)){
                if (leaf.parent.leftChild == leaf){
                    leaf.parent.leftChild = null;
                    leaf.parent.availableToAddLeftChildren = true;
                    return true;
                }
                if (leaf.parent.rightChild == leaf){
                    leaf.parent.rightChild = null;
                    leaf.parent.availableToAddRightChildren = true;
                    return true;
                }
            }
        }while (!queue.isEmpty());
        return false;
    }

    public String getParent(String s){
        Queue<Entry> queue = new LinkedList<>();
        Entry<String> leaf = root;
        queue.add(root);
        do{
            if (!queue.isEmpty())
                leaf = queue.poll();
            if (leaf.elementName.equals(s)){
                return leaf.parent.elementName;
            }
            if (!leaf.availableToAddLeftChildren)
                queue.add(leaf.leftChild);
            if (!leaf.availableToAddRightChildren)
                queue.add(leaf.rightChild);

        }while (!queue.isEmpty());
        return null;
    }

    @Override
    public boolean add(String elementName) {
        Queue<Entry> queue = new LinkedList<>();
        Entry<String> leaf = root;
        do{
            if (!leaf.availableToAddLeftChildren)
                queue.add(leaf.leftChild);
            else {
                Entry<String> newLeaf = new Entry<>(elementName);
                newLeaf.parent = leaf;
                leaf.leftChild = newLeaf;
                leaf.availableToAddLeftChildren = false;
                return true;
            }
            if (!leaf.availableToAddRightChildren)
                queue.add(leaf.rightChild);
            else {
                Entry<String> newLeaf = new Entry<>(elementName);
                newLeaf.parent = leaf;
                leaf.rightChild = newLeaf;
                leaf.availableToAddRightChildren = false;
                return true;
            }
            if (!queue.isEmpty())
                leaf = queue.poll();
        }while (!queue.isEmpty());
        return false;
    }

    @Override
    public int size() {
        Queue<Entry> queue = new LinkedList<>();
        Entry<String> leaf = root;
        int size = -1;
        queue.add(root);
        do{
            if (!queue.isEmpty()) {
                leaf = queue.poll();
                size++;
            }
            if (!leaf.availableToAddLeftChildren)
                queue.add(leaf.leftChild);
            if (!leaf.availableToAddRightChildren)
                queue.add(leaf.rightChild);
        }while (!queue.isEmpty());
        return size;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }



    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
