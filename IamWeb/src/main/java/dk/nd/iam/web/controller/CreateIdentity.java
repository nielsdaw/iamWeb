package dk.nd.iam.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
 * Servlet implementation class Create
 */
@WebServlet("/create-identity")
public class CreateIdentity extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = sessionFunctions.getUserSession(request);
		request.getSession().removeAttribute("msg");
		request.getSession().removeAttribute("msgCreate");
		sessionFunctions.validateUserSession(user, "/views/create-identity.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");	// get params 
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String rawDate = request.getParameter("date");
		
		if(!authenticator.isValidName(fname)){	// check firstname
			request.getSession().removeAttribute("msgCreate");
			sessionFunctions.sendFlashMessage(request, "Wrong input for firstname", "msg");
			RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
			view.forward(request, response);
		}
		else if(!authenticator.isValidName(lname)){	//check lastname
			request.getSession().removeAttribute("msgCreate");
			sessionFunctions.sendFlashMessage(request, "Wrong input for lastname", "msg");
			RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
			view.forward(request, response);
		}
		else if(!authenticator.isValidEmail(email)){	//check email
			request.getSession().removeAttribute("msgCreate");
			sessionFunctions.sendFlashMessage(request, "Wrong input for email", "msg");
			RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
			view.forward(request, response);
		}
		else if(!authenticator.isValidBirthdate(rawDate)){	//check birthdate
			request.getSession().removeAttribute("msgCreate");
			sessionFunctions.sendFlashMessage(request, "Wrong input for birthDate - must be yyyy-mm-dd", "msg");
			RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
			view.forward(request, response);
		}
		else{
			Identity identity = new Identity(fname, lname, email);	//set birthdate
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = formatter.parse(rawDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			identity.setBirthDate(date);
			Identity identityEmail = new Identity("", "", email);
			List<Identity> search = dao.search(identityEmail);	//check if email already exist
			if(search.size() == 0 ){	// it's not there
				dao.write(identity);
				System.out.println(fname +" "+lname +" "+  rawDate);
				request.getSession().removeAttribute("msg");	// remove prev msg
				sessionFunctions.sendFlashMessage(request, "Identity succesfully created", "msgCreate");
				RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
				view.forward(request, response);
			}
			else{	// the identity already exist
				request.getSession().removeAttribute("msgCreate");
				sessionFunctions.sendFlashMessage(request, "Identity already exists with email: " + email, "msg");
				RequestDispatcher view = request.getRequestDispatcher("/views/create-identity.jsp");
				view.forward(request, response);
			}
		}
	}

}
