package sysml;

import java.util.Collection;

import org.json.JSONObject;

/**
 * Embeddable in a view.
 *
 */
public interface Viewable< E > {
    /**
     * @return JSON for embedding in a SysML View (TODO -- include a specification)
     */
    public JSONObject toViewJson();
    public Collection<E> getDisplayedElements();
}
