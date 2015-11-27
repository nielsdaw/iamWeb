package dk.nd.iam.web.model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * Model used for sending messages in the session
 */
public class SessionFunctions {
	
	public void sendFlashMessage(HttpServletRequest request,String message, String parameter){
		HttpSession session = request.getSession();
		if(session.getAttribute(message) != null){
			return;
		}
		session.setAttribute(parameter, message);
	}
	
	public void getFlashMessage(HttpServletRequest request, String parameter){
		HttpSession session = request.getSession();
		if(session != null){
			return;
		}
		request.setAttribute(parameter, (String) session.getAttribute(parameter));
		request.getSession().removeAttribute(parameter);
	}
	
	public void setUserSession(HttpServletRequest request, User user){
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
	}
	
	public User getUserSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		return (User) session.getAttribute("user");
	}
	
	
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
	
	
	
}
