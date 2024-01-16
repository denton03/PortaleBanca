package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.BeanUserLogin;
import service.UtenteService;

public class LoginAction extends Action {
	private UtenteService service;
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	request.setAttribute("errorMessage", "");
        // Cast the form bean to BeanUserLogin
    	service = new UtenteService();
        BeanUserLogin loginForm = (BeanUserLogin) form;

        // Retrieve username and password from the form
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        int isAuthenticated =  service.getUtenteByNomeUserPassword(username, password);        	

        System.out.println(isAuthenticated);
        if (isAuthenticated==0) {
            // If authentication is successful, redirect to success page
        	System.out.println("Success");
        	request.setAttribute("successMessage", "Sei Loggato come: "+username+"!");
        	System.out.println(request.getAttribute("successMessage"));
        	request.getSession().setAttribute("user", username);
            return mapping.findForward("success");
        } else if(isAuthenticated==1){
            // If authentication fails, forward back to the login page with an error message
            request.setAttribute("errorMessage", "Invalid username ");
            return mapping.findForward("failure");
        } else if(isAuthenticated==2){
            // If authentication fails, forward back to the login page with an error message
            request.setAttribute("errorMessage", "Invalid password ");
            return mapping.findForward("failure");
        }else {
        	request.setAttribute("errorMessage", "Invalid");
            return mapping.findForward("failure");	
        }
    }
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        BeanUserLogin loginForm = (BeanUserLogin) request.getAttribute("beanUserLogin");

        // Validate username
        if (loginForm.getUsername() == null || !loginForm.getUsername().matches("^[a-zA-Z0-9]+$")) {
            errors.add("username", new org.apache.struts.action.ActionMessage("error.username.invalid"));
        }
        
        // Validate password
        if (loginForm.getPassword() == null || 
            !loginForm.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            errors.add("password", new org.apache.struts.action.ActionMessage("error.password.invalid"));
        }
        
        return errors;
    }

}
