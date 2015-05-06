package sysml;

import java.util.Collection;

public interface Property<N, I, D> extends Element< N, I, D > {
    public Collection< Element< N, I, D > > getType();
    // TODO -- REVIEW -- This is meant to be a declaration, not an instance, so
    // Property should not have a value. To fix this, we need an Object, or
    // ElementInstance, or something. But, since old SysML allows defaultValues,
    // we're stuck with them. I suppose we should have Property serve the
    // purpose of both the declaration and the instance.
    public <T> T getValue(); // REVIEW -- Should the return type be Element?
}
