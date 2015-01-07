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
public class ElementImpl< X > implements Element< String, String, Version< String, Date, Element >, Date, X > {

    String id;
    String name = null;
    Version< String, Date, Element > version;
    Workspace< String, String, Version< String, Date, Element >, Date, X > workspace;
    Map< String, Property< String, String, Version< String, Date, Element >, Date, X >  > properties;

    /**
     *
     */
    public ElementImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public int compareTo( Element o ) {
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
    public Workspace< String, String, Version< String, Date, Element >, Date, X >
           getWorkspace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Version< String, Date, Element >, Date, X >>
           getProperty( Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Version< String, Date, Element >, Date, X >>
           getPropertyWithIdentifier( String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Version< String, Date, Element >, Date, X >>
           getPropertyWithName( String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Version< String, Date, Element >, Date, X >>
           getPropertyWithType( Element< String, String, Version< String, Date, Element >, Date, X > specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property< String, String, Version< String, Date, Element >, Date, X >>
           getPropertyWithValue( Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List< Version< String, Date, Element >> getVersions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map< Date, Version< String, Date, Element >> getVersionMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element > getLatestVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element > getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element > getVersion( Date dateTime ) {
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
