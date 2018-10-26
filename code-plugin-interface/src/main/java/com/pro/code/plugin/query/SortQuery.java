package com.pro.code.plugin.query;

/**
 * [排序]查询类, 表名[PRO_SORT].
 */
public class SortQuery implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String sortId; // 排序编号

  protected java.lang.String sortRule; // 排序规则

  protected java.lang.String dtId; // 数据库表编号

  protected java.lang.String columnsId; // 列编号

  protected java.util.List<java.lang.String> sortIdAndin; // 排序编号Andin查询

  protected java.lang.String dtIdAndeq; // 数据库表编号Andeq查询

  protected java.lang.String columnsIdAndeq; // 列编号Andeq查询

  protected java.lang.String sortRuleAndKeyLike; // 排序规则AndKeyLike查询

  protected java.lang.String dtIdOrKeyLike; // 数据库表编号OrKeyLike查询

  protected java.lang.String columnsIdOrKeyLike; // 列编号OrKeyLike查询

  public java.lang.String getSortId() {
    return sortId;
  }

  public void setSortId(java.lang.String sortId) {
    this.sortId = sortId;
  }

  public java.lang.String getSortRule() {
    return sortRule;
  }

  public void setSortRule(java.lang.String sortRule) {
    this.sortRule = sortRule;
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

  public java.util.List<java.lang.String> getSortIdAndin() {
    return sortIdAndin;
  }

  public void setSortIdAndin(java.util.List<java.lang.String> sortIdAndin) {
    this.sortIdAndin = sortIdAndin;
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

  public java.lang.String getSortRuleAndKeyLike() {
    return sortRuleAndKeyLike;
  }

  public void setSortRuleAndKeyLike(java.lang.String sortRuleAndKeyLike) {
    this.sortRuleAndKeyLike = sortRuleAndKeyLike;
  }

  public java.lang.String getDtIdOrKeyLike() {
    return dtIdOrKeyLike;
  }

  public void setDtIdOrKeyLike(java.lang.String dtIdOrKeyLike) {
    this.dtIdOrKeyLike = dtIdOrKeyLike;
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
