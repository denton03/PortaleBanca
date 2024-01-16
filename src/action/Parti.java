package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

//import bean.BeanMes;

public class Parti extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		//BeanMes hwForm = (BeanMes) form;
		
		//hwForm.setMessaggio("Benvenuto nel mio Applicativo");
		System.out.println("sono in action parti");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	
	    HttpSession session = httpRequest.getSession(false);
	    if (session == null || session.getAttribute("user") == null) {
	    	System.out.println("sus");
	        // User is not logged in, redirect to the login page
	    	return mapping.findForward("success");    	
	    } else {
	    	System.out.println("logggato");
	    	return mapping.findForward("logged");
	    }
	}
}