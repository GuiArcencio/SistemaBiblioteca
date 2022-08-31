package app.Domain.SubjectObserver;

import java.util.ArrayList;
import java.util.List;
import app.Domain.SubjectObserver.Observer;


public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();


    public void attach(Observer observer) {
        observers.add(observer);		
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }
}
