/**
 *
 */
package sysml.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sysml.Element;
import sysml.Property;
import sysml.Version;
import sysml.Workspace;

/**
 *
 */
public class ElementImpl implements Element< String, String, Date > {

    String id;
    String name = null;
    Version< String, Date, Element< String, String, Date > > version;
    Workspace< String, String, Date > workspace;
    Map< String, Property< String, String, Date > > properties;
    String qualifiedName;
    String qualifiedId;


    /**
     *
     */
    public ElementImpl() {
        // TODO Auto-generated constructor stub
    }

    public ElementImpl( ElementImpl e ) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public ElementImpl clone() throws CloneNotSupportedException {
        return new ElementImpl( this );
    }

    @Override
    public int compareTo( Element< String, String, Date > o ) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace< String, String, Date > getWorkspace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Date > >
        getProperty( Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Date > >
           getPropertyWithIdentifier( String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Date > >
           getPropertyWithName( String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Date > >
           getPropertyWithType( Element< String, String, Date > specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Date > > getPropertyWithValue( Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List< Version< String, Date, Element< String, String, Date > > > getVersions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map< Date, Version< String, Date, Element< String, String, Date > > > getVersionMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element< String, String, Date > > getLatestVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element< String, String, Date > > getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element< String, String, Date > > getVersion( Date dateTime ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date getCreationTime() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Date getModifiedTime() {
        // TODO Auto-generated method stub
        return null;
    }


}
