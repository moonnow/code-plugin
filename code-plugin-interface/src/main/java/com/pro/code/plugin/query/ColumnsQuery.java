package com.pro.code.plugin.query;

/**
 * [列]查询类, 表名[PRO_COLUMNS].
 */
public class ColumnsQuery implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  protected java.lang.String columnsId; // 列编号

  protected java.lang.String columnName; // 列名

  protected java.lang.String columnNameAnnotation; // 列名注释

  protected java.lang.String dataType; // 数据类型

  protected java.lang.String isNull; // 是否为空

  protected java.lang.String initialCaseColumnName; // 首字母大写列名

  protected java.lang.String initialLowercaseColumnName; // 首字母小写列名

  protected java.lang.Integer weightOrder; // 排序权重

  protected java.lang.String dtId; // 数据库表编号

  protected java.util.List<java.lang.String> columnsIdAndin; // 列编号Andin查询

  protected java.lang.String columnNameAndeq; // 列名Andeq查询

  protected java.lang.String dtIdAndeq; // 数据库表编号Andeq查询

  protected java.lang.String columnNameAndKeyLike; // 列名AndKeyLike查询

  protected java.lang.String columnNameAnnotationOrKeyLike; // 列名注释OrKeyLike查询

  protected java.lang.String dataTypeOrKeyLike; // 数据类型OrKeyLike查询

  protected java.lang.String isNullOrKeyLike; // 是否为空OrKeyLike查询

  protected java.lang.String initialCaseColumnNameOrKeyLike; // 首字母大写列名OrKeyLike查询

  protected java.lang.String initialLowercaseColumnNameOrKeyLike; // 首字母小写列名OrKeyLike查询

  protected java.lang.String dtIdOrKeyLike; // 数据库表编号OrKeyLike查询

  public java.lang.String getColumnsId() {
    return columnsId;
  }

  public void setColumnsId(java.lang.String columnsId) {
    this.columnsId = columnsId;
  }

  public java.lang.String getColumnName() {
    return columnName;
  }

  public void setColumnName(java.lang.String columnName) {
    this.columnName = columnName;
  }

  public java.lang.String getColumnNameAnnotation() {
    return columnNameAnnotation;
  }

  public void setColumnNameAnnotation(java.lang.String columnNameAnnotation) {
    this.columnNameAnnotation = columnNameAnnotation;
  }

  public java.lang.String getDataType() {
    return dataType;
  }

  public void setDataType(java.lang.String dataType) {
    this.dataType = dataType;
  }

  public java.lang.String getIsNull() {
    return isNull;
  }

  public void setIsNull(java.lang.String isNull) {
    this.isNull = isNull;
  }

  public java.lang.String getInitialCaseColumnName() {
    return initialCaseColumnName;
  }

  public void setInitialCaseColumnName(java.lang.String initialCaseColumnName) {
    this.initialCaseColumnName = initialCaseColumnName;
  }

  public java.lang.String getInitialLowercaseColumnName() {
    return initialLowercaseColumnName;
  }

  public void setInitialLowercaseColumnName(java.lang.String initialLowercaseColumnName) {
    this.initialLowercaseColumnName = initialLowercaseColumnName;
  }

  public java.lang.Integer getWeightOrder() {
    return weightOrder;
  }

  public void setWeightOrder(java.lang.Integer weightOrder) {
    this.weightOrder = weightOrder;
  }

  public java.lang.String getDtId() {
    return dtId;
  }

  public void setDtId(java.lang.String dtId) {
    this.dtId = dtId;
  }

  public java.util.List<java.lang.String> getColumnsIdAndin() {
    return columnsIdAndin;
  }

  public void setColumnsIdAndin(java.util.List<java.lang.String> columnsIdAndin) {
    this.columnsIdAndin = columnsIdAndin;
  }

  public java.lang.String getColumnNameAndeq() {
    return columnNameAndeq;
  }

  public void setColumnNameAndeq(java.lang.String columnNameAndeq) {
    this.columnNameAndeq = columnNameAndeq;
  }

  public java.lang.String getDtIdAndeq() {
    return dtIdAndeq;
  }

  public void setDtIdAndeq(java.lang.String dtIdAndeq) {
    this.dtIdAndeq = dtIdAndeq;
  }

  public java.lang.String getColumnNameAndKeyLike() {
    return columnNameAndKeyLike;
  }

  public void setColumnNameAndKeyLike(java.lang.String columnNameAndKeyLike) {
    this.columnNameAndKeyLike = columnNameAndKeyLike;
  }

  public java.lang.String getColumnNameAnnotationOrKeyLike() {
    return columnNameAnnotationOrKeyLike;
  }

  public void setColumnNameAnnotationOrKeyLike(java.lang.String columnNameAnnotationOrKeyLike) {
    this.columnNameAnnotationOrKeyLike = columnNameAnnotationOrKeyLike;
  }

  public java.lang.String getDataTypeOrKeyLike() {
    return dataTypeOrKeyLike;
  }

  public void setDataTypeOrKeyLike(java.lang.String dataTypeOrKeyLike) {
    this.dataTypeOrKeyLike = dataTypeOrKeyLike;
  }

  public java.lang.String getIsNullOrKeyLike() {
    return isNullOrKeyLike;
  }

  public void setIsNullOrKeyLike(java.lang.String isNullOrKeyLike) {
    this.isNullOrKeyLike = isNullOrKeyLike;
  }

  public java.lang.String getInitialCaseColumnNameOrKeyLike() {
    return initialCaseColumnNameOrKeyLike;
  }

  public void setInitialCaseColumnNameOrKeyLike(java.lang.String initialCaseColumnNameOrKeyLike) {
    this.initialCaseColumnNameOrKeyLike = initialCaseColumnNameOrKeyLike;
  }

  public java.lang.String getInitialLowercaseColumnNameOrKeyLike() {
    return initialLowercaseColumnNameOrKeyLike;
  }

  public void setInitialLowercaseColumnNameOrKeyLike(java.lang.String initialLowercaseColumnNameOrKeyLike) {
    this.initialLowercaseColumnNameOrKeyLike = initialLowercaseColumnNameOrKeyLike;
  }

  public java.lang.String getDtIdOrKeyLike() {
    return dtIdOrKeyLike;
  }

  public void setDtIdOrKeyLike(java.lang.String dtIdOrKeyLike) {
    this.dtIdOrKeyLike = dtIdOrKeyLike;
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
