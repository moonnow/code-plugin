package com.pro.code.plugin.persistent.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.pro.code.plugin.entity.VirtualColumns;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.persistent.IVirtualColumnsPersistent;
import com.pro.code.plugin.query.VirtualColumnsQuery;
import com.pro.code.plugin.vo.VirtualColumnsVO;
import com.pro.tool.persistent.implement.ToolPersistent;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Repository("com.pro.code.plugin.VirtualColumnsPersistent")
public class VirtualColumnsPersistentImpl extends ToolPersistent implements IVirtualColumnsPersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(VirtualColumnsPersistentImpl.class);

  public static final String TABLE_ALIAS = "virtualColumns";

  public static final LinkedHashSet<String> PRIMARY_KEY = new LinkedHashSet<>();
  public static final LinkedHashSet<String> COLUMNS = new LinkedHashSet<>();
  public static final LinkedHashMap<String, String> COLUMNS_PARAMETER = new LinkedHashMap<>();
  public static final LinkedHashSet<String> VIRTUAL_COLUMNS = new LinkedHashSet<>();
  public static final LinkedHashMap<String, String> SORT = new LinkedHashMap<>();

  private static StringBuilder INSERT_SQL = new StringBuilder();
  private static StringBuilder UPDATE_SQL = new StringBuilder();
  private static StringBuilder DEL_SQL_BY_PK = new StringBuilder();
  public static StringBuilder SELECT_SQL = new StringBuilder();
  public static StringBuilder SELECT_VO_SQL = new StringBuilder();
  public static StringBuilder COUNT_SQL = new StringBuilder();
  public static StringBuilder COLUMN_LIST_ALIAS = new StringBuilder();
  public static StringBuilder COLUMN_LIST_NOT_ALIAS = new StringBuilder();

  public static final String COLUMN_VIRTUAL_COLUMNS_ID = "virtualColumnsId";
  public static final String COLUMN_SOURCE_DT_ID = "sourceDtId";
  public static final String COLUMN_TARGET_DT_ID = "targetDtId";
  public static final String COLUMN_SOURCE_COLUMNS_ID = "sourceColumnsId";
  public static final String COLUMN_TARGET_COLUMNS_ID = "targetColumnsId";
  public static final String COLUMN_TARGET_DISPLAY_COLUMNS_ID = "targetDisplayColumnsId";
  public static final String COLUMN_DISPLAY_COLUMNS_ALIAS = "displayColumnsAlias";
  public static final String COLUMN_VIRTUAL_COLUMNS_SQL = "virtualColumnsSql";
  public static final String COLUMN_WEIGHT_ORDER = "weightOrder";

  public static final String VIRTUAL_COLUMN_DT_NAME = "( SELECT dt.DT_NAME FROM PRO_DT dt WHERE dt.DT_ID IS NOT NULL AND dt.DT_ID = virtualColumns.SOURCE_DT_ID ) AS DT_NAME";
  public static final String VIRTUAL_COLUMN_COLUMN_NAME = "( SELECT columns.COLUMN_NAME FROM PRO_COLUMNS columns WHERE columns.COLUMNS_ID IS NOT NULL AND columns.COLUMNS_ID = virtualColumns.TARGET_DISPLAY_COLUMNS_ID ) AS COLUMN_NAME";

  static {
    PRIMARY_KEY.add(COLUMN_VIRTUAL_COLUMNS_ID);

    COLUMNS.add(COLUMN_VIRTUAL_COLUMNS_ID);
    COLUMNS.add(COLUMN_SOURCE_DT_ID);
    COLUMNS.add(COLUMN_TARGET_DT_ID);
    COLUMNS.add(COLUMN_SOURCE_COLUMNS_ID);
    COLUMNS.add(COLUMN_TARGET_COLUMNS_ID);
    COLUMNS.add(COLUMN_TARGET_DISPLAY_COLUMNS_ID);
    COLUMNS.add(COLUMN_DISPLAY_COLUMNS_ALIAS);
    COLUMNS.add(COLUMN_VIRTUAL_COLUMNS_SQL);
    COLUMNS.add(COLUMN_WEIGHT_ORDER);

    COLUMNS_PARAMETER.put(COLUMN_VIRTUAL_COLUMNS_ID, "VIRTUAL_COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_SOURCE_DT_ID, "SOURCE_DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_TARGET_DT_ID, "TARGET_DT_ID");
    COLUMNS_PARAMETER.put(COLUMN_SOURCE_COLUMNS_ID, "SOURCE_COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_TARGET_COLUMNS_ID, "TARGET_COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_TARGET_DISPLAY_COLUMNS_ID, "TARGET_DISPLAY_COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_DISPLAY_COLUMNS_ALIAS, "DISPLAY_COLUMNS_ALIAS");
    COLUMNS_PARAMETER.put(COLUMN_VIRTUAL_COLUMNS_SQL, "VIRTUAL_COLUMNS_SQL");
    COLUMNS_PARAMETER.put(COLUMN_WEIGHT_ORDER, "WEIGHT_ORDER");

    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_DT_NAME);
    VIRTUAL_COLUMNS.add(VIRTUAL_COLUMN_COLUMN_NAME);

    SORT.put(COLUMN_WEIGHT_ORDER, ASC);

    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    SELECT_VO_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
    COLUMN_LIST_ALIAS = getColumnList(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS);
    COLUMN_LIST_NOT_ALIAS = getColumnList(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
  }

//  @PostConstruct
//  private void init() {
//    super.init(TABLE_NAME, PRIMARY_KEY, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, SORT);
//    INSERT_SQL = getInsertSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
//    UPDATE_SQL = getUpdateSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY);
//    DEL_SQL_BY_PK = getDelSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY);
//    SELECT_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
//    SELECT_VO_SQL = getSelectSql(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, VIRTUAL_COLUMNS, PRIMARY_KEY, TABLE_ALIAS);
//    COUNT_SQL = getCountSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS);
//    COLUMN_LIST_ALIAS = getColumnList(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS);
//    COLUMN_LIST_NOT_ALIAS = getColumnList(TABLE_NAME, COLUMNS, COLUMNS_PARAMETER);
//  }

  @Override
  public void saveVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsPersistent.saveVirtualColumns ");
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      this.insert(INSERT_SQL, virtualColumns);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void batchSaveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsPersistent.batchSaveVirtualColumns ");
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      this.insert(INSERT_SQL, virtualColumnss);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void updateVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsPersistent.updateVirtualColumns ");
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      this.update(UPDATE_SQL, virtualColumns);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void batchUpdateVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsPersistent.batchUpdateVirtualColumns ");
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      this.update(UPDATE_SQL, virtualColumnss);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void removeVirtualColumns(VirtualColumns virtualColumns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsPersistent.removeVirtualColumns ");
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, virtualColumns.getVirtualColumnsId());
        }
      }
      this.del(DEL_SQL_BY_PK, mapSqlParameterSource);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public void batchRemoveVirtualColumns(Collection<VirtualColumns> virtualColumnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsPersistent.batchRemoveVirtualColumns ");
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      this.del(DEL_SQL_BY_PK, virtualColumnss);
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.getCountVirtualColumns ");
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      return this.queryCount(countSql, virtualColumnsQuery, Long.class);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.getVirtualColumnsByPk ");
      log.debug("parameter virtualColumnsId is : " + virtualColumnsId);
    }
    try {
      if (ToolUtil.isNullStr(virtualColumnsId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnsId ");
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, virtualColumnsId);
        }
      }
      Collection<VirtualColumns> virtualColumnsSet = this.query(querySql, mapSqlParameterSource, VirtualColumns.class);
      return ToolUtil.isNotEmpty(virtualColumnsSet) ? virtualColumnsSet.iterator().next() : null;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.getAllVirtualColumns ");
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, VirtualColumns.class);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.pagingGetVirtualColumns ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      if (parameter.getRows() < 0) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " rows ", " 大于等于0" });
      }
      if (parameter.getPage() < 1) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page ", " 大于等于1" });
      }
      Paging<VirtualColumns> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<VirtualColumns> virtualColumnsSet = this.query(querySql, VirtualColumns.class);
        if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
          Set<String> inVirtualColumnsId = new LinkedHashSet<>();
          for (VirtualColumns virtualColumns : virtualColumnsSet) {
            inVirtualColumnsId.add(virtualColumns.getVirtualColumnsId());
          }
          VirtualColumnsQuery virtualColumnsQuery = new VirtualColumnsQuery();
          virtualColumnsQuery.setVirtualColumnsIdAndin(new ArrayList<>(inVirtualColumnsId));
          Collection<VirtualColumns> rVirtualColumnsSet = this.queryVirtualColumns(virtualColumnsQuery);
          if (ToolUtil.isNotEmpty(rVirtualColumnsSet)) {
            paging.setData(rVirtualColumnsSet);
          }
        }
      }
      return paging;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.queryVirtualColumns ");
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, VirtualColumns.class, virtualColumnsQuery);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.pagingQueryVirtualColumns ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      if (parameter == null) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      if (parameter.getRows() < 0) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " rows ", " 大于等于0" });
      }
      if (parameter.getPage() < 1) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page ", " 大于等于1" });
      }
      Paging<VirtualColumns> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<VirtualColumns> virtualColumnsSet = this.query(querySql, VirtualColumns.class, virtualColumnsQuery);
        if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
          Set<String> inVirtualColumnsId = new LinkedHashSet<>();
          for (VirtualColumns virtualColumns : virtualColumnsSet) {
            inVirtualColumnsId.add(virtualColumns.getVirtualColumnsId());
          }
          VirtualColumnsQuery rVirtualColumnsQuery = new VirtualColumnsQuery();
          rVirtualColumnsQuery.setVirtualColumnsIdAndin(new ArrayList<>(inVirtualColumnsId));
          Collection<VirtualColumns> rVirtualColumnsSet = this.queryVirtualColumns(rVirtualColumnsQuery);
          if (ToolUtil.isNotEmpty(rVirtualColumnsSet)) {
            paging.setData(rVirtualColumnsSet);
          }
        }
      }
      return paging;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public boolean isUnique(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsPersistent.isUnique ");
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      Long count = this.queryCount(countSql, virtualColumnsQuery, Long.class);
      return count < 1;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

  @Override
  public String getNotUniqueErrorMessage(VirtualColumnsQuery virtualColumnsQuery) throws CodePluginException {
    return this.getNotUniqueErrorMsg(COLUMNS, COLUMNS_PARAMETER, virtualColumnsQuery).toString();
  }

  @Override
  public VirtualColumnsVO getVirtualColumnsVOByPk(java.lang.String virtualColumnsId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsPersistent.getVirtualColumnsVOByPk ");
      log.debug("parameter virtualColumnsId is : " + virtualColumnsId);
    }
    try {
      if (ToolUtil.isNullStr(virtualColumnsId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnsId ");
      }
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, virtualColumnsId);
        }
      }
      Collection<VirtualColumnsVO> virtualColumnsVOSet = this.query(querySql, mapSqlParameterSource, VirtualColumnsVO.class);
      return ToolUtil.isNotEmpty(virtualColumnsVOSet) ? virtualColumnsVOSet.iterator().next() : null;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.getAllVirtualColumnsVO ");
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, VirtualColumnsVO.class);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.pagingGetVirtualColumnsVO ");
      log.debug("parameter parameter is : " + parameter);
    }
    try {
      if (parameter == null) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      if (parameter.getRows() < 0) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " rows ", " 大于等于0" });
      }
      if (parameter.getPage() < 1) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page ", " 大于等于1" });
      }
      Paging<VirtualColumnsVO> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<VirtualColumns> virtualColumnsSet = this.query(querySql, VirtualColumns.class);
        if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
          Set<String> inVirtualColumnsId = new LinkedHashSet<>();
          for (VirtualColumns virtualColumns : virtualColumnsSet) {
            inVirtualColumnsId.add(virtualColumns.getVirtualColumnsId());
          }
          VirtualColumnsQuery virtualColumnsQuery = new VirtualColumnsQuery();
          virtualColumnsQuery.setVirtualColumnsIdAndin(new ArrayList<>(inVirtualColumnsId));
          Collection<VirtualColumnsVO> rVirtualColumnsVOSet = this.queryVirtualColumnsVO(virtualColumnsQuery);
          if (ToolUtil.isNotEmpty(rVirtualColumnsVOSet)) {
            paging.setData(rVirtualColumnsVOSet);
          }
        }
      }
      return paging;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.queryVirtualColumnsVO ");
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, VirtualColumnsVO.class, virtualColumnsQuery);
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
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
      log.debug("Staring call VirtualColumnsPersistent.pagingQueryVirtualColumnsVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    try {
      if (parameter == null) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " parameter ");
      }
      if (parameter.getRows() < 0) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " rows ", " 大于等于0" });
      }
      if (parameter.getPage() < 1) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_CONT_LONG_ERROR, new String[] { " page ", " 大于等于1" });
      }
      Paging<VirtualColumnsVO> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, virtualColumnsQuery));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<VirtualColumns> virtualColumnsSet = this.query(querySql, VirtualColumns.class, virtualColumnsQuery);
        if (ToolUtil.isNotEmpty(virtualColumnsSet)) {
          Set<String> inVirtualColumnsId = new LinkedHashSet<>();
          for (VirtualColumns virtualColumns : virtualColumnsSet) {
            inVirtualColumnsId.add(virtualColumns.getVirtualColumnsId());
          }
          VirtualColumnsQuery rVirtualColumnsQuery = new VirtualColumnsQuery();
          rVirtualColumnsQuery.setVirtualColumnsIdAndin(new ArrayList<>(inVirtualColumnsId));
          Collection<VirtualColumnsVO> rVirtualColumnsVOSet = this.queryVirtualColumnsVO(rVirtualColumnsQuery);
          if (ToolUtil.isNotEmpty(rVirtualColumnsVOSet)) {
            paging.setData(rVirtualColumnsVOSet);
          }
        }
      }
      return paging;
    } catch (CodePluginException e) {
      if (log.isErrorEnabled()) {
        log.error(e);
      }
      throw e;
    } catch (org.springframework.dao.DataAccessException e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_DAO_ACCESS_ERROR, e.getMessage());
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      throw CodePluginException.getException(e, CodePluginException.FW_ERROR, e.getMessage());
    }
  }

}
