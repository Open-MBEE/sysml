package sysml;

import java.util.Map;

public interface Workspace< N, I, V, D, X > {
    public Map< I, Element< N, I, V, D, X > > getElements();
    public Element< N, I, V, D, X > getElement( I id );
    public Element< N, I, V, D, X > getElement( I id, D dateTime );
    public Workspace< N, I, V, D, X > getParentWorkspace();
    public Workspace< N, I, V, D, X > getChildWorkspaces();
    public Map< D, X > getChangeHistory();
    public Workspace< N, I, V, D, X > getMaster();
}
