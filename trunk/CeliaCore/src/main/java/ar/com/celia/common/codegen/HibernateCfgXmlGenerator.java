/**
 * 
 */
package ar.com.celia.common.codegen;

/**
 * @author ldigiuseppe
 * 
 */
public class HibernateCfgXmlGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] clases = { "TAplicacion", "TCategoria", "TDominio", "TError", "TEstadisticas", "TGrupo", "TGrupoPermiso", "TGrupoUsuario", "TImagen", "TItemMenu", "TMenu", "TPermiso", "TPosta", "TPropiedad", "TServicio", "TServicioPosta", "TUsuario" };
		String domain = "ar.gov.turismo.caminoreal.domain";

		for (int i = 0; i < clases.length; i++) {
			System.out.println("<mapping class=\"" + domain + "." + clases[i] + "\" />");
		}
	}
}
