package sysml;

import java.util.Collection;

/**
 * Embeddable in a view.
 *
 */
public interface Viewable< E > {
    /**
     * @return JSON for embedding in a SysML View (TODO -- include a specification)
     */
    public String toViewJson();
    public Collection<E> getDisplayedElements();
}
