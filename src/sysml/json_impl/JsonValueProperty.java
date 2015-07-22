package sysml.json_impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class JsonValueProperty extends JsonProperty
{
   private final static Logger LOGGER = Logger.getLogger(JsonValueProperty.class.getName());   
   
   public static void setLogLevel(Level level)
   {
      LOGGER.setLevel(level);   
   }    
   
   public JsonValueProperty(JsonSystemModel systemModel, JSONObject jObj)
   {
      super(systemModel, jObj);
   }
   
   public Object getDefaultValue()
   {
      JsonPropertyValues values = getValue();
      if (values.getLength() == 0)
      {
         return null;
      }
      
      // TODO: return multiple values?
      return values.getValue(0);
   }
   
   // TODO: override getType
   // TODO: getUnits
}
