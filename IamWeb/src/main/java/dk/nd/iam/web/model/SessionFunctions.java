package dk.nd.iam.web.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.tbr.iamcore.datamodel.Identity;

/*
 * Model used for session management
 */
public class SessionFunctions {
	

	/**
	 * Send a "flash message" which is set in the session
	 * 
	 * @param request
	 * @param message
	 * @param parameter
	 */
	public void sendFlashMessage(HttpServletRequest request,String message, String parameter){
		HttpSession session = request.getSession();
		if(session.getAttribute(parameter) != null){
			session.removeAttribute(parameter);
		}
		session.setAttribute(parameter, message);
	}
	
	
	/**
	 * Retrieve a flash message from the current request's sesison, and set in in the request attribute
	 * The parameter is removed from the session .
	 * 
	 * @param request
	 * @param parameter
	 */
	public void getFlashMessage(HttpServletRequest request, String parameter){
		HttpSession session = request.getSession();
		request.setAttribute(parameter, (String) session.getAttribute(parameter));
		session.removeAttribute(parameter);
	}
	
	
	/**
	 * Set the current user in session
	 * 
	 * @param request
	 * @param user
	 */
	public void setUserSession(HttpServletRequest request, User user){
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
	}
	
	
	/**
	 * Get the current user session
	 * 
	 * @param request
	 * @return
	 */
	public User getUserSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		return (User) session.getAttribute("user");
	}
	
	
	/**
	 * Method validate if @arg user is in session. Else it redirects to given view.
	 * 
	 * @param user
	 * @param viewTo
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void validateUserSession(User user, String viewTo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(user == null){
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			sendFlashMessage(request, "Please log in to enter site", "msg");
			view.forward(request, response);
		}
		else{
			getFlashMessage(request, "msg");
			RequestDispatcher view = request.getRequestDispatcher(viewTo);
			view.forward(request, response);
		}
	}
	
	/**
	 * Destroy/kill the current session
	 * 
	 * @param request
	 */
	public void killUserSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
	}
	
	/**
	 * Get the current searchResult in session (List)
	 * 
	 * @param request
	 * @return List<Identity>
	 */
	@SuppressWarnings("unchecked")
	public List<Identity> getSearchResults(HttpServletRequest request){
		HttpSession session = request.getSession();
		return (List<Identity>) session.getAttribute("searchResult");
	}
	
	/**
	 * Add a list of searchRestults to session
	 * 
	 * @param request
	 * @param list
	 */
	public void addSearchResults(HttpServletRequest request, List<Identity> list){
		HttpSession session = request.getSession();
		if(session.getAttribute("searchResult") != null){	// clear current
			session.removeAttribute("searchResult");
		}
		session.setAttribute("searchResult", list);
	}
	
	/**
	 * Remove an identity from current searchResults. If list is empty after removal, 
	 * the list will be removed from the session
	 * 
	 * @param request
	 * @param identity
	 */
	public void removeIdentityFromSearchResult(HttpServletRequest request, Identity identity){
		List<Identity> list = getSearchResults(request);
		for (Identity identity2 : list) {
			if(identity2.getId() == identity.getId()){
				list.remove(identity2);
				break;
			}
		}
		clearSearchResults(request);	// remove current list
		if(list.size() != 0){
			addSearchResults(request, list);	// update if not null / empty
		}
	}
	
	/**
	 * Remove list from searchResults in session and if set in request
	 * 
	 * @param request
	 */
	public void clearSearchResults(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("searchResult");
		request.removeAttribute("searchResult");
	}
	
	
	/**
	 * Set an identity in session, by given ID, which is found in current searchList
	 * 
	 * @param request
	 * @param id
	 */
	public void setIdentityByID(HttpServletRequest request, int id){
		HttpSession session = request.getSession();
		session.removeAttribute("identity");	// remove existing
		List<Identity> list = getSearchResults(request);
		if(list == null) return;	// return if null
		for (Identity identity : list) {
			if(identity.getId() == id){
				session.setAttribute("identity", identity);
			}
		}
	}
	
	/**
	 * Set an identity in session by Identity
	 * 
	 * @param request
	 * @param identity
	 */
	public void setIdentity(HttpServletRequest request, Identity identity){
		HttpSession session = request.getSession();
		session.removeAttribute("identity");	// remove existing
		session.setAttribute("identity", identity);	// update
	}
	
	/**
	 * Get the current identity from session
	 * 
	 * @param request
	 * @return Identity
	 */
	public Identity getCurrentIdentity(HttpServletRequest request){
		HttpSession session = request.getSession();
		return (Identity) session.getAttribute("identity");
	}
}
