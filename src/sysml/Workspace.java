package sysml;

import java.util.Map;

public interface Workspace< N, I, D > {
    public Map< I, Element< N, I, D > > getElements();
    public Element< N, I, D > getElement( I id );
    public Element< N, I, D > getElement( I id, D dateTime );
    public Workspace< N, I, D > getParentWorkspace();
    public Workspace< N, I, D > getChildWorkspaces();
    public Map< D, ChangeSet > getChangeHistory();
    public Workspace< N, I, D > getMaster();
}
