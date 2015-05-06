package sysml.view;

import java.util.Collection;
import java.util.Date;

import org.json.JSONObject;

/**
 * Embeddable in a view.
 *
 */
public interface Viewable< E > {
    /**
     * @return JSON for embedding in a SysML View (TODO -- include a specification)
     */
    public JSONObject toViewJson(Date dateTime);
    /**
     * @return the elements whose information is accessible in a view
     */
    public Collection<E> getDisplayedElements();
}
