package app.Observer;

import app.Subject.Subject;

public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}
