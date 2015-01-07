package sysml.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import sysml.Element;
import sysml.Version;
import sysml.Workspace;

public class WorkspaceImpl<X> implements Workspace<String, String, Version< String, Date, Element >, Date, X> {

    protected LinkedHashMap< String, Element<String, String, Version< String, Date, Element >, Date, X > >
        elements = new LinkedHashMap< String, Element<String, String, Version< String, Date, Element >, Date, X > >();

    protected LinkedHashMap< String, Map< Date, Version< String, Date, Element > > >
        versions = new LinkedHashMap< String, Map< Date, Version< String, Date, Element > > >();

    @Override
    public Map< Date, X > getChangeHistory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< String, String, Version< String, Date, Element >, Date, X >
           getChildWorkspaces() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element< String, String, Version< String, Date, Element >, Date, X >
            getElement( String id ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element< String, String, Version< String, Date, Element >, Date, X >
            getElement( String id, Date dateTime ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map< String, Element< String, String, Version< String, Date, Element >, Date, X >>
           getElements() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< String, String, Version< String, Date, Element >, Date, X >
           getMaster() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< String, String, Version< String, Date, Element >, Date, X >
           getParentWorkspace() {
        // TODO Auto-generated method stub
        return null;
    }

}
