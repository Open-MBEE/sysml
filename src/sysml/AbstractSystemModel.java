/**
 * 
 */
package sysml;

import gov.nasa.jpl.mbee.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

/**
 * An abstract SystemModel that provides some straightforward implementations of
 * the more abstract methods, such as op(), get(), set(), map(), and filter(),
 * based on more specific methods, like {@link getName(O, V)}, that overlap
 * functionality.
 */
public abstract class AbstractSystemModel< O, C, T, P, N, I, U, R, V, W, CT >
                      implements SystemModel< O, C, T, P, N, I, U, R, V, W, CT > {

    /* (non-Javadoc)
     * @see sysml.SystemModel#op(sysml.SystemModel.Operation, java.util.Collection, java.util.Collection, java.util.Collection, java.lang.Object, java.lang.Boolean)
     */
    @Override
    public Collection< Object >
            op( sysml.SystemModel.Operation operation,
                Collection< sysml.SystemModel.ModelItem > itemTypes,
                Collection< sysml.SystemModel.Item > context,
                Collection< sysml.SystemModel.Item > specifier, U newValue,
                Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#isAllowed(sysml.SystemModel.Operation, java.util.Collection, java.util.Collection, java.util.Collection, sysml.SystemModel.Item, java.lang.Boolean)
     */
    @Override
    public boolean
            isAllowed( sysml.SystemModel.Operation operation,
                       Collection< sysml.SystemModel.ModelItem > itemTypes,
                       Collection< sysml.SystemModel.Item > context,
                       Collection< sysml.SystemModel.Item > specifier,
                       sysml.SystemModel.Item newValue,
                       Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#op(sysml.SystemModel.Operation, java.util.Collection, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object, boolean)
     */
    @Override
    public Collection< Object >
            op( sysml.SystemModel.Operation operation,
                Collection< sysml.SystemModel.ModelItem > itemTypes,
                Collection< C > context, I identifier, N name, V version,
                boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#get(java.util.Collection, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< Object >
            get( Collection< sysml.SystemModel.ModelItem > itemTypes,
                 Collection< C > context, I identifier, N name, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#create(sysml.SystemModel.ModelItem, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< Object > create( sysml.SystemModel.ModelItem item,
                                        Collection< C > context, I identifier,
                                        N name, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#delete(sysml.SystemModel.ModelItem, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< Object > delete( sysml.SystemModel.ModelItem item,
                                        Collection< C > context, I identifier,
                                        N name, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#set(sysml.SystemModel.ModelItem, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< Object > set( sysml.SystemModel.ModelItem item,
                                     Collection< C > context, I identifier,
                                     N name, V version, U newValue ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#setContext(java.util.Collection)
     */
    @Override
    public void setContext( Collection< C > context ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getContext()
     */
    @Override
    public Collection< C > getContext() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#setWorkspace(java.lang.Object)
     */
    @Override
    public void setWorkspace( W workspace ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getWorkspace()
     */
    @Override
    public W getWorkspace() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#setVersion(java.lang.Object)
     */
    @Override
    public void setVersion( V version ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getVersion()
     */
    @Override
    public V getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getObject(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public O getObject( C context, I identifier, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getRootObjects(java.lang.Object)
     */
    @Override
    public Collection< O > getRootObjects( V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getObjectId(java.lang.Object, java.lang.Object)
     */
    @Override
    public I getObjectId( O object, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getName(java.lang.Object, java.lang.Object)
     */
    @Override
    public N getName( O object, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getTypeOf(java.lang.Object, java.lang.Object)
     */
    @Override
    public T getTypeOf( O object, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getType(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public T getType( C context, N name, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getTypeProperties(java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< P > getTypeProperties( T type, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getProperties(java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< P > getProperties( O object, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getProperty(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public P getProperty( O object, N propertyName, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getRelationships(java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< R > getRelationships( O object, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getRelationships(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< R > getRelationships( O object, N relationshipName,
                                             V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getRelated(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< O > getRelated( O object, N relationshipName, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#isDirected(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean isDirected( R relationship, V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getRelatedObjects(java.lang.Object, java.lang.Object)
     */
    @Override
    public O getRelatedObjects( R relationship, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getObjectForRole(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public O getObjectForRole( R relationship, N role, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getSource(java.lang.Object, java.lang.Object)
     */
    @Override
    public O getSource( R relationship, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getTarget(java.lang.Object, java.lang.Object)
     */
    @Override
    public O getTarget( R relationship, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#latestVersion(java.util.Collection)
     */
    @Override
    public V latestVersion( Collection< C > context ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getObjectClass()
     */
    @Override
    public Class< O > getObjectClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getContextClass()
     */
    @Override
    public Class< C > getContextClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getTypeClass()
     */
    @Override
    public Class< T > getTypeClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getPropertyClass()
     */
    @Override
    public Class< P > getPropertyClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getNameClass()
     */
    @Override
    public Class< N > getNameClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getIdentifierClass()
     */
    @Override
    public Class< I > getIdentifierClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getValueClass()
     */
    @Override
    public Class< U > getValueClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getRelationshipClass()
     */
    @Override
    public Class< R > getRelationshipClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getVersionClass()
     */
    @Override
    public Class< V > getVersionClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getWorkspaceClass()
     */
    @Override
    public Class< W > getWorkspaceClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getConstraintClass()
     */
    @Override
    public Class< CT > getConstraintClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asObject(java.lang.Object)
     */
    @Override
    public O asObject( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asContext(java.lang.Object)
     */
    @Override
    public C asContext( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asType(java.lang.Object)
     */
    @Override
    public T asType( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asProperty(java.lang.Object)
     */
    @Override
    public P asProperty( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asName(java.lang.Object)
     */
    @Override
    public N asName( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asIdentifier(java.lang.Object)
     */
    @Override
    public I asIdentifier( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asValue(java.lang.Object)
     */
    @Override
    public U asValue( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asRelationship(java.lang.Object)
     */
    @Override
    public R asRelationship( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asVersion(java.lang.Object)
     */
    @Override
    public V asVersion( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asWorkspace(java.lang.Object)
     */
    @Override
    public W asWorkspace( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#asConstraint(java.lang.Object)
     */
    @Override
    public CT asConstraint( Object o ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#idsAreSettable()
     */
    @Override
    public boolean idsAreSettable() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#namesAreSettable()
     */
    @Override
    public boolean namesAreSettable() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#objectsMayBeChangedForVersion(java.lang.Object)
     */
    @Override
    public boolean objectsMayBeChangedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#typesMayBeChangedForVersion(java.lang.Object)
     */
    @Override
    public boolean typesMayBeChangedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#propertiesMayBeChangedForVersion(java.lang.Object)
     */
    @Override
    public boolean propertiesMayBeChangedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#objectsMayBeCreatedForVersion(java.lang.Object)
     */
    @Override
    public boolean objectsMayBeCreatedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#typesMayBeCreatedForVersion(java.lang.Object)
     */
    @Override
    public boolean typesMayBeCreatedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#propertiesMayBeCreatedForVersion(java.lang.Object)
     */
    @Override
    public boolean propertiesMayBeCreatedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#objectsMayBeDeletedForVersion(java.lang.Object)
     */
    @Override
    public boolean objectsMayBeDeletedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#typesMayBeDeletedForVersion(java.lang.Object)
     */
    @Override
    public boolean typesMayBeDeletedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#propertiesMayBeDeletedForVersion(java.lang.Object)
     */
    @Override
    public boolean propertiesMayBeDeletedForVersion( V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#createObject(java.lang.Object, java.lang.Object)
     */
    @Override
    public O createObject( I identifier, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#setIdentifier(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean setIdentifier( O object, V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#setName(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean setName( O object, V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#setType(java.lang.Object, java.lang.Object)
     */
    @Override
    public boolean setType( O object, V version ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#deleteObject(java.lang.Object, java.lang.Object)
     */
    @Override
    public O deleteObject( I identifier, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#deleteType(java.lang.Object, java.lang.Object)
     */
    @Override
    public T deleteType( O object, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#map(java.util.Collection, sysml.SystemModel.MethodCall, int)
     */
    @Override
    public Collection< Object >
            map( Collection< O > objects,
                 sysml.SystemModel.MethodCall methodCall,
                 int indexOfObjectArgument ) throws InvocationTargetException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#filter(java.util.Collection, sysml.SystemModel.MethodCall, int)
     */
    @Override
    public Collection< Object >
            filter( Collection< O > objects,
                    sysml.SystemModel.MethodCall methodCall,
                    int indexOfObjectArgument )
                                               throws InvocationTargetException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#forAll(java.util.Collection, sysml.SystemModel.MethodCall, int)
     */
    @Override
    public boolean
            forAll( Collection< O > objects,
                    sysml.SystemModel.MethodCall methodCall,
                    int indexOfObjectArgument )
                                               throws InvocationTargetException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#thereExists(java.util.Collection, sysml.SystemModel.MethodCall, int)
     */
    @Override
    public
            boolean
            thereExists( Collection< O > objects,
                         sysml.SystemModel.MethodCall methodCall,
                         int indexOfObjectArgument )
                                                    throws InvocationTargetException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#fold(java.util.Collection, java.lang.Object, sysml.SystemModel.MethodCall, int, int)
     */
    @Override
    public
            Object
            fold( Collection< O > objects, Object initialValue,
                  sysml.SystemModel.MethodCall methodCall,
                  int indexOfObjectArgument, int indexOfPriorResultArgument )
                                                                             throws InvocationTargetException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#sort(java.util.Collection, java.util.Comparator, sysml.SystemModel.MethodCall, int)
     */
    @Override
    public Collection< O >
            sort( Collection< O > objects, Comparator< ? > comparator,
                  sysml.SystemModel.MethodCall methodCall,
                  int indexOfObjectArgument ) throws InvocationTargetException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getDomainConstraint(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public CT getDomainConstraint( O object, V version, W workspace ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#addConstraint(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public void addConstraint( CT constraint, V version, W workspace ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#addDomainConstraint(java.lang.Object, java.lang.Object, java.util.Set, java.lang.Object)
     */
    @Override
    public void addDomainConstraint( CT constraint, V version,
                                     Set< U > valueDomainSet, W workspace ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#addDomainConstraint(java.lang.Object, java.lang.Object, gov.nasa.jpl.mbee.util.Pair, java.lang.Object)
     */
    @Override
    public void
            addDomainConstraint( CT constraint, V version,
                                 Pair< U, U > valueDomainRange, W workspace ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#relaxDomain(java.lang.Object, java.lang.Object, java.util.Set, java.lang.Object)
     */
    @Override
    public void relaxDomain( CT constraint, V version, Set< U > valueDomainSet,
                             W workspace ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#relaxDomain(java.lang.Object, java.lang.Object, gov.nasa.jpl.mbee.util.Pair, java.lang.Object)
     */
    @Override
    public void relaxDomain( CT constraint, V version,
                             Pair< U, U > valueDomainRange, W workspace ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getConstraintsOfElement(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< CT > getConstraintsOfElement( O element, V version,
                                                     W workspace ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getConstraintsOfContext(java.lang.Object)
     */
    @Override
    public Collection< CT > getConstraintsOfContext( C context ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getViolatedConstraintsOfElement(java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< CT > getViolatedConstraintsOfElement( O element,
                                                             V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getViolatedConstraintsOfContext(java.lang.Object)
     */
    @Override
    public Collection< CT > getViolatedConstraintsOfContext( C context ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#setOptimizationFunction(java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public void setOptimizationFunction( Method method, Object... arguments ) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see sysml.SystemModel#getScore()
     */
    @Override
    public Number getScore() {
        // TODO Auto-generated method stub
        return null;
    }

}
