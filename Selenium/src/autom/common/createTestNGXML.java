package autom.common;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import Exception.FilloException;
import Fillo.Connection;
import Fillo.Fillo;
import Fillo.Recordset;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//import com.vidsys.autom.common.TestPrefs;

public class createTestNGXML {
	protected String sXMLFileName;
	protected String sXMLFilePath;
	protected String sWorkBookSheetName;
	protected String Row;

	public createTestNGXML() {
	}

	public void setRow(String iRow){
		Row=iRow;
	}

	public createTestNGXML(String sSuiteName) {
		sWorkBookSheetName=sSuiteName;
	}
	public void setName(String sName){
		sXMLFileName=sName;
	}

	public void setPath(String sName){
		sXMLFilePath=sName;
	}
	
	
	protected void modifyXMLSysprefs(String sXMLFileFullName){
//		sXMLFilePath=sName;
		
		   try {
				String filepath = sXMLFileFullName;
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces, or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				 Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
//				Node staff = doc.getElementsByTagName("staff").item(0);
//
//				// update staff attribute
//				NamedNodeMap attr = staff.getAttributes();
//				Node nodeAttr = attr.getNamedItem("id");
//				nodeAttr.setTextContent("2");

				// append a new node to staff
//				Element age = doc.createElement("age");
//				age.appendChild(doc.createTextNode("28"));
//				staff.appendChild(age);

				// loop the staff child node
				NodeList list = staff.getChildNodes();

				for (int i = 0; i < list.getLength(); i++) {

		                   Node node = list.item(i);

				   // get the salary element, and update the value
				   if ("salary".equals(node.getNodeName())) {
					node.setTextContent("2000000");
				   }

		                   //remove firstname
				   if ("firstname".equals(node.getNodeName())) {
					staff.removeChild(node);
				   }

				}

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filepath));
				transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
				transformer.transform(source, result);
//				
//				System.out.println("Done");

			   } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			   } catch (TransformerException tfe) {
				tfe.printStackTrace();
			   } catch (IOException ioe) {
				ioe.printStackTrace();
			   } catch (SAXException sae) {
				sae.printStackTrace();
			   }
	}
	
	protected boolean modifyXMLSysprefs(String filePath, 
										String sTagName, 
										String sAttributeName, 
										String sTargetKey,
										String sTargetNodeValue,
										String sDocType){
        boolean bModified=false;
		try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);
            String sTempkeyString;
            NodeList nodeList = doc.getElementsByTagName(sTagName);
            if(nodeList.getLength()>0){
                for(int x=0,size= nodeList.getLength(); x<size; x++) {
                	sTempkeyString="";
                	sTempkeyString=nodeList.item(x).getAttributes().getNamedItem(sAttributeName).getNodeValue();
                    if (sTempkeyString.equalsIgnoreCase(sTargetKey)){
                    	nodeList.item(x).setTextContent(sTargetNodeValue);
                    	bModified=true;
                    }
                }
            } else {
            	System.out.println("Node with tag name '" + sTagName + "' not found.");
            }
            if(bModified){
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc.getDocumentElement());
                StreamResult result = new StreamResult(new File(filePath));
                if(sDocType!=null){
        			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, sDocType);                	
                }
    			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//    			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
//    			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                transformer.transform(source, result);
            }
	       } catch (ParserConfigurationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (TransformerException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SAXException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		return bModified;
	}
	
	public static boolean hasColumn(Recordset recordSet, String columnName) {
		try{
			ResultSetMetaData rsmd = ((ResultSet) recordSet).getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
				if (columnName.equals(rsmd.getColumnName(x))) {
					return true;
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public void createXML(){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			doc.appendChild(rootElement);

			Attr rootNameAttribute = doc.createAttribute("name");
			rootNameAttribute.setValue("Automation Suite");
//
//			Attr rootParallelAttribute = doc.createAttribute("parallel");
//			rootParallelAttribute.setValue("none");

			Attr rootParallelAttribute2 = doc.createAttribute("preserve-order");
			rootParallelAttribute2.setValue("true");

			rootElement.setAttributeNode(rootNameAttribute);
//			rootElement.setAttributeNode(rootParallelAttribute);
			rootElement.setAttributeNode(rootParallelAttribute2);

			Element testElement = doc.createElement("test");
			rootElement.appendChild(testElement);

			Attr testNameAttribute = doc.createAttribute("name");
			testNameAttribute.setValue("SeleniumAutomationTestSuite");
			testElement.setAttributeNode(testNameAttribute);

			Attr testNameAttribute2 = doc.createAttribute("verbose");
			testNameAttribute2.setValue("2");
			testElement.setAttributeNode(testNameAttribute2);
			Element classesElement = doc.createElement("classes");
			testElement.appendChild(classesElement);

			Fillo fillo = new Fillo();
			Connection con = fillo.getConnection("C:\\Temp\\debug\\testCaseStatus.xlsx");
			if(sWorkBookSheetName==null){
				sWorkBookSheetName="Sheet1";
			}
			String query = "Select * from " + sWorkBookSheetName;

			//	            String all = rs.getString("Column_Name");

			Recordset recordSet = con.executeQuery(query);
			ArrayList<String> sColumnNames=recordSet.getFieldNames();
			System.out.println("Key: " + recordSet.getFieldNames());
			while (recordSet.next()) {
				if (recordSet.getField("Execute").equalsIgnoreCase("Y")) {
					Element classElement = doc.createElement("class");
					Attr classNameAttribute = doc.createAttribute("name");

					if(sColumnNames.contains("Class")){
						classNameAttribute.setValue("com.vidsys.autom.test.regression." + recordSet.getField("Class"));
						classElement.setAttributeNode(classNameAttribute);
						classesElement.appendChild(classElement);
					} else {
						classNameAttribute.setValue("com.vidsys.autom.test.regression." + recordSet.getField("TestCase"));
						classElement.setAttributeNode(classNameAttribute);
						classesElement.appendChild(classElement);
					}
				}
			}
			recordSet.close();
			con.close();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(sXMLFilePath + sXMLFileName + ".xml"));
//			transformer.transform(source, result);
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
//			StreamResult consoleResult = new StreamResult(System.out);
//			transformer.transform(source, consoleResult);
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void createSingleTCXML(String sClassName){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			doc.appendChild(rootElement);

			Attr rootNameAttribute = doc.createAttribute("name");
			rootNameAttribute.setValue("Automation Suite");

//			Attr rootParallelAttribute = doc.createAttribute("parallel");
//			rootParallelAttribute.setValue("none");

			Attr rootParallelAttribute2 = doc.createAttribute("preserve-order");
			rootParallelAttribute2.setValue("true");

			rootElement.setAttributeNode(rootNameAttribute);
//			rootElement.setAttributeNode(rootParallelAttribute);
			rootElement.setAttributeNode(rootParallelAttribute2);

			Element testElement = doc.createElement("test");
			rootElement.appendChild(testElement);

			Attr testNameAttribute = doc.createAttribute("name");
			testNameAttribute.setValue("SeleniumAutomationTestSuite");
			testElement.setAttributeNode(testNameAttribute);

			Attr testNameAttribute2 = doc.createAttribute("verbose");
			testNameAttribute2.setValue("2");
			testElement.setAttributeNode(testNameAttribute2);


			Element classesElement = doc.createElement("classes");
			testElement.appendChild(classesElement);

			Element classElement = doc.createElement("class");
			Attr classNameAttribute = doc.createAttribute("name");
			classNameAttribute.setValue("autom.common." + sClassName);
			classElement.setAttributeNode(classNameAttribute);
			classesElement.appendChild(classElement);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(sXMLFilePath + sXMLFileName + ".xml"));
//			transformer.transform(source, result);
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
//			StreamResult consoleResult = new StreamResult(System.out);
			
			// Output to console for testing
//			StreamResult consoleResult = new StreamResult(System.out);
//			transformer.transform(source, consoleResult);
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createExcuteSuiteXML(String sExcelFullLoc, String sSheet){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.newDocument();
			Element rootElement = doc.createElement("suite");
			doc.appendChild(rootElement);

			Attr rootNameAttribute = doc.createAttribute("name");
			rootNameAttribute.setValue("Automation Suite");

//			Attr rootParallelAttribute = doc.createAttribute("parallel");
//			rootParallelAttribute.setValue("none");

			Attr rootParallelAttribute2 = doc.createAttribute("preserve-order");
			rootParallelAttribute2.setValue("true");

			rootElement.setAttributeNode(rootNameAttribute);
//			rootElement.setAttributeNode(rootParallelAttribute);
			rootElement.setAttributeNode(rootParallelAttribute2);

			Element testElement = doc.createElement("test");
			rootElement.appendChild(testElement);

			Attr testNameAttribute = doc.createAttribute("name");
			testNameAttribute.setValue("SeleniumAutomationTestSuite");
			testElement.setAttributeNode(testNameAttribute);

			Attr testNameAttribute2 = doc.createAttribute("verbose");
			testNameAttribute2.setValue("2");
			testElement.setAttributeNode(testNameAttribute2);


			Element classesElement = doc.createElement("classes");
			testElement.appendChild(classesElement);

			Fillo fillo = new Fillo();
			Connection con = fillo.getConnection(sExcelFullLoc);
			if(sSheet==null){
				sWorkBookSheetName="Sheet1";
			}
			String query = "Select * from " + sWorkBookSheetName;
			Recordset recordSet = con.executeQuery(query);

			while (recordSet.next()) {
				if (recordSet.getField("Execute").equalsIgnoreCase("Y")) {
					Element classElement = doc.createElement("class");
					Attr classNameAttribute = doc.createAttribute("name");
					classNameAttribute.setValue("com.vidsys.autom.test.regression." + recordSet.getField("TestCase"));
					classElement.setAttributeNode(classNameAttribute);
					classesElement.appendChild(classElement);
				}
			}
			recordSet.close();
			con.close();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(sXMLFilePath + sXMLFileName + ".xml"));
			transformer.transform(source, result);
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			// Output to console for testing
//			StreamResult consoleResult = new StreamResult(System.out);
//			transformer.transform(source, consoleResult);
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void alignSysprefs(String file1, String file2, String sKeyAttrValue) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();		
			Document document = builder.parse(new File(file1));
			Element rootElement = document.getDocumentElement();
			Document document2 = builder.parse(new File(file2));
			Element rootElement2 = document2.getDocumentElement();
			String requestQueueName2 = getString("entry", rootElement2,sKeyAttrValue);
			String requestQueueName = getString("entry", rootElement,sKeyAttrValue);
			if(!requestQueueName2.equalsIgnoreCase(requestQueueName)){
				System.out.println("found not eque");
				Node abc=getNode("entry", rootElement,sKeyAttrValue);
				abc.setTextContent(requestQueueName);
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(document);
	            StreamResult result = new StreamResult(new File(file2));
	            transformer.transform(source, result);
	            System.out.println("updated");
			}
            System.out.println("Done");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getString(String tagName, Element element, String sKeyValue) {
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			for(int loop=0; loop<list.getLength(); ++loop){
				NodeList subList = list.item(loop).getChildNodes();
				if (subList != null && subList.getLength() > 0) {
					Element baseElmnt_gold = (Element) list.item(loop);
					NamedNodeMap baseElmnt_gold_attr = baseElmnt_gold.getAttributes();
					for (int i = 0; i < baseElmnt_gold_attr.getLength(); ++i){
						Node attr = baseElmnt_gold_attr.item(i);
						if(attr.getNodeValue().contains(sKeyValue)){
							return subList.item(i).getNodeValue();
						}
					}
				}
			}
		}
		return null;
	}
	
	
	protected Node getNode(String tagName, Element element, String sKeyValue) {
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			for(int loop=0; loop<list.getLength(); ++loop){
				NodeList subList = list.item(loop).getChildNodes();
				if (subList != null && subList.getLength() > 0) {
					Element baseElmnt_gold = (Element) list.item(loop);
					NamedNodeMap baseElmnt_gold_attr = baseElmnt_gold.getAttributes();
					for (int i = 0; i < baseElmnt_gold_attr.getLength(); ++i){
						Node attr = baseElmnt_gold_attr.item(i);
						if(attr.getNodeValue().contains(sKeyValue)){
							return list.item(loop);
						}
					}
				}
			}
		}
		return null;
	}
	
}

