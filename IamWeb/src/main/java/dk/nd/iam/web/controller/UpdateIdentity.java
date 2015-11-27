package dk.nd.iam.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.nd.iam.web.genericservlet.GenericSpringServlet;
import dk.nd.iam.web.model.User;
import fr.tbr.iamcore.datamodel.Identity;

/**
 * Servlet implementation class UpdateIdentity
 */
@WebServlet("/update-identity")
public class UpdateIdentity extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateIdentity() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = sessionFunctions.getUserSession(request);
		sessionFunctions.validateUserSession(user, "/views/edit-identity.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String birthDate = request.getParameter("birthdate");
		Identity identity = sessionFunctions.getCurrentIdentity(request);
		if(authenticator.isValidName(fname)){
			identity.setFirstName(fname);	// set firstname if valid
		}
		if(authenticator.isValidName(lname)){
			identity.setLastName(lname);	// set lastname if valid
		}
		if(authenticator.isValidBirthdate(birthDate)){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = formatter.parse(birthDate);
				identity.setBirthDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		dao.update(identity);	// update identity
		sessionFunctions.setIdentity(request, identity);	// update identity in session
		sessionFunctions.sendFlashMessage(request, "Identity has been updated", "msgSearch");
		RequestDispatcher view = request.getRequestDispatcher("/views/search-identity.jsp");
		view.forward(request, response);
	}
}
