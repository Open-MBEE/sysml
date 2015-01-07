/**
 *
 */
package sysml;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public interface Element<N, I, V, D, X> extends Comparable< Element<N, I, V, D, X> >, Cloneable {

    public I getId();

    public N getName();

    public Collection< Property<N, I, V, D, X > > getProperty( Object specifier );
    public Collection< Property< N, I, V, D, X > > getPropertyWithIdentifier( I specifier );
    public Collection< Property< N, I, V, D, X > > getPropertyWithName( N specifier );
    public Collection< Property< N, I, V, D, X > > getPropertyWithType( Element< N, I, V, D, X > specifier );
    public Collection< Property< N, I, V, D, X > > getPropertyWithValue( Object specifier );

    public List< V > getVersions();
    public V getLatestVersion();
    public V getVersion();
    public V getVersion( );

}
