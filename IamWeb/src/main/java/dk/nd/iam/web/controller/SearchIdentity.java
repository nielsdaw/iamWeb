package dk.nd.iam.web.controller;

import java.io.IOException;
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
 * Servlet implementation class SearchIdentity
 */
@WebServlet("/search-identity")
public class SearchIdentity extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdentity() {
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
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		Identity identity = new Identity(fname, lname, email);

		if(sessionFunctions.getSearchResults(request) != null){
			sessionFunctions.getFlashMessage(request, "msgSearch");
			RequestDispatcher view = request.getRequestDispatcher("/views/search-identity.jsp");
			view.forward(request, response);
		}
		else{
			List<Identity> searchResult = dao.search(identity);
			if(searchResult.size() < 1){
				sessionFunctions.clearSearchResults(request);
				sessionFunctions.sendFlashMessage(request, "No Identeties matched your search criterias", "msgSearch");
				RequestDispatcher view = request.getRequestDispatcher("/views/search-identity.jsp");
				view.forward(request, response);
			}
			else{
				sessionFunctions.clearSearchResults(request);
				sessionFunctions.sendFlashMessage(request,  "Your search gave: " + searchResult.size() + (searchResult.size() > 1? 
						" results":" result"), "msgSearch");
				sessionFunctions.addSearchResults(request, searchResult);
				RequestDispatcher view = request.getRequestDispatcher("/views/search-identity.jsp");
				view.forward(request, response);
			}
		}
	}
}
