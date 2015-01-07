package sysml.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import sysml.Element;
import sysml.Property;
import sysml.Version;
import sysml.Workspace;

public class PropertyImpl< X >
                         implements
                         Property< String, String, sysml.Version< String, Date, Element< String, String, Version, Date, X > >, Date, X > {

    public PropertyImpl() {
        // TODO Auto-generated constructor stub
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
    public
            Workspace< String, String, Version< String, Date, Element< String, String, Version, Date, X >>, Date, X >
            getWorkspace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< Property< String, String, Version< String, Date, Element< String, String, Version, Date, X >>, Date, X >>
            getProperty( Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< Property< String, String, Version< String, Date, Element< String, String, Version, Date, X >>, Date, X >>
            getPropertyWithIdentifier( String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< Property< String, String, Version< String, Date, Element< String, String, Version, Date, X >>, Date, X >>
            getPropertyWithName( String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< Property< String, String, Version< String, Date, Element< String, String, Version, Date, X >>, Date, X >>
            getPropertyWithType( Element< String, String, Version< String, Date, Element< String, String, Version, Date, X >>, Date, X > specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< Property< String, String, Version< String, Date, Element< String, String, Version, Date, X >>, Date, X >>
            getPropertyWithValue( Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            List< Version< String, Date, Element< String, String, Version, Date, X >>>
            getVersions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Map< Date, Version< String, Date, Element< String, String, Version, Date, X >>>
            getVersionMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element< String, String, Version, Date, X >>
            getLatestVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element< String, String, Version, Date, X >>
            getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Version< String, Date, Element< String, String, Version, Date, X >>
            getVersion( Date dateTime ) {
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

    @Override
    public int compareTo( Element< String, String,
                                   Version< String, Date, Element< String, String, Version, Date, X > >,
                                   Date, X > o ) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection< Element< String, String,
                                Version< String, Date, Element< String, String, Version, Date, X >>,
                                Date, X >> getType() {
        // TODO Auto-generated method stub
        return null;
    }

}
