package sysml.impl;

import gov.nasa.jpl.mbee.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sysml.AccessPrivileges;
import sysml.ChangeSet;
import sysml.Element;
import sysml.Version;
import sysml.Workspace;

public class WorkspaceImpl implements Workspace<String, String, Date> {

    protected static WorkspaceImpl master = new WorkspaceImpl( "master" );

    // fields
    protected LinkedHashMap< String, Element< String, String, Date > >
        elements = new LinkedHashMap< String, Element<String, String, Date > >();

    protected LinkedHashMap< String, Map< Date, Version< String, Date, Element<String, String, Date > > > >
        versions = new LinkedHashMap< String, Map< Date, Version< String, Date, Element<String, String, Date > > > >();

    protected LinkedHashMap< Date, ChangeSet > changeHistory = new LinkedHashMap<Date, ChangeSet>();

    protected LinkedHashMap< Element<String, String, Date >, Map< String, AccessPrivileges > >
        accesPrivilegeMap = new LinkedHashMap< Element<String, String, Date >, Map< String, AccessPrivileges > >();

    protected String id;
    protected String name;
    protected Workspace<String, String, Date> parent;
    protected Map< String, Workspace<String, String, Date> > children;

    public WorkspaceImpl( String name ) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Map< Date, Version< String, Date, Element< String, String, Date > > > getVersionMap( String id ) {
        return versions.get( id );
    }

    @Override
    public List< Version< String, Date, Element<String, String, Date > > > getVersions( String id ) {
        return new ArrayList< Version< String, Date, Element<String, String, Date > > >( versions.get( id ).values() );
    }

    @Override
    public Map< Date, ChangeSet > getChangeHistory() {
        return changeHistory;
    }

    @Override
    public Map< String, Element< String, String, Date > > getElements() {
        return elements;
    }

    @Override
    public Element< String, String, Date > getElement( String id ) {
        return elements.get( id );
    }

    @Override
    public Element< String, String, Date > getElement( String id, Date dateTime ) {
        Element< String, String, Date > element = getElement( id );
        Version< String, Date, Element< String, String, Date >> version =
                element.getVersion( dateTime );
        if ( version == null ) return null;
        return version.getData();
    }

    @Override
    public Workspace< String, String, Date > getParentWorkspace() {
        return parent;
    }

    @Override
    public Map< String, Workspace< String, String, Date > > getChildWorkspaces() {
        return children;
    }

    @Override
    public Workspace< String, String, Date > getMaster() {
        return master;
    }

    @Override
    public AccessPrivileges
            getAccessPrivileges( String username,
                                 Element< String, String, Date > element ) {
        return Utils.get( accesPrivilegeMap, element, username );
    }

}
