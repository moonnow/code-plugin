package com.pro.code.plugin.persistent;

import java.util.Collection;

import com.pro.code.plugin.entity.Columns;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.ColumnsQuery;
import com.pro.code.plugin.vo.ColumnsVO;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;

public interface IColumnsPersistent {

  public static final String TABLE_NAME = "PRO_COLUMNS";

  public void saveColumns(Columns columns) throws CodePluginException;

  public void batchSaveColumns(Collection<Columns> columnss) throws CodePluginException;

  public void updateColumns(Columns columns) throws CodePluginException;

  public void batchUpdateColumns(Collection<Columns> columnss) throws CodePluginException;

  public void removeColumns(Columns columns) throws CodePluginException;

  public void batchRemoveColumns(Collection<Columns> columnss) throws CodePluginException;

  public Long getCountColumns(ColumnsQuery columnsQuery) throws CodePluginException;

  public Columns getColumnsByPk(java.lang.String columnsId) throws CodePluginException;

  public Collection<Columns> getAllColumns() throws CodePluginException;

  public Paging<Columns> pagingGetColumns(Parameter parameter) throws CodePluginException;

  public Collection<Columns> queryColumns(ColumnsQuery columnsQuery) throws CodePluginException;

  public Paging<Columns> pagingQueryColumns(Parameter parameter, ColumnsQuery columnsQuery) throws CodePluginException;

  public boolean isUnique(ColumnsQuery columnsQuery) throws CodePluginException;

  public String getNotUniqueErrorMessage(ColumnsQuery columnsQuery) throws CodePluginException;

  public ColumnsVO getColumnsVOByPk(java.lang.String columnsId) throws CodePluginException;

  public Collection<ColumnsVO> getAllColumnsVO() throws CodePluginException;

  public Paging<ColumnsVO> pagingGetColumnsVO(Parameter parameter) throws CodePluginException;

  public Collection<ColumnsVO> queryColumnsVO(ColumnsQuery columnsQuery) throws CodePluginException;

  public Paging<ColumnsVO> pagingQueryColumnsVO(Parameter parameter, ColumnsQuery columnsQuery) throws CodePluginException;

}
