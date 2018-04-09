package com.company.Utils;

import java.util.ArrayList;
import java.util.List;

public class ObservedList<E> extends ArrayList<E> implements Observable<E> {
    protected List<Observer<E>> observers=new ArrayList<Observer<E>>();

    @Override
    public void addObserver(Observer<E> o) {
        observers.add(o);
    }
    @Override
    public E set(int index, E e){
        E ret = super.set(index, e);
        ListEvent<E> event= createEvent(ListEventType.UPDATE, e);
        notifyObservers(event);
        return ret;
    }
    @Override
    public void removeObserver(Observer<E> o) {
        observers.remove(o);
    }

    private ListEvent<E> createEvent(ListEventType type,final E elem )
    {
        return new ListEvent<E>(type) {
            @Override
            public ObservedList<E> getList() {
                return ObservedList.this;
            }

            @Override
            public E getElement() {
                return elem;
            }
        };
    }
    @Override
    public void notifyObservers(ListEvent<E> event) {
        for(Observer o:observers) {
            o.notifyEvent(event);
        }
    }

}
