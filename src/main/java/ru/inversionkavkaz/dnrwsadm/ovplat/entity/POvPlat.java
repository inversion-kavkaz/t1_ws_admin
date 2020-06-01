package ru.inversionkavkaz.dnrwsadm.ovplat.entity;


import ru.inversionkavkaz.dnrwsadm.utils.LovInterface;

import java.io.Serializable;
import javax.persistence.*;


/**
@author  porche
@since   2020/05/25 12:25:07
*/
@Entity (name="ru.inversionkavkaz.dnrwsabm.entity.POvPlat")
@NamedNativeQuery (name="ru.inversionkavkaz.dnrwsabm.entity.POvPlat", query="SELECT idov_plat,cpsevdo,cinn,cacc, cnameoper,cpsevdo FROM ov_plat")
public class POvPlat implements Serializable, LovInterface
{
    private static final long serialVersionUID = 25_05_2020_12_25_07l;

    private Long IDOV_PLAT;
    private String CPSEVDO;
    private String CINN;
    private String CACC;
    private String CNAMEOPER;
    private String CPLATNAME;

    public POvPlat(){}

    @Id 
    @Column(name="IDOV_PLAT",nullable = false,length = 12)
    public Long getIDOV_PLAT() {
        return IDOV_PLAT;
    }
    public void setIDOV_PLAT(Long val) {
        IDOV_PLAT = val; 
    }
    @Column(name="CPSEVDO",length = 30)
    public String getCPSEVDO() {
        return CPSEVDO;
    }
    public void setCPSEVDO(String val) {
        CPSEVDO = val; 
    }
    @Column(name="CINN",length = 13)
    public String getCINN() {
        return CINN;
    }
    public void setCINN(String val) {
        CINN = val; 
    }
    @Column(name="CACC",length = 25)
    public String getCACC() {
        return CACC;
    }
    public void setCACC(String val) {
        CACC = val; 
    }
    @Column(name="CNAMEOPER",length = 300)
    public String getCNAMEOPER() {
        return CNAMEOPER;
    }
    public void setCNAMEOPER(String val) {
        CNAMEOPER = val; 
    }

    @Override
    public Object getKey() {
        return getIDOV_PLAT();
    }

    @Override
    public Object getValue() {
        return getCNAMEOPER();
    }
}