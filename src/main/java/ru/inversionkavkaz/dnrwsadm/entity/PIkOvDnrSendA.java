package ru.inversionkavkaz.dnrwsadm.entity;

import java.time.*;
import java.io.Serializable;
import javax.persistence.*;

/**
@author  Valeriy Bugaev
@since   2020/12/09 12:07:13
*/
@Entity (name="ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrSendA")
@Table (name="IK_OV_DNR_SEND_A")
public class PIkOvDnrSendA implements Serializable
{
    private static final String serialVersionUID = "09_12_2020_12_07_13";

    private Long I_ID;
    private LocalDateTime DATECREATE;
    private Long SEND_ID;
    private String ERR;
    private Long ERR_CODE;
    private Boolean IS_ERR_CODE_FINAL;
    private Boolean IS_ARCHIVE;

    public PIkOvDnrSendA(){}

    @Id 
    @Column(name="I_ID",nullable = false,length = 12)
    public Long getI_ID() {
        return I_ID;
    }
    public void setI_ID(Long val) {
        I_ID = val; 
    }
    @Column(name="DATECREATE",nullable = false)
    public LocalDateTime getDATECREATE() {
        return DATECREATE;
    }
    public void setDATECREATE(LocalDateTime val) {
        DATECREATE = val; 
    }
    @Column(name="SEND_ID",nullable = false,length = 12)
    public Long getSEND_ID() {
        return SEND_ID;
    }
    public void setSEND_ID(Long val) {
        SEND_ID = val; 
    }
    @Column(name="ERR",length = 4000)
    public String getERR() {
        return ERR;
    }
    public void setERR(String val) {
        ERR = val; 
    }
    @Column(name="ERR_CODE",nullable = false,length = 7)
    public Long getERR_CODE() {
        return ERR_CODE;
    }
    public void setERR_CODE(Long val) {
        ERR_CODE = val; 
    }
    @Column(name="IS_ERR_CODE_FINAL",nullable = false,length = 0)
    public Boolean getIS_ERR_CODE_FINAL() {
        return IS_ERR_CODE_FINAL;
    }
    public void setIS_ERR_CODE_FINAL(Boolean val) {
        IS_ERR_CODE_FINAL = val; 
    }
    @Column(name="IS_ARCHIVE",nullable = false,length = 0)
    public Boolean getIS_ARCHIVE() {
        return IS_ARCHIVE;
    }
    public void setIS_ARCHIVE(Boolean val) {
        IS_ARCHIVE = val; 
    }
}