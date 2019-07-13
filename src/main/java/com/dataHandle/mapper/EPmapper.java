package com.dataHandle.mapper;

import com.dataHandle.bean.ElectricProject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;


public interface EPmapper{
    @Select("select * from project")
    List<ElectricProject> getEPall();
    @Select("select * from project where packageNo=#{packageNo}")
    List<ElectricProject> getEPpackageNo(String packageNo);
    @Select("select * from project where name=#{name}")
    List<ElectricProject> getEPname(String name);
    @Select("select * from project where company=#{company}")
    List<ElectricProject> getEPcompany(String company);
    @Select("select * from project where technicalNormID=#{technicalNormID}")
    List<ElectricProject> getEPtechnicalNormID(String technicalNormID);

    @Select("select DISTINCT name from project")
    List<String> getEPnameList();

    @Select("select DISTINCT packageNo from project")
    List<String> getEPpackageNoList();

    @Select("select DISTINCT company from project")
    List<String> getEPcompanyList();

    @Select("select DISTINCT materials from project")
    List<String> getEPmaterialsList();

    @Select("select DISTINCT technicalNormID from project")
    List<String> getEPtechnicalNormIDList();


    @Select({"<script>" ,
            "select * from project " ,
            "where packageNo in " ,
            "<foreach collection='packageNos' item='item' open='(' separator=',' close=')'>" ,
            "#{item}</foreach> AND" ,

            "company in " ,
            "<foreach collection='companys' item='item' open='(' separator=',' close=')'>" ,
            "#{item}</foreach> AND"  ,

            "name in " ,
            "<foreach collection='names' item='item' open='(' separator=',' close=')'>" ,
            "#{item}</foreach> AND" ,

            "materials in " ,
            "<foreach collection='materialss' item='item' open='(' separator=',' close=')'>" ,
            "#{item}</foreach> AND"  ,

            "technicalNormID in " ,
            "<foreach collection='technicalNormIDs' item='item' open='(' separator=',' close=')'>" ,
            "#{item}</foreach> AND" ,

            "smart in " ,
            "<foreach collection='smarts' item='item' open='(' separator=',' close=')'>" ,
            "#{item}</foreach> AND" ,

            "varyingVoltageLevel in " ,
            "<foreach collection='varyingVoltageLevels' item='item' open='(' separator=',' close=')'>" ,
            "#{item}</foreach> " ,

            "</script>"})
    List<ElectricProject> getEPfilter(@Param("packageNos") String[] packageNos,
                                      @Param("companys") String[] companys,
                                      @Param("names") String[] names,
                                      @Param("materialss") String[] materialss,
                                      @Param("technicalNormIDs") String[] technicalNormIDs,
                                      @Param("smarts") boolean[] smarts,
                                      @Param("varyingVoltageLevels") String[] varyingVoltageLevels);

    @Insert("insert into project" +
            "(packageNo,name,describe,company,amount,unit,materials," +
            "technicalNormID,smart,varyingVoltageLevel)" +
            "values(#{packageNo},#{name},#{describe},#{company},#{amount},#{unit},#{materials}," +
            "#{technicalNormID},#{smart},#{varyingVoltageLevel})")
    int insertEP(ElectricProject electricProject);


}
