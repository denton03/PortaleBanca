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

import service.ComuneService;

public class GetComuniAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ComuneService service = new ComuneService();
		ArrayList<String> descrComuni = service.getDescrizioneComuni();
		
		// Convert the list of surnames to JSON or any other desired format
		String jsonResponse = new Gson().toJson(descrComuni);
		
		// Set the response content type to JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		// Send the JSON response back to the client
		try (PrintWriter out = response.getWriter()) {
			out.print(jsonResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null; // Tell Struts that the response has been handled, no further processing is required.
	}
}
