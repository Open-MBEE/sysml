/**
 *
 */
package sysml;

import java.util.List;

import sysml.json_impl.JsonBaseElement;

/**
 *
 */
public interface Connector<N, I, D> extends BaseElement<N, I, D> {

   public JsonBaseElement getTarget();   
   public JsonBaseElement getSource();    
   public List<? extends JsonBaseElement> getTargetPath();
   public List<? extends JsonBaseElement> getSourcePath();

}
