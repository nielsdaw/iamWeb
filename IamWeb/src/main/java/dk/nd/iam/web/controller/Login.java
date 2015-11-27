package dk.nd.iam.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.nd.iam.web.genericservlet.GenericSpringServlet;
import dk.nd.iam.web.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
     
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		sessionFunctions.getFlashMessage(request, "msg");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.getSession().removeAttribute("msgOK");
		if(userModel.getUser(username, password) != null){	// check credentials
			RequestDispatcher view = request.getRequestDispatcher("/views/main.jsp");
			User user = new User(username, password);
			sessionFunctions.setUserSession(request, user);
			view.forward(request, response);
		}
		else{
			sessionFunctions.sendFlashMessage(request, "Wrong username or password, please try agin", "msg");
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		}
	}

}
