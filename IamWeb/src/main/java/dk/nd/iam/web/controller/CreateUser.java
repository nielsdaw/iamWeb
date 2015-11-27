package dk.nd.iam.web.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.nd.iam.web.genericservlet.GenericSpringServlet;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/create-user")
public class CreateUser extends GenericSpringServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sessionFunctions.getFlashMessage(request, "msgCreate");
		RequestDispatcher view = request.getRequestDispatcher("/views/create-user.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if(authenticator.isValidUsername(username)){	// check username and password
			if(!userModel.usernameExist(username)){
				if(authenticator.isValidPassword(password)){
					userModel.createUser(username, password);
					request.getSession().removeAttribute("msg");
					sessionFunctions.sendFlashMessage(request, "User successfully created!", "msgOK");
					RequestDispatcher view = request.getRequestDispatcher("index.jsp");
					view.forward(request, response);
				}
				else{
					sessionFunctions.sendFlashMessage(request, "Incorrect password, please choose another", "msgCreate");
					RequestDispatcher view = request.getRequestDispatcher("/views/create-user.jsp");
					view.forward(request, response);
				}
			}
			else{
				sessionFunctions.sendFlashMessage(request, "User already exist! Please choose another name", "msgCreate");
				RequestDispatcher view = request.getRequestDispatcher("/views/create-user.jsp");
				view.forward(request, response);
			}
		}
		else{
			sessionFunctions.sendFlashMessage(request, "invalid username, it must start with a character from A-z and "
					+ "no special characters allowed", "msgCreate");
			RequestDispatcher view = request.getRequestDispatcher("/views/create-user.jsp");
			view.forward(request, response);
		}
	}
}
