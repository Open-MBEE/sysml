package sysml.json_impl;

import org.json.JSONObject;

public abstract class JsonGraphicalElement
{
   protected String id;
   protected JSONObject jsonObj;
   protected JsonSystemModel systemModel;
   protected JsonGraphicalElement parent;
   
   public JsonGraphicalElement(JsonSystemModel systemModel, String id, JsonGraphicalElement parent)
   {

      this.systemModel = systemModel;
      this.id = id;
      this.jsonObj = systemModel.getElement(id);
      this.parent = parent;  
   }


   public JsonGraphicalElement(JsonSystemModel systemModel, JSONObject jObj, JsonGraphicalElement parent)
   {
      this.systemModel = systemModel;
      this.jsonObj = jObj;
      this.id = systemModel.getIdentifier(jObj);
      this.parent = parent;
   }   
   
   public JSONObject getModelElement()
   {
      return systemModel.getElement(id);
   }
   
   public JsonGraphicalElement getParent()
   {
      return parent;
   }
}
