package com.sinosoft.ie.booster.workflow.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysPositionEntity;
import com.sinosoft.ie.booster.admin.api.feign.*;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.admin.api.model.message.SentMessageModel;
import com.sinosoft.ie.booster.common.core.constant.SecurityConstants;
import com.sinosoft.ie.booster.common.core.constant.enums.DataSourceEnum;
import com.sinosoft.ie.booster.common.core.entity.BaseEntity;
import com.sinosoft.ie.booster.common.core.exception.WorkFlowException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.constant.type.IntegerNumber;
import com.sinosoft.ie.booster.visualdev.constant.type.MethodType;
import com.sinosoft.ie.booster.visualdev.constant.type.RequestType;
import com.sinosoft.ie.booster.visualdev.constant.type.SortType;
import com.sinosoft.ie.booster.visualdev.model.base.FormDataModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.config.ConfigModel;
import com.sinosoft.ie.booster.visualdev.util.DataSourceUtil;
import com.sinosoft.ie.booster.visualdev.util.DateUtil;
import com.sinosoft.ie.booster.visualdev.util.HttpUtil;
import com.sinosoft.ie.booster.visualdev.util.PageUtil;
import com.sinosoft.ie.booster.workflow.entity.*;
import com.sinosoft.ie.booster.workflow.enums.FlowMessageEnum;
import com.sinosoft.ie.booster.workflow.enums.FlowNodeEnum;
import com.sinosoft.ie.booster.workflow.enums.FlowTaskOperatorEnum;
import com.sinosoft.ie.booster.workflow.enums.FlowTaskStatusEnum;
import com.sinosoft.ie.booster.workflow.mapper.FlowTaskMapper;
import com.sinosoft.ie.booster.workflow.model.FlowHandleModel;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode.ChildNode;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.childnode.Properties;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.nodejson.ChildNodeList;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.nodejson.ConditionList;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.nodejson.Custom;
import com.sinosoft.ie.booster.workflow.model.flowengine.shuntjson.nodejson.DateProperties;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTableModel;
import com.sinosoft.ie.booster.workflow.model.flowtask.FlowTaskWaitListModel;
import com.sinosoft.ie.booster.workflow.model.flowtask.PaginationFlowTask;
import com.sinosoft.ie.booster.workflow.service.*;
import com.sinosoft.ie.booster.workflow.util.FlowDataUtil;
import com.sinosoft.ie.booster.workflow.util.FlowJsonUtil;
import com.sinosoft.ie.booster.workflow.util.FlowNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程任务
 *
 * @author booster code generator
 * @since 2021-09-23
 */
@Service
public class FlowTaskServiceImpl extends ServiceImpl<FlowTaskMapper, FlowTaskEntity> implements FlowTaskService {

	@Autowired
	private RemoteUserService usersApi;
	@Autowired
	private RemotePositionService positionApi;
	@Autowired
	private RemoteDeptService organizeApi;
	@Autowired
	private RemoteDictService dictionaryDataApi;
	@Autowired
	private NoticeApi noticeApi;
	@Autowired
	private FlowDelegateService flowDelegateService;
	@Autowired
	private FlowTaskNodeService flowTaskNodeService;
	@Autowired
	private FlowTaskOperatorService flowTaskOperatorService;
	@Autowired
	private FlowTaskOperatorRecordService flowTaskOperatorRecordService;
	@Autowired
	private FlowTaskCirculateService flowTaskCirculateService;
	@Autowired
	private FlowEngineService flowEngineService;
	@Autowired
	private DataSourceUtil dataSourceUtils;
	@Autowired
	private FlowDataUtil flowDataUtil;
	@Autowired
	private OAuth2RestTemplate restTemplate;

	/**
	 * 节点id
	 **/
	private String taskNodeId = "taskNodeId";
	/**
	 * 节点状态
	 **/
	private String handleStatus = "handleStatus";
	/**
	 * 任务id
	 **/
	private String taskId = "taskId";
	/**
	 * 任务状态
	 **/
	private String taskStatus = "taskStatus";

	@Override
	public List<FlowTaskEntity> getMonitorList(PaginationFlowTask paginationFlowTask) {
		QueryWrapper<FlowTaskEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().gt(FlowTaskEntity::getStatus, 0);
		//关键字（流程名称、流程编码）
		String keyWord = paginationFlowTask.getKeyword() != null ? paginationFlowTask.getKeyword() : null;
		if (!StrUtil.isEmpty(keyWord)) {
			queryWrapper.lambda().and(
					t -> t.like(FlowTaskEntity::getEncode, keyWord)
							.or().like(FlowTaskEntity::getFullName, keyWord)
			);
		}
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationFlowTask.getStartTime() != null ? paginationFlowTask.getStartTime() : null;
		String endTime = paginationFlowTask.getEndTime() != null ? paginationFlowTask.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			Date startTimes = new Date(Long.parseLong(startTime));
			Date endTimes = DateUtil.dateAddDays(new Date(Long.parseLong(endTime)), 1);
			queryWrapper.lambda().ge(FlowTaskEntity::getCreateTime, startTimes).le(FlowTaskEntity::getCreateTime, endTimes);
		}
		//所属流程
		String flowId = paginationFlowTask.getFlowId() != null ? paginationFlowTask.getFlowId() : null;
		if (!StrUtil.isEmpty(flowId)) {
			queryWrapper.lambda().eq(FlowTaskEntity::getFlowId, flowId);
		}
		//所属分类
		String flowCategory = paginationFlowTask.getFlowCategory() != null ? paginationFlowTask.getFlowCategory() : null;
		if (!StrUtil.isEmpty(flowCategory)) {
			queryWrapper.lambda().eq(FlowTaskEntity::getFlowCategory, flowCategory);
		}
		//发起人员
		String creatorUser = paginationFlowTask.getCreateBy() != null ? paginationFlowTask.getCreateBy() : null;
		if (!StrUtil.isEmpty(creatorUser)) {
			queryWrapper.lambda().eq(FlowTaskEntity::getCreateBy, creatorUser);
		}
		//排序
		if (SortType.DESC.equalsIgnoreCase(paginationFlowTask.getSort())) {
			queryWrapper.lambda().orderByDesc(FlowTaskEntity::getCreateTime);
		} else {
			queryWrapper.lambda().orderByAsc(FlowTaskEntity::getCreateTime);
		}
		Page<FlowTaskEntity> page = new Page<>(paginationFlowTask.getCurrentPage(), paginationFlowTask.getPageSize());
		IPage<FlowTaskEntity> flowTaskEntityPage = this.page(page, queryWrapper);
		return paginationFlowTask.setData(flowTaskEntityPage.getRecords(), page.getTotal());
	}

	@Override
	public List<FlowTaskEntity> getLaunchList(PaginationFlowTask paginationFlowTask) {
		QueryWrapper<FlowTaskEntity> queryWrapper = new QueryWrapper<>();
		String userName = SecurityUtils.getUser().getUsername();
		queryWrapper.lambda().eq(FlowTaskEntity::getCreateBy, userName);
		//关键字（流程名称、流程编码）
		String keyWord = paginationFlowTask.getKeyword() != null ? paginationFlowTask.getKeyword() : null;
		if (!StrUtil.isEmpty(keyWord)) {
			queryWrapper.lambda().and(
					t -> t.like(FlowTaskEntity::getEncode, keyWord)
							.or().like(FlowTaskEntity::getFullName, keyWord)
			);
		}
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationFlowTask.getStartTime() != null ? paginationFlowTask.getStartTime() : null;
		String endTime = paginationFlowTask.getEndTime() != null ? paginationFlowTask.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			Date startTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00");
			Date endTimes = DateUtil.stringToDate(DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59");
			queryWrapper.lambda().ge(FlowTaskEntity::getCreateTime, startTimes).le(FlowTaskEntity::getCreateTime, endTimes);
		}
		//所属流程
		String flowName = paginationFlowTask.getFlowId() != null ? paginationFlowTask.getFlowId() : null;
		if (!StrUtil.isEmpty(flowName)) {
			queryWrapper.lambda().eq(FlowTaskEntity::getFlowId, flowName);
		}
		//所属分类
		String flowCategory = paginationFlowTask.getFlowCategory() != null ? paginationFlowTask.getFlowCategory() : null;
		if (!StrUtil.isEmpty(flowCategory)) {
			queryWrapper.lambda().eq(FlowTaskEntity::getFlowCategory, flowCategory);
		}
		//排序
		if (SortType.ASC.equalsIgnoreCase(paginationFlowTask.getSort())) {
			queryWrapper.lambda().orderByAsc(FlowTaskEntity::getCreateTime);
		} else {
			queryWrapper.lambda().orderByDesc(FlowTaskEntity::getCreateTime);
		}
		Page<FlowTaskEntity> page = new Page<>(paginationFlowTask.getCurrentPage(), paginationFlowTask.getPageSize());
		IPage<FlowTaskEntity> flowTaskEntityPage = this.page(page, queryWrapper);
		return paginationFlowTask.setData(flowTaskEntityPage.getRecords(), page.getTotal());
	}

	@Override
	public List<FlowTaskEntity> getWaitList(PaginationFlowTask paginationFlowTask) {
		String userName = SecurityUtils.getUser().getUsername();
		StringBuilder dbSql = new StringBuilder();
		//查询自己的待办
		dbSql.append(" AND (");
		dbSql.append("o.handle_id = '").append(userName).append("' ");
		//委托审核
		List<FlowDelegateEntity> flowDelegateList = flowDelegateService.getUser(userName);
		if (flowDelegateList.size() > 0) {
			dbSql.append(" OR ");
			for (int i = 0; i < flowDelegateList.size(); i++) {
				FlowDelegateEntity delegateEntity = flowDelegateList.get(i);
				//委托的人
				dbSql.append(" o.handle_id = '").append(delegateEntity.getCreateBy()).append("' ");
				if (flowDelegateList.size() - 1 > i) {
					dbSql.append(" OR ");
				}
			}
			dbSql.append(")");
		} else {
			dbSql.append(")");
		}
		//关键字（流程名称、流程编码）
		String keyWord = paginationFlowTask.getKeyword() != null ? paginationFlowTask.getKeyword() : null;
		if (!StrUtil.isEmpty(keyWord)) {
			dbSql.append(" AND (t.encode like '%").append(keyWord).append("%' or t.full_name like '%").append(keyWord).append("%') ");
		}
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationFlowTask.getStartTime() != null ? paginationFlowTask.getStartTime() : null;
		String endTime = paginationFlowTask.getEndTime() != null ? paginationFlowTask.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			if (DataSourceEnum.ORACLE.getDbLinkType().equals(dataSourceUtils.getDataType().toLowerCase())) {
				String startTimes = DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00";
				String endTimes = DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59";
				dbSql.append(" AND o.create_time Between TO_DATE('").append(startTimes).append("','yyyy-mm-dd HH24:mi:ss') AND TO_DATE('").append(endTimes).append("','yyyy-mm-dd HH24:mi:ss') ");
			} else {
				String startTimes = DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00";
				String endTimes = DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59";
				dbSql.append(" AND o.create_time Between '").append(startTimes).append("' AND '").append(endTimes).append("' ");
			}
		}
		//所属流程
		String flowId = paginationFlowTask.getFlowId() != null ? paginationFlowTask.getFlowId() : null;
		if (!StrUtil.isEmpty(flowId)) {
			dbSql.append(" AND t.flow_id = '").append(flowId).append("'");
		}
		//所属分类
		String flowCategory = paginationFlowTask.getFlowCategory() != null ? paginationFlowTask.getFlowCategory() : null;
		if (!StrUtil.isEmpty(flowCategory)) {
			dbSql.append(" AND t.flow_category = '").append(flowCategory).append("'");
		}
		//发起人员
		String creatorUser = paginationFlowTask.getCreateBy() != null ? paginationFlowTask.getCreateBy() : null;
		if (!StrUtil.isEmpty(creatorUser)) {
			dbSql.append(" AND t.create_by = '").append(creatorUser).append("'");
		}
		//排序
		StringBuilder orderBy = new StringBuilder();
		if (SortType.DESC.equalsIgnoreCase(paginationFlowTask.getSort())) {
			orderBy.append(" Order by create_time DESC");
		} else {
			orderBy.append(" Order by create_time ASC");
		}
		String sql = dbSql + " " + orderBy;
		List<FlowTaskWaitListModel> data = this.baseMapper.getWaitList(sql);
		List<FlowTaskEntity> result = new LinkedList<>();
		for (FlowTaskWaitListModel model : data) {
			ChildNodeList childNodeModelList = JsonUtil.getJsonToBean(model.getNodePropertyJson(), ChildNodeList.class);
			FlowTaskEntity entity = JsonUtil.getJsonToBean(model, FlowTaskEntity.class);
			boolean delegate = true;
			boolean isUser = model.getHandleId().equals(userName);
			entity.setFullName(!isUser ? entity.getFullName() + "(委托)" : entity.getFullName());
			List<FlowDelegateEntity> flowList = flowDelegateList.stream().filter(t -> Objects.equals(t.getFlowId(), Convert.toLong(model.getFlowId()))).collect(Collectors.toList());
			//判断是否有自己审核
			if (!isUser) {
				//是否委托当前流程引擎 true是 false否
				delegate = flowList.stream().anyMatch(t -> t.getCreateBy().equals(model.getHandleId()));
			}
			if (delegate) {
				result.add(entity);
				List<DateProperties> timerAll = childNodeModelList.getTimerAll();
				Date date = new Date();
				boolean del = timerAll.stream().anyMatch(t -> t.getDate().getTime() > date.getTime());
				if (del) {
					result.remove(entity);
				}
			}
		}
		//返回数据
		return paginationFlowTask.setData(PageUtil.getListPage((int) paginationFlowTask.getCurrentPage(), (int) paginationFlowTask.getPageSize(), result), result.size());
	}

	@Override
	public List<FlowTaskEntity> getTrialList(PaginationFlowTask paginationFlowTask) {
		String userName = SecurityUtils.getUser().getUsername();
		Map<String, Object> queryParam = new HashMap<>(16);
		StringBuilder dbSql = new StringBuilder();
		//关键字（流程名称、流程编码）
		String keyWord = paginationFlowTask.getKeyword() != null ? paginationFlowTask.getKeyword() : null;
		if (!StrUtil.isEmpty(keyWord)) {
			dbSql.append(" AND (t.encode like '%").append(keyWord).append("%' or t.full_name like '%").append(keyWord).append("%') ");
		}
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationFlowTask.getStartTime() != null ? paginationFlowTask.getStartTime() : null;
		String endTime = paginationFlowTask.getEndTime() != null ? paginationFlowTask.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			if (DataSourceEnum.ORACLE.getDbLinkType().equals(dataSourceUtils.getDataType().toLowerCase())) {
				String startTimes = DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00";
				String endTimes = DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59";
				dbSql.append(" AND r.handle_time Between TO_DATE('").append(startTimes).append("','yyyy-mm-dd HH24:mi:ss') AND TO_DATE('").append(endTimes).append("','yyyy-mm-dd HH24:mi:ss') ");
			} else {
				String startTimes = DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00";
				String endTimes = DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59";
				dbSql.append(" AND r.handle_time Between '").append(startTimes).append("' AND '").append(endTimes).append("' ");
			}
		}
		//所属流程
		String flowId = paginationFlowTask.getFlowId() != null ? paginationFlowTask.getFlowId() : null;
		if (!StrUtil.isEmpty(flowId)) {
			dbSql.append(" AND t.flow_id = '").append(flowId).append("' ");
		}
		//所属分类
		String flowCategory = paginationFlowTask.getFlowCategory() != null ? paginationFlowTask.getFlowCategory() : null;
		if (!StrUtil.isEmpty(flowCategory)) {
			dbSql.append(" AND t.flow_category = '").append(flowCategory).append("' ");
		}
		//发起人员
		String creatorUser = paginationFlowTask.getCreateBy() != null ? paginationFlowTask.getCreateBy() : null;
		if (!StrUtil.isEmpty(creatorUser)) {
			dbSql.append(" AND t.create_by = '").append(creatorUser).append("' ");
		}
		//排序
		if (SortType.DESC.equalsIgnoreCase(paginationFlowTask.getSort())) {
			dbSql.append(" Order by update_time DESC");
		} else {
			dbSql.append(" Order by update_time ASC");
		}
		queryParam.put("handleId", userName);
		queryParam.put("sql", dbSql.toString());
		List<FlowTaskEntity> data = this.baseMapper.getTrialList(queryParam);
		return paginationFlowTask.setData(PageUtil.getListPage((int) paginationFlowTask.getCurrentPage(), (int) paginationFlowTask.getPageSize(), data), data.size());
	}

	@Override
	public List<FlowTaskEntity> getTrialList() {
		String userName = SecurityUtils.getUser().getUsername();
		Map<String, Object> queryParam = new HashMap<>(16);
		queryParam.put("handleId", userName);
		queryParam.put("sql", "");
		return this.baseMapper.getTrialList(queryParam);
	}


	@Override
	public List<FlowTaskEntity> getWaitList() {
		String userName = SecurityUtils.getUser().getUsername();
		StringBuilder dbSql = new StringBuilder();
		//查询自己的待办
		dbSql.append(" AND (");
		dbSql.append("o.handle_id = '").append(userName).append("' ");
		//委托审核
		List<FlowDelegateEntity> flowDelegateList = flowDelegateService.getUser(userName);
		if (flowDelegateList.size() > 0) {
			dbSql.append(" OR ");
			for (int i = 0; i < flowDelegateList.size(); i++) {
				FlowDelegateEntity delegateEntity = flowDelegateList.get(i);
				//委托的人
				dbSql.append(" o.handle_id = '").append(delegateEntity.getCreateBy()).append("' ");
				if (flowDelegateList.size() - 1 > i) {
					dbSql.append(" OR ");
				}
			}
			dbSql.append(")");
		} else {
			dbSql.append(")");
		}
		List<FlowTaskWaitListModel> data = this.baseMapper.getWaitList(dbSql.toString());
		//返回数据
		return JsonUtil.getJsonToList(data, FlowTaskEntity.class);
	}

	@Override
	public List<FlowTaskEntity> getAllWaitList() {
		List<FlowTaskWaitListModel> data = this.baseMapper.getWaitList("");
		return JsonUtil.getJsonToList(data, FlowTaskEntity.class);
	}

	@Override
	public List<FlowTaskEntity> getCirculateList(PaginationFlowTask paginationFlowTask) {
		String userName = SecurityUtils.getUser().getUsername();
		List<String> objectIds = positionApi.getListByUserName(userName).getData()
				.stream().map(SysPositionEntity::getId).map(String::valueOf).collect(Collectors.toList());
		objectIds.add(userName);
		List<String> objectIdSql = objectIds.stream().map(r -> "c.object_id = '" + r + "'").collect(Collectors.toList());
		//传阅人员
		StringBuilder dbSql = new StringBuilder();
		dbSql.append(" AND (");
		dbSql.append(String.join(" OR ", objectIdSql));
		dbSql.append(")");
		//关键字（流程名称、流程编码）
		String keyWord = paginationFlowTask.getKeyword() != null ? paginationFlowTask.getKeyword() : null;
		if (!StrUtil.isEmpty(keyWord)) {
			dbSql.append(" AND (t.encode like " + " '%").append(keyWord).append("%' ").append(" or t.full_name like").append(" '%").append(keyWord).append("%') ");
		}
		//日期范围（近7天、近1月、近3月、自定义）
		String startTime = paginationFlowTask.getStartTime() != null ? paginationFlowTask.getStartTime() : null;
		String endTime = paginationFlowTask.getEndTime() != null ? paginationFlowTask.getEndTime() : null;
		if (!StrUtil.isEmpty(startTime) && !StrUtil.isEmpty(endTime)) {
			if (DataSourceEnum.ORACLE.getDbLinkType().equals(dataSourceUtils.getDataType().toLowerCase())) {
				String startTimes = DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00";
				String endTimes = DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59";
				dbSql.append(" AND c.create_time Between TO_DATE('").append(startTimes).append("','yyyy-mm-dd HH24:mi:ss') AND TO_DATE('").append(endTimes).append("','yyyy-mm-dd HH24:mi:ss') ");
			} else {
				String startTimes = DateUtil.daFormat(Long.parseLong(startTime)) + " 00:00:00";
				String endTimes = DateUtil.daFormat(Long.parseLong(endTime)) + " 23:59:59";
				dbSql.append(" AND c.create_time Between  '").append(startTimes).append("' AND '").append(endTimes).append("' ");
			}
		}
		//所属流程
		String flowId = paginationFlowTask.getFlowId() != null ? paginationFlowTask.getFlowId() : null;
		if (!StrUtil.isEmpty(flowId)) {
			dbSql.append(" AND t.flow_id = '").append(flowId).append("'");
		}
		//所属分类
		String flowCategory = paginationFlowTask.getFlowCategory() != null ? paginationFlowTask.getFlowCategory() : null;
		if (!StrUtil.isEmpty(flowCategory)) {
			dbSql.append(" AND t.flow_category = '").append(flowCategory).append("'");
		}
		//发起人员
		String creatorUser = paginationFlowTask.getCreateBy() != null ? paginationFlowTask.getCreateBy() : null;
		if (!StrUtil.isEmpty(creatorUser)) {
			dbSql.append(" AND t.create_by = '").append(creatorUser).append("'");
		}
		//排序
		if (SortType.DESC.equals(paginationFlowTask.getSort().toLowerCase())) {
			dbSql.append(" Order by update_time DESC");
		} else {
			dbSql.append(" Order by update_time ASC");
		}
		List<FlowTaskEntity> data = this.baseMapper.getCirculateList(dbSql.toString());
		return paginationFlowTask.setData(PageUtil.getListPage((int) paginationFlowTask.getCurrentPage(), (int) paginationFlowTask.getPageSize(), data), data.size());
	}

	@Override
	public FlowTaskEntity getInfo(Long id) throws WorkFlowException {
		QueryWrapper<FlowTaskEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().and(
				t -> t.eq(FlowTaskEntity::getId, id)
						.or().eq(FlowTaskEntity::getProcessId, id)
		);
		FlowTaskEntity entity = this.getOne(queryWrapper);
		if (entity == null) {
			throw new WorkFlowException("未找到流程任务");
		}
		return entity;
	}

	@Override
	public FlowTaskEntity getInfoSubmit(Long id) {
		QueryWrapper<FlowTaskEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().and(
				t -> t.eq(FlowTaskEntity::getId, id)
						.or().eq(FlowTaskEntity::getProcessId, id)
		);
		return this.getOne(queryWrapper);
	}

	@Override
	public void delete(FlowTaskEntity entity) throws WorkFlowException {
		if (!checkStatus(entity.getStatus())) {
			throw new WorkFlowException("当前流程正在运行不能删除");
		} else {
			this.removeById(entity.getId());
			QueryWrapper<FlowTaskNodeEntity> node = new QueryWrapper<>();
			node.lambda().eq(FlowTaskNodeEntity::getTaskId, entity.getId());
			flowTaskNodeService.remove(node);
			QueryWrapper<FlowTaskOperatorEntity> operator = new QueryWrapper<>();
			operator.lambda().eq(FlowTaskOperatorEntity::getTaskId, entity.getId());
			flowTaskOperatorService.remove(operator);
			QueryWrapper<FlowTaskOperatorRecordEntity> record = new QueryWrapper<>();
			record.lambda().eq(FlowTaskOperatorRecordEntity::getTaskId, entity.getId());
			flowTaskOperatorRecordService.remove(record);
			QueryWrapper<FlowTaskCirculateEntity> circulate = new QueryWrapper<>();
			circulate.lambda().eq(FlowTaskCirculateEntity::getTaskId, entity.getId());
			flowTaskCirculateService.remove(circulate);
		}
	}

	@Override
	public void delete(String[] ids) {
		if (ids.length > 0) {
			QueryWrapper<FlowTaskEntity> task = new QueryWrapper<>();
			task.lambda().in(FlowTaskEntity::getId, ids);
			this.remove(task);
			QueryWrapper<FlowTaskNodeEntity> node = new QueryWrapper<>();
			node.lambda().in(FlowTaskNodeEntity::getTaskId, ids);
			flowTaskNodeService.remove(node);
			QueryWrapper<FlowTaskOperatorEntity> operator = new QueryWrapper<>();
			operator.lambda().in(FlowTaskOperatorEntity::getTaskId, ids);
			flowTaskOperatorService.remove(operator);
			QueryWrapper<FlowTaskOperatorRecordEntity> record = new QueryWrapper<>();
			record.lambda().in(FlowTaskOperatorRecordEntity::getTaskId, ids);
			flowTaskOperatorRecordService.remove(record);
			QueryWrapper<FlowTaskCirculateEntity> circulate = new QueryWrapper<>();
			circulate.lambda().in(FlowTaskCirculateEntity::getTaskId, ids);
			flowTaskCirculateService.remove(circulate);
		}
	}

	@Override
	public void save(Long id, Long flowId, Long processId, String flowTitle, int flowUrgent, String billNo, String formData) throws WorkFlowException {
		if (id == null) {
			//创建实例
			FlowEngineEntity flowEngineEntity = flowEngineService.getInfo(flowId);
			FlowTaskEntity taskEntity = new FlowTaskEntity();
			taskEntity.setId(processId);
			taskEntity.setProcessId(processId);
			taskEntity.setEncode(billNo);
			taskEntity.setFullName(flowTitle);
			taskEntity.setFlowUrgent(flowUrgent);
			taskEntity.setFlowId(flowEngineEntity.getId());
			taskEntity.setFlowCode(flowEngineEntity.getEncode());
			taskEntity.setFlowName(flowEngineEntity.getFullName());
			taskEntity.setFlowType(flowEngineEntity.getType());
			taskEntity.setFlowCategory(flowEngineEntity.getCategory());
			taskEntity.setFlowForm(flowEngineEntity.getFormData());
			if (formData != null) {
				taskEntity.setFlowFormContentJson(formData);
			}
			taskEntity.setFlowTemplateJson(flowEngineEntity.getFlowTemplateJson());
			taskEntity.setFlowVersion(flowEngineEntity.getVersion());
			taskEntity.setStatus(FlowTaskStatusEnum.Draft.getCode());
			taskEntity.setCompletion(0);
			taskEntity.setThisStep("开始");
			this.save(taskEntity);
		} else {
			FlowTaskEntity flowTaskEntity = this.getInfo(processId);
			if (!checkStatus(flowTaskEntity.getStatus())) {
				throw new WorkFlowException("当前流程正在运行不能重复保存");
			} else {
				flowTaskEntity.setFullName(flowTitle);
				flowTaskEntity.setFlowUrgent(flowUrgent);
				if (formData != null) {
					flowTaskEntity.setFlowFormContentJson(formData);
				}
				this.updateById(flowTaskEntity);
			}
		}
	}

	@Override
	public void save(Long id, Long flowId, Long processId, String flowTitle, int flowUrgent, String billNo) throws WorkFlowException {
		this.save(id, flowId, processId, flowTitle, flowUrgent, billNo, null);
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void submit(Long id, Long flowId, Long processId, String flowTitle, int flowUrgent, String billNo, Object formEntity, String freeApprover) throws WorkFlowException {
		try {
			//流程引擎
			FlowEngineEntity flowEngineEntity = flowEngineService.getInfo(flowId);
			//流程实例
			FlowTaskEntity flowTaskEntity = new FlowTaskEntity();
			//流程节点
			List<FlowTaskNodeEntity> flowTaskNodeEntityList = new LinkedList<>();
			//流程经办
			List<FlowTaskOperatorEntity> flowTaskOperatorEntityList = new ArrayList<>();
			//流程传阅
			List<FlowTaskCirculateEntity> flowTaskCirculateEntityList = new ArrayList<>();
			//判断字段类型
			Map<String, String> boosKey = new HashMap<>(16);
			//下拉、单选、高级控件的list
			Map<String, Object> keyList = new HashMap<>(16);
			//自定义的可以获取到boosKey
			if (FlowNature.CUSTOM.equals(flowEngineEntity.getFormType())) {
				FormDataModel formData = JsonUtil.getJsonToBean(flowEngineEntity.getFormData(), FormDataModel.class);
				List<FieLdsModel> fieLdsModelList = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
				tempJson(fieLdsModelList, boosKey, keyList);
			}
			boolean flag = id == null;
			if (id == null) {
				//创建实例
				flowTaskEntity.setId(processId);
				flowTaskEntity.setProcessId(processId);
				flowTaskEntity.setEncode(billNo);
				flowTaskEntity.setFullName(flowTitle);
				flowTaskEntity.setFlowUrgent(flowUrgent);
				flowTaskEntity.setFlowId(flowEngineEntity.getId());
				flowTaskEntity.setFlowCode(flowEngineEntity.getEncode());
				flowTaskEntity.setFlowName(flowEngineEntity.getFullName());
				flowTaskEntity.setFlowType(flowEngineEntity.getType());
				flowTaskEntity.setFlowCategory(flowEngineEntity.getCategory());
				flowTaskEntity.setFlowForm(flowEngineEntity.getFormData());
				flowTaskEntity.setFlowTemplateJson(flowEngineEntity.getFlowTemplateJson());
				flowTaskEntity.setFlowVersion(flowEngineEntity.getVersion());
				flowTaskEntity.setStatus(FlowTaskStatusEnum.Handle.getCode());
				flowTaskEntity.setCompletion(0);
				flowTaskEntity.setStartTime(LocalDateTime.now());
				if (formEntity != null) {
					flowTaskEntity.setFlowFormContentJson(JsonUtilEx.getObjectToString(formEntity));
				}
			} else {
				//更新实例
				flowTaskEntity = this.getInfo(id);
				if (!checkStatus(flowTaskEntity.getStatus())) {
					throw new WorkFlowException("当前流程正在运行不能重复提交");
				}
				flowTaskEntity.setFullName(flowTitle);
				flowTaskEntity.setFlowUrgent(flowUrgent);
				flowTaskEntity.setStatus(FlowTaskStatusEnum.Handle.getCode());
				flowTaskEntity.setStartTime(LocalDateTime.now());
				flowTaskEntity.setFlowForm(flowEngineEntity.getFormData());
				flowTaskEntity.setFlowTemplateJson(flowEngineEntity.getFlowTemplateJson());
				if (formEntity != null) {
					flowTaskEntity.setFlowFormContentJson(JsonUtilEx.getObjectToString(formEntity));
				}
			}
			//更新流程任务
			if (flag) {
				this.save(flowTaskEntity);
			} else {
				this.updateById(flowTaskEntity);
			}
			//流程表单Json
			String formDataJson = flowTaskEntity.getFlowTemplateJson();
			ChildNode childNodeAll = JsonUtil.getJsonToBean(formDataJson, ChildNode.class);
			//获取流程节点
			List<ChildNodeList> childNodeListAll = new ArrayList<>();
			List<ConditionList> conditionListAll = new ArrayList<>();
			//递归获取条件数据和节点数据
			FlowJsonUtil.getTemplateAll(childNodeAll, childNodeListAll, conditionListAll);
			ChildNodeList start = new ChildNodeList();
			//创建节点
			List<FlowTaskNodeEntity> emptyList = new ArrayList<>();
			List<FlowTaskNodeEntity> timerList = new ArrayList<>();
			for (ChildNodeList childNode : childNodeListAll) {
				FlowTaskNodeEntity flowTaskNodeEntity = new FlowTaskNodeEntity();
				String nodeId = childNode.getCustom().getNodeId();
				String dataJson = flowTaskEntity.getFlowFormContentJson();
				String type = childNode.getCustom().getType();
				flowTaskNodeEntity.setId(IdWorker.getId());
				childNode.setTaskNodeId(String.valueOf(flowTaskNodeEntity.getId()));
				childNode.setTaskId(String.valueOf(flowTaskEntity.getId()));
				flowTaskNodeEntity.setCreateTime(LocalDateTime.now());
				flowTaskNodeEntity.setTaskId(flowTaskEntity.getId());
				flowTaskNodeEntity.setNodeCode(nodeId);
				flowTaskNodeEntity.setNodeName(childNode.getProperties().getTitle());
				flowTaskNodeEntity.setNodeType(type);
				flowTaskNodeEntity.setState("-2");
				flowTaskNodeEntity.setNodeUp(childNode.getProperties().getRejectStep());
				flowTaskNodeEntity.setNodeNext(FlowJsonUtil.getNextNode(nodeId, dataJson, childNodeListAll, conditionListAll));
				flowTaskNodeEntity.setNodePropertyJson(JsonUtilEx.getObjectToString(childNode));
				boolean isStart = "start".equals(childNode.getCustom().getType());
				flowTaskNodeEntity.setCompletion(isStart ? 1 : 0);
				if (isStart) {
					childNode.getProperties().setApproverPos(new String[]{});
					childNode.getProperties().setApprovers(new String[]{});
					childNode.getProperties().setCirculatePosition(new String[]{});
					childNode.getProperties().setCirculateUser(new String[]{});
					childNode.getProperties().setHasEndfunc(childNode.getProperties().getHasEndfunc() != null ? childNode.getProperties().getHasEndfunc() : false);
					childNode.getProperties().setHasInitfunc(childNode.getProperties().getHasInitfunc() != null ? childNode.getProperties().getHasInitfunc() : false);
					start = childNode;
					flowTaskNodeEntity.setNodeName("开始");
					flowTaskNodeEntity.setNodePropertyJson(JsonUtilEx.getObjectToString(childNode));
				}
				flowTaskNodeEntityList.add(flowTaskNodeEntity);
				if ("empty".equals(type)) {
					emptyList.add(flowTaskNodeEntity);
				}
				if ("timer".equals(type)) {
					timerList.add(flowTaskNodeEntity);
				}
			}
			//指向empty，继续指向下一个节点
			for (FlowTaskNodeEntity empty : emptyList) {
				List<FlowTaskNodeEntity> nextEmptyList = flowTaskNodeEntityList.stream().filter(t -> t.getNodeNext().contains(empty.getNodeCode())).collect(Collectors.toList());
				for (FlowTaskNodeEntity entity : nextEmptyList) {
					entity.setNodeNext(empty.getNodeNext());
				}
			}
			//指向timer，继续指向下一个节点
			for (FlowTaskNodeEntity timer : timerList) {
				//获取到timer的上一节点
				ChildNodeList timerNodeList = JsonUtil.getJsonToBean(timer.getNodePropertyJson(), ChildNodeList.class);
				DateProperties timers = timerNodeList.getTimer();
				timers.setNodeId(timer.getNodeCode());
				timers.setTime(true);
				List<FlowTaskNodeEntity> upEmptyList = flowTaskNodeEntityList.stream().filter(t -> t.getNodeNext().contains(timer.getNodeCode())).collect(Collectors.toList());
				for (FlowTaskNodeEntity entity : upEmptyList) {
					//上一节点赋值timer的属性
					ChildNodeList modelList = JsonUtil.getJsonToBean(entity.getNodePropertyJson(), ChildNodeList.class);
					modelList.setTimer(timers);
					entity.setNodeNext(timer.getNodeNext());
					entity.setNodePropertyJson(JsonUtilEx.getObjectToString(modelList));
				}
			}
			flowTaskNodeService.create(flowTaskNodeEntityList);
			//获取下一个节点
			FlowTaskNodeEntity startNode = flowTaskNodeEntityList.stream().filter(t -> "start".equals(t.getNodeType())).findFirst().get();
			ChildNodeList timerNode = JsonUtil.getJsonToBean(startNode.getNodePropertyJson(), ChildNodeList.class);
			String[] fNodeNext = String.valueOf(startNode.getNodeNext()).split(",");
			List<String> stepId = new ArrayList<>();
			List<String> stepTitle = new ArrayList<>();
			List<String> progress = new ArrayList<>();
			for (String nextNode : fNodeNext) {
				List<ChildNodeList> nextNodeList = childNodeListAll.stream().filter(t -> t.getCustom().getNodeId().contains(nextNode)).collect(Collectors.toList());
				List<FlowTaskOperatorEntity> nextList = new ArrayList<>();
				for (ChildNodeList nextNodeModel : nextNodeList) {
					Properties properties = nextNodeModel.getProperties();
					Custom custom = nextNodeModel.getCustom();
					String type = String.valueOf(properties.getAssigneeType());
					//判断上一个节点是否有定时器,更新节点的定时时间
					DateProperties timer = timerNode.getTimer();
					Date date = new Date();
					if (timer.getTime()) {
						//添加定时器
						FlowTaskNodeEntity entity = flowTaskNodeEntityList.stream().filter(t -> t.getNodeCode().equals(nextNode)).findFirst().get();
						ChildNodeList entityTimer = JsonUtil.getJsonToBean(entity.getNodePropertyJson(), ChildNodeList.class);
						date = DateUtil.dateAddDays(date, timer.getDay());
						date = DateUtil.dateAddHours(date, timer.getHour());
						date = DateUtil.dateAddMinutes(date, timer.getMinute());
						date = DateUtil.dateAddSeconds(date, timer.getSecond());
						timer.setDate(date);
						List<DateProperties> timerAll = new ArrayList<>();
						timerAll.add(timer);
						entityTimer.setTimerAll(timerAll);
						entity.setNodePropertyJson(JsonUtilEx.getObjectToString(entityTimer));
						flowTaskNodeService.update(entity);
					}
					if (type.equals(String.valueOf(FlowTaskOperatorEnum.LaunchCharge.getCode()))) {
						//实时查用户主管
						R<UserInfoModel> info = usersApi.info(SecurityUtils.getUser().getUsername(), SecurityConstants.FROM_IN);
						//发起者【发起主管】
						if (info.getData() == null || StrUtil.isEmpty(info.getData().getSysUser().getManager())) {
							throw new WorkFlowException("没有找到发起者主管");
						}
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(FlowTaskOperatorEnum.LaunchCharge.getCode());
						flowTask.setHandleId(info.getData().getSysUser().getManager());
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(Convert.toLong(nextNodeModel.getTaskNodeId()));
						flowTask.setTaskId(Convert.toLong(nextNodeModel.getTaskId()));
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(type);
						nextList.add(flowTask);
					}
					//发起者【部门主管】
					if (type.equals(String.valueOf((FlowTaskOperatorEnum.DepartmentCharge.getCode())))) {
						R<SysDeptEntity> organizeEntity = organizeApi.info(SecurityUtils.getUser().getDeptId());
						if (organizeEntity.getData() != null && StrUtil.isEmpty(organizeEntity.getData().getManager())) {
							throw new WorkFlowException("没有找到部门主管");
						}
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(FlowTaskOperatorEnum.DepartmentCharge.getCode());
						flowTask.setHandleId(organizeEntity.getData().getManager());
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(Convert.toLong(nextNodeModel.getTaskNodeId()));
						flowTask.setTaskId(Convert.toLong(nextNodeModel.getTaskId()));
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(type);
						nextList.add(flowTask);
					}
					//发起者【发起本人】
					if (type.equals(String.valueOf(FlowTaskOperatorEnum.InitiatorMe.getCode()))) {
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(FlowTaskOperatorEnum.InitiatorMe.getCode());
						flowTask.setHandleId(flowTaskEntity.getCreateBy());
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(Convert.toLong(nextNodeModel.getTaskNodeId()));
						flowTask.setTaskId(Convert.toLong(nextNodeModel.getTaskId()));
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(type);
						nextList.add(flowTask);
					}
					//发起者【授权审批人】
					if (type.equals(String.valueOf(FlowTaskOperatorEnum.FreeApprover.getCode()))) {
						if (!StrUtil.isEmpty(freeApprover)) {
							FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
							flowTask.setHandleType(FlowTaskOperatorEnum.FreeApprover.getCode());
							flowTask.setHandleId(freeApprover);
							flowTask.setNodeCode(custom.getNodeId());
							flowTask.setNodeName(properties.getTitle());
							flowTask.setTaskNodeId(Convert.toLong(nextNodeModel.getTaskNodeId()));
							flowTask.setTaskId(Convert.toLong(nextNodeModel.getTaskId()));
							flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
							flowTask.setCompletion(0);
							flowTask.setState(FlowNodeEnum.Process.getCode());
							flowTask.setType(type);
							nextList.add(flowTask);
						}
					}
					//发起者【指定用户】
					for (String userName : properties.getApprovers()) {
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(FlowTaskOperatorEnum.AppointUser.getCode());
						flowTask.setHandleId(userName);
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(Convert.toLong(nextNodeModel.getTaskNodeId()));
						flowTask.setTaskId(Convert.toLong(nextNodeModel.getTaskId()));
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(type);
						nextList.add(flowTask);
					}
					//发起者【指定岗位】
					if (properties.getApproverPos().length > 0) {
						List<String> userPosition = positionApi.getUserPositionByIds(String.join(",", properties.getApproverPos())).getData().getUserNames();
						getApproverUser(userPosition, flowTaskOperatorEntityList, nextNodeModel);
					}
					//创建传阅
					for (String positionId : properties.getCirculatePosition()) {
						FlowTaskCirculateEntity flowTask = new FlowTaskCirculateEntity();
						flowTask.setObjectType(FlowTaskOperatorEnum.AppointPosition.getCode());
						flowTask.setObjectId(positionId);
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(Convert.toLong(nextNodeModel.getTaskNodeId()));
						flowTask.setTaskId(Convert.toLong(nextNodeModel.getTaskId()));
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTaskCirculateEntityList.add(flowTask);
					}
					for (String userName : properties.getCirculateUser()) {
						//用户岗位
						List<String> positionIds = positionApi.getListByUserName(userName).getData()
								.stream().map(t -> String.valueOf(t.getId())).collect(Collectors.toList());
						if (getCirculateUser(properties.getCirculatePosition(), positionIds)) {
							FlowTaskCirculateEntity flowTask = new FlowTaskCirculateEntity();
							flowTask.setObjectType(FlowTaskOperatorEnum.AppointUser.getCode());
							flowTask.setObjectId(userName);
							flowTask.setNodeCode(custom.getNodeId());
							flowTask.setNodeName(properties.getTitle());
							flowTask.setTaskNodeId(Long.valueOf(nextNodeModel.getTaskNodeId()));
							flowTask.setTaskId(Long.valueOf(nextNodeModel.getTaskId()));
							flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
							flowTaskCirculateEntityList.add(flowTask);
						}
					}
					stepId.add(custom.getNodeId());
					stepTitle.add(properties.getTitle());
					if (properties.getProgress() != null) {
						progress.add(properties.getProgress());
					}
				}
				flowTaskOperatorEntityList.addAll(nextList);
			}
			//审核人
			flowTaskOperatorService.create(flowTaskOperatorEntityList);
			//传阅人
			flowTaskCirculateService.create(flowTaskCirculateEntityList);
			//更新实例
			flowTaskEntity.setThisStepId(String.join(",", stepId));
			flowTaskEntity.setThisStep(String.join(",", stepTitle));
			//节点进度
			Collections.sort(progress);
			flowTaskEntity.setCompletion(progress.size() > 0 ? Integer.valueOf(progress.get(0)) : null);
			this.updateById(flowTaskEntity);
			//提交记录
			FlowTaskOperatorRecordEntity flowTaskOperatorRecordEntity = new FlowTaskOperatorRecordEntity();
			flowTaskOperatorRecordEntity.setHandleId(SecurityUtils.getUser().getUsername());
			flowTaskOperatorRecordEntity.setHandleTime(LocalDateTime.now());
			flowTaskOperatorRecordEntity.setHandleStatus(2);
			flowTaskOperatorRecordEntity.setNodeName("开始");
			flowTaskOperatorRecordEntity.setTaskId(flowTaskEntity.getId());
			flowTaskOperatorRecordService.create(flowTaskOperatorRecordEntity);
			//开始事件
			Properties properties = start.getProperties();
			boolean func = properties.getHasInitfunc() != null ? properties.getHasInitfunc() : false;
			if (func) {
				String faceUrl = properties.getInitInterfaceUrl()
						+ "?" + taskNodeId + "=" + startNode.getId()
						+ "&" + taskId + "=" + startNode.getTaskId()
						+ "&" + taskStatus + "=" + flowTaskEntity.getStatus();
				System.out.println("进入开始事件:" + faceUrl);
				httpRequestAll(faceUrl);
			}
			//消息提醒
			Map<String, Object> message = new HashMap<>(16);
			message.put("type", FlowMessageEnum.wait.getCode());
			message.put("id", processId);
			//审核提醒
			messagePush(flowTaskOperatorEntityList, flowTitle + "【审核】", JsonUtilEx.getObjectToString(message));
			//抄送提醒
			message.put("type", FlowMessageEnum.circulate.getCode());
			messagePushCirculate(flowTaskCirculateEntityList, flowTaskEntity.getFullName() + "【抄送】", JsonUtilEx.getObjectToString(message));
		} catch (WorkFlowException e) {
			//手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new WorkFlowException(e.getMessage());
		}
	}

	@Override
	public void submit(Long id, Long flowId, Long processId, String flowTitle, int flowUrgent, String billNo, Object formEntity) throws WorkFlowException {
		this.submit(processId, flowId, flowTitle, flowUrgent, billNo, formEntity, null);
	}

	@Override
	public void submit(Long processId, Long flowId, String flowTitle, int flowUrgent, String billNo, Object formEntity) throws WorkFlowException {
		this.submit(processId, flowId, flowTitle, flowUrgent, billNo, formEntity, null);
	}

	@Override
	public void submit(Long processId, Long flowId, String flowTitle, int flowUrgent, String billNo, Object formEntity, String freeApprover) throws WorkFlowException {
		FlowTaskEntity flowTaskEntity = this.getInfoSubmit(processId);
		if (flowTaskEntity != null) {
			this.submit(flowTaskEntity.getId(), flowId, processId, flowTitle, flowUrgent, billNo, formEntity, freeApprover);
		} else {
			this.submit(null, flowId, processId, flowTitle, flowUrgent, billNo, formEntity, freeApprover);
		}
	}

	@Override
	public void revoke(FlowTaskEntity flowTaskEntity, FlowHandleModel flowHandleModel) {
		List<FlowTaskNodeEntity> list = flowTaskNodeService.getList(flowTaskEntity.getId());
		FlowTaskNodeEntity start = list.stream().filter(t -> "start".equals(String.valueOf(t.getNodeType()))).findFirst().orElse(null);
		//删除节点
		flowTaskNodeService.deleteByTaskId(flowTaskEntity.getId());
		//删除经办
		flowTaskOperatorService.deleteByTaskId(flowTaskEntity.getId());
		//删除传阅
		flowTaskCirculateService.deleteByTaskId(flowTaskEntity.getId());
		//更新实例
		flowTaskEntity.setThisStepId("");
		flowTaskEntity.setThisStep("开始");
		flowTaskEntity.setCompletion(0);
		flowTaskEntity.setStatus(FlowTaskStatusEnum.Revoke.getCode());
		flowTaskEntity.setStartTime(null);
		flowTaskEntity.setEndTime(null);
		this.updateById(flowTaskEntity);
		//撤回记录
		FlowTaskOperatorRecordEntity flowTaskOperatorRecordEntity = new FlowTaskOperatorRecordEntity();
		flowTaskOperatorRecordEntity.setHandleOpinion(flowHandleModel.getHandleOpinion());
		flowTaskOperatorRecordEntity.setHandleId(SecurityUtils.getUser().getUsername());
		flowTaskOperatorRecordEntity.setHandleTime(LocalDateTime.now());
		flowTaskOperatorRecordEntity.setHandleStatus(3);
		flowTaskOperatorRecordEntity.setNodeName("开始");
		flowTaskOperatorRecordEntity.setTaskId(flowTaskEntity.getId());
		flowTaskOperatorRecordService.create(flowTaskOperatorRecordEntity);
		//发起撤回事件
		if (start != null) {
			ChildNodeList childNode = JsonUtil.getJsonToBean(start.getNodePropertyJson(), ChildNodeList.class);
			Properties properties = childNode.getProperties();
			boolean func = properties.getHasFlowRecallFunc() != null ? properties.getHasFlowRecallFunc() : false;
			if (func) {
				String faceUrl = properties.getFlowRecallInterfaceUrl()
						+ "?" + handleStatus + "=" + flowTaskOperatorRecordEntity.getHandleStatus()
						+ "&" + taskId + "=" + flowTaskEntity.getId()
						+ "&" + taskStatus + "=" + flowTaskEntity.getStatus();
				System.out.println("开始撤回事件:" + faceUrl);
				httpRequestAll(faceUrl);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void audit(FlowTaskEntity flowTaskEntity, FlowTaskOperatorEntity flowTaskOperatorEntity, FlowHandleModel flowHandleModel) throws WorkFlowException {
		try {
			String freeApprover = flowHandleModel.getFreeApprover();
			BoosterUser userInfo = SecurityUtils.getUser();
			//流程节点
			List<FlowTaskNodeEntity> flowTaskNodeAll = flowTaskNodeService.getList(flowTaskEntity.getId());
			List<FlowTaskNodeEntity> flowTaskNodeEntityList = flowTaskNodeAll.stream().filter(t -> FlowNodeEnum.Process.getCode().equals(t.getState())).collect(Collectors.toList());
			//当前节点
			FlowTaskNodeEntity flowTaskNodeEntity = flowTaskNodeEntityList.stream().filter(m -> m.getId().equals(flowTaskOperatorEntity.getTaskNodeId())).findFirst().get();
			//当前节点属性
			ChildNodeList nodeModel = JsonUtil.getJsonToBean(flowTaskNodeEntity.getNodePropertyJson(), ChildNodeList.class);
			String assignType = String.valueOf(nodeModel.getProperties().getAssigneeType());
			//当前节点经办
			List<FlowTaskOperatorEntity> entityList = flowTaskOperatorService.getList(flowTaskNodeEntity.getTaskId()).stream().filter(t -> FlowNodeEnum.Process.getCode().equals(t.getState())).collect(Collectors.toList());
			List<FlowTaskOperatorEntity> thisFlowTaskOperatorEntityList = entityList.stream().filter(m -> m.getTaskNodeId().equals(flowTaskNodeEntity.getId())).collect(Collectors.toList());
			//审核记录
			FlowTaskOperatorRecordEntity flowTaskOperatorRecordEntity = new FlowTaskOperatorRecordEntity();
			flowTaskOperatorRecordEntity.setHandleOpinion(flowHandleModel.getHandleOpinion());
			flowTaskOperatorRecordEntity.setHandleId(userInfo.getUsername());
			flowTaskOperatorRecordEntity.setHandleTime(LocalDateTime.now());
			flowTaskOperatorRecordEntity.setHandleStatus(1);
			flowTaskOperatorRecordEntity.setNodeCode(flowTaskNodeEntity.getNodeCode());
			flowTaskOperatorRecordEntity.setNodeName(flowTaskNodeEntity.getNodeName());
			flowTaskOperatorRecordEntity.setTaskOperatorId(flowTaskOperatorEntity.getId());
			flowTaskOperatorRecordEntity.setTaskNodeId(flowTaskNodeEntity.getId());
			flowTaskOperatorRecordEntity.setTaskId(flowTaskEntity.getId());
			flowTaskOperatorRecordService.create(flowTaskOperatorRecordEntity);
			//更新或签都改成完成
			if (FlowTaskOperatorEnum.FixedApprover.getCode().equals(assignType)) {
				flowTaskOperatorService.update(flowTaskNodeEntity.getId(), FlowTaskOperatorEnum.FixedApprover.getCode());
			}
			flowTaskOperatorEntity.setCompletion(1);
			flowTaskOperatorEntity.setHandleStatus(1);
			flowTaskOperatorEntity.setHandleTime(LocalDateTime.now());
			flowTaskOperatorService.update(flowTaskOperatorEntity);
			//获取委托人的id
			List<String> userNameListAll = new ArrayList<>();
			//审核自己的话，更新委托人，不是审核自己的话，不更新自己
			if (flowTaskOperatorEntity.getHandleId().equals(userInfo.getUsername())) {
				userNameListAll = flowDelegateService.getUser(userInfo.getUsername()).stream().map(BaseEntity::getCreateBy).collect(Collectors.toList());
			}
			//更新委托人的经办
			if (FlowTaskOperatorEnum.FixedJointlyApprover.getCode().equals(assignType)) {
				flowTaskOperatorService.update(flowTaskNodeEntity.getId(), userNameListAll);
			}
			//查询下一个节点
			String[] fNodeNext = String.valueOf(flowTaskNodeEntity.getNodeNext()).split(",");
			//更新节点
			boolean isUpdateFlowTaskNode = true;
			//会签是否结束
			boolean isFixedJointly = true;
			//加签是否结束
			boolean isFreeApprover = FlowTaskOperatorEnum.FreeApprover.getCode().equals(assignType) && !StrUtil.isEmpty(freeApprover);
			List<String> operatorList = thisFlowTaskOperatorEntityList.stream().filter(t -> !t.getHandleId().equals(userInfo.getUsername()) && t.getCompletion() == 0).map(FlowTaskOperatorEntity::getHandleId).collect(Collectors.toList());
			List<String> userNameAll = userNameListAll;
			boolean fixedJointlyApprover = operatorList.stream().anyMatch(t -> !userNameAll.contains(t));
			if (isFreeApprover) {
				isUpdateFlowTaskNode = false;
				//加签下一节点替换成当前节点
				fNodeNext = flowTaskNodeEntity.getNodeCode().split(",");
			} else if (FlowTaskOperatorEnum.FixedJointlyApprover.getCode().equals(assignType) && fixedJointlyApprover) {
				isUpdateFlowTaskNode = false;
				isFixedJointly = false;
			}
			//当前节点id
			Set<String> stepId = new HashSet<>(16);
			List<String> progress = new ArrayList<>();
			List<String> interFlowId = new ArrayList<>();
			//流程经办
			List<FlowTaskOperatorEntity> flowTaskOperatorEntityList = new ArrayList<>();
			//流程传阅
			List<FlowTaskCirculateEntity> flowTaskCirculateEntityList = new ArrayList<>();
			//判断是否继续下一节点
			boolean isNext = true;
			//判断流程是否结束
			boolean endRound = Arrays.asList(fNodeNext).contains("end");
			//结束属性
			ChildNodeList endChildNode = new ChildNodeList();
			//判断单前节点是否有定时器
			//汇合点显示时间
			DateProperties timer = nodeModel.getTimer();
			//分流节点的定时器
			List<DateProperties> interTimerAll = new ArrayList<>();
			boolean isTimer = timer.getTime();
			String isNextId = flowTaskNodeEntity.getNodeNext();
			Date date = new Date();
			if (isTimer) {
				//赋值定时器执行完时间
				date = DateUtil.dateAddDays(date, timer.getDay());
				date = DateUtil.dateAddHours(date, timer.getHour());
				date = DateUtil.dateAddMinutes(date, timer.getMinute());
				date = DateUtil.dateAddSeconds(date, timer.getSecond());
				timer.setDate(date);
				List<DateProperties> timerAll = new ArrayList<>();
				timerAll.add(timer);
				interTimerAll.add(timer);
				ChildNodeList entityTimer = JsonUtil.getJsonToBean(flowTaskNodeEntity.getNodePropertyJson(), ChildNodeList.class);
				entityTimer.setTimerAll(timerAll);
				flowTaskNodeEntity.setNodePropertyJson(JsonUtilEx.getObjectToString(entityTimer));
			}
			//加签节点审批通过
			List<FlowTaskNodeEntity> freeListAll = new ArrayList<>();
			//判断分流、还是单节点
			for (String nextNode : fNodeNext) {
				//加签的下一节点
				List<String> nextNodeList = new ArrayList<>();
				//查询分流节点是否都完成
				List<FlowTaskNodeEntity> interFlowAll = flowTaskNodeEntityList.stream().filter(t -> String.valueOf(t.getNodeNext()).contains(nextNode) && !t.getNodeCode().equals(flowTaskNodeEntity.getNodeCode()) && t.getCompletion() == 0).collect(Collectors.toList());
				if (interFlowAll.size() > 0) {
					isNext = false;
					break;
				}
				//分流的全部节点和名称
				List<FlowTaskNodeEntity> interFlow = flowTaskNodeEntityList.stream().filter(t -> String.valueOf(t.getNodeNext()).contains(nextNode) && !t.getNodeCode().equals(flowTaskNodeEntity.getNodeCode()) && t.getCompletion() == 1).collect(Collectors.toList());
				interFlowId = interFlow.stream().map(FlowTaskNodeEntity::getNodeCode).distinct().collect(Collectors.toList());
				//查询下一节点审批人
				List<FlowTaskNodeEntity> nextList = flowTaskNodeEntityList.stream().filter(t -> String.valueOf(t.getNodeCode()).contains(nextNode)).collect(Collectors.toList());
				//跳过加签
				List<FlowTaskNodeEntity> nextAll = new ArrayList<>();
				for (FlowTaskNodeEntity entity : nextList) {
					ChildNodeList modelList = JsonUtil.getJsonToBean(entity.getNodePropertyJson(), ChildNodeList.class);
					Properties properties = modelList.getProperties();
					String type = String.valueOf(properties.getAssigneeType());
					nextAll.add(entity);
					//当前节点替换
					nextNodeList.add(entity.getNodeCode());
					if (FlowTaskOperatorEnum.FreeApprover.getCode().equals(type) && StrUtil.isEmpty(freeApprover)) {
						nextAll.remove(entity);
						freeListAll.add(entity);
						String[] freeNext = String.valueOf(entity.getNodeNext()).split(",");
						for (String free : freeNext) {
							FlowTaskNodeEntity taskNodeEntity = flowTaskNodeEntityList.stream().filter(t -> t.getNodeCode().equals(free)).findFirst().orElse(null);
							//查询分流节点是否都完成
							List<FlowTaskNodeEntity> freeList = flowTaskNodeEntityList.stream().filter(t -> String.valueOf(t.getNodeNext()).contains(free) && !t.getNodeCode().equals(free) && t.getCompletion() == 0).collect(Collectors.toList());
							if (freeList.size() == 0 && taskNodeEntity != null) {
								nextAll.add(taskNodeEntity);
								//当前节点替换
								nextNodeList.remove(entity.getNodeCode());
								nextNodeList.addAll(freeList.stream().map(FlowTaskNodeEntity::getNodeCode).collect(Collectors.toList()));
							}
						}
					}
				}
				//下一审批节点
				for (FlowTaskNodeEntity entity : nextAll) {
					ChildNodeList modelList = JsonUtil.getJsonToBean(entity.getNodePropertyJson(), ChildNodeList.class);
					if ("endround".equals(entity.getNodeType())) {
						endChildNode = modelList;
					}
					Properties properties = modelList.getProperties();
					Custom custom = modelList.getCustom();
					String type = String.valueOf(properties.getAssigneeType());
					Long taskId = Convert.toLong(modelList.getTaskId());
					Long taskNodeId = Convert.toLong(modelList.getTaskNodeId());
					//创建经办
					//发起者【发起主管】
					UserVO createUser = usersApi.getUserByUserName(flowTaskEntity.getCreateBy()).getData();
					if (FlowTaskOperatorEnum.LaunchCharge.getCode().equals(type)) {
						if (StrUtil.isEmpty(createUser.getManager())) {
							throw new WorkFlowException("没有找到部门主管");
						}
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.LaunchCharge.getCode()));
						flowTask.setHandleId(createUser.getManager());
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(type);
						flowTaskOperatorEntityList.add(flowTask);
					}
					//发起者【部门主管】
					if (FlowTaskOperatorEnum.DepartmentCharge.getCode().equals(type)) {
						R<SysDeptEntity> organizeEntity = organizeApi.infoByName(createUser.getDeptName());
						if (organizeEntity.getData() != null && StrUtil.isEmpty(organizeEntity.getData().getManager())) {
							throw new WorkFlowException("没有找到部门主管");
						}
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.DepartmentCharge.getCode()));
						flowTask.setHandleId(organizeEntity.getData().getManager());
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(type);
						flowTaskOperatorEntityList.add(flowTask);
					}
					//发起者【发起本人】
					if (FlowTaskOperatorEnum.InitiatorMe.getCode().equals(type)) {
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.InitiatorMe.getCode()));
						flowTask.setHandleId(flowTaskEntity.getCreateBy());
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(type);
						flowTaskOperatorEntityList.add(flowTask);
					}
					//发起者【授权审批人】
					if (FlowTaskOperatorEnum.FreeApprover.getCode().equals(type)) {
						if (!StrUtil.isEmpty(freeApprover)) {
							FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
							flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.FreeApprover.getCode()));
							flowTask.setHandleId(freeApprover);
							flowTask.setNodeCode(custom.getNodeId());
							flowTask.setNodeName(properties.getTitle());
							flowTask.setTaskNodeId(taskNodeId);
							flowTask.setTaskId(taskId);
							flowTask.setCompletion(0);
							flowTask.setState(FlowNodeEnum.Process.getCode());
							flowTask.setType(type);
							flowTaskOperatorEntityList.add(flowTask);
						}
					}
					//发起者【指定用户】
					for (String userName : properties.getApprovers()) {
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.AppointUser.getCode()));
						flowTask.setHandleId(userName);
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(type);
						flowTaskOperatorEntityList.add(flowTask);
					}
					//发起者【指定岗位】
					if (properties.getApproverPos().length > 0) {
						List<String> userPosition = positionApi.getUserPositionByIds(String.join(",", properties.getApproverPos())).getData().getUserNames();
						getApproverUser(userPosition, flowTaskOperatorEntityList, modelList);
					}
					//创建传阅
					//传阅者【指定岗位】
					for (String positionId : properties.getCirculatePosition()) {
						FlowTaskCirculateEntity flowTask = new FlowTaskCirculateEntity();
						flowTask.setObjectType(String.valueOf(FlowTaskOperatorEnum.AppointPosition.getCode()));
						flowTask.setObjectId(positionId);
						flowTask.setNodeCode(custom.getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
						flowTaskCirculateEntityList.add(flowTask);
					}
					//传阅者【指定用户】
					for (String userName : properties.getCirculateUser()) {
						//用户岗位
						List<String> positionIds = positionApi.getListByUserName(userName).getData()
								.stream().map(t -> String.valueOf(t.getId())).collect(Collectors.toList());
						if (getCirculateUser(properties.getCirculatePosition(), positionIds)) {
							FlowTaskCirculateEntity flowTask = new FlowTaskCirculateEntity();
							flowTask.setObjectType(String.valueOf(FlowTaskOperatorEnum.AppointUser.getCode()));
							flowTask.setObjectId(userName);
							flowTask.setNodeCode(custom.getNodeId());
							flowTask.setNodeName(properties.getTitle());
							flowTask.setTaskNodeId(taskNodeId);
							flowTask.setTaskId(taskId);
							flowTask.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
							flowTaskCirculateEntityList.add(flowTask);
						}
					}
					stepId.add(custom.getNodeId());
					if (properties.getProgress() != null) {
						progress.add(properties.getProgress());
					}
				}
				//判断是否跳过加签节点
				if (stepId.size() == 0) {
					stepId.addAll(nextNodeList);
				}
			}
			//更新实例
			if (isUpdateFlowTaskNode) {
				flowTaskNodeEntity.setCompletion(1);
				flowTaskNodeService.update(flowTaskNodeEntity);
				//加签节点审批通过
				for (FlowTaskNodeEntity entity : freeListAll) {
					entity.setCompletion(1);
					flowTaskNodeService.update(entity);
				}
				//审批事件
				Properties properties = nodeModel.getProperties();
				boolean func = properties.getHasApproverfunc() != null ? properties.getHasApproverfunc() : false;
				if (func) {
					String faceUrl = properties.getApproverInterfaceUrl()
							+ "?" + taskNodeId + "=" + flowTaskNodeEntity.getId()
							+ "&" + handleStatus + "=" + flowTaskOperatorRecordEntity.getHandleStatus()
							+ "&" + taskId + "=" + flowTaskNodeEntity.getTaskId()
							+ "&" + taskStatus + "=" + flowTaskEntity.getStatus();
					System.out.println("进入审批事件:" + faceUrl);
					httpRequestAll(faceUrl);
				}
				//判断是否进入下一个节点
				if (isNext) {
					String[] stepIdsAll = flowTaskEntity.getThisStepId().split(",");
					Set<String> id = new HashSet<>(16);
					List<String> title = new ArrayList<>();
					//当前节点编码
					for (String ids : stepIdsAll) {
						if (ids.equals(flowTaskNodeEntity.getNodeCode())) {
							String stepIdAll = ids.replace(flowTaskNodeEntity.getNodeCode(), String.join(",", stepId));
							id.add(stepIdAll);
						} else {
							boolean flowId = interFlowId.stream().noneMatch(t -> t.contains(ids));
							if (flowId) {
								id.add(ids);
							} else {
								//判断定时器时间，获取最大的定时器
								FlowTaskNodeEntity interTimeEntity = flowTaskNodeEntityList.stream().filter(m -> m.getNodeCode().equals(ids)).findFirst().get();
								ChildNodeList interTimeChildNode = JsonUtil.getJsonToBean(interTimeEntity.getNodePropertyJson(), ChildNodeList.class);
								boolean nextIsTimer = interTimeChildNode.getTimer().getTime();
								if (nextIsTimer) {
									//添加分流节点的定时器
									interTimerAll.addAll(interTimeChildNode.getTimerAll());
									isTimer = true;
								}
							}
						}
					}
					//当前节点名称
					for (String ids : id) {
						List<FlowTaskNodeEntity> nameList = flowTaskNodeEntityList.stream().filter(t -> ids.contains(t.getNodeCode())).collect(Collectors.toList());
						title = nameList.stream().map(FlowTaskNodeEntity::getNodeName).collect(Collectors.toList());
					}
					flowTaskEntity.setThisStepId(String.join(",", id));
					flowTaskEntity.setThisStep(String.join(",", title));
					//节点进度排序
					Collections.sort(progress);
					flowTaskEntity.setCompletion(progress.size() > 0 ? Integer.valueOf(progress.get(0)) : null);
					//判断结束流程
					if (endRound) {
						flowTaskEntity.setStatus(FlowTaskStatusEnum.Adopt.getCode());
						flowTaskEntity.setCompletion(100);
						flowTaskEntity.setEndTime(LocalDateTime.now());
						flowTaskEntity.setThisStepId("end");
						flowTaskEntity.setThisStep("结束");
						//审核结束给创建者消息提醒
						List<String> toUserNames = new ArrayList<>();
						toUserNames.add(flowTaskEntity.getCreateBy());
						SentMessageModel model = new SentMessageModel();
						model.setBodyText("");
						model.setTitle(flowTaskEntity.getFullName() + "【流程结束】");
						model.setToUserNames(toUserNames);
						noticeApi.sentMessage(model);
						//结束事件
						Properties endProperties = endChildNode.getProperties();
						boolean endFunc = endProperties.getHasEndfunc() != null ? endProperties.getHasEndfunc() : false;
						if (endFunc) {
							String faceUrl = endProperties.getEndInterfaceUrl()
									+ "?" + taskNodeId + "=" + flowTaskNodeEntity.getId()
									+ "&" + handleStatus + "=" + flowTaskOperatorRecordEntity.getHandleStatus()
									+ "&" + taskId + "=" + flowTaskNodeEntity.getTaskId()
									+ "&" + taskStatus + "=" + flowTaskEntity.getStatus();
							System.out.println("进入结束事件:" + faceUrl);
							httpRequestAll(faceUrl);
						}
					}
					//更新自定义数据
					FlowEngineEntity flowEntity = flowEngineService.getInfo(flowTaskEntity.getFlowId());
					if (FlowNature.CUSTOM.equals(flowEntity.getFormType())) {
						List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(flowEntity.getRefTables(), FlowTableModel.class);
						Object objectData = flowHandleModel.getFormData();
						if (objectData instanceof Map) {
							Map<String, Object> formDataAll = (Map<String, Object>) objectData;
							Map<String, Object> data = JsonUtil.stringToMap(String.valueOf(formDataAll.get("data")));
							//formTempJson
							FormDataModel formData = JsonUtil.getJsonToBean(flowTaskEntity.getFlowForm(), FormDataModel.class);
							List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
							Map<String, Object> dataAll = flowDataUtil.update(data, list, tableModelList, flowTaskEntity.getProcessId());
							//更新task表数据
							flowTaskEntity.setFlowFormContentJson(JsonUtil.getObjectToString(dataAll));
						}
					} else if (FlowNature.SYSTEM.equals(flowEntity.getFormType())) {
						String coed = flowHandleModel.getEncode();
						Object objectData = flowHandleModel.getFormData();
						if (objectData instanceof Map) {
							String data = JsonUtil.getObjectToString(objectData);
							formData(coed, flowTaskEntity.getId(), data);
							//更新task表数据
							flowTaskEntity.setFlowFormContentJson(data);
						}
					}
					this.updateById(flowTaskEntity);
					//更新汇合点的定时器
					if (isTimer) {
						String[] ids = isNextId.split(",");
						for (String interId : ids) {
							FlowTaskNodeEntity interTimeEntity = flowTaskNodeEntityList.stream().filter(m -> m.getNodeCode().equals(interId)).findFirst().orElse(null);
							if (interTimeEntity != null) {
								ChildNodeList interTimeChildNode = JsonUtil.getJsonToBean(interTimeEntity.getNodePropertyJson(), ChildNodeList.class);
								interTimeChildNode.setTimerAll(interTimerAll);
								interTimeEntity.setNodePropertyJson(JsonUtilEx.getObjectToString(interTimeChildNode));
								flowTaskNodeService.update(interTimeEntity);
							}
						}
					}
				}
			}
			//判断会签是否全部结束
			if (isFixedJointly && isNext) {
				flowTaskOperatorService.create(flowTaskOperatorEntityList);
			}
			flowTaskCirculateService.create(flowTaskCirculateEntityList);
			//消息提醒
			if (isUpdateFlowTaskNode) {
				//消息提醒
				Map<String, Object> message = new HashMap<>(16);
				message.put("type", FlowMessageEnum.wait.getCode());
				message.put("id", flowTaskEntity.getId());
				//审核提醒
				messagePush(flowTaskOperatorEntityList, flowTaskEntity.getFullName() + "【审核】", JsonUtilEx.getObjectToString(message));
				//抄送提醒
				message.put("type", FlowMessageEnum.circulate.getCode());
				messagePushCirculate(flowTaskCirculateEntityList, flowTaskEntity.getFullName() + "【抄送】", JsonUtilEx.getObjectToString(message));
				//创建者消息提醒
				List<String> toUserNames = new ArrayList<>();
				toUserNames.add(flowTaskEntity.getCreateBy());
				message.put("type", FlowMessageEnum.me.getCode());
				SentMessageModel model = new SentMessageModel();
				model.setBodyText(JsonUtil.getObjectToString(message));
				model.setTitle(flowTaskEntity.getFullName() + "【审核通过】");
				model.setToUserNames(toUserNames);
				noticeApi.sentMessage(model);
			}
		} catch (SQLException e) {
			//手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new WorkFlowException("表单数据异常");
		} catch (WorkFlowException work) {
			//手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new WorkFlowException(work.getMessage());
		}
	}

	@Override
	@Transactional(rollbackFor = WorkFlowException.class)
	public void reject(FlowTaskEntity flowTaskEntity, FlowTaskOperatorEntity flowTaskOperatorEntity, FlowHandleModel flowHandleModel) throws WorkFlowException {
		try {
			BoosterUser userInfo = SecurityUtils.getUser();
			UserVO userVO = usersApi.getUserByUserName(flowTaskEntity.getCreateBy()).getData();
			//流程节点
			List<FlowTaskNodeEntity> flowTaskNodeEntityList = flowTaskNodeService.getList(flowTaskEntity.getId()).stream().filter(t -> FlowNodeEnum.Process.getCode().equals(t.getState())).collect(Collectors.toList());
			//当前节点
			FlowTaskOperatorEntity task = flowTaskOperatorEntity;
			FlowTaskNodeEntity flowTaskNodeEntity = flowTaskNodeEntityList.stream().filter(m -> m.getId().equals(task.getTaskNodeId())).findFirst().get();
			//当前节点属性
			ChildNodeList nodeModel = JsonUtil.getJsonToBean(flowTaskNodeEntity.getNodePropertyJson(), ChildNodeList.class);
			String assignType = nodeModel.getProperties().getAssigneeType();
			//当前节点经办
			List<FlowTaskOperatorEntity> thisFlowTaskOperatorEntityList = flowTaskOperatorService.getList(flowTaskNodeEntity.getTaskId()).stream().filter(t -> FlowNodeEnum.Process.getCode().equals(t.getState())).collect(Collectors.toList());
			//上个节点
			List<FlowTaskNodeEntity> upNodeEntity = flowTaskNodeEntityList.stream().filter(m -> String.valueOf(m.getNodeNext()).contains(String.valueOf(flowTaskNodeEntity.getNodeCode()))).collect(Collectors.toList());
			//流程经办
			List<FlowTaskOperatorEntity> flowTaskOperatorEntityList = new ArrayList<>();
			//驳回记录
			FlowTaskOperatorRecordEntity flowTaskOperatorRecordEntity = new FlowTaskOperatorRecordEntity();
			flowTaskOperatorRecordEntity.setHandleOpinion(flowHandleModel.getHandleOpinion());
			flowTaskOperatorRecordEntity.setHandleId(userInfo.getUsername());
			flowTaskOperatorRecordEntity.setHandleTime(LocalDateTime.now());
			flowTaskOperatorRecordEntity.setHandleStatus(0);
			flowTaskOperatorRecordEntity.setNodeCode(flowTaskNodeEntity.getNodeCode());
			flowTaskOperatorRecordEntity.setNodeName(flowTaskNodeEntity.getNodeName());
			flowTaskOperatorRecordEntity.setTaskOperatorId(flowTaskOperatorEntity.getId());
			flowTaskOperatorRecordEntity.setTaskNodeId(flowTaskNodeEntity.getId());
			flowTaskOperatorRecordEntity.setTaskId(flowTaskEntity.getId());
			flowTaskOperatorRecordService.create(flowTaskOperatorRecordEntity);
			//更新或签都改成驳回
			if (assignType.equals(String.valueOf(FlowTaskOperatorEnum.FixedApprover.getCode()))) {
				flowTaskOperatorService.update(flowTaskOperatorEntity.getTaskNodeId(), FlowTaskOperatorEnum.FixedApprover.getCode());
			}
			//更新会签都改成驳回
			if (assignType.equals(String.valueOf(FlowTaskOperatorEnum.FixedJointlyApprover.getCode()))) {
				flowTaskOperatorService.update(flowTaskOperatorEntity.getTaskNodeId(), FlowTaskOperatorEnum.FixedJointlyApprover.getCode());
			}
			// 更新经办
			if (assignType.equals(String.valueOf(FlowTaskOperatorEnum.FixedJointlyApprover.getCode()))) {
				flowTaskOperatorEntity = thisFlowTaskOperatorEntityList.stream().filter(t -> t.getHandleId().equals(userInfo.getUsername())).findFirst().get();
			}
			flowTaskOperatorEntity.setCompletion(1);
			flowTaskOperatorEntity.setHandleStatus(0);
			flowTaskOperatorEntity.setHandleTime(LocalDateTime.now());
			flowTaskOperatorService.update(flowTaskOperatorEntity);
			//更新节点
			flowTaskNodeEntity.setCompletion(-1);
			flowTaskNodeService.update(flowTaskNodeEntity);
			//驳回节点 true驳回节点、false驳回开始
			boolean isWill = true;
			//判断驳回节点是否存在
			boolean isNode = false;
			//单前节点id
			Set<String> stepId = new HashSet<>(16);
			Set<String> stepTitle = new HashSet<>(16);
			List<String> progress = new ArrayList<>();
			//驳回的节点
			Set<String> rejectId = new HashSet<>(16);
			//更新实例
			if (FlowNature.START.equals(flowTaskNodeEntity.getNodeUp())) {
				isWill = false;
			} else if (FlowNature.UP.equals(flowTaskNodeEntity.getNodeUp())) {
				List<FlowTaskNodeEntity> upTaskList = new ArrayList<>();
				long all = upNodeEntity.stream().filter(t1 -> "start".equals(t1.getNodeType())).count();
				//上一节点
				for (FlowTaskNodeEntity taskNode : upNodeEntity) {
					ChildNodeList modelList = JsonUtil.getJsonToBean(taskNode.getNodePropertyJson(), ChildNodeList.class);
					Properties properties = modelList.getProperties();
					String upType = properties.getAssigneeType();
					upTaskList.add(taskNode);
					//加签
					if (upType.equals(String.valueOf(FlowTaskOperatorEnum.FreeApprover.getCode()))) {
						upTaskList.remove(taskNode);
						List<FlowTaskNodeEntity> upFree = flowTaskNodeEntityList.stream().filter(m -> String.valueOf(m.getNodeNext()).contains(String.valueOf(taskNode.getNodeCode()))).collect(Collectors.toList());
						long count = upFree.stream().filter(t -> "start".equals(t.getNodeType())).count();
						all += count;
						upTaskList.addAll(upFree);
					}
				}
				//判断是否驳回开始节点
				isWill = all == 0;
				if (isWill) {
					for (FlowTaskNodeEntity entity : upTaskList) {
						rejectId.add(entity.getNodeCode());
						ChildNodeList modelList = JsonUtil.getJsonToBean(entity.getNodePropertyJson(), ChildNodeList.class);
						Custom custom = modelList.getCustom();
						Properties properties = modelList.getProperties();
						String upType = properties.getAssigneeType();
						Long taskId = Convert.toLong(modelList.getTaskId());
						Long taskNodeId = Convert.toLong(modelList.getTaskNodeId());
						//创建经办
						//发起者【发起主管】
						if (upType.equals(String.valueOf(FlowTaskOperatorEnum.LaunchCharge.getCode()))) {
							FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
							flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.LaunchCharge.getCode()));
							flowTask.setHandleId(userVO.getManager());
							flowTask.setNodeCode(modelList.getCustom().getNodeId());
							flowTask.setNodeName(properties.getTitle());
							flowTask.setTaskNodeId(taskNodeId);
							flowTask.setTaskId(taskId);
							flowTask.setCreateTime(LocalDateTime.now());
							flowTask.setCompletion(0);
							flowTask.setState(FlowNodeEnum.Process.getCode());
							flowTask.setType(upType);
							flowTaskOperatorEntityList.add(flowTask);
						}
						//发起者【部门主管】
						if (upType.equals(String.valueOf((FlowTaskOperatorEnum.DepartmentCharge.getCode())))) {
							R<SysDeptEntity> organizeEntity = organizeApi.infoByName(userVO.getDeptName());
							if (organizeEntity.getData() != null && StrUtil.isEmpty(organizeEntity.getData().getManager())) {
								throw new WorkFlowException("没有找到部门主管");
							}
							FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
							flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.DepartmentCharge.getCode()));
							flowTask.setHandleId(organizeEntity.getData().getManager());
							flowTask.setNodeCode(modelList.getCustom().getNodeId());
							flowTask.setNodeName(properties.getTitle());
							flowTask.setTaskNodeId(taskNodeId);
							flowTask.setTaskId(taskId);
							flowTask.setCreateTime(LocalDateTime.now());
							flowTask.setCompletion(0);
							flowTask.setState(FlowNodeEnum.Process.getCode());
							flowTask.setType(upType);
							flowTaskOperatorEntityList.add(flowTask);
						}
						//发起者【指定用户】
						for (String userName : properties.getApprovers()) {
							FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
							flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.AppointUser.getCode()));
							flowTask.setHandleId(userName);
							flowTask.setNodeCode(modelList.getCustom().getNodeId());
							flowTask.setNodeName(properties.getTitle());
							flowTask.setTaskNodeId(taskNodeId);
							flowTask.setTaskId(taskId);
							flowTask.setCreateTime(LocalDateTime.now());
							flowTask.setCompletion(0);
							flowTask.setState(FlowNodeEnum.Process.getCode());
							flowTask.setType(upType);
							flowTaskOperatorEntityList.add(flowTask);
						}
						//发起者【指定岗位】
						if (properties.getApproverPos().length > 0) {
							List<String> userPosition = positionApi.getUserPositionByIds(String.join(",", properties.getApproverPos())).getData().getUserNames();
							getApproverUser(userPosition, flowTaskOperatorEntityList, modelList);
						}
						//发起者【发起本人】
						if (upType.equals(String.valueOf(FlowTaskOperatorEnum.InitiatorMe.getCode()))) {
							FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
							flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.InitiatorMe.getCode()));
							flowTask.setHandleId(flowTaskEntity.getCreateBy());
							flowTask.setNodeCode(modelList.getCustom().getNodeId());
							flowTask.setNodeName(properties.getTitle());
							flowTask.setTaskNodeId(taskNodeId);
							flowTask.setTaskId(taskId);
							flowTask.setCreateTime(LocalDateTime.now());
							flowTask.setCompletion(0);
							flowTask.setState(FlowNodeEnum.Process.getCode());
							flowTask.setType(upType);
							flowTaskOperatorEntityList.add(flowTask);
						}
						stepId.add(custom.getNodeId());
						stepTitle.add(properties.getTitle());
						if (properties.getProgress() != null) {
							progress.add(properties.getProgress());
						}
					}
				}
			} else {
				//任意节点
				upNodeEntity = flowTaskNodeEntityList.stream().filter(m -> String.valueOf(m.getNodeCode()).equals(String.valueOf(flowTaskNodeEntity.getNodeUp()))).collect(Collectors.toList());
				//判断驳回节点是否是开始
				isNode = upNodeEntity.size() == 0;
				for (FlowTaskNodeEntity taskNode : upNodeEntity) {
					rejectId.add(taskNode.getNodeCode());
					ChildNodeList modelList = JsonUtil.getJsonToBean(taskNode.getNodePropertyJson(), ChildNodeList.class);
					Custom custom = modelList.getCustom();
					Properties properties = modelList.getProperties();
					String upType = String.valueOf(properties.getAssigneeType());
					Long taskId = Convert.toLong(modelList.getTaskId());
					Long taskNodeId = Convert.toLong(modelList.getTaskNodeId());
					//创建经办
					//发起者【发起主管】
					if (upType.equals(String.valueOf(FlowTaskOperatorEnum.LaunchCharge.getCode()))) {
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.LaunchCharge.getCode()));
						flowTask.setHandleId(userVO.getManager());
						flowTask.setNodeCode(modelList.getCustom().getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.now());
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(upType);
						flowTaskOperatorEntityList.add(flowTask);
					}
					//发起者【部门主管】
					if (upType.equals(String.valueOf((FlowTaskOperatorEnum.DepartmentCharge.getCode())))) {
						R<SysDeptEntity> organizeEntity = organizeApi.infoByName(userVO.getDeptName());
						if (organizeEntity.getData() != null && StrUtil.isEmpty(organizeEntity.getData().getManager())) {
							throw new WorkFlowException("没有找到部门主管");
						}
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.DepartmentCharge.getCode()));
						flowTask.setHandleId(organizeEntity.getData().getManager());
						flowTask.setNodeCode(modelList.getCustom().getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.now());
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(upType);
						flowTaskOperatorEntityList.add(flowTask);
					}
					//发起者【指定用户】
					for (String userName : properties.getApprovers()) {
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.AppointUser.getCode()));
						flowTask.setHandleId(userName);
						flowTask.setNodeCode(modelList.getCustom().getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.now());
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(upType);
						flowTaskOperatorEntityList.add(flowTask);
					}
					//发起者【指定岗位】
					if (properties.getApproverPos().length > 0) {
						List<String> userPosition = positionApi.getUserPositionByIds(String.join(",", properties.getApproverPos())).getData().getUserNames();
						getApproverUser(userPosition, flowTaskOperatorEntityList, modelList);
					}
					//发起者【发起本人】
					if (upType.equals(String.valueOf(FlowTaskOperatorEnum.InitiatorMe.getCode()))) {
						FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
						flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.InitiatorMe.getCode()));
						flowTask.setHandleId(flowTaskEntity.getCreateBy());
						flowTask.setNodeCode(modelList.getCustom().getNodeId());
						flowTask.setNodeName(properties.getTitle());
						flowTask.setTaskNodeId(taskNodeId);
						flowTask.setTaskId(taskId);
						flowTask.setCreateTime(LocalDateTime.now());
						flowTask.setCompletion(0);
						flowTask.setState(FlowNodeEnum.Process.getCode());
						flowTask.setType(upType);
						flowTaskOperatorEntityList.add(flowTask);
					}
					stepId.add(custom.getNodeId());
					stepTitle.add(properties.getTitle());
					if (properties.getProgress() != null) {
						progress.add(properties.getProgress());
					}
				}
			}
			//驳回任意节点不存在
			if (isNode) {
				throw new WorkFlowException("任意节点驳回失败");
			}
			//驳回节点
			if (isWill) {
				//查询驳回节点之后经过的节点
				Set<String> nodeIdAll = new HashSet<>(16);
				List<String> reTitle = new ArrayList<>();
				for (String nodeId : rejectId) {
					//获取驳回节点之后经过的节点
					FlowJsonUtil.nextList(flowTaskNodeEntityList, nodeId, nodeIdAll, new String[]{});
					flowTaskNodeEntityList.stream().filter(t -> t.getNodeCode().equals(nodeId)).findFirst().ifPresent(entity -> reTitle.add(entity.getNodeName()));
				}
				//驳回节点之后的节点作废
				flowTaskOperatorService.updateReject(flowTaskEntity.getId(), nodeIdAll);
				//替换当前节点
				String[] stepIdsAll = flowTaskEntity.getThisStepId().split(",");
				Set<String> id = new HashSet<>(16);
				List<String> title = new ArrayList<>();
				for (String ids : stepIdsAll) {
					//判断驳回节点之后的节点是否经过当前节点
					boolean contains = nodeIdAll.contains(ids);
					if (!contains) {
						FlowTaskNodeEntity entity = flowTaskNodeEntityList.stream().filter(t -> t.getNodeCode().equals(ids)).findFirst().orElse(null);
						if (ids.equals(flowTaskNodeEntity.getNodeCode())) {
							String step = String.join(",", stepId);
							String stepIdAll = ids.replace(flowTaskNodeEntity.getNodeCode(), step);
							id.add(stepIdAll);
							List<String> name = flowTaskNodeEntityList.stream().filter(t -> step.contains(t.getNodeCode())).map(FlowTaskNodeEntity::getNodeName).collect(Collectors.toList());
							title.addAll(name);
						} else {
							id.add(ids);
							if (entity != null) {
								title.add(entity.getNodeName());
							}
						}
					}
				}
				if (id.size() == 0) {
					id.addAll(rejectId);
					title.addAll(reTitle);
				}
				flowTaskEntity.setThisStepId(String.join(",", id));
				flowTaskEntity.setThisStep(String.join(",", title));
				//节点进度
				Collections.sort(progress);
				flowTaskEntity.setCompletion(progress.size() > 0 ? Integer.valueOf(progress.get(0)) : null);
				flowTaskOperatorService.create(flowTaskOperatorEntityList);
			} else {
				//从头开始
				flowTaskEntity.setThisStepId(flowTaskNodeEntity.getNodeCode());
				flowTaskEntity.setThisStep("开始");
				flowTaskEntity.setCompletion(0);
				flowTaskEntity.setStatus(FlowTaskStatusEnum.Reject.getCode());
				flowTaskNodeService.update(flowTaskEntity.getId());
				flowTaskOperatorService.update(flowTaskEntity.getId());
			}
			//更新自定义数据
			FlowEngineEntity flowentity = flowEngineService.getInfo(flowTaskEntity.getFlowId());
			if (FlowNature.CUSTOM.equals(flowentity.getFormType())) {
				List<FlowTableModel> tableModelList = JsonUtil.getJsonToList(flowentity.getRefTables(), FlowTableModel.class);
				Object objectData = flowHandleModel.getFormData();
				if (objectData instanceof Map) {
					Map<String, Object> formDataAll = (Map<String, Object>) objectData;
					Map<String, Object> data = JsonUtil.stringToMap(String.valueOf(formDataAll.get("data")));
					//formTempJson
					FormDataModel formData = JsonUtil.getJsonToBean(flowTaskEntity.getFlowForm(), FormDataModel.class);
					List<FieLdsModel> list = JsonUtil.getJsonToList(formData.getFields(), FieLdsModel.class);
					Map<String, Object> dataAll = flowDataUtil.update(data, list, tableModelList, flowTaskEntity.getProcessId());
					//更新task表数据
					flowTaskEntity.setFlowFormContentJson(JsonUtil.getObjectToString(dataAll));
				}
			} else if (flowentity.getFormType() == 1) {
				String coed = flowHandleModel.getEncode();
				Object objectData = flowHandleModel.getFormData();
				if (objectData instanceof Map) {
					String data = JsonUtil.getObjectToString(objectData);
					formData(coed, flowTaskEntity.getId(), data);
				}
			}
			this.updateById(flowTaskEntity);
			//驳回事件
			Properties properties = nodeModel.getProperties();
			boolean func = properties.getHasApproverfunc() != null ? properties.getHasApproverfunc() : false;
			if (func) {
				String faceUrl = properties.getApproverInterfaceUrl()
						+ "?" + taskNodeId + "=" + flowTaskNodeEntity.getId()
						+ "&" + handleStatus + "=" + flowTaskOperatorRecordEntity.getHandleStatus()
						+ "&" + taskId + "=" + flowTaskNodeEntity.getTaskId()
						+ "&" + taskStatus + "=" + flowTaskEntity.getStatus();
				System.out.println("进入驳回事件:" + faceUrl);
				httpRequestAll(faceUrl);
			}
			//消息提醒
			Map<String, Object> message = new HashMap<>(16);
			message.put("type", FlowMessageEnum.wait.getCode());
			message.put("id", flowTaskEntity.getId());
			messagePush(flowTaskOperatorEntityList, flowTaskEntity.getFullName() + "【审核】", JsonUtilEx.getObjectToString(message));
			//创建者消息提醒
			List<String> toUserNames = new ArrayList<>();
			toUserNames.add(flowTaskEntity.getCreateBy());
			message.put("type", FlowMessageEnum.me.getCode());
			SentMessageModel model = new SentMessageModel();
			model.setBodyText(JsonUtil.getObjectToString(message));
			model.setTitle(flowTaskEntity.getFullName() + "【审核驳回】");
			model.setToUserNames(toUserNames);
			noticeApi.sentMessage(model);
		} catch (SQLException e) {
			//手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new WorkFlowException("表单数据异常");
		} catch (
				WorkFlowException work) {
			//手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new WorkFlowException(work.getMessage());
		}
	}

	@Override
	public void recall(FlowTaskEntity flowTaskEntity, List<FlowTaskNodeEntity> flowTaskNodeList, FlowTaskOperatorRecordEntity flowTaskOperatorRecordEntity, FlowHandleModel flowHandleModel) throws WorkFlowException {
		if (flowTaskEntity.getCompletion() == 100) {
			throw new WorkFlowException("当前流程已结束，无法撤回流程");
		}
		//获取撤回节点的编码
		FlowTaskNodeEntity nodeEntity = flowTaskNodeList.stream().filter(t -> t.getId().equals(flowTaskOperatorRecordEntity.getTaskNodeId())).findFirst().get();
		//驳回任意节点
		List<FlowTaskNodeEntity> upList = flowTaskNodeList.stream().filter(t -> nodeEntity.getNodeUp().equals(t.getNodeCode())).collect(Collectors.toList());
		if (upList.size() == 0) {
			//驳回上一节点
			upList = flowTaskNodeList.stream().filter(t -> nodeEntity.getNodeNext() != null && nodeEntity.getNodeNext().contains(t.getNodeCode())).collect(Collectors.toList());
		}
		//获取驳回节点
		List<String> upNode = upList.stream().map(FlowTaskNodeEntity::getNodeCode).collect(Collectors.toList());
		//获取经办数据
		List<FlowTaskOperatorEntity> operatorList = flowTaskOperatorService.getList(flowTaskEntity.getId()).stream().filter(t -> FlowNodeEnum.Process.getCode().equals(t.getState())).collect(Collectors.toList());
		//判断是否进行撤回操作
		boolean isNext = operatorList.stream().anyMatch(t -> upNode.contains(t.getNodeCode()) && t.getCompletion() == 0);
		//查询驳回节点之前经过的节点
		Set<String> nodeIdAll = new HashSet<>(16);
		FlowJsonUtil.upList(flowTaskNodeList, nodeEntity.getNodeCode(), nodeIdAll, new String[]{});
		FlowJsonUtil.nextList(flowTaskNodeList, nodeEntity.getNodeCode(), nodeIdAll, new String[]{});
		//判断是否是加签和当前节点是否审核过
		if (isNext) {
			//驳回节点之前的节点作废
			flowTaskOperatorService.updateReject(flowTaskEntity.getId(), nodeIdAll);
			//更新经办
			FlowTaskOperatorEntity flowTaskOperatorEntity = flowTaskOperatorService.getInfo(flowTaskOperatorRecordEntity.getTaskOperatorId());
			flowTaskOperatorEntity.setCompletion(0);
			flowTaskOperatorEntity.setHandleStatus(null);
			flowTaskOperatorEntity.setHandleTime(null);
			flowTaskOperatorService.update(flowTaskOperatorEntity);
			//更改记录
			flowTaskOperatorRecordEntity.setTaskOperatorId(null);
			flowTaskOperatorRecordEntity.setTaskNodeId(null);
			flowTaskOperatorRecordService.update(flowTaskOperatorRecordEntity.getId(), flowTaskOperatorRecordEntity);
			//召回记录
			flowTaskOperatorRecordEntity.setHandleId(SecurityUtils.getUser().getUsername());
			flowTaskOperatorRecordEntity.setHandleTime(LocalDateTime.now());
			flowTaskOperatorRecordEntity.setHandleStatus(3);
			flowTaskOperatorRecordEntity.setNodeName(nodeEntity.getNodeName());
			flowTaskOperatorRecordEntity.setTaskId(flowTaskEntity.getId());
			flowTaskOperatorRecordEntity.setHandleOpinion(flowHandleModel.getHandleOpinion());
			flowTaskOperatorRecordService.create(flowTaskOperatorRecordEntity);
			//删除传阅人
			flowTaskCirculateService.deleteByNodeId(nodeEntity.getId());
			//查询单前节点
			List<String> thisStepId = Arrays.asList(flowTaskEntity.getThisStepId().split(","));
			List<String> thisStepIdAll = thisStepId.stream().filter(item -> !upNode.contains(item)).collect(Collectors.toList());
			//赋值当前节点
			thisStepIdAll.add(flowTaskOperatorRecordEntity.getNodeCode());
			//节点名称
			List<String> stepTitleAll = new ArrayList<>();
			for (String id : thisStepIdAll) {
				FlowTaskNodeEntity entity = flowTaskNodeList.stream().filter(t -> t.getNodeCode().equals(id)).findFirst().get();
				stepTitleAll.add(entity.getNodeName());
			}
			flowTaskEntity.setThisStepId(String.join(",", thisStepIdAll));
			flowTaskEntity.setThisStep(String.join(",", stepTitleAll));
			flowTaskEntity.setCompletion(0);
			flowTaskEntity.setStatus(FlowTaskStatusEnum.Handle.getCode());
			this.updateById(flowTaskEntity);
			//撤回事件
			ChildNodeList nodeModel = JsonUtil.getJsonToBean(nodeEntity.getNodePropertyJson(), ChildNodeList.class);
			Properties properties = nodeModel.getProperties();
			boolean func = properties.getHasRecallFunc() != null ? properties.getHasRecallFunc() : false;
			if (func) {
				String faceUrl = properties.getRecallInterfaceUrl()
						+ "?" + taskNodeId + "=" + nodeEntity.getId()
						+ "&" + handleStatus + "=" + flowTaskOperatorRecordEntity.getHandleStatus()
						+ "&" + taskId + "=" + nodeEntity.getTaskId()
						+ "&" + taskStatus + "=" + flowTaskEntity.getStatus();
				System.out.println("进入撤回事件:" + faceUrl);
				httpRequestAll(faceUrl);
			}
		} else {
			throw new WorkFlowException("当前流程被处理，无法撤回流程");
		}
	}

	@Override
	public void cancel(FlowTaskEntity flowTaskEntity, FlowHandleModel flowHandleModel) {
		//更新实例
		flowTaskEntity.setStatus(FlowTaskStatusEnum.Cancel.getCode());
		flowTaskEntity.setEndTime(LocalDateTime.now());
		this.updateById(flowTaskEntity);
		//作废记录
		BoosterUser userInfo = SecurityUtils.getUser();
		FlowTaskOperatorRecordEntity flowTaskOperatorRecordEntity = new FlowTaskOperatorRecordEntity();
		flowTaskOperatorRecordEntity.setHandleOpinion(flowHandleModel.getHandleOpinion());
		flowTaskOperatorRecordEntity.setHandleId(userInfo.getUsername());
		flowTaskOperatorRecordEntity.setHandleTime(LocalDateTime.now());
		flowTaskOperatorRecordEntity.setHandleStatus(4);
		flowTaskOperatorRecordEntity.setNodeName(flowTaskEntity.getThisStep());
		flowTaskOperatorRecordEntity.setTaskId(flowTaskEntity.getId());
		flowTaskOperatorRecordService.create(flowTaskOperatorRecordEntity);
	}

	@Override
	public List<FlowTaskEntity> getTaskList(Long id) {
		QueryWrapper<FlowTaskEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(FlowTaskEntity::getFlowId, id);
		return this.list(queryWrapper);
	}

	@Override
	public List<FlowTaskEntity> getOrderStaList(List<Long> id) {
		QueryWrapper<FlowTaskEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().in(FlowTaskEntity::getId, id);
		return this.list(queryWrapper);
	}

	/**
	 * 验证有效状态
	 *
	 * @param status 状态编码
	 * @return
	 */
	private boolean checkStatus(int status) {
		return status == FlowTaskStatusEnum.Draft.getCode() || status == FlowTaskStatusEnum.Reject.getCode() || status == FlowTaskStatusEnum.Revoke.getCode();
	}

	/**
	 * 消息提醒（审核提醒）
	 *
	 * @param data     经办用户
	 * @param title    消息标题
	 * @param bodyText 消息内容
	 */
	private void messagePush(List<FlowTaskOperatorEntity> data, String title, String bodyText) {
		if (data.size() > 0) {
			List<String> toUserNames = new ArrayList<>();
			for (FlowTaskOperatorEntity item : data) {
				if (item.getHandleType().equals(String.valueOf(FlowTaskOperatorEnum.AppointPosition.getCode()))) {
					//根据岗位找出用户列表
					toUserNames = positionApi.getUserPositionByIds(item.getHandleId()).getData().getUserNames();
				} else {
					if (StrUtil.isNotEmpty(item.getHandleId())) {
						toUserNames.add(item.getHandleId());
					}
				}
			}
			SentMessageModel model = new SentMessageModel();
			model.setBodyText(bodyText);
			model.setTitle(title);
			model.setToUserNames(toUserNames);
			noticeApi.sentMessage(model);
		}
	}

	/**
	 * 消息提醒（抄送提醒）
	 *
	 * @param data     经办用户
	 * @param title    消息标题
	 * @param bodyText 消息内容
	 */
	private void messagePushCirculate(List<FlowTaskCirculateEntity> data, String title, String bodyText) {
		if (data.size() > 0) {
			List<String> toUserNames = new ArrayList<>();
			for (FlowTaskCirculateEntity item : data) {
				if (item.getObjectType().equals(String.valueOf(FlowTaskOperatorEnum.AppointPosition.getCode()))) {
					//根据岗位找出用户列表
					toUserNames = positionApi.getUserPositionByIds(item.getObjectId()).getData().getUserNames();
				} else {
					toUserNames.add(item.getObjectId());
				}
			}
			SentMessageModel model = new SentMessageModel();
			model.setBodyText(bodyText);
			model.setTitle(title);
			model.setToUserNames(toUserNames);
			noticeApi.sentMessage(model);
		}
	}

	/**
	 * 判断传阅用户是否在添加岗位上
	 *
	 * @param positionAll  岗位数据
	 * @param userPosition 用户岗位数据
	 * @return
	 */
	private boolean getCirculateUser(String[] positionAll, List<String> userPosition) {
		boolean flag = true;
		for (String positionId : positionAll) {
			if (userPosition.stream().anyMatch(t -> t.contains(positionId))) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 判断经办岗位是否存在指定用户
	 *
	 * @param userPosition 获取岗位岗位下的人
	 * @param operatorList 经办的数据
	 * @param nodeModel    节点数据
	 */
	private void getApproverUser(List<String> userPosition, List<FlowTaskOperatorEntity> operatorList, ChildNodeList nodeModel) {
		for (String user : userPosition) {
			//判断是否有添加这个人
			if (operatorList.stream().noneMatch(t -> t.getHandleId().equals(user))) {
				FlowTaskOperatorEntity flowTask = new FlowTaskOperatorEntity();
				flowTask.setHandleType(String.valueOf(FlowTaskOperatorEnum.AppointUser.getCode()));
				flowTask.setHandleId(user);
				flowTask.setNodeCode(nodeModel.getCustom().getNodeId());
				flowTask.setNodeName(nodeModel.getProperties().getTitle());
				flowTask.setTaskNodeId(Convert.toLong(nodeModel.getTaskNodeId()));
				flowTask.setTaskId(Convert.toLong(nodeModel.getTaskId()));
				flowTask.setCreateTime(LocalDateTime.now());
				flowTask.setCompletion(0);
				flowTask.setState(FlowNodeEnum.Process.getCode());
				flowTask.setType(nodeModel.getProperties().getAssigneeType());
				operatorList.add(flowTask);
			}
		}
	}

	/**
	 * 封装属性key和保存list
	 *
	 * @param fieLdsModelList 引擎的json
	 * @param boosKey         属性对象
	 * @param keyList         属性list
	 */
	private void tempJson(List<FieLdsModel> fieLdsModelList, Map<String, String> boosKey, Map<String, Object> keyList) {
		List<SysDeptEntity> organizeList = organizeApi.getList().getData();
		List<UserVO> userList = usersApi.getAll().getData();
		List<SysPositionEntity> positionList = positionApi.getListAll().getData();
		for (FieLdsModel fieLdsModel : fieLdsModelList) {
			String model = fieLdsModel.getVModel();
			ConfigModel config = fieLdsModel.getConfig();
			String key = config.getBoosKey();
			boosKey.put(model, key);
			if ("select".equals(key) || "checkbox".equals(key) || "radio".equals(key)) {
				String type = config.getDataType();
				List<Map<String, String>> optionsList = new ArrayList<>();
				String fullName = config.getProps().getLabel();
				String value = config.getProps().getValue();
				if ("dictionary".equals(type)) {
					String dictionaryType = config.getDictionaryType();
					List<SysDictItemEntity> dicList = dictionaryDataApi.getDictByType(dictionaryType).getData();
					for (SysDictItemEntity dataEntity : dicList) {
						Map<String, String> optionsModel = new HashMap<>(16);
						optionsModel.put("id", dataEntity.getValue());
						optionsModel.put("fullName", dataEntity.getLabel());
						optionsList.add(optionsModel);
					}
				} else if ("static".equals(type)) {
					List<Map<String, Object>> staticList = JsonUtil.getJsonToListMap(fieLdsModel.getSlot().getOptions());
					for (Map<String, Object> options : staticList) {
						Map<String, String> optionsModel = new HashMap<>(16);
						optionsModel.put("id", String.valueOf(options.get(value)));
						optionsModel.put("fullName", String.valueOf(options.get(fullName)));
						optionsList.add(optionsModel);
					}
				} else if ("dynamic".equals(type)) {
					String dynId = config.getPropsUrl();
					//查询外部接口
					Map<String, Object> dynamicMap = new HashMap<>(16);
					if (dynamicMap.get("data") != null) {
						List<Map<String, Object>> dataList = JsonUtil.getJsonToListMap(dynamicMap.get("data").toString());
						for (Map<String, Object> options : dataList) {
							Map<String, String> optionsModel = new HashMap<>(16);
							optionsModel.put("id", String.valueOf(options.get(value)));
							optionsModel.put("fullName", String.valueOf(options.get(fullName)));
							optionsList.add(optionsModel);
						}
					}
				}
				keyList.put(model, optionsList);
			}
			//公司
			if ("comSelect".equals(key)) {
				keyList.put(model, organizeList);
			}
			//部门
			if ("depSelect".equals(key)) {
				keyList.put(model, organizeList);
			}
			//用户
			if ("userSelect".equals(key)) {
				keyList.put(model, userList);
			}
			//岗位
			if ("posSelect".equals(key)) {
				keyList.put(model, positionList);
			}
		}
	}

	private void formData(String code, Long id, String data) throws WorkFlowException {
		try {
            /*Class[] types = new Class[]{String.class, String.class};
            Object[] datas = new Object[]{id, data};
            Object service = SpringContext.getBean(code);
            ReflectionUtil.invokeMethod(service, "data", types, datas);*/
		} catch (Exception e) {
			throw new WorkFlowException("系统表单反射失败");
		}
	}

	/**
	 * 工作流事件接口请求
	 *
	 * @param requestUrl url
	 * @return
	 */
	private void httpRequestAll(String requestUrl) {
		if (RequestType.HTTPS.equalsIgnoreCase(requestUrl.substring(IntegerNumber.ZERO, IntegerNumber.FIVE))) {
			HttpUtil.httpsRequest(requestUrl, MethodType.GET.getMethod(), null);
		} else if (RequestType.HTTP.equalsIgnoreCase(requestUrl.substring(IntegerNumber.ZERO, IntegerNumber.FOUR))) {
			HttpUtil.httpRequest(requestUrl, MethodType.GET.getMethod(), null);
		} else if (RequestType.LOCAL.equalsIgnoreCase(requestUrl.substring(IntegerNumber.ZERO, IntegerNumber.FIVE))) {
			// 内部接口访问URL模式：local://serviceId/path
			String[] pathArray = requestUrl.split("//");
			restTemplate.getForObject(String.format("http://%s", pathArray[1]), R.class);
		}
	}
}
