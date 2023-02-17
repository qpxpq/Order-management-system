/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.entity;

/**
 *
 * @author Administrator
 */
public class Kind {
    private String kindNo;
    private String kindName;
    
    public Kind() {
    }

	public Kind(String kindNo, String kindName) {
		super();
		this.kindNo = kindNo;
		this.kindName = kindName;
	}

	public String getkindNo() {//Clazz   Kind?????
		return kindNo;
	}

	public void setKindNo(String kindNo) {
		this.kindNo = kindNo;
	}

	public String getkindName() {
		return kindName;
	}

	public void setkindName(String kindName) {
		this.kindName = kindName;
	}

	@Override
	public String toString() {
		return "kind [kindNo=" + kindNo + ", kindName=" + kindName + "]";
	}

    
    
    
    
}
