package com.pro.code.plugin.vo;

/**
 * [虚拟列]VO类, 表名[PRO_VIRTUAL_COLUMNS].
 */
public class VirtualColumnsVO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String virtualColumnsId; // 虚拟列编号

  protected java.lang.String sourceDtId; // 源表数据库表编号

  protected java.lang.String targetDtId; // 目标表数据库表编号

  protected java.lang.String sourceColumnsId; // 源表列编号

  protected java.lang.String targetColumnsId; // 目标表列编号

  protected java.lang.String targetDisplayColumnsId; // 目标表显示字段列编号

  protected java.lang.String displayColumnsAlias; // 显示字段别名

  protected java.lang.String virtualColumnsSql; // 虚拟列sql

  protected java.lang.Integer weightOrder; // 排序权重

  protected java.lang.String dtName; // 表名

  protected java.lang.String columnName; // 列名

  public java.lang.String getVirtualColumnsId() {
    return virtualColumnsId;
  }

  public void setVirtualColumnsId(java.lang.String virtualColumnsId) {
    this.virtualColumnsId = virtualColumnsId;
  }

  public java.lang.String getSourceDtId() {
    return sourceDtId;
  }

  public void setSourceDtId(java.lang.String sourceDtId) {
    this.sourceDtId = sourceDtId;
  }

  public java.lang.String getTargetDtId() {
    return targetDtId;
  }

  public void setTargetDtId(java.lang.String targetDtId) {
    this.targetDtId = targetDtId;
  }

  public java.lang.String getSourceColumnsId() {
    return sourceColumnsId;
  }

  public void setSourceColumnsId(java.lang.String sourceColumnsId) {
    this.sourceColumnsId = sourceColumnsId;
  }

  public java.lang.String getTargetColumnsId() {
    return targetColumnsId;
  }

  public void setTargetColumnsId(java.lang.String targetColumnsId) {
    this.targetColumnsId = targetColumnsId;
  }

  public java.lang.String getTargetDisplayColumnsId() {
    return targetDisplayColumnsId;
  }

  public void setTargetDisplayColumnsId(java.lang.String targetDisplayColumnsId) {
    this.targetDisplayColumnsId = targetDisplayColumnsId;
  }

  public java.lang.String getDisplayColumnsAlias() {
    return displayColumnsAlias;
  }

  public void setDisplayColumnsAlias(java.lang.String displayColumnsAlias) {
    this.displayColumnsAlias = displayColumnsAlias;
  }

  public java.lang.String getVirtualColumnsSql() {
    return virtualColumnsSql;
  }

  public void setVirtualColumnsSql(java.lang.String virtualColumnsSql) {
    this.virtualColumnsSql = virtualColumnsSql;
  }

  public java.lang.Integer getWeightOrder() {
    return weightOrder;
  }

  public void setWeightOrder(java.lang.Integer weightOrder) {
    this.weightOrder = weightOrder;
  }

  public java.lang.String getDtName() {
    return dtName;
  }

  public void setDtName(java.lang.String dtName) {
    this.dtName = dtName;
  }

  public java.lang.String getColumnName() {
    return columnName;
  }

  public void setColumnName(java.lang.String columnName) {
    this.columnName = columnName;
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
