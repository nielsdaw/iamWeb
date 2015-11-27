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
import dk.nd.iam.web.model.SessionFunctions;
import dk.nd.iam.web.model.User;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("/main")
public class MainPage extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	SessionFunctions sessionFunctions;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User currentUser = sessionFunctions.getUserSession(request);
		
		if(currentUser == null){
			RequestDispatcher view = request.getRequestDispatcher("/login");
			sessionFunctions.sendFlashMessage(request, "Please log in to enter site", "msg");
			view.forward(request, response);
		}
		else{
			RequestDispatcher view = request.getRequestDispatcher("/views/main.jsp");
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
