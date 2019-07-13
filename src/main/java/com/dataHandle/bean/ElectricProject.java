package com.dataHandle.bean;
import java.util.Objects;

public class ElectricProject{

    public static final String[] varyingVoltageLevelList={
            "AC35kV", "AC66kV", "AC110kV"
    };
    public static final String smartMatch = "智能";
    public static final String[] selectAttrNames ={
            "packageNo","company","name","materials","technicalNormID", "smart", "varyingVoltageLevel"
    };
    public static final String[] attrNames ={
            "packageNo","name","company","materials","describe", "unit",
            "amount","technicalNormID", "smart", "varyingVoltageLevel"
    };
    private String packageNo;
    private String name;
    private String company;
    private String materials;
    private String describe;
    private String unit;
    private int amount;
    private String technicalNormID;
    private boolean smart;
    private String varyingVoltageLevel;

    public ElectricProject(){

    }

    public ElectricProject(String packageNo, String name, String materials, String describe, String company, int amount, String unit, String technicalNormID) {
        this.packageNo = packageNo;
        this.name = name;
        this.materials = materials;
        this.describe = describe;
        this.company = company;
        this.amount = amount;
        this.unit = unit;
        this.technicalNormID = technicalNormID;
    }

    public ElectricProject(Object ... parms){
        this.packageNo = (String) parms[0] ;
        this.company = (String) parms[1];
        this.name = (String) parms[2] ;
        this.materials = (String) parms[3];
        this.describe = (String) parms[4] ;
        this.unit = (String) parms[5] ;
        if (parms[6]!=null) {
            this.amount = (int) ((Double) parms[6]).doubleValue();
        }else{
            this.amount = 0;
        }
        this.technicalNormID = (String) parms[7];
        setOther(this.materials);
    }

    private void setOther(String materials){
        this.smart = judgeIsSmart(materials);
        System.out.print(smart+" ");
        this.varyingVoltageLevel = judgeVaryingVoltageLevel(materials);
        System.out.print(varyingVoltageLevel+" ");
        System.out.println();
    }

    private boolean judgeIsSmart(String materials){
        return materials.contains(smartMatch);
    }

    private String judgeVaryingVoltageLevel(String materials){
        for (String s:varyingVoltageLevelList) {
             if(materials.contains(s)){
                 return s;
             }
        }
        return " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectricProject that = (ElectricProject) o;
        return amount == that.amount &&
                packageNo.equals(that.packageNo) &&
                name.equals(that.name) &&
                materials.equals(that.materials) &&
                describe.equals(that.describe) &&
                company.equals(that.company) &&
                unit.equals(that.unit) &&
                technicalNormID.equals(that.technicalNormID);
    }


    @Override
    public int hashCode() {
        // todo
        return Objects.hash(packageNo, name, describe, company, amount);
    }

    public String[] getParms(){
        return new String[]{
                this.packageNo,
                this.company,
                this.name,
                this.materials,
                this.describe,
                this.unit,
                String.valueOf(this.amount),
                this.technicalNormID,
                String.valueOf(this.smart),
                this.varyingVoltageLevel
        };
    }
    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTechnicalNormID() {
        return technicalNormID;
    }

    public void setTechnicalNormID(String technicalNormID) {
        this.technicalNormID = technicalNormID;
    }

    public boolean isSmart() {
        return smart;
    }

    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    public String getVaryingVoltageLevel() {
        return varyingVoltageLevel;
    }

    public void setVaryingVoltageLevel(String varyingVoltageLevel) {
        this.varyingVoltageLevel = varyingVoltageLevel;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }
}
