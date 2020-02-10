package ru.inversionkavkaz.dnrwsadm.entity;

import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;

/**
@author  porche
@since   2020/02/07 13:05:51
*/
@Entity (name="ru.inversionkavkaz.dnrwsabm.entity.PIkOvDnrService")
@Table (name="IK_OV_DNR_SERVICE")
public class PIkOvDnrService extends UMarkable implements Serializable
{
    private static final long serialVersionUID = 07_02_2020_13_05_51l;

    private String CNAME;
    private Boolean IENABLED;
    private String CDESCRIPTION;
    private String SECURITY_TAG;
    private String CURL;

    public PIkOvDnrService(){}

    @Id 
    @Column(name="CNAME",nullable = false,length = 32)
    public String getCNAME() {
        return CNAME;
    }
    public void setCNAME(String val) {
        CNAME = val; 
    }
    @Column(name="IENABLED",nullable = false,length = 1)
    public Boolean getIENABLED() {
        return IENABLED;
    }
    public void setIENABLED(Boolean val) {
        IENABLED = val; 
    }
    @Column(name="CDESCRIPTION",length = 255)
    public String getCDESCRIPTION() {
        return CDESCRIPTION;
    }
    public void setCDESCRIPTION(String val) {
        CDESCRIPTION = val; 
    }
    @Column(name="SECURITY_TAG",length = 500)
    public String getSECURITY_TAG() {
        return SECURITY_TAG;
    }
    public void setSECURITY_TAG(String val) {
        SECURITY_TAG = val; 
    }
    @Column(name="CURL",nullable = false,length = 1024)
    public String getCURL() {
        return CURL;
    }
    public void setCURL(String val) {
        CURL = val; 
    }
    @Transient
    @Override
    public String getMarkStringID() {
        return getCNAME();
    }
    @Override
    public boolean isMark() {
        return super.isMark();
    }
}