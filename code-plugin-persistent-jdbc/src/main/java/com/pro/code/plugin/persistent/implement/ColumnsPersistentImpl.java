package com.pro.code.plugin.persistent.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.pro.code.plugin.entity.Columns;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.persistent.IColumnsPersistent;
import com.pro.code.plugin.query.ColumnsQuery;
import com.pro.code.plugin.vo.ColumnsVO;
import com.pro.tool.persistent.implement.ToolPersistent;
import com.pro.tool.util.Paging;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Repository("com.pro.code.plugin.ColumnsPersistent")
public class ColumnsPersistentImpl extends ToolPersistent implements IColumnsPersistent {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(ColumnsPersistentImpl.class);

  public static final String TABLE_ALIAS = "columns";

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

  public static final String COLUMN_COLUMNS_ID = "columnsId";
  public static final String COLUMN_COLUMN_NAME = "columnName";
  public static final String COLUMN_COLUMN_NAME_ANNOTATION = "columnNameAnnotation";
  public static final String COLUMN_DATA_TYPE = "dataType";
  public static final String COLUMN_IS_NULL = "isNull";
  public static final String COLUMN_INITIAL_CASE_COLUMN_NAME = "initialCaseColumnName";
  public static final String COLUMN_INITIAL_LOWERCASE_COLUMN_NAME = "initialLowercaseColumnName";
  public static final String COLUMN_WEIGHT_ORDER = "weightOrder";
  public static final String COLUMN_DT_ID = "dtId";

  static {
    PRIMARY_KEY.add(COLUMN_COLUMNS_ID);

    COLUMNS.add(COLUMN_COLUMNS_ID);
    COLUMNS.add(COLUMN_COLUMN_NAME);
    COLUMNS.add(COLUMN_COLUMN_NAME_ANNOTATION);
    COLUMNS.add(COLUMN_DATA_TYPE);
    COLUMNS.add(COLUMN_IS_NULL);
    COLUMNS.add(COLUMN_INITIAL_CASE_COLUMN_NAME);
    COLUMNS.add(COLUMN_INITIAL_LOWERCASE_COLUMN_NAME);
    COLUMNS.add(COLUMN_WEIGHT_ORDER);
    COLUMNS.add(COLUMN_DT_ID);

    COLUMNS_PARAMETER.put(COLUMN_COLUMNS_ID, "COLUMNS_ID");
    COLUMNS_PARAMETER.put(COLUMN_COLUMN_NAME, "COLUMN_NAME");
    COLUMNS_PARAMETER.put(COLUMN_COLUMN_NAME_ANNOTATION, "COLUMN_NAME_ANNOTATION");
    COLUMNS_PARAMETER.put(COLUMN_DATA_TYPE, "DATA_TYPE");
    COLUMNS_PARAMETER.put(COLUMN_IS_NULL, "IS_NULL");
    COLUMNS_PARAMETER.put(COLUMN_INITIAL_CASE_COLUMN_NAME, "INITIAL_CASE_COLUMN_NAME");
    COLUMNS_PARAMETER.put(COLUMN_INITIAL_LOWERCASE_COLUMN_NAME, "INITIAL_LOWERCASE_COLUMN_NAME");
    COLUMNS_PARAMETER.put(COLUMN_WEIGHT_ORDER, "WEIGHT_ORDER");
    COLUMNS_PARAMETER.put(COLUMN_DT_ID, "DT_ID");

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
  public void saveColumns(Columns columns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.saveColumns ");
      log.debug("parameter columns is : " + columns);
    }
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      this.insert(INSERT_SQL, columns);
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
  public void batchSaveColumns(Collection<Columns> columnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.batchSaveColumns ");
      log.debug("parameter columnss is : " + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnss ");
      }
      this.insert(INSERT_SQL, columnss);
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
  public void updateColumns(Columns columns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.updateColumns ");
      log.debug("parameter columns is : " + columns);
    }
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      this.update(UPDATE_SQL, columns);
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
  public void batchUpdateColumns(Collection<Columns> columnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.batchUpdateColumns ");
      log.debug("parameter columnss is : " + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnss ");
      }
      this.update(UPDATE_SQL, columnss);
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
  public void removeColumns(Columns columns) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.removeColumns ");
      log.debug("parameter columns is : " + columns);
    }
    try {
      if (columns == null || ToolUtil.isNullEntityAllFieldValue(columns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columns ");
      }
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, columns.getColumnsId());
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
  public void batchRemoveColumns(Collection<Columns> columnss) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.batchRemoveColumns ");
      log.debug("parameter columnss is : " + columnss);
    }
    try {
      if (ToolUtil.isEmpty(columnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnss ");
      }
      this.del(DEL_SQL_BY_PK, columnss);
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
  public Long getCountColumns(ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.getCountColumns ");
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      return this.queryCount(countSql, columnsQuery, Long.class);
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
  public Columns getColumnsByPk(java.lang.String columnsId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.getColumnsByPk ");
      log.debug("parameter columnsId is : " + columnsId);
    }
    try {
      if (ToolUtil.isNullStr(columnsId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnsId ");
      }
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, columnsId);
        }
      }
      Collection<Columns> columnsSet = this.query(querySql, mapSqlParameterSource, Columns.class);
      return ToolUtil.isNotEmpty(columnsSet) ? columnsSet.iterator().next() : null;
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
  public Collection<Columns> getAllColumns() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.getAllColumns ");
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Columns.class);
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
  public Paging<Columns> pagingGetColumns(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.pagingGetColumns ");
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
      Paging<Columns> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Columns> columnsSet = this.query(querySql, Columns.class);
        if (ToolUtil.isNotEmpty(columnsSet)) {
          Set<String> inColumnsId = new LinkedHashSet<>();
          for (Columns columns : columnsSet) {
            inColumnsId.add(columns.getColumnsId());
          }
          ColumnsQuery columnsQuery = new ColumnsQuery();
          columnsQuery.setColumnsIdAndin(new ArrayList<>(inColumnsId));
          Collection<Columns> rColumnsSet = this.queryColumns(columnsQuery);
          if (ToolUtil.isNotEmpty(rColumnsSet)) {
            paging.setData(rColumnsSet);
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
  public Collection<Columns> queryColumns(ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.queryColumns ");
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, Columns.class, columnsQuery);
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
  public Paging<Columns> pagingQueryColumns(Parameter parameter, ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.pagingQueryColumns ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columnsQuery is : " + columnsQuery);
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
      Paging<Columns> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Columns> columnsSet = this.query(querySql, Columns.class, columnsQuery);
        if (ToolUtil.isNotEmpty(columnsSet)) {
          Set<String> inColumnsId = new LinkedHashSet<>();
          for (Columns columns : columnsSet) {
            inColumnsId.add(columns.getColumnsId());
          }
          ColumnsQuery rColumnsQuery = new ColumnsQuery();
          rColumnsQuery.setColumnsIdAndin(new ArrayList<>(inColumnsId));
          Collection<Columns> rColumnsSet = this.queryColumns(rColumnsQuery);
          if (ToolUtil.isNotEmpty(rColumnsSet)) {
            paging.setData(rColumnsSet);
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
  public boolean isUnique(ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.isUnique ");
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        countSql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      Long count = this.queryCount(countSql, columnsQuery, Long.class);
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
  public String getNotUniqueErrorMessage(ColumnsQuery columnsQuery) throws CodePluginException {
    return this.getNotUniqueErrorMsg(COLUMNS, COLUMNS_PARAMETER, columnsQuery).toString();
  }

  @Override
  public ColumnsVO getColumnsVOByPk(java.lang.String columnsId) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.getColumnsVOByPk ");
      log.debug("parameter columnsId is : " + columnsId);
    }
    try {
      if (ToolUtil.isNullStr(columnsId)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " columnsId ");
      }
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getByPkSql(COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
      if (ToolUtil.isNotEmpty(PRIMARY_KEY) && PRIMARY_KEY.size() == 1) {
        for (String pk : PRIMARY_KEY) {
          mapSqlParameterSource.addValue(pk, columnsId);
        }
      }
      Collection<ColumnsVO> columnsVOSet = this.query(querySql, mapSqlParameterSource, ColumnsVO.class);
      return ToolUtil.isNotEmpty(columnsVOSet) ? columnsVOSet.iterator().next() : null;
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
  public Collection<ColumnsVO> getAllColumnsVO() throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.getAllColumnsVO ");
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, ColumnsVO.class);
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
  public Paging<ColumnsVO> pagingGetColumnsVO(Parameter parameter) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.pagingGetColumnsVO ");
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
      Paging<ColumnsVO> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Columns> columnsSet = this.query(querySql, Columns.class);
        if (ToolUtil.isNotEmpty(columnsSet)) {
          Set<String> inColumnsId = new LinkedHashSet<>();
          for (Columns columns : columnsSet) {
            inColumnsId.add(columns.getColumnsId());
          }
          ColumnsQuery columnsQuery = new ColumnsQuery();
          columnsQuery.setColumnsIdAndin(new ArrayList<>(inColumnsId));
          Collection<ColumnsVO> rColumnsVOSet = this.queryColumnsVO(columnsQuery);
          if (ToolUtil.isNotEmpty(rColumnsVOSet)) {
            paging.setData(rColumnsVOSet);
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
  public Collection<ColumnsVO> queryColumnsVO(ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.queryColumnsVO ");
      log.debug("parameter columnsQuery is : " + columnsQuery);
    }
    try {
      StringBuilder querySql = new StringBuilder(SELECT_VO_SQL);
      if (!ToolUtil.isNullEntityAllFieldValue(columnsQuery)) {
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
      }
      querySql.append(this.getSortSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS));
      return this.query(querySql, ColumnsVO.class, columnsQuery);
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
  public Paging<ColumnsVO> pagingQueryColumnsVO(Parameter parameter, ColumnsQuery columnsQuery) throws CodePluginException {
    if (log.isDebugEnabled()) {
      log.debug("Staring call ColumnsPersistent.pagingQueryColumnsVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter columnsQuery is : " + columnsQuery);
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
      Paging<ColumnsVO> paging = new Paging<>(parameter);
      StringBuilder countSql = new StringBuilder(COUNT_SQL);
      Long count = this.queryCount(countSql, Long.class);
      paging.setCount(count);
      if (count > 0) {
        StringBuilder querySql = new StringBuilder(this.getPagingSql(TABLE_NAME, COLUMNS_PARAMETER, PRIMARY_KEY, TABLE_ALIAS));
        querySql.append(this.getQuerySql(COLUMNS, COLUMNS_PARAMETER, TABLE_ALIAS, columnsQuery));
        querySql.append(this.getPagingSql(SORT, COLUMNS_PARAMETER, TABLE_ALIAS, paging));
        Collection<Columns> columnsSet = this.query(querySql, Columns.class, columnsQuery);
        if (ToolUtil.isNotEmpty(columnsSet)) {
          Set<String> inColumnsId = new LinkedHashSet<>();
          for (Columns columns : columnsSet) {
            inColumnsId.add(columns.getColumnsId());
          }
          ColumnsQuery rColumnsQuery = new ColumnsQuery();
          rColumnsQuery.setColumnsIdAndin(new ArrayList<>(inColumnsId));
          Collection<ColumnsVO> rColumnsVOSet = this.queryColumnsVO(rColumnsQuery);
          if (ToolUtil.isNotEmpty(rColumnsVOSet)) {
            paging.setData(rColumnsVOSet);
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
