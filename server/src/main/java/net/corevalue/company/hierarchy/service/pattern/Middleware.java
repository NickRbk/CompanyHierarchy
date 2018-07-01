package net.corevalue.company.hierarchy.service.pattern;

public abstract class Middleware {

    private Middleware next;

    public Middleware then(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean check();

    boolean checkNext() {
        return next == null || next.check();
    }
}