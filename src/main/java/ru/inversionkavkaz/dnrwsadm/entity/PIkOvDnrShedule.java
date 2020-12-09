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
@since   2020/11/30 14:53:40
*/
@Entity (name="ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrShedule")
@Table (name="IK_OV_DNR_SHEDULE")
public class PIkOvDnrShedule implements Serializable
{
    private static final long serialVersionUID = 30_11_2020_14_53_40l;

    private Long ATTEMPT;
    private Long SHIFT_MINUTES;

    public PIkOvDnrShedule(){}

    @Id 
    @Column(name="ATTEMPT",nullable = false,length = 0)
    public Long getATTEMPT() {
        return ATTEMPT;
    }
    public void setATTEMPT(Long val) {
        ATTEMPT = val; 
    }
    @Column(name="SHIFT_MINUTES",nullable = false,length = 0)
    public Long getSHIFT_MINUTES() {
        return SHIFT_MINUTES;
    }
    public void setSHIFT_MINUTES(Long val) {
        SHIFT_MINUTES = val; 
    }
}