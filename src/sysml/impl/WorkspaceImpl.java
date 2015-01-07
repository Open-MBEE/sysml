package sysml.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import sysml.ChangeSet;
import sysml.Element;
import sysml.Version;
import sysml.Workspace;

public class WorkspaceImpl implements Workspace<String, String, Date> {

    protected LinkedHashMap< String, Element< String, String, Date > >
        elements = new LinkedHashMap< String, Element<String, String, Date > >();

    protected LinkedHashMap< String, Map< Date, Version< String, Date, Element<String, String, Date > > > >
        versions = new LinkedHashMap< String, Map< Date, Version< String, Date, Element<String, String, Date > > > >();

    @Override
    public Map< Date, ChangeSet > getChangeHistory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map< String, Element< String, String, Date > > getElements() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element< String, String, Date > getElement( String id ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element< String, String, Date >
            getElement( String id, Date dateTime ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< String, String, Date > getParentWorkspace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< String, String, Date > getChildWorkspaces() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< String, String, Date > getMaster() {
        // TODO Auto-generated method stub
        return null;
    }

}
