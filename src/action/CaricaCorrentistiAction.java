package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.Correntisti;
import model.Anagrafica;
import service.AnagraficaService;

public class CaricaCorrentistiAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Correntisti correntistiBean = (Correntisti) form;
		AnagraficaService anagService = new AnagraficaService();
		ArrayList<Anagrafica> correntisti = new ArrayList<>();
		
		correntisti= anagService.getAllAnagrafica();
		System.out.println("Correntisti: "+correntisti);
		correntistiBean.setElencoCorrentisti(correntisti);
		return mapping.findForward("ok");
	}
}
