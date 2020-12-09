package ru.inversionkavkaz.dnrwsadm.entity;

import java.time.*;
import java.io.Serializable;
import javax.persistence.*;


/**
@author  porche
@since   2020/12/08 22:29:20
*/
@Entity (name="ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrSendActions")
@Table (name="IK_OV_DNR_SEND_ACTIONS")
public class PIkOvDnrSendActions implements Serializable
{
    private static final String serialVersionUID = "08_12_2020_22_29_20";

    private Long ID;
    private Long SEND_ID;
    private LocalDateTime DCREATE;
    private String ACTIONTYPE;
    private String MESSAGE;
    private String CUSRLOGNAME;

    public PIkOvDnrSendActions(){}

    @Id 
    @Column(name="ID",nullable = false,length = 0)
    public Long getID() {
        return ID;
    }
    public void setID(Long val) {
        ID = val; 
    }
    @Column(name="SEND_ID",nullable = false,length = 0)
    public Long getSEND_ID() {
        return SEND_ID;
    }
    public void setSEND_ID(Long val) {
        SEND_ID = val; 
    }
    @Column(name="DCREATE",nullable = false)
    public LocalDateTime getDCREATE() {
        return DCREATE;
    }
    public void setDCREATE(LocalDateTime val) {
        DCREATE = val; 
    }
    @Column(name="ACTIONTYPE",nullable = false,length = 1)
    public String getACTIONTYPE() {
        return ACTIONTYPE;
    }
    public void setACTIONTYPE(String val) {
        ACTIONTYPE = val; 
    }
    @Column(name="MESSAGE",nullable = false,length = 255)
    public String getMESSAGE() {
        return MESSAGE;
    }
    public void setMESSAGE(String val) {
        MESSAGE = val; 
    }
    @Column(name="CUSRLOGNAME",nullable = false,length = 32)
    public String getCUSRLOGNAME() {
        return CUSRLOGNAME;
    }
    public void setCUSRLOGNAME(String val) {
        CUSRLOGNAME = val; 
    }
}