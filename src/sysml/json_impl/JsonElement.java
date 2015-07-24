/**
 *
 */
package sysml.json_impl;


import gov.nasa.jpl.mbee.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import sysml.Element;
import sysml.Property;

/**
 *
 */
public class JsonElement extends JsonBaseElement implements
      Element<String, String, Date>
{

   public JsonElement(JsonSystemModel systemModel, JSONObject jObj)
   {
      super(systemModel, jObj);
   }

   @Override
   public Collection<Element<String, String, Date>> getSuperClasses()
   {
      List<Element<String, String, Date>> classes = new ArrayList<Element<String, String, Date>>();
      List<JSONObject> jList = systemModel.getSuperClasses(systemModel
            .getElement(id));
      for (JSONObject jObj : jList)
      {
         JsonBaseElement bElem = systemModel.wrap(jObj);
         if (bElem instanceof JsonElement)
         {
            classes.add((JsonElement) bElem);
         }
      }

      return classes;
   }

   @Override
   public Collection<Property<String, String, Date>> getProperties()
   {
      List<Property<String, String, Date>> props = new ArrayList<Property<String, String, Date>>();
      
      Map<String, JSONObject> jProps = systemModel.getElementProperties(jsonObj);
      for (JSONObject jProp : jProps.values())
      {
         JsonBaseElement elem = systemModel.wrap(jProp);
         if (elem instanceof JsonProperty)
         {
            props.add((JsonProperty)elem);
         }
      }
      return props;
   }

   @Override
   public Collection<Property<String, String, Date>> getProperty(
         Object specifier)
   {
      if (specifier == null)
         return null;
      
      Collection<Property<String, String, Date>> props = null;
      Property<String, String, Date> prop = getPropertyWithIdentifier(specifier
            .toString());
      if (prop != null)
      {
         props = new ArrayList<Property<String, String, Date>>();
         props.add(prop);
      }
      if (props == null || props.isEmpty())
      {
         props = getPropertyWithName(name);
      }
      if (props == null || props.isEmpty())
      {
         props = getPropertyWithValue(name);
      }
      return props;
   }

   public Property<String, String, Date> getSingleProperty(Object specifier)
   {
      Collection<Property<String, String, Date>> props = getProperty(specifier);
      if (Utils.isNullOrEmpty(props))
      {
         return null;
      }
      return props.iterator().next();
   }

   @Override
   public Property<String, String, Date> getPropertyWithIdentifier(String id)
   {
      if (id == null)
         return null;
      
      Collection<Property<String, String, Date>> props = getProperties();
      for (Property<String, String, Date> prop : props)
      {
         if (id.equals(prop.getId()))
         {
            return prop;
         }
      }
      
      return null;
   }

   @Override
   public Collection<Property<String, String, Date>> getPropertyWithName(
         String name)
   {
      ArrayList<Property<String, String, Date>> list = new ArrayList<Property<String, String, Date>>();
      for (Property<String, String, Date> prop : getProperties())
      {
         if (prop.getName() != null && prop.getName().equals(name))
         {
            list.add(prop);
         }
      }
      return list;
   }

   @Override
   public Collection<Property<String, String, Date>> getPropertyWithType(
         Element<String, String, Date> type)
   {
      ArrayList<Property<String, String, Date>> list = new ArrayList<Property<String, String, Date>>();
      for (Property<String, String, Date> prop : getProperties())
      {
         if (prop.getType() != null && prop.getType().equals(type))
         {
            list.add(prop);
         }
      }
      return list;
   }

   @Override
   public Collection<Property<String, String, Date>> getPropertyWithValue(
         Object value)
   {
      ArrayList<Property<String, String, Date>> list = new ArrayList<Property<String, String, Date>>();
      for (Property<String, String, Date> prop : getProperties())
      {
         if (prop.getValue() != null && prop.getValue().equals(value))
         {
            list.add(prop);
         }
      }
      return list;
   }
}
