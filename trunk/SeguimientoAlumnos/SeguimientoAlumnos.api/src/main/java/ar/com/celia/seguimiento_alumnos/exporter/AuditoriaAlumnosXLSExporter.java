package ar.com.celia.seguimiento_alumnos.exporter;

import static ar.com.celia.common.business.ExcelGenerator.VALOR_NUMERICO;
import static ar.com.celia.common.business.ExcelGenerator.VALOR_STRING;
import static org.apache.poi.ss.usermodel.CellStyle.ALIGN_CENTER;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.apache.poi.hssf.util.HSSFColor.WHITE;
import org.apache.poi.ss.util.CellRangeAddress;

import ar.com.celia.common.business.ExcelGenerator;
import ar.com.celia.seguimiento_alumnos.domain.VwEtapa;
import ar.com.celia.seguimiento_alumnos.domain.VwEvaluacion;
import ar.com.celia.seguimiento_alumnos.domain.VwExamen;
import ar.com.celia.seguimiento_alumnos.domain.VwMateria;
import ar.com.celia.seguimiento_alumnos.domain.VwNotasAlumno;
import ar.com.celia.seguimiento_alumnos.domain.VwPeriodo;
import ar.com.celia.seguimiento_alumnos.domain.VwTrabajoPractico;

public class AuditoriaAlumnosXLSExporter {

	public File export(String fileName, VwPeriodo vwPeriodo, VwEtapa vwEtapa, List<VwMateria> vwMaterias, Map<VwNotasAlumno,List<VwNotasAlumno>> mapNotasAlumno) {
		
		ExcelGenerator excel = new ExcelGenerator();
		HSSFCellStyle cellStyleWhite = excel.getCustomStyle(WHITE.index, BLACK.index, false, true, (short)12, ALIGN_CENTER);
		HSSFCellStyle cellStyleWhiteHeaders = excel.getCustomStyle(WHITE.index, BLACK.index, true, true, (short)14, ALIGN_CENTER);
		HSSFCellStyle cellStyleWhiteBold = excel.getCustomStyle(WHITE.index, BLACK.index, true, true, (short)12, ALIGN_CENTER);
		HSSFCellStyle decimalStyle= excel.getCustomStyleDecimal(WHITE.index, BLACK.index, false, true, (short)10, ALIGN_CENTER);
	    	    
	    for (VwMateria vwMateria : vwMaterias) {
	    	HSSFSheet sheet = excel.addSheet(vwMateria.getMateria());
	    	int row = 0;
		    int col = 2;
		    
		    excel.addCellWithStyle(sheet, cellStyleWhiteHeaders, "Datos del alumno", row, 0, VALOR_STRING);
			sheet.addMergedRegion(new CellRangeAddress(row, row, 0, 1));
			
		    int tpColSpan =  vwMateria.getTrabajosPracticos().isEmpty()? 0:vwMateria.getTrabajosPracticos().size()-1;
		    excel.addCellWithStyle(sheet, cellStyleWhiteHeaders, "TRABAJOS PR�CTICOS", row, col, VALOR_STRING);
		    sheet.addMergedRegion(new CellRangeAddress(row, row, col, col + tpColSpan));
		    
		    col = col + tpColSpan+1;
		    int exColSpan = vwMateria.getExamenes().isEmpty()? 0:vwMateria.getExamenes().size() - 1;
		    excel.addCellWithStyle(sheet, cellStyleWhiteHeaders, "EX�MENES", row, col, VALOR_STRING);
		    sheet.addMergedRegion(new CellRangeAddress(row, row, col, col + exColSpan));
		    excel.addCellWithStyle(sheet, cellStyleWhiteHeaders, "", row, col + exColSpan, VALOR_STRING);
		    
			excel.addCellWithStyle(sheet, cellStyleWhiteBold, "Apellido", 1, 0, VALOR_STRING);
			excel.addCellWithStyle(sheet, cellStyleWhiteBold, "Nombre", 1, 1, VALOR_STRING);

			col = 1;
			
			Collection<VwEvaluacion> evaluaciones = new LinkedList<VwEvaluacion>();
			
			for (VwTrabajoPractico vwTrabajoPractico : vwMateria.getTrabajosPracticos()){
				evaluaciones.add(vwTrabajoPractico);
				excel.addCellWithStyle(sheet, cellStyleWhiteBold, vwTrabajoPractico.getTituloCorto(), row = 1, ++col, VALOR_STRING);
			}
			
			for (VwExamen vwExamen : vwMateria.getExamenes()){
				evaluaciones.add(vwExamen);
				excel.addCellWithStyle(sheet, cellStyleWhiteBold, vwExamen.getTituloCorto(), row, ++col, VALOR_STRING);
			}
			
			for (VwNotasAlumno alumno : mapNotasAlumno.keySet()) {
				excel.addCellWithStyle(sheet, cellStyleWhite, alumno.getLastname(), ++row, col = 0, VALOR_STRING);
				excel.addCellWithStyle(sheet, cellStyleWhite, alumno.getFirstname(), row, ++col, VALOR_STRING);

				evaluaciones:
				for (VwEvaluacion vwEvaluacion : evaluaciones){
					notas:
					for (VwNotasAlumno vwNotasAlumno : mapNotasAlumno.get(alumno)) {
						if(!vwNotasAlumno.getMatId().equals(vwMateria.getMatId())){
							continue notas;
						}else if(vwNotasAlumno.getTipoEvaluacion().equals(vwEvaluacion.getTipoEvaluacion()) 
							   && vwNotasAlumno.getEvalId().equals(vwEvaluacion.getId())){
							excel.addCellWithStyle(sheet, decimalStyle, vwNotasAlumno.getNotaDivididoDiez(), row, ++col, VALOR_NUMERICO);
							continue evaluaciones;
						}
					}
					excel.addCellWithStyle(sheet, cellStyleWhite, "-", row, ++col, VALOR_STRING);
				}
			}
			for (int i = 0; i < col; sheet.autoSizeColumn(i++));
	    }
	    
	    File res = new File(System.getProperty("java.io.tmpdir")+File.separator+vwPeriodo.getPeriodo()+" - "+vwEtapa.getEtapa()+fileName);
	    FileOutputStream fos = null;
	    try{
	    	fos = new FileOutputStream(res);
	    	excel.exportXls(fos);
	    } catch (Exception e) {
			throw new RuntimeException("Error al exportar notas.");
		}finally{
	    	try {
	    		if(fos != null)
				fos.close();
			} catch (IOException e) {
				throw new RuntimeException("Error al exportar notas.");
			}
		}
	    return res;
	}

}
