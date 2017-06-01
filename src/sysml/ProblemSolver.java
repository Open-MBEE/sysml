package sysml;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import gov.nasa.jpl.mbee.util.Pair;

public interface ProblemSolver<E, C, T, P, N, I, U, R, V, W, CT> {
    
    // support for problem solving

    // example problem solving session
    //
    // f = getElementWithName( "MyFolder" ).iterator().next();
    // x = getElementWithName( f, "x" ).iterator().next();
    // y = getElementWithName( f, "y" ).iterator().next();
    // c = createConstraint( f );
    // fixConstraintViolations(c);
    // System.out.println(y.toString());


//    // problem solving session
//    // TODO -- replace sessions with model workspaces
//    // TODO -- otherwise, create a Session class that would include these session functions, which would return Sessions.
//  /**
//   * Create a new problem solving session.
//   * <p>
//   * TODO -- REVIEW -- this is similar to a workspace where hypothetical model
//   * changes can be made -- this should be implemented for the model as a
//   * whole, and problem solving sessions will be unnecessary.
//   *
//   * @param suggestedSessionId
//   * @return the id for a new problem solving session, the one suggested if
//   *         possible
//   */
//    public I createNewSolverSession(I suggestedSessionId);
//    public I copySolverSession( I idOfSessionToCopy );
//    public I switchSolverSession( I idOfSessionToWhichToSwitch );
//    public I deleteSolverSession( I idOfSessionToWhichToSwitch );

//    // Constraint CRUD
//    public Constraint addConstraint( CE constraintElement, V version, W workspace );
//    public Constraint addDomainConstraint( CE constraintElement, V version, Set<U> valueDomainSet, W workspace );
//    public Constraint addDomainConstraint( CE constraintElement, V version, Pair<U,U> valueDomainRange, W workspace );
//    public Constraint relaxDomain( CE constraintElement, V version, Set<U> valueDomainSet, W workspace );
//    public Constraint relaxDomain( CE constraintElement, V version, Pair<U,U> valueDomainRange, W workspace );
//    public Collection<CE> getConstraintElementsOfElement( E element, V version, W workspace );
//    public Collection<CE> getConstraintElementsOfContext( C context );
//    public Collection<Constraint> getConstraintsOfElement( E element, V version, W workspace );
//    public Collection<Constraint> getConstraintsOfContext( C context );
//    public void setOptimizationFunction( Method method, Object... arguments ); // REVIEW -- should these be elements?
//    public Collection<CE> getViolatedConstraintElementsOfElement( E element, V version );
//    public Collection<CE> getViolatedConstraintElementsOfContext( C context );
//    public Collection<Constraint> getViolatedConstraintsOfElement( E element, V version );
//    public Collection<Constraint> getViolatedConstraintsOfContext( C context );
//    public Number getScore();

    // Constraint CRUD
    public CT getDomainConstraint( E element, V version, W workspace );
    // TODO -- easier i/f for adding constraint that
    public void addConstraint( CT constraint, V version, W workspace );
    public void addDomainConstraint( CT constraint, V version, Set<U> valueDomainSet, W workspace );
    public void addDomainConstraint( CT constraint, V version, Pair<U,U> valueDomainRange, W workspace );
    public void relaxDomain( CT constraint, V version, Set<U> valueDomainSet, W workspace );
    public void relaxDomain( CT constraint, V version, Pair<U,U> valueDomainRange, W workspace );
    public Collection<CT> getConstraintsOfElement( E element, V version, W workspace );
    //public Collection<CT> getConstraintsOfContext( C context );
    public Collection<CT> getViolatedConstraintsOfElement( E element, V version );
    //public Collection<CT> getViolatedConstraintsOfContext( C context );
    public void setOptimizationFunction( Method method, Object... arguments ); // REVIEW -- should these be elements? should the function be an interface type (add F to ModelItem)?
    public Number getScore();
    //public <B> Number getScore(B objective); // TODO -- add B to class parameters?
    // TODO -- add other functions? like for delete? update?

    // TODO -- invoke solver/fix
    public boolean fixConstraintViolations( E element, V version );

}
