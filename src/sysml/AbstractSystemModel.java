/**
 * 
 */
package sysml;

import gov.nasa.jpl.mbee.util.ClassUtils;
import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.MethodCall;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
     * @see SystemModel#op(SystemModel.Operation, java.util.Collection, java.util.Collection, java.util.Collection, java.lang.Object, java.lang.Boolean)
     */
    @Override
    public Collection< Object > op( SystemModel.Operation operation,
                                    Collection< SystemModel.ModelItem > itemTypes,
                                    Collection< SystemModel.Item > contexts,
                                    Collection< SystemModel.Item > specifiers,
                                    U newValue,
                                    Boolean failForMultipleItemMatches ) {
        if ( operation == null ) {
            Debug.error( "Operation must not be null!" );
            return Collections.emptyList();
        }
        SystemModel.Item newValueItem = new Item( newValue, ModelItem.VALUE );
        if ( !isAllowed( operation, itemTypes, contexts, specifiers, newValueItem, failForMultipleItemMatches ) ) {
            return Collections.emptyList();
        }
        Collection< Object > results = Utils.newList();
        Collection< Object > res = null;
        if ( Utils.isNullOrEmpty( contexts ) ) {
//            SystemModel.Item[] oneNullArg = new SystemModel.Item[1];
//            oneNullArg[0] = null;
            contexts = Utils.newListWithOneNull();
        }
        if ( Utils.isNullOrEmpty( itemTypes ) ) {
            itemTypes = Utils.newListWithOneNull();
        }
        if ( Utils.isNullOrEmpty( specifiers ) ) {
            specifiers = Utils.newListWithOneNull();
        }
        boolean someResultsWereNull = false;
        boolean allResultsWereNull = true;
        for ( SystemModel.Item context : contexts ) {
            //SystemModel.ModelItem contextType = context == null ? null : context.kind;  
            for ( SystemModel.Item specifier : specifiers ) {
                //SystemModel.ModelItem specifierType = specifier == null ? null : specifier.kind;  
                for ( SystemModel.ModelItem itemType : itemTypes ) {
                    try {
                        MethodCall mc =
                                getMethodCall( this, operation, itemType,
                                               context, specifier, newValue, false );
                        Pair< Boolean, Object > p = mc.invoke( true );
                        if ( p.first ) {
                            res = null;
                            if ( p.second instanceof Collection ) {
                                res = (Collection< Object >)p.second;
                            }
                            if ( res == null ) {
                                someResultsWereNull = true;
                                // TODO -- should return null here?
                                // if the method call invoked properly, but
                                // returned null, then the itemType, context,
                                // and specifier are incompatible.
                                // return null;
                            } else {
                                allResultsWereNull = false;
                                results.addAll( res );
                            }
                        }
                        if ( Utils.isTrue( failForMultipleItemMatches )
                             && results.size() > 1 ) return null;
                    } catch (Throwable e ) {
                        // ignore!
                    }
                }
            }
        }
        if ( allResultsWereNull && someResultsWereNull ) return null;
        return results;
//        switch( operation ) {
//            case CREATE:
//                return create(itemTypes, context, specifier, newValue, failForMultipleItemMatches );
//            case DELETE:
//                return delete(itemTypes, context, specifier, failForMultipleItemMatches );
//            case GET:
//                return get(itemTypes, context, specifier, failForMultipleItemMatches );
//            case READ:
//                return get(itemTypes, context, specifier, failForMultipleItemMatches );
//            case SET:
//                return set(itemTypes, context, specifier, newValue, failForMultipleItemMatches );
//            case UPDATE:
//                return set(itemTypes, context, specifier, newValue, failForMultipleItemMatches );
//            default:
//                Debug.error( "Unexpected SystemModel.Operation: " + operation );
//        }
//        return null;
    }


    /* (non-Javadoc)
     * @see SystemModel#isAllowed(SystemModel.Operation, java.util.Collection, java.util.Collection, java.util.Collection, SystemModel.Item, java.lang.Boolean)
     */
    @Override
    public boolean
            isAllowed( SystemModel.Operation operation,
                       Collection< SystemModel.ModelItem > itemTypes,
                       Collection< SystemModel.Item > context,
                       Collection< SystemModel.Item > specifier,
                       SystemModel.Item newValue,
                       Boolean failForMultipleItemMatches ) {
        switch( operation ) {
            case CREATE:
                return mayCreate(itemTypes, context, specifier, newValue, failForMultipleItemMatches );
            case DELETE:
                return mayDelete(itemTypes, context, specifier, failForMultipleItemMatches );
            case GET:
                return mayGet(itemTypes, context, specifier, failForMultipleItemMatches );
            case READ:
                return mayGet(itemTypes, context, specifier, failForMultipleItemMatches );
            case SET:
                return maySet(itemTypes, context, specifier, newValue, failForMultipleItemMatches );
            case UPDATE:
                return maySet(itemTypes, context, specifier, newValue, failForMultipleItemMatches );
            default:
                Debug.error( "Unexpected SystemModel.Operation: " + operation );
        }
        return false;
    }

    /**
     * @param operation
     * @return a lower case name for the operation using "get" for READ and "set" for UPDATE.
     */
    public static String getOperationName(SystemModel.Operation operation ) {
        switch( operation ) {
            case CREATE:
            case DELETE:
            case GET:
            case SET:
                return operation.toString().toLowerCase();
            case READ:
                return getOperationName( Operation.GET );
            case UPDATE:
                return getOperationName( Operation.SET );
            default:
                Debug.error( "Unexpected SystemModel.Operation: " + operation );
        }
        return null;
    }
    
    /* (non-Javadoc)
     * @see SystemModel#op(SystemModel.Operation, java.util.Collection, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object, boolean)
     */
    @Override
    public Collection< Object >
            op( SystemModel.Operation operation,
                Collection< SystemModel.ModelItem > itemTypes,
                Collection< C > context, I identifier, N name, V version,
                boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return null;
    }

    protected static String toCamelCase( String s ) {
        if ( s == null ) return null;
        if ( s.isEmpty() || !Character.isAlphabetic( s.codePointAt( 0 ) ) )
            return s;
        char prefix = s.charAt( 0 );
        String suffix = s.substring( 1 ).toLowerCase();
        prefix = Character.toUpperCase( prefix );
        return prefix + suffix;
    }

    /**
     * @param operation
     * @param itemType
     * @param contextType
     * @param specifierType
     * @param complain
     * @return the specific Method in this class for performing the operation with the given arguments.   
     */
    public static String getMethodName( SystemModel.Operation operation,
                                        SystemModel.ModelItem itemType,
                                        SystemModel.ModelItem contextType,
                                        SystemModel.ModelItem specifierType,
                                        boolean complain ) {
        if ( operation == null ) {
            Debug.error( complain, complain, "Trying to pass in null operation!" );
            return null;
        }
        String opName = getOperationName( operation );
        String contextTypeName;

        String itemTypeName;
        if ( itemType == null ) {
            itemTypeName = "";
        } else {
            itemTypeName = toCamelCase( itemType.toString() );
        }
        
        String specifierTypeName;
        if ( specifierType == null ) {
            if ( itemType == null ) {
                Debug.error( complain, complain, "Either itemType or specifierType may be null, but not both!" );
                return null;
            }
            specifierTypeName = "";
        } else {
            specifierTypeName = toCamelCase( specifierType.toString() );
            if ( itemType == null ) {
                itemTypeName = specifierTypeName;
            }
        }
        
        if ( contextType == null ) {
            contextTypeName = "";
        } else {
            contextTypeName = toCamelCase( contextType.toString() );
        }
        
        // construct method call name
        String by = "";
        if ( itemType != null && specifierType != null
             && specifierType != ModelItem.NAME && specifierType != itemType ) {
            by = "By" + specifierTypeName;
        }
        String callName = opName + itemTypeName + ( contextType == null ? "" : "Of" + contextTypeName ) + by;
        return callName;
    }
    
    private String getMethodNamePrepositionForContext( SystemModel.ModelItem targetType,
                                                       SystemModel.ModelItem contextType ) {
        return "Of";
    }

    private String getMethodNamePrepositionForSpecifier( SystemModel.ModelItem specifierType,
                                                         SystemModel.ModelItem contextType ) {
        return "By";
    }
    
    /**
     * @param systemModel
     * @param operation
     * @param itemType
     * @param context
     * @param specifier
     * @param newValue
     * @param complain
     * @return the specific Method in this class for performing the operation with the given arguments.
     */
    public static <VAL> MethodCall getMethodCall( AbstractSystemModel< ?, ?, ?, ?, ?, ?, VAL, ?, ?, ?, ? > systemModel,
                                                  SystemModel.Operation operation,
                                                  SystemModel.ModelItem itemType,
                                                  SystemModel.Item context,
                                                  SystemModel.Item specifier,
                                                  VAL newValue,
                                                  boolean complain ) {
        
        if ( operation == null ) {
            Debug.error( complain, complain, "Trying to pass in null operation!" );
            return null;
        }

        ArrayList< Class< ? > > argTypeList = new ArrayList< Class< ? > >();
        ArrayList< Object > argList = new ArrayList< Object >();

        String opName = getOperationName( operation );

        // get name and args for itemType
        String itemTypeName;
        if ( itemType == null ) {
            itemTypeName = "";
        } else {
            itemTypeName = toCamelCase( itemType.toString() );
            //argTypeList.add( getClass( itemType ) );
        }
        
        // get name and args for specifier (and for the item as the specifier if itemType == null)
        SystemModel.ModelItem specifierType = specifier == null ? null : specifier.kind;
        String specifierTypeName;
        if ( specifierType == null ) {
            if ( itemType == null ) {
                Debug.error( complain, complain, "Either itemType or specifierType may be null, but not both!" );
            }
            specifierTypeName = "";
        } else {
            specifierTypeName = toCamelCase( specifierType.toString() );
            if ( itemType == null ) {
                itemTypeName = specifierTypeName;
            }
            argTypeList.add( systemModel.getClass( specifierType ) );
            argList.add( specifier.obj );
        }
        
        // get name and args for context
        SystemModel.ModelItem contextType = context == null ? null : context.kind;  
        String contextTypeName;
        if ( contextType == null ) {
            contextTypeName = "";
        } else {
            contextTypeName = toCamelCase( contextType.toString() );
            argTypeList.add( systemModel.getClass( contextType ) );
            argList.add( context.obj );
        }
        
        if ( newValue != null ) {
            @SuppressWarnings( "unchecked" ) // the newValue parameter's type is U, so this is safe
            Class< ? extends VAL > nvClass = (Class< ? extends VAL >)newValue.getClass();
            Class< ? > itemClass = systemModel.getClass( itemType );
            Collection< Class<?> > classes =  Utils.newList( nvClass, itemClass );
            Class<?> cls = ClassUtils.leastUpperBoundSuperclass( classes );
            argTypeList.add( cls );
        }
        
        // construct method call name
        String by = "";
        if ( itemType != null && specifierType != null
             && specifierType != ModelItem.NAME && specifierType != itemType ) {
            by = "By" + specifierTypeName;
        }
        String callName = opName + itemTypeName + ( contextType == null ? "" : "Of" + contextTypeName ) + by;

        // FIXME -- delete debug code!
        boolean wasOn = Debug.isOn();
        Debug.turnOn();
        System.out.println( callName );
        if ( !wasOn ) Debug.turnOff();

        // put the argument types into an array
        Class< ? >[] argTypes = new Class<?>[ argTypeList.size() ];
        argTypeList.toArray( argTypes );
        
        // try to lookup the Method from the callName and argTypes using reflection 
        Method method = ClassUtils.getMethodForArgTypes( systemModel.getClass(), callName , argTypes , complain );
        if ( method == null ) {
            method = ClassUtils.getMethodForArgs( systemModel.getClass(), callName, argList );
        }
        if ( method == null && by.length() > 0 ) {
            callName = opName + itemTypeName + ( contextType == null ? "" : "Of" + contextTypeName );
            method = ClassUtils.getMethodForArgTypes( systemModel.getClass(), callName , argTypes , complain );
            if ( method == null ) {
                method = ClassUtils.getMethodForArgs( systemModel.getClass(), callName, argList );
            }
        }
        // fail and return null if the Method could not be found
        if ( method == null ) return null;
        
        // put the arguments into an array
        Object[] arguments = new Object[ argList.size() ];
        argList.toArray( arguments );
        
        // construct the method call
        return new MethodCall( systemModel, method, arguments );
    }
    
    public Collection< Object > get( Collection< SystemModel.ModelItem > itemTypes,
                                     Collection< SystemModel.Item > contexts,
                                     Collection< SystemModel.Item > specifiers,
                                     Boolean failForMultipleItemMatches ) {
        Collection< Object > results = Utils.newList();
        Collection< Object > res = null;
        if ( Utils.isNullOrEmpty( contexts ) ) {
//            SystemModel.Item[] oneNullArg = new SystemModel.Item[1];
//            oneNullArg[0] = null;
            contexts = Utils.newListWithOneNull();
        }
        if ( Utils.isNullOrEmpty( itemTypes ) ) {
            itemTypes = Utils.newListWithOneNull();
        }
        if ( Utils.isNullOrEmpty( specifiers ) ) {
            specifiers = Utils.newListWithOneNull();
        }
        boolean someResultsWereNull = false;
        boolean allResultsWereNull = true;
        for ( SystemModel.Item context : contexts ) {
            //SystemModel.ModelItem contextType = context == null ? null : context.kind;  
            for ( SystemModel.Item specifier : specifiers ) {
                //SystemModel.ModelItem specifierType = specifier == null ? null : specifier.kind;  
                for ( SystemModel.ModelItem itemType : itemTypes ) {
                    try {
                        MethodCall mc =
                                getMethodCall( this, Operation.GET, itemType,
                                               context, specifier, null, false );
                        Pair< Boolean, Object > p = mc.invoke( true );
                        if ( p.first ) {
                            res = null;
                            if ( p.second instanceof Collection ) {
                                res = (Collection< Object >)p.second;
                            }
                            if ( res == null ) {
                                someResultsWereNull = true;
                                // TODO -- should return null here?
                                // if the method call invoked properly, but
                                // returned null, then the itemType, context,
                                // and specifier are incompatible.
                                // return null;
                            } else {
                                allResultsWereNull = false;
                                results.addAll( res );
                            }
                        }
                        if ( Utils.isTrue( failForMultipleItemMatches )
                             && results.size() > 1 ) return null;
                    } catch (Throwable e ) {
                        // ignore!
                    }
                }
            }
        }
        if ( allResultsWereNull && someResultsWereNull ) return null;
        return results;
    }

//    private Collection< Object >
//            getWorkspace( Collection< sysml.SystemModel.Item > context,
//                          Collection< sysml.SystemModel.Item > specifier,
//                          Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getViewpoint( Collection< sysml.SystemModel.Item > context,
//                          Collection< sysml.SystemModel.Item > specifier,
//                          Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getView( Collection< sysml.SystemModel.Item > context,
//                     Collection< sysml.SystemModel.Item > specifier,
//                     Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getVersion( Collection< sysml.SystemModel.Item > context,
//                        Collection< sysml.SystemModel.Item > specifier,
//                        Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getValue( Collection< sysml.SystemModel.Item > context,
//                      Collection< sysml.SystemModel.Item > specifier,
//                      Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getType( Collection< sysml.SystemModel.Item > context,
//                     Collection< sysml.SystemModel.Item > specifier,
//                     Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getRelationship( Collection< sysml.SystemModel.Item > context,
//                             Collection< sysml.SystemModel.Item > specifier,
//                             Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getProperty( Collection< sysml.SystemModel.Item > context,
//                         Collection< sysml.SystemModel.Item > specifier,
//                         Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getObject( Collection< sysml.SystemModel.Item > context,
//                       Collection< sysml.SystemModel.Item > specifier,
//                       Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getName( Collection< sysml.SystemModel.Item > context,
//                     Collection< sysml.SystemModel.Item > specifier,
//                     Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getIdentifier( Collection< sysml.SystemModel.Item > context,
//                           Collection< sysml.SystemModel.Item > specifier,
//                           Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//
//    private Collection< Object >
//            getContext( Collection< sysml.SystemModel.Item > context,
//                        Collection< sysml.SystemModel.Item > specifier,
//                        Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public Collection< Object >
//            getConstraints( Collection< SystemModel.Item > context,
//                            Collection< SystemModel.Item > specifier,
//                            Boolean failForMultipleItemMatches ) {
//        // TODO Auto-generated method stub
//        return null;
//    }

    public Collection< Object >
            create( Collection< SystemModel.ModelItem > itemTypes,
                    Collection< SystemModel.Item > context,
                    Collection< SystemModel.Item > specifier, U newValue,
                    Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return null;
    }

    public Collection< Object >
            set( Collection< SystemModel.ModelItem > itemTypes,
                 Collection< SystemModel.Item > context,
                 Collection< SystemModel.Item > specifier, U newValue,
                 Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return null;
    }

    public Collection< Object >
            delete( Collection< SystemModel.ModelItem > itemTypes,
                    Collection< SystemModel.Item > context,
                    Collection< SystemModel.Item > specifier,
                    Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean maySet( Collection< sysml.SystemModel.ModelItem > itemTypes,
                           Collection< sysml.SystemModel.Item > context,
                           Collection< sysml.SystemModel.Item > specifier,
                           sysml.SystemModel.Item newValue,
                           Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean mayGet( Collection< sysml.SystemModel.ModelItem > itemTypes,
                           Collection< sysml.SystemModel.Item > context,
                           Collection< sysml.SystemModel.Item > specifier,
                           Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean
            mayDelete( Collection< sysml.SystemModel.ModelItem > itemTypes,
                       Collection< sysml.SystemModel.Item > context,
                       Collection< sysml.SystemModel.Item > specifier,
                       Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean
            mayCreate( Collection< sysml.SystemModel.ModelItem > itemTypes,
                       Collection< sysml.SystemModel.Item > context,
                       Collection< sysml.SystemModel.Item > specifier,
                       sysml.SystemModel.Item newValue,
                       Boolean failForMultipleItemMatches ) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see SystemModel#get(java.util.Collection, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< Object >
            get( Collection< SystemModel.ModelItem > itemTypes,
                 Collection< C > context, I identifier, N name, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#create(SystemModel.ModelItem, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< Object > create( SystemModel.ModelItem item,
                                        Collection< C > context, I identifier,
                                        N name, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#delete(SystemModel.ModelItem, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< Object > delete( SystemModel.ModelItem item,
                                        Collection< C > context, I identifier,
                                        N name, V version ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#set(SystemModel.ModelItem, java.util.Collection, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public Collection< Object > set( SystemModel.ModelItem item,
                                     Collection< C > context, I identifier,
                                     N name, V version, U newValue ) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#setContext(java.util.Collection)
     */
    @Override
    public abstract void setContext( Collection< C > context );

    /* (non-Javadoc)
     * @see SystemModel#getContext()
     */
    @Override
    public abstract Collection< C > getContext();

    /* (non-Javadoc)
     * @see SystemModel#setWorkspace(java.lang.Object)
     */
    @Override
    public abstract void setWorkspace( W workspace );

    /* (non-Javadoc)
     * @see SystemModel#getWorkspace()
     */
    @Override
    public abstract W getWorkspace();

    /* (non-Javadoc)
     * @see SystemModel#setVersion(java.lang.Object)
     */
    @Override
    public abstract void setVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#getVersion()
     */
    @Override
    public abstract V getVersion();

    /* (non-Javadoc)
     * @see SystemModel#getObject(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract O getObject( C context, I identifier, V version );

    /* (non-Javadoc)
     * @see SystemModel#getRootObjects(java.lang.Object)
     */
    @Override
    public abstract Collection< O > getRootObjects( V version );

    /* (non-Javadoc)
     * @see SystemModel#getObjectId(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract I getObjectId( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#getName(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract N getName( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#getTypeOf(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract T getTypeOf( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#getType(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract T getType( C context, N name, V version );

    /* (non-Javadoc)
     * @see SystemModel#getTypeProperties(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract Collection< P > getTypeProperties( T type, V version );

    /* (non-Javadoc)
     * @see SystemModel#getProperties(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract Collection< P > getProperties( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#getProperty(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract P getProperty( O object, N propertyName, V version );

    /* (non-Javadoc)
     * @see SystemModel#getRelationships(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract Collection< R > getRelationships( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#getRelationships(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract Collection< R > getRelationships( O object, N relationshipName,
                                             V version );

    /* (non-Javadoc)
     * @see SystemModel#getRelated(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract Collection< O > getRelated( O object, N relationshipName, V version );

    /* (non-Javadoc)
     * @see SystemModel#isDirected(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract boolean isDirected( R relationship, V version );

    /* (non-Javadoc)
     * @see SystemModel#getRelatedObjects(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract O getRelatedObjects( R relationship, V version );

    /* (non-Javadoc)
     * @see SystemModel#getObjectForRole(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract O getObjectForRole( R relationship, N role, V version );

    /* (non-Javadoc)
     * @see SystemModel#getSource(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract O getSource( R relationship, V version );

    /* (non-Javadoc)
     * @see SystemModel#getTarget(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract O getTarget( R relationship, V version );

    /* (non-Javadoc)
     * @see SystemModel#latestVersion(java.util.Collection)
     */
    @Override
    public abstract V latestVersion( Collection< C > context );

    @Override
    public Class<?> getClass(ModelItem item) {
        switch ( item ) {
            case CONSTRAINT:
                return getConstraintClass();
            case CONTEXT:
                return getContextClass();
            case IDENTIFIER:
                return getIdentifierClass();
            case NAME:
                return getNameClass();
            case OBJECT:
                return getObjectClass();
            case PROPERTY:
                return getPropertyClass();
            case RELATIONSHIP:
                return getRelationshipClass();
            case TYPE:
                return getTypeClass();
            case VALUE:
                return getValueClass();
            case VERSION:
                return getVersionClass();
            case VIEW:
                return getViewClass();
            case VIEWPOINT:
                return getViewpointClass();
            case WORKSPACE:
                return getWorkspaceClass();
            default:
                Debug.error( "Unexpected SystemModel.ModelItem: " + item );
        }
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#getObjectClass()
     */
    @Override
    public abstract Class< O > getObjectClass();

    /* (non-Javadoc)
     * @see SystemModel#getContextClass()
     */
    @Override
    public abstract Class< C > getContextClass();

    /* (non-Javadoc)
     * @see SystemModel#getTypeClass()
     */
    @Override
    public abstract Class< T > getTypeClass();

    /* (non-Javadoc)
     * @see SystemModel#getPropertyClass()
     */
    @Override
    public abstract Class< P > getPropertyClass();

    /* (non-Javadoc)
     * @see SystemModel#getNameClass()
     */
    @Override
    public abstract Class< N > getNameClass();

    /* (non-Javadoc)
     * @see SystemModel#getIdentifierClass()
     */
    @Override
    public abstract Class< I > getIdentifierClass();

    /* (non-Javadoc)
     * @see SystemModel#getValueClass()
     */
    @Override
    public abstract Class< U > getValueClass();

    /* (non-Javadoc)
     * @see SystemModel#getRelationshipClass()
     */
    @Override
    public abstract Class< R > getRelationshipClass();

    /* (non-Javadoc)
     * @see SystemModel#getVersionClass()
     */
    @Override
    public abstract Class< V > getVersionClass();

    /* (non-Javadoc)
     * @see SystemModel#getWorkspaceClass()
     */
    @Override
    public abstract Class< W > getWorkspaceClass();

    /* (non-Javadoc)
     * @see SystemModel#getConstraintClass()
     */
    @Override
    public abstract Class< CT > getConstraintClass();
    
    /* (non-Javadoc)
     * @see sysml.SystemModel#getViewClass()
     */
    @Override
    public abstract Class< ? extends O > getViewClass();
    
    /* (non-Javadoc)
     * @see sysml.SystemModel#getViewpointClass()
     */
    @Override
    public abstract Class< ? extends O > getViewpointClass();

    
    /**
     * @param o
     * @param cls the type to which the Object is converted
     * @return a conversion of the Object to the specified type, null if o is null or if the conversion is unsuccessful
     */
    protected static <T> T as( Object o, Class<T> cls ) {
        T t = null;
        Pair< Boolean, T > res = ClassUtils.coerce( o, cls, true );
        if ( res.first ) t = res.second;
        return t;
    }
    
    /* (non-Javadoc)
     * @see sysml.SystemModel#asName(java.lang.Object)
     */
    @Override
    public N asName( Object o ) {
        return as( o, getNameClass() );
    }
    
    /* (non-Javadoc)
     * @see sysml.SystemModel#asIdentifier(java.lang.Object)
     */
    @Override
    public I asIdentifier( Object o ) {
        return as( o, getIdentifierClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#asObject(java.lang.Object)
     */
    @Override
    public O asObject( Object o ) {
        return as( o, getObjectClass() );
    }
    
    /* (non-Javadoc)
     * @see sysml.SystemModel#asContextCollection(java.lang.Object)
     */
    @Override
    public Collection<C> asContextCollection( Object object ) {
        if ( object == null ) return null;
        Pair< Boolean, List< C > > result =
                ClassUtils.coerceList( object, getContextClass(), true );
        return result.second;
    }

    /* (non-Javadoc)
     * @see SystemModel#asContext(java.lang.Object)
     */
    @Override
    public C asContext( Object object ) {
        return as( object, getContextClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#asType(java.lang.Object)
     */
    @Override
    public T asType( Object o ) {
        return as( o, getTypeClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#asProperty(java.lang.Object)
     */
    @Override
    public P asProperty( Object o ) {
        return as( o, getPropertyClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#asValue(java.lang.Object)
     */
    @Override
    public U asValue( Object o ) {
        return as( o, getValueClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#asRelationship(java.lang.Object)
     */
    @Override
    public R asRelationship( Object o ) {
        return as( o, getRelationshipClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#asVersion(java.lang.Object)
     */
    @Override
    public V asVersion( Object o ) {
        return as( o, getVersionClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#asWorkspace(java.lang.Object)
     */
    @Override
    public W asWorkspace( Object o ) {
        return as( o, getWorkspaceClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#asConstraint(java.lang.Object)
     */
    @Override
    public CT asConstraint( Object o ) {
        return as( o, getConstraintClass() );
    }

    /* (non-Javadoc)
     * @see SystemModel#idsAreSettable()
     */
    @Override
    public abstract boolean idsAreSettable();

    /* (non-Javadoc)
     * @see SystemModel#namesAreSettable()
     */
    @Override
    public abstract boolean namesAreSettable();

    /* (non-Javadoc)
     * @see SystemModel#objectsMayBeChangedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean objectsMayBeChangedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#typesMayBeChangedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean typesMayBeChangedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#propertiesMayBeChangedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean propertiesMayBeChangedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#objectsMayBeCreatedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean objectsMayBeCreatedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#typesMayBeCreatedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean typesMayBeCreatedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#propertiesMayBeCreatedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean propertiesMayBeCreatedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#objectsMayBeDeletedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean objectsMayBeDeletedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#typesMayBeDeletedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean typesMayBeDeletedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#propertiesMayBeDeletedForVersion(java.lang.Object)
     */
    @Override
    public abstract boolean propertiesMayBeDeletedForVersion( V version );

    /* (non-Javadoc)
     * @see SystemModel#createObject(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract O createObject( I identifier, V version );

    /* (non-Javadoc)
     * @see SystemModel#setIdentifier(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract boolean setIdentifier( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#setName(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract boolean setName( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#setType(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract boolean setType( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#deleteObject(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract O deleteObject( I identifier, V version );

    /* (non-Javadoc)
     * @see SystemModel#deleteType(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract T deleteType( O object, V version );

    /* (non-Javadoc)
     * @see SystemModel#map(java.util.Collection, SystemModel.MethodCall, int)
     */
    @Override
    public Collection< Object >
            map( Collection< O > objects,
                 MethodCall methodCall,
                 int indexOfObjectArgument ) throws InvocationTargetException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#filter(java.util.Collection, SystemModel.MethodCall, int)
     */
    @Override
    public Collection< Object >
            filter( Collection< O > objects,
                    MethodCall methodCall,
                    int indexOfObjectArgument )
                                               throws InvocationTargetException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#forAll(java.util.Collection, SystemModel.MethodCall, int)
     */
    @Override
    public boolean
            forAll( Collection< O > objects,
                    MethodCall methodCall,
                    int indexOfObjectArgument )
                                               throws InvocationTargetException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see SystemModel#thereExists(java.util.Collection, SystemModel.MethodCall, int)
     */
    @Override
    public
            boolean
            thereExists( Collection< O > objects,
                         MethodCall methodCall,
                         int indexOfObjectArgument )
                                                    throws InvocationTargetException {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see SystemModel#fold(java.util.Collection, java.lang.Object, SystemModel.MethodCall, int, int)
     */
    @Override
    public
            Object
            fold( Collection< O > objects, Object initialValue,
                  MethodCall methodCall,
                  int indexOfObjectArgument, int indexOfPriorResultArgument )
                                                                             throws InvocationTargetException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#sort(java.util.Collection, java.util.Comparator, SystemModel.MethodCall, int)
     */
    @Override
    public Collection< O >
            sort( Collection< O > objects, Comparator< ? > comparator,
                  MethodCall methodCall,
                  int indexOfObjectArgument ) throws InvocationTargetException {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see SystemModel#getDomainConstraint(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract CT getDomainConstraint( O object, V version, W workspace );

    /* (non-Javadoc)
     * @see SystemModel#addConstraint(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract void addConstraint( CT constraint, V version, W workspace );

    /* (non-Javadoc)
     * @see SystemModel#addDomainConstraint(java.lang.Object, java.lang.Object, java.util.Set, java.lang.Object)
     */
    @Override
    public abstract void addDomainConstraint( CT constraint, V version,
                                     Set< U > valueDomainSet, W workspace );

    /* (non-Javadoc)
     * @see SystemModel#addDomainConstraint(java.lang.Object, java.lang.Object, gov.nasa.jpl.mbee.util.Pair, java.lang.Object)
     */
    @Override
    public abstract void
            addDomainConstraint( CT constraint, V version,
                                 Pair< U, U > valueDomainRange, W workspace );

    /* (non-Javadoc)
     * @see SystemModel#relaxDomain(java.lang.Object, java.lang.Object, java.util.Set, java.lang.Object)
     */
    @Override
    public abstract void relaxDomain( CT constraint, V version, Set< U > valueDomainSet,
                             W workspace );

    /* (non-Javadoc)
     * @see SystemModel#relaxDomain(java.lang.Object, java.lang.Object, gov.nasa.jpl.mbee.util.Pair, java.lang.Object)
     */
    @Override
    public abstract void relaxDomain( CT constraint, V version,
                             Pair< U, U > valueDomainRange, W workspace );

    /* (non-Javadoc)
     * @see SystemModel#getConstraintsOfElement(java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract Collection< CT > getConstraintsOfObject( O element, V version,
                                                     W workspace );

    /* (non-Javadoc)
     * @see SystemModel#getConstraintsOfContext(java.lang.Object)
     */
    @Override
    public abstract Collection< CT > getConstraintsOfContext( C context );

    /* (non-Javadoc)
     * @see SystemModel#getViolatedConstraintsOfElement(java.lang.Object, java.lang.Object)
     */
    @Override
    public abstract Collection< CT > getViolatedConstraintsOfObject( O element,
                                                             V version );

    /* (non-Javadoc)
     * @see SystemModel#getViolatedConstraintsOfContext(java.lang.Object)
     */
    @Override
    public abstract Collection< CT > getViolatedConstraintsOfContext( C context );

    /* (non-Javadoc)
     * @see SystemModel#setOptimizationFunction(java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public abstract void setOptimizationFunction( Method method, Object... arguments );

    /* (non-Javadoc)
     * @see SystemModel#getScore()
     */
    @Override
    public abstract Number getScore();

}
