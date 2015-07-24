/**
 *
 */
package sysml.json_impl;

import gov.nasa.jpl.mbee.util.CompareUtils;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import sysml.BaseElement;
import sysml.Version;
import sysml.Workspace;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 */
public class JsonBaseElement implements BaseElement<String, String, Date>, Comparable<JsonBaseElement>
{

   JsonSystemModel systemModel;
   String id;
   String name;
   JSONObject jsonObj;

   String qualifiedName = null;
   String qualifiedId = null;

   private final static Logger LOGGER = Logger.getLogger(JsonBaseElement.class.getName());

   public static void setLogLevel(Level level)
   {
      LOGGER.setLevel(level);
   }

   public JsonBaseElement(JsonSystemModel systemModel, String id)
   {

      this.systemModel = systemModel;
      this.id = id;
      this.jsonObj = systemModel.getElement(id);
      this.name = systemModel.getElementName(this.jsonObj);
   }

   public JsonBaseElement(JsonBaseElement elem)
   {
      this(elem.systemModel, elem.id);
   }

   public JsonBaseElement(JsonSystemModel systemModel, JSONObject jObj)
   {
      this.systemModel = systemModel;
      this.jsonObj = jObj;
      this.id = systemModel.getIdentifier(jObj);
      this.name = systemModel.getElementName(jObj);
   }

   @Override
   public String getId()
   {
      return id;
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public String getQualifiedName()
   {
      Object obj = systemModel.getJsonProperty(jsonObj, JsonSystemModel.QUALIFIED_ID);
      if (obj instanceof String)
      {
         return (String)obj;
      }
      else
      {
         LOGGER.log(Level.WARNING, "Unexpected format for qualified name: %s", jsonObj);
         return null;
      }
   }

   @Override
   public JsonBaseElement getOwner()
   {
      JSONObject elemJ = systemModel.getElement(id);
      JSONObject ownerJ = systemModel.getOwner(elemJ);

      return systemModel.wrap(ownerJ);
   }
   
   @Override
   public List<JsonBaseElement> getOwnedElements()
   {
      List<JsonBaseElement> elems = new ArrayList<JsonBaseElement>();
      List<JSONObject> jList = systemModel.getOwnedElements(jsonObj);
      for (JSONObject jObj : jList)
      {
         elems.add(systemModel.wrap(jObj));
      }

      return elems;
   }   

   @Override
   public BaseElement<String, String, Date> getProject()
   {
      Object qId = systemModel.getJsonProperty(jsonObj, JsonSystemModel.QUALIFIED_ID);
      if (qId instanceof String)
      {
         StringTokenizer stk = new StringTokenizer((String) qId, "/");
         if (stk.countTokens() < 2)
         {
            LOGGER.log(Level.WARNING, "Unexpected format of qualified id: %s", jsonObj);
            return null;
         } else
         {
            // first segment is site name
            stk.nextToken();
            // second segment is the project id
            String projectId = stk.nextToken();

            JSONObject jProject = systemModel.getElement(projectId);
            return systemModel.wrap(jProject);
         }
      } else
      {
         LOGGER.log(Level.WARNING, "Unexpected type of qualified id: %s", jsonObj);
         return null;
      }
   }
   
   @Override
   public boolean isStereotypedAs(String name)
   {
      String stereotypeId = JsonSystemModel.getStereotypeID(name);
      if (stereotypeId != null)
      {
         List<String> metaTypeIds = systemModel.getAppliedMetaTypes(jsonObj);
         for (String metaTypeId: metaTypeIds)
         {
            if (stereotypeId.equals(metaTypeId))
            {
               return true;
            }
         }
      }
      else
      {
         LOGGER.log(Level.WARNING, "Unknown stereotype: %s", name);
      }
      
      return false;
   }
   
   @Override
   public String getTagValue(String name)
   {
      String tagId = JsonSystemModel.getTagID(name);
      if (tagId != null)
      {
         List<JSONObject> jList = systemModel.getOwnedElements(jsonObj);
         for (JSONObject jObj : jList)
         {
            String type = systemModel.getType(jObj);
            if (JsonSystemModel.INSTANCE_SPECIFICATION.equals(type))
            {
               // Object classifier = systemModel.getSpecializationProperty(jObj, JsonSystemModel.CLASSIFIER);
               // if (classifier instanceof JSONArray)
               // {
               //   JSONArray jaClassifier = (JSONArray)classifier; 
               // }
               
               List<JSONObject> jTagProperties = systemModel.getOwnedElements(jObj);
               for (JSONObject jTagProperty : jTagProperties)
               {
                  String propTypeId = systemModel.getPropertyTypeID(jTagProperty);
                  if (tagId.equals(propTypeId))
                  {          
                     if (systemModel.isSlot(jTagProperty))
                     {
                        JsonSlot slot = (JsonSlot)systemModel.wrap(jTagProperty);
                        
                        JsonPropertyValues propValues = slot.getValue();
                        if (propValues.getLength() == 0)
                        {
                           LOGGER.log(Level.WARNING, "No value for tag: %s", name);
                        }
                        else
                        {
                           if (propValues.getLength() > 1)
                           {
                              LOGGER.log(Level.WARNING, "Multiple values for tag. Return the first one: %s", name);
                           }
                           
                           Object propValue = propValues.getValue(0);
                           if (propValue instanceof String)
                           {
                              return (String)propValue;
                           }
                           else if (propValue instanceof Double)
                           {
                              return "" + propValue;
                           }
                           else if (propValue instanceof Integer)
                           {
                              return "" + propValue;
                           }  
                           else if (propValue instanceof JSONObject)
                           {
                              // TODO: for now return element name.
                              return systemModel.getElementName((JSONObject)propValue);
                           }                       
                        }
                     }
                  }
               }
            }
         }
      }
      else
      {
         LOGGER.log(Level.WARNING, "Unknown tag: %s", name);
      }
      
      return null;
   }

   @Override
   public Workspace<String, String, Date> getWorkspace()
   {
      return null;
   }

   @Override
   public List<Version<String, Date, BaseElement<String, String, Date>>> getVersions()
   {
      return null;
   }

   @Override
   public Map<Date, Version<String, Date, BaseElement<String, String, Date>>> getVersionMap()
   {
      return null;
   }

   @Override
   public Version<String, Date, BaseElement<String, String, Date>> getLatestVersion()
   {
      return null;
   }

   @Override
   public Version<String, Date, BaseElement<String, String, Date>> getVersion()
   {
      return null;
   }

   @Override
   public Version<String, Date, BaseElement<String, String, Date>> getVersion(Date dateTime)
   {
      return null;
   }

   @Override
   public Date getCreationTime()
   {
      return null;
   }

   @Override
   public Date getModifiedTime()
   {
      return null;
   }

   @Override
   public void setVersion(Version<String, Date, BaseElement<String, String, Date>> version)
   {
      // TODO: implement
   }

   @Override
   public int compareTo(JsonBaseElement o)
   {
      // NOTE: simply check the element id
      return CompareUtils.compare(getId(), o.getId());
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null)
         return false;

      if (o instanceof JsonBaseElement)
      {
         return compareTo((JsonBaseElement) o) == 0;
      } else
      {
         return false;
      }
   }

   @Override
   public JsonBaseElement clone() throws CloneNotSupportedException
   {
      return new JsonBaseElement(this);
   }

   @Override
   public int hashCode()
   {
      return getId().hashCode();
   }
}
