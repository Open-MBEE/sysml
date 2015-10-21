/**
 *
 */
package sysml.json_impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 */
public class JsonConstraintParameter extends JsonBaseElement
{
   private final static Logger LOGGER = Logger.getLogger(JsonConstraintParameter.class.getName());   
   
   public JsonConstraintParameter(JsonSystemModel systemModel, JSONObject jObj)
   {
      super(systemModel, jObj);
   }

   public static void setLogLevel(Level level)
   {
      LOGGER.setLevel(level);   
   }
}
