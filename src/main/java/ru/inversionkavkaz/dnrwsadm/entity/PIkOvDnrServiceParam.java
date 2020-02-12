package ru.inversionkavkaz.dnrwsadm.entity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  porche
@since   2020/02/12 14:46:21
*/
@Entity (name="ru.inversionkavkaz.dnrwsabm.entity.PIkOvDnrServiceParam")
@Table (name="IK_OV_DNR_SERVICE_PARAM")
public class PIkOvDnrServiceParam implements Serializable
{
    private static final long serialVersionUID = 12_02_2020_14_46_21l;

    public static class Columns {
        public static final String SERVICE_ID = "SERVICE_ID";
        public static final String PNAME = "PNAME";
        public static final String PVALUE = "PVALUE";
    }

    private String SERVICE_ID;
    private String PNAME;
    private String PVALUE;

    public PIkOvDnrServiceParam(){}

    @Id 
    @Column(name="SERVICE_ID",nullable = false,length = 32)
    public String getSERVICE_ID() {
        return SERVICE_ID;
    }
    public void setSERVICE_ID(String val) {
        SERVICE_ID = val; 
    }
    @Id 
    @Column(name="PNAME",nullable = false,length = 255)
    public String getPNAME() {
        return PNAME;
    }
    public void setPNAME(String val) {
        PNAME = val; 
    }
    @Column(name="PVALUE",length = 255)
    public String getPVALUE() {
        return PVALUE;
    }
    public void setPVALUE(String val) {
        PVALUE = val; 
    }
}