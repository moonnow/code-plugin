package com.pro.code.plugin.persistent;

import java.util.Collection;

import com.pro.code.plugin.entity.VirtualColumns;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.VirtualColumnsQuery;
import com.pro.code.plugin.vo.VirtualColumnsVO;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;

public interface IVirtualColumnsPersistent {

  public static final String TABLE_NAME = "PRO_VIRTUAL_COLUMNS";

  public void saveVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException;

  public void batchSaveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException;

  public void updateVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException;

  public void batchUpdateVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException;

  public void removeVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException;

  public void batchRemoveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException;

  public Long getCountVirtualColumns(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

  public VirtualColumns getVirtualColumnsByPk(java.lang.String virtualColumnsId) throws CodePluginException;

  public Collection<VirtualColumns> getAllVirtualColumns() throws CodePluginException;

  public Paging<VirtualColumns> pagingGetVirtualColumns(Parameter parameter) throws CodePluginException;

  public Collection<VirtualColumns> queryVirtualColumns(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

  public Paging<VirtualColumns> pagingQueryVirtualColumns(Parameter parameter, VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

  public boolean isUnique(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

  public String getNotUniqueErrorMessage(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

  public VirtualColumnsVO getVirtualColumnsVOByPk(java.lang.String virtualColumnsId) throws CodePluginException;

  public Collection<VirtualColumnsVO> getAllVirtualColumnsVO() throws CodePluginException;

  public Paging<VirtualColumnsVO> pagingGetVirtualColumnsVO(Parameter parameter) throws CodePluginException;

  public Collection<VirtualColumnsVO> queryVirtualColumnsVO(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

  public Paging<VirtualColumnsVO> pagingQueryVirtualColumnsVO(Parameter parameter, VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

}
