/*******************************************************************************
 * Copyright (c) <2014>, California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice, this list of
 *    conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, this list
 *    of conditions and the following disclaimer in the documentation and/or other materials
 *    provided with the distribution.
 *  - Neither the name of Caltech nor its operating division, the Jet Propulsion Laboratory,
 *    nor the names of its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package sysml.json_impl.examples;

import sysml.json_impl.JsonSystemModel;
import sysml.json_impl.JsonBlock;
import sysml.json_impl.JsonPart;
import sysml.json_impl.JsonConstraintBlock;
import sysml.json_impl.JsonConstraintProperty;
import sysml.json_impl.JsonParametricDiagram;
import sysml.json_impl.JsonBindingConnector;
import sysml.json_impl.JsonBaseElement;


import gov.nasa.jpl.mbee.util.FileUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sysml.AbstractSystemModel;

public class JsonSystemModelTest
{
   /**
    * @param args
    */
   public static void main(String[] args) throws Exception
   {
      if (args.length < 1)
      {
         System.err.println("Must provide JSON filename as argument!");
         System.exit(1);
      }

      System.out.println(String.format("JSON file: %s", args[0]));
      
      String jsonString = FileUtils.fileToString(args[0]);
      JsonSystemModel systemModel = new JsonSystemModel(jsonString);
      JsonSystemModel.setLogLevel(Level.FINE);
      Collection<JSONObject> elements;

      // Search for all elements with name "Bike"
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "Bike");
      if (elements.size() == 0)
      {
         throw new Exception("No element was found");   
      }
      else if (elements.size() > 1)
      {
         throw new Exception("More than one element was found");   
      }         
      Iterator<JSONObject> iter = elements.iterator();       
      JSONObject jBike = iter.next();
      
      //
      JsonBlock bike = (JsonBlock)systemModel.wrap(jBike);
      List<JsonPart> parts = bike.getParts();
      System.out.println(String.format("Block: %s (%s)", bike.getName(), bike.getId()));
      for (JsonPart part : parts)
      {
         JsonBlock partType = part.getType();
         System.out.println(String.format("   %s (%s)  type: %s", part.getName(), part.getId(), partType.getName()));
      }

      List<JsonParametricDiagram> parametricDiagrams = bike.getParametricDiagrams();
      for (JsonParametricDiagram parametricDiagram : parametricDiagrams)
      {
         System.out.println(String.format("   parametricDiagram: %s (%s)", parametricDiagram.getName(), parametricDiagram.getId()));
         
         List<JsonConstraintProperty> constraintProperties = parametricDiagram.getConstraintProperties();
         System.out.println(String.format("      constraint properties: count=%d", constraintProperties.size()));
         for (JsonConstraintProperty constraintProperty : constraintProperties)
         {
            JsonConstraintBlock cpType = constraintProperty.getType();
            System.out.println(String.format("         %s (%s)  type: %s", constraintProperty.getName(), constraintProperty.getId(), cpType.getName()));
         }
         
         List<JsonBindingConnector> bindingConnectors = parametricDiagram.getBindingConnectors();
         System.out.println(String.format("      binding connectors: count=%d", bindingConnectors.size()));
         for (JsonBindingConnector bindingConnector : bindingConnectors)
         {
            System.out.println(String.format("         %s (%s)", bindingConnector.getName(), bindingConnector.getId()));
            
            System.out.println("            Source sequence:");
            List<JsonBaseElement> sourceSequence = bindingConnector.getSourcePath();
            for (JsonBaseElement elem : sourceSequence)
            {
               System.out.println(String.format("               %s (%s)", 
                     elem.getName(), elem.getId()));
            }

            System.out.println("            Target sequence:");
            List<JsonBaseElement> targetSequence = bindingConnector.getTargetPath();
            for (JsonBaseElement elem : targetSequence)
            {
               System.out.println(String.format("               %s (%s)", 
                     elem.getName(), elem.getId()));
            }
         }         
      }
   }
}
