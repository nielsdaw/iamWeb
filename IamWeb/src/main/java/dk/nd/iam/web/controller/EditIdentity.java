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
 * Servlet implementation class EditIdentity
 */
@WebServlet("/edit")
public class EditIdentity extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditIdentity() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = sessionFunctions.getUserSession(request);
		sessionFunctions.validateUserSession(user, "/views/search-identity.jsp", request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		sessionFunctions.setIdentityByID(request, id);
		RequestDispatcher view = request.getRequestDispatcher("/views/edit-identity.jsp");
		view.forward(request, response);
	}

}
