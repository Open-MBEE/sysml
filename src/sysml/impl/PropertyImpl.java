package sysml.impl;

import gov.nasa.jpl.mbee.util.CompareUtils;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import sysml.Element;
import sysml.Property;
import sysml.Version;
import sysml.Workspace;

public class PropertyImpl extends ElementImpl implements Property< String, String, Date > {

    protected Map< String, Element< String, String, Date > > type;
    protected Object value;

    public PropertyImpl( PropertyImpl p ) {
        super( p );
        type = new LinkedHashMap< String, Element< String, String, Date > >( p.getTypeMap() );
        value = p.value;
    }

    public PropertyImpl( Workspace< String, String, Date > workspace,
                         String id,
                         String name,
                         Version< String, Date, Element< String, String, Date > > version,
                         Map< String, Property< String, String, Date > > properties,
                         Map< String, Element< String, String, Date > > type,
                         Object value ) {
        super( workspace, id, name, version, properties );
        this.type = type;
        this.value = value;
    }

    public PropertyImpl( Workspace< String, String, Date > workspace,
                         String id, String name,
                         Map< String, Element< String, String, Date > > type,
                         Object value ) {
        super( workspace, id, name );
        this.type = type;
        this.value = value;
    }

    public PropertyImpl( Workspace< String, String, Date > workspace,
                         String id, String name,
                         Element< String, String, Date > type,
                         Object value ) {
        super( workspace, id, name );
        this.getTypeMap().put( type.getName(), type );
        this.value = value;
    }

    @Override
    public PropertyImpl clone() {
        return new PropertyImpl( this );
    }

    @Override
    public int compareTo( Element< String, String, Date > o ) {
        if ( o == null || !( o instanceof PropertyImpl ) ) return 1;
        PropertyImpl p = (PropertyImpl)o;
        int comp = super.compareTo( p );
        if ( comp != 0 ) return comp;
        comp = CompareUtils.compare( getType(), p.getType() );
        if ( comp != 0 ) return comp;
        comp = CompareUtils.compare( getValue(), p.getValue() );
        return 0;
    }

    @Override
    public Collection< Element< String, String, Date > > getType() {
        return getTypeMap().values();
    }

    private Map< String, Element< String, String, Date >> getTypeMap() {
        if ( type == null ) type =
                new LinkedHashMap< String, Element< String, String, Date >>();
        return type;
    }

    @Override
    public <T> T getValue() {
        return (T)value;
    }


}
