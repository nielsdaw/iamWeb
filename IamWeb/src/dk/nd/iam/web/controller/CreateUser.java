package dk.nd.iam.web.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import dk.nd.iam.web.genericservlet.GenericSpringServlet;
import dk.nd.iam.web.model.Authenticator;
import dk.nd.iam.web.model.SessionFunctions;
import dk.nd.iam.web.model.UserModelInterface;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/create-user")
public class CreateUser extends GenericSpringServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UserModelInterface userModel;
	
	@Autowired
	Authenticator authenticator;
	
	@Autowired
	SessionFunctions sessionFunctions;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sessionFunctions.getFlashMessage(request, "msg");
		RequestDispatcher view = request.getRequestDispatcher("/views/create-user.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sessionFunctions.getFlashMessage(request, "msg");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if(authenticator.isValidUsername(username)){
			if(!userModel.usernameExist(username)){
				if(authenticator.isValidPassword(password)){
					userModel.createUser(username, password);
					sessionFunctions.sendFlashMessage(request, "User successfully created!", "msgOk");
					RequestDispatcher view = request.getRequestDispatcher("/login");
					view.forward(request, response);
				}
				else{
					sessionFunctions.sendFlashMessage(request, "Incorrect password, please choose another", "msg");
					RequestDispatcher view = request.getRequestDispatcher("/views/create-user.jsp");
					view.forward(request, response);
				}
			}
			else{
				sessionFunctions.sendFlashMessage(request, "User already exist! Please choose another name", "msg");
				RequestDispatcher view = request.getRequestDispatcher("/views/create-user.jsp");
				view.forward(request, response);
			}
		}
		else{
			sessionFunctions.sendFlashMessage(request, "invalid username, it must start with a character from A-z and "
					+ "no special characters allowed", "msg");
			RequestDispatcher view = request.getRequestDispatcher("/views/create-user.jsp");
			view.forward(request, response);
		}
	}

}
