package sysml.impl;

import gov.nasa.jpl.mbee.util.Pair;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import sysml.AbstractSystemModel;

public class SystemModelImpl
        extends AbstractSystemModel< ElementImpl, Object, ElementImpl, PropertyImpl, String, String, ElementImpl, ElementImpl, VersionImpl, WorkspaceImpl, ElementImpl > {

    //protected static SystemModelImpl instance = new SystemModelImpl();

    Map< String, WorkspaceImpl > workspaces =
            new LinkedHashMap< String, WorkspaceImpl >();

//    public SystemModelImpl getInstance() {
//        return instance;
//    }

    //Map< I, Element > elements;

    public SystemModelImpl() {
        super();
    }

    @Override
    public boolean isDirected( ElementImpl relationship ) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection< ElementImpl >
            getRelatedElements( ElementImpl relationship ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementForRole( ElementImpl relationship, String role ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getSource( ElementImpl relationship ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getTarget( ElementImpl relationship ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< ElementImpl > getElementClass() {
        return ElementImpl.class;
    }

    @Override
    public Class< Object > getContextClass() {
        return Object.class;
    }

    @Override
    public Class< ElementImpl > getTypeClass() {
        return ElementImpl.class;
    }

    @Override
    public Class< PropertyImpl > getPropertyClass() {
        return PropertyImpl.class;
    }

    @Override
    public Class< String > getNameClass() {
        return String.class;
    }

    @Override
    public Class< String > getIdentifierClass() {
        return String.class;
    }

    @Override
    public Class< ElementImpl > getValueClass() {
        return ElementImpl.class;
    }

    @Override
    public Class< ElementImpl > getRelationshipClass() {
        return ElementImpl.class;
    }

    @Override
    public Class< VersionImpl > getVersionClass() {
        return VersionImpl.class;
    }

    @Override
    public Class< WorkspaceImpl > getWorkspaceClass() {
        return WorkspaceImpl.class;
    }

    @Override
    public Class< ElementImpl > getConstraintClass() {
       return ElementImpl.class;
    }

    @Override
    public Class< ? extends ElementImpl > getViewClass() {
        return ElementImpl.class;
    }

    @Override
    public Class< ? extends ElementImpl > getViewpointClass() {
        return ElementImpl.class;
    }

    @Override
    public ElementImpl createConstraint( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ElementImpl createElement( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String createIdentifier( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String createName( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PropertyImpl createProperty( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ElementImpl createRelationship( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ElementImpl createType( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ElementImpl createValue( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VersionImpl createVersion( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ElementImpl createView( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ElementImpl createViewpoint( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorkspaceImpl createWorkspace( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object delete( Object object ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getConstraint( Object context,
                                                    Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getConstraintWithElement( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getConstraintWithIdentifier( Object context, String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getConstraintWithName( Object context,
                                                            String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getConstraintWithProperty( Object context, PropertyImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< ElementImpl >
            getConstraintWithRelationship( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getConstraintWithType( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getConstraintWithValue( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getConstraintWithVersion( Object context, VersionImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getConstraintWithView( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getConstraintWithViewpoint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< ElementImpl >
            getConstraintWithWorkspace( Object context, WorkspaceImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementWithConstraint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementWithIdentifier( Object context, String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getElementWithName( Object context,
                                                         String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementWithProperty( Object context, PropertyImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementWithRelationship( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getElementWithType( Object context,
                                                         ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementWithValue( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementWithVersion( Object context, VersionImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getElementWithView( Object context,
                                                         ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementWithViewpoint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getElementWithWorkspace( Object context, WorkspaceImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< String > getName( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< String > getIdentifier( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl > getProperty( Object context,
                                                   Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithConstraint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithElement( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithIdentifier( Object context, String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithRelationship( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithType( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithValue( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithVersion( Object context, VersionImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithView( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithViewpoint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< PropertyImpl >
            getPropertyWithWorkspace( Object context, WorkspaceImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getRelationship( Object context,
                                                      Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< ElementImpl >
            getRelationshipWithConstraint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getRelationshipWithElement( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getRelationshipWithIdentifier( Object context, String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getRelationshipWithName( Object context,
                                                              String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< ElementImpl >
            getRelationshipWithProperty( Object context, PropertyImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getRelationshipWithType( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getRelationshipWithValue( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getRelationshipWithVersion( Object context, VersionImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getRelationshipWithView( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< ElementImpl >
            getRelationshipWithViewpoint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getRelationshipWithWorkspace( Object context,
                                          WorkspaceImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getType( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getTypeString( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getTypeWithConstraint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getTypeWithElement( Object context,
                                                         ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getTypeWithIdentifier( Object context,
                                                            String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getTypeWithName( Object context,
                                                      String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getTypeWithProperty( Object context, PropertyImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getTypeWithRelationship( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getTypeWithValue( Object context,
                                                       ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getTypeWithVersion( Object context,
                                                         VersionImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getTypeWithView( Object context,
                                                      ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getTypeWithViewpoint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getTypeWithWorkspace( Object context, WorkspaceImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getValue( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getValueWithConstraint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getValueWithElement( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getValueWithIdentifier( Object context,
                                                             String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getValueWithName( Object context,
                                                       String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getValueWithProperty( Object context, PropertyImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getValueWithRelationship( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getValueWithType( Object context,
                                                       ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getValueWithVersion( Object context, VersionImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getValueWithView( Object context,
                                                       ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getValueWithViewpoint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getValueWithWorkspace( Object context, WorkspaceImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< VersionImpl > getVersion( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getView( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getViewpoint( Object context,
                                                   Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithConstraint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithElement( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithIdentifier( Object context, String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getViewpointWithName( Object context,
                                                           String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithProperty( Object context, PropertyImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public
            Collection< ElementImpl >
            getViewpointWithRelationship( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithType( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithValue( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithVersion( Object context, VersionImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithView( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewpointWithWorkspace( Object context, WorkspaceImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewWithConstraint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getViewWithElement( Object context,
                                                         ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getViewWithIdentifier( Object context,
                                                            String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getViewWithName( Object context,
                                                      String specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewWithProperty( Object context, PropertyImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewWithRelationship( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getViewWithType( Object context,
                                                      ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getViewWithValue( Object context,
                                                       ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl > getViewWithVersion( Object context,
                                                         VersionImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewWithViewpoint( Object context, ElementImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViewWithWorkspace( Object context, WorkspaceImpl specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< WorkspaceImpl > getWorkspace( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    // FIXME -- TODO -- This should be defined in the SystemModel interface!
    public WorkspaceImpl getWorkspaceWithId( String id ) {
        return workspaces.get( id );
    }

    @Override
    public Object set( Object object, Object specifier, ElementImpl value ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean fixConstraintViolations( ElementImpl element,
                                            VersionImpl version ) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean idsAreWritable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean namesAreWritable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean versionsAreWritable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ElementImpl getDomainConstraint( ElementImpl element,
                                            VersionImpl version,
                                            WorkspaceImpl workspace ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addConstraint( ElementImpl constraint, VersionImpl version,
                               WorkspaceImpl workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addDomainConstraint( ElementImpl constraint,
                                     VersionImpl version,
                                     Set< ElementImpl > valueDomainSet,
                                     WorkspaceImpl workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public
            void
            addDomainConstraint( ElementImpl constraint,
                                 VersionImpl version,
                                 Pair< ElementImpl, ElementImpl > valueDomainRange,
                                 WorkspaceImpl workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void relaxDomain( ElementImpl constraint, VersionImpl version,
                             Set< ElementImpl > valueDomainSet,
                             WorkspaceImpl workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void relaxDomain( ElementImpl constraint, VersionImpl version,
                             Pair< ElementImpl, ElementImpl > valueDomainRange,
                             WorkspaceImpl workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection< ElementImpl >
            getConstraintsOfElement( ElementImpl element, VersionImpl version,
                                     WorkspaceImpl workspace ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< ElementImpl >
            getViolatedConstraintsOfElement( ElementImpl element,
                                             VersionImpl version ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setOptimizationFunction( Method method, Object... arguments ) {
        // TODO Auto-generated method stub

    }

    @Override
    public Number getScore() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main( String[] args ) {
        System.out.println("Hello, world.");
    }

}
