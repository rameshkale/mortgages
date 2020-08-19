package com.mortgages.dslayer.engine.impl;

import com.mortgages.dslayer.engine.entity.Application;
import com.mortgages.dslayer.engine.ApplicationStore;

import java.util.Arrays;

/**
 * Created by rameshkale on 14/08/20.
 */
public class BasicApplicationStore implements ApplicationStore {
    private Application[] elementData;
    private static final Application[] EMPTY = {};
    private static final int DEFAULT_SIZE = 5;
    private static final int DEFAULT_INCR_SIZE = 10;
    private int size;

    public BasicApplicationStore(int initialCapacity){
        if (initialCapacity > 0) {
            this.elementData = new Application[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY;
        }
    }

    public BasicApplicationStore(){
        this.elementData = EMPTY;
    }

    private void checkCapacity(int min) {
        if (elementData == EMPTY) {
            min = Math.max(DEFAULT_SIZE, min);
        }

        ensureExplicitCapacity(min);
    }
    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0)
            resize();
    }
    private void resize() {
        int oldSize = elementData.length;
        int newSize = oldSize + DEFAULT_INCR_SIZE; //(oldSize >> 3);
        Application[] newElements = new Application[newSize];
        System.arraycopy(elementData, 0, newElements, 0,oldSize);
        elementData = newElements;
    }

    @Override
    public boolean add(Application application) {
        checkCapacity(size+1);
        elementData[size++] = application;
        return true;
    }

    @Override
    public Application get(int index) {
        return elementData[index];
    }

    @Override
    public Application replace(int index, Application application) {
        elementData[index] = application;
        return elementData[index];
    }

    @Override
    public Application[] getAll() {
        Application[] apps = new Application[size];
        System.arraycopy(elementData, 0, apps, 0,size);
        return apps;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean delete(Application application) {
        return false;
    }

    @Override
    public String toString() {
        return "BasicApplicationStore{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }
}
