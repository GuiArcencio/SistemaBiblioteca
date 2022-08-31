package app.Domain.SubjectObserver;

import app.Domain.SubjectObserver.Subject;

public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}
