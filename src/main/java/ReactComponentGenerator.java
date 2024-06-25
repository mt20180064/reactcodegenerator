

import generated.Attribute;


import generated.ScenarioStep;
import generated.Specification;
import generated.UseCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;


public class ReactComponentGenerator {

    public static void main(String[] args) {
        try {
           
            JAXBContext jaxbContext = JAXBContext.newInstance(Specification.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Specification specification = (Specification) unmarshaller.unmarshal(new File("src/main/resources/specification.xml"));

            List<String> componentNames = new ArrayList<>();

            for (UseCase useCase : specification.getUseCase()) {
                String componentName = useCase.getName() + "Component";
                componentNames.add(componentName);
                String reactCode = generateUseCaseComponent(useCase);
                saveToFile("src/main/javascript/" + componentName + ".js", reactCode);
            }

            String appJsCode = generateAppJs(componentNames);
            saveToFile("src/main/javascript/App.js", appJsCode);

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateUseCaseComponent(UseCase useCase) {
        StringBuilder reactCode = new StringBuilder();
        reactCode.append("import React, { useState } from 'react';\n\n");
        reactCode.append("const ").append(useCase.getName()).append("Component = () => {\n");
        
      
       if (useCase.getTemplate().equals("tableSablon")){
           reactCode.append(
"  const tableStyle = {\n" +
"    borderCollapse: 'collapse', \n" +
"    width: '70%'\n" +
"  };\n");
           reactCode.append("const cellStyle = {\n" +
"    border: '1px solid black',\n" +
"    padding: '8px'\n" +
"  };\n");
        reactCode.append("  return (\n");
        reactCode.append("<>");
        reactCode.append("      <h2>").append(useCase.getName()).append("</h2>\n");
        reactCode.append("      <div>\n");
       
           if (useCase.getOrder()==4){
        reactCode.append("<select>\n" +
"          <option value=\"option1\">Kriterijum 1</option>\n" +
"          <option value=\"option2\">Kriterijum 2</option>\n" +
"          <option value=\"option3\">Kriterijum 3</option>\n" +
"        </select>\n" +
"        <input type=\"text\" placeholder=\"Unesite vrednost kriterijuma\" />\n" +
"        <button \n" +
"          type=\"submit\" \n" +
"          style={{ \n" +
"            border: '1px solid black', \n" +
"            borderRadius: '0px', \n" +
"            backgroundColor: 'lightgray' \n" +
"          }}\n" +
"        >\n" +
"          Pretraga\n" +
"        </button>\n " );
           }
           ArrayList<String> kolone = new ArrayList<String>();
           for (Attribute attribute : useCase.getEntity().getAttribute()) {
               kolone.add(attribute.getName());
           }
          
           DefaultTableModel dtm = new DefaultTableModel(kolone.toArray(),10);
           JTable tabelaTurnira = new JTable(dtm);
           String reactCodeTemplate = 
  
    
    "        <table style={tableStyle}>\n" +
    "            <thead>\n" +
    "                <tr>\n" +
    "                    %s\n" +
    "                </tr>\n" +
    "            </thead>\n" +
    "            <tbody>\n" +

    "                    <tr>\n" +
                     "%s\n"+
"                        </tr>\n" +
                   
           
    "            </tbody>\n" +
    "        </table>\n" +
    "\n" ;
    

String tableHeaderTemplate = "<th style={cellStyle}>%s</th>";
String tableDataTemplate = "<td style={cellStyle}>upisi vrednost</td>";
DefaultTableModel model = (DefaultTableModel) tabelaTurnira.getModel();
int columnCount = model.getColumnCount();

   
String tableHeader = IntStream.range(0, columnCount)
    .mapToObj(j -> String.format(tableHeaderTemplate, model.getColumnName(j)))
    .collect(Collectors.joining("\n"));
String tableData = IntStream.range(0, columnCount)
    .mapToObj(j -> String.format(tableDataTemplate, model.getColumnName(j)))
    .collect(Collectors.joining("\n"));

String reactTable = String.format(reactCodeTemplate, tableHeader, tableData);
reactCode.append(reactTable);

           for (ScenarioStep step : useCase.getMainScenario().getStep()) {
               if (step.getAction().value().equals("REQUEST_OPERATION")){
                   reactCode.append("      <button  type=\"submit\" style={{ \n" +
"      border: '1px solid black', \n" +
"      borderRadius: '0px', \n" +
"      backgroundColor: 'lightgray', \n" +
"      margin: '5px', \n" +
"      padding: '10px'\n"+
"    }}>").append(useCase.getName()).append("</button>\n");
                    
                }
           }
    
        
       } else if (useCase.getTemplate().equals("formaSablon")){
   reactCode.append("  const [formData, setFormData] = useState({});\n\n");
        reactCode.append("  const handleChange = (e) => {\n");
        reactCode.append("    setFormData({ ...formData, [e.target.name]: e.target.value });\n");
        reactCode.append("  };\n\n");

        reactCode.append("  return (\n");
        reactCode.append("    <form onSubmit={handleSubmit}>\n");

        reactCode.append("      <h2>").append(useCase.getName()).append("</h2>\n");
       
        reactCode.append("      <div>\n");
        List<Attribute> attributes = useCase.getEntity().getAttribute();
            try {
                addValidations(attributes, reactCode);
            } catch (IOException ex) {
                System.out.println("uslo u catch kod addValidations");
                ex.printStackTrace();
            }
        for (ScenarioStep step : useCase.getMainScenario().getStep()){
                Attribute attribute =findAttribute(step, attributes);
                if (attribute!=null){
                    
                     if (step.getAction().value().equals("ENTRY")){
            reactCode.append("        <div>\n");
            reactCode.append("          <label>").append(attribute.getName()).append("</label>\n");
    
              
            reactCode.append("          <input type=\"text\" name=\"").append(attribute.getName()).append("\" onChange={handleChange} />\n");
            reactCode.append("        </div>\n");
           
                     } else if (step.getAction().value().equals("SELECTION")){
                          reactCode.append("        <div>\n");
            reactCode.append("          <label>").append(attribute.getName()).append("</label>\n");
            reactCode.append("          <select name=\"\" class=\"\">\n" +
"        <option value=\"\" disabled selected>").append(attribute.getName()).append("</option></select>\n");
            reactCode.append("        </div>\n");
                     }
                     }
               if (step.getAction().value().equals("REQUEST_OPERATION")){
                    reactCode.append("      <button  type=\"submit\" style={{ \n" +
"      border: '1px solid black', \n" +
"      borderRadius: '0px', \n" +
"      backgroundColor: 'lightgray', \n" +
"      margin: '5px', \n" +
"      padding: '10px'\n"+
"    }}>").append(useCase.getName()).append("</button>\n");
                    
                } 
        
        
        } 
    } else if (useCase.getTemplate().equals("displaySablon")){
        
        reactCode.append("  return (\n");
        reactCode.append("<>");

        reactCode.append("      <h2>").append(useCase.getName()).append("</h2>\n");

       
        reactCode.append("      <div>\n");
       
           for (Attribute attribute : useCase.getEntity().getAttribute()) {
               reactCode.append("        <div>\n");
            reactCode.append("          <label style={{ \n" +
"      border: '1px solid black', \n" +
       " display: 'inline-blocks', \n"+            
"      borderRadius: '1px', \n" +
"      margin: '25px', \n" +
"      padding: '3px' \n"+
                  
"    }} >").append(attribute.getName()).append(": ").append("</label>\n");
            
            reactCode.append("          <label style={{ \n" +
"      border: '1px solid black', \n" +
                    " display: 'inline-blocks', \n"+      
"      borderRadius: '1px', \n" +
"      margin: '25px', \n" +
"      padding: '3px', \n"+
                    "width: '20px'\n"+
                   
"    }} >").append(attribute.getName()).append("</label>\n");
            reactCode.append("        <br></br>\n");
             reactCode.append("        <br></br>\n");
            reactCode.append("</div>\n");
            
    } reactCode.append("      </div>\n");
        reactCode.append("</>\n);\n") ;  
       
           
    }
    
        
     if (useCase.getTemplate().equals("formaSablon")){
         reactCode.append("      </div>\n");
         reactCode.append("</form> ); ");
     } 
     if (useCase.getTemplate().equals("tableSablon")){
         reactCode.append("      </div>\n");
         reactCode.append(("</>);"));
     }
     
        
        reactCode.append("};\n\n");
        reactCode.append("export default ").append(useCase.getName()).append("Component;\n");

        return reactCode.toString();
    }
//ovaj deo dalje ne zavisi od mojih Use case tako da necu ga trenutno zanemarujem i ostavljam ovakvog
    private static String generateAppJs(List<String> componentNames) {
        StringBuilder appJs = new StringBuilder();
        appJs.append("import React from 'react';\n");
        appJs.append("import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';\n");

        for (String componentName : componentNames) {
            appJs.append("import ").append(componentName).append(" from './").append(componentName).append("';\n");
        }

        appJs.append("\nconst App = () => (\n");
        appJs.append("  <Router>\n");
        appJs.append("    <div>\n");
        appJs.append("      <nav>\n");
        appJs.append("        <ul>\n");

        for (String componentName : componentNames) {
            appJs.append("          <li>\n");
            appJs.append("            <Link to=\"/").append(componentName.toLowerCase()).append("\">").append(componentName).append("</Link>\n");
            appJs.append("          </li>\n");
        }

        appJs.append("        </ul>\n");
        appJs.append("      </nav>\n");
        appJs.append("      <Routes>\n");

        for (String componentName : componentNames) {
            appJs.append("        <Route path=\"/").append(componentName.toLowerCase()).append("\" element={<").append(componentName).append(" />} />\n");
        }

        appJs.append("      </Routes>\n");
        appJs.append("    </div>\n");
        appJs.append("  </Router>\n");
        appJs.append(");\n\n");
        appJs.append("export default App;\n");

        return appJs.toString();
    }

    private static void saveToFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes());
    }
//zbog ove metode mora da se dodaje toString u entity svaki put
    private static Attribute findAttribute(ScenarioStep step, List<Attribute> attributes) {
       
        for (Attribute attribute : attributes) {
          //  System.out.println("atribut: "+attribute.getName());
           // System.out.println("iz koraka:"+step.getEntity().toString());
            if (step.getEntity().toString().equals(attribute.getName())){
              //  System.out.println(step.getEntity().toString());
              //  System.out.println(attribute.getName());
                return attribute;
            }
            
        } 
        return null;
    }

    private static void insertCodeAfterText(StringBuilder reactCode, String textToInsertAfter, String newCode) {
       int index = reactCode.indexOf(textToInsertAfter);
        if (index != -1) {
            index += textToInsertAfter.length();
            reactCode.insert(index, newCode);
        } else {
            System.out.println("Text not found in the existing code.");
        }
    }
    
    private static void addValidations(List<Attribute> attributes, StringBuilder reactCode) throws IOException {
        boolean general = false;
        boolean specific = false;
        boolean typeBased = false;
        boolean pass = false;
        boolean user = false;
        boolean name = false;
        boolean once = false;
        boolean onceSpecific = false;
        int counter = -1;
        LinkedList<String> scared = new LinkedList<>();
        LinkedList<String> numericValues = new LinkedList<>();
        LinkedList<String> textValues = new LinkedList<>();
        for (Attribute attribute : attributes) {
            if (typeBased==true && general==true && typeBased==true) return;
            if (attribute.getValidation().value().equals("generalContext") && general==false){
                
                String textToInsertAfter = "const handleChange = (e) => {\n" +
"    setFormData({ ...formData, [e.target.name]: e.target.value });\n" +
"  };\n";
                switch (attribute.getName()){
                    case "username" : String newCodeUsername = "function validateUsername ()\n {"
                            + "let usernameValid = true;"
                            + "if (formData.hasOwnProperty('username')) {\n" +
"      const username = formData.username;\n" +
"      usernameValid = /^[a-z]+$/.test(username);\n" +
"      if (!usernameValid) {\n" +
"        console.log('Username mora sadrzati samo mala slova.');\n" +
"      } else {return true;}\n" +
"    }\n" +
"  }\n";
                    insertCodeAfterText(reactCode, textToInsertAfter, newCodeUsername);
                    user=true;
                    scared.add("validateUsername()");
                    break;
                    case "password" : String newCodePass = "function validatePassword() \n{"
                            + "let passwordValid = true;"
                            + " if (formData.hasOwnProperty('password')) {\n" +
"      const password = formData.password;\n" +
"      passwordValid = /[a-z]/.test(password) && /[A-Z]/.test(password) && /\\d/.test(password);\n" +
"      if (!passwordValid) {\n" +
"        console.log('Password mora sadrzati bar jedno malo slovo, jedno veliko slovo i jednu cifru.');\n" +
"      }else {return true;};\n" +
"    }\n" +
"  }\n";
                  insertCodeAfterText(reactCode, textToInsertAfter, newCodePass);  
                  pass=true;
                  scared.add("validatePassword()");
                  break; 
                    case "name" : String newCodeName = "function validateName () \n {"
                            + "let nameValid = true;"
                            + "if (formData.hasOwnProperty('name')) { \n"
                            + "const name = formData.name; \n"
                            + "nameValid = /^[A-Z]/.test(name) && /^[A-Za-z]+$/.test(name);\n"
                            + "if (!nameValid){\n"
                            + "console.log('ime nije validno');} \n"
                            + "else {return true;}\n"
                            + "}\n"
                            + "}\n";
                           insertCodeAfterText(reactCode, textToInsertAfter, newCodeName);
                           name=true;
                           scared.add("validateName()");
                    
                    default: continue;
                }
    
                if (pass && user && name)
                general=true;
            
            }
          AttributeProperties ap = new AttributeProperties("src/main/resources/specificContext.properties");
         if (attribute.getValidation().value().equals("specificContext") ){
           
             System.out.println("uslo u specific Context za atribut: "+attribute);
             
            
             
             //ovo se radi na nivou aplikacije. na primer moze da se napravi fajl koji sastavlja klijent sa programerom
             //u kome se prolazi kroz specifican kontekst za svako od polja koje ce se potencijalno naci kao input
             if (attribute.getType().value().equalsIgnoreCase("NUMBER")){
                  if (!once)
             { 
             insertCodeBeforeText(reactCode, "return (\n" +
"    <form onSubmit={handleSubmit}>", "function validateNumericAttribute(attributeName, attributeValue, donjaGranica, gornjaGranica ) {\n" +
"  let attributeValid = true;\n" +
"  if (formData.hasOwnProperty(attributeName)) {\n" +
"\n" +
"    if (!isNaN(attributeValue)) {\n" +
"      const numericValue = parseFloat(attributeValue);\n" +
"\n" +
"      attributeValid = numericValue >= donjaGranica && numericValue <= gornjaGranica;\n" +
"\n" +
"      if (!attributeValid) {\n" +
"        console.log(`${attributeName} mora biti izmedju ${donjaGranica} i ${gornjaGranica}.`);\n" +
"      } else {\n" +
"        return true;\n" +
"      }\n" +
"    } else {\n" +
"      console.log(`${attributeName} mora biti numericka vrednost.`);\n" +
"      attributeValid = false;\n" +
"    }\n" +
"  }\n" +
"  return attributeValid;\n" +
"};\n");
             once=true;}
                 numericValues.add(attribute.getName());
                 counter++;
             }
             if (attribute.getType().value().equalsIgnoreCase("STRING")){
                  if (!onceSpecific)
             { 
             insertCodeBeforeText(reactCode, "return (\n" +
"    <form onSubmit={handleSubmit}>", "function validateTextFinalValues(textInput, allowedValues) {\n" +
"    if (!allowedValues.includes(textInput)) {\n" +
"        console.log(\"nedozvoljena vrednost unosa\");\n" +
"    } else return true;\n" +
"};");
             onceSpecific=true;}
                 textValues.add(attribute.getName());
                 counter++;
             }
             //OVO JE POKUSAJ DA SE VISE PUTA PRIMENJUJE SVE DA BI SVE STO TREBA BILO U SUBMIT FUNKCIJI ZA SPECIFIC CONTEXT
             //SMISLICU BOLJI NACIN
            // if (counter==textValues.size()+numericValues.size())
             //specific=true;
                
         }
         if (attribute.getValidation().value().equals("typeBased") && typeBased==false){
             System.out.println("uslo u typeBased za atribut: "+attribute);
             typeBased=true;
         }
           
        
    }
        String submitFunction = "const handleSubmit = (e) => {\n" +
"  e.preventDefault();\n" +
"  if (" +formatValidationNumeric(numericValues)+ "&&" + formatValidationText(textValues)+ "&&" +formatValidationFunctions(scared)+") {\n" +
"    console.log('Form data submitted:', formData);\n" +
"  } else {\n" +
"    console.log('Neispravan unos');\n" +
"  }\n" +
"}; \n";
                insertCodeBeforeText(reactCode, "return (\n" +
"    <form onSubmit={handleSubmit}>", submitFunction);
    }

    private static StringBuilder formatValidationFunctions(LinkedList<String> scared) {
        StringBuilder formatted = new StringBuilder();
        if (scared.isEmpty()) formatted.append("true");
        else{
        for (String validationFunction : scared) {
            formatted.append(validationFunction);
            if (!validationFunction.equalsIgnoreCase(scared.getLast()))
                    formatted.append("&&"); }
        } return formatted;
    }
      private static StringBuilder formatValidationNumeric(LinkedList<String> numericAttributes) throws IOException {
        StringBuilder formatted = new StringBuilder();
        AttributeProperties ap = new AttributeProperties("src/main/resources/specificContext.properties");
        if (numericAttributes.isEmpty()) formatted.append("true");
        else{
        for (String numa : numericAttributes) {
            Double donjaGranica= ap.getLowerBound(numa);
                 Double gornjaGranica = ap.getUpperBound(numa);
            formatted.append("validateNumericAttribute(").append("'").append(numa).append("'").append(",").append("formData.").append(numa).append(",").append(String.valueOf(donjaGranica)).append(",").append(String.valueOf(gornjaGranica)).append(") ");
            if (!numa.equalsIgnoreCase(numericAttributes.getLast()))
                    formatted.append("&&"); }
        } return formatted;
    }
      
        private static StringBuilder formatValidationText(LinkedList<String> textAttributes) throws IOException {
        StringBuilder formatted = new StringBuilder();
         AttributeProperties ap = new AttributeProperties("src/main/resources/specificContext.properties");
        if (textAttributes.isEmpty()) formatted.append("true");
        else{
        for (String attName : textAttributes) {
            formatted.append("validateTextFinalValues(").append("formData.").append(attName).append(",").append(ap.getAllowedValues(attName)).append(")");
            if (!attName.equalsIgnoreCase(textAttributes.getLast()))
                    formatted.append("&&"); }
        } return formatted;
    }

    private static void insertCodeBeforeText(StringBuilder reactCode, String textToInsertBefore, String newCode) {
         int index = reactCode.indexOf(textToInsertBefore);
    if (index != -1) {
        reactCode.insert(index, newCode);
    } else {
        System.out.println("Text not found in the existing code.");
    }
    }
   
}