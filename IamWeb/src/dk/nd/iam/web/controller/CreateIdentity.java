package dk.nd.iam.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dk.nd.iam.web.genericservlet.GenericSpringServlet;
import dk.nd.iam.web.model.Authenticator;
import dk.nd.iam.web.model.SessionFunctions;
import dk.nd.iam.web.model.User;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;


/**
 * Servlet implementation class Create
 */
@WebServlet("/create-identity")
public class CreateIdentity extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IdentityDAOInterface dao;
	
	@Autowired
	Authenticator authenticator;
	
	@Autowired
	SessionFunctions sessionFunctions;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = sessionFunctions.getUserSession(request);
		sessionFunctions.validateUserSession(user, "/views/create-identity.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String rawDate = request.getParameter("date");
		Identity identity = new Identity(fname, lname, null);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(rawDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		identity.setBirthDate(date);
		
		if(fname != null || lname != null || date != null){
			List<Identity> search = dao.search(identity);
			if(search.size() == 0){	// it's not there
				dao.write(identity);
				RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
				view.forward(request, response);
			}
			else{	// the identity already exist
				sessionFunctions.sendFlashMessage(request, "Identity already exists", "msg");
				RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
				view.forward(request, response);
			}
		}
		else{
			sessionFunctions.sendFlashMessage(request, "Empty input for identity creation", "msg");
			RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
			view.forward(request, response);
		}
	}
}
