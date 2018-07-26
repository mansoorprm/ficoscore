package com.mufg.ficoscorecheckservice.model;

import java.util.Date;

public class FicoscoreCheckModel {

	private String ssn;

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getFscore() {
		return fscore;
	}

	public void setFscore(int fscore) {
		this.fscore = fscore;
	}

	public Date getVfdate() {
		return vfdate;
	}

	public void setVfdate(Date vfdate) {
		this.vfdate = vfdate;
	}

	public Date getVtdate() {
		return vtdate;
	}

	public void setVtdate(Date vtdate) {
		this.vtdate = vtdate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	private int fscore;
	private Date vfdate;
	private Date vtdate;
	private boolean status;

}
