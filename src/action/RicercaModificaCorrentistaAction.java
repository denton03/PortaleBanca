package action;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.actions.DispatchAction;

import bean.BeanAnagrafica;
import model.Anagrafica;
import service.AnagraficaService;

public class RicercaModificaCorrentistaAction extends DispatchAction {
	private AnagraficaService anagraficaService; 
	
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    System.out.println("Executing RicercaModificaCorrentistaAction");
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		System.out.println("redirecting to tiles.ricercaCorrentista");
		return mapping.findForward("view");
	}
	
	
	public ActionForward modifica(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		System.out.println("sono in modifica");
        HttpSession session = request.getSession(false);
         
        BeanAnagrafica beanAnagrafica = (BeanAnagrafica) form;
        System.out.println("bean codFiscale: "+beanAnagrafica.getCodFiscale());
        Anagrafica anagraficaModel = new Anagrafica();
        anagraficaService=new AnagraficaService();
        System.out.println(beanAnagrafica.getCodAnag());
        BeanUtils.copyProperties(anagraficaModel, beanAnagrafica);
        try {
            anagraficaService.updateAnagrafica(anagraficaModel);
            
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("Record modified successfully."));
            saveMessages(request, messages);

        } catch (Exception e) {
            e.printStackTrace();
        	request.setAttribute("errorMessage", "Cancellazione anagrafica FALLITA");
        }

        request.setAttribute("successMessage", "Anagrafica MODIFICATA correttamente!");
        System.out.println(request.getAttribute("successMessage"));


        return mapping.findForward("view");
    }
	public ActionForward elimina(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        
        BeanAnagrafica beanAnagrafica = (BeanAnagrafica) form;
        Anagrafica anagraficaModel = new Anagrafica();
        anagraficaService=new AnagraficaService();
        
        BeanUtils.copyProperties(anagraficaModel, beanAnagrafica);
        try {
            System.out.println(anagraficaService.deleteAnagraficaByCodAnag(anagraficaModel.getCodAnag()));
            
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("Record deleted successfully."));
            saveMessages(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Cancellazione anagrafica FALLITA.");
        }

        request.setAttribute("successMessage", "Anagrafica ELIMINATA correttamente!");
        System.out.println("ododo "+request.getAttribute("successMessage"));
        return mapping.findForward("view");
    }
}
