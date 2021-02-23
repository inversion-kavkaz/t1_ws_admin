package ru.inversionkavkaz.dnrwsadm.entity;

import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversionkavkaz.dnrwsadm.utils.LovInterface;

/**
@author  porche
@since   2020/02/07 13:05:51
*/
@Entity (name="ru.inversionkavkaz.dnrwsabm.entity.PIkOvDnrService")
@Table (name="IK_OV_DNR_SERVICE")
public class PIkOvDnrService extends UMarkable implements Serializable, LovInterface
{
    private static final long serialVersionUID = 07_02_2020_13_05_51l;

    private String CNAME;
    private Boolean IENABLED;
    private String CDESCRIPTION;
    private String SECURITY_TAG;
    private String CURL;
    private String PROTOCOL;
    private Long IDOV_PLAT;
    private String PERSINFOXPATH;
    private String SELLERXPATH;
    private String COMPARISONURL;
    private String PAYELEMENTIDS;


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
    @Column(name="PROTOCOL", nullable = false, length = 31)
    public String getPROTOCOL() {
        return PROTOCOL;
    }
    public void setPROTOCOL(String val) {
        PROTOCOL = val;
    }
    @Column(name="IDOV_PLAT", nullable = false, length = 0)
    public Long getIDOV_PLAT() {
        return IDOV_PLAT;
    }
    public void setIDOV_PLAT(Long  val) {
        IDOV_PLAT = val;
    }
    @Column(name="PERSINFOXPATH",length = 255)
    public String getPERSINFOXPATH() {
        return PERSINFOXPATH;
    }
    public void setPERSINFOXPATH(String val) {
        PERSINFOXPATH = val;
    }
    @Column(name="SELLERXPATH",length = 255)
    public String getSELLERXPATH() {
        return SELLERXPATH;
    }
    public void setSELLERXPATH(String SELLERXPATH) {
        this.SELLERXPATH = SELLERXPATH;
    }
    @Column(name = "COMPARISONURL", length = 255)
    public String getCOMPARISONURL() {
        return COMPARISONURL;
    }
    public void setCOMPARISONURL(String COMPARISONURL) {
        this.COMPARISONURL = COMPARISONURL;
    }
    @Column(name = "PAYELEMENTIDS", length = 255)
    public String getPAYELEMENTIDS() {
        return PAYELEMENTIDS;
    }
    public void setPAYELEMENTIDS(String PAYELEMENTIDS) {
        this.PAYELEMENTIDS = PAYELEMENTIDS;
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

    @Override
    public Object getKey() {
        return getCNAME();
    }

    @Override
    public Object getValue() {
        return getCDESCRIPTION();
    }
}