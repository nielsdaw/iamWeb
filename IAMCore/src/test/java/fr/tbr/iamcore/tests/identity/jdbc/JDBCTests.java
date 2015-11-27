package fr.tbr.iamcore.tests.identity.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.impl.IdentityJDBCDAO;

public class JDBCTests {

	
	
	public static void main(String[] args) throws Exception {
		IdentityJDBCDAO dao = new IdentityJDBCDAO();
		Identity newIdentity = new Identity("Jean-Luc", "Tholozan", "jltho@gmail.com");
		newIdentity.setBirthDate(new java.util.Date());
		dao.write(newIdentity);
		List<Identity> identityList = dao.readAll();
		System.out.println(identityList);
		Identity toBeUpdated = identityList.get(0);
		toBeUpdated.setFirstName("Jeremie");
		dao.update(toBeUpdated);
		System.out.println(dao.readAll());

		
	}


	private static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		//1-Set up the driver
		Class.forName("org.apache.derby.jdbc.ClientDriver");
		
		//2-Prepare the connection
		String url = "jdbc:derby://localhost:1527/IAM;create=true";
		
		//3-connect
		Connection connection = DriverManager.getConnection(url, "tom", "tom");
		return connection;
	}
	
	
	public static void searchTest(Connection connection) throws SQLException{
		List<Identity> identities = new ArrayList<Identity>();
		String query = "SELECT * from IDENTITIES";
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.execute();
		ResultSet results = pstmt.getResultSet();
		while(results.next()){
			String firstName = results.getString("IDENTITIES_FIRSTNAME");
			String lastName = results.getString("IDENTITIES_LASTNAME");
			String email = results.getString("IDENTITIES_EMAIL");
			Date birthDate = results.getDate("IDENTITIES_BIRTHDATE");
			Identity id = new Identity(firstName, lastName, email);
			id.setBirthDate(birthDate);
			identities.add(id);
		}
		System.out.println(identities);
	}
	
}
