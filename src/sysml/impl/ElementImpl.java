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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sysml.BaseElement;
import sysml.Element;
import sysml.Property;
import sysml.Version;
import sysml.Workspace;

/**
 *
 */
public class ElementImpl extends BaseElementImpl implements Element< String, String, Date > {

    Set< Element< String, String, Date > > superClasses = null;
    Version< String, Date, BaseElement< String, String, Date > > version = null;
    Map< String, Property< String, String, Date > > properties;
    String qualifiedName = null;
    String qualifiedId = null;

    // TODO -- create version
    public ElementImpl( Workspace< String, String, Date > workspace,
                        String id,
                        String name,
                        Version< String, Date, BaseElement< String, String, Date >> version,
                        Map< String, Property< String, String, Date >> properties ) {
       
        super( workspace, id, name, version );

        this.properties = properties;
    }

    public ElementImpl( Element< String, String, Date > e ) {
        super(e.getWorkspace(), e.getId(), e.getName(), e.getVersion());

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
    public Collection< Element< String, String, Date > > getSuperClasses() {
        if ( superClasses == null ) {
            superClasses = new LinkedHashSet< Element< String, String, Date > >();
        }
        return superClasses;
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
}
