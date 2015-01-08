/**
 *
 */
package sysml;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface Element<N, I, D> extends Comparable< Element<N, I, D> >, Cloneable {

    public Element<N, I, D> clone() throws CloneNotSupportedException;

    public I getId();

    public N getName();

    public Workspace< N, I, D >  getWorkspace();

    public Collection< Property< N, I, D > > getProperties();
    public Collection< Property< N, I, D > > getProperty( Object specifier );
    public Property< String, String, Date > getPropertyWithIdentifier( I specifier );
    public Collection< Property< N, I, D > > getPropertyWithName( N specifier );
    public Collection< Property< N, I, D > > getPropertyWithType( Element< N, I, D > specifier );
    public Collection< Property< N, I, D > > getPropertyWithValue( Object specifier );

    public List< Version< N, D, Element< N, I, D > > > getVersions();
    public Map< D, Version< N, D, Element< N, I, D > > > getVersionMap();
    public Version< N, D, Element< N, I, D > > getLatestVersion();
    public Version< N, D, Element< N, I, D > > getVersion();
    public Version< N, D, Element< N, I, D > > getVersion( D dateTime );

    public D getCreationTime();
    public D getModifiedTime();

}
