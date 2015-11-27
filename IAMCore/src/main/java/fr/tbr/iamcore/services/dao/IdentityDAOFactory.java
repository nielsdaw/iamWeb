package fr.tbr.iamcore.services.dao;

import fr.tbr.iam.configuration.IConfiguration;
import fr.tbr.iam.configuration.ConfigurationFactory;
import fr.tbr.iamcore.services.dao.impl.IdentityFileDAO;
import fr.tbr.iamcore.services.dao.impl.IdentityJDBCDAO;
import fr.tbr.iamcore.services.dao.impl.IdentityXMLDAO;

public class IdentityDAOFactory {

	public static IdentityDAOInterface getDao() {
		IConfiguration conf = ConfigurationFactory.getConfiguration();
		IdentityDAOInterface dao = null;
		switch (conf.getConfiguredDAOType()) {
		case "XML":
			dao = new IdentityXMLDAO();
			break;

		case "FLAT":
			dao = new IdentityFileDAO();
			break;
		
		default:
			dao = new IdentityJDBCDAO();
		
		
		}
		return dao;

	}

}
