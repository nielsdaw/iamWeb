package fr.tbr.iamcore.services.dao.impl;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;

/**
 * This is a DAO that uses XML as a data source
 * @author Tom
 *
 */
public class IdentityXMLDAO  implements IdentityDAOInterface{
	
	private Document doc;
	//reference to the output file in write mode
	private PrintWriter writer;
	
	//The transformer allows to convert a Document into a String representation (Document to String)
	private Transformer transformer;
	
	/**
	 * This creates a new instance of the XMLDAO.
	 */
	public IdentityXMLDAO(){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			this.doc = db.newDocument();
			this.doc.appendChild(this.doc.createElement("identities"));
			File file = new File("/identities/identities.xml");
			file.createNewFile();
			this.writer = new PrintWriter(file);
			TransformerFactory tf = TransformerFactory.newInstance();
			this.transformer = tf.newTransformer();
			
		} catch (ParserConfigurationException e) {
			//TODO what happens when something goes wrong during the construction?
			e.printStackTrace();
		} catch (Exception e){
			
		}
	}
	
	/**
	 * Writes an identity in the XML file.
	 * @param identity
	 */
	public void write(Identity identity){
		//creates a new element to be inserted in the XML Document
		Element idElement = this.doc.createElement("identity");
		
		//Populates the element's attributes with the Identity data
		idElement.setAttribute("firstName", identity.getFirstName());
		idElement.setAttribute("lastName", identity.getLastName());
		idElement.setAttribute("birthDate", String.valueOf(identity.getBirthDate()));
		idElement.setAttribute("email", identity.getEmail());
		
		//insert that element under the Root element (reachable through the getDocumentElement() method)
		this.doc.getDocumentElement().appendChild(idElement);
		writeDocumentInTheFile();
		
	}
	
	private void writeDocumentInTheFile(){
		String output = "";
		try{
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
		    output = writer.getBuffer().toString().replaceAll("\n|\r", "");
			System.out.println(output);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		writer.println(output);
		writer.flush();
	}
	public void update(Identity identity){
		
	}
	public void delete(Identity identity){
		
	}
	public List<Identity> search(Identity identity){
		return null;
		
	}
	
	public List<Identity> readAll(){
		return null;
	}

}
