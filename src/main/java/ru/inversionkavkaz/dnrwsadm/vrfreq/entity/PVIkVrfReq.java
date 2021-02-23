package ru.inversionkavkaz.dnrwsadm.vrfreq.entity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  porche
@since   2021/02/15 16:50:35
*/
@Entity (name="ru.inversionkavkaz.dnrwsadm.entity.PVIkVrfReq")
@Table (name="V_IK_VRF_REQ")
public class PVIkVrfReq extends IDMarkable implements Serializable
{
    private static final long serialVersionUID = 15_02_2021_16_50_35l;

    private Long ID;
    private LocalDateTime DCREATE;
    private String SERVICENAME;

    private String SERVICEDESCRIPTION;
    private String PAYELEMENTIDS;
    private String URL;

    private LocalDateTime DATESTART;
    private LocalDateTime DATEEND;
    private Long PAYELEMENTID;
    private String STATUS;
    private LocalDateTime STATUSDATE;
    private String STATUSINFO;
    private Boolean RESPONCE_EXISTS;
    private Boolean REPORTDATA_EXISTS;
    private String FILENAME;
    private String SAVEDUSR;
    private LocalDateTime SAVEDDATE;

    public PVIkVrfReq(){}

    @Id 
    @Column(name="ID",nullable = false,length = 0)
    public Long getID() {
        return ID;
    }
    public void setID(Long val) {
        ID = val; 
    }
    @Column(name="DCREATE",nullable = false)
    public LocalDateTime getDCREATE() {
        return DCREATE;
    }
    public void setDCREATE(LocalDateTime val) {
        DCREATE = val; 
    }

    @Column(name="SERVICENAME",nullable = false,length = 32)
    public String getSERVICENAME() {
        return SERVICENAME;
    }
    public void setSERVICENAME(String val) {
        SERVICENAME = val; 
    }

    @Transient
    @Column(name="SERVICEDESCRIPTION",nullable = false,length = 255)
    public String getSERVICEDESCRIPTION() {
        return SERVICEDESCRIPTION;
    }
    public void setSERVICEDESCRIPTION(String val) {
        SERVICEDESCRIPTION = val;
    }

    @Transient
    @Column(name="PAYELEMENTIDS",nullable = false,length = 255)
    public String getPAYELEMENTIDS() {
        return PAYELEMENTIDS;
    }
    public void setPAYELEMENTIDS(String val) {
        PAYELEMENTIDS = val;
    }

    @Transient
    @Column(name="URL",nullable = false,length = 255)
    public String getURL() {
        return URL;
    }
    public void setURL(String val) {
        URL = val;
    }

    @Column(name="DATESTART",nullable = false)
    public LocalDateTime getDATESTART() {
        return DATESTART;
    }
    public void setDATESTART(LocalDateTime val) {
        DATESTART = val; 
    }
    @Column(name="DATEEND",nullable = false)
    public LocalDateTime getDATEEND() {
        return DATEEND;
    }
    public void setDATEEND(LocalDateTime val) {
        DATEEND = val; 
    }
    @Column(name="PAYELEMENTID",length = 0)
    public Long getPAYELEMENTID() {
        return PAYELEMENTID;
    }
    public void setPAYELEMENTID(Long val) {
        PAYELEMENTID = val; 
    }
    @Column(name="STATUS",length = 16)
    public String getSTATUS() {
        return STATUS;
    }
    public void setSTATUS(String val) {
        STATUS = val; 
    }
    @Column(name="STATUSDATE",nullable = false)
    public LocalDateTime getSTATUSDATE() {
        return STATUSDATE;
    }
    public void setSTATUSDATE(LocalDateTime val) {
        STATUSDATE = val; 
    }
    @Column(name="STATUSINFO",length = 512)
    public String getSTATUSINFO() {
        return STATUSINFO;
    }
    public void setSTATUSINFO(String val) {
        STATUSINFO = val; 
    }
    @Column(name="RESPONCE_EXISTS",length = 1)
    public Boolean getRESPONCE_EXISTS() {
        return RESPONCE_EXISTS;
    }
    public void setRESPONCE_EXISTS(Boolean val) {
        RESPONCE_EXISTS = val; 
    }
    @Column(name="REPORTDATA_EXISTS",length = 1)
    public Boolean getREPORTDATA_EXISTS() {
        return REPORTDATA_EXISTS;
    }
    public void setREPORTDATA_EXISTS(Boolean val) {
        REPORTDATA_EXISTS = val; 
    }
    @Column(name="FILENAME",length = 255)
    public String getFILENAME() {
        return FILENAME;
    }
    public void setFILENAME(String val) {
        FILENAME = val; 
    }
    @Column(name="SAVEDUSR",length = 30)
    public String getSAVEDUSR() {
        return SAVEDUSR;
    }
    public void setSAVEDUSR(String val) {
        SAVEDUSR = val; 
    }
    @Column(name="SAVEDDATE")
    public LocalDateTime getSAVEDDATE() {
        return SAVEDDATE;
    }
    public void setSAVEDDATE(LocalDateTime val) {
        SAVEDDATE = val; 
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