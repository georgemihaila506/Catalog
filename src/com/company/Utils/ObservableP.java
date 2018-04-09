package com.company.Utils;

public interface ObservableP<E> {
    /**
     * Register an observer.
     * @param o the observer
     */
    void addObserver(ObserverP<E> o);
    /**
     * Unregister an observer.
     * @param o the observer
     */
    void removeObserver(ObserverP<E> o);


    public void notifyObservers();
}
