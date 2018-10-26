package com.pro.code.plugin.service.implement;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.service.ICodePluginService;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.SqlTools;
import com.pro.tool.util.ToolUtil;

import com.pro.code.plugin.entity.Dt;
import com.pro.code.plugin.persistent.IDtPersistent;
import com.pro.code.plugin.query.DtQuery;
import com.pro.code.plugin.vo.DtVO;

import com.pro.code.plugin.entity.Columns;
import com.pro.code.plugin.persistent.IColumnsPersistent;
import com.pro.code.plugin.query.ColumnsQuery;
import com.pro.code.plugin.vo.ColumnsVO;

import com.pro.code.plugin.entity.Pk;
import com.pro.code.plugin.persistent.IPkPersistent;
import com.pro.code.plugin.query.PkQuery;
import com.pro.code.plugin.vo.PkVO;

import com.pro.code.plugin.entity.Sort;
import com.pro.code.plugin.persistent.ISortPersistent;
import com.pro.code.plugin.query.SortQuery;
import com.pro.code.plugin.vo.SortVO;

import com.pro.code.plugin.entity.Query;
import com.pro.code.plugin.persistent.IQueryPersistent;
import com.pro.code.plugin.query.QueryQuery;
import com.pro.code.plugin.vo.QueryVO;

import com.pro.code.plugin.entity.VirtualColumns;
import com.pro.code.plugin.persistent.IVirtualColumnsPersistent;
import com.pro.code.plugin.query.VirtualColumnsQuery;
import com.pro.code.plugin.vo.VirtualColumnsVO;

@org.springframework.stereotype.Service("com.pro.code.plugin.CodePluginService")
@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED, readOnly = true, rollbackFor = java.lang.Exception.class)
public class CodePluginServiceImpl implements ICodePluginService {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(CodePluginServiceImpl.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.DtPersistent")
  private IDtPersistent dtPersistent;

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveDt(Dt dt) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.saveDt ");
      log.debug("parameter dt is : " + dt);
    }
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      dt.setDtId(ToolUtil.getUUID());
      dt.setDtName(SqlTools.getDtNameFromDtStr(dt.getDtSql()));
      DtQuery dtQuery = new DtQuery();
      dtQuery.setDtNameAndeq(dt.getDtName());
      if (!dtPersistent.isUnique(dtQuery)) {
        throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { dtPersistent.getNotUniqueErrorMessage(dtQuery) });
      }
      dtPersistent.saveDt(dt);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchSaveDt(Collection<Dt> dts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchSaveDt ");
      log.debug("parameter dts is : " + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      for (Dt dt : dts) {
        if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
        }
        dt.setDtId(ToolUtil.getUUID());
        dt.setDtName(SqlTools.getDtNameFromDtStr(dt.getDtSql()));
        DtQuery dtQuery = new DtQuery();
        dtQuery.setDtNameAndeq(dt.getDtName());
        if (!dtPersistent.isUnique(dtQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { dtPersistent.getNotUniqueErrorMessage(dtQuery) });
        }
      }
      dtPersistent.batchSaveDt(dts);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void updateDt(Dt dt) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.updateDt ");
      log.debug("parameter dt is : " + dt);
    }
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      if (ToolUtil.isNullStr(dt.getDtId())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      Dt oldDt = dtPersistent.getDtByPk(dt.getDtId());
      if (oldDt == null) {
        throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      dt.setDtName(SqlTools.getDtNameFromDtStr(dt.getDtSql()));
      DtQuery dtQuery = new DtQuery();
      dtQuery.setDtNameAndeq(dt.getDtName());
      if (!dtQuery.getDtNameAndeq().equals(oldDt.getDtName())) {
        if (!dtPersistent.isUnique(dtQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { dtPersistent.getNotUniqueErrorMessage(dtQuery) });
        }
      }
      dtPersistent.updateDt(dt);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchUpdateDt(Collection<Dt> dts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchUpdateDt ");
      log.debug("parameter dts is : " + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      for (Dt dt : dts) {
        if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
        }
        if (ToolUtil.isNullStr(dt.getDtId())) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
        }
        Dt oldDt = dtPersistent.getDtByPk(dt.getDtId());
        if (oldDt == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        dt.setDtName(SqlTools.getDtNameFromDtStr(dt.getDtSql()));
        DtQuery dtQuery = new DtQuery();
        dtQuery.setDtNameAndeq(dt.getDtName());
        if (!dtQuery.getDtNameAndeq().equals(oldDt.getDtName())) {
          if (!dtPersistent.isUnique(dtQuery)) {
            throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { dtPersistent.getNotUniqueErrorMessage(dtQuery) });
          }
        }
      }
      dtPersistent.batchUpdateDt(dts);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void removeDt(Dt dt) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.removeDt ");
      log.debug("parameter dt is : " + dt);
    }
    try {
      if (dt == null || ToolUtil.isNullEntityAllFieldValue(dt)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dt ");
      }
      Set<Dt> dtSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(dt.getDtId())) {
        DtQuery dtQuery = ToolUtil.attributeReplication(DtQuery.class, dt);
        dtSet.addAll(dtPersistent.queryDt(dtQuery));
      } else {
        Dt oldDt = dtPersistent.getDtByPk(dt.getDtId());
        if (oldDt == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        dtSet.add(oldDt);
      }
      if (ToolUtil.isNotEmpty(dtSet)) {
        dtPersistent.batchRemoveDt(dtSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchRemoveDt(Collection<Dt> dts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchRemoveDt ");
      log.debug("parameter dts is : " + dts);
    }
    try {
      if (ToolUtil.isEmpty(dts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dts ");
      }
      Set<Dt> dtSet = new LinkedHashSet<>();
      for (Dt dt : dts) {
        if (ToolUtil.isNullStr(dt.getDtId())) {
          DtQuery dtQuery = ToolUtil.attributeReplication(DtQuery.class, dt);
          dtSet.addAll(dtPersistent.queryDt(dtQuery));
        } else {
          Dt oldDt = dtPersistent.getDtByPk(dt.getDtId());
          if (oldDt == null) {
            throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          dtSet.add(oldDt);
        }
      }
      if (ToolUtil.isNotEmpty(dtSet)) {
        dtPersistent.batchRemoveDt(dtSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Long getCountDt(DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getCountDt ");
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      return dtPersistent.getCountDt(dtQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Dt getDtByPk(java.lang.String dtId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getDtByPk ");
      log.debug("parameter dtId is : " + dtId);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      return dtPersistent.getDtByPk(dtId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Dt> getAllDt() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllDt ");
    }
    try {
      return dtPersistent.getAllDt();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Dt> pagingGetDt(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetDt ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return dtPersistent.pagingGetDt(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Dt> queryDt(DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryDt ");
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      return dtPersistent.queryDt(dtQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Dt> pagingQueryDt(Parameter parameter, DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryDt ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return dtPersistent.pagingQueryDt(parameter, dtQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public DtVO getDtVOByPk(java.lang.String dtId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getDtVOByPk ");
      log.debug("parameter dtId is : " + dtId);
    }
    try {
      if (ToolUtil.isNullStr(dtId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " dtId ");
      }
      return dtPersistent.getDtVOByPk(dtId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<DtVO> getAllDtVO() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllDtVO ");
    }
    try {
      return dtPersistent.getAllDtVO();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<DtVO> pagingGetDtVO(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetDtVO ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return dtPersistent.pagingGetDtVO(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<DtVO> queryDtVO(DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryDtVO ");
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      return dtPersistent.queryDtVO(dtQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<DtVO> pagingQueryDtVO(Parameter parameter, DtQuery dtQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryDtVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter dtQuery is : " + dtQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return dtPersistent.pagingQueryDtVO(parameter, dtQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @javax.annotation.Resource(name = "com.pro.code.plugin.ColumnsPersistent")
  private IColumnsPersistent columnsPersistent;

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveColumns(Columns columns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.saveColumns ");
      log.debug("parameter columns is : " + columns);
    }
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      columns.setColumnsId(ToolUtil.getUUID());
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setColumnNameAndeq(columns.getColumnName());
      columnsQuery.setDtIdAndeq(columns.getDtId());
      if (!columnsPersistent.isUnique(columnsQuery)) {
        throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { columnsPersistent.getNotUniqueErrorMessage(columnsQuery) });
      }
      columnsPersistent.saveColumns(columns);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchSaveColumns(Collection<Columns> columnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchSaveColumns ");
      log.debug("parameter columnss is : " + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnss ");
      }
      for (Columns columns : columnss) {
        if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
        }
        columns.setColumnsId(ToolUtil.getUUID());
        ColumnsQuery columnsQuery = new ColumnsQuery();
        columnsQuery.setColumnNameAndeq(columns.getColumnName());
        columnsQuery.setDtIdAndeq(columns.getDtId());
        if (!columnsPersistent.isUnique(columnsQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { columnsPersistent.getNotUniqueErrorMessage(columnsQuery) });
        }
      }
      columnsPersistent.batchSaveColumns(columnss);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void updateColumns(Columns columns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.updateColumns ");
      log.debug("parameter columns is : " + columns);
    }
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      if (ToolUtil.isNullStr(columns.getColumnsId())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnsId ");
      }
      Columns oldColumns = columnsPersistent.getColumnsByPk(columns.getColumnsId());
      if (oldColumns == null) {
        throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      ColumnsQuery columnsQuery = new ColumnsQuery();
      columnsQuery.setColumnNameAndeq(columns.getColumnName());
      columnsQuery.setDtIdAndeq(columns.getDtId());
      if (!(columnsQuery.getColumnNameAndeq().equals(oldColumns.getColumnName()) && columnsQuery.getDtIdAndeq().equals(oldColumns.getDtId()))) {
        if (!columnsPersistent.isUnique(columnsQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { columnsPersistent.getNotUniqueErrorMessage(columnsQuery) });
        }
      }
      columnsPersistent.updateColumns(columns);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchUpdateColumns(Collection<Columns> columnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchUpdateColumns ");
      log.debug("parameter columnss is : " + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnss ");
      }
      for (Columns columns : columnss) {
        if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
        }
        if (ToolUtil.isNullStr(columns.getColumnsId())) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnsId ");
        }
        Columns oldColumns = columnsPersistent.getColumnsByPk(columns.getColumnsId());
        if (oldColumns == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        ColumnsQuery columnsQuery = new ColumnsQuery();
        columnsQuery.setColumnNameAndeq(columns.getColumnName());
        columnsQuery.setDtIdAndeq(columns.getDtId());
        if (!(columnsQuery.getColumnNameAndeq().equals(oldColumns.getColumnName()) && columnsQuery.getDtIdAndeq().equals(oldColumns.getDtId()))) {
          if (!columnsPersistent.isUnique(columnsQuery)) {
            throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { columnsPersistent.getNotUniqueErrorMessage(columnsQuery) });
          }
        }
      }
      columnsPersistent.batchUpdateColumns(columnss);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void removeColumns(Columns columns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.removeColumns ");
      log.debug("parameter columns is : " + columns);
    }
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      Set<Columns> columnsSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(columns.getColumnsId())) {
        ColumnsQuery columnsQuery = ToolUtil.attributeReplication(ColumnsQuery.class, columns);
        columnsSet.addAll(columnsPersistent.queryColumns(columnsQuery));
      } else {
        Columns oldColumns = columnsPersistent.getColumnsByPk(columns.getColumnsId());
        if (oldColumns == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        columnsSet.add(oldColumns);
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        columnsPersistent.batchRemoveColumns(columnsSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchRemoveColumns(Collection<Columns> columnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchRemoveColumns ");
      log.debug("parameter columnss is : " + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnss ");
      }
      Set<Columns> columnsSet = new LinkedHashSet<>();
      for (Columns columns : columnss) {
        if (ToolUtil.isNullStr(columns.getColumnsId())) {
          ColumnsQuery columnsQuery = ToolUtil.attributeReplication(ColumnsQuery.class, columns);
          columnsSet.addAll(columnsPersistent.queryColumns(columnsQuery));
        } else {
          Columns oldColumns = columnsPersistent.getColumnsByPk(columns.getColumnsId());
          if (oldColumns == null) {
            throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          columnsSet.add(oldColumns);
        }
      }
      if (ToolUtil.isNotEmpty(columnsSet)) {
        columnsPersistent.batchRemoveColumns(columnsSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Long getCountColumns(ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getCountColumns ");
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      return columnsPersistent.getCountColumns(columnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Columns getColumnsByPk(java.lang.String columnsId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getColumnsByPk ");
      log.debug("parameter columnsId is : " + columnsId);
    }
    try {
      if (ToolUtil.isNullStr(columnsId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnsId ");
      }
      return columnsPersistent.getColumnsByPk(columnsId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Columns> getAllColumns() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllColumns ");
    }
    try {
      return columnsPersistent.getAllColumns();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Columns> pagingGetColumns(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetColumns ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return columnsPersistent.pagingGetColumns(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Columns> queryColumns(ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryColumns ");
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      return columnsPersistent.queryColumns(columnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Columns> pagingQueryColumns(Parameter parameter, ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryColumns ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return columnsPersistent.pagingQueryColumns(parameter, columnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public ColumnsVO getColumnsVOByPk(java.lang.String columnsId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getColumnsVOByPk ");
      log.debug("parameter columnsId is : " + columnsId);
    }
    try {
      if (ToolUtil.isNullStr(columnsId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnsId ");
      }
      return columnsPersistent.getColumnsVOByPk(columnsId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<ColumnsVO> getAllColumnsVO() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllColumnsVO ");
    }
    try {
      return columnsPersistent.getAllColumnsVO();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<ColumnsVO> pagingGetColumnsVO(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetColumnsVO ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return columnsPersistent.pagingGetColumnsVO(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<ColumnsVO> queryColumnsVO(ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryColumnsVO ");
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      return columnsPersistent.queryColumnsVO(columnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<ColumnsVO> pagingQueryColumnsVO(Parameter parameter, ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryColumnsVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return columnsPersistent.pagingQueryColumnsVO(parameter, columnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @javax.annotation.Resource(name = "com.pro.code.plugin.PkPersistent")
  private IPkPersistent pkPersistent;

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void savePk(Pk pk) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.savePk ");
      log.debug("parameter pk is : " + pk);
    }
    try {
      if (pk == null || ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pk ");
      }
      pk.setPkId(ToolUtil.getUUID());
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtIdAndeq(pk.getDtId());
      pkQuery.setColumnsIdAndeq(pk.getColumnsId());
      if (!pkPersistent.isUnique(pkQuery)) {
        throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { pkPersistent.getNotUniqueErrorMessage(pkQuery) });
      }
      pkPersistent.savePk(pk);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchSavePk(Collection<Pk> pks) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchSavePk ");
      log.debug("parameter pks is : " + pks);
    }
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pks ");
      }
      for (Pk pk : pks) {
        if (pk == null || ToolUtil.isNullEntityAllFieldValue(pk)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pk ");
        }
        pk.setPkId(ToolUtil.getUUID());
        PkQuery pkQuery = new PkQuery();
        pkQuery.setDtIdAndeq(pk.getDtId());
        pkQuery.setColumnsIdAndeq(pk.getColumnsId());
        if (!pkPersistent.isUnique(pkQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { pkPersistent.getNotUniqueErrorMessage(pkQuery) });
        }
      }
      pkPersistent.batchSavePk(pks);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void updatePk(Pk pk) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.updatePk ");
      log.debug("parameter pk is : " + pk);
    }
    try {
      if (pk == null || ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pk ");
      }
      if (ToolUtil.isNullStr(pk.getPkId())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pkId ");
      }
      Pk oldPk = pkPersistent.getPkByPk(pk.getPkId());
      if (oldPk == null) {
        throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtIdAndeq(pk.getDtId());
      pkQuery.setColumnsIdAndeq(pk.getColumnsId());
      if (!(pkQuery.getDtIdAndeq().equals(oldPk.getDtId()) && pkQuery.getColumnsIdAndeq().equals(oldPk.getColumnsId()))) {
        if (!pkPersistent.isUnique(pkQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { pkPersistent.getNotUniqueErrorMessage(pkQuery) });
        }
      }
      pkPersistent.updatePk(pk);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchUpdatePk(Collection<Pk> pks) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchUpdatePk ");
      log.debug("parameter pks is : " + pks);
    }
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pks ");
      }
      for (Pk pk : pks) {
        if (pk == null || ToolUtil.isNullEntityAllFieldValue(pk)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pk ");
        }
        if (ToolUtil.isNullStr(pk.getPkId())) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pkId ");
        }
        Pk oldPk = pkPersistent.getPkByPk(pk.getPkId());
        if (oldPk == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        PkQuery pkQuery = new PkQuery();
        pkQuery.setDtIdAndeq(pk.getDtId());
        pkQuery.setColumnsIdAndeq(pk.getColumnsId());
        if (!(pkQuery.getDtIdAndeq().equals(oldPk.getDtId()) && pkQuery.getColumnsIdAndeq().equals(oldPk.getColumnsId()))) {
          if (!pkPersistent.isUnique(pkQuery)) {
            throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { pkPersistent.getNotUniqueErrorMessage(pkQuery) });
          }
        }
      }
      pkPersistent.batchUpdatePk(pks);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void removePk(Pk pk) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.removePk ");
      log.debug("parameter pk is : " + pk);
    }
    try {
      if (pk == null || ToolUtil.isNullEntityAllFieldValue(pk)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pk ");
      }
      Set<Pk> pkSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(pk.getPkId())) {
        PkQuery pkQuery = ToolUtil.attributeReplication(PkQuery.class, pk);
        pkSet.addAll(pkPersistent.queryPk(pkQuery));
      } else {
        Pk oldPk = pkPersistent.getPkByPk(pk.getPkId());
        if (oldPk == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        pkSet.add(oldPk);
      }
      if (ToolUtil.isNotEmpty(pkSet)) {
        pkPersistent.batchRemovePk(pkSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchRemovePk(Collection<Pk> pks) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchRemovePk ");
      log.debug("parameter pks is : " + pks);
    }
    try {
      if (ToolUtil.isEmpty(pks)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pks ");
      }
      Set<Pk> pkSet = new LinkedHashSet<>();
      for (Pk pk : pks) {
        if (ToolUtil.isNullStr(pk.getPkId())) {
          PkQuery pkQuery = ToolUtil.attributeReplication(PkQuery.class, pk);
          pkSet.addAll(pkPersistent.queryPk(pkQuery));
        } else {
          Pk oldPk = pkPersistent.getPkByPk(pk.getPkId());
          if (oldPk == null) {
            throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          pkSet.add(oldPk);
        }
      }
      if (ToolUtil.isNotEmpty(pkSet)) {
        pkPersistent.batchRemovePk(pkSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Long getCountPk(PkQuery pkQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getCountPk ");
      log.debug("parameter pkQuery is : " + pkQuery);
    }
    try {
      return pkPersistent.getCountPk(pkQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Pk getPkByPk(java.lang.String pkId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getPkByPk ");
      log.debug("parameter pkId is : " + pkId);
    }
    try {
      if (ToolUtil.isNullStr(pkId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pkId ");
      }
      return pkPersistent.getPkByPk(pkId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Pk> getAllPk() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllPk ");
    }
    try {
      return pkPersistent.getAllPk();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Pk> pagingGetPk(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetPk ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return pkPersistent.pagingGetPk(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Pk> queryPk(PkQuery pkQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryPk ");
      log.debug("parameter pkQuery is : " + pkQuery);
    }
    try {
      return pkPersistent.queryPk(pkQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Pk> pagingQueryPk(Parameter parameter, PkQuery pkQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryPk ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pkQuery is : " + pkQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return pkPersistent.pagingQueryPk(parameter, pkQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public PkVO getPkVOByPk(java.lang.String pkId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getPkVOByPk ");
      log.debug("parameter pkId is : " + pkId);
    }
    try {
      if (ToolUtil.isNullStr(pkId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " pkId ");
      }
      return pkPersistent.getPkVOByPk(pkId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<PkVO> getAllPkVO() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllPkVO ");
    }
    try {
      return pkPersistent.getAllPkVO();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<PkVO> pagingGetPkVO(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetPkVO ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return pkPersistent.pagingGetPkVO(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<PkVO> queryPkVO(PkQuery pkQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryPkVO ");
      log.debug("parameter pkQuery is : " + pkQuery);
    }
    try {
      return pkPersistent.queryPkVO(pkQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<PkVO> pagingQueryPkVO(Parameter parameter, PkQuery pkQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryPkVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter pkQuery is : " + pkQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return pkPersistent.pagingQueryPkVO(parameter, pkQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @javax.annotation.Resource(name = "com.pro.code.plugin.SortPersistent")
  private ISortPersistent sortPersistent;

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveSort(Sort sort) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.saveSort ");
      log.debug("parameter sort is : " + sort);
    }
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      sort.setSortId(ToolUtil.getUUID());
      SortQuery sortQuery = new SortQuery();
      sortQuery.setDtIdAndeq(sort.getDtId());
      sortQuery.setColumnsIdAndeq(sort.getColumnsId());
      if (!sortPersistent.isUnique(sortQuery)) {
        throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { sortPersistent.getNotUniqueErrorMessage(sortQuery) });
      }
      sortPersistent.saveSort(sort);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchSaveSort(Collection<Sort> sorts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchSaveSort ");
      log.debug("parameter sorts is : " + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      for (Sort sort : sorts) {
        if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
        }
        sort.setSortId(ToolUtil.getUUID());
        SortQuery sortQuery = new SortQuery();
        sortQuery.setDtIdAndeq(sort.getDtId());
        sortQuery.setColumnsIdAndeq(sort.getColumnsId());
        if (!sortPersistent.isUnique(sortQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { sortPersistent.getNotUniqueErrorMessage(sortQuery) });
        }
      }
      sortPersistent.batchSaveSort(sorts);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void updateSort(Sort sort) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.updateSort ");
      log.debug("parameter sort is : " + sort);
    }
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      if (ToolUtil.isNullStr(sort.getSortId())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sortId ");
      }
      Sort oldSort = sortPersistent.getSortByPk(sort.getSortId());
      if (oldSort == null) {
        throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      SortQuery sortQuery = new SortQuery();
      sortQuery.setDtIdAndeq(sort.getDtId());
      sortQuery.setColumnsIdAndeq(sort.getColumnsId());
      if (!(sortQuery.getDtIdAndeq().equals(oldSort.getDtId()) && sortQuery.getColumnsIdAndeq().equals(oldSort.getColumnsId()))) {
        if (!sortPersistent.isUnique(sortQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { sortPersistent.getNotUniqueErrorMessage(sortQuery) });
        }
      }
      sortPersistent.updateSort(sort);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchUpdateSort(Collection<Sort> sorts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchUpdateSort ");
      log.debug("parameter sorts is : " + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      for (Sort sort : sorts) {
        if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
        }
        if (ToolUtil.isNullStr(sort.getSortId())) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sortId ");
        }
        Sort oldSort = sortPersistent.getSortByPk(sort.getSortId());
        if (oldSort == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        SortQuery sortQuery = new SortQuery();
        sortQuery.setDtIdAndeq(sort.getDtId());
        sortQuery.setColumnsIdAndeq(sort.getColumnsId());
        if (!(sortQuery.getDtIdAndeq().equals(oldSort.getDtId()) && sortQuery.getColumnsIdAndeq().equals(oldSort.getColumnsId()))) {
          if (!sortPersistent.isUnique(sortQuery)) {
            throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { sortPersistent.getNotUniqueErrorMessage(sortQuery) });
          }
        }
      }
      sortPersistent.batchUpdateSort(sorts);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void removeSort(Sort sort) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.removeSort ");
      log.debug("parameter sort is : " + sort);
    }
    try {
      if (sort == null || ToolUtil.isNullEntityAllFieldValue(sort)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sort ");
      }
      Set<Sort> sortSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(sort.getSortId())) {
        SortQuery sortQuery = ToolUtil.attributeReplication(SortQuery.class, sort);
        sortSet.addAll(sortPersistent.querySort(sortQuery));
      } else {
        Sort oldSort = sortPersistent.getSortByPk(sort.getSortId());
        if (oldSort == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        sortSet.add(oldSort);
      }
      if (ToolUtil.isNotEmpty(sortSet)) {
        sortPersistent.batchRemoveSort(sortSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchRemoveSort(Collection<Sort> sorts) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchRemoveSort ");
      log.debug("parameter sorts is : " + sorts);
    }
    try {
      if (ToolUtil.isEmpty(sorts)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sorts ");
      }
      Set<Sort> sortSet = new LinkedHashSet<>();
      for (Sort sort : sorts) {
        if (ToolUtil.isNullStr(sort.getSortId())) {
          SortQuery sortQuery = ToolUtil.attributeReplication(SortQuery.class, sort);
          sortSet.addAll(sortPersistent.querySort(sortQuery));
        } else {
          Sort oldSort = sortPersistent.getSortByPk(sort.getSortId());
          if (oldSort == null) {
            throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          sortSet.add(oldSort);
        }
      }
      if (ToolUtil.isNotEmpty(sortSet)) {
        sortPersistent.batchRemoveSort(sortSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Long getCountSort(SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getCountSort ");
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    try {
      return sortPersistent.getCountSort(sortQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Sort getSortByPk(java.lang.String sortId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getSortByPk ");
      log.debug("parameter sortId is : " + sortId);
    }
    try {
      if (ToolUtil.isNullStr(sortId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sortId ");
      }
      return sortPersistent.getSortByPk(sortId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Sort> getAllSort() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllSort ");
    }
    try {
      return sortPersistent.getAllSort();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Sort> pagingGetSort(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetSort ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return sortPersistent.pagingGetSort(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Sort> querySort(SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.querySort ");
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    try {
      return sortPersistent.querySort(sortQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Sort> pagingQuerySort(Parameter parameter, SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQuerySort ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return sortPersistent.pagingQuerySort(parameter, sortQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public SortVO getSortVOByPk(java.lang.String sortId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getSortVOByPk ");
      log.debug("parameter sortId is : " + sortId);
    }
    try {
      if (ToolUtil.isNullStr(sortId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " sortId ");
      }
      return sortPersistent.getSortVOByPk(sortId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<SortVO> getAllSortVO() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllSortVO ");
    }
    try {
      return sortPersistent.getAllSortVO();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<SortVO> pagingGetSortVO(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetSortVO ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return sortPersistent.pagingGetSortVO(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<SortVO> querySortVO(SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.querySortVO ");
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    try {
      return sortPersistent.querySortVO(sortQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<SortVO> pagingQuerySortVO(Parameter parameter, SortQuery sortQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQuerySortVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter sortQuery is : " + sortQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return sortPersistent.pagingQuerySortVO(parameter, sortQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @javax.annotation.Resource(name = "com.pro.code.plugin.QueryPersistent")
  private IQueryPersistent queryPersistent;

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveQuery(Query query) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.saveQuery ");
      log.debug("parameter query is : " + query);
    }
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      query.setQueryId(ToolUtil.getUUID());
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setQueryTypeAndeq(query.getQueryType());
      queryQuery.setDtIdAndeq(query.getDtId());
      queryQuery.setColumnsIdAndeq(query.getColumnsId());
      if (!queryPersistent.isUnique(queryQuery)) {
        throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { queryPersistent.getNotUniqueErrorMessage(queryQuery) });
      }
      queryPersistent.saveQuery(query);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchSaveQuery(Collection<Query> querys) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchSaveQuery ");
      log.debug("parameter querys is : " + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      for (Query query : querys) {
        if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
        }
        query.setQueryId(ToolUtil.getUUID());
        QueryQuery queryQuery = new QueryQuery();
        queryQuery.setQueryTypeAndeq(query.getQueryType());
        queryQuery.setDtIdAndeq(query.getDtId());
        queryQuery.setColumnsIdAndeq(query.getColumnsId());
        if (!queryPersistent.isUnique(queryQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { queryPersistent.getNotUniqueErrorMessage(queryQuery) });
        }
      }
      queryPersistent.batchSaveQuery(querys);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void updateQuery(Query query) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.updateQuery ");
      log.debug("parameter query is : " + query);
    }
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      if (ToolUtil.isNullStr(query.getQueryId())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " queryId ");
      }
      Query oldQuery = queryPersistent.getQueryByPk(query.getQueryId());
      if (oldQuery == null) {
        throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      QueryQuery queryQuery = new QueryQuery();
      queryQuery.setQueryTypeAndeq(query.getQueryType());
      queryQuery.setDtIdAndeq(query.getDtId());
      queryQuery.setColumnsIdAndeq(query.getColumnsId());
      if (!(queryQuery.getQueryTypeAndeq().equals(oldQuery.getQueryType()) && queryQuery.getDtIdAndeq().equals(oldQuery.getDtId()) && queryQuery.getColumnsIdAndeq().equals(oldQuery.getColumnsId()))) {
        if (!queryPersistent.isUnique(queryQuery)) {
          throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { queryPersistent.getNotUniqueErrorMessage(queryQuery) });
        }
      }
      queryPersistent.updateQuery(query);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchUpdateQuery(Collection<Query> querys) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchUpdateQuery ");
      log.debug("parameter querys is : " + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      for (Query query : querys) {
        if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
        }
        if (ToolUtil.isNullStr(query.getQueryId())) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " queryId ");
        }
        Query oldQuery = queryPersistent.getQueryByPk(query.getQueryId());
        if (oldQuery == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        QueryQuery queryQuery = new QueryQuery();
        queryQuery.setQueryTypeAndeq(query.getQueryType());
        queryQuery.setDtIdAndeq(query.getDtId());
        queryQuery.setColumnsIdAndeq(query.getColumnsId());
        if (!(queryQuery.getQueryTypeAndeq().equals(oldQuery.getQueryType()) && queryQuery.getDtIdAndeq().equals(oldQuery.getDtId()) && queryQuery.getColumnsIdAndeq().equals(oldQuery.getColumnsId()))) {
          if (!queryPersistent.isUnique(queryQuery)) {
            throw CodePluginException.getException(CodePluginException.FW_DATA_CONTENTION_ERROR, new String[] { queryPersistent.getNotUniqueErrorMessage(queryQuery) });
          }
        }
      }
      queryPersistent.batchUpdateQuery(querys);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void removeQuery(Query query) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.removeQuery ");
      log.debug("parameter query is : " + query);
    }
    try {
      if (query == null || ToolUtil.isNullEntityAllFieldValue(query)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " query ");
      }
      Set<Query> querySet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(query.getQueryId())) {
        QueryQuery queryQuery = ToolUtil.attributeReplication(QueryQuery.class, query);
        querySet.addAll(queryPersistent.queryQuery(queryQuery));
      } else {
        Query oldQuery = queryPersistent.getQueryByPk(query.getQueryId());
        if (oldQuery == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        querySet.add(oldQuery);
      }
      if (ToolUtil.isNotEmpty(querySet)) {
        queryPersistent.batchRemoveQuery(querySet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchRemoveQuery(Collection<Query> querys) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchRemoveQuery ");
      log.debug("parameter querys is : " + querys);
    }
    try {
      if (ToolUtil.isEmpty(querys)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " querys ");
      }
      Set<Query> querySet = new LinkedHashSet<>();
      for (Query query : querys) {
        if (ToolUtil.isNullStr(query.getQueryId())) {
          QueryQuery queryQuery = ToolUtil.attributeReplication(QueryQuery.class, query);
          querySet.addAll(queryPersistent.queryQuery(queryQuery));
        } else {
          Query oldQuery = queryPersistent.getQueryByPk(query.getQueryId());
          if (oldQuery == null) {
            throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          querySet.add(oldQuery);
        }
      }
      if (ToolUtil.isNotEmpty(querySet)) {
        queryPersistent.batchRemoveQuery(querySet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Long getCountQuery(QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getCountQuery ");
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    try {
      return queryPersistent.getCountQuery(queryQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Query getQueryByPk(java.lang.String queryId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getQueryByPk ");
      log.debug("parameter queryId is : " + queryId);
    }
    try {
      if (ToolUtil.isNullStr(queryId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " queryId ");
      }
      return queryPersistent.getQueryByPk(queryId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Query> getAllQuery() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllQuery ");
    }
    try {
      return queryPersistent.getAllQuery();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Query> pagingGetQuery(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetQuery ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return queryPersistent.pagingGetQuery(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<Query> queryQuery(QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryQuery ");
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    try {
      return queryPersistent.queryQuery(queryQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<Query> pagingQueryQuery(Parameter parameter, QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryQuery ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return queryPersistent.pagingQueryQuery(parameter, queryQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public QueryVO getQueryVOByPk(java.lang.String queryId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getQueryVOByPk ");
      log.debug("parameter queryId is : " + queryId);
    }
    try {
      if (ToolUtil.isNullStr(queryId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " queryId ");
      }
      return queryPersistent.getQueryVOByPk(queryId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<QueryVO> getAllQueryVO() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllQueryVO ");
    }
    try {
      return queryPersistent.getAllQueryVO();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<QueryVO> pagingGetQueryVO(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetQueryVO ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return queryPersistent.pagingGetQueryVO(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<QueryVO> queryQueryVO(QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryQueryVO ");
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    try {
      return queryPersistent.queryQueryVO(queryQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<QueryVO> pagingQueryQueryVO(Parameter parameter, QueryQuery queryQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryQueryVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter queryQuery is : " + queryQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return queryPersistent.pagingQueryQueryVO(parameter, queryQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @javax.annotation.Resource(name = "com.pro.code.plugin.VirtualColumnsPersistent")
  private IVirtualColumnsPersistent virtualColumnsPersistent;

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void saveVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.saveVirtualColumns ");
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      virtualColumns.setVirtualColumnsId(ToolUtil.getUUID());
      Dt sourceDt = dtPersistent.getDtByPk(virtualColumns.getSourceDtId());
      Dt targetDt = dtPersistent.getDtByPk(virtualColumns.getTargetDtId());
      Columns sourceColumns = columnsPersistent.getColumnsByPk(virtualColumns.getSourceColumnsId());
      Columns targetColumns = columnsPersistent.getColumnsByPk(virtualColumns.getTargetColumnsId());
      Columns targetDisplayColumns = columnsPersistent.getColumnsByPk(virtualColumns.getTargetDisplayColumnsId());
      Columns targetPkColumns = null;
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(virtualColumns.getTargetDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        targetPkColumns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
      }
      StringBuilder virtualColumnsSql = new StringBuilder("( SELECT ");
      // ( SELECT dt.DT_NAME FROM PRO_DT dt WHERE dt.DT_ID IS NOT NULL AND dt.DT_ID = sort.DT_ID ) AS DT_NAME
      virtualColumnsSql.append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetDisplayColumns.getColumnName())
          .append(" FROM ").append(targetDt.getDtName()).append(" ").append(targetDt.getInitialLowercaseEntityName())
          .append(" WHERE ").append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetPkColumns.getColumnName())
          .append(" IS NOT NULL AND ").append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetColumns.getColumnName())
          .append(" = ").append(sourceDt.getInitialLowercaseEntityName()).append(".").append(sourceColumns.getColumnName())
          .append(" ) AS ");
      if (ToolUtil.isNullStr(virtualColumns.getDisplayColumnsAlias())) {
        virtualColumnsSql.append(targetDisplayColumns.getColumnName());
      } else {
        virtualColumnsSql.append(virtualColumns.getDisplayColumnsAlias());
      }
      virtualColumns.setVirtualColumnsSql(virtualColumnsSql.toString());
      virtualColumnsPersistent.saveVirtualColumns(virtualColumns);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchSaveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchSaveVirtualColumns ");
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      for (VirtualColumns virtualColumns : virtualColumnss) {
        if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
        }
        virtualColumns.setVirtualColumnsId(ToolUtil.getUUID());
      }
      virtualColumnsPersistent.batchSaveVirtualColumns(virtualColumnss);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void updateVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.updateVirtualColumns ");
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnsId ");
      }
      VirtualColumns oldVirtualColumns = virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId());
      if (oldVirtualColumns == null) {
        throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
      }
      Dt sourceDt = dtPersistent.getDtByPk(virtualColumns.getSourceDtId());
      Dt targetDt = dtPersistent.getDtByPk(virtualColumns.getTargetDtId());
      Columns sourceColumns = columnsPersistent.getColumnsByPk(virtualColumns.getSourceColumnsId());
      Columns targetColumns = columnsPersistent.getColumnsByPk(virtualColumns.getTargetColumnsId());
      Columns targetDisplayColumns = columnsPersistent.getColumnsByPk(virtualColumns.getTargetDisplayColumnsId());
      Columns targetPkColumns = null;
      PkQuery pkQuery = new PkQuery();
      pkQuery.setDtId(virtualColumns.getTargetDtId());
      Collection<Pk> pkSet = pkPersistent.queryPk(pkQuery);
      if (ToolUtil.isNotEmpty(pkSet)) {
        targetPkColumns = columnsPersistent.getColumnsByPk(pkSet.iterator().next().getColumnsId());
      }
      StringBuilder virtualColumnsSql = new StringBuilder("( SELECT ");
      // ( SELECT dt.DT_NAME FROM PRO_DT dt WHERE dt.DT_ID IS NOT NULL AND dt.DT_ID = sort.DT_ID ) AS DT_NAME
      virtualColumnsSql.append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetDisplayColumns.getColumnName())
          .append(" FROM ").append(targetDt.getDtName()).append(" ").append(targetDt.getInitialLowercaseEntityName())
          .append(" WHERE ").append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetPkColumns.getColumnName())
          .append(" IS NOT NULL AND ").append(targetDt.getInitialLowercaseEntityName()).append(".").append(targetColumns.getColumnName())
          .append(" = ").append(sourceDt.getInitialLowercaseEntityName()).append(".").append(sourceColumns.getColumnName())
          .append(" ) AS ");
      if (ToolUtil.isNullStr(virtualColumns.getDisplayColumnsAlias())) {
        virtualColumnsSql.append(targetDisplayColumns.getColumnName());
      } else {
        virtualColumnsSql.append(virtualColumns.getDisplayColumnsAlias());
      }
      virtualColumns.setVirtualColumnsSql(virtualColumnsSql.toString());
      virtualColumnsPersistent.updateVirtualColumns(virtualColumns);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchUpdateVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchUpdateVirtualColumns ");
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      for (VirtualColumns virtualColumns : virtualColumnss) {
        if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
        }
        if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnsId ");
        }
        VirtualColumns oldVirtualColumns = virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId());
        if (oldVirtualColumns == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
      }
      virtualColumnsPersistent.batchUpdateVirtualColumns(virtualColumnss);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void removeVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.removeVirtualColumns ");
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      Set<VirtualColumns> virtualColumnsSet = new LinkedHashSet<>();
      if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
        VirtualColumnsQuery virtualColumnsQuery = ToolUtil.attributeReplication(VirtualColumnsQuery.class, virtualColumns);
        virtualColumnsSet.addAll(virtualColumnsPersistent.queryVirtualColumns(virtualColumnsQuery));
      } else {
        VirtualColumns oldVirtualColumns = virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId());
        if (oldVirtualColumns == null) {
          throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
        }
        virtualColumnsSet.add(oldVirtualColumns);
      }
      if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
        virtualColumnsPersistent.batchRemoveVirtualColumns(virtualColumnsSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  @org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRED, readOnly = false)
  public void batchRemoveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.batchRemoveVirtualColumns ");
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      Set<VirtualColumns> virtualColumnsSet = new LinkedHashSet<>();
      for (VirtualColumns virtualColumns : virtualColumnss) {
        if (ToolUtil.isNullStr(virtualColumns.getVirtualColumnsId())) {
          VirtualColumnsQuery virtualColumnsQuery = ToolUtil.attributeReplication(VirtualColumnsQuery.class, virtualColumns);
          virtualColumnsSet.addAll(virtualColumnsPersistent.queryVirtualColumns(virtualColumnsQuery));
        } else {
          VirtualColumns oldVirtualColumns = virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumns.getVirtualColumnsId());
          if (oldVirtualColumns == null) {
            throw CodePluginException.getException(CodePluginException.FW_UPDATE_DATA_NOT_FIND_ERROR);
          }
          virtualColumnsSet.add(oldVirtualColumns);
        }
      }
      if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
        virtualColumnsPersistent.batchRemoveVirtualColumns(virtualColumnsSet);
      }
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Long getCountVirtualColumns(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getCountVirtualColumns ");
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      return virtualColumnsPersistent.getCountVirtualColumns(virtualColumnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public VirtualColumns getVirtualColumnsByPk(java.lang.String virtualColumnsId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getVirtualColumnsByPk ");
      log.debug("parameter virtualColumnsId is : " + virtualColumnsId);
    }
    try {
      if (ToolUtil.isNullStr(virtualColumnsId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnsId ");
      }
      return virtualColumnsPersistent.getVirtualColumnsByPk(virtualColumnsId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<VirtualColumns> getAllVirtualColumns() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllVirtualColumns ");
    }
    try {
      return virtualColumnsPersistent.getAllVirtualColumns();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<VirtualColumns> pagingGetVirtualColumns(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetVirtualColumns ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return virtualColumnsPersistent.pagingGetVirtualColumns(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<VirtualColumns> queryVirtualColumns(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryVirtualColumns ");
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      return virtualColumnsPersistent.queryVirtualColumns(virtualColumnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<VirtualColumns> pagingQueryVirtualColumns(Parameter parameter, VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryVirtualColumns ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return virtualColumnsPersistent.pagingQueryVirtualColumns(parameter, virtualColumnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public VirtualColumnsVO getVirtualColumnsVOByPk(java.lang.String virtualColumnsId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getVirtualColumnsVOByPk ");
      log.debug("parameter virtualColumnsId is : " + virtualColumnsId);
    }
    try {
      if (ToolUtil.isNullStr(virtualColumnsId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnsId ");
      }
      return virtualColumnsPersistent.getVirtualColumnsVOByPk(virtualColumnsId);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<VirtualColumnsVO> getAllVirtualColumnsVO() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.getAllVirtualColumnsVO ");
    }
    try {
      return virtualColumnsPersistent.getAllVirtualColumnsVO();
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<VirtualColumnsVO> pagingGetVirtualColumnsVO(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingGetVirtualColumnsVO ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return virtualColumnsPersistent.pagingGetVirtualColumnsVO(parameter);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Collection<VirtualColumnsVO> queryVirtualColumnsVO(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.queryVirtualColumnsVO ");
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      return virtualColumnsPersistent.queryVirtualColumnsVO(virtualColumnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public Paging<VirtualColumnsVO> pagingQueryVirtualColumnsVO(Parameter parameter, VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call CodePluginService.pagingQueryVirtualColumnsVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      if (parameter == null || ToolUtil.isNullEntityAllFieldValue(parameter)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      return virtualColumnsPersistent.pagingQueryVirtualColumnsVO(parameter, virtualColumnsQuery);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

}
