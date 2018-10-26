package com.pro.code.plugin.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pro.code.plugin.entity.VirtualColumns;
import com.pro.code.plugin.exception.CodePluginException;
import com.pro.code.plugin.query.VirtualColumnsQuery;
import com.pro.code.plugin.service.ICodePluginService;
import com.pro.code.plugin.vo.VirtualColumnsVO;
import com.pro.tool.rest.ToolController;
import com.pro.tool.util.Parameter;
import com.pro.tool.util.Responses;
import com.pro.tool.util.ToolUtil;

@org.springframework.stereotype.Controller
@org.springframework.context.annotation.Scope("prototype")
@RequestMapping(value = { "pro/code/plugin/virtualColumns" })
public class VirtualColumnsController extends ToolController {

  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(VirtualColumnsController.class);

  @javax.annotation.Resource(name = "com.pro.code.plugin.CodePluginService")
  private ICodePluginService codePluginService;

  @RequestMapping(method = { RequestMethod.POST }, params = { ToolUtil.ACTION + ToolUtil.ACTION_SAVE })
  @ResponseBody
  public Responses<VirtualColumns> save(Parameter parameter, @RequestBody VirtualColumns virtualColumns) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.save ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      codePluginService.saveVirtualColumns(virtualColumns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.POST }, params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_SAVE })
  @ResponseBody
  public Responses<VirtualColumns> batchSave(Parameter parameter, @RequestBody List<VirtualColumns> virtualColumnss) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.batchSave ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      codePluginService.batchSaveVirtualColumns(virtualColumnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.PUT }, params = { ToolUtil.ACTION + ToolUtil.ACTION_UPDATE })
  @ResponseBody
  public Responses<VirtualColumns> update(Parameter parameter, @RequestBody VirtualColumns virtualColumns) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.update ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      codePluginService.updateVirtualColumns(virtualColumns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.PUT }, params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_UPDATE })
  @ResponseBody
  public Responses<VirtualColumns> batchUpdate(Parameter parameter, @RequestBody List<VirtualColumns> virtualColumnss) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.batchUpdate ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      codePluginService.batchUpdateVirtualColumns(virtualColumnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.DELETE }, params = { ToolUtil.ACTION + ToolUtil.ACTION_REMOVE })
  @ResponseBody
  public Responses<VirtualColumns> remove(Parameter parameter, @RequestBody VirtualColumns virtualColumns) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.remove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumns is : " + virtualColumns);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (virtualColumns == null || ToolUtil.isNullEntityAllFieldValue(virtualColumns)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumns ");
      }
      codePluginService.removeVirtualColumns(virtualColumns);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.DELETE }, params = { ToolUtil.ACTION + ToolUtil.ACTION_BATCH_REMOVE })
  @ResponseBody
  public Responses<VirtualColumns> batchRemove(Parameter parameter, @RequestBody List<VirtualColumns> virtualColumnss) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.batchRemove ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnss is : " + virtualColumnss);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isEmpty(virtualColumnss)) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnss ");
      }
      codePluginService.batchRemoveVirtualColumns(virtualColumnss);
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.GET }, params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_BY_PK })
  @ResponseBody
  public Responses<VirtualColumns> getByPk(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.getByPk ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<VirtualColumns> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnsId ");
      }
      responses.setData(codePluginService.getVirtualColumnsByPk(parameter.getPrimaryKey()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.POST })
  @ResponseBody
  public Responses<VirtualColumns> get(Parameter parameter, @RequestBody VirtualColumnsQuery virtualColumnsQuery) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.get ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    Responses<VirtualColumns> responses = new Responses<>(parameter);
    try {
      if (virtualColumnsQuery == null || ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.getAllVirtualColumns());
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingGetVirtualColumns(parameter));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      } else {
        if (ToolUtil.ACTION_GET_ALL.equals(parameter.getAction())) {
          responses.setData(codePluginService.queryVirtualColumns(virtualColumnsQuery));
        } else if (ToolUtil.ACTION_PAGING.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingQueryVirtualColumns(parameter, virtualColumnsQuery));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(method = { RequestMethod.GET }, params = { ToolUtil.ACTION + ToolUtil.ACTION_GET_VO_BY_PK })
  @ResponseBody
  public Responses<VirtualColumnsVO> getVOByPk(Parameter parameter) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.getVOByPk ");
      log.debug("parameter parameter is : " + parameter);
    }
    Responses<VirtualColumnsVO> responses = new Responses<>();
    try {
      if (ToolUtil.isNullStr(parameter.getPrimaryKey())) {
        throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_NULL_ERROR, " virtualColumnsId ");
      }
      responses.setData(codePluginService.getVirtualColumnsVOByPk(parameter.getPrimaryKey()));
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

  @RequestMapping(value = { "vo" }, method = { RequestMethod.POST })
  @ResponseBody
  public Responses<VirtualColumnsVO> getVO(Parameter parameter, @RequestBody VirtualColumnsQuery virtualColumnsQuery) {
    if (log.isDebugEnabled()) {
      log.debug("Staring call VirtualColumnsController.getVO ");
      log.debug("parameter parameter is : " + parameter);
      log.debug("parameter virtualColumnsQuery is : " + virtualColumnsQuery);
    }
    Responses<VirtualColumnsVO> responses = new Responses<>(parameter);
    try {
      if (virtualColumnsQuery == null || ToolUtil.isNullEntityAllFieldValue(virtualColumnsQuery)) {
        if (ToolUtil.ACTION_GET_ALL_VO.equals(parameter.getAction())) {
          responses.setData(codePluginService.getAllVirtualColumnsVO());
        } else if (ToolUtil.ACTION_PAGING_VO.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingGetVirtualColumnsVO(parameter));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      } else {
        if (ToolUtil.ACTION_GET_ALL_VO.equals(parameter.getAction())) {
          responses.setData(codePluginService.queryVirtualColumnsVO(virtualColumnsQuery));
        } else if (ToolUtil.ACTION_PAGING_VO.equals(parameter.getAction())) {
          responses.setData(codePluginService.pagingQueryVirtualColumnsVO(parameter, virtualColumnsQuery));
        } else {
          throw CodePluginException.getException(CodePluginException.FW_PARAMETER_IS_ERROR, " action ");
        }
      }
    } catch (Exception e) {
      if (log.isErrorEnabled()) {
        log.error(e.getMessage(), e);
      }
      responses.setException(e);
    }
    return responses;
  }

}
