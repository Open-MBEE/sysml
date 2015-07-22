package sysml.json_impl;

import org.json.JSONObject;

public abstract class JSONGraphElement
{
   protected JSONObject modelElement;
   public JSONObject getModelElement()
   {
      return modelElement;
   }
}
