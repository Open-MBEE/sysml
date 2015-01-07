package sysml;

import java.util.Collection;

public interface Property<N, I, V, D, X> extends Element< N, I, V, D, X > {
    public Collection< Element< N, I, V, D, X > > getType();
}
