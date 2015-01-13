/**
 *
 */
package sysml.impl;

import gov.nasa.jpl.mbee.util.CompareUtils;
import gov.nasa.jpl.mbee.util.InterpolatedMap;
import gov.nasa.jpl.mbee.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
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

    Workspace< String, String, Date > workspace;
    String id;
    String name;
    Version< String, Date, Element< String, String, Date > > version = null;
    Map< String, Property< String, String, Date > > properties;
    String qualifiedName = null;
    String qualifiedId = null;

    // TODO -- superclass

    // TODO -- create version
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

    public ElementImpl( Element< String, String, Date > e ) {
        this.id = e.getId();
        this.name = e.getName();
        this.workspace = e.getWorkspace();
        this.version = e.getVersion();
        Collection< Property< String, String, Date > > props = e.getProperties();
        for ( Property< String, String, Date > prop : props ) {
            try {
                Property< String, String, Date > newValue =
                        (Property< String, String, Date >)prop.clone();
                this.getPropertyMap().put( prop.getId(), newValue );
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

    public Property< String, String, Date > getSingleProperty( Object specifier ) {
        Collection< Property< String, String, Date > > props = getProperty( specifier );
        if ( Utils.isNullOrEmpty( props ) ) {
            return null;
        }
        return props.iterator().next();
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
        List< Version< String, Date, Element< String, String, Date > > > versions =
                getVersions();
        if ( versions.isEmpty() ) {
            if ( version != null ) {
                // TODO -- REVIEW -- should this case never happen?
                return version;
            }
            return null;
        }
        return versions.get( versions.size() - 1 );
    }

    @Override
    public Version< String, Date, Element< String, String, Date > > getVersion() {
        return this.version;
    }

    @Override
    public Version< String, Date, Element< String, String, Date > > getVersion( Date dateTime ) {
        Map< Date, Version< String, Date, Element< String, String, Date > > > map =
                getVersionMap();
        InterpolatedMap< Date, Version< String, Date, Element< String, String, Date > > > imap;
        if ( map instanceof InterpolatedMap ) {
            imap = (InterpolatedMap< Date, Version< String, Date, Element< String, String, Date > > >)map;
        } else {
            imap = new InterpolatedMap< Date, Version< String, Date, Element< String, String, Date > > >( map );
        }
        return imap.get( dateTime );
    }

    @Override
    public <T> T getPropertyValue( Object specifier ) {
        try {
            Property< String, String, Date > prop =
                    getSingleProperty( specifier );
            if ( prop == null ) return null;
            return (T)prop.getValue();
        } catch ( ClassCastException e ) {
            e.printStackTrace();
        }
        return null;
     }

    @Override
    public Date getCreationTime() {
        version = getVersion();
        if ( version == null ) {
            Date val = getPropertyValue( "created" );
            if ( val != null ) return val;
            val = getPropertyValue( "creationTime" );
            if ( val != null ) return val;
            val = getPropertyValue( "createdTime" );
            if ( val != null ) return val;
            val = getPropertyValue( "creation" );
            if ( val != null ) return val;
            return val;
        } else {
            return version.getTimestamp();
        }
    }

    @Override
    public Date getModifiedTime() {
        version = getLatestVersion();
        if ( version == null ) {
            Date val = getPropertyValue( "modified" );
            if ( val != null ) return val;
            val = getPropertyValue( "modifiedTime" );
            if ( val != null ) return val;
            val = getPropertyValue( "modificationTime" );
            if ( val != null ) return val;
            val = getPropertyValue( "modification" );
            if ( val != null ) return val;
            return val;
        } else {
            return version.getTimestamp();
        }
    }

    @Override
    public void setVersion( Version< String, Date, Element< String, String, Date > > version ) {
        this.version = version;
    }


}
