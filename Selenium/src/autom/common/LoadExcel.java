package autom.common;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadExcel {
	protected static XSSFWorkbook wBook;
	protected static XSSFSheet oSheet;
	@SuppressWarnings("rawtypes")
	public static HashMap loadExcelLines(File fileName) {
	         // Used the LinkedHashMap and LikedList to maintain the order
	         HashMap<String, LinkedHashMap<Integer, List>> outerMap = new LinkedHashMap<String, LinkedHashMap<Integer, List>>();
	         LinkedHashMap<Integer, List> hashMap = new LinkedHashMap<Integer, List>();
	         String sheetName = null;
	         // Create an ArrayList to store the data read from excel sheet.
	         // List sheetData = new ArrayList();
	         FileInputStream fis = null;
	         try {
	             fis = new FileInputStream(fileName);
	             // Create an excel workbook from the file system
	             XSSFWorkbook workBook = new XSSFWorkbook(fis);
	             wBook=workBook;
	             // Get the first sheet on the workbook.
	             for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
	                 XSSFSheet sheet = workBook.getSheetAt(i);
	                 oSheet=sheet;
	                 // XSSFSheet sheet = workBook.getSheetAt(0);
	                 sheetName = workBook.getSheetName(i);

	                 Iterator rows = sheet.rowIterator();
	                 while (rows.hasNext()) {
	                     XSSFRow row = (XSSFRow) rows.next();
	                     Iterator cells = row.cellIterator();

	                     List<XSSFCell> data = new LinkedList<XSSFCell>();
	                     while (cells.hasNext()) {
	                         XSSFCell cell = (XSSFCell) cells.next();
	                         cell.setCellType(Cell.CELL_TYPE_STRING);
	                         data.add(cell);
	                     }
	                     hashMap.put(row.getRowNum(), data);
	                     // sheetData.add(data);
	                 }
	                 outerMap.put(sheetName, hashMap);
	                 hashMap = new LinkedHashMap<Integer, List>();
	             }
	         }
	         catch (IOException e) {
	             e.printStackTrace();
	         }
	         finally {
	             if (fis != null) {
	                 try {
	                     fis.close();
	                 }
	                 catch (IOException e) {
	                     e.printStackTrace();
	                 }
	             }
	         }
	         return outerMap;
	     }
	     
	@SuppressWarnings("rawtypes")
	public static HashMap loadExcelLines(File fileName,String sSheetName) {
	         // Used the LinkedHashMap and LikedList to maintain the order
	         HashMap<String, LinkedHashMap<String, List>> outerMap = new LinkedHashMap<String, LinkedHashMap<String, List>>();
	         LinkedHashMap<String, List> hashMap = new LinkedHashMap<String, List>();
	         String sheetName = null;
	         String RowKeyName=null;
	         int iCol=0;
	         // Create an ArrayList to store the data read from excel sheet.
	         // List sheetData = new ArrayList();
	         FileInputStream fis = null;
	         try {
	             fis = new FileInputStream(fileName);
	             // Create an excel workbook from the file system
	             XSSFWorkbook workBook = new XSSFWorkbook(fis);
	             wBook=workBook;
	             // Get the first sheet on the workbook.
	             for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
	                 XSSFSheet sheet = workBook.getSheetAt(i);
	                 oSheet=sheet;
	                 // XSSFSheet sheet = workBook.getSheetAt(0);
	                 sheetName = workBook.getSheetName(i);
	                 if (sheetName.equals(sSheetName)) {
	                	 Iterator rows = sheet.rowIterator();
	                	 while (rows.hasNext()) {
	                		 XSSFRow row = (XSSFRow) rows.next();
	                		 Iterator cells = row.cellIterator();
	                		 List<XSSFCell> data = new LinkedList<XSSFCell>();
	                		 RowKeyName=null;
		                	 iCol=0;
	                		 while (cells.hasNext()) {
	                			 XSSFCell cell = (XSSFCell) cells.next();
	                			 cell.setCellType(Cell.CELL_TYPE_STRING);
	                			 if (iCol==0){
	                				 RowKeyName=cell.getStringCellValue();
	                				 iCol++;
	                			 } else {
	                				 data.add(cell);
	                				 iCol++;
	                			 }
	                		 }
	                		 hashMap.put(RowKeyName, data);
	                		 // sheetData.add(data);
	                	 }
	                	 outerMap.put(sheetName, hashMap);
	                	 hashMap = new LinkedHashMap<String, List>();
	                 }
	             }
	         }
	         catch (IOException e) {
	             e.printStackTrace();
	         }
	         finally {
	             if (fis != null) {
	                 try {
	                     fis.close();
	                 }
	                 catch (IOException e) {
	                     e.printStackTrace();
	                 }
	             }
	         }
	         return outerMap;
	     }
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public static HashMap GetExcelLines(File fileName,String sSheetName) {
	         // Used the LinkedHashMap and LikedList to maintain the order
	         HashMap<String, HashMap<String, HashMap<String,HashMap<String,String>>>> outerMap = new HashMap<String, HashMap<String, HashMap<String,HashMap<String,String>>>>();
	         HashMap<String, HashMap<String, HashMap<String,String>>> hashMap = new HashMap<String, HashMap<String, HashMap<String,String>>>();
	         
	         String sheetName = null;
	         String RowKeyName=null;
	         int iCol=0,iRow=0,iTableHeaderNameIndex=0;
	         // Create an ArrayList to store the data read from excel sheet.
	         // List sheetData = new ArrayList();
	         FileInputStream fis = null;
	         try {
	             fis = new FileInputStream(fileName);
	             // Create an excel workbook from the file system
	             XSSFWorkbook workBook = new XSSFWorkbook(fis);
	             wBook=workBook;
	             // Get the first sheet on the workbook.
	             for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
	                 XSSFSheet sheet = workBook.getSheetAt(i);
	                 // XSSFSheet sheet = workBook.getSheetAt(0);
	                 sheetName = workBook.getSheetName(i);
	                 oSheet=sheet;
	                 if (sheetName.equals(sSheetName)) {
	                	 Iterator rows = sheet.rowIterator();
	                	 iRow=0;
	                	 ArrayList<String> TableHeaderNames = new ArrayList<String>();
	                	 
	                	 
	                	 while (rows.hasNext()) {
	                		 XSSFRow row = (XSSFRow) rows.next();
	                		 Iterator cells = row.cellIterator();
	                		 HashMap data = new HashMap();
	                		 RowKeyName=null;
		                	 iCol=0;
		                	 if (iRow==0){
		                		 while (cells.hasNext()) {
		                			 XSSFCell cell = (XSSFCell) cells.next();
		                			 cell.setCellType(Cell.CELL_TYPE_STRING);
		                			 if (iCol==0){
		                				 RowKeyName=cell.getStringCellValue();
		                				 iCol++;
		                			 } else {
		                				 TableHeaderNames.add(cell.getStringCellValue());
		                				 iCol++;
		                			 }		                		 
		                		 }
		                	 } else {
		                		 while (cells.hasNext()) {
		                			 XSSFCell cell = (XSSFCell) cells.next();
		                			 cell.setCellType(Cell.CELL_TYPE_STRING);
		                			 if (iCol==0){
		                				 RowKeyName=cell.getStringCellValue();
		                				 iCol++;
		                			 } else {
		                				 data.put(TableHeaderNames.get(iTableHeaderNameIndex),cell.getStringCellValue());
		                				 iCol++;
		                				 iTableHeaderNameIndex++;
		                				 if (TableHeaderNames.size()-iTableHeaderNameIndex==0){
		                				 	iTableHeaderNameIndex=0;
		                					break;
		                				 }
		                			}
		                		 }
	                		 }
		                	 iRow++;
		                	 if (iRow>1) {
		                		 hashMap.put(RowKeyName, data);
	                		 // sheetData.add(data);
		                	 }
	                	 }
	                	 outerMap.put(sheetName, hashMap);
	                	 hashMap = new HashMap<String, HashMap<String, HashMap<String,String>>>();
	                 }
	             }
	         }
	         catch (IOException e) {
	             e.printStackTrace();
	         }
	         finally {
	             if (fis != null) {
	                 try {
	                     fis.close();
	                 }
	                 catch (IOException e) {
	                     e.printStackTrace();
	                 }
	             }
	         }
	         return outerMap;
	     }    
	     @SuppressWarnings({ "rawtypes", "unchecked" })
		public static HashMap TestContents(File fileName,String sSheetName,String sTestCaseID) {
	         // Used the LinkedHashMap and LikedList to maintain the order
	         HashMap<String, HashMap<String, HashMap<String,HashMap<String,String>>>> outerMap = new HashMap<String, HashMap<String, HashMap<String,HashMap<String,String>>>>();
	         HashMap<String, HashMap<String, HashMap<String,String>>> hashMap = new HashMap<String, HashMap<String, HashMap<String,String>>>();
	         String sheetName = null;
	         String RowKeyName=null;
	         
	         int iCol=0,iRow=0,iTableHeaderNameIndex=0;
	         // Create an ArrayList to store the data read from excel sheet.
	         // List sheetData = new ArrayList();
	         FileInputStream fis = null;
	         try {
	             fis = new FileInputStream(fileName);
	             XSSFWorkbook workBook = new XSSFWorkbook(fis);
	             wBook=workBook;
	             boolean bFindSheet=false;
	             for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
	                 XSSFSheet sheet = workBook.getSheetAt(i);
	                 oSheet=sheet;
	                 sheetName = workBook.getSheetName(i);
	                 if (sheetName.equals(sSheetName)) {
	                	 Iterator rows = sheet.rowIterator();
	                	 iRow=0;
	                	 ArrayList<String> TableHeaderNames = new ArrayList<String>();
	                	 bFindSheet=true;
	                	 while (rows.hasNext()) {
	                		 XSSFRow row = (XSSFRow) rows.next();
	                		 Iterator cells = row.cellIterator();
	                		 HashMap data = new HashMap();
	                		 RowKeyName=null;
		                	 iCol=0;
		                	 if (iRow==0){
		                		 while (cells.hasNext()) {
		                			 XSSFCell cell = (XSSFCell) cells.next();
		                			 cell.setCellType(Cell.CELL_TYPE_STRING);
		                			 if (iCol==0){
		                				 RowKeyName=cell.getStringCellValue();
		                				 iCol++;
		                			 } else {
		                				 TableHeaderNames.add(cell.getStringCellValue());
		                				 iCol++;
		                			 }		                		 
		                		 }
		                	 } else {
		                		 while (cells.hasNext()) {
		                			 XSSFCell cell = (XSSFCell) cells.next();
		                			 cell.setCellType(Cell.CELL_TYPE_STRING);
		                			 if (iCol==0){
		                				 RowKeyName=cell.getStringCellValue();
		                				 if (!RowKeyName.equals(sTestCaseID)){
		                					 RowKeyName="";
		                					 iCol=0;
		                					 break;
		                				 }
		                				 iCol++;
		                			 } else {
		                				 data.put(TableHeaderNames.get(iTableHeaderNameIndex),cell.getStringCellValue());
		                				 iCol++;
		                				 iTableHeaderNameIndex++;
		                				 if (TableHeaderNames.size()-iTableHeaderNameIndex==0){
		                				 	iTableHeaderNameIndex=0;
		                					break;
		                				 }
		                			 }
		                		 }
	                		 }
		                	 iRow++;
		                	 if (iRow>1) {
		                		 if (data.size()>0) {
		                			 hashMap.put(RowKeyName, data);
		                		 // 	sheetData.add(data);
		                		 }
		                	 }
	                	 }
	                	 if (hashMap.size()>0){
	                		outerMap.put(sheetName, hashMap);
	                	 	hashMap = new HashMap<String, HashMap<String, HashMap<String,String>>>();
	                	 }
	                 } else {
	                	 if (i == workBook.getNumberOfSheets() && bFindSheet==false) {
	                		 System.out.println("WorkSheet with name '" + sSheetName + "' not found.");
	                	 }
	                	 if(bFindSheet){
	                		 System.out.println("WorkSheet found.");
	                		 break;
	                	 }
	                 }
	             }
	         }
	         catch (IOException e) {
	             e.printStackTrace();
	             System.out.println("Workbook '" + fileName + "' reading error.");
	         }
	         finally {
	             if (fis != null) {
	                 try {
	                     fis.close();
	                 }
	                 catch (IOException e) {
	                     e.printStackTrace();
	                 }
	             }
	         }
	         return outerMap;
	     }	   
	         @SuppressWarnings("rawtypes")
			public static XSSFRow GetRow(String sSheetName,String stringToFind){
		    	 boolean bBreakLoopCondition=false,bFoundSheet=false;
		    	 String sheetName = null;
		         try {
		             for (int i = 0; i < wBook.getNumberOfSheets(); i++) {
		                 XSSFSheet sheet = wBook.getSheetAt(i);
		                 sheetName = wBook.getSheetName(i);	   
		                 if (sheetName.equals(sSheetName)) {
		                	 bFoundSheet=true;
		        	    	 Iterator rows = sheet.rowIterator();
		        	    	 while(rows.hasNext()){
		        	    		 bBreakLoopCondition=false;
		                		 XSSFRow row = (XSSFRow) rows.next();
		        	    		 Cell currentCell = row.getCell(0);
		        	    		 switch (currentCell.getCellType()) {
		        	    		 case Cell.CELL_TYPE_STRING:
		        	    			 if(row.getCell(0).toString()!=null){
		        	    				 if(row.getCell(0).toString().trim().length() != 0){
		        	    					 if(row.getCell(0).toString().trim().equalsIgnoreCase(stringToFind)){
		        	    						 return row;
		        	    					 }
		        	    				 }
		        	    			 }
		        	    			 break;
		        	    		 case Cell.CELL_TYPE_NUMERIC:
		        	    			 if(String.valueOf(currentCell.getNumericCellValue())!=null){
		        	    				 if(String.valueOf(currentCell.getNumericCellValue()).trim().length() != 0){
		        	    					 if(String.valueOf(currentCell.getNumericCellValue()).equalsIgnoreCase(stringToFind)){
		        	    						 return row;
		        	    					 }
		        	    				 }
		        	    			 }
		        	    			 break;
		        	    		 }
		        	    		 if (bBreakLoopCondition){
		        	    			 break;
		        	    		 } 
		        	    	 }	                	 
		                 }
		                 if (bFoundSheet){
		                	 break;
		                 }
		             }
		         } catch (Exception e) {
		             e.printStackTrace();
		         }
	    	 return null;
	     }

	     @SuppressWarnings("rawtypes")
		public static int findRow(String sSheetName,String stringToFind){
	    	 boolean bBreakLoopCondition=false,bFoundSheet=false;
	    	 int iMatchRow;
	    	 String sheetName = null;
	         try {
	             for (int i = 0; i < wBook.getNumberOfSheets(); i++) {
	                 XSSFSheet sheet = wBook.getSheetAt(i);
	                 sheetName = wBook.getSheetName(i);	   
	                 if (sheetName.equals(sSheetName)) {
	                	 bFoundSheet=true;
	        	    	 Iterator rows = sheet.rowIterator();
	        	    	 iMatchRow=0;
	        	    	 while(rows.hasNext()){
	        	    		 bBreakLoopCondition=false;
	        	    		 iMatchRow=iMatchRow+1;
	                		 XSSFRow row = (XSSFRow) rows.next();
	        	    		 Cell currentCell = row.getCell(0);
	        	    		 switch (currentCell.getCellType()) {
	        	    		 case Cell.CELL_TYPE_STRING:
	        	    			 if(row.getCell(0).toString()!=null){
	        	    				 if(row.getCell(0).toString().trim().length() != 0){
	        	    					 if(row.getCell(0).toString().trim().equalsIgnoreCase(stringToFind)){
//	        	    						 bFound=true;
//	        	    						 bBreakLoopCondition=true;
	        	    						 return iMatchRow;
	        	    					 }
	        	    				 }
	        	    			 }
	        	    			 break;
	        	    		 case Cell.CELL_TYPE_NUMERIC:
	        	    			 if(String.valueOf(currentCell.getNumericCellValue())!=null){
	        	    				 if(String.valueOf(currentCell.getNumericCellValue()).trim().length() != 0){
	        	    					 if(String.valueOf(currentCell.getNumericCellValue()).equalsIgnoreCase(stringToFind)){
//	        	    						 bFound=true;
//	        	    						 bBreakLoopCondition=true;
	        	    						 return iMatchRow;
	        	    					 }
	        	    				 }
	        	    			 }
	        	    			 break;
	        	    		 }
	        	    		 if (bBreakLoopCondition){
	        	    			 break;
	        	    		 } 
	        	    	 }	                	 
	                 }
	                 if (bFoundSheet){
	                	 break;
	                 }
	             }
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	         return 0;
	     }
	 }