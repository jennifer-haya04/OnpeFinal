package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class svlActas
 */
public class svlActas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public svlActas() {
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
    	
    	String id = request.getParameter("id");
    	String nroMesa = request.getParameter("nroMesa");
    	String tipo = request.getParameter("tipo");
    	String idDepartamento = request.getParameter("cboDepartamento");
    	String idProvincia = request.getParameter("cboProvincias");
    	String idDistrito = request.getParameter("cboDistritos");
    	String idLocalVotacion = request.getParameter("cboLocalVotacion");;
    	Object grupos = null;
    	Object data = null;
    	
    	Object DPD = session.getAttribute("DPD");
    	
    	if(idDepartamento == null) idDepartamento = "-1";
    	if(idProvincia == null) idProvincia = "-1";
    	if(idDistrito == null) idDistrito = "-1";
    	if(idLocalVotacion == null) idLocalVotacion = "-1";
    	
    	if(idDepartamento.equals("-1")) {
    		idProvincia = "-1";
    		session.setAttribute("provincias", null);
    	}
    	if(idProvincia.equals("-1")) {
    		idDistrito = "-1";
    		session.setAttribute("distritos", null);
    	}
    	if(idDistrito.equals("-1")) {
    		idLocalVotacion = "-1";
    		session.setAttribute("locales", null);
    	}
    	
    	if(idLocalVotacion.equals("-1")) {
    		session.setAttribute("grupos", null);
    	}
    	
    	
    	if (tipo == null && session.getAttribute("departamentos") == null) {
    		session.setAttribute("departamentos", daoOnpe.getDepartamentos(1,25));
    	}
    	
    	if(!idDepartamento.equals( session.getAttribute("idDepartamento"))) {
    		idProvincia = "-1";
    		session.setAttribute("idDepartamento", idDepartamento);
    		session.setAttribute("provincias", daoOnpe.getProvincias(idDepartamento));
    	}
    	
    	if(!idProvincia.equals(session.getAttribute("idProvincia")))
    		idDistrito = "-1";
    		session.setAttribute("idProvincia", idProvincia);
    		session.setAttribute("distritos", daoOnpe.getDistritos(idProvincia));
    	
    	if(!idDistrito.equals(session.getAttribute("idDistrito")))
    		idLocalVotacion = "-1";
    		session.setAttribute("idDistrito", idDistrito);
    		session.setAttribute("locales", daoOnpe.getLocalesVotacion(idDistrito));
    	
    	if(!idLocalVotacion.equals(session.getAttribute("idLocalVotacion"))) {
    		session.setAttribute("idLocalVotacion", idLocalVotacion);
    		session.setAttribute("grupos", daoOnpe.getGruposVotacion(idLocalVotacion));
    	}
    	
    	if(nroMesa != null) {
    		data = daoOnpe.getGrupoVotacion(nroMesa);
    	}
    	
    	String sDPD = idDepartamento + "," + idProvincia + "," + idDistrito + "," + idLocalVotacion;
    	
    	session.setAttribute("dpd", sDPD);
    	session.setAttribute("data", data);
    	session.setAttribute("tipo", tipo);
    	
    	response.sendRedirect("actas.jsp");
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
