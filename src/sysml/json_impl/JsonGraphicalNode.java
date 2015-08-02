package sysml.json_impl;

import org.json.JSONObject;

public class JsonGraphicalNode extends JsonGraphicalElement
{
   public JsonGraphicalNode(JsonSystemModel systemModel, JSONObject jObj, JsonGraphicalElement parent)
   {
      super(systemModel, jObj, parent);
   }
}
