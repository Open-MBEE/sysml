/*******************************************************************************
 * Copyright (c) <2014>, California Institute of Technology ("Caltech").  
 * U.S. Government sponsorship acknowledged.
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met:
 * 
 *  - Redistributions of source code must retain the above copyright notice, this list of 
 *    conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, this list 
 *    of conditions and the following disclaimer in the documentation and/or other materials 
 *    provided with the distribution.
 *  - Neither the name of Caltech nor its operating division, the Jet Propulsion Laboratory, 
 *    nor the names of its contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS 
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER  
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package sysml.examples;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.FileUtils;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;
import sysml.AbstractSystemModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Compile: from mbee-dev/sysml/src run: javac sysml/examples/ReadJSON.java -classpath ".:org:../../util/src/"
 * Run: from mbee-dev/sysml/src run: Opening sysml/examples/Load Job PROJECT-ID_10_14_14_11_41_30_AM_188fb709_1490a823d0d_7361_europa_tw_jpl_nasa_gov_128_149_19_48.json
 *  
 */
public class JsonSystemModel extends AbstractSystemModel< JSONObject, JSONObject, JSONObject, JSONObject, String, String, Object, JSONObject, String, String, JSONObject > {
//public class JsonSystemModel{
	
	protected JSONObject json = null;
	
	// Map of element sysmlid to JSON element objects:
	protected Map<String, JSONObject> elementMap = new HashMap<String, JSONObject>();
	
	// Map of element sysmlid to List of sysmlids that that element owns
	protected Map<String, List<String> > ownershipMap = new HashMap<String, List<String> >();
	
	//public JsonSystemModel() { }
	
	public JsonSystemModel(String jsonString) throws JSONException{ 
		readJson(jsonString);
	}
	
	public void readJson(String jsonString) throws JSONException{
		try{
			json = new JSONObject(jsonString);
			// Make sure JSON format contains what we expect:
			if( json.has("elements") ) {
    			JSONArray elements = json.getJSONArray("elements");
    			
    			for( int i = 0; i < elements.length(); i++) {
    				JSONObject jsonObj = elements.getJSONObject(i);
    				//System.out.println(i + ": " + jsonObj.toString());
    	
    				// Make sure JSON format contains what we expect:
    				if( jsonObj.has("sysmlid") ) {
    					
    					// Update element map:
    					//System.out.println("Key: " + jsonObj.getString("sysmlid"));
    					elementMap.put(jsonObj.getString("sysmlid"), jsonObj);
    					
    					// Update ownership map:
    					if( jsonObj.has("owner") ) {
    						String owner = jsonObj.getString("owner");
    						List<String> owned = ownershipMap.get(owner);
    					    if (owned == null) {        
    					    	ownershipMap.put(owner, (owned = new ArrayList<String>()) );
    					    }
    					    owned.add(jsonObj.getString("sysmlid"));
        				}
    					
    				}else {
    					System.err.println("Error, invalid JSON format!");
    				}
    			}	
    		} else {
    			System.err.println("Error, invalid JSON format!");
    		}
			
		} catch (Exception e){
            System.err.println("Error reading JSON string!");
            throw e;
		}
	}
	
	protected Collection<JSONObject> searchForElements(Object context,
			String jsonName, String jsonValue) {
		
		if( jsonName == null )
			return null;
		
		List<JSONObject> elementList = new ArrayList<JSONObject>();
		// TODO -- take into account context:
		for( JSONObject element : elementMap.values() ) {
			if( element.has(jsonName) ) {
				if( jsonValue == null || element.get(jsonName).equals(jsonValue) ) {
					elementList.add(element);
				}
			}
		}
		
		if(elementList.isEmpty()){
			return null;
		}
		
		return elementList;
	}

	@Override
	public boolean isDirected(JSONObject relationship) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<JSONObject> getRelatedElements(JSONObject relationship) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementForRole(JSONObject relationship,
			String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getSource(JSONObject relationship) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTarget(JSONObject relationship) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<JSONObject> getElementClass() {
		return JSONObject.class;
	}

	@Override
	public Class<JSONObject> getContextClass() {
		return JSONObject.class;
	}

	@Override
	public Class<JSONObject> getTypeClass() {
		return JSONObject.class;
	}

	@Override
	public Class<JSONObject> getPropertyClass() {
		return JSONObject.class;
	}

	@Override
	public Class<String> getNameClass() {
		return String.class;
	}

	@Override
	public Class<String> getIdentifierClass() {
		return String.class;
	}

	@Override
	public Class<Object> getValueClass() {
		return Object.class;
	}

	@Override
	public Class<JSONObject> getRelationshipClass() {
		return JSONObject.class;
	}

	@Override
	public Class<String> getVersionClass() {
		return String.class;
	}

	@Override
	public Class<String> getWorkspaceClass() {
		return String.class;
	}

	@Override
	public Class<JSONObject> getConstraintClass() {
		return JSONObject.class;
	}

	@Override
	public Class<? extends JSONObject> getViewClass() {
		return JSONObject.class;
	}

	@Override
	public Class<? extends JSONObject> getViewpointClass() {
		return JSONObject.class;
	}

	@Override
	public JSONObject createConstraint(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject createElement(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createIdentifier(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createName(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject createProperty(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject createRelationship(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject createType(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject createValue(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createVersion(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject createView(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject createViewpoint(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createWorkspace(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object delete(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraint(Object context, Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithElement(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithIdentifier(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithName(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithProperty(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithRelationship(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithType(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithView(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithViewpoint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getConstraintWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithConstraint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithIdentifier(Object context,
			String specifier) {
		// TODO -- need to take into account the context!
        return Utils.newList( elementMap.get(specifier) );
	}

	@Override
	public Collection<JSONObject> getElementWithName(Object context,
			String specifier) {
		if( specifier == null ){
			return null; 
		}
		return searchForElements(context,"name", specifier);
	}

	@Override
	public Collection<JSONObject> getElementWithProperty(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithRelationship(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithType(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithView(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithViewpoint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public Collection< String > getName( Object context ) {

  	// Assuming that we can only have JSONObject context:
  	if (context instanceof JSONObject) {

  		JSONObject element = (JSONObject) context;

  		// Note: This returns the sysml:name not the cm:name, which is what we
  		//		 want
  		Object name = element.getString("name");

  		return Utils.asList(name, String.class);
  	}

  	else {
          // TODO -- error????  Are there any other contexts than an JSONObject that would have a property?
          Debug.error("context is not an EmsScriptNode!");
          return null;
      }
    }

	@Override
	public Collection<String> getIdentifier(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getProperty(Object context, Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithConstraint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithElement(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithIdentifier(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithRelationship(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithType(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithView(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithViewpoint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationship(Object context,
			Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithConstraint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithElement(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithIdentifier(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithName(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithProperty(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithType(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithView(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithViewpoint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getType(Object context, Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeString(Object context, Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithConstraint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithElement(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithIdentifier(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithName(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithProperty(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithRelationship(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithView(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithViewpoint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValue(Object context, Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithConstraint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithElement(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithIdentifier(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithName(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithProperty(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithRelationship(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithType(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithView(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithViewpoint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValueWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getVersion(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getView(Object context, Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpoint(Object context, Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithConstraint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithElement(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithIdentifier(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithName(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithProperty(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithRelationship(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithType(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithView(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithConstraint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithElement(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithIdentifier(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithName(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithProperty(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithRelationship(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithType(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithViewpoint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getWorkspace(Object context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean fixConstraintViolations(JSONObject element, String version) {
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
	public JSONObject getDomainConstraint(JSONObject element, String version,
			String workspace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addConstraint(JSONObject constraint, String version,
			String workspace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<JSONObject> getConstraintsOfElement(JSONObject element,
			String version, String workspace) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViolatedConstraintsOfElement(
			JSONObject element, String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOptimizationFunction(Method method, Object... arguments) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Number getScore() {
		// TODO Auto-generated method stub
		return null;
	}
		
	
//    @Override
//    public boolean isDirected( EmsScriptNode relationship ) {
//        if ( relationship == null ) return false;
//        return services.getDictionaryService()
//                       .isSubClass( relationship.getQNameType(),
//                                    QName.createQName( Acm.ACM_DIRECTED_RELATIONSHIP ) );
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelatedElements( EmsScriptNode relationship ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getElementForRole( EmsScriptNode relationship, String role ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getSource( EmsScriptNode relationship ) {
//
//        return getProperty(relationship, Acm.ACM_SOURCE);
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getTarget( EmsScriptNode relationship ) {
//
//        return getProperty(relationship, Acm.ACM_TARGET);
//    }
//
//    @Override
//    public Class< EmsScriptNode > getElementClass() {
//        return EmsScriptNode.class;
//    }
//
//    @Override
//    public Class< EmsScriptNode > getContextClass() {
//        return EmsScriptNode.class;
//    }
//
//    @Override
//    public Class< EmsScriptNode > getTypeClass() {
//        return EmsScriptNode.class;
//    }
//
//    @Override
//    public Class< EmsScriptNode > getPropertyClass() {
//        return EmsScriptNode.class;
//    }
//
//    @Override
//    public Class< String > getNameClass() {
//        return String.class;
//    }
//
//    @Override
//    public Class< String > getIdentifierClass() {
//        return String.class;
//    }
//
//    @Override
//    public Class< Object > getValueClass() {
//        return Object.class;
//    }
//
//    @Override
//    public Class< EmsScriptNode > getRelationshipClass() {
//        return EmsScriptNode.class;
//    }
//
//    @Override
//    public Class< String > getVersionClass() {
//        return String.class;
//    }
//
//    @Override
//    public Class< String > getWorkspaceClass() {
//        return String.class;
//    }
//
//    @Override
//    public Class< EmsScriptNode > getConstraintClass() {
//        return EmsScriptNode.class;
//    }
//
//    @Override
//    public Class< ? extends EmsScriptNode > getViewClass() {
//        return EmsScriptNode.class;
//    }
//
//    @Override
//    public Class< ? extends EmsScriptNode > getViewpointClass() {
//        return EmsScriptNode.class;
//    }
//
//    @Override
//    public EmsScriptNode createConstraint( Object context ) {
//        if ( context instanceof EmsScriptNode ) {
//
//        }
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public EmsScriptNode createElement( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public String createIdentifier( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public String createName( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public EmsScriptNode createProperty( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public EmsScriptNode createRelationship( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public EmsScriptNode createType( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public EmsScriptNode createValue( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public String createVersion( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public EmsScriptNode createView( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public EmsScriptNode createViewpoint( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public String createWorkspace( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Object delete( Object object ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getConstraint( Object context,
//                                                      Object specifier ) {
//
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithElement( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithIdentifier( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getConstraintWithName( Object context,
//                                                              String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithProperty( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithRelationship( Object context,
//                                           EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithType( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithValue( Object context, Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithVersion( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithView( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public
//            Collection< EmsScriptNode >
//            getConstraintWithViewpoint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintWithWorkspace( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getElement( Object context,
//                                                   Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getElementWithConstraint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getElementWithIdentifier( Object context, String specifier ) {
//        // TODO -- need to take into account the context!
//        NodeRef element = NodeUtil.findNodeRefById( specifier, true, null, null, services, false );
//        EmsScriptNode emsSN = new EmsScriptNode( element, services );
//        return Utils.newList( emsSN );
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getElementWithName( Object context,
//                                                           String specifier ) {
//        return getElementWithName(context, specifier, null);
//    }
//    public Collection< EmsScriptNode > getElementWithName( Object context,
//                                                           String specifier,
//                                                           Date dateTime ) {
//        StringBuffer response = new StringBuffer();
//        Status status = new Status();
//        // TODO -- need to take into account the context!
//        Map< String, EmsScriptNode > elements =
//                NodeUtil.searchForElements( specifier, true, null, dateTime,
//                                            services, response, status );
//        if ( elements != null ) return elements.values();
//        return Collections.emptyList();
//    }
//
	
	
	
//	@Override
//    public Collection< JSONObject >
//            getElementWithIdentifier( Object context, String specifier ) {
//        
//		// TODO -- need to take into account the context!
//        // NodeRef element = NodeUtil.findNodeRefById( specifier, true, null, null, services, false );
//        //EmsScriptNode emsSN = new EmsScriptNode( element, services );
//        
//		
//		
//		
//		return Utils.newList( emsSN );
//    }

//    @Override
//    public Collection< JSONObject > getElementWithName( Object context,
//                                                           String specifier ) {
//        return getElementWithName(context, specifier, null);
//    }
//    public Collection< JSONObject > getElementWithName( Object context,
//                                                           String specifier,
//                                                           Date dateTime ) {
//        StringBuffer response = new StringBuffer();
//        Status status = new Status();
//        // TODO -- need to take into account the context!
//        Map< String, EmsScriptNode > elements =
//                NodeUtil.searchForElements( specifier, true, null, dateTime,
//                                            services, response, status );
//        if ( elements != null ) return elements.values();
//        return Collections.emptyList();
//    }
    
    
    
//    @Override
//    public Collection< EmsScriptNode >
//            getElementWithProperty( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public
//            Collection< EmsScriptNode >
//            getElementWithRelationship( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getElementWithType( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getElementWithValue( Object context, Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getElementWithVersion( Object context,
//                                                              String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getElementWithView( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getElementWithViewpoint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getElementWithWorkspace( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< String > getName( Object context ) {
//
//    	// Assuming that we can only have EmsScriptNode context:
//    	if (context instanceof EmsScriptNode) {
//
//    		EmsScriptNode node = (EmsScriptNode) context;
//
//    		// Note: This returns the sysml:name not the cm:name, which is what we
//    		//		 want
//    		Object name = node.getProperty(Acm.ACM_NAME);
//
//    		return Utils.asList(name, String.class);
//    	}
//
//    	else {
//            // TODO -- error????  Are there any other contexts than an EmsScriptNode that would have a property?
//            Debug.error("context is not an EmsScriptNode!");
//            return null;
//        }
//
//    }
//
//    @Override
//    public Collection< String > getIdentifier( Object context ) {
//
//        return null;
//    }
//
//    /**
//     * Attempts to convert propVal to a EmsScriptNode.  If conversion is possible, adds
//     * to the passed List.
//     *
//     * @param propVal the property to try and convert
//     * @param returnList the list of nodes to possibly add to
//     */
//    private void convertToScriptNode(Object propVal, List<EmsScriptNode> returnList) {
//
//       	// The propVal can be a ArrayList<NodeRef>, ArrayList<Object>, NodeRef, or
//    	// Object
//
//    	if (propVal != null) {
//
//	 		if (propVal instanceof ArrayList) {
//
//				// Loop through the arrayList and convert each NodeRef to a EmsScriptNode
//				ArrayList<?> propValArray = (ArrayList<?>)propVal;
//				for (Object propValNode : propValArray) {
//
//					// If its a NodeRef then convert:
//					if (propValNode instanceof NodeRef) {
//
//						returnList.add(new EmsScriptNode((NodeRef)propValNode, services));
//					}
//
//					// TODO what do we do for other objects?  For now, nothing....
//				}
//
//			} // ends if propVal is a ArrayList
//
//			else if (propVal instanceof NodeRef) {
//				returnList.add(new EmsScriptNode((NodeRef)propVal, services));
//			}
//
//			else if (propVal instanceof String) {
//				// Get the corresponding node with a name of the propVal:
//				Collection<EmsScriptNode> nodeList = getElementWithName(null, (String)propVal);
//				if (!Utils.isNullOrEmpty(nodeList)) {
//					returnList.add(nodeList.iterator().next());
//				}
//			}
//
//			else {
//				// TODO what do we do for other objects?  For now, nothing....
//			}
//
//    	}
//
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getProperty( Object context,
//                                                    Object specifier ) {
//
//        ArrayList< EmsScriptNode > allProperties = new ArrayList< EmsScriptNode >();
//
//        Object mySpecifier = specifier;
//        // Convert specifier to add ACM type, ie prepend "sysml:":
//        Map<String, String> convertMap = Acm.getJSON2ACM();
//        if (specifier instanceof String && convertMap.containsKey(specifier)) {
//        	 mySpecifier = convertMap.get(specifier);
//        }
//
//        // find the specified property inside the context
//        if ( context instanceof EmsScriptNode ) {
//
//            EmsScriptNode node = (EmsScriptNode)context;
//
//            if ( mySpecifier == null ) {
//                // if no specifier, return all properties
//                Map< String, Object > props = node.getProperties();
//                if ( props != null ) {
//
//                	// Loop through all of returned properties:
//                	Collection<Object> propValues = props.values();
//                	for (Object propVal : propValues) {
//
//                		// Attempt to convert to a EmsScriptNode and add to the list
//                		// to later return if conversion succeeded:
//                		convertToScriptNode(propVal, allProperties);
//
//                	} // ends for loop through properties
//                }
//
//            } // ends if specifies is null
//
//            else {
//                Object prop = node.getProperty( "" + mySpecifier );
//
//        		// Attempt to converted to a EmsScriptNode and add to the list
//        		// to later return if conversion succeeded:
//                convertToScriptNode(prop, allProperties);
//
//        	}
//
//            return allProperties;
//        }
//
//        if ( context != null ) {
//            // TODO -- error????  Are there any other contexts than an EmsScriptNode that would have a property?
//            Debug.error("context is not an EmsScriptNode!");
//            return null;
//        }
//
//        // context is null; look for nodes of type Property that match the specifier
//        if ( mySpecifier != null ) {
//            return getElementWithName( context, "" + mySpecifier );
//        }
//
//        // context and specifier are both be null
//        // REVIEW -- error?
//        // Debug.error("context and specifier cannot both be null!");
//        // REVIEW -- What about returning all properties?
//        Collection< EmsScriptNode > propertyTypes = getTypeWithName( context, "Property" );
//        if ( !Utils.isNullOrEmpty( propertyTypes ) ) {
//            for ( EmsScriptNode prop : propertyTypes ) {
//                allProperties.addAll( getElementWithType( context, prop ) );
//            }
//            return allProperties;
//        }
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithConstraint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithElement( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithIdentifier( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public
//            Collection< EmsScriptNode >
//            getPropertyWithRelationship( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithType( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithValue( Object context, Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithVersion( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithView( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithViewpoint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getPropertyWithWorkspace( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getRelationship( Object context,
//                                                        Object specifier ) {
//
//    	// TODO
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithConstraint( Object context,
//                                           EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public
//            Collection< EmsScriptNode >
//            getRelationshipWithElement( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithIdentifier( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithName( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public
//            Collection< EmsScriptNode >
//            getRelationshipWithProperty( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithType( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithValue( Object context, Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithVersion( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithView( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithViewpoint( Object context,
//                                          EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getRelationshipWithWorkspace( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    /**
//     * Get matching types
//     * <p>
//     * Examples:
//     * <ul>
//     * <li>getType(elementA, "typeX") returns the types of elementA whose name
//     * or ID is "typeX."
//     * <li>getType(packageB, "typeX") returns the types located inside packageB
//     * whose name or id is "typeX."
//     * <li>getType(myWorkspace, "typeX") returns the types whose names or IDs
//     * are "typeX" for myWorkspace.
//     * </ul>
//     *
//     * @param context
//     *            the element whose type is sought or a location as a package or
//     *            workspace within which the type is to be found
//     * @param specifier
//     *            the ID, name, version, workspace, etc. for the type element
//     * @return type elements that match any interpretation of the specifier for
//     *         any interpretation of the context or an empty list if there are
//     *         no such types
//     * @see sysml.SystemModel#getType(java.lang.Object, java.lang.Object)
//     */
//    @Override
//    public Collection< EmsScriptNode >
//            getType( Object context, Object specifier ) {
//
//        // TODO -- the code below is relevant to getElementWithType(), not getType().
//
//    	// TODO ScriptNode getType returns a QName or String, why does he want a collection
//    	// of EmsScriptNode?  I think we should change T to String.
//
//    	// Ignoring context b/c it doesnt make sense
//
//    	// Search for all elements with the specified type name:
//    	if (specifier instanceof String) {
////	        StringBuffer response = new StringBuffer();
////	        Status status = new Status();
////	        Map< String, EmsScriptNode > elements =
////	                NodeUtil.searchForElements( "@sysml\\:type:\"", (String)specifier, services, response,
////	                                            status );
//////            NodeUtil.searchForElements( "TYPE:\"", (String)specifier, services, response,
//////                                        status );
////
////	        if ( elements != null && !elements.isEmpty()) return elements.values();
//
////	        if ( elements == null ) elements = new LinkedHashMap<String, EmsScriptNode>();
//
//	        Collection< EmsScriptNode > elementColl = null;
//	        try {
//	        		elementColl = NodeUtil.luceneSearchElements( "ASPECT:\"sysml:" + specifier + "\"" );
//	        } catch (Exception e) {
//	        		// if lucene query fails, most likely due to non-existent aspect, we should look for type now
//	        		try {
//	        			elementColl = NodeUtil.luceneSearchElements( "TYPE:\"sysml:" + specifier + "\"");
//	        		} catch (Exception ee) {
//	        			// do nothing
//	        		}
//	        }
////	        for ( EmsScriptNode e : elementColl ) {
////	            elements.put( e.getId(), e );
////	        }
//            if ( elementColl != null && !elementColl.isEmpty()) {
//            		return elementColl;
//            }
//
//    	}
//
//        return Collections.emptyList();
//    }
//
//    // TODO remove this once we fix getType()
//    @Override
//   public String getTypeString( Object context, Object specifier ) {
//
//        // TODO finish this, just a partial implementation
//
//        if (context instanceof EmsScriptNode) {
//        	EmsScriptNode node = (EmsScriptNode) context;
//        	return node.getTypeName();
//        }
//
//        return null;
//
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getTypeWithConstraint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getTypeWithElement( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getTypeWithIdentifier( Object context,
//                                                              String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getTypeWithName( Object context,
//                                                        String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getTypeWithProperty( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getTypeWithRelationship( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getTypeWithValue( Object context, Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getTypeWithVersion( Object context,
//                                                           String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getTypeWithView( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getTypeWithViewpoint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getTypeWithWorkspace( Object context,
//                                                             String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object > getValue( Object context,
//    									  Object specifier ) {
//
//        Object mySpecifier = specifier;
//        // Convert specifier to add ACM type, ie prepend "sysml:":
//        Map<String, String> convertMap = Acm.getJSON2ACM();
//        if (specifier instanceof String && convertMap.containsKey(specifier)) {
//        	 mySpecifier = convertMap.get(specifier);
//        }
//
//    	// Assuming that we can only have EmsScriptNode context:
//    	if (context instanceof EmsScriptNode) {
//
//    		EmsScriptNode node = (EmsScriptNode) context;
//
//			// If it is a Property type, then the value is a NodeRef, which
//			// we convert to a EmsScriptNode:
//    		if (node.hasAspect(Acm.ACM_PROPERTY)) {
//
//		    	List<EmsScriptNode> returnList = new ArrayList<EmsScriptNode>();
//				Collection<NodeRef> valueNodes =
//				        (Collection< NodeRef >)node.getProperty(Acm.ACM_VALUE);
//				convertToScriptNode(valueNodes, returnList);
//
//	    		return Utils.asList(returnList, Object.class);
//			}
//
//			// Otherwise, return the Object for the value
//			else {
//
//	    		// If no specifier is supplied:
//				if (mySpecifier == null) {
//					// TODO what should we do here?
//	    		}
//				else {
//
//					Object valueNode = node.getProperty("" + mySpecifier);
//
//					if (valueNode != null) {
//						return Utils.newList(valueNode);
//					}
//				}
//
//			}
//
//    	}
//
//    	else {
//            // TODO -- error????  Are there any other contexts than an EmsScriptNode that would have a property?
//            Debug.error("context is not an EmsScriptNode!");
//            return null;
//        }
//
//    	return null;
//
//    }
//
//    @Override
//    public Collection< Object >
//            getValueWithConstraint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object >
//            getValueWithElement( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object >
//            getValueWithIdentifier( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object > getValueWithName( Object context,
//                                                         String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object >
//            getValueWithProperty( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object >
//            getValueWithRelationship( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object >
//            getValueWithType( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object > getValueWithVersion( Object context,
//                                                            String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object >
//            getValueWithView( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object >
//            getValueWithViewpoint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< Object > getValueWithWorkspace( Object context,
//                                                              String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< String > getVersion( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getView( Object context, Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getViewpoint( Object context,
//                                                     Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public
//            Collection< EmsScriptNode >
//            getViewpointWithConstraint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithElement( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithIdentifier( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getViewpointWithName( Object context,
//                                                             String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithProperty( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithRelationship( Object context,
//                                          EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithType( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithValue( Object context, Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithVersion( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithView( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewpointWithWorkspace( Object context, String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewWithConstraint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewWithElement( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getViewWithIdentifier( Object context,
//                                                              String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getViewWithName( Object context,
//                                                        String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewWithProperty( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewWithRelationship( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewWithType( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewWithValue( Object context, Object specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getViewWithVersion( Object context,
//                                                           String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViewWithViewpoint( Object context, EmsScriptNode specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode > getViewWithWorkspace( Object context,
//                                                             String specifier ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< String > getWorkspace( Object context ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    /**
//     * Set the value for the passed node to the passed value
//     * 
//     * @param node
//     * @param value
//     */
//    public < T extends Serializable > void setValue(EmsScriptNode node, T value) {
//    	
//    	if (node == null || value == null) {
//            Debug.error("setValue(): passed node or value is null!");
//    	}
//    	else {
//	    	String type = getTypeString(node, null);
//
//	    	if (type == null) {
//	            Debug.error("setValue(): type for the passed node is null!");
//	    	}
//	    	else {
//		        if (type.equals(Acm.JSON_LITERAL_INTEGER)) {
//
//		        	node.createOrUpdateProperty(Acm.ACM_INTEGER, value);
//		        }
//		        else if (type.equals(Acm.JSON_LITERAL_REAL)) {
//
//		        	node.createOrUpdateProperty(Acm.ACM_DOUBLE, value);
//		        }
//		        else if (type.equals(Acm.JSON_LITERAL_BOOLEAN)) {
//
//		        	node.createOrUpdateProperty(Acm.ACM_BOOLEAN, value);
//		        }
//		        else if (type.equals(Acm.JSON_LITERAL_UNLIMITED_NATURAL)) {
//
//		        	node.createOrUpdateProperty(Acm.ACM_NATURAL_VALUE, value);
//		        }
//		        else if (type.equals(Acm.JSON_LITERAL_STRING)) {
//		        	node.createOrUpdateProperty(Acm.ACM_STRING, value);
//		        }
//		        else {
//		            Debug.error("setValue(): unrecognized type: "+type);
//		        }
//	    	}
//    	}
//
//    }
//    
//    @Override
//    public Object set( Object object, Object specifier, Object value ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public boolean idsAreWritable() {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    public boolean namesAreWritable() {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    public boolean versionsAreWritable() {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    public EmsScriptNode getDomainConstraint( EmsScriptNode element,
//                                              String version, String workspace ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void addConstraint( EmsScriptNode constraint, String version,
//                               String workspace ) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void addDomainConstraint( EmsScriptNode constraint, String version,
//                                     Set< Object > valueDomainSet,
//                                     String workspace ) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public
//            void
//            addDomainConstraint( EmsScriptNode constraint,
//                                 String version,
//                                 Pair< Object, Object > valueDomainRange,
//                                 String workspace ) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void relaxDomain( EmsScriptNode constraint, String version,
//                             Set< Object > valueDomainSet,
//                             String workspace ) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void
//            relaxDomain( EmsScriptNode constraint, String version,
//                         Pair< Object, Object > valueDomainRange,
//                         String workspace ) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getConstraintsOfElement( EmsScriptNode element, String version,
//                                     String workspace ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Collection< EmsScriptNode >
//            getViolatedConstraintsOfElement( EmsScriptNode element,
//                                             String version ) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void setOptimizationFunction( Method method, Object... arguments ) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public Number getScore() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public ServiceRegistry getServices() {
//        return services;
//    }
//
//    @Override
//    public boolean fixConstraintViolations( EmsScriptNode element,
//                                            String version ) {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    // TODO dont like dependence on BAE for Call here....
//    public Collection< Object >
//    		map( Collection< Object > elements,
//    			 Call call) throws InvocationTargetException {
//
//    	return call.map( elements, 1 );
//    }
//
//    public Collection< Object >
//			map( Collection< Object > elements,
//				 Call call,
//				 int indexOfObjectArgument) throws InvocationTargetException {
//
//		return call.map( elements, indexOfObjectArgument );
//	}
	
	protected Collection< JSONObject > searchForElement(String property, String value)
	{
		
		return null;
	}
	
	/**
     * @param args
     */
    public static void main( String[] args ) {

        if( args.length < 1 )
        {
            System.err.println("Must provide JSON filename as argument!");
            System.exit(1);
        }
        
        try {
            System.out.println("Opening " + args[0]);
            String jsonString = FileUtils.fileToString(args[0]);            
            JsonSystemModel systemModel = new JsonSystemModel(jsonString);
            
            // Search for all elements with the name "Info":
            Collection<JSONObject> elements = systemModel.getElementWithName(null, "Info");
            System.out.println("Items with name Info (" + elements.size() + "):");
            for( JSONObject element : elements.toArray(new JSONObject[0]) ){
            	System.out.println(element.toString());
            }
            
            
            
        } catch (Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }

	@Override
	public Collection<JSONObject> getConstraintWithValue(Object context,
			Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getElementWithValue(Object context,
			Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getPropertyWithValue(Object context,
			Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getRelationshipWithValue(Object context,
			Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getTypeWithValue(Object context,
			Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewpointWithValue(Object context,
			Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<JSONObject> getViewWithValue(Object context,
			Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object set(Object object, Object specifier, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDomainConstraint(JSONObject constraint, String version,
			Set<Object> valueDomainSet, String workspace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDomainConstraint(JSONObject constraint, String version,
			Pair<Object, Object> valueDomainRange, String workspace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void relaxDomain(JSONObject constraint, String version,
			Set<Object> valueDomainSet, String workspace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void relaxDomain(JSONObject constraint, String version,
			Pair<Object, Object> valueDomainRange, String workspace) {
		// TODO Auto-generated method stub
		
	}

}
