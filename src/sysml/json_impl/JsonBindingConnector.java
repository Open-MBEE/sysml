/**
 *
 */
package sysml.json_impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;


/**
 *
 */
public class JsonBindingConnector extends JsonBaseElement
{
   private final static Logger LOGGER = Logger.getLogger(JsonBindingConnector.class.getName());   
   
   public JsonBindingConnector(JsonSystemModel systemModel, JSONObject jObj)
   {
      super(systemModel, jObj);
   }

   public static void setLogLevel(Level level)
   {
      LOGGER.setLevel(level);   
   }
   
   public List<JsonBaseElement> getTargetPath()
   {
      List<JsonBaseElement> pathElems = new ArrayList<JsonBaseElement>();
      List<JSONObject> jPathElems = systemModel.getTargetPath(jsonObj);
      for (JSONObject jPathElem : jPathElems)
      {
         pathElems.add(systemModel.wrap(jPathElem));
      }
      
      return pathElems;
   }
   
   public List<JsonBaseElement> getSourcePath()
   {
      List<JsonBaseElement> pathElems = new ArrayList<JsonBaseElement>();
      List<JSONObject> jPathElems = systemModel.getSourcePath(jsonObj);
      for (JSONObject jPathElem : jPathElems)
      {
         pathElems.add(systemModel.wrap(jPathElem));
      }
      
      return pathElems;
   }   
}
