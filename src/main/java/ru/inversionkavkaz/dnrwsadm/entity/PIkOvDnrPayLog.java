package ru.inversionkavkaz.dnrwsadm.entity;

import java.math.BigDecimal;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;

/**
@author  porche
@since   2020/02/10 09:58:36
*/
@Entity (name="ru.inversionkavkaz.dnrwsabm.entity.PIkOvDnrPayLog")
@Table (name="IK_OV_DNR_PAY_LOG")
public class PIkOvDnrPayLog extends IDMarkable implements Serializable
{
    private static final long serialVersionUID = 10_02_2020_09_58_36l;
    public static final class Columns {
        public static final String ID = "ID";
        public static final String IDDEAL = "IDDEAL";
        public static final String ERRCODE = "ERRCODE";
        public static final String ERRMESSAGE = "ERRMESSAGE";
        public static final String DCREATEDATE = "DCREATEDATE";
        public static final String OPERTYPE ="OPERTYPE";
        public static final String SUMM = "SUMM";
        public static final String ACCOUNT = "ACCOUNT";
        public static final String SINP = "SINP";
        public static final String SOUT = "SOUT";
        public static final String CSYSTEM = "CSYSTEM";
    }
    private Long ID;
    private Long IDDEAL;
    private Long ERRCODE;
    private String ERRMESSAGE;
    private LocalDate DCREATEDATE;
    private String OPERTYPE;
    private BigDecimal SUMM;
    private String ACCOUNT;
    private String SINP;
    private String SOUT;
    private String CSYSTEM;

    public PIkOvDnrPayLog(){}

    @Id 
    @Column(name="ID",nullable = false,length = 0)
    public Long getID() {
        return ID;
    }
    public void setID(Long val) {
        ID = val; 
    }
    @Column(name="IDDEAL",nullable = false,length = 0)
    public Long getIDDEAL() {
        return IDDEAL;
    }
    public void setIDDEAL(Long val) {
        IDDEAL = val; 
    }
    @Column(name="ERRCODE",nullable = false,length = 0)
    public Long getERRCODE() {
        return ERRCODE;
    }
    public void setERRCODE(Long val) {
        ERRCODE = val; 
    }
    @Column(name="ERRMESSAGE",length = 1024)
    public String getERRMESSAGE() {
        return ERRMESSAGE;
    }
    public void setERRMESSAGE(String val) {
        ERRMESSAGE = val; 
    }
    @Column(name="DCREATEDATE")
    public LocalDate getDCREATEDATE() {
        return DCREATEDATE;
    }
    public void setDCREATEDATE(LocalDate val) {
        DCREATEDATE = val; 
    }
    @Column(name="OPERTYPE",length = 20)
    public String getOPERTYPE() {
        return OPERTYPE;
    }
    public void setOPERTYPE(String val) {
        OPERTYPE = val; 
    }
    @Column(name="SUMM",length = 0)
    public BigDecimal getSUMM() {
        return SUMM;
    }
    public void setSUMM(BigDecimal val) {
        SUMM = val; 
    }
    @Column(name="ACCOUNT",length = 32)
    public String getACCOUNT() {
        return ACCOUNT;
    }
    public void setACCOUNT(String val) {
        ACCOUNT = val; 
    }
    @Column(name="SINP",length = 1024)
    public String getSINP() {
        return SINP;
    }
    public void setSINP(String val) {
        SINP = val; 
    }
    @Column(name="SOUT",length = 1024)
    public String getSOUT() {
        return SOUT;
    }
    public void setSOUT(String val) {
        SOUT = val; 
    }
    @Column(name="CSYSTEM",length = 32)
    public String getCSYSTEM() {
        return CSYSTEM;
    }
    public void setCSYSTEM(String val) {
        CSYSTEM = val; 
    }
    @Transient
    @Override
    public Long getMarkLongID() {
        return getID();
    }
    @Override
    public boolean isMark() {
        return super.isMark();
    }
}