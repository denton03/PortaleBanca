package action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.BeanAnagrafica;
import bean.BeanUserLogin;
import service.AnagraficaService;
import service.ContoCorrenteService;

public class NuovoCorrentistaAction extends Action{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AnagraficaService anagraficaService = new AnagraficaService();
		ContoCorrenteService contoService = new ContoCorrenteService();
        BeanAnagrafica bean = (BeanAnagrafica) form;
        if(bean!=null) {
        	
        	int codAnag = anagraficaService.save(bean);
        	if(bean.getCodCC()!=0) {
        		long codCC = contoService.insertContoCorrente("", 0, "", 0 , 1, bean.getCodCC(), codAnag, new BigDecimal("2500"), new BigDecimal("1000"));        		
        	}
        	if(codAnag>0) {
        		request.setAttribute("successMessage", "Anagrafica inserita!");
        		return mapping.findForward("success");			
        	}else {
        		request.setAttribute("successMessage", "Anagrafica NON inserita!");
        		return mapping.findForward("fail");
        	}        	
        }
        return mapping.findForward("fail");
	}
	   public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
	        ActionErrors errors = new ActionErrors();
	        
	        BeanAnagrafica form = (BeanAnagrafica) request.getAttribute("beanAnagrafica");

	        // Validate codcc
	        if (form.getPartitaIva() == null ) {
	            errors.add("username", new org.apache.struts.action.ActionMessage("error.username.invalid"));
	        }
	        
	        return errors;
	    }
}
