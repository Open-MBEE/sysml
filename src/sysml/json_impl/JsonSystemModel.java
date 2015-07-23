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

package sysml.json_impl;

import gov.nasa.jpl.mbee.util.Debug;
import gov.nasa.jpl.mbee.util.FileUtils;
import gov.nasa.jpl.mbee.util.Pair;
import gov.nasa.jpl.mbee.util.Utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.collections4.map.MultiValueMap;

import sysml.AbstractSystemModel;

public class JsonSystemModel
{

   public static final String NAME = "name";
   public static final String TYPE = "type";
   public static final String OWNER = "owner";
   public static final String SOURCE = "source";
   public static final String TARGET = "target";
   public static final String SYSMLID = "sysmlid";
   public static final String ELEMENTS = "elements";
   public static final String SPECIALIZATION = "specialization";
   public static final String VALUE = "value";   
   public static final String PROPERTY = "Property";    
   public static final String PROPERTY_TYPE = "propertyType";  
   public static final String APPLIED_METATYPES = "appliedMetatypes";
   public static final String CONTENTS = "contents";
   public static final String OPERAND = "operand";   
   public static final String INSTANCE = "instance";   
   public static final String ELEMENT = "element"; 
   public static final String _SLOT_ = "-slot-";    
   public static final String STRING = "string";
   public static final String LIST = "list";   
   public static final String INSTANCE_SPECIFICATION_SPECIFICATION = "instanceSpecificationSpecification";
   public static final String SOURCE_PATH = "sourcePath";
   public static final String TARGET_PATH = "targetPath";
   
   public static final String ST_BLOCK = "_11_5EAPbeta_be00301_1147424179914_458922_958"; 
   public static final String ST_PART = "_15_0_be00301_1199377756297_348405_2678";
   public static final String ST_VALUE_PROPERTY = "_12_0_be00301_1164123483951_695645_2041";
   public static final String ST_CONSTRAINT_BLOCK = "_17_0_1_42401aa_1327611546796_59249_12173";
   public static final String ST_CONSTRAINT_PROPERTY = "_11_5EAPbeta_be00301_1147767840464_372327_467";   
   public static final String ST_CONSTRAINT_PARAMETER = "_17_0_1_42401aa_1327611824171_784118_12184";   
   
   public static final String ST_BINDING_CONNECTOR = "_15_0_be00301_1204738115945_253883_5044";   
   public static final String ST_DEPENDENCY = "_16_5_4_409a058d_1259862803278_226185_1083";
   public static final String ST_VIEW = "_17_0_1_232f03dc_1325612611695_581988_21583";
   public static final String ST_VIEWPOINT = "_11_5EAPbeta_be00301_1147420812402_281263_364";
   public static final String ST_EXPOSE = "_16_5_4_409a058d_1259862803278_226185_1083";
   public static final String ST_CONFORMS = "_17_0_2_3_407019f_1389807639137_860750_29082";
   public static final String ST_DIAGRAM_INFO = "_9_0_be00301_1108044380615_150487_0";
   public static final String ST_DIAGRAM = "_9_0_62a020a_1106296071977_61607_0";   
   
   public static final String TAG_GENERATED_FROM_VIEW = "_17_0_5_1_407019f_1430628276506_565_12080";
   
   public static final String ID_COLLECT_PARAM_DIAGRAM_ELEMENTS_VIEWPOINT = "_18_0_2_f060354_1436758053298_656033_17322";
   
   // JSONObject that contains the JSON model:
   protected JSONObject root = null;

   // Map of element sysmlid to JSON element objects:
   protected Map<String, JSONObject> elementMap = new LinkedHashMap<String, JSONObject>();

   // Map of element sysmlid to List of sysmlids that that element owns
   protected Map<String, List<String>> ownershipMap = new LinkedHashMap<String, List<String>>();
   
   // Map of element sysmlid to List of views of the element
   protected MultiValueMap<String, String> viewMap = new MultiValueMap<String, String>();   
   
   // Map of view to viewpoint
   protected Map<String, String> viewpointMap = new LinkedHashMap<String, String>();     
   
   private final static Logger LOGGER = Logger.getLogger(JsonSystemModel.class.getName());

   public JsonSystemModel(String jsonString) throws JsonSystemModelException
   {
      readJson(jsonString);
   }

   public static void setLogLevel(Level level)
   {
      LOGGER.setLevel(level);   
   }
   
   public void readJson(String jsonString) throws JsonSystemModelException
   {
      try
      {
         JSONObject json = new JSONObject(jsonString);
         // Make sure JSON format contains what we expect:
         if (json.has(ELEMENTS))
         {
            JSONArray elements = json.getJSONArray(ELEMENTS);

            for (int i = 0; i < elements.length(); i++)
            {
               JSONObject jsonObj = elements.getJSONObject(i);

               // Make sure JSON format contains what we expect:
               if (jsonObj.has(SYSMLID))
               {
                  // Update element map
                  elementMap.put(jsonObj.getString(SYSMLID), jsonObj);

                  // Update ownership map
                  if (jsonObj.has(OWNER) && !jsonObj.isNull(OWNER))
                  {
                     String owner = jsonObj.getString(OWNER);
                     List<String> owned = ownershipMap.get(owner);
                     if (owned == null)
                     {
                        owned = new ArrayList<String>();
                        ownershipMap.put(owner, owned);
                     }
                     owned.add(jsonObj.getString(SYSMLID));
                  }
                  
                  if (isExpose(jsonObj))
                  {
                     Object source = getSpecializationProperty(jsonObj, SOURCE);
                     Object target = getSpecializationProperty(jsonObj, TARGET);
                     
                     if (!(source instanceof String))
                     {
                        LOGGER.log(Level.WARNING, "Source id is not a string for element: {0}", jsonObj);
                     }
                     else if (!(target instanceof String))
                     {
                        LOGGER.log(Level.WARNING, "Target id is not a string for element: {0}", jsonObj);
                     }
                     else
                     {
                        viewMap.put((String)target, (String)source);
                     }
                  }
                  
                  if (isConforms(jsonObj))
                  {
                     Object source = getSpecializationProperty(jsonObj, SOURCE);
                     Object target = getSpecializationProperty(jsonObj, TARGET);
                     
                     if (!(source instanceof String))
                     {
                        LOGGER.log(Level.WARNING, "Source id is not a string for element: {0}", jsonObj);
                     }
                     else if (!(target instanceof String))
                     {
                        LOGGER.log(Level.WARNING, "Target id is not a string for element: {0}", jsonObj);
                     }
                     else
                     {
                        viewpointMap.put((String)source, (String)target);
                     }                     
                  }
               } 
               else
               {
                  throw new JsonSystemModelException("Element has no sysmlid: " 
                     + jsonObj.toString());
               }
            }
         } 
         else
         {
            throw new JsonSystemModelException("Root element is not " + ELEMENTS);        
         }
      } 
      catch (Exception ex)
      {
         throw new JsonSystemModelException("Root element is not " + ELEMENTS, ex);
      }
   }
   
   public JsonBaseElement wrap(JSONObject jObj)
   {
      if (jObj == null)
      {
         return null;
      }
      else if (isBlock(jObj))
      {
         return new JsonBlock(this, jObj);
      }
      else if (isPart(jObj))
      {
         return new JsonPart(this, jObj);
      }      
      else if (isValueProperty(jObj))
      {
         return new JsonValueProperty(this, jObj);
      }      
      else if (isConstraintBlock(jObj))
      {
         return new JsonConstraintBlock(this, jObj);
      }
      else if (isConstraintProperty(jObj))
      {
         return new JsonConstraintProperty(this, jObj);
      } 
      else if (isConstraintParameter(jObj))
      {
         return new JsonConstraintParameter(this, jObj);
      }       
      else if (isBindingConnector(jObj))
      {
         return new JsonBindingConnector(this, jObj);
      }
      else if (isParametricDiagram(jObj))
      {
         return new JsonParametricDiagram(this, jObj);
      }
      else
      {
         return new JsonBaseElement(this, jObj);
      }
 
   }   
   
   
   public String getElementName(JSONObject element)
   {
      Object name = getJsonProperty(element, NAME);
      if (name instanceof String)
      {
         return (String) name;
      }
      else
      {
         throw new IllegalStateException("Element name is not string: " + name);
      }
   }

   public JSONObject getElement(String id)
   {
      if (elementMap.containsKey(id))
      {
         return elementMap.get(id);
      }
      return null;
   }

   public List<JSONObject> getOwnedElements(String id)
   {
      List<JSONObject> elements = new ArrayList<JSONObject>();
      if (id == null)
      {
         LOGGER.log(Level.WARNING, "elementid is null");
         return elements;
      }
      
      if (!ownershipMap.containsKey(id))
      {
         // the element does not own any elements.
         return elements;
      }      
      
      List<String> ownedIds = ownershipMap.get(id);
      
      for (String ownedId : ownedIds)
      {
         JSONObject child = getElement(ownedId);
         if (child != null)
         {
            elements.add(child);
         }
      }
      return elements;
   }
   
   public List<JSONObject> getSuperClasses(JSONObject element)
   {
      return null;
   }
   
   public boolean isBlock(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_BLOCK);
   }
   
   public boolean isPart(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_PART);
   }   

   public boolean isValueProperty(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_VALUE_PROPERTY);
   } 

   public boolean isConstraintBlock(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_CONSTRAINT_BLOCK);
   }
   
   public boolean isConstraintProperty(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_CONSTRAINT_PROPERTY);
   }   

   public boolean isConstraintParameter(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_CONSTRAINT_PARAMETER);
   }
   
   public boolean isBindingConnector(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_BINDING_CONNECTOR);
   }   

   public boolean isDiagram(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_DIAGRAM);
   }
   
   public boolean isParametricDiagram(JSONObject element)
   {
      if (!isDiagram(element))
      {
         return false;
      }
      
      List<JSONObject> jList = getParametricDiagramElements(element);
      
      return jList.size() > 0;
   }
   
   public boolean isView(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_VIEW);
   }
   
   public boolean isViewpoint(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_VIEWPOINT);
   }   

   public boolean isExpose(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_EXPOSE);
   }   

   public boolean isConforms(JSONObject element)
   {
      List<String> metaTypes = getAppliedMetaTypes(element);
      return metaTypes.contains(ST_CONFORMS);
   }   
   
   public List<JSONObject> getSourcePath(JSONObject bindingConnector)
   {
      ArrayList<JSONObject> pathElements = new ArrayList<JSONObject>();
      
      Object sourcePath = getSpecializationProperty(bindingConnector, SOURCE_PATH);
      if (sourcePath instanceof JSONArray)
      {
         JSONArray sourcePathJA = (JSONArray) sourcePath;
         for (int i=0; i < sourcePathJA.length(); i++)
         {
            String id = sourcePathJA.getString(i);
            JSONObject elem = getElement(id);
            if (elem == null)
            {
               LOGGER.log(Level.WARNING, "Element was not found: %s", id);
            }
            else
            {
               pathElements.add(elem);
            }
         }
      }
      else
      {
         LOGGER.log(Level.WARNING, "sourcePath is not a JSON array: %s", bindingConnector);
      }
      return pathElements;
   }
   
   public List<JSONObject> getTargetPath(JSONObject bindingConnector)
   {
      ArrayList<JSONObject> pathElements = new ArrayList<JSONObject>();
      
      Object targetPath = getSpecializationProperty(bindingConnector, TARGET_PATH);
      if (targetPath instanceof JSONArray)
      {
         JSONArray targetPathJA = (JSONArray) targetPath;
         for (int i=0; i < targetPathJA.length(); i++)
         {
            String id = targetPathJA.getString(i);
            JSONObject elem = getElement(id);
            if (elem == null)
            {
               LOGGER.log(Level.WARNING, "Element was not found: %s", id);
            }
            else
            {
               pathElements.add(elem);
            }
         }
      }
      else
      {
         LOGGER.log(Level.WARNING, "targetPath is not a JSON array: %s", bindingConnector);
      }
      return pathElements;
   }   
   
   
   private boolean isCollectParamElementsView(JSONObject element)
   {
      String id = getIdentifier(element);
      
      String viewpointID = viewpointMap.get(id);
      
      if (ID_COLLECT_PARAM_DIAGRAM_ELEMENTS_VIEWPOINT.equals(viewpointID))
      {
         return true;
      }
      
      return false;
   }  
   
   public List<JSONObject> getParametricDiagramView(String diagramID)
   {
      Collection<String> views = viewMap.getCollection(diagramID);
      
      ArrayList<JSONObject> parametricDiagramViews = new ArrayList<JSONObject>();
      
      if (views != null)
      {
         for (String viewID : views)
         {
            JSONObject view = getElementWithIdentifier(null, viewID);
            
            if (isCollectParamElementsView(view))
            {
               parametricDiagramViews.add(view);
            }
         }
      }
      return parametricDiagramViews;
   }
   

   public JSONGraphElement createJSONGraphElement(JSONObject element)
   {
      if (isBindingConnector(element))
      {
         return new JSONGraphEdge(element);
      }
      else if (isConstraintProperty(element))
      {
         return new JSONGraphNode(element);
      }
      else
      {
         LOGGER.log(Level.WARNING, "unsupported element type for graph element: %s", element);
      }
      return null;
   }
   
   public List<JSONGraphElement> getDiagramGraphElements(JSONObject parametricDiagram)
   {
      List<JSONGraphElement> graphElements = new ArrayList<JSONGraphElement>();
      
      List<JSONObject> diagramElements = getParametricDiagramElements(parametricDiagram);
      
      for (JSONObject diagramElement : diagramElements)
      {
         JSONGraphElement graphElement = createJSONGraphElement(diagramElement);
         if (graphElement != null)
         {
            graphElements.add(graphElement);
         }
      }
      
      return graphElements;
   }
   

   
   public List<JSONObject> getParts(JSONObject element)
   {
      Map<String, JSONObject> props = getElementProperties(element);
      List<JSONObject> parts = new ArrayList<JSONObject>();
      
      for (JSONObject prop : props.values())
      {
         if (isPart(prop))
         {
            parts.add(prop);
         }
      }
      
      return parts;
   }   
   
   public List<JSONObject> getValueProperties(JSONObject element)
   {
      Map<String, JSONObject> props = getElementProperties(element);
      List<JSONObject> valueProperties = new ArrayList<JSONObject>();
      
      for (JSONObject prop : props.values())
      {
         if (isValueProperty(prop))
         {
            valueProperties.add(prop);
         }
      }
      
      return valueProperties;
   }      
   
   public List<JSONObject> getConstraintParameters(JSONObject element)
   {
      Map<String, JSONObject> props = getElementProperties(element);
      List<JSONObject> parameters = new ArrayList<JSONObject>();
      
      for (JSONObject prop : props.values())
      {
         if (isConstraintParameter(prop))
         {
            parameters.add(prop);
         }
      }
      
      return parameters;      
   }

   protected String getType(JSONObject element)
   {
      Object type = getSpecializationProperty(element, TYPE);
      
      if (type == null)
      {
         return null;
      }
      if (!(type instanceof String))
      {
         LOGGER.log(Level.WARNING, "Type is not in understandable format: {0}", type.toString());
         return null;
      }
      
      return (String) type;
   }
   
   protected List<String> getAppliedMetaTypes(JSONObject element)
   {
      List<String> metaTypes = new ArrayList<String>();
      
      Object obj = getJsonProperty(element, APPLIED_METATYPES);
      if (obj instanceof JSONArray)
      {
         JSONArray objJA = (JSONArray)obj;

         for (int i = 0; i < objJA.length(); i++)
         {
            metaTypes.add(objJA.getString(i));

         }          
      }
      return metaTypes; 
   }

   protected Object getElementValue(JSONObject element)
   {
      return getSpecializationProperty(element, VALUE);
   }

   protected String getPropertyTypeID(JSONObject element)
   {
      Object id = getSpecializationProperty(element, PROPERTY_TYPE);
      if (id instanceof String)
      {
         return (String)id;
      }
      else
      {
         LOGGER.log(Level.WARNING, "propertyType is not a string of id: %s", element);
         return null;
      }
   }

   protected boolean hasElementProperty(JSONObject element, String name)
   {
      if (element != null)
      {
         // See if element has the jsonName field:
         if (element.has(name) && !element.isNull(name))
         {
            return true;
         }
      }
      return false;
   }
   
   protected boolean hasSpecializationProperty(JSONObject element, String propName)
         throws JSONException
   {
      if (element != null)
      {
         if (getSpecializationProperty(element, propName) != null)
         {
            return true;
         }
      }
      return false;
   }   

   protected Object getJsonProperty(JSONObject element, String propName)
   {
      if (element != null)
      {
         if (element.has(propName) && !element.isNull(propName))
         {
            return element.get(propName);
         }
      }
      return null;
   }
   
   protected Object getSpecializationProperty(JSONObject element, String name)
   {
      if (element.has(SPECIALIZATION))
      {
         Object specialization = element.get(SPECIALIZATION);
         if (specialization instanceof JSONObject)
         {
            JSONObject specializationJson = (JSONObject) specialization;
            if (specializationJson.has(name))
            {
               return specializationJson.get(name);
            }
         }
      }
      return null;
   }   

   // TODO: refactor to getParts and getValueProperties
   public Map<String, JSONObject> getElementProperties(JSONObject element)
         throws JSONException
   {      
      Map<String, JSONObject> propertyMap = new LinkedHashMap<String, JSONObject>();

      if (element != null)
      {
         List<JSONObject> children = getOwnedElements(element);

         for (JSONObject child : children)
         {
            // Make sure the children are of type "Property":
            if (PROPERTY.equals(getType(child)))
            {
               propertyMap.put(getName(child), child);
            }
         }
      }
      return propertyMap;
   }

   protected List<JSONObject> searchWithinContextByProperty(JSONObject owner,
         String propName, Object propValue)
   {
      List<JSONObject> elementList = new ArrayList<JSONObject>();
      
      if (owner == null)
         return elementList;

      Collection<JSONObject> children = getOwnedElements(owner);
      for (JSONObject child : children)
      {
         if (propValue == null
               || propValue.equals(getJsonProperty(child, propName)))
         {
            elementList.add(child);
         }

         List<JSONObject> childElementList = searchWithinContextByProperty(child,
               propName, propValue);

         elementList.addAll(childElementList);
      }

      return elementList;
   }
   
   protected List<JSONObject> searchWithinContextBySpecialization(JSONObject owner,
         String propName, Object propValue)
   {
      List<JSONObject> elementList = new ArrayList<JSONObject>();
      
      if (owner == null)
         return elementList;

      Collection<JSONObject> children = getOwnedElements(owner);
      for (JSONObject child : children)
      {
         if (propValue == null
               || propValue.equals(getSpecializationProperty(child, propName)))
         {
            elementList.add(child);
         }

         List<JSONObject> childElementList = searchWithinContextBySpecialization(child,
               propName, propValue);

         elementList.addAll(childElementList);
      }

      return elementList;
   }   

   protected List<JSONObject> searchForElementsByProperty(JSONObject context,
         String propName, Object propValue)
   {
      List<JSONObject> elementList = new ArrayList<JSONObject>();
      if (propName == null)
      {
         return elementList;
      }

      if (context == null)
      {
         // Search for element by going linearly through all the elements:
         for (JSONObject element : elementMap.values())
         {
            if (hasElementProperty(element, propName))
            {
               if (propValue == null
                     || getJsonProperty(element, propName).equals(propValue))
               {
                  elementList.add(element);
               }
            }
         }
      }      
      else
      {
         // Do depth first search within the context of the owner for a match:

         JSONObject owner = (JSONObject) context;
         elementList = searchWithinContextByProperty(owner, propName, propValue);
      }
      return elementList;
   }
   
   protected List<JSONObject> searchForElementsBySpecialization(JSONObject context,
         String propName, Object propValue)
   {
      List<JSONObject> elementList = new ArrayList<JSONObject>();
      if (propName == null)
      {
         return elementList;
      }

      if (context == null)
      {
         // Search for element by going linearly through all the elements:
         for (JSONObject element : elementMap.values())
         {
            if (hasSpecializationProperty(element, propName))
            {
               if (propValue == null
                     || getSpecializationProperty(element, propName).equals(propValue))
               {
                  elementList.add(element);
               }
            }
         }
      }      
      else
      {
         // Do depth first search within the context of the owner for a match:
         JSONObject owner = (JSONObject) context;
         elementList = searchWithinContextBySpecialization(owner, propName, propValue);
      }
      return elementList;
   }   
   
   public List<JSONObject> getOwnedElements(JSONObject owner)
   {
      ArrayList<JSONObject> elements = new ArrayList<JSONObject>();
      if (owner == null)
      {
         throw new IllegalArgumentException("owner cannot be null");
      }
      
      if (!owner.has(SYSMLID) || owner.isNull(SYSMLID))
      {
         LOGGER.log(Level.WARNING, "sysmlid is not in a valid format: {0}", owner.toString());
         return elements;     
      }
      
      String id = (String) owner.get(SYSMLID);
      return getOwnedElements(id);
   }   

   public JSONObject getOwner(JSONObject element)
   {
      Object prop = getJsonProperty(element, OWNER);
      if (prop instanceof String)
      {
         String id = (String) prop;
         return getElementWithIdentifier(null, id);
      }
      return null;
   }
   
   
   // override
   public JSONObject getSource(JSONObject relationship)
   {
      Object prop = getSpecializationProperty(relationship, SOURCE);
      if (prop instanceof String)
      {
         String id = (String) prop;
         return getElementWithIdentifier(null, id);
      }
      
      List<JSONObject> sourceSequence = getSourcePath(relationship);
      if (sourceSequence.size() > 0)
      {
         return sourceSequence.get(sourceSequence.size()-1);
      }
      
      return null; 
   }

   // override   
   public JSONObject getTarget(JSONObject relationship)
   {
      Object prop = getSpecializationProperty(relationship, TARGET);
      if (prop instanceof String)
      {
         String id = (String) prop;
         return getElementWithIdentifier(null, id);
      }
      
      List<JSONObject> targetSequence = getSourcePath(relationship);
      if (targetSequence.size() > 0)
      {
         return targetSequence.get(targetSequence.size()-1);
      }      
      return null;      
   }

   // override   
   public JSONObject getElementWithIdentifier(JSONObject context,
         String id)
   {
      if (id == null)
      {
         return null;
      }
      if (context == null)
      {
         return elementMap.get(id);
      }
      
      List<JSONObject> elems = searchForElementsByProperty(context, SYSMLID, id);
      
      if (elems.size() == 0)
      {
         return null;
      }
      else if (elems.size() > 1)
      {
         throw new IllegalStateException(String.format("There are more than one element with the same id: %s", id));
      }
      
      return elems.get(0);
   }

   // override   
   public Collection<JSONObject> getElementWithName(JSONObject context,
         String name)
   {
      if (name == null)
      {
         return null;
      }
      return searchForElementsByProperty(context, NAME, name);
   }


   /**
    * Get child elements of the given type. The search is recursive following containment
    * relationships.
    * 
    * @param context owner
    * @param specifier type of child elements to be searched
    */
   // override
   public Collection<JSONObject> getElementWithType(JSONObject context,
         String specifier)
   {
      if (specifier == null)
      {
         return null;
      }
      return searchForElementsBySpecialization(context, TYPE, specifier);
   }

   // override
   public String getName(JSONObject context)
   {
      // Note: This returns the sysml:name not the cm:name, which is what we
      // want
      Object name = null;
      try
      {
         name = getJsonProperty(context, NAME);
      } 
      catch (JSONException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      if (name instanceof String)
      {
         return (String) name;
      }

      return null;
   }

   // override   
   public String getIdentifier(JSONObject context)
   {
      Object id = null;
      try
      {
         id = getJsonProperty(context, SYSMLID);
      } catch (JSONException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      if (id == null)
         return null;
      return id.toString();// Utils.asList(id, String.class);
   }

   // override   
   public Collection<JSONObject> getPropertyWithIdentifier(JSONObject context,
         String specifier)
   {

      Map<String, JSONObject> properties = null;
      try
      {
         properties = getElementProperties(context);
      } 
      catch (JSONException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      if (properties != null)
      {
         Collection<JSONObject> propertiesToReturn = new ArrayList<JSONObject>();
         for (JSONObject property : properties.values())
         {
            // Make sure property type matches the specifier:
            JSONObject jsonProperty = (JSONObject) property;
            String id = getIdentifier(jsonProperty);
            if (id != null && id.equals(specifier))
            {
               propertiesToReturn.add(jsonProperty);
            }
         }

         if (!propertiesToReturn.isEmpty())
            return propertiesToReturn;
      }

      return null;
   }

   // override, not used currently
   public Collection<JSONObject> getPropertyWithType(JSONObject context,
         String specifier)
   {

      Map<String, JSONObject> properties = null;
      try
      {
         properties = getElementProperties(context);
      } 
      catch (JSONException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      Collection<JSONObject> propertiesToReturn = new ArrayList<JSONObject>();
      for (JSONObject property : properties.values())
      {
         // Make sure property type matches the specifier:
         String propertyTypeID = getPropertyTypeID(property);
         JSONObject propertyType = getElement(propertyTypeID);
         if (propertyType != null)
         {
            Object propertyTypeName = getJsonProperty(propertyType, NAME);
            if (specifier.equals(propertyTypeName))
            {
               propertiesToReturn.add(property);
            }
         }
      }

      return propertiesToReturn;
   }

   // override   
   public Collection<String> getType(JSONObject context, Object specifier)
   {
      Object type = null;
      try
      {
         type = hasElementProperty(context, TYPE);
      } 
      catch (JSONException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return Utils.asList(type, String.class);
   }
   
   protected List<JSONObject> getParametricDiagramElements(JSONObject parametricDiagram)
   {
      ArrayList<JSONObject> diagramElements = new ArrayList<JSONObject>();
      
      String id = getIdentifier(parametricDiagram);
      
      List<JSONObject> diagramViews = getParametricDiagramView(id);
      
      for (JSONObject diagramView : diagramViews)
      {
         String viewID = getIdentifier(diagramView);
         Object contents = getSpecializationProperty(diagramView, CONTENTS);
         if (contents instanceof JSONObject)
         {
            JSONObject contentsJ = (JSONObject) contents;
            if (contentsJ.has(OPERAND) && !contentsJ.isNull(OPERAND))
            {
               Object operand = contentsJ.get(OPERAND);
               if (operand instanceof JSONArray)
               {
                  JSONArray operandJA = (JSONArray)operand;
                  for (int i=0; i < operandJA.length(); i++)
                  {
                     Object oper = operandJA.get(i);
                     if (oper instanceof JSONObject)
                     {
                        JSONObject operJ = (JSONObject)oper;
                        String instanceID = operJ.getString(INSTANCE);
                        
                        JSONObject generatedFromViewTag = getElement(instanceID + _SLOT_ + 
                              TAG_GENERATED_FROM_VIEW);
                        
                        if (generatedFromViewTag != null)
                        {
                           Object value = getSpecializationProperty(generatedFromViewTag, VALUE);
                           if (value instanceof JSONArray)
                           {
                              JSONArray valueJA = (JSONArray) value;
                              for (int i2=0; i2 < valueJA.length(); i2++)
                              {
                                 Object v = valueJA.get(i2);
                                 if (v instanceof JSONObject)
                                 {
                                    JSONObject vJ = (JSONObject) v;
                                    if (vJ.has(ELEMENT) && !vJ.isNull(ELEMENT))
                                    {
                                       String elementID = vJ.getString(ELEMENT);
                                       if (viewID.equals(elementID))
                                       {
                                          JSONObject instance = getElement(instanceID);
                                          if (instance != null)
                                          {
                                             diagramElements.addAll(getElementsFromViewInstance(instance));
                                          }
                                          else
                                          {
                                             LOGGER.log(Level.WARNING, "View instance is not found: %s", instanceID);
                                          }
                                       }
                                    }                                    
                                 }
                              }
                           }
                           else
                           {
                              LOGGER.log(Level.WARNING, "specialization value is not a json array: %s", instanceID);
                           }
                        }
                        else
                        {
                           LOGGER.log(Level.WARNING, "generatedFromView tag is null: %s", instanceID);
                        }
                        
                     }
                     else
                     {
                        LOGGER.log(Level.WARNING, "element of operand is not a JSON array: %s", oper);
                     }
                  }
               }
               else
               {
                  LOGGER.log(Level.WARNING, "operand is not a JSON array: %s", operand);
               }
            }
         }
         else
         {
            LOGGER.log(Level.WARNING, "contents is not a JSON object");
         }
      }
      
      return diagramElements;
   }   
   
   private List<JSONObject> getElementsFromViewInstance(JSONObject viewInstance)
   {
      ArrayList<JSONObject> elements = new ArrayList<JSONObject>();
      
      Object specSpecification = getSpecializationProperty(viewInstance, 
            INSTANCE_SPECIFICATION_SPECIFICATION);
      if (specSpecification instanceof JSONObject)
      {
         JSONObject specSpecificationJ = (JSONObject) specSpecification;
         if (specSpecificationJ.has(STRING) && !specSpecificationJ.isNull(STRING))
         {
            String valueStr = specSpecificationJ.getString(STRING);
            JSONObject jValue = new JSONObject(valueStr);
            
            if (jValue.has(LIST))
            {
               JSONArray listElems = jValue.getJSONArray(LIST);

               for (int i = 0; i < listElems.length(); i++)
               {
                  Object listElem = listElems.get(i);
                  if (listElem instanceof JSONArray)
                  {
                     JSONArray listElemJA = (JSONArray)listElem;
                     if (listElemJA.length() > 0)
                     {
                        Object listElem0 = listElemJA.get(0);
                        if (listElem0 instanceof JSONObject)
                        {
                           JSONObject listElem0J = (JSONObject)listElem0;
                           if (listElem0J.has(SOURCE) && !listElem0J.isNull(SOURCE))
                           {
                              String sourceID = listElem0J.getString(SOURCE);
                              JSONObject elem = getElement(sourceID);
                              if (elem != null)
                              {
                                 elements.add(elem);
                              }
                              else
                              {
                                 LOGGER.log(Level.WARNING, "element was not found: %s", sourceID);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      return elements;
   }   
         
}