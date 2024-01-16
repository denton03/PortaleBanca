package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;

import model.Anagrafica;
import service.AnagraficaService;
import service.ContoCorrenteService;

public class GetCodCCAction extends DispatchAction{
	
	public ActionForward getAllCodCC(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		ContoCorrenteService service = new ContoCorrenteService();
        try {
            // Call the service to get the list of codAnag
            ArrayList<Long> codCCList = service.getAllCodCC();

         // Convert the list to a JSON object using Gson
            Gson gson = new Gson();
            String json = gson.toJson(codCCList);

            // Set the JSON object in the response
            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            // You might want to set an appropriate error response here
        }

        return null;
    }
	public ActionForward getLastCodCC(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		ContoCorrenteService service = new ContoCorrenteService();
        try {
            // Call the service to get the list of codAnag
            long codCC = service.getLastIdPlus1();

         // Convert the list to a JSON object using Gson
            Gson gson = new Gson();
            String json = gson.toJson(codCC);
            

            // Set the JSON object in the response
            response.setContentType("application/json");
            response.getWriter().write(json);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            // You might want to set an appropriate error response here
        }

        return null;
    }
}
