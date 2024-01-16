package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends Action {
  private HttpSession session;

  public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
	  HttpSession session = request.getSession(false);
	  System.out.println(session.getAttribute("user"));
	  
	  if (session != null) {
		  session.invalidate(); 
	  }
      return mapping.findForward("success");
   }

   public void setSession(HttpSession session) {
       this.session = session;
   }
}
