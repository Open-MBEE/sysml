package sysml.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import sysml.Element;
import sysml.Workspace;

public class WorkspaceImpl<N, I, V, X> implements Workspace<N, I, V, Date, X> {

    protected Map< I, Element<N, I, V, Date, X > > elements =
            new LinkedHashMap< I, Element<N, I, V, Date, X > >();

    @Override
    public Map< I, Element<N, I, V, Date, X > > getElements() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< N, I, V, Date, X > getParentWorkspace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< N, I, V, Date, X > getChildWorkspaces() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map< Date, X > getChangeHistory() {
        // TODO Auto-generated method stub
        return null;
    }
}
