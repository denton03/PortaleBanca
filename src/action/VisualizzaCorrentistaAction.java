package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import bean.Correntista;
import model.Anagrafica;
import service.AnagraficaService;

public class VisualizzaCorrentistaAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        System.out.println("wowowo");
        try {
            String codAnagString = request.getParameter("codAnag");
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String codCCString = request.getParameter("codCC");
            Anagrafica anag = new Anagrafica();

            if (codAnagString != null) {
            	System.out.println("codAnaggggggg");
                int codAnag = Integer.parseInt(codAnagString);
                AnagraficaService anagService = new AnagraficaService();
                anag = anagService.getAnagraficaByCodAnag(codAnag);
            } else if (nome != null && cognome != null) {
            	System.out.println(nome+" "+cognome);
                // Handle the request based on nome and cognome parameters
                AnagraficaService anagService = new AnagraficaService();
                System.out.println("VisualizzaCorrentistaAction: "+nome+" "+cognome);
                anag = anagService.getAnagraficaByCognomeNome(cognome, nome);
            } else if (codCCString != null) {
            	System.out.println("codddcccCCC");
            	System.out.println("codCC : "+codCCString);
            	long codCC = Long.parseLong(codCCString);
            	System.out.println("codCC parsed : "+codCC);
            	AnagraficaService anagService = new AnagraficaService();
                anag = anagService.getAnagraficaByCodCC(codCC);
                System.out.println(anag.getCodAnag());
                System.out.println("Anagrafica : "+anag.getNome());
            } else {
                // Handle other cases or provide an error response
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
                return null;
            }
            // Convert Anagrafica object to JSON
            Gson gson = new Gson();
            String jsonData = gson.toJson(anag);
            System.out.println("json data: "+jsonData);
            // Set response content type to JSON
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            // Send JSON response to the client
            try (PrintWriter out = response.getWriter()) {
                out.print(jsonData);
            }

        } catch (NumberFormatException e) {
            // Handle the case where codAnag parameter is not a valid integer
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid codAnag parameter");
        } catch (Exception e) {
            // Handle other exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }

        return null;
    }
}