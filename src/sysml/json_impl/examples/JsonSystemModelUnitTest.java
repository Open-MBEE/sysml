package sysml.json_impl.examples;
import org.json.JSONObject;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.Test;

import sysml.json_impl.JsonBaseElement;
import sysml.json_impl.JsonBindingConnector;
import sysml.json_impl.JsonBlock;
import sysml.json_impl.JsonConstraintBlock;
import sysml.json_impl.JsonConstraintParameter;
import sysml.json_impl.JsonConstraintProperty;
import sysml.json_impl.JsonElement;
import sysml.json_impl.JsonGraphElement;
import sysml.json_impl.JsonParametricDiagram;
import sysml.json_impl.JsonPart;
import sysml.json_impl.JsonProject;
import sysml.json_impl.JsonProperty;
import sysml.json_impl.JsonPropertyValues;
import sysml.json_impl.JsonStereotype;
import sysml.json_impl.JsonSystemModel;
import sysml.json_impl.JsonSystemModelException;
import sysml.json_impl.JsonValueProperty;
import sysml.json_impl.JsonValueType;
import sysml.json_impl.JsonInstanceSpecification;
import sysml.json_impl.JsonSlot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.logging.Level;

public class JsonSystemModelUnitTest
{
   JsonSystemModel systemModel = null;  
   @Before
   public void setUp() {
      ArrayList<String> libraryFiles = new ArrayList<String>();
      // the file path can be absolute or relative to CWD
      libraryFiles.add("SysML.json");
      libraryFiles.add("MD_Customization_for_SysML.json");
      libraryFiles.add("SI_Definitions.json");
      libraryFiles.add("SI_Specializations.json");
      libraryFiles.add("SI_Valuetype_Library.json");
      libraryFiles.add("QUDV.json");
      libraryFiles.add("MD_Customization_for_View_Viewpoint.json");      
      libraryFiles.add("AnalysisViews.json");  
      // libraryFiles.add("SysML_Extensions.json");
      libraryFiles.add("MBSE_Analyzer.json");
      
      try
      {
         systemModel = new JsonSystemModel(libraryFiles);
         systemModel.readJson("Bike_Project.json");
      }
      catch(JsonSystemModelException ex)
      {
         ex.printStackTrace();
      }
      JsonSystemModel.setLogLevel(Level.FINE);
   }

   @After
   public void tearDown() {

   }   

   @Test
   public void testBlocks()
   {
      Collection<JSONObject> elements;

      // Search for all elements with name "Bike"
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "Bike");
      assertTrue(elements.size() == 1);
      Iterator<JSONObject> iter = elements.iterator();       
      JSONObject jBike0 = iter.next();      

      elements = (List<JSONObject>) systemModel.getElementWithQualifiedName(null, "/my-new-bike/MyBike/Structure/Bike");
      assertTrue(elements.size() == 1);
      iter = elements.iterator();       
      JSONObject jBike = iter.next();  
      
      assertTrue(jBike0 == jBike);
      
      //
      JsonBlock bike = (JsonBlock)systemModel.wrap(jBike);
      assertEquals("Bike", bike.getName());
      assertEquals("/my-new-bike/MyBike/Structure/Bike", bike.getQualifiedName());
      
      List<JsonPart> parts = bike.getParts();
      assertEquals(3, parts.size());
      
      HashMap<String, JsonPart> partsMap = new HashMap<String, JsonPart>();
      for (JsonPart part : parts)
      {
         partsMap.put(part.getName(), part);
      }
      assertTrue(partsMap.containsKey("frontWheel"));
      assertTrue(partsMap.containsKey("rearWheel"));
      assertTrue(partsMap.containsKey("frame"));      
      
      List<JsonValueProperty> valueProps = bike.getValueProperties();
      assertEquals(1, valueProps.size());
      
      HashMap<String, JsonValueProperty> valuePropsMap = new HashMap<String, JsonValueProperty>();
      for (JsonValueProperty valueProp : valueProps)
      {
         valuePropsMap.put(valueProp.getName(), valueProp);
      }      
      assertTrue(valuePropsMap.containsKey("weight"));          
      JsonValueProperty bikeWeight = bike.getValueProperty("weight");
      JsonPropertyValues propertyValues = bikeWeight.getValue();
      assertEquals(1, propertyValues.getLength());
      assertEquals("7.5", propertyValues.getValueAsString(0));
      assertEquals("_16_6_2104050f_1253865957968_136935_6575", bikeWeight.getTypeId());
      assertEquals("kilogram", bikeWeight.getType().getUnit());
      assertEquals("mass", bikeWeight.getType().getQuantityKind());
      
      JsonPart frontWheel = bike.getPart("frontWheel"); 
      assertEquals("frontWheel", frontWheel.getName());
   }
   
   @Test
   public void testConstraintBlock()
   {
      // Constraint block "SumTwo"
      List<JSONObject> elements = (List<JSONObject>) systemModel.getElementWithName(null, "SumTwo");
      assertEquals(1, elements.size());

      Iterator<JSONObject> iter = elements.iterator();       
      JSONObject jSumTwo = iter.next();      
      JsonConstraintBlock sumTwo = (JsonConstraintBlock)systemModel.wrap(jSumTwo);
      assertEquals("SumTwo", sumTwo.getName());
 
      assertTrue(sumTwo.isStereotypedAs(JsonSystemModel.EXTERNAL_ANALYSIS));

      assertEquals("aserv://localhost/math/SumTwo", sumTwo.getTagValue(JsonSystemModel.URL));
      assertEquals("AnalysisServer", sumTwo.getTagValue(JsonSystemModel.TYPE));
      
      JsonConstraintParameter x1 = sumTwo.getConstraintParameter("x1");
      assertEquals("0.0", x1.getTagValue(JsonSystemModel.DEFAULT_VALUE));
      assertEquals("input", x1.getTagValue(JsonSystemModel.DIRECTION));      
      assertTrue(x1.getTagValue(JsonSystemModel.LOWER_BOUND) == null);
      assertTrue(x1.getTagValue(JsonSystemModel.UPPER_BOUND) == null);

      JsonConstraintParameter sum = sumTwo.getConstraintParameter("sum");
      assertEquals("0.0", sum.getTagValue(JsonSystemModel.DEFAULT_VALUE));
      assertEquals("output", sum.getTagValue(JsonSystemModel.DIRECTION));      
      assertTrue(x1.getTagValue(JsonSystemModel.LOWER_BOUND) == null);
      assertTrue(x1.getTagValue(JsonSystemModel.UPPER_BOUND) == null);
   }
   
   @Test
   public void testProject()
   {
      // Search for all elements with name "Bike"
      List<JSONObject> elements = (List<JSONObject>) systemModel.getElementWithName(null, "Bike");
      assertTrue(elements.size() == 1);

      Iterator<JSONObject> iter = elements.iterator();       
      JSONObject jBike = iter.next();
      JsonBlock bike = (JsonBlock)systemModel.wrap(jBike);
      
      // constraint block
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "SumTwo");
      assertTrue(elements.size() == 1);
      
      iter = elements.iterator();       
      JSONObject jSumTwo = iter.next();      
      JsonConstraintBlock sumTwo = (JsonConstraintBlock)systemModel.wrap(jSumTwo);
      
      JsonProject p1 = bike.getProject();
      JsonProject p2 = sumTwo.getProject();
      
      assertEquals(p1, p2);
                  
      JSONObject jMbseAnalyzerProj = systemModel.getProject("MBSEAnalyzer");
      JsonProject mbseAnalyzerProj = (JsonProject)systemModel.wrap(jMbseAnalyzerProj);
      assertEquals("MBSEAnalyzer", mbseAnalyzerProj.getName());
      
      JsonStereotype externalAnalysis = mbseAnalyzerProj.getStereotype(JsonSystemModel.EXTERNAL_ANALYSIS);
      assertEquals("ExternalAnalysis", externalAnalysis.getName());      

      JsonProperty urlTag = externalAnalysis.getTag(JsonSystemModel.URL);
      assertEquals("url", urlTag.getName());     
   }   
   
   
   @Test
   @Ignore
   public void testValueType()
   {   
      List<JSONObject> elements = (List<JSONObject>) systemModel.getElementWithName(null, "ton");
      assertEquals(1, elements.size());
      
      for (JSONObject jObj : elements)
      {
         if(systemModel.isValueType(jObj))
         {
            JsonValueType ton = (JsonValueType)systemModel.wrap(jObj);
            assertEquals("metric ton", ton.getUnit());
            assertEquals("mass", ton.getQuantityKind());        
         }
      }
   }
   
   @Test
   public void testGeneralization()
   {   
      JsonBlock childBlock = null;
      JsonBlock parentBlock = null; 
      JsonBlock grandParentBlock = null;     
      
      List<JSONObject> elements = null;
      
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
      
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "GrandParentBlock");
      for (JSONObject jObj : elements)
      {
         if(systemModel.isBlock(jObj))
         {
            grandParentBlock = (JsonBlock)systemModel.wrap(jObj);            
         }
      }       
      
      System.out.println(String.format("Super classes of : ", grandParentBlock));
      Collection<JsonElement> superClasses = childBlock.getSuperClasses();
      assertEquals(2, superClasses.size());
      HashMap<String, JsonElement> superClassMap = new HashMap<String, JsonElement>();
       
      for (JsonElement superClass : superClasses)
      {
         superClassMap.put(superClass.getName(), superClass);
      }
      assertTrue(superClassMap.containsKey("ParentBlock"));
      assertTrue(superClassMap.containsKey("GrandParentBlock"));
     
      assertTrue(grandParentBlock.isSuperClassOf(parentBlock));
      assertTrue(parentBlock.isSuperClassOf(childBlock));
      assertTrue(grandParentBlock.isSuperClassOf(childBlock)); 
      
      assertFalse(grandParentBlock.isSubClassOf(parentBlock));  
      assertFalse(parentBlock.isSubClassOf(childBlock));
      assertFalse(grandParentBlock.isSubClassOf(childBlock));
      
      assertFalse(parentBlock.isSuperClassOf(grandParentBlock));
      assertFalse(childBlock.isSuperClassOf(parentBlock));
      assertFalse(childBlock.isSuperClassOf(grandParentBlock));
      
      assertTrue(parentBlock.isSubClassOf(grandParentBlock));
      assertTrue(childBlock.isSubClassOf(parentBlock));
      assertTrue(childBlock.isSubClassOf(grandParentBlock));
      
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "Bike");
      assertTrue(elements.size() == 1);

      Iterator<JSONObject> iter = elements.iterator();       
      JSONObject jBike = iter.next();
      JsonBlock bike = (JsonBlock)systemModel.wrap(jBike);      
      
      assertFalse(bike.isSuperClassOf(parentBlock));
      assertFalse(bike.isSubClassOf(parentBlock));
   }   
   
   @Test
   public void testMultiplicity()
   {
      Collection<JSONObject> elements;   
      // multiplicity
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "BlockM");
      assertEquals(1, elements.size());

      Iterator<JSONObject> iter = elements.iterator();       
      JSONObject jBlockM = iter.next();
      
      JsonBlock blockM = (JsonBlock)systemModel.wrap(jBlockM);
      JsonPart part = null;
      
      part = blockM.getPart("blockM2");
      assertEquals(2L, part.getMultiplicityMin());
      assertEquals(2L, part.getMultiplicityMax());
      
      JsonBlock blockM2 = part.getType();      
      JsonValueProperty m1 = blockM2.getValueProperty("m1");
      assertEquals(1L, m1.getMultiplicityMin());
      assertEquals(-1L, m1.getMultiplicityMax());
      
      part = blockM.getPart("blockMS");
      assertEquals(0L, part.getMultiplicityMin());
      assertEquals(-1L, part.getMultiplicityMax());      
      
      part = blockM.getPart("blockM1S");
      assertEquals(1L, part.getMultiplicityMin());
      assertEquals(-1L, part.getMultiplicityMax());
      
      part = blockM.getPart("blockM0S");
      assertEquals(0L, part.getMultiplicityMin());
      assertEquals(-1L, part.getMultiplicityMax());
      
      part = blockM.getPart("blockM01");
      assertEquals(0L, part.getMultiplicityMin());
      assertEquals(1L, part.getMultiplicityMax());      
   }      
   
   @Test
   public void testPropRedefinition()
   {
      Collection<JSONObject> elements;   
      // 
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "GrandParentBlock");
      assertEquals(1, elements.size());
      JsonBlock grandParentBlock = (JsonBlock)systemModel.wrap(elements.iterator().next());
      
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "ParentBlock");
      assertEquals(1, elements.size());
      JsonBlock parentBlock = (JsonBlock)systemModel.wrap(elements.iterator().next());
      
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "ChildBlock");
      assertEquals(1, elements.size());      
      JsonBlock childBlock = (JsonBlock)systemModel.wrap(elements.iterator().next());      
      
      // Search for all elements with name "Bike"
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "Bike");
      assertEquals(1, elements.size());      
      JsonBlock bike = (JsonBlock)systemModel.wrap(elements.iterator().next());         

      List<JsonProperty> redefinedByThis = null;
      List<JsonProperty> redefiningThis = null;
      
      // redefinition of part
      JsonPart part1 = parentBlock.getPart("part1");
      redefinedByThis = part1.getRedefinedByThis();
      assertEquals(0, redefinedByThis.size());
      redefiningThis = part1.getRedefiningThis();
      assertEquals(1, redefiningThis.size());
      assertEquals("/my-new-bike/MyBike/Modeling/Inheritance/ChildBlock/part2", redefiningThis.iterator().next().getQualifiedName());
      
      JsonPart part2 = childBlock.getPart("part2");
      redefinedByThis = part2.getRedefinedByThis();
      assertEquals(1, redefinedByThis.size());     
      assertEquals("/my-new-bike/MyBike/Modeling/Inheritance/ParentBlock/part1", redefinedByThis.iterator().next().getQualifiedName());
      redefiningThis = part2.getRedefiningThis();
      assertEquals(0, redefiningThis.size());      
      
      // redefinition of value property
      JsonValueProperty p2 = parentBlock.getValueProperty("p2");
      redefinedByThis = p2.getRedefinedByThis();
      assertEquals(0, redefinedByThis.size());
      redefiningThis = p2.getRedefiningThis();
      assertEquals(1, redefiningThis.size());
      assertEquals("/my-new-bike/MyBike/Modeling/Inheritance/ChildBlock/p2redef", redefiningThis.iterator().next().getQualifiedName());    
      
      JsonValueProperty p2redef = childBlock.getValueProperty("p2redef");
      redefinedByThis = p2redef.getRedefinedByThis();
      assertEquals(1, redefinedByThis.size());     
      assertEquals("/my-new-bike/MyBike/Modeling/Inheritance/ParentBlock/p2", redefinedByThis.iterator().next().getQualifiedName());
      redefiningThis = p2redef.getRedefiningThis();
      assertEquals(0, redefiningThis.size());       
      
      // no property redefinition
      JsonValueProperty p1 = grandParentBlock.getValueProperty("p1");
      redefinedByThis = p1.getRedefinedByThis();
      assertEquals(0, redefinedByThis.size());   
      redefiningThis = p1.getRedefiningThis();
      assertEquals(0, redefiningThis.size());      
      
      JsonPart frame = bike.getPart("frame");
      redefinedByThis = frame.getRedefinedByThis();
      assertEquals(0, redefinedByThis.size());   
      redefiningThis = frame.getRedefiningThis();
      assertEquals(0, redefiningThis.size()); 
   }
   
   @Test
   public void testInstanceSpecification()
   {
      Collection<JSONObject> elements;   
      // 
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "bike");
      assertEquals(1, elements.size());
      JsonInstanceSpecification bikeInstance = 
            (JsonInstanceSpecification)systemModel.wrap(elements.iterator().next());
      
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "Bike");
      assertEquals(1, elements.size());
      JsonBlock bike = (JsonBlock)systemModel.wrap(elements.iterator().next());      
      
      assertEquals(bike.getId(), bikeInstance.getClassifier().getId());
      // JsonPart frame = bike.getPart("frame");
      // JsonPart frontWheel = bike.getPart("frontWheel");
      // JsonPart rearWheel = bike.getPart("rearWheel");
      // JsonValueProperty weight = bike.getValueProperty("weight");
      
      HashMap<String, JsonProperty> propsMap = new HashMap<String, JsonProperty>();
      for (JsonProperty prop : bike.getProperties())
      {
         propsMap.put(prop.getId(), prop);
      }
      
      List<JsonSlot> slots = null;
      slots = bikeInstance.getSlots();
      assertEquals(4, slots.size());
      HashMap<String, JsonSlot> slotsMap = new HashMap<String, JsonSlot>();
      
      for (JsonSlot slot : slots)
      {
         String typeId = slot.getTypeId();
         JsonProperty prop = propsMap.get(typeId);
         assertTrue(prop != null);
         assertEquals(typeId, prop.getId());
         
         slotsMap.put(prop.getName(), slot);
         
         JsonPropertyValues values = slot.getValue();
          
         if (prop instanceof JsonPart)
         {
            JsonPart partProp = (JsonPart)prop;
            JSONObject jValue = (JSONObject)values.getValue(0);
            JsonInstanceSpecification value = (JsonInstanceSpecification)systemModel.wrap(jValue);
            assertEquals( partProp.getType().getId(), value.getClassifier().getId());
            System.out.println(String.format("%s: %s", prop.getName(), value.getName()));
         }
         else if (prop instanceof JsonValueProperty)
         {
            String value = values.getValueAsString(0);
            System.out.println(String.format("%s: %s", prop.getName(), value));
         }
      }
      
      // get instance specification
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "bike.frontWheel");
      assertEquals(1, elements.size());
      JsonInstanceSpecification frontWheelInstance = 
            (JsonInstanceSpecification)systemModel.wrap(elements.iterator().next()); 
      
      JsonSlot containingSlot = frontWheelInstance.findContainingSlot();
      assertTrue(containingSlot != null);
      assertEquals(slotsMap.get("frontWheel").getId(), containingSlot.getId());
      
      JsonInstanceSpecification ownerInstance = frontWheelInstance.findOwningInstanceSpecification();
      assertEquals(bikeInstance.getId(), ownerInstance.getId());
      
   }
   
   @Test
   public void testParametricDiagram()
   {
      Collection<JSONObject> elements;

      // Search for all elements with name "Bike"
      elements = (List<JSONObject>) systemModel.getElementWithName(null, "Bike");
      assertTrue(elements.size() == 1);

      Iterator<JSONObject> iter = elements.iterator();       
      JSONObject jBike = iter.next();
      
      //
      JsonBlock bike = (JsonBlock)systemModel.wrap(jBike);
      
      List<JsonParametricDiagram> parametricDiagrams = bike.getParametricDiagrams();
      assertEquals(1, parametricDiagrams.size());
      JsonParametricDiagram parametricDiagram = parametricDiagrams.iterator().next();
      assertEquals("/my-new-bike/MyBike/Structure/Bike/par_bikeWeight", parametricDiagram.getQualifiedName());
      
      List<JsonConstraintProperty> constraintProperties = parametricDiagram.getConstraintProperties();
      assertEquals(1, constraintProperties.size());
      JsonConstraintProperty constraintProperty = constraintProperties.iterator().next();
      assertEquals("sumThree", constraintProperty.getName());
      JsonConstraintBlock cpType = constraintProperty.getType();
      assertEquals("SumThree", cpType.getName());
      
      List<JsonBindingConnector> bindingConnectors = parametricDiagram.getBindingConnectors();
      assertEquals(4, bindingConnectors.size());
      HashMap<String, JsonBindingConnector> bindingConnectorsMap = new HashMap<String, JsonBindingConnector>();
      for (JsonBindingConnector bindingConnector : bindingConnectors)
      {
         bindingConnectorsMap.put(bindingConnector.getName(), bindingConnector);
      }      
      assertTrue(bindingConnectorsMap.containsKey("bc_frame"));         
      assertTrue(bindingConnectorsMap.containsKey("bc_fw"));      
      assertTrue(bindingConnectorsMap.containsKey("bc_rw"));
      assertTrue(bindingConnectorsMap.containsKey("bc_b"));      
      
      JsonBindingConnector bc = null;      
      List<JsonBaseElement> sourcePath = null;
      List<JsonBaseElement> targetPath = null;  
      Iterator<JsonBaseElement> iterPath = null;
      JsonBaseElement e1 = null;
      JsonBaseElement e2 = null;
      
      // bc_frame
      bc = bindingConnectorsMap.get("bc_frame");
      assertEquals("weight", bc.getSource().getName());
      assertEquals("x1", bc.getTarget().getName());
      
      sourcePath = bc.getSourcePath();
      assertEquals(2, sourcePath.size());
      iterPath = sourcePath.iterator();
      e1 = iterPath.next();
      e2 = iterPath.next();
      assertEquals("frame", e1.getName());
      assertEquals("weight", e2.getName());
      
      targetPath = bc.getTargetPath();
      assertEquals(2, targetPath.size());
      iterPath = targetPath.iterator();
      e1 = iterPath.next();
      e2 = iterPath.next();
      assertEquals("sumThree", e1.getName());
      assertEquals("x1", e2.getName());      
      
      // bc_fw
      bc = bindingConnectorsMap.get("bc_fw");
      assertEquals("weight", bc.getSource().getName());
      assertEquals("x2", bc.getTarget().getName());
      
      sourcePath = bc.getSourcePath();
      assertEquals(2, sourcePath.size());
      iterPath = sourcePath.iterator();
      e1 = iterPath.next();
      e2 = iterPath.next();
      assertEquals("frontWheel", e1.getName());
      assertEquals("weight", e2.getName());
      
      targetPath = bc.getTargetPath();
      assertEquals(2, targetPath.size());
      iterPath = targetPath.iterator();
      e1 = iterPath.next();
      e2 = iterPath.next();
      assertEquals("sumThree", e1.getName());
      assertEquals("x2", e2.getName());          
      
      // bc_rw
      bc = bindingConnectorsMap.get("bc_rw");
      assertEquals("weight", bc.getSource().getName());
      assertEquals("x3", bc.getTarget().getName());   
      
      sourcePath = bc.getSourcePath();
      assertEquals(2, sourcePath.size());
      iterPath = sourcePath.iterator();
      e1 = iterPath.next();
      e2 = iterPath.next();
      assertEquals("rearWheel", e1.getName());
      assertEquals("weight", e2.getName());
      
      targetPath = bc.getTargetPath();
      assertEquals(2, targetPath.size());
      iterPath = targetPath.iterator();
      e1 = iterPath.next();
      e2 = iterPath.next();
      assertEquals("sumThree", e1.getName());
      assertEquals("x3", e2.getName());           

      // bc_b
      bc = bindingConnectorsMap.get("bc_b");
      assertEquals("weight", bc.getSource().getName());
      assertEquals("sum", bc.getTarget().getName());
      
      sourcePath = bc.getSourcePath();
      assertEquals(1, sourcePath.size());
      iterPath = sourcePath.iterator();
      e1 = iterPath.next();
      assertEquals("weight", e1.getName());
      
      targetPath = bc.getTargetPath();
      assertEquals(2, targetPath.size());
      iterPath = targetPath.iterator();
      e1 = iterPath.next();
      e2 = iterPath.next();
      assertEquals("sumThree", e1.getName());
      assertEquals("sum", e2.getName());       
      
      // elements in diagram
      Collection<JsonBaseElement> elems = parametricDiagram.getElementsInDiagram();
      assertEquals(15, elems.size());
      HashMap<String, JsonBaseElement> elemsMap = new HashMap<String, JsonBaseElement>();
      for (JsonBaseElement elem : elems)
      {
         elemsMap.put(elem.getId(), elem);
      }      
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796489694_896526_12147"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796150117_112893_11646"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796335801_442531_11786"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796427514_471471_11880"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796228649_131443_11680"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796483419_247163_12121"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796007292_237363_11573"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796227978_489416_11678"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796479594_740267_12095"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417795995057_910774_11547"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796335008_995453_11783"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796201951_9700_11676"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796493281_281333_12173"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796320506_216079_11780"));
      assertTrue(elemsMap.containsKey("_17_0_5_1_f060354_1417796243696_919_11682"));
      
      Collection<JsonGraphElement> gElems = parametricDiagram.getGraphicalElements();
      assertEquals(18, gElems.size());
      
      HashMap<String, JsonGraphElement> gElemsMap = new HashMap<String, JsonGraphElement>();
      for (JsonGraphElement gElem : gElems)
      {
         gElemsMap.put(gElem.toString(), gElem);
      }
      assertTrue(gElemsMap.containsKey("par_bikeWeight"));
      assertTrue(gElemsMap.containsKey("Bike"));
      assertTrue(gElemsMap.containsKey("rearWheel=>Bike"));
      assertTrue(gElemsMap.containsKey("weight=>rearWheel=>Bike"));
      assertTrue(gElemsMap.containsKey("sumThree=>Bike"));
      assertTrue(gElemsMap.containsKey("x3=>sumThree=>Bike"));
      assertTrue(gElemsMap.containsKey("bc_rw=>Bike"));
      assertTrue(gElemsMap.containsKey("frontWheel=>Bike"));
      assertTrue(gElemsMap.containsKey("weight=>frontWheel=>Bike"));
      assertTrue(gElemsMap.containsKey("x2=>sumThree=>Bike"));
      assertTrue(gElemsMap.containsKey("bc_fw=>Bike"));
      assertTrue(gElemsMap.containsKey("frame=>Bike"));
      assertTrue(gElemsMap.containsKey("weight=>frame=>Bike"));
      assertTrue(gElemsMap.containsKey("x1=>sumThree=>Bike"));
      assertTrue(gElemsMap.containsKey("bc_frame=>Bike"));
      assertTrue(gElemsMap.containsKey("weight=>Bike"));
      assertTrue(gElemsMap.containsKey("sum=>sumThree=>Bike"));
      assertTrue(gElemsMap.containsKey("bc_b=>Bike"));
      
      JsonGraphElement gRearWheel = gElemsMap.get("rearWheel=>Bike");
      JsonGraphElement gFrontWheel = gElemsMap.get("frontWheel=>Bike");
      
      assertFalse(gRearWheel.equals(gFrontWheel));
   }   

}
