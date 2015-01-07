package sysml;

import java.util.Collection;

public interface Property<N, I, D> extends Element< N, I, D > {
    public Collection< Element< N, I, D > > getType();
}
