package sysml.json_impl;

public class JsonSystemModelException extends Exception
{
   public JsonSystemModelException(String msg)
   {
      super(msg);
   }
   
   public JsonSystemModelException(String msg, Throwable cause)
   {
      super(msg, cause);
   }
}
