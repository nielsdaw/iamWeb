package fr.tbr.iamcore.tests.identity.xml;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.impl.IdentityXMLDAO;

public class IdentitiesXMLTests {

	public static void main(String[] args) throws ParserConfigurationException, IOException {

//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		Document doc = db.newDocument();
//
//		Element identities = doc.createElement("identities");
//		doc.appendChild(identities);
//
//		Element identity = doc.createElement("identity");
//		identity.setAttribute("name", "thomas");
//
//		identities.appendChild(identity);

		Identity identity = new Identity("Thomas", "Broussard","thomas.broussard@tbr.com");
		IdentityXMLDAO dao = new IdentityXMLDAO();
		dao.write(identity);
		
	}

}
