package sysml.impl;

import gov.nasa.jpl.mbee.util.CompareUtils;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import sysml.BaseElement;
import sysml.Element;
import sysml.Property;
import sysml.Version;
import sysml.Workspace;

public class PropertyImpl extends BaseElementImpl implements Property< String, String, Date > {

    protected Element< String, String, Date > type;
    protected Object value;

    public PropertyImpl( PropertyImpl p ) {
        super( p );
        type = p.type;
        value = p.value;
    }

    public PropertyImpl( Workspace< String, String, Date > workspace,
                         String id, String name, Version< String, Date, BaseElement< String, String, Date >> version,
                         Element< String, String, Date > type,
                         Object value ) {
        super( workspace, id, name, version );
        this.type = type;
        this.value = value;
    }

    @Override
    public PropertyImpl clone() {
        return new PropertyImpl( this );
    }   

    @Override
    public int compareTo( Property< String, String, Date > o ) {
       if ( this == o ) return 0;
       if ( o == null ) return 1;
       int comp = CompareUtils.compare( getWorkspace(), o.getWorkspace() );
       if ( comp != 0 ) return comp;
       comp = CompareUtils.compare( getId(), o.getId() );
       if ( comp != 0 ) return comp;
       comp = CompareUtils.compare( getName(), o.getName() );
       if ( comp != 0 ) return comp;
       comp = CompareUtils.compare( getType(), o.getType() );
       if ( comp != 0 ) return comp;
       comp = CompareUtils.compare( getValue(), o.getValue() );     
       // Note, not considering version, so two nodes of different versions may
       // be the same.
       return 0;
    }

    @Override
    public Element<String, String, Date> getType() {
        return type;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
