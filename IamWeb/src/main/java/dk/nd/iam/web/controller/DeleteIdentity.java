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
import fr.tbr.iamcore.datamodel.Identity;

/**
 * Servlet implementation class DeleteIdentity
 */
@WebServlet("/delete")
public class DeleteIdentity extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteIdentity() {
        super();
        // TODO Auto-generated constructor stub
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
		Identity identity = new Identity();
		identity.setId(id);
		dao.delete(identity);
		sessionFunctions.removeIdentityFromSearchResult(request, identity);
		sessionFunctions.sendFlashMessage(request, "Identity deleted", "msgSearch");
		RequestDispatcher view = request.getRequestDispatcher("/search-identity");
		view.forward(request, response);
	}

}
