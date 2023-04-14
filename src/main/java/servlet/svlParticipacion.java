package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.apache.el.parser.AstIdentifier;

/**
 * Servlet implementation class svlParticipacion
 */
public class svlParticipacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public svlParticipacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
    	dao.OnpeDAO daoOnpe = new dao.OnpeDAO();
    	
    	String id  = request.getParameter("id");
    	String sDPD = null, sAmbito = null;
    	Object data = null, totales = null;
    	
    	if(id != null) {
    		String aId[] = id.split(",");
    		boolean bNacional = aId[0].equals("Nacional");
    		
    		if(aId.length == 1) sDPD = bNacional ? "DEPARTAMENTO" : "CONTINENTE";
    		else if(aId.length == 2) sDPD = bNacional ? "PROVINCIA" : "PAIS";
    		else if(aId.length == 3) sDPD = bNacional ? "DISTRITO" : "CIUDAD";
    		
    		if (aId.length == 1) data = daoOnpe.getVotos(bNacional  ? 1 : 26, bNacional  ? 25: 30);
    		else if (aId.length == 2) data = daoOnpe.getVotosDepartamento(aId[1]);
    		else if (aId.length == 3) data = daoOnpe.getVotosProvincia(aId[2]);

    		sAmbito = "Ambito: " + aId[0];
    		if(aId.length > 1) sAmbito += "<br/>" + ( bNacional ? "Departamento: " : "Continente: ") + aId[1];
    		if(aId.length > 2) sAmbito += "<br/>" + ( bNacional ? "Provincia: " : "Pais: ") + aId[2];
    		if(aId.length > 3) sAmbito += "<br/>" + ( bNacional ? "Distrito: " : "Ciudad: ") + aId[3];
    		
    	}
    	
    	session.setAttribute("id", id);
    	session.setAttribute("DPD", sDPD );
    	session.setAttribute("data", data);
    	session.setAttribute("ambito", sAmbito);
    	session.setAttribute("totales", totales);
    	
    	response.sendRedirect("participacion.jsp");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
