package ru.inversionkavkaz.dnrwsadm.entity;

import java.math.BigDecimal;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;


/**
 * @author porche
 * @since 2020/12/07 15:19:05
 */
@Entity(name = "ru.inversionkavkaz.dnrwsadm.entity.PIkOvDnrSend")
@Table(name = "IK_OV_DNR_SEND")
public class PIkOvDnrSend extends IDMarkable implements Serializable {
    private static final String serialVersionUID = "07_12_2020_15_19_05";

    private Long I_ID;
    private LocalDateTime DATECREATE;
    private String SYSTEM_ID;
    private Long DEAL_ID;
    private String OPERTYPE;
    private BigDecimal SUM;
    private String ACCOUNT;
    private Long PAY_ID;
    private String TRADE_POINT;
    private Long ACCOUNT_KIND;
    private Long ATTEMPT_NUM;
    private LocalDateTime LAST_RUN_DATE;
    private Long LAST_RESULTCODE;
    private LocalDateTime LAST_RESULTDATE;
    private String LAST_RESULTMESSAGE;
    private Boolean IS_FINAL_RESULT;
    private LocalDateTime NEXT_RUN_DATE;
    private Boolean IS_ACTIVE;

    public PIkOvDnrSend() {
    }

    @Id
    @Column(name = "I_ID", nullable = false, length = 12)
    public Long getI_ID() {
        return I_ID;
    }

    public void setI_ID(Long val) {
        I_ID = val;
    }

    @Column(name = "DATECREATE", nullable = false)
    public LocalDateTime getDATECREATE() {
        return DATECREATE;
    }

    public void setDATECREATE(LocalDateTime val) {
        DATECREATE = val;
    }

    @Column(name = "SYSTEM_ID", nullable = false, length = 128)
    public String getSYSTEM_ID() {
        return SYSTEM_ID;
    }

    public void setSYSTEM_ID(String val) {
        SYSTEM_ID = val;
    }

    @Column(name = "DEAL_ID", nullable = false, length = 12)
    public Long getDEAL_ID() {
        return DEAL_ID;
    }

    public void setDEAL_ID(Long val) {
        DEAL_ID = val;
    }

    @Column(name = "OPERTYPE", nullable = false, length = 16)
    public String getOPERTYPE() {
        return OPERTYPE;
    }

    public void setOPERTYPE(String val) {
        OPERTYPE = val;
    }

    @Column(name = "SUM", nullable = false, length = 16)
    public BigDecimal getSUM() {
        return SUM;
    }

    public void setSUM(BigDecimal val) {
        SUM = val;
    }

    @Column(name = "ACCOUNT", length = 25)
    public String getACCOUNT() {
        return ACCOUNT;
    }

    public void setACCOUNT(String val) {
        ACCOUNT = val;
    }

    @Column(name = "PAY_ID", length = 12)
    public Long getPAY_ID() {
        return PAY_ID;
    }

    public void setPAY_ID(Long val) {
        PAY_ID = val;
    }

    @Column(name = "TRADE_POINT", length = 32)
    public String getTRADE_POINT() {
        return TRADE_POINT;
    }

    public void setTRADE_POINT(String val) {
        TRADE_POINT = val;
    }

    @Column(name = "ACCOUNT_KIND", length = 5)
    public Long getACCOUNT_KIND() {
        return ACCOUNT_KIND;
    }

    public void setACCOUNT_KIND(Long val) {
        ACCOUNT_KIND = val;
    }

    @Column(name = "ATTEMPT_NUM", nullable = false, length = 0)
    public Long getATTEMPT_NUM() {
        return ATTEMPT_NUM;
    }

    public void setATTEMPT_NUM(Long val) {
        ATTEMPT_NUM = val;
    }

    @Column(name = "LAST_RUN_DATE")
    public LocalDateTime getLAST_RUN_DATE() {
        return LAST_RUN_DATE;
    }

    public void setLAST_RUN_DATE(LocalDateTime val) {
        LAST_RUN_DATE = val;
    }

    @Column(name = "LAST_RESULTCODE", length = 0)
    public Long getLAST_RESULTCODE() {
        return LAST_RESULTCODE;
    }

    public void setLAST_RESULTCODE(Long val) {
        LAST_RESULTCODE = val;
    }

    @Column(name = "LAST_RESULTDATE")
    public LocalDateTime getLAST_RESULTDATE() {
        return LAST_RESULTDATE;
    }

    public void setLAST_RESULTDATE(LocalDateTime val) {
        LAST_RESULTDATE = val;
    }

    @Column(name = "LAST_RESULTMESSAGE", length = 4000)
    public String getLAST_RESULTMESSAGE() {
        return LAST_RESULTMESSAGE;
    }

    public void setLAST_RESULTMESSAGE(String val) {
        LAST_RESULTMESSAGE = val;
    }

    @Column(name = "IS_FINAL_RESULT", nullable = false, length = 0)
    public Boolean getIS_FINAL_RESULT() {
        return IS_FINAL_RESULT;
    }

    public void setIS_FINAL_RESULT(Boolean val) {
        IS_FINAL_RESULT = val;
    }

    @Column(name = "NEXT_RUN_DATE")
    public LocalDateTime getNEXT_RUN_DATE() {
        return NEXT_RUN_DATE;
    }

    public void setNEXT_RUN_DATE(LocalDateTime val) {
        NEXT_RUN_DATE = val;
    }

    @Column(name = "IS_ACTIVE", nullable = false, length = 0)
    public Boolean getIS_ACTIVE() {
        return IS_ACTIVE;
    }

    public void setIS_ACTIVE(Boolean val) {
        IS_ACTIVE = val;
    }

    @Transient
    @Override
    public Long getMarkLongID() {
        return getI_ID();
    }

    @Override
    public boolean isMark() {
        return super.isMark();
    }
}