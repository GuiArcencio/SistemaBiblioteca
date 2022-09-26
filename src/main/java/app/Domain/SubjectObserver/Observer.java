package app.Domain.SubjectObserver;


public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}
