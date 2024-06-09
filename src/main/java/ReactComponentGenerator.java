
import generated.Actor;
import generated.Attribute;
import generated.Entity;
import generated.ScenarioStep;
import generated.Specification;
import generated.UseCase;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ReactComponentGenerator {

    public static void main(String[] args) {
        try {
            // ?????? XML ???????? ? ??????????? ? Java ???????
            JAXBContext jaxbContext = JAXBContext.newInstance(Specification.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Specification specification = (Specification) unmarshaller.unmarshal(new File("src/main/resources/specification.xml"));

            // ?????? ????? ?????????? ?? App.js
            List<String> componentNames = new ArrayList<>();

            // ?????????? React ???? ?? ????? UseCase
            for (UseCase useCase : specification.getUseCase()) {
                String componentName = useCase.getName() + "Component";
                componentNames.add(componentName);
                String reactCode = generateUseCaseComponent(useCase);
                saveToFile("src/main/javascript/" + componentName + ".js", reactCode);
                System.out.println("React ?????????? ?? ??????? ?????????? ?? " + useCase.getName());
            }

            // ?????????? App.js
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
        reactCode.append("  const [formData, setFormData] = useState({});\n\n");
        reactCode.append("  const handleChange = (e) => {\n");
        reactCode.append("    setFormData({ ...formData, [e.target.name]: e.target.value });\n");
        reactCode.append("  };\n\n");
        reactCode.append("  const handleSubmit = (e) => {\n");
        reactCode.append("    e.preventDefault();\n");
        reactCode.append("    console.log('Form data submitted:', formData);\n");
        reactCode.append("  };\n\n");
        reactCode.append("  return (\n");
        reactCode.append("    <form onSubmit={handleSubmit}>\n");

        reactCode.append("      <h2>").append(useCase.getName()).append("</h2>\n");

        // ?????? ??? ??????
        /*if (useCase.getActor() != null) {
            reactCode.append("      <h3>Actors:</h3>\n");
            for (Actor actor : useCase.getActor()) {
                reactCode.append("      <p>").append(actor.getName()).append("</p>\n");
            }
        }*/

        // ???????? ? ???????? ??? ?????? ????
        Entity entity = useCase.getEntity();
        reactCode.append("      <div>\n");
        List<Attribute> attributes = useCase.getEntity().getAttribute();
       // reactCode.append("        <h4>").append(entity.getName()).append("</h4>\n");
       if (useCase.getTemplate().equals("table")){
           int len = useCase.getEntity().getAttribute().size();
           ArrayList<String> kolone = new ArrayList<String>();
           for (Attribute attribute : useCase.getEntity().getAttribute()) {
               kolone.add(attribute.getName());
           }
          
           DefaultTableModel dtm = new DefaultTableModel(kolone.toArray(),10);
           JTable tabelaTurnira = new JTable(dtm);
           String reactCodeTemplate = 
  
    
    "        <table>\n" +
    "            <thead>\n" +
    "                <tr>\n" +
    "                    %s\n" +
    "                </tr>\n" +
    "            </thead>\n" +
    "            <tbody>\n" +

    "                    <tr>\n" +
    "                        <td>1</td>" +
                  " <td>2</td>" +
                       "<td>1</td>" +
                           "<td>1</td>" +
    "                    </tr>\n" +
                  
    "            </tbody>\n" +
    "        </table>\n" +
    "\n" ;
    

String tableHeaderTemplate = "<th>%s</th>";
String tableDataTemplate = "<td>{row['%s']}</td>";
DefaultTableModel model = (DefaultTableModel) tabelaTurnira.getModel();
int rowCount = model.getRowCount();
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
    
           
       } else if (useCase.getTemplate().equals("form")){
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
"        <option value=\"\" disabled selected>").append(attribute.getName()).append("</option></select>\"\n");
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
                    
                } //else reactCode.append("          <label>").append(attribute.getName()).append("</label>\n");
        
        
        }
    } else if (useCase.getTemplate().equals("display")){
        
           for (Attribute attribute : useCase.getEntity().getAttribute()) {
               reactCode.append("        <div>\n");
            reactCode.append("          <label>").append(attribute.getName()).append("</label>\n");
            reactCode.append("          <label>").append(attribute.getName()).append("</label>\n");
            reactCode.append("        </div>\n");
    }
    }
    
        reactCode.append("      </div>\n");
      

       // reactCode.append("      <button type=\"submit\">Submit</button>\n");
        reactCode.append("    </form>\n");
        reactCode.append("  );\n");
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

    private static Attribute findAttribute(ScenarioStep step, List<Attribute> attributes) {
       
        for (Attribute attribute : attributes) {
            System.out.println("atribut: "+attribute.getName());
            System.out.println("iz koraka:"+step.getEntity().toString());
            if (step.getEntity().toString().equals(attribute.getName())){
                System.out.println(step.getEntity().toString());
                System.out.println(attribute.getName());
                return attribute;
            }
            
        } 
        System.out.println("nouu");return null;
    }

   
}