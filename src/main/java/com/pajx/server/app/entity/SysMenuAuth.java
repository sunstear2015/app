package com.pajx.server.app.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统菜单实体对象
 * SYS_MENU_AUTH
 * @author:	taller &　azzcsimp(改版)
 * @Createdate:	2014年7月20日 下午8:28:06
 */
public class SysMenuAuth implements Serializable {

	private static final long serialVersionUID = -1356946938712765006L;
	
	/** 菜单编号 */
	private String menuId;
	
	/** 系统名称字典编码 */
	private String sysNameDc;
	
	/** 菜单类型（超链接菜单不在主菜单中显示），0-页面内超链接，1-主菜单 */
	private int menuType = -1;
	
	/** 菜单名称 */
	private String menuName;
	
	/** 菜单样式 */
	private String menuCSS;
	
	/** 菜单图标 */
	private String menuIcon;
	
	/** 菜单URL */
	private String menuUrl;
	
	/** 菜单描述（鼠标hint帮助） */
	private String menuDesc;
	
	/** 上级菜单编码 2014-08-05 菜单修改注释
	private String parentMenuCode;
	 */

	/** 菜单排列顺序，用于控制显示顺序 */
	private int menuOrder = 1000;
	
	/** 菜单状态编码，0-停用，1-正常 */
	private int menuStatusFlag = -1;
	
	/** 菜单target属性 */
	private String menuTarget;
	
	/** 菜单rel属性 */
	private String menuRel;
	
	/** 上级菜单信息 */
	private SysMenuAuth parent;
	
	/** 下级菜单信息 */
	private Set<SysMenuAuth> children = new HashSet<SysMenuAuth>();
	
	
	@Override
	public boolean equals(Object obj) {
		if(null == obj) return false;
		if(!(obj instanceof SysMenuAuth)) return false;
		else{
			SysMenuAuth menu = (SysMenuAuth)obj;
			if(null == this.getMenuId() || null == menu.getMenuId()) return false;
			else return (this.getMenuId().equals(menu.getMenuId()));
		}
	}
	
	// =================get/set============================

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getSysNameDc() {
		return sysNameDc;
	}

	public void setSysNameDc(String sysNameDc) {
		this.sysNameDc = sysNameDc;
	}

	public int getMenuType() {
		return menuType;
	}

	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}

	public String getMenuCSS() {
		return menuCSS;
	}

	public void setMenuCSS(String menuCSS) {
		this.menuCSS = menuCSS;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	/** 上级菜单编码 2014-08-05 菜单修改注释
	public String getParentMenuCode() {
		return parentMenuCode;
	}

	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
	}
	*/

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public int getMenuStatusFlag() {
		return menuStatusFlag;
	}

	public void setMenuStatusFlag(int menuStatusFlag) {
		this.menuStatusFlag = menuStatusFlag;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuTarget() {
		return menuTarget;
	}

	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}

	public String getMenuRel() {
		return menuRel;
	}

	public void setMenuRel(String menuRel) {
		this.menuRel = menuRel;
	}

	public SysMenuAuth getParent() {
		return parent;
	}

	public void setParent(SysMenuAuth parent) {
		this.parent = parent;
	}

	public Set<SysMenuAuth> getChildren() {
		return children;
	}

	public void setChildren(Set<SysMenuAuth> children) {
		this.children = children;
	}
	
	
}
