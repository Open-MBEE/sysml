package sysml.json_impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class JsonInstanceSpecification extends JsonBaseElement
{
   private final static Logger LOGGER = Logger.getLogger(JsonInstanceSpecification.class.getName());   
   
   public static void setLogLevel(Level level)
   {
      LOGGER.setLevel(level);   
   }    
   
   public JsonInstanceSpecification(JsonSystemModel systemModel, JSONObject jObj)
   {
      super(systemModel, jObj);
   }
   
   public JsonElement getClassifier()
   {
      String classifierID = systemModel.getClassifierID(jsonObj);

      if (classifierID == null)
         return null;

      JSONObject jTypeObj = systemModel.getElement(classifierID);
      JsonBaseElement typeObj = systemModel.wrap(jTypeObj);

      if (typeObj instanceof JsonElement)
      {
         return (JsonElement) typeObj;
      }
      else
      {
         LOGGER.log(Level.WARNING, "Classifier of an instance specification is not an element: %s", typeObj);
      }      
      return null;
   }   
}
