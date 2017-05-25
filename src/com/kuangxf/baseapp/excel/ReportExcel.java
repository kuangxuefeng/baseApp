package com.kuangxf.baseapp.excel;

public class ReportExcel {
	private String fileName = "熔点测试报告.xls";
	private String sheetName = "熔点测试报告";
	private String title = "熔点测试报告";
	private String qiye;
	private String ypmc;
	private String czr;
	private String csTime;
	private String yswd;
	private String swsl;
	private String cr1;
	private String zr1;
	private String cr2;
	private String zr2;
	private String cr3;
	private String zr3;
	private String cr4;
	private String zr4;
	private String imagePath;//截图
	private String info;//备注
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQiye() {
		return qiye;
	}
	public void setQiye(String qiye) {
		this.qiye = qiye;
	}
	public String getYpmc() {
		return ypmc;
	}
	public void setYpmc(String ypmc) {
		this.ypmc = ypmc;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public String getCsTime() {
		return csTime;
	}
	public void setCsTime(String csTime) {
		this.csTime = csTime;
	}
	public String getYswd() {
		return yswd;
	}
	public void setYswd(String yswd) {
		this.yswd = yswd;
	}
	public String getSwsl() {
		return swsl;
	}
	public void setSwsl(String swsl) {
		this.swsl = swsl;
	}
	public String getCr1() {
		return cr1;
	}
	public void setCr1(String cr1) {
		this.cr1 = cr1;
	}
	public String getZr1() {
		return zr1;
	}
	public void setZr1(String zr1) {
		this.zr1 = zr1;
	}
	public String getCr2() {
		return cr2;
	}
	public void setCr2(String cr2) {
		this.cr2 = cr2;
	}
	public String getZr2() {
		return zr2;
	}
	public void setZr2(String zr2) {
		this.zr2 = zr2;
	}
	public String getCr3() {
		return cr3;
	}
	public void setCr3(String cr3) {
		this.cr3 = cr3;
	}
	public String getZr3() {
		return zr3;
	}
	public void setZr3(String zr3) {
		this.zr3 = zr3;
	}
	public String getCr4() {
		return cr4;
	}
	public void setCr4(String cr4) {
		this.cr4 = cr4;
	}
	public String getZr4() {
		return zr4;
	}
	public void setZr4(String zr4) {
		this.zr4 = zr4;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "ReportExcel [fileName=" + fileName + ", sheetName=" + sheetName
				+ ", title=" + title + ", qiye=" + qiye + ", ypmc=" + ypmc
				+ ", czr=" + czr + ", csTime=" + csTime + ", yswd=" + yswd
				+ ", swsl=" + swsl + ", cr1=" + cr1 + ", zr1=" + zr1 + ", cr2="
				+ cr2 + ", zr2=" + zr2 + ", cr3=" + cr3 + ", zr3=" + zr3
				+ ", cr4=" + cr4 + ", zr4=" + zr4 + ", imagePath=" + imagePath
				+ ", info=" + info + "]";
	}
	
}
