package com.pajx.server.app.entity;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 内部用户信息实体对象
 * SYS_INSIDE_USER
 * @author:	taller
 * @Createdate:	2014年7月21日 上午8:46:48
 */
//@Entity(name="SYS_INSIDE_USER")
public class SysInsideUser implements Serializable{

	private static final long serialVersionUID = 3788903106431595475L;
	
	/** 内部用户编号 */
	private String isuId;
	
	/** 用户组编码 */
	private String isuGroupCode;
	
	/** 角色编码 */
	private String isuRoleCode;
	
	/** 部门编码 */
	private String deptCode;
	
	/** 用户姓名 */
	private String isuName;
	
	/** 性别 0-未知，1-男，2-女 */
	private int isuSex = 0;
	
	/** 手机号 */
	private String isuPhone;
	
	/** 手机唯一串号 */
	private String isuIMEI;
	
	/** 电子邮件 */
	private String isuEmail;
	
	/** 登录用户名 */
	private String account;
	
	/** 登录密码 */
	private String password;
	
	/** 最后一次登录IP */
	private String lastLoginIp;
	
	/** 最后一次登录时间yyyyMMddHHmmss */
	private String lastLoginTime;
	
	/** 用户状态标识 0-停用，1-正常 */
	private int isuStatusFlag = 0;
	
	/** 备注信息 */
	private String remark;
	
	/** 创建时间 */
	private String createTime;
	
	/** 创建人员id */
	private String createOperId;
	
	/** 用户角色，主要处理在没有关联关系中，不实用session保存用户角色对象 */
	private SysUserRole userRole;
	/** 是否大区经理 */
	private String managerFlag;
	
	public boolean isAdmin(){
		return "SuperAdmin".equals(account);
	}
	
	public boolean isDebug(){
		if("SuperAdmin".equals(account)) return true;
		if("admin".equals(account)) return true;
		if("18203677345".equals(account)) return true;
		return false;
	}
	
	// =================================================

	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(name="ISU_ID")
	public String getIsuId() {
		return isuId;
	}

	public void setIsuId(String isuId) {
		this.isuId = isuId;
	}
	@Column(name = "ISU_GROUP_CODE")
	public String getIsuGroupCode() {
		return isuGroupCode;
	}

	public void setIsuGroupCode(String isuGroupCode) {
		this.isuGroupCode = isuGroupCode;
	}
	@Column(name = "")
	public String getIsuRoleCode() {
		return isuRoleCode;
	}

	public void setIsuRoleCode(String isuRoleCode) {
		this.isuRoleCode = isuRoleCode;
	}
	@Column(name = "")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	@Column(name = "")
	public String getIsuName() {
		return isuName;
	}

	public void setIsuName(String isuName) {
		this.isuName = isuName;
	}
	@Column(name = "")
	public int getIsuSex() {
		return isuSex;
	}

	public void setIsuSex(int isuSex) {
		this.isuSex = isuSex;
	}
	@Column(name = "")
	public String getIsuPhone() {
		return isuPhone;
	}

	public void setIsuPhone(String isuPhone) {
		this.isuPhone = isuPhone;
	}
	@Column(name = "")
	public String getIsuIMEI() {
		return isuIMEI;
	}

	public void setIsuIMEI(String isuIMEI) {
		this.isuIMEI = isuIMEI;
	}
	@Column(name = "")
	public String getIsuEmail() {
		return isuEmail;
	}

	public void setIsuEmail(String isuEmail) {
		this.isuEmail = isuEmail;
	}
	@Column(name = "")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	@Column(name = "")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "")
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	@Column(name = "")
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Column(name = "")
	public int getIsuStatusFlag() {
		return isuStatusFlag;
	}

	public void setIsuStatusFlag(int isuStatusFlag) {
		this.isuStatusFlag = isuStatusFlag;
	}
	@Column(name = "")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "")
	public String getCreateOperId() {
		return createOperId;
	}

	public void setCreateOperId(String createOperId) {
		this.createOperId = createOperId;
	}

	public SysUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(SysUserRole userRole) {
		this.userRole = userRole;
	}

	public String getManagerFlag() {
		return managerFlag;
	}

	public void setManagerFlag(String managerFlag) {
		this.managerFlag = managerFlag;
	}
	
	
	
	
}
