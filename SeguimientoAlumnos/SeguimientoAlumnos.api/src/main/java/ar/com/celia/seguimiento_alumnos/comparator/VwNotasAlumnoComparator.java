package ar.com.celia.seguimiento_alumnos.comparator;
import java.util.Comparator;

import ar.com.celia.seguimiento_alumnos.domain.VwNotasAlumno;


public class VwNotasAlumnoComparator implements Comparator<VwNotasAlumno> {
	
	@Override
	public int compare(VwNotasAlumno o1, VwNotasAlumno o2) {
		int res = o1.getLastname().compareTo(o2.getLastname());
		if(res == 0)
			res = o1.getFirstname().compareTo(o2.getFirstname());
		if(res == 0)
			res = o1.getUserId().compareTo(o2.getUserId());
		return res;
	}
	
}
