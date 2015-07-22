package sysml.json_impl;

import gov.nasa.jpl.mbee.util.CompareUtils;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import sysml.Element;
import sysml.Property;

public class JsonProperty extends JsonBaseElement implements
      Property<String, String, Date>
{
   private final static Logger LOGGER = Logger.getLogger(JsonProperty.class.getName());   
   
   public static void setLogLevel(Level level)
   {
      LOGGER.setLevel(level);   
   }    
   
   public JsonProperty(JsonSystemModel systemModel, JSONObject jObj)
   {
      super(systemModel, jObj);
   }

   @Override
   public int compareTo(Property<String, String, Date> o)
   {
      // NOTE: simply check the element id
      return CompareUtils.compare(getId(), o.getId());
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null)
         return false;

      if (o instanceof Property)
      {
         return compareTo((Property) o) == 0;
      } 
      else
      {
         return false;
      }
   }

   @Override
   public Element<String, String, Date> getType()
   {
      String propID = systemModel.getPropertyTypeID(jsonObj);

      if (propID == null)
         return null;

      JSONObject jTypeObj = systemModel.getElement(propID);
      JsonBaseElement typeObj = systemModel.wrap(jTypeObj);

      if (typeObj instanceof Element)
      {
         return (Element) typeObj;
      }
      return null;
   }

   @Override
   public JsonPropertyValues getValue()
   {
      Object value = systemModel.getSpecializationProperty(jsonObj, JsonSystemModel.VALUE);
      if (value instanceof JSONArray)
      {
         JSONArray jArray = (JSONArray)value;
         return new JsonPropertyValues(systemModel, jArray);
      }
      else
      {
         LOGGER.log(Level.WARNING, "Property value is not in array form: %s", value);
         return null;
      }
   }
}
