package ar.com.celia.seguimiento_alumnos.business;

import java.util.HashMap;

import ar.com.celia.seguimiento_alumnos.domain.VwIndicadoresAlumnos;

public class Utils {
	
	public static String getImageIndicador(HashMap<Long,Long> rangoIndicadores,VwIndicadoresAlumnos indicador)
	{
		Long rangoRojo=rangoIndicadores.get(indicador.getIdIndicador());
		return getImageIndicador(rangoRojo,indicador);
	}
	
	public static String getImageIndicador(Long valorRojo,VwIndicadoresAlumnos indicador)
	{
		Long rangoRojo=valorRojo;
		if(valorRojo==null)
			rangoRojo=new Long(2);
		        	
		if(indicador.getValorIndicador()==1)
			return "/img/green.jpg";
		else if(indicador.getValorIndicador()>=rangoRojo)
			return "/img/red.jpg";
		else if (indicador.getValorIndicador()==0)
			return "/img/nodata.jpg";
		else
			return "/img/yellow.jpg";
	}
}