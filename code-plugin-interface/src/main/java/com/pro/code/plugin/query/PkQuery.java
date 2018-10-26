package com.pro.code.plugin.query;

/**
 * [主键]查询类, 表名[PRO_PK].
 */
public class PkQuery implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String pkId; // 主键编号

  protected java.lang.String dtId; // 数据库表编号

  protected java.lang.String columnsId; // 列编号

  protected java.util.List<java.lang.String> pkIdAndin; // 主键编号Andin查询

  protected java.lang.String dtIdAndeq; // 数据库表编号Andeq查询

  protected java.lang.String columnsIdAndeq; // 列编号Andeq查询

  protected java.lang.String dtIdAndKeyLike; // 数据库表编号AndKeyLike查询

  protected java.lang.String columnsIdOrKeyLike; // 列编号OrKeyLike查询

  public java.lang.String getPkId() {
    return pkId;
  }

  public void setPkId(java.lang.String pkId) {
    this.pkId = pkId;
  }

  public java.lang.String getDtId() {
    return dtId;
  }

  public void setDtId(java.lang.String dtId) {
    this.dtId = dtId;
  }

  public java.lang.String getColumnsId() {
    return columnsId;
  }

  public void setColumnsId(java.lang.String columnsId) {
    this.columnsId = columnsId;
  }

  public java.util.List<java.lang.String> getPkIdAndin() {
    return pkIdAndin;
  }

  public void setPkIdAndin(java.util.List<java.lang.String> pkIdAndin) {
    this.pkIdAndin = pkIdAndin;
  }

  public java.lang.String getDtIdAndeq() {
    return dtIdAndeq;
  }

  public void setDtIdAndeq(java.lang.String dtIdAndeq) {
    this.dtIdAndeq = dtIdAndeq;
  }

  public java.lang.String getColumnsIdAndeq() {
    return columnsIdAndeq;
  }

  public void setColumnsIdAndeq(java.lang.String columnsIdAndeq) {
    this.columnsIdAndeq = columnsIdAndeq;
  }

  public java.lang.String getDtIdAndKeyLike() {
    return dtIdAndKeyLike;
  }

  public void setDtIdAndKeyLike(java.lang.String dtIdAndKeyLike) {
    this.dtIdAndKeyLike = dtIdAndKeyLike;
  }

  public java.lang.String getColumnsIdOrKeyLike() {
    return columnsIdOrKeyLike;
  }

  public void setColumnsIdOrKeyLike(java.lang.String columnsIdOrKeyLike) {
    this.columnsIdOrKeyLike = columnsIdOrKeyLike;
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
