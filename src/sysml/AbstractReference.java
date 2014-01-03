/**
 * 
 */
package sysml;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import sysml.AbstractReference.Interpretation.Category;
import sysml.SystemModel.ModelItem;

/**
 * AbstractReference simply provides fields for supporting accessors in
 * Reference. Subclasses of AbstractReference must minimally redefine
 * makeReference(), getAlternatives(), and getItems().
 */
public abstract class AbstractReference< RT, SM extends SystemModel< ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? > > implements Reference< RT, SM > {

    // try to use ModelItem enum as interpretation 
    public static class Interpretation {
        public Interpretation( Category category,
                               ModelItem modelItemInterpretation ) {
            super();
            this.category = category;
            this.modelItemInterpretation = modelItemInterpretation;
        }
        public static enum Category { ModelItem, Related, RelatedSource, RelatedTarget, MULTIPLE, UNKNOWN };
        Category category = Category.UNKNOWN;
        SystemModel.ModelItem modelItemInterpretation = null;
    }
    
    SM model = null;
    Object scope = null;
    Object specifier = null;
    Object nextSpecifier = null;
    Class< RT > type = null;
    Boolean isTemplate = null;
    
    Interpretation interpretation = new Interpretation();
    
    
    public AbstractReference() {
    }

    public AbstractReference( SM model ) {
        this.model = model;
    }

    public AbstractReference( SM model,
                              Object scope,
                              Object specifier,
                              Object nextSpecifier,
                              Class< RT > type,
                              Boolean isTemplate ) {
        super();
        this.model = model;
        this.scope = scope;
        this.type = type;
        this.specifier = specifier;
        this.nextSpecifier = nextSpecifier;
        this.isTemplate = isTemplate;
    }

    @Override
    public abstract Reference< RT, SM > makeReference();
//    {
//        return new AbstractReference< RT, SM >(); 
//    }

    @Override
    public  Reference< RT, SM > makeReference( SM model ) {
        //return new AbstractReference< RT >( model );
        Reference< RT, SM > r = makeReference();
        r.setModel( model );
        return r;
    }

    @Override
    public Reference< RT, SM > makeReference( SM model,
                                           Object scope, Class< RT > type, Object specifier,
                                           Object nextSpecifier, boolean isTemplate ) {
        //return new AbstractReference< RT >( model, scope, type, specifier, nextSpecifier, isTemplate );
        Reference< RT, SM > r = makeReference();
        r.setModel( model );
        r.setScope( scope );
        r.setType( type );
        r.setSpecifier( specifier );
        r.setNextSpecifier( nextSpecifier );
        r.setIsTemplate( isTemplate );
        return r;
    }
    
    public Reference< RT, SM > clone() throws CloneNotSupportedException {
        //return new AbstractReference< RT >( model, scope, type, specifier, nextSpecifier, type, isTemplate );
        return makeReference( model, scope, type, specifier, nextSpecifier, isTemplate );
    }


    /* (non-Javadoc)
     * @see sysml.Reference#getModel()
     */
    @Override
    public SM getModel() {
        return this.model;
    }

    @Override
    public void setModel( SM model ) {
        this.model = model;
    }

    @Override
    public Object getScope() {
        return scope;
    }

    @Override
    public void setScope( Object scope ) {
        this.scope = scope;
    }

    @Override
    public Object getSpecifier() {
        return specifier;
    }

    @Override
    public void setSpecifier( Object specifier ) {
        this.specifier = specifier;
    }

    @Override
    public Class< RT > getType() {
        return type;
    }

    @Override
    public void setType( Class< RT > type ) {
        this.type = type;
    }

    @Override
    public Object getNextSpecifier() {
        return nextSpecifier;
    }

    @Override
    public void setNextSpecifier( Object nextSpecifier ) {
        this.nextSpecifier = nextSpecifier;
    }

    @Override
    public boolean isTemplate() {
        return isTemplate;
    }

    @Override
    public void setIsTemplate( boolean isTemplate ) {
        this.isTemplate = isTemplate;
    }

    /* (non-Javadoc)
     * @see sysml.Reference#isConsistent()
     */
    @Override
    public boolean isConsistent() {
        Collection< RT > items = getItems();
        if ( !isTemplate() && ( items == null || items.isEmpty() ) ) return false;
        if ( items != null && getType() != null ) {
            for ( RT item : items ) {
                if ( item != null && !getType().isInstance( item ) ) return false;
            }
        }
        return true;
    }

    @Override
    public abstract List< Reference< ? extends RT, SM > > getAlternatives() {
        
    }
//  throw new UnsupportedOperationException();

    public Collection< RT > getItemsForInterpretation( Interpretation i ) {
        switch ( i.category ) {
            case UNKNOWN:
                return Collections.emptyList();
            case Related:
                Collection< RT > items =
                    getItemsForInterpretation( new Interpretation( Category.ModelItem, ModelItem.RELATIONSHIP ) );
                // TODO -- check null model and type
                getModel().filter( items, getType().getMethod( "isInstance", new Class<?>[] {Object.class} ), 0, null );
                // TODO
                break;
            case RelatedSource:
                // TODO
                break;
            case RelatedTarget:
                // TODO
                break;
            case ModelItem:
                if ( i.modelItemInterpretation == null ) return Collections.emptyList();
                switch( i.modelItemInterpretation ) {
                    case CONSTRAINT:
                    //{HERE!!!;} // TODO
                        break;
                    case CONTEXT:
                    case IDENTIFIER:
                    case NAME:
                    case OBJECT:
                    case PROPERTY:
                    case RELATIONSHIP:
                    case TYPE:
                    case VALUE:
                    case VERSION:
                    case VIEW:
                    case VIEWPOINT:
                    case WORKSPACE:
                    default:
                    // TODO
                };
                break;
            default:
                // TODO -- ERROR!
        };
        if ( i.category == Category.UNKNOWN ) 
        if ( )
        return Collections.emptyList();
    }
    
    @Override
    public abstract Collection< RT > getItems() {
        
    }

}
