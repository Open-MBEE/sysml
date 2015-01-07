package sysml;

import gov.nasa.jpl.mbee.util.Pair;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SystemModelImpl< N, I > extends
                                     AbstractSystemModel< Element, Object, Element, Property, N, I, Element, Element, Element, Workspace, Element > {

    protected static SystemModelImpl instance = new SystemModelImpl<>();
    Map< I, Workspace > workspaces;

    public SystemModelImpl getInstance() {
        return instance;
    }

    //Map< I, Element > elements;

    public SystemModelImpl() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isDirected( Element relationship ) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection< Element > getRelatedElements( Element relationship ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getElementForRole( Element relationship, N role ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getSource( Element relationship ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTarget( Element relationship ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Element > getElementClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Object > getContextClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Element > getTypeClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Property > getPropertyClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< N > getNameClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< I > getIdentifierClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Element > getValueClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Element > getRelationshipClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Element > getVersionClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Workspace > getWorkspaceClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< Element > getConstraintClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< ? extends Element > getViewClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class< ? extends Element > getViewpointClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element createConstraint( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element createElement( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public I createIdentifier( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public N createName( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Property createProperty( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element createRelationship( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element createType( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element createValue( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element createVersion( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element createView( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element createViewpoint( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Workspace createWorkspace( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object delete( Object object ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getConstraint( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithElement( Object context,
                                                           Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithIdentifier( Object context,
                                                              I specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithName( Object context,
                                                        N specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithProperty( Object context,
                                                            Property specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getConstraintWithRelationship( Object context, Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithType( Object context,
                                                        Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithValue( Object context,
                                                         Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithVersion( Object context,
                                                           Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithView( Object context,
                                                        Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getConstraintWithViewpoint( Object context,
                                                             Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getConstraintWithWorkspace( Object context, Workspace specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithConstraint( Object context,
                                                           Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithIdentifier( Object context,
                                                           I specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getElementWithName( Object context, N specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithProperty( Object context,
                                                         Property specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithRelationship( Object context,
                                                             Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithType( Object context,
                                                     Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithValue( Object context,
                                                      Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithVersion( Object context,
                                                        Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithView( Object context,
                                                     Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithViewpoint( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getElementWithWorkspace( Object context,
                                                          Workspace specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< N > getName( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< I > getIdentifier( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property >
            getProperty( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property > getPropertyWithConstraint( Object context,
                                                             Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property > getPropertyWithElement( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property > getPropertyWithIdentifier( Object context,
                                                             I specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property >
            getPropertyWithRelationship( Object context, Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property > getPropertyWithType( Object context,
                                                       Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property > getPropertyWithValue( Object context,
                                                        Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property > getPropertyWithVersion( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property > getPropertyWithView( Object context,
                                                       Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property > getPropertyWithViewpoint( Object context,
                                                            Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Property >
            getPropertyWithWorkspace( Object context, Workspace specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getRelationship( Object context,
                                                  Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getRelationshipWithConstraint( Object context, Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getRelationshipWithElement( Object context,
                                                             Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getRelationshipWithIdentifier( Object context,
                                                                I specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getRelationshipWithName( Object context,
                                                          N specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getRelationshipWithProperty( Object context, Property specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getRelationshipWithType( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getRelationshipWithValue( Object context,
                                                           Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getRelationshipWithVersion( Object context,
                                                             Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getRelationshipWithView( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getRelationshipWithViewpoint( Object context, Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getRelationshipWithWorkspace( Object context, Workspace specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getType( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getTypeString( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithConstraint( Object context,
                                                        Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithElement( Object context,
                                                     Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithIdentifier( Object context,
                                                        I specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithName( Object context, N specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithProperty( Object context,
                                                      Property specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithRelationship( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithValue( Object context,
                                                   Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithVersion( Object context,
                                                     Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithView( Object context,
                                                  Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithViewpoint( Object context,
                                                       Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getTypeWithWorkspace( Object context,
                                                       Workspace specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValue( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithConstraint( Object context,
                                                         Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithElement( Object context,
                                                      Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithIdentifier( Object context,
                                                         I specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithName( Object context, N specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithProperty( Object context,
                                                       Property specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithRelationship( Object context,
                                                           Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithType( Object context,
                                                   Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithVersion( Object context,
                                                      Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithView( Object context,
                                                   Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithViewpoint( Object context,
                                                        Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getValueWithWorkspace( Object context,
                                                        Workspace specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getVersion( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getView( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getViewpoint( Object context, Object specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithConstraint( Object context,
                                                             Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithElement( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithIdentifier( Object context,
                                                             I specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithName( Object context,
                                                       N specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithProperty( Object context,
                                                           Property specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getViewpointWithRelationship( Object context, Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithType( Object context,
                                                       Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithValue( Object context,
                                                        Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithVersion( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewpointWithView( Object context,
                                                       Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getViewpointWithWorkspace( Object context, Workspace specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithConstraint( Object context,
                                                        Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithElement( Object context,
                                                     Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithIdentifier( Object context,
                                                        I specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithName( Object context, N specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithProperty( Object context,
                                                      Property specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithRelationship( Object context,
                                                          Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithType( Object context,
                                                  Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithValue( Object context,
                                                   Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithVersion( Object context,
                                                     Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithViewpoint( Object context,
                                                       Element specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element > getViewWithWorkspace( Object context,
                                                       Workspace specifier ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Workspace > getWorkspace( Object context ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object set( Object object, Object specifier, Element value ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean fixConstraintViolations( Element element, Element version ) {
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
    public Element getDomainConstraint( Element element, Element version,
                                        Workspace workspace ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addConstraint( Element constraint, Element version,
                               Workspace workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addDomainConstraint( Element constraint, Element version,
                                     Set< Element > valueDomainSet,
                                     Workspace workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addDomainConstraint( Element constraint, Element version,
                                     Pair< Element, Element > valueDomainRange,
                                     Workspace workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void
            relaxDomain( Element constraint, Element version,
                         Set< Element > valueDomainSet, Workspace workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public void relaxDomain( Element constraint, Element version,
                             Pair< Element, Element > valueDomainRange,
                             Workspace workspace ) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection< Element > getConstraintsOfElement( Element element,
                                                          Element version,
                                                          Workspace workspace ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection< Element >
            getViolatedConstraintsOfElement( Element element, Element version ) {
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
        // TODO Auto-generated method stub

    }

}
