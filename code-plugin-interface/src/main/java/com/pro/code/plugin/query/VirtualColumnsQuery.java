package com.pro.code.plugin.query;

/**
 * [虚拟列]查询类, 表名[PRO_VIRTUAL_COLUMNS].
 */
public class VirtualColumnsQuery implements java.io.Serializable {

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

  protected java.util.List<java.lang.String> virtualColumnsIdAndin; // 虚拟列编号Andin查询

  protected java.lang.String sourceDtIdAndKeyLike; // 源表数据库表编号AndKeyLike查询

  protected java.lang.String targetDtIdOrKeyLike; // 目标表数据库表编号OrKeyLike查询

  protected java.lang.String sourceColumnsIdOrKeyLike; // 源表列编号OrKeyLike查询

  protected java.lang.String targetColumnsIdOrKeyLike; // 目标表列编号OrKeyLike查询

  protected java.lang.String targetDisplayColumnsIdOrKeyLike; // 目标表显示字段列编号OrKeyLike查询

  protected java.lang.String displayColumnsAliasOrKeyLike; // 显示字段别名OrKeyLike查询

  protected java.lang.String virtualColumnsSqlOrKeyLike; // 虚拟列sqlOrKeyLike查询

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

  public java.util.List<java.lang.String> getVirtualColumnsIdAndin() {
    return virtualColumnsIdAndin;
  }

  public void setVirtualColumnsIdAndin(java.util.List<java.lang.String> virtualColumnsIdAndin) {
    this.virtualColumnsIdAndin = virtualColumnsIdAndin;
  }

  public java.lang.String getSourceDtIdAndKeyLike() {
    return sourceDtIdAndKeyLike;
  }

  public void setSourceDtIdAndKeyLike(java.lang.String sourceDtIdAndKeyLike) {
    this.sourceDtIdAndKeyLike = sourceDtIdAndKeyLike;
  }

  public java.lang.String getTargetDtIdOrKeyLike() {
    return targetDtIdOrKeyLike;
  }

  public void setTargetDtIdOrKeyLike(java.lang.String targetDtIdOrKeyLike) {
    this.targetDtIdOrKeyLike = targetDtIdOrKeyLike;
  }

  public java.lang.String getSourceColumnsIdOrKeyLike() {
    return sourceColumnsIdOrKeyLike;
  }

  public void setSourceColumnsIdOrKeyLike(java.lang.String sourceColumnsIdOrKeyLike) {
    this.sourceColumnsIdOrKeyLike = sourceColumnsIdOrKeyLike;
  }

  public java.lang.String getTargetColumnsIdOrKeyLike() {
    return targetColumnsIdOrKeyLike;
  }

  public void setTargetColumnsIdOrKeyLike(java.lang.String targetColumnsIdOrKeyLike) {
    this.targetColumnsIdOrKeyLike = targetColumnsIdOrKeyLike;
  }

  public java.lang.String getTargetDisplayColumnsIdOrKeyLike() {
    return targetDisplayColumnsIdOrKeyLike;
  }

  public void setTargetDisplayColumnsIdOrKeyLike(java.lang.String targetDisplayColumnsIdOrKeyLike) {
    this.targetDisplayColumnsIdOrKeyLike = targetDisplayColumnsIdOrKeyLike;
  }

  public java.lang.String getDisplayColumnsAliasOrKeyLike() {
    return displayColumnsAliasOrKeyLike;
  }

  public void setDisplayColumnsAliasOrKeyLike(java.lang.String displayColumnsAliasOrKeyLike) {
    this.displayColumnsAliasOrKeyLike = displayColumnsAliasOrKeyLike;
  }

  public java.lang.String getVirtualColumnsSqlOrKeyLike() {
    return virtualColumnsSqlOrKeyLike;
  }

  public void setVirtualColumnsSqlOrKeyLike(java.lang.String virtualColumnsSqlOrKeyLike) {
    this.virtualColumnsSqlOrKeyLike = virtualColumnsSqlOrKeyLike;
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
