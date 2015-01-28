package com.pajx.server.app.entity;

/**
 * 系统用户组信息实体类
 * SYS_USER_GROUP
 * @author:	azzcsimp
 * @Createdate:	2014年7月17日 下午7:06:22
 */
public class SysUserGroup implements java.io.Serializable {

	private static final long serialVersionUID = -7093009595268368883L;
	
	private int hashCode = Integer.MIN_VALUE;

	// ================================

	/** 用户组编号 */
	private String usgId;

	/** 用户组名称 */
	private String usgName;

	/** 用户组编码 */
	private String usgCode;

	// =================================

	public SysUserGroup() {

	}

	public SysUserGroup(String usgName, String usgCode) {
		this.usgName = usgName;
		this.usgCode = usgCode;
	}
	
	// =================================

	public String getUsgId() {
		return usgId;
	}

	public void setUsgId(String usgId) {
		this.usgId = usgId;
	}

	public String getUsgName() {
		return usgName;
	}

	public void setUsgName(String usgName) {
		this.usgName = usgName;
	}

	public String getUsgCode() {
		return usgCode;
	}

	public void setUsgCode(String usgCode) {
		this.usgCode = usgCode;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(null == obj) return false;
		if(!(obj instanceof SysUserGroup)) return false;
		else{
			SysUserGroup sysUserGroup = (SysUserGroup)obj;
			if(null == this.getUsgId() || null == sysUserGroup.getUsgId()) return false;
			else return (this.getUsgId().equals(sysUserGroup.getUsgId()));
		}
	}
	
	@Override
	public int hashCode() {
		if(Integer.MIN_VALUE == this.hashCode){
			if(null == this.getUsgId()) return super.hashCode();
			else{
				String hashStr = this.getClass().getName() + ":" + this.getUsgId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
