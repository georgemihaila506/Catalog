package com.company;

import java.util.Comparator;

public class PairG<T extends  Comparable<T>,U> extends  javafx.util.Pair <T,U> implements Comparator< javafx.util.Pair<T,U> > {

    PairG(T el, U el2) {
        super(el,el2);
    }


    @Override
    public int compare(javafx.util.Pair<T, U> o1, javafx.util.Pair<T, U> o2) {
        return o1.getKey().compareTo(o2.getKey());
    }

    @Override
    public  T getKey(){
        return super.getKey();
    }

    @Override
    public U getValue(){
        return super.getValue();
    }
}
