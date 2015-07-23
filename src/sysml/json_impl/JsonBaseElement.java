/**
 *
 */
package sysml.json_impl;

import gov.nasa.jpl.mbee.util.CompareUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import sysml.BaseElement;
import sysml.Version;
import sysml.Workspace;

import org.json.JSONObject;

/**
 *
 */
public class JsonBaseElement implements BaseElement< String, String, Date >, Comparable<JsonBaseElement> {

    JsonSystemModel systemModel;
    String id;
    String name;
    JSONObject jsonObj;

    String qualifiedName = null;
    String qualifiedId = null;
    
    public JsonBaseElement(JsonSystemModel systemModel,
          String id) {
       
        this.systemModel = systemModel;
        this.id = id;
        this.jsonObj = systemModel.getElement(id);
        this.name = systemModel.getElementName(this.jsonObj);
    }    

    public JsonBaseElement(JsonBaseElement elem) {
       this(elem.systemModel, elem.id);
    }    

    public JsonBaseElement(JsonSystemModel systemModel,
          JSONObject jObj) {
        this.systemModel = systemModel;
        this.jsonObj = jObj;
        this.id = systemModel.getIdentifier(jObj);
        this.name = systemModel.getElementName(jObj);
    }    
    
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public BaseElement<String, String, Date> getOwner()
    {
       JSONObject elemJ = systemModel.getElement(id);
       JSONObject ownerJ = systemModel.getOwner(elemJ);
       
       return systemModel.wrap(ownerJ);
    }

    @Override
    public Workspace< String, String, Date > getWorkspace() {
        return null;
    }

    @Override
    public List< Version< String, Date, BaseElement< String, String, Date > > > getVersions() {
        return null;
    }

    @Override
    public Map< Date, Version< String, Date, BaseElement< String, String, Date > > > getVersionMap() {
        return null;
    }

    @Override
    public Version< String, Date, BaseElement< String, String, Date > > getLatestVersion() {
        return null;
    }

    @Override
    public Version< String, Date, BaseElement< String, String, Date > > getVersion() {
        return null;
    }

    @Override
    public Version< String, Date, BaseElement< String, String, Date > > getVersion( Date dateTime ) {
        return null;
    }

    @Override
    public Date getCreationTime() {
        return null;
    }

    @Override
    public Date getModifiedTime() {
        return null;
    }

    @Override
    public void setVersion( Version< String, Date, BaseElement< String, String, Date > > version ) {
        // TODO: implement
    }
    
    @Override
    public int compareTo(JsonBaseElement o)
    {
       // NOTE: simply check the element id
       return CompareUtils.compare(getId(), o.getId());
    }
    
    @Override
    public boolean equals(Object o)
    {
       if (o == null)
          return false;

       if (o instanceof JsonBaseElement)
       {
          return compareTo((JsonBaseElement) o) == 0;
       } 
       else
       {
          return false;
       }
    }    
    
    @Override
    public JsonBaseElement clone() throws CloneNotSupportedException
    {
       return new JsonBaseElement(this);
    }
    
    @Override
    public int hashCode()
    {
       return getId().hashCode();
    }    
}
