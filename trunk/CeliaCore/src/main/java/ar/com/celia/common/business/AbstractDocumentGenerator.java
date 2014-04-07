package ar.com.celia.common.business;

import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public abstract class AbstractDocumentGenerator {

	public abstract HSSFSheet addSheet (String name);
	public abstract void addCellCommon (HSSFSheet sheet, Object value, int row, int column, int valueType);
	public abstract void addCellYellow (HSSFSheet sheet, Object value, int row, int column, int valueType, boolean isBold, boolean border, int size, short alignment);
	public abstract void addCellWithStyle (HSSFSheet sheet, HSSFCellStyle p_style, Object value, int row, int column, int valueType);
	public abstract void addCellTitle (HSSFSheet sheet, Object value, int row, int column);
	public abstract void addCellDetail (HSSFSheet sheet, Object value, int row, int column);
	public abstract void addCellDetail (HSSFSheet sheet, Object value, int row, int column, int type);
	public abstract void addResultSet (HSSFSheet sheet, java.sql.ResultSet rs, int startRow, int startColumn, SimpleDateFormat sdf) throws Exception;
	public abstract void addResultSet (HSSFSheet sheet, java.sql.ResultSet rs, int startRow, int startColumn, SimpleDateFormat sdf, java.util.List totalColumns) throws Exception;
	public abstract void exportXls(OutputStream os) throws Exception;
	
	public static final int EXCEL_TYPE=0;
	public static final int HTML_TYPE=1;
	public static final int PDF_TYPE=2;
	public static AbstractDocumentGenerator newInstanceDocumentGenerator (int type)
	{
		switch (type) {
			case EXCEL_TYPE: return new ExcelGenerator();
		
		};
		return null;
	}
}