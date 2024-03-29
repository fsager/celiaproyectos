package ar.com.celia.common.business;

import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelGenerator extends AbstractDocumentGenerator {

	protected HSSFWorkbook xls;

	private HSSFFont fnt_titulo;
	private HSSFCellStyle style;
	private HSSFCellStyle style_detail;
	private HSSFCellStyle style_common;
	private int cantFilas; 

	public static final int VALOR_NUMERICO = HSSFCell.CELL_TYPE_NUMERIC;
	public static final int VALOR_BOOLEAN = HSSFCell.CELL_TYPE_BOOLEAN;
	public static final int VALOR_FORMULA = HSSFCell.CELL_TYPE_FORMULA;
	public static final int VALOR_STRING = HSSFCell.CELL_TYPE_STRING;
	public static final int VALOR_NULL = HSSFCell.CELL_TYPE_BLANK;
	
	public static final short PCT_FORMAT = HSSFDataFormat.getBuiltinFormat("0.00%");
	public static final short DEC_FORMAT = HSSFDataFormat.getBuiltinFormat("#,##0.00");
	
	public ExcelGenerator() {
		clearExcel();
	}

	public void clearExcel ()
	{
		xls = new HSSFWorkbook();
		style_common = xls.createCellStyle();
		style_common.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_common.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style_common.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_common.setBorderRight(HSSFCellStyle.BORDER_THIN);

		fnt_titulo = xls.createFont();
		fnt_titulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fnt_titulo.setColor(HSSFColor.WHITE.index);

		style = xls.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setFillBackgroundColor(HSSFColor.DARK_BLUE.index);
		style.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment((short) HSSFCellStyle.ALIGN_CENTER);
		style.setFont(fnt_titulo);

		style_detail = xls.createCellStyle();
		style_detail.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_detail.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style_detail.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_detail.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_detail.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		style_detail.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		style_detail.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//		style_detail.setFont(fnt_titulo);
	}

	public HSSFSheet addSheet (String name) {
		HSSFSheet sheet = xls.createSheet(name);
		
		return sheet;
	}
	
	public HSSFWorkbook getHSSFWorkbook(){
		return xls;
	}

	public void addCellCommon (HSSFSheet sheet, Object value, int row, int column, int valueType) {
		HSSFRow rowXls = null;
		if (sheet.getRow(row)==null)
			rowXls = sheet.createRow(row);
		else 
			rowXls = sheet.getRow(row);
		addCell(column, rowXls, null, value, valueType);
		
//		addCell(i, rowXls, style, "" + header.get(i));
	}
	
	public void addCellCommonNoAutoSizeColumn (HSSFSheet sheet, Object value, int row, int column, int valueType) {
		HSSFRow rowXls = null;
		if (sheet.getRow(row)==null)
			rowXls = sheet.createRow(row);
		else 
			rowXls = sheet.getRow(row);
		addCell(column, rowXls, null, value, valueType);
	}
	
	public void addCellYellow (HSSFSheet sheet, Object value, int row, int column, int valueType, boolean isBold, boolean border, int size,
			short alignment) {
		HSSFRow rowXls = null;
		HSSFCellStyle newStyle = xls.createCellStyle();
		if(border) {
			newStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		}
		newStyle.setFillBackgroundColor(HSSFColor.LIGHT_YELLOW.index);
		newStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		newStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
		newStyle.setAlignment(alignment);
		
		if (sheet.getRow(row)==null)
			rowXls = sheet.createRow(row);
		else 
			rowXls = sheet.getRow(row);
		
		HSSFFont newFont = xls.createFont();
		if (isBold)
			newFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		newFont.setColor(HSSFColor.BLACK.index);
		newFont.setFontHeightInPoints((short) size);
		newStyle.setFont(newFont);
		
		addCell(column, rowXls, newStyle, value, valueType);
		
	}
	
	public void addCellYellowNoAutoSizeColumn (HSSFSheet sheet, Object value, int row, int column, int valueType, boolean isBold, boolean border, int size,
			short alignment) {
		HSSFRow rowXls = null;
		HSSFCellStyle newStyle = xls.createCellStyle();
		if(border) {
			newStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		}
		newStyle.setFillBackgroundColor(HSSFColor.LIGHT_YELLOW.index);
		newStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		newStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
		newStyle.setAlignment(alignment);
		
		if (sheet.getRow(row)==null)
			rowXls = sheet.createRow(row);
		else 
			rowXls = sheet.getRow(row);
		
		HSSFFont newFont = xls.createFont();
		if (isBold)
			newFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		newFont.setColor(HSSFColor.BLACK.index);
		newFont.setFontHeightInPoints((short) size);
		newStyle.setFont(newFont);
		
		addCell(column, rowXls, newStyle, value, valueType);		
	}
	
	public HSSFCellStyle getCustomStyle (short backgroundColor, short foregroundColor, boolean isBold) {
		HSSFCellStyle newStyle = xls.createCellStyle();
		newStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		newStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		newStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		newStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		newStyle.setFillBackgroundColor(backgroundColor);
		newStyle.setFillForegroundColor(backgroundColor);
		newStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFFont newFont = xls.createFont();
		if (isBold)
			newFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		newFont.setColor(foregroundColor);

		newStyle.setFont(newFont);//fnt_titulo);
		
		return newStyle;
	}
	
	public HSSFCellStyle getCustomStyle (short backgroundColor, short foregroundColor, boolean isBold, boolean border,
			short size, short alignment) {
		HSSFCellStyle newStyle = xls.createCellStyle();
		if(border) {
			newStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			newStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		}
		newStyle.setFillBackgroundColor(backgroundColor);
		newStyle.setFillForegroundColor(backgroundColor);
		newStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		newStyle.setWrapText(true);
		newStyle.setAlignment(alignment);

		HSSFFont newFont = xls.createFont();
		if (isBold)
			newFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		newFont.setColor(foregroundColor);
		newFont.setFontHeightInPoints(size);
		newStyle.setFont(newFont);
		
		return newStyle;
	}
	
	public HSSFCellStyle getCustomStyleDecimal (short backgroundColor, short foregroundColor, boolean isBold, boolean border,
			short size, short alignment) {
		HSSFCellStyle newStyle = getCustomStyle(backgroundColor, foregroundColor, isBold, border, size, alignment);
		newStyle.setDataFormat(DEC_FORMAT);
		return newStyle;
	}
	
	public HSSFCellStyle getCustomStylePercent (short backgroundColor, short foregroundColor, boolean isBold, boolean border,
			short size, short alignment) {
		HSSFCellStyle newStyle = getCustomStyle(backgroundColor, foregroundColor, isBold, border, size, alignment);
		newStyle.setDataFormat(PCT_FORMAT);
		return newStyle;
	}
	
	public void addCellWithStyle (HSSFSheet sheet, HSSFCellStyle p_style, Object value, int row, int column, int valueType) {
		HSSFRow rowXls = null;
		if (sheet.getRow(row)==null)
			rowXls = sheet.createRow(row);
		else 
			rowXls = sheet.getRow(row);
		addCell(column, rowXls, p_style, value, valueType);
		
	}
	
	public void addCellTitle (HSSFSheet sheet, Object value, int row, int column) {
		HSSFRow rowXls = null;
		if (sheet.getRow(row)==null)
			rowXls = sheet.createRow(row);
		else 
			rowXls = sheet.getRow(row);
		addCell(column, rowXls, style, value, ExcelGenerator.VALOR_STRING);
		
	}
	
	public void addCellDetail (HSSFSheet sheet, Object value, int row, int column) {
		addCellDetail (sheet, value, row, column, ExcelGenerator.VALOR_STRING);
	}
	
	public void addCellDetail (HSSFSheet sheet, Object value, int row, int column, int type) {
		HSSFRow rowXls = null;
		if (sheet.getRow(row)==null)
			rowXls = sheet.createRow(row);
		else 
			rowXls = sheet.getRow(row);
		addCell(column, rowXls, style_detail, value, type);
		
	}
	
	public void addResultSet (HSSFSheet sheet, java.sql.ResultSet rs, int startRow, int startColumn, SimpleDateFormat sdf) throws Exception {
		
		addResultSet (sheet, rs, startRow, startColumn, sdf, null);
	}
	
	public void addResultSet (HSSFSheet sheet, java.sql.ResultSet rs, int startRow, int startColumn, SimpleDateFormat sdf, java.util.List totalColumns) throws Exception {
		int fila = 0;
		cantFilas = 0;
		double totales[]=null;
		if (totalColumns!=null && totalColumns.size()>0)
		{
			totales = new double [totalColumns.size()];
			
			for (int i=0;i<totales.length;i++)
				totales[i] = 0;
		}
		
		while (rs.next()) {
			cantFilas++;
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			String row = null;

			for (int i=1;i<=rsmd.getColumnCount();i++)
			{
				Object obj = null;
				int type=ExcelGenerator.VALOR_STRING;
				
				if (rs.getString(i)==null)
				{
					obj = "";
					type=ExcelGenerator.VALOR_NULL;
				}
				else
				{
					if (rsmd.getColumnType(i) == java.sql.Types.DATE)
						obj = sdf.format(rs.getDate(i));

					if (java.lang.Number.class.isAssignableFrom(Class.forName(rsmd.getColumnClassName(i))))
					{
						obj = rs.getBigDecimal(i).doubleValue(); 
						type=ExcelGenerator.VALOR_NUMERICO;
									
						if (totalColumns!=null && totalColumns.contains(""+i))
						{
							totales[totalColumns.indexOf(""+i)] = totales[totalColumns.indexOf(""+i)] + rs.getBigDecimal(i).doubleValue();
						}
					}
					
					if (obj == null)
						obj = rs.getObject(i);
				}
				
				addCellCommon (sheet, obj, startRow+fila, startColumn+i-1, type);
				
			}
			fila = fila+1;
		}

		Object obj = null;
		int type=ExcelGenerator.VALOR_NUMERICO;
		for (int i=0;totales!=null && i<totales.length;i++)
		{
			int col = Integer.parseInt(""+totalColumns.get(i));
			obj = totales[i];
			this.addCellDetail(sheet, obj, startRow+fila, startColumn+col-1, type);
			totales[i] = 0;
		}
	}

	public void exportXls(OutputStream os) throws Exception {
		xls.write(os);
	}

	protected HSSFCell addCell(int column, HSSFRow row,
		HSSFCellStyle style_title, Object obj, int typeValue) {
		HSSFCell cell = row.createCell(column); //
		
		String texto = ""+obj;
		if (obj == null)
			texto = "";

		if (typeValue == ExcelGenerator.VALOR_STRING || typeValue == ExcelGenerator.VALOR_NULL)
		{
			//HSSFRichTextString rich = new HSSFRichTextString(texto); PRUEBA 28/07/2010
			cell.setCellValue(texto); //rich PRUEBA 28/07/2010
		}
		 
		if (typeValue == ExcelGenerator.VALOR_NUMERICO)
			cell.setCellValue(Double.parseDouble(""+obj));
		
		if (typeValue == ExcelGenerator.VALOR_FORMULA)
			cell.setCellFormula(obj.toString());
		
		cell.setCellType(typeValue);

		if (style_title != null)
			cell.setCellStyle(style_title);
		else
			cell.setCellStyle(style_common);

		return cell;
	}

	public int getCantFilas() {
		return cantFilas;
	}

	public void setCantFilas(int cantFilas) {
		this.cantFilas = cantFilas;
	}
}