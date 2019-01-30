package com.lti.dto;

import java.io.Serializable;

public class Employee implements Serializable {

	@Override
	public String toString() {
		return "Employee [ename=" + ename + ", esal=" + esal + "]";
	}

	private static int empno;
	private String ename;
	private double esal;

	public Employee() {

	}

	public Employee(int empno, String ename, double esal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.esal = esal;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getEsal() {
		return esal;
	}

	public void setEsal(double esal) {
		this.esal = esal;
	}

}
