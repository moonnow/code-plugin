package com.pro.code.plugin.vo;

/**
 * [数据库表]VO类, 表名[PRO_DT].
 */
public class DtVO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String dtId; // 数据库表编号

  protected java.lang.String dtSql; // 数据库表sql

  protected java.lang.String dtName; // 表名

  protected java.lang.String dtNameAnnotation; // 表名注释

  protected java.lang.String dtPrefix; // 数据库表前缀

  protected java.lang.String initialCaseEntityName; // 首字母大写实体类名

  protected java.lang.String initialLowercaseEntityName; // 首字母小写实体类名

  protected java.lang.String proPath; // 项目路径

  protected java.lang.String proAllName; // 项目全称

  public java.lang.String getDtId() {
    return dtId;
  }

  public void setDtId(java.lang.String dtId) {
    this.dtId = dtId;
  }

  public java.lang.String getDtSql() {
    return dtSql;
  }

  public void setDtSql(java.lang.String dtSql) {
    this.dtSql = dtSql;
  }

  public java.lang.String getDtName() {
    return dtName;
  }

  public void setDtName(java.lang.String dtName) {
    this.dtName = dtName;
  }

  public java.lang.String getDtNameAnnotation() {
    return dtNameAnnotation;
  }

  public void setDtNameAnnotation(java.lang.String dtNameAnnotation) {
    this.dtNameAnnotation = dtNameAnnotation;
  }

  public java.lang.String getDtPrefix() {
    return dtPrefix;
  }

  public void setDtPrefix(java.lang.String dtPrefix) {
    this.dtPrefix = dtPrefix;
  }

  public java.lang.String getInitialCaseEntityName() {
    return initialCaseEntityName;
  }

  public void setInitialCaseEntityName(java.lang.String initialCaseEntityName) {
    this.initialCaseEntityName = initialCaseEntityName;
  }

  public java.lang.String getInitialLowercaseEntityName() {
    return initialLowercaseEntityName;
  }

  public void setInitialLowercaseEntityName(java.lang.String initialLowercaseEntityName) {
    this.initialLowercaseEntityName = initialLowercaseEntityName;
  }

  public java.lang.String getProPath() {
    return proPath;
  }

  public void setProPath(java.lang.String proPath) {
    this.proPath = proPath;
  }

  public java.lang.String getProAllName() {
    return proAllName;
  }

  public void setProAllName(java.lang.String proAllName) {
    this.proAllName = proAllName;
  }

  @Override
  public String toString() {
    return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
  }

  @Override
  public boolean equals(Object object) {
    return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(this, object);
  }

  @Override
  public int hashCode() {
    return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(this);
  }

}
