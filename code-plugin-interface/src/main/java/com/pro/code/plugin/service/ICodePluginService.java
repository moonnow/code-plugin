package com.pro.code.plugin.service;

import java.util.Collection;

import com.pro.code.plugin.exception.CodePluginException;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;

import com.pro.code.plugin.entity.Dt;
import com.pro.code.plugin.query.DtQuery;
import com.pro.code.plugin.vo.DtVO;

import com.pro.code.plugin.entity.Columns;
import com.pro.code.plugin.query.ColumnsQuery;
import com.pro.code.plugin.vo.ColumnsVO;

import com.pro.code.plugin.entity.Pk;
import com.pro.code.plugin.query.PkQuery;
import com.pro.code.plugin.vo.PkVO;

import com.pro.code.plugin.entity.Sort;
import com.pro.code.plugin.query.SortQuery;
import com.pro.code.plugin.vo.SortVO;

import com.pro.code.plugin.entity.Query;
import com.pro.code.plugin.query.QueryQuery;
import com.pro.code.plugin.vo.QueryVO;

import com.pro.code.plugin.entity.VirtualColumns;
import com.pro.code.plugin.query.VirtualColumnsQuery;
import com.pro.code.plugin.vo.VirtualColumnsVO;

public interface ICodePluginService {

  public void saveDt(Dt dt) throws CodePluginException;

  public void batchSaveDt(Collection<Dt> dts) throws CodePluginException;

  public void updateDt(Dt dt) throws CodePluginException;

  public void batchUpdateDt(Collection<Dt> dts) throws CodePluginException;

  public void removeDt(Dt dt) throws CodePluginException;

  public void batchRemoveDt(Collection<Dt> dts) throws CodePluginException;

  public Long getCountDt(DtQuery dtQuery) throws CodePluginException;

  public Dt getDtByPk(java.lang.String dtId) throws CodePluginException;

  public Collection<Dt> getAllDt() throws CodePluginException;

  public Paging<Dt> pagingGetDt(Parameter parameter) throws CodePluginException;

  public Collection<Dt> queryDt(DtQuery dtQuery) throws CodePluginException;

  public Paging<Dt> pagingQueryDt(Parameter parameter, DtQuery dtQuery) throws CodePluginException;

  public DtVO getDtVOByPk(java.lang.String dtId) throws CodePluginException;

  public Collection<DtVO> getAllDtVO() throws CodePluginException;

  public Paging<DtVO> pagingGetDtVO(Parameter parameter) throws CodePluginException;

  public Collection<DtVO> queryDtVO(DtQuery dtQuery) throws CodePluginException;

  public Paging<DtVO> pagingQueryDtVO(Parameter parameter, DtQuery dtQuery) throws CodePluginException;

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

  public ColumnsVO getColumnsVOByPk(java.lang.String columnsId) throws CodePluginException;

  public Collection<ColumnsVO> getAllColumnsVO() throws CodePluginException;

  public Paging<ColumnsVO> pagingGetColumnsVO(Parameter parameter) throws CodePluginException;

  public Collection<ColumnsVO> queryColumnsVO(ColumnsQuery columnsQuery) throws CodePluginException;

  public Paging<ColumnsVO> pagingQueryColumnsVO(Parameter parameter, ColumnsQuery columnsQuery) throws CodePluginException;

  public void savePk(Pk pk) throws CodePluginException;

  public void batchSavePk(Collection<Pk> pks) throws CodePluginException;

  public void updatePk(Pk pk) throws CodePluginException;

  public void batchUpdatePk(Collection<Pk> pks) throws CodePluginException;

  public void removePk(Pk pk) throws CodePluginException;

  public void batchRemovePk(Collection<Pk> pks) throws CodePluginException;

  public Long getCountPk(PkQuery pkQuery) throws CodePluginException;

  public Pk getPkByPk(java.lang.String pkId) throws CodePluginException;

  public Collection<Pk> getAllPk() throws CodePluginException;

  public Paging<Pk> pagingGetPk(Parameter parameter) throws CodePluginException;

  public Collection<Pk> queryPk(PkQuery pkQuery) throws CodePluginException;

  public Paging<Pk> pagingQueryPk(Parameter parameter, PkQuery pkQuery) throws CodePluginException;

  public PkVO getPkVOByPk(java.lang.String pkId) throws CodePluginException;

  public Collection<PkVO> getAllPkVO() throws CodePluginException;

  public Paging<PkVO> pagingGetPkVO(Parameter parameter) throws CodePluginException;

  public Collection<PkVO> queryPkVO(PkQuery pkQuery) throws CodePluginException;

  public Paging<PkVO> pagingQueryPkVO(Parameter parameter, PkQuery pkQuery) throws CodePluginException;

  public void saveSort(Sort sort) throws CodePluginException;

  public void batchSaveSort(Collection<Sort> sorts) throws CodePluginException;

  public void updateSort(Sort sort) throws CodePluginException;

  public void batchUpdateSort(Collection<Sort> sorts) throws CodePluginException;

  public void removeSort(Sort sort) throws CodePluginException;

  public void batchRemoveSort(Collection<Sort> sorts) throws CodePluginException;

  public Long getCountSort(SortQuery sortQuery) throws CodePluginException;

  public Sort getSortByPk(java.lang.String sortId) throws CodePluginException;

  public Collection<Sort> getAllSort() throws CodePluginException;

  public Paging<Sort> pagingGetSort(Parameter parameter) throws CodePluginException;

  public Collection<Sort> querySort(SortQuery sortQuery) throws CodePluginException;

  public Paging<Sort> pagingQuerySort(Parameter parameter, SortQuery sortQuery) throws CodePluginException;

  public SortVO getSortVOByPk(java.lang.String sortId) throws CodePluginException;

  public Collection<SortVO> getAllSortVO() throws CodePluginException;

  public Paging<SortVO> pagingGetSortVO(Parameter parameter) throws CodePluginException;

  public Collection<SortVO> querySortVO(SortQuery sortQuery) throws CodePluginException;

  public Paging<SortVO> pagingQuerySortVO(Parameter parameter, SortQuery sortQuery) throws CodePluginException;

  public void saveQuery(Query query) throws CodePluginException;

  public void batchSaveQuery(Collection<Query> querys) throws CodePluginException;

  public void updateQuery(Query query) throws CodePluginException;

  public void batchUpdateQuery(Collection<Query> querys) throws CodePluginException;

  public void removeQuery(Query query) throws CodePluginException;

  public void batchRemoveQuery(Collection<Query> querys) throws CodePluginException;

  public Long getCountQuery(QueryQuery queryQuery) throws CodePluginException;

  public Query getQueryByPk(java.lang.String queryId) throws CodePluginException;

  public Collection<Query> getAllQuery() throws CodePluginException;

  public Paging<Query> pagingGetQuery(Parameter parameter) throws CodePluginException;

  public Collection<Query> queryQuery(QueryQuery queryQuery) throws CodePluginException;

  public Paging<Query> pagingQueryQuery(Parameter parameter, QueryQuery queryQuery) throws CodePluginException;

  public QueryVO getQueryVOByPk(java.lang.String queryId) throws CodePluginException;

  public Collection<QueryVO> getAllQueryVO() throws CodePluginException;

  public Paging<QueryVO> pagingGetQueryVO(Parameter parameter) throws CodePluginException;

  public Collection<QueryVO> queryQueryVO(QueryQuery queryQuery) throws CodePluginException;

  public Paging<QueryVO> pagingQueryQueryVO(Parameter parameter, QueryQuery queryQuery) throws CodePluginException;

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

  public VirtualColumnsVO getVirtualColumnsVOByPk(java.lang.String virtualColumnsId) throws CodePluginException;

  public Collection<VirtualColumnsVO> getAllVirtualColumnsVO() throws CodePluginException;

  public Paging<VirtualColumnsVO> pagingGetVirtualColumnsVO(Parameter parameter) throws CodePluginException;

  public Collection<VirtualColumnsVO> queryVirtualColumnsVO(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

  public Paging<VirtualColumnsVO> pagingQueryVirtualColumnsVO(Parameter parameter, VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException;

}
