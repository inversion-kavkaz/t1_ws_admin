package ru.inversionkavkaz.dnrwsadm.protocol.entity;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;
import ru.inversionkavkaz.dnrwsadm.utils.LovInterface;

/**
@author  porche
@since   2020/05/22 12:01:31
*/
@Entity (name="ru.inversionkavkaz.dnrwsabm.entity.PIkOvDnrServiceProtocol")
@Table (name="IK_OV_DNR_SERVICE_PROTOCOL")
public class PIkOvDnrServiceProtocol implements Serializable, LovInterface
{
    private static final long serialVersionUID = 22_05_2020_12_01_31l;

    private String CPROTOCOL;
    private String CDESCR;
    private String CVRFCLASSNAME;
    private String CVRFJARFILEPATH;

    public PIkOvDnrServiceProtocol(){}

    @Id 
    @Column(name="CPROTOCOL",nullable = false,length = 31)
    public String getCPROTOCOL() {
        return CPROTOCOL;
    }
    public void setCPROTOCOL(String val) {
        CPROTOCOL = val; 
    }
    @Column(name="CDESCR",length = 255)
    public String getCDESCR() {
        return CDESCR;
    }
    public void setCDESCR(String val) {
        CDESCR = val; 
    }
    @Column(name="CVRFCLASSNAME",length = 255)
    public String getCVRFCLASSNAME() {
        return CVRFCLASSNAME;
    }
    public void setCVRFCLASSNAME(String CVRFCLASSNAME) {
        this.CVRFCLASSNAME = CVRFCLASSNAME;
    }
    @Column(name="CVRFJARFILEPATH",length = 255)
    public String getCVRFJARFILEPATH() {
        return CVRFJARFILEPATH;
    }
    public void setCVRFJARFILEPATH(String CVRFJARFILEPATH) {
        this.CVRFJARFILEPATH = CVRFJARFILEPATH;
    }

    @Override
    public Object getKey() {
        return getCPROTOCOL();
    }

    @Override
    public Object getValue() {
        return getCPROTOCOL();
    }
}