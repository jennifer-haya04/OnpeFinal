package dao;

public class OnpeDAO {

	 db.db db = new db.db("sql5108.site4now.net", "db_a952ba_onpe_admin","onpe1234", "db_a952ba_onpe");
	
	public Object getVotos(int inicio, int fin) {
		db.Sentencia(String.format("usp_getVotos %s,%s",inicio, fin));
		return db.getRegistros();
	}
	
	public Object getVotosDepartamento(String id) {
		db.Sentencia(String.format("usp_getVotosDepartamento '%s' ",id));
		return db.getRegistros();
	}
	
	public Object getVotosProvincia(String id) {
		db.Sentencia(String.format("usp_getVotosProvincia '%s'",id));
		return db.getRegistros();
	}
	
	public Object getGrupoVotacion(String id) {
		db.Sentencia(String.format("usp_getGrupoVotacion '%s'",id));
		return db.getRegistro();
	}
	
	public Object getDepartamentos(int inicio, int fin) {
		db.Sentencia(String.format("usp_getDepartamentos %s,%s",inicio, fin));
		return db.getRegistros();
	}

	public Object getProvincias(String idDepartamento) {
		db.Sentencia(String.format("usp_getProvincias " + idDepartamento));
		return db.getRegistros();
	}
	
	public Object getDistritos(String idProvincia) {
		db.Sentencia(String.format("usp_getDistritos " + idProvincia));
		return db.getRegistros();
	}
	
	public Object getLocalesVotacion(String idDistrito) {
		db.Sentencia(String.format("usp_getLocalesVotacion " + idDistrito));
		return db.getRegistros();
	}
	
	public Object getGruposVotacion(String idLocalVotacion) {
		db.Sentencia(String.format("usp_getGruposVotacion " + idLocalVotacion));
		return db.getColumna();
	}
	
	public Object getResumen() {
		db.Sentencia(String.format("sp_getResumen_Haya"));
		return db.getRegistro();
	}
	
	public Object getViewTotalVotos() {
		db.Sentencia(String.format("sp_ViewTotalVotos"));
		return db.getRegistro();
	}
}
