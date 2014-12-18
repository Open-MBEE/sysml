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
public class JsonSystemModel extends AbstractSystemModel< JSONObject, JSONObject, String, JSONObject, String, String, Object, JSONObject, String, String, JSONObject > {
	
	// JSONObject that contains the JSON:
	protected JSONObject json = null;
	
	// Map of element sysmlid to JSON element objects:
	protected Map<String, JSONObject> elementMap = new HashMap<String, JSONObject>();
	
	// Map of element sysmlid to List of sysmlids that that element owns
	protected Map<String, List<String> > ownershipMap = new HashMap<String, List<String> >();
		
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
	
	protected JSONObject getElement(String id) {
		if( elementMap.containsKey(id) ) {
			return elementMap.get(id);
		}
		return null;
	}
	
	protected boolean hasElementValue(JSONObject element, String jsonName) throws JSONException {
		
		// See if element has the jsonName field:
		if( element.has(jsonName) ) {
			return true;
		}
		// See if element has a specialization that has the jsonName field:
		else if( element.has("specialization") ){
			JSONObject specialization = (JSONObject) element.get("specialization");
			if( specialization != null && specialization.has(jsonName) ) {
				return true;
			}
		}
		
		return false;
	}

	protected Object getElementValue(JSONObject element, String jsonName) throws JSONException {
		
		// See if element has the jsonName field:
		if( element.has(jsonName) ) {
			return element.get(jsonName);
		}
		// See if element has a specialization that has the jsonName field:
		else if( element.has("specialization") ){
			JSONObject specialization = (JSONObject) element.get("specialization");
			if( specialization != null && specialization.has(jsonName) ) {
				return specialization.get(jsonName);
			}
		}
		
		return null;
	}
	
	protected Collection<JSONObject> searchForElements(Object context,
			String jsonName, String jsonValue) {
		
		if( jsonName == null )
			return null;
		
		List<JSONObject> elementList = new ArrayList<JSONObject>();
		// TODO -- take into account context:
		for( JSONObject element : elementMap.values() ) {
			
			// If element has value and there is not provided jsonValue to match it against, return that element
			// If element has the value and there is a provided jsonValue to match it against, return that element if it equals the jsonValue
			try {
                if( hasElementValue(element, jsonName) )
                {
                	if( jsonValue == null || getElementValue(element, jsonName).equals(jsonValue))
                	{
                		elementList.add(element);
                	}
                }
            } catch ( JSONException e ) {
                e.printStackTrace();
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
		return getProperty(relationship, "source");
	}

	@Override
	public Collection<JSONObject> getTarget(JSONObject relationship) {
		return getProperty(relationship, "target");
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
	public Class<String> getTypeClass() {
		return String.class;
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
	public String createType(Object context) {
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
			String specifier) {
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
			String specifier) {
		if( specifier == null ){
			return null; 
		}
		return searchForElements(context,"type", specifier);
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
  		Object name = null;
        try {
            name = getElementValue(element, "name");
        } catch ( JSONException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

  		return Utils.asList(name, String.class);
  	}

  	else {
          // TODO -- error????  Are there any other contexts than an JSONObject that would have a property?
          Debug.error("context is not an JSONObject!");
          return null;
      }
    }

	@Override
	public Collection<String> getIdentifier(Object context) {
		// Assuming that we can only have JSONObject context:
	  	if (context instanceof JSONObject) {

	  		JSONObject element = (JSONObject) context;
	  		Object id = null;
            try {
                id = getElementValue(element, "sysmlid");
            } catch ( JSONException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

	  		return Utils.asList(id, String.class);
	  	}

	  	else {
	          // TODO -- error????  Are there any other contexts than an JSONObject that would have a property?
	          Debug.error("context is not an JSONObject!");
	          return null;
	    }
	}

	@Override
	public Collection<JSONObject> getProperty(Object context, Object specifier) {
		if ( context instanceof JSONObject ) {
			JSONObject jsonContext = (JSONObject) context;
			try {
                if( context != null && hasElementValue(jsonContext, "" + specifier) ) {
                	String id = (String) getElementValue(jsonContext, "" + specifier);
                	JSONObject element = getElement(id);
                	if( element != null ) {
                		return Utils.newList( element );	
                	}
                }
            } catch ( JSONException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
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
			String specifier) {
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
			String specifier) {
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
	public Collection<String> getType(Object context, Object specifier) {
		// Assuming that we can only have JSONObject context:
		// TODO: Take into account specifier??
	  	if (context instanceof JSONObject) {

	  		JSONObject element = (JSONObject) context;
	  		Object type = null;
            try {
                type = getElementValue(element, "type");
            } catch ( JSONException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

	  		return Utils.asList(type, String.class);
	  	}

	  	else {
	          // TODO -- error????  Are there any other contexts than an JSONObject that would have a property?
	          Debug.error("context is not an JSONObject!");
	          return null;
	    }
	}

	@Override
	public String getTypeString(Object context, Object specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithConstraint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithElement(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithIdentifier(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithName(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithProperty(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithRelationship(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithVersion(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithView(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithViewpoint(Object context,
			JSONObject specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getTypeWithWorkspace(Object context,
			String specifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Object> getValue(Object context, Object specifier) {
		if ( context instanceof JSONObject ) {
			JSONObject jsonContext = (JSONObject) context;
			try {
                if( context != null && hasElementValue(jsonContext, "" + specifier) ) {
                	String id = (String) getElementValue(jsonContext, "" + specifier);
                	Object element = getElement(id);
                	if( element != null ) {
                		return Utils.newList( element );	
                	}
                }
            } catch ( JSONException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
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
			String specifier) {
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
			String specifier) {
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
			String specifier) {
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
		
	protected Collection< JSONObject > searchForElement(String property, String value)
	{
		
		return null;
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
	public Collection<String> getTypeWithValue(Object context,
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
            Collection<JSONObject> elements;
            
            // Search for all elements with the name "Info":
            elements = systemModel.getElementWithName(null, "Info");
            System.out.println("\nItems with name 'Info' (" + elements.size() + "):");
            for( JSONObject element : elements.toArray(new JSONObject[0]) ){
            	System.out.println(systemModel.getName(element) + ": " + element.toString());
            }
            
        	// Search for all elements with the type "Expose":
            elements = systemModel.getElementWithType(null, "Expose");
            System.out.println("\nItems with type 'Expose' (" + elements.size() + "):");
            for( JSONObject element : elements.toArray(new JSONObject[0]) ){
            	System.out.println(systemModel.getType(element, null) + ": " + element.toString());
            	
            	// Print the source and target for this expose:
            	JSONObject source = systemModel.getSource(element).toArray(new JSONObject[0])[0];
            	JSONObject target = systemModel.getTarget(element).toArray(new JSONObject[0])[0];
            	System.out.println("\tsource: " + systemModel.getType( source, null ) + " " + systemModel.getName( source ) + " " + systemModel.getIdentifier( source ));
            	System.out.println("\ttarget: " + systemModel.getType( target, null ) + " " + systemModel.getName( target ) + " " + systemModel.getIdentifier( target ));
            }
            
            // Search for all elements with the sysmlid "_17_0_5_1_6050206_1414171540166_947652_15968":
            elements = systemModel.getElementWithIdentifier(null, "_17_0_5_1_6050206_1414171540166_947652_15968");
            System.out.println("\nItems with id '_17_0_5_1_6050206_1414171540166_947652_15968' (" + elements.size() + "):");
            for( JSONObject element : elements.toArray(new JSONObject[0]) ){
            	System.out.println(systemModel.getIdentifier(element) + ": " + element.toString());
            }
        
        } catch (Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }
}
