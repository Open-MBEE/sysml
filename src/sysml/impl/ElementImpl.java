/**
 *
 */
package sysml.impl;

import gov.nasa.jpl.mbee.util.CompareUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sysml.Element;
import sysml.Property;
import sysml.Version;
import sysml.Workspace;

/**
 *
 */
public class ElementImpl implements Element< String, String, Date > {

    Workspace< String, String, Date > workspace;
    String id;
    String name;
    Version< String, Date, Element< String, String, Date > > version = null;
    Map< String, Property< String, String, Date > > properties;
    String qualifiedName = null;
    String qualifiedId = null;


    public ElementImpl( Workspace< String, String, Date > workspace,
                        String id,
                        String name,
                        Version< String, Date, Element< String, String, Date >> version,
                        Map< String, Property< String, String, Date >> properties ) {
        this( workspace, id, name );
        this.version = version;
        this.properties = properties;
    }

    public ElementImpl( Workspace< String, String, Date > workspace,
                        String id,
                        String name ) {
        super();
        this.id = id;
        this.name = name;
        this.workspace = workspace;
    }

    public ElementImpl( ElementImpl e ) {
        this.id = e.id;
        this.name = e.name;
        this.workspace = e.workspace;
        this.version = e.version;
        for ( Entry< String, Property< String, String, Date > > pe :
              e.getPropertyMap().entrySet() ) {
            try {
                Property< String, String, Date > newValue =
                        (Property< String, String, Date >)pe.getValue().clone();
                this.getPropertyMap().put( pe.getKey(), newValue );
            } catch ( CloneNotSupportedException e1 ) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public ElementImpl clone() throws CloneNotSupportedException {
        return new ElementImpl( this );
    }

    @Override
    public int compareTo( Element< String, String, Date > o ) {
        if ( this == o ) return 0;
        if ( o == null ) return 1;
        int comp = CompareUtils.compare( getWorkspace(), o.getWorkspace() );
        if ( comp != 0 ) return comp;
        comp = CompareUtils.compare( getId(), o.getId() );
        if ( comp != 0 ) return comp;
        comp = CompareUtils.compare( getName(), o.getName() );
        if ( comp != 0 ) return comp;
        comp = CompareUtils.compare( getProperties(), o.getProperties() );
        // Note, not considering version, so two nodes of different versions may
        // be the same.
        return 0;
    }

    @Override
    public boolean equals( Object o ) {
        if ( o == null || !( o instanceof Element ) ) return false;
        return compareTo( (Element)o ) == 0;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Workspace< String, String, Date > getWorkspace() {
        return workspace;
    }

    @Override
    public Collection< Property< String, String, Date > > getProperties() {
        return getPropertyMap().values();
    }

    public Map< String, Property< String, String, Date > > getPropertyMap() {
        if ( properties == null ) properties =
                new LinkedHashMap< String, Property< String, String, Date > >();
        return properties;
    }

    @Override
    public Collection< Property< String, String, Date > >
        getProperty( Object specifier ) {
        if ( specifier == null ) return null;
        Collection< Property< String, String, Date > > props = null;
        Property< String, String, Date > prop =
                getPropertyWithIdentifier( specifier.toString() );
        if ( prop != null ) {
            props = new ArrayList< Property< String, String, Date > >();
            props.add( prop );
        }
        if ( props == null || props.isEmpty()  ) {
            props = getPropertyWithName( name );
        }
        if ( props == null || props.isEmpty()  ) {
            props = getPropertyWithValue( name );
        }
        return props;
    }

    @Override
    public Property< String, String, Date >
           getPropertyWithIdentifier( String id ) {
        if ( id == null ) return null;
        Property< String, String, Date > prop = properties.get( id );
        return prop;
    }

    @Override
    public Collection< Property< String, String, Date > >
           getPropertyWithName( String name ) {
        ArrayList< Property< String, String, Date > > list =
                new ArrayList< Property< String, String, Date > >();
        for ( Property< String, String, Date > prop : getProperties() ) {
            if ( prop.getName() != null && prop.getName().equals( name ) ) {
                list.add( prop );
            }
        }
        return list;
    }

    @Override
    public Collection< Property< String, String, Date > >
           getPropertyWithType( Element< String, String, Date > type ) {
        ArrayList< Property< String, String, Date > > list =
                new ArrayList< Property< String, String, Date > >();
        for ( Property< String, String, Date > prop : getProperties() ) {
            if ( prop.getType() != null && prop.getType().equals( type ) ) {
                list.add( prop );
            }
        }
        return list;
    }

    @Override
    public Collection< Property< String, String, Date > > getPropertyWithValue( Object value ) {
        ArrayList< Property< String, String, Date > > list =
                new ArrayList< Property< String, String, Date > >();
        for ( Property< String, String, Date > prop : getProperties() ) {
            if ( prop.getValue() != null && prop.getValue().equals( value ) ) {
                list.add( prop );
            }
        }
        return list;
    }

    @Override
    public List< Version< String, Date, Element< String, String, Date > > > getVersions() {
        return getWorkspace().getVersions( getId() );
    }

    @Override
    public Map< Date, Version< String, Date, Element< String, String, Date > > > getVersionMap() {
        return getWorkspace().getVersionMap( getId() );
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
