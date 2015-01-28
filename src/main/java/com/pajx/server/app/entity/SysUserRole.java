package com.pajx.server.app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统用户角色信息实体类
 * SYS_USER_ROLE
 * @author:	azzcsimp
 * @Createdate:	2014年7月17日 下午7:22:53
 */
public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 479611942399194584L;
	
	/** 角色编号 */
	private String usrId;
	
	/** 角色名称 */
	private String usrName;
	
	/** 角色编码 */
	private String usrCode;
	
	/** 数据权限，0-全部数据，1-部门数据，2-个人数据 */
	private int usrDataRange;
	
	/** 用户组信息 */
	private SysUserGroup sysUserGroup;
	
	/** 角色拥有的菜单集合 */
	private Set<SysMenuAuth> menuAuths = new HashSet<SysMenuAuth>();
	
	// =======================================
	
	/**
	 * 判断本角色是否有指定名称的权限
	 * @param privilegeName
	 * @return   
	 * @author:	azzcsimp
	 * @Createdate:	2014年7月31日 下午4:03:58
	 */
	public boolean hasPrivilegeByName(String privilegeName){
		for(SysMenuAuth menuAuth : this.getMenuAuths()){
			if(menuAuth.getMenuName().equals(privilegeName)){
				return true;
			}
		}
		return false;
	}
	// =======================================

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrCode() {
		return usrCode;
	}

	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}

	public int getUsrDataRange() {
		return usrDataRange;
	}

	public void setUsrDataRange(int usrDataRange) {
		this.usrDataRange = usrDataRange;
	}

	public SysUserGroup getSysUserGroup() {
		return sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}

	public Set<SysMenuAuth> getMenuAuths() {
		return menuAuths;
	}

	public void setMenuAuths(Set<SysMenuAuth> menuAuths) {
		this.menuAuths = menuAuths;
	}

}
