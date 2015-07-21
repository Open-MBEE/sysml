package sysml;

import java.util.Collection;

public interface Property<N, I, D> extends BaseElement< N, I, D >, Comparable<Property<N, I, D> > {
    public Element<N, I, D> getType();

    public Object getValue();
}
