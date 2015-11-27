package dk.nd.iam.web.genericservlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import dk.nd.iam.web.model.Authenticator;
import dk.nd.iam.web.model.SessionFunctions;
import dk.nd.iam.web.model.UserModelInterface;
import fr.tbr.iamcore.services.dao.IdentityDAOInterface;

/**
 * Servlet implementation class GenericSpringServlet
 */
@WebServlet("/GenericSpringServlet")
public class GenericSpringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	protected IdentityDAOInterface dao;
	
	@Autowired
	protected UserModelInterface userModel;
	
	@Autowired
	protected Authenticator authenticator;
	
	@Autowired
	protected SessionFunctions sessionFunctions;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
	}
}
