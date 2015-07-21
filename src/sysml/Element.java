/**
 *
 */
package sysml;

import java.util.Collection;
import java.util.Date;

/**
 *
 */
public interface Element<N, I, D> extends BaseElement<N, I, D>, Comparable<Element<N, I, D> > {

    public Collection< Element< N, I, D > > getSuperClasses();

    public Collection< Property< N, I, D > > getProperties();
    public Collection< Property< N, I, D > > getProperty( Object specifier );
    public Property< String, String, Date > getPropertyWithIdentifier( I specifier );
    public Collection< Property< N, I, D > > getPropertyWithName( N specifier );
    public Collection< Property< N, I, D > > getPropertyWithType( Element< N, I, D > specifier );
    public Collection< Property< N, I, D > > getPropertyWithValue( Object specifier );
}
