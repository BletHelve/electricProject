package com.dataHandle.service;


import com.dataHandle.bean.ElectricProject;
import com.dataHandle.mapper.EPmapper;
import com.dataHandle.mapper.EPmybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EPservice {

    private EPmapper epMapper ;
    private SqlSession session ;

    public EPservice(){
    }

    private void buildSqlSession(){
        SqlSessionFactory factory= EPmybatisUtils.getFactory();
        this.session=factory.openSession(true);
        this.epMapper = session.getMapper(EPmapper.class);
    }

    public List<ElectricProject> getFilter(String[] packageNos,
                                           String[] companys,
                                           String[] names,
                                           String[] materialss,
                                           String[] technicalNormIDs,
                                           String[] smarts,
                                           String[] varyingVoltageLevels){
        this.buildSqlSession();
        boolean[] isSmarts = new boolean[smarts.length];
        for (int i=0;i<smarts.length;i++){
            if (smarts[i].equals("true")) isSmarts[i] = true;
            if (smarts[i].equals("false")) isSmarts[i] = false;
        }
        List<ElectricProject> res_list = epMapper.getEPfilter(packageNos, companys,names,materialss,
                                                                technicalNormIDs, isSmarts,
                                                                varyingVoltageLevels);
        session.close();
        return res_list;
    }


    public List<String> getOneCol(String colName){ //通过列名得到一个所有数据
        this.buildSqlSession();
        ArrayList<String> res_list;
        switch (colName){
            case "packageNo":
                res_list = new ArrayList<>(epMapper.getEPpackageNoList());
                break;
            case "name":
                res_list =  new ArrayList<>(epMapper.getEPnameList());
                break;
            case "company":
                res_list =  new ArrayList<>(epMapper.getEPcompanyList());
                break;
            case "materials":
                res_list =  new ArrayList<>(epMapper.getEPmaterialsList());
                break;
            case "technicalNormID":
                res_list =  new ArrayList<>(epMapper.getEPtechnicalNormIDList());
                break;
            case "smart":
                res_list = new ArrayList<>(Arrays.asList("true","false"));
                break;
            case "varyingVoltageLevel":
                res_list = new ArrayList<>(Arrays.asList( ElectricProject.varyingVoltageLevelList));
                break;
            default:
                res_list = new ArrayList<>();
        }
        session.close();
        return res_list;
    }
    public List<ElectricProject> getAll(){
        this.buildSqlSession();
        List<ElectricProject> res_list = epMapper.getEPall();
        session.close();
        return res_list;
    }

    public List<ElectricProject> getPackageNo(String packageNo){
        this.buildSqlSession();
        List<ElectricProject> res_list = epMapper.getEPpackageNo(packageNo);
        session.close();
        return res_list;
    }

    public List<ElectricProject> getName(String name){
        this.buildSqlSession();
        List<ElectricProject> res_list = epMapper.getEPname(name);
        session.close();
        return res_list;
    }

    public List<ElectricProject> getCompany(String company){
        this.buildSqlSession();
        List<ElectricProject> res_list = epMapper.getEPcompany(company);
        session.close();
        return res_list;
    }

    public List<ElectricProject> getTechnicalNormID(String technicalNormID){
        this.buildSqlSession();
        List<ElectricProject> res_list = epMapper.getEPtechnicalNormID(technicalNormID);
        session.close();
        return res_list;
    }

    public int insert(ElectricProject ep){
        this.buildSqlSession();
        int res = epMapper.insertEP(ep);
        session.close();
        return res;
    }


}
