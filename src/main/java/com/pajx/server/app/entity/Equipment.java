package com.pajx.server.app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by admin on 2015/2/3.
 */
@Entity
@Table(name = "HS_EQUIPMENT_INFO",schema = "HOME_SCHOOL")
public class Equipment implements Serializable {
    private String SCL_ID;
    private Integer EQU_NO;
    private String EQU_TYPE = "0";
    private String EQU_NAME_DC;
    private String EQU_SIP;
    private String EQU_SPORT;
    private String EQU_APN_SIM;
    private String EQU_SOFT_VER;
    private String EQU_HARD_VER;
    private String EQU_START_DATE;
    private String EQU_ATT_TYPE = "0";
    private String EQU_ATT_INOUT_TYPE = "0";
    private String EQU_DESCRIP;
    private String EQU_LAST_TIME;
    private String EQU_STATUS_FLAG = "1";
    private String REMARK;
    private String CREATE_TIME;
    private String CREATE_OPER_ID;
    private String EQU_ID;
    public String getSCL_ID() {
        return SCL_ID;
    }

    public void setSCL_ID(String SCL_ID) {
        this.SCL_ID = SCL_ID;
    }

    public Integer getEQU_NO() {
        return EQU_NO;
    }

    public void setEQU_NO(Integer EQU_NO) {
        this.EQU_NO = EQU_NO;
    }

    public String getEQU_TYPE() {
        return EQU_TYPE;
    }

    public void setEQU_TYPE(String EQU_TYPE) {
        this.EQU_TYPE = EQU_TYPE;
    }

    public String getEQU_NAME_DC() {
        return EQU_NAME_DC;
    }

    public void setEQU_NAME_DC(String EQU_NAME_DC) {
        this.EQU_NAME_DC = EQU_NAME_DC;
    }

    public String getEQU_SIP() {
        return EQU_SIP;
    }

    public void setEQU_SIP(String EQU_SIP) {
        this.EQU_SIP = EQU_SIP;
    }

    public String getEQU_SPORT() {
        return EQU_SPORT;
    }

    public void setEQU_SPORT(String EQU_SPORT) {
        this.EQU_SPORT = EQU_SPORT;
    }

    public String getEQU_APN_SIM() {
        return EQU_APN_SIM;
    }

    public void setEQU_APN_SIM(String EQU_APN_SIM) {
        this.EQU_APN_SIM = EQU_APN_SIM;
    }

    public String getEQU_SOFT_VER() {
        return EQU_SOFT_VER;
    }

    public void setEQU_SOFT_VER(String EQU_SOFT_VER) {
        this.EQU_SOFT_VER = EQU_SOFT_VER;
    }

    public String getEQU_HARD_VER() {
        return EQU_HARD_VER;
    }

    public void setEQU_HARD_VER(String EQU_HARD_VER) {
        this.EQU_HARD_VER = EQU_HARD_VER;
    }

    public String getEQU_START_DATE() {
        return EQU_START_DATE;
    }

    public void setEQU_START_DATE(String EQU_START_DATE) {
        this.EQU_START_DATE = EQU_START_DATE;
    }

    public String getEQU_ATT_TYPE() {
        return EQU_ATT_TYPE;
    }

    public void setEQU_ATT_TYPE(String EQU_ATT_TYPE) {
        this.EQU_ATT_TYPE = EQU_ATT_TYPE;
    }

    public String getEQU_ATT_INOUT_TYPE() {
        return EQU_ATT_INOUT_TYPE;
    }

    public void setEQU_ATT_INOUT_TYPE(String EQU_ATT_INOUT_TYPE) {
        this.EQU_ATT_INOUT_TYPE = EQU_ATT_INOUT_TYPE;
    }

    public String getEQU_DESCRIP() {
        return EQU_DESCRIP;
    }

    public void setEQU_DESCRIP(String EQU_DESCRIP) {
        this.EQU_DESCRIP = EQU_DESCRIP;
    }

    public String getEQU_LAST_TIME() {
        return EQU_LAST_TIME;
    }

    public void setEQU_LAST_TIME(String EQU_LAST_TIME) {
        this.EQU_LAST_TIME = EQU_LAST_TIME;
    }

    public String getEQU_STATUS_FLAG() {
        return EQU_STATUS_FLAG;
    }

    public void setEQU_STATUS_FLAG(String EQU_STATUS_FLAG) {
        this.EQU_STATUS_FLAG = EQU_STATUS_FLAG;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getCREATE_OPER_ID() {
        return CREATE_OPER_ID;
    }

    public void setCREATE_OPER_ID(String CREATE_OPER_ID) {
        this.CREATE_OPER_ID = CREATE_OPER_ID;
    }
    @Id
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    public String getEQU_ID() {
        return EQU_ID;
    }

    public void setEQU_ID(String EQU_ID) {
        this.EQU_ID = EQU_ID;
    }
}
