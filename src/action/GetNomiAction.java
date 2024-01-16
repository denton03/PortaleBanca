package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import repository.AnagraficaDAO;
import service.AnagraficaService;

public class GetNomiAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
		String cognome = request.getParameter("cognome");
		System.out.println(cognome);
	      AnagraficaService service = new AnagraficaService();
	      
	      ArrayList<String> nomi = service.getNomi(cognome);
	      System.out.println(nomi);
	      String jsonResponse = new Gson().toJson(nomi);
	      System.out.println(jsonResponse);
	  	// Set the response content type to JSON
	  	response.setContentType("application/json");
	  	response.setCharacterEncoding("UTF-8");
	  	
	  	// Send the JSON response back to the client
	  	try (PrintWriter out = response.getWriter()) {
	  		out.print(jsonResponse);
	  	} catch (IOException e) {
	  		e.printStackTrace();
	  	}
	  	
	  	return null;
	  }
}
