package app.Domain.SubjectObserver;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();


    public void attach(Observer observer) {
        if(observers == null){
            observers = new ArrayList<Observer>();
        }
        observers.add(observer);		
    }

    public void notifyAllObservers() {
        if(observers == null)
            return;
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void detach(Observer observer){
        if (observers == null){
            return;
        }
        observers.remove(observer);
    }
}
