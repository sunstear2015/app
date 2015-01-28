package com.pajx.server.app.entity;

import java.io.Serializable;

/**
 * 外部用户信息实体对象
 * SYS_OUTSIDE_USER
 * @author:	azzcsimp
 * @Createdate:	2014年7月21日 下午9:39:53
 */
public class SysOutsideUser implements Serializable {

	private static final long serialVersionUID = 8977690007436227002L;

	/** 外部用户编号 */
	private String osuId;

	/** 用户类型，0-学校管理员，1-移动客户经理 */
	private int osuType = -1;

	/** 姓名 */
	private String osuName;

	/** 性别，0-未知，1-男，2-女 */
	private int osuSex = 0;

	/** 手机号 */
	private String osuPhone;

	/** 电子邮件 */
	private String osuEmail;

	/** 登录用户名 */
	private String account;

	/** 登录密码（加密） */
	private String password;

	/** 最后一次登录IP */
	private String lastLoginIp;

	/** 最后一次登录时间，YYYYMMDDHHMMSS */
	private String lastLoginTime;

	/** 信息状态标识，0-停用，1-正常 */
	private int osuStatusFlag = -1;

	/** 备注信息 */
	private String remark;

	/** 创建时间，YYYYMMDDHHMMSS */
	private String createTime;

	/** 创建人员ID */
	private String createOperId;

	/** 角色编码 */
	private String osuRoleCode;
	
	/** 地区编码 */
	private String areaCode;
	//==============冗余字段=======================
	
	/**
	 * 区县地区编码
	 */
	private String areaChildcode;

	public String getOsuId() {
		return osuId;
	}

	public void setOsuId(String osuId) {
		this.osuId = osuId;
	}

	public int getOsuType() {
		return osuType;
	}

	public void setOsuType(int osuType) {
		this.osuType = osuType;
	}

	public String getOsuName() {
		return osuName;
	}

	public void setOsuName(String osuName) {
		this.osuName = osuName;
	}

	public String getOsuPhone() {
		return osuPhone;
	}

	public void setOsuPhone(String osuPhone) {
		this.osuPhone = osuPhone;
	}

	public String getOsuEmail() {
		return osuEmail;
	}

	public void setOsuEmail(String osuEmail) {
		this.osuEmail = osuEmail;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getOsuStatusFlag() {
		return osuStatusFlag;
	}

	public void setOsuStatusFlag(int osuStatusFlag) {
		this.osuStatusFlag = osuStatusFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateOperId() {
		return createOperId;
	}

	public void setCreateOperId(String createOperId) {
		this.createOperId = createOperId;
	}

	public String getOsuRoleCode() {
		return osuRoleCode;
	}

	public void setOsuRoleCode(String osuRoleCode) {
		this.osuRoleCode = osuRoleCode;
	}

	public int getOsuSex() {
		return osuSex;
	}

	public void setOsuSex(int osuSex) {
		this.osuSex = osuSex;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaChildcode() {
		return areaChildcode;
	}

	public void setAreaChildcode(String areaChildcode) {
		this.areaChildcode = areaChildcode;
	}

	
}
