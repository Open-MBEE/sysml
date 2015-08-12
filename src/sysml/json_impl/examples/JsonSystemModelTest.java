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
import sysml.json_impl.JsonElement;
import sysml.json_impl.JsonBlock;
import sysml.json_impl.JsonConstraintBlock;
import sysml.json_impl.JsonPart;
import sysml.json_impl.JsonConstraintParameter;
import sysml.json_impl.JsonConstraintProperty;
import sysml.json_impl.JsonParametricDiagram;
import sysml.json_impl.JsonBindingConnector;
import sysml.json_impl.JsonBaseElement;
import sysml.json_impl.JsonProject;
import sysml.json_impl.JsonStereotype;
import sysml.json_impl.JsonProperty;
import sysml.json_impl.JsonPropertyValues;
import sysml.json_impl.JsonValueProperty;
import sysml.json_impl.JsonValueType;
import sysml.json_impl.JsonGraphElement;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.json.JSONObject;

public class JsonSystemModelTest
{
   /**
    * @param args
    */
   public static void main(String[] args) throws Exception
   {
      if (args.length < 1)
      {
         System.err.println("Usage: java JsonSystemModelTest pathToJsonFile");
         System.exit(1);
      }

      System.out.println(String.format("JSON file: %s", args[0]));
      
      ArrayList<String> libraryFiles = new ArrayList<String>();
      // the file path can be absolute or relative to CWD
      libraryFiles.add("SysML.json");
      libraryFiles.add("MD_Customization_for_SysML.json");
      libraryFiles.add("SI_Definitions.json");
      libraryFiles.add("SI_Specializations.json");
      libraryFiles.add("SI_Valuetype_Library.json");
      libraryFiles.add("QUDV.json");
      libraryFiles.add("MD_Customization_for_View_Viewpoint.json");      
      // libraryFiles.add("SysML_Extensions.json");
      libraryFiles.add("MBSE_Analyzer.json");
      
      JsonSystemModel systemModel = new JsonSystemModel(libraryFiles);
      
      systemModel.readJson(args[0]);
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
      System.out.println(String.format("Block: %s", bike));
      System.out.println(String.format("   Qualified name: %s", bike.getQualifiedName()));
      System.out.println(String.format("   parts count: %d ", parts.size()));
      for (JsonPart part : parts)
      {
         JsonBlock partType = part.getType();
         System.out.println(String.format("   %s  type: %s", 
               part, partType.getName()));
      }
      
      System.out.println(String.format("   getPart: %s", bike.getPart("frontWheel")));
      System.out.println(String.format("   getValueProperty: %s", bike.getValueProperty("weight")));
      
      List<JsonValueProperty> valueProps = bike.getValueProperties();
      System.out.println(String.format("   value properties count: %d ", valueProps.size()));
      for (JsonValueProperty valueProp : valueProps)
      {
         JsonPropertyValues values = valueProp.getValue();
         String valuePropTypeId = valueProp.getTypeId();
         JsonValueType valueType = valueProp.getType();
         System.out.println(String.format("   %s (%s) value: %s value.class: %s typeId: %s type: %s", 
               valueProp, valueProp.getQualifiedName(), values.getValueAsString(0), 
               values.getClass().getName(), valuePropTypeId, valueType));
         if (valueType != null)
         {
            System.out.println(String.format("      unit: %s quantityKind: %s", 
                  valueType.getUnit(), valueType.getQuantityKind()));             
         }
      }      
      
      // Parametric diagram
      List<JsonParametricDiagram> parametricDiagrams = bike.getParametricDiagrams();
      for (JsonParametricDiagram parametricDiagram : parametricDiagrams)
      {
         System.out.println(String.format("   parametricDiagram: %s  %s", 
               parametricDiagram, parametricDiagram.getQualifiedName()));
         
         List<JsonConstraintProperty> constraintProperties = parametricDiagram.getConstraintProperties();
         System.out.println(String.format("      constraint properties: count=%d", constraintProperties.size()));
         for (JsonConstraintProperty constraintProperty : constraintProperties)
         {
            JsonConstraintBlock cpType = constraintProperty.getType();
            System.out.println(String.format("        %s  type: %s", 
                  constraintProperty, cpType.getName()));
         }
         
         List<JsonBindingConnector> bindingConnectors = parametricDiagram.getBindingConnectors();
         System.out.println(String.format("      binding connectors: count=%d", bindingConnectors.size()));
         for (JsonBindingConnector bindingConnector : bindingConnectors)
         {
            System.out.println(String.format("         %s", 
                  bindingConnector));
            
            System.out.println("            Source sequence:");
            List<JsonBaseElement> sourceSequence = bindingConnector.getSourcePath();
            for (JsonBaseElement elem : sourceSequence)
            {
               System.out.println(String.format("               %s", elem));
            }

            System.out.println("            Target sequence:");
            List<JsonBaseElement> targetSequence = bindingConnector.getTargetPath();
            for (JsonBaseElement elem : targetSequence)
            {
               System.out.println(String.format("               %s", elem));
            }
         }  
         
         
         Collection<JsonBaseElement> elems = parametricDiagram.getElementsInDiagram();
         Iterator<JsonBaseElement> iterElems = elems.iterator();
         System.out.println(String.format("      Num elements on diagram: %d", elems.size()));
         while (iterElems.hasNext())
         {
            JsonBaseElement elem = iterElems.next();
            System.out.println(String.format("         %s", elem));
         }

         Collection<JsonGraphElement> gElems = parametricDiagram.getGraphicalElements();
         Iterator<JsonGraphElement> iterGElems = gElems.iterator();
         System.out.println(String.format("      Num graph elements on diagram: %d", gElems.size()));
         while (iterGElems.hasNext())
         {
            JsonGraphElement gElem = iterGElems.next();
            System.out.println(String.format("         %s", gElem.toString()));
         }         
         
         JsonGraphElement[] gArray = new JsonGraphElement[0];
         gArray = gElems.toArray(gArray);
         System.out.println(String.format("equals=%s", gArray[2].equals(gArray[7])));
      }
      
      // Constraint block "SumTwo"
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "SumTwo");
      if (elements.size() == 0)
      {
         throw new Exception("No element was found");   
      }
      else if (elements.size() > 1)
      {
         throw new Exception("More than one element was found");   
      }         
      iter = elements.iterator();       
      JSONObject jSumTwo = iter.next();      
      JsonConstraintBlock sumTwo = (JsonConstraintBlock)systemModel.wrap(jSumTwo);
      System.out.println(String.format("Constraint block: %s", sumTwo));
      
      boolean isStereotyped = sumTwo.isStereotypedAs(JsonSystemModel.EXTERNAL_ANALYSIS);
      System.out.println(String.format("   Stereotyped by \"%s\": %s", JsonSystemModel.EXTERNAL_ANALYSIS, isStereotyped));
      
      System.out.println(String.format("   Tag (%s): %s", JsonSystemModel.URL, 
            sumTwo.getTagValue(JsonSystemModel.URL)));      
      System.out.println(String.format("   Tag (%s): %s", JsonSystemModel.TYPE, 
            sumTwo.getTagValue(JsonSystemModel.TYPE)));      
      
      List<JsonConstraintParameter> constraintParameters = sumTwo.getConstraintParameters();
      for (JsonConstraintParameter constraintParameter : constraintParameters)
      {
         System.out.println(String.format("   %s (%s)", constraintParameter.getName(), constraintParameter.getId()));
         System.out.println(String.format("       Tag (%s): %s", JsonSystemModel.DEFAULT_VALUE, 
               constraintParameter.getTagValue(JsonSystemModel.DEFAULT_VALUE)));          
         System.out.println(String.format("       Tag (%s): %s", JsonSystemModel.LOWER_BOUND, 
               constraintParameter.getTagValue(JsonSystemModel.LOWER_BOUND))); 
         System.out.println(String.format("       Tag (%s): %s", JsonSystemModel.UPPER_BOUND, 
               constraintParameter.getTagValue(JsonSystemModel.UPPER_BOUND)));     
         System.out.println(String.format("       Tag (%s): %s", JsonSystemModel.DIRECTION, 
               constraintParameter.getTagValue(JsonSystemModel.DIRECTION)));         
      }
      
      // Projects
      JsonProject p1 = bike.getProject();
      JsonProject p2 = sumTwo.getProject();
      
      System.out.println(String.format("Project of Bike: %s", p1));
      System.out.println(String.format("Project of SumTwo: %s", p2));
      System.out.println(String.format("p1==p2: %s", p1.equals(p2)));
                  
      JSONObject jMbseAnalyzerProj = systemModel.getProject("MBSEAnalyzer");
      JsonProject mbseAnalyzerProj = (JsonProject)systemModel.wrap(jMbseAnalyzerProj);
      System.out.println(String.format("MBSEAnalyzer profile: %s", mbseAnalyzerProj));
      JsonStereotype externalAnalysis = mbseAnalyzerProj.getStereotype(JsonSystemModel.EXTERNAL_ANALYSIS);
      System.out.println(String.format("   %s (%s)", externalAnalysis.getName(), externalAnalysis.getId()));
      JsonProperty urlTag = externalAnalysis.getTag(JsonSystemModel.URL);
      System.out.println(String.format("      %s", urlTag));
      
      // value type
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "ton");
      for (JSONObject jObj : elements)
      {
         if(systemModel.isValueType(jObj))
         {
            JsonValueType ton = (JsonValueType)systemModel.wrap(jObj);
            System.out.println(String.format("      %s unit: %s quantityKind: %s", 
                  ton, ton.getUnit(), ton.getQuantityKind()));              
         }
      }
      
      // generalization
      JsonBlock childBlock = null;
      JsonBlock parentBlock = null; 
      JsonBlock grandParentBlock = null;      
      
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "ChildBlock");
      for (JSONObject jObj : elements)
      {
         if(systemModel.isBlock(jObj))
         {
            childBlock = (JsonBlock)systemModel.wrap(jObj);            
         }
      }      
      
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "ParentBlock");
      for (JSONObject jObj : elements)
      {
         if(systemModel.isBlock(jObj))
         {
            parentBlock = (JsonBlock)systemModel.wrap(jObj);            
         }
      } 
      
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "GrantParentBlock");
      for (JSONObject jObj : elements)
      {
         if(systemModel.isBlock(jObj))
         {
            grandParentBlock = (JsonBlock)systemModel.wrap(jObj);            
         }
      }       
      
      System.out.println(String.format("Super classes of : ", grandParentBlock));
      Collection<JsonElement> superClasses = childBlock.getSuperClasses();
      for (JsonElement superClass : superClasses)
      {
         System.out.println(String.format("   %s", superClass));
      }
      
      System.out.println(String.format("%s is a super class of %s: %s", 
            grandParentBlock.getName(), parentBlock.getName(), grandParentBlock.isSuperClassOf(parentBlock)));
      System.out.println(String.format("%s is a super class of %s: %s", 
            parentBlock.getName(), childBlock.getName(), parentBlock.isSuperClassOf(childBlock)));
      System.out.println(String.format("%s is a super class of %s: %s", 
            grandParentBlock.getName(), childBlock.getName(), grandParentBlock.isSuperClassOf(childBlock)));  
      
      System.out.println(String.format("%s is a sub class of %s: %s", 
            grandParentBlock.getName(), parentBlock.getName(), grandParentBlock.isSubClassOf(parentBlock)));
      System.out.println(String.format("%s is a sub class of %s: %s", 
            parentBlock.getName(), childBlock.getName(), parentBlock.isSubClassOf(childBlock)));
      System.out.println(String.format("%s is a sub class of %s: %s", 
            grandParentBlock.getName(), childBlock.getName(), grandParentBlock.isSubClassOf(childBlock))); 
      
      System.out.println(String.format("%s is a super class of %s: %s", 
            parentBlock.getName(), grandParentBlock.getName(), parentBlock.isSuperClassOf(grandParentBlock)));
      System.out.println(String.format("%s is a super class of %s: %s", 
            childBlock.getName(), parentBlock.getName(), childBlock.isSuperClassOf(parentBlock)));
      System.out.println(String.format("%s is a super class of %s: %s", 
            childBlock.getName(), grandParentBlock.getName(), childBlock.isSuperClassOf(grandParentBlock)));  
      
      System.out.println(String.format("%s is a sub class of %s: %s", 
            parentBlock.getName(), grandParentBlock.getName(), parentBlock.isSubClassOf(grandParentBlock)));
      System.out.println(String.format("%s is a sub class of %s: %s", 
            childBlock.getName(), parentBlock.getName(), childBlock.isSubClassOf(parentBlock)));
      System.out.println(String.format("%s is a sub class of %s: %s", 
            childBlock.getName(), grandParentBlock.getName(), childBlock.isSubClassOf(grandParentBlock)));
      
      System.out.println(String.format("%s is a super class of %s: %s", 
            bike.getName(), parentBlock.getName(), bike.isSuperClassOf(parentBlock)));  
      System.out.println(String.format("%s is a sub class of %s: %s", 
            bike.getName(), parentBlock.getName(), bike.isSubClassOf(parentBlock)));       
   }
}
