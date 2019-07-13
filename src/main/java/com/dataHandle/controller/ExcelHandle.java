package com.dataHandle.controller;

import com.dataHandle.bean.ElectricProject;
import com.dataHandle.service.EPservice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelHandle {
    public static final int COL_LEN = 8;
    public static final int SELECT_LEN = 7;

    public static final String[] colName = {
            "包号","项目单位","项目名称","物资名称","物资描述","单位	","数量","技术规范ID"
    };
    public static final String[] otherColName={
            "智能","变压等级"
    };
    public static final HashMap<String,String> col2attr=new HashMap<String,String>(){{
        put("包号","packageNo");
        put("项目单位","company");
        put("项目名称","name");
        put("物资名称","materials");
        put("物资描述","describe");
        put("单位","unit");
        put("数量","amount");
        put("技术规范ID","technicalNormID");
        put("智能","smart");
        put("变压等级","varyingVoltageLevel");
    }};
    public static final HashMap<String,String> attr2col=new HashMap<String,String>(){{
        put("packageNo","包号");
        put("company","项目单位");
        put("name","项目名称");
        put("materials","物资名称");
        put("describe","物资描述");
        put("unit","单位");
        put("amount","数量");
        put("technicalNormID","技术规范ID");
        put("smart","智能");
        put("varyingVoltageLevel","变压等级");
    }};


    public ExcelHandle(){

    }

    public void excel2db(String excelPath) throws Exception {
        FileInputStream in = new FileInputStream(new File(excelPath));
        XSSFWorkbook xw = new XSSFWorkbook(in);
        XSSFSheet sheet = xw.getSheetAt(0);
        int line = 0 ;
        for (Row row : sheet) {
            boolean bankLine = true;
            int i=0;
            Object[] cells = new Object[COL_LEN];
            for (Cell cell : row) {
                //获取每个单元格的类型
                CellType cellType = cell.getCellType();
                if(cellType == CellType.NUMERIC){
                    cells[i++] = cell.getNumericCellValue();
                }
                if(cellType == CellType.STRING) {
                    cells[i++] = cell.getStringCellValue() ;
                }
            }
            if(line > 0) {
                for (Object c:cells){
                    if(c!=null){
                        bankLine = false;
                        break;
                    }
                }
                if (bankLine) break;
                else {
                    System.out.print(line + 1 + " ");
                    ElectricProject ep = new ElectricProject(cells);
                    EPservice eps = new EPservice();
                    eps.insert(ep);
                }
            }
            line++;
        }
    }

    public void db2excel(String excelName, List<ElectricProject> selectList){
        for (ElectricProject ep:selectList) {
            for (String s: ep.getParms()){
                System.out.print(s);
            }
            System.out.println();
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(excelName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        int line=0;
        XSSFRow row = sheet.createRow(line++);
        int i=0;
        for (String colName:colName){
            row.createCell(i++).setCellValue(colName);
        }
        for (String colName:otherColName){
            row.createCell(i++).setCellValue(colName);
        }
        for (ElectricProject ep:selectList){
            i=0;
            row = sheet.createRow(line++);
            row.createCell(i++).setCellValue(ep.getPackageNo());
            row.createCell(i++).setCellValue(ep.getCompany());
            row.createCell(i++).setCellValue(ep.getName());
            row.createCell(i++).setCellValue(ep.getMaterials());
            row.createCell(i++).setCellValue(ep.getDescribe());
            row.createCell(i++).setCellValue(ep.getUnit());
            row.createCell(i++).setCellValue(ep.getAmount());
            row.createCell(i++).setCellValue(ep.getTechnicalNormID());
            row.createCell(i++).setCellValue(ep.isSmart());
            row.createCell(i).setCellValue(ep.getVaryingVoltageLevel());
        }
        try {
            if (out !=null) {
                workbook.write(out);
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printDB(String[] packageNos,
                         String[] companys,
                         String[] names,
                         String[] materialss,
                         String[] technicalNormIDs,
                         String[] smarts,
                         String[] varyingVoltageLevels){
        EPservice eps = new EPservice();
        ArrayList<ElectricProject> selectList =
                (ArrayList<ElectricProject>) eps.getFilter(packageNos, companys,names,materialss,
                                                            technicalNormIDs, smarts,
                                                            varyingVoltageLevels);
        for (ElectricProject ep:selectList) {
            for (String s:ep.getParms()){
                System.out.println(s);
            }
        }

    }


}
