package ru.inversionkavkaz.dnrwsadm.entity;

import java.math.BigDecimal;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;

/**
@author  porche
@since   2020/12/15 11:08:00
*/
@Entity (name="ru.inversionkavkaz.dnrwsadm.entity.PVerifyRequest")
//@Table (name="IK_OV_DNR_VRFREQ")
public class PVerifyRequest implements Serializable
{
    private static final long serialVersionUID = 15_12_2020_11_08_00l;

    private Long ID;
    private String SERVICENAME;
    private LocalDate DATESTART;
    private LocalDate DATEEND;
    private Long PAYELEMENTID;

    public PVerifyRequest(){}

    @Id
    @GeneratedValue(generator="ik_vrf_req_id_seq")
    @Column(name="ID",nullable = false,length = 0)
    public Long getID() {
        return ID;
    }
    public void setID(Long val) {
        ID = val; 
    }

    @Column(name="SERVICENAME",nullable = false,length = 32)
    public String getSERVICENAME() {
        return SERVICENAME;
    }
    public void setSERVICENAME(String val) {
        SERVICENAME = val; 
    }

    @Column(name="DATESTART",nullable = false)
    public LocalDate getDATESTART() {
        return DATESTART;
    }
    public void setDATESTART(LocalDate val) {
        DATESTART = val; 
    }

    @Column(name="DATEEND",nullable = false)
    public LocalDate getDATEEND() {
        return DATEEND;
    }
    public void setDATEEND(LocalDate val) {
        DATEEND = val; 
    }

    @Column(name="PAYELEMENTID",length = 0)
    public Long getPAYELEMENTID() {
        return PAYELEMENTID;
    }
    public void setPAYELEMENTID(Long val) {
        PAYELEMENTID = val; 
    }
}