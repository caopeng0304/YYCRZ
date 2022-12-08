package com.sinosoft.ie.booster.visualdev.controller;


import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.DownloadVO;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevModelDataEntity;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.*;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevModelDataService;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevService;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.custom.VisualUtils;
import com.sinosoft.ie.booster.visualdev.util.ConfigValueUtil;
import com.sinosoft.ie.booster.visualdev.util.onlinedev.AutoFieldsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 0代码无表开发
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Slf4j
@Api(tags = "0代码无表开发")
@RestController
@RequestMapping("/OnlineDev")
public class VisualDevModelDataController {
	@Autowired
	private BaseVisualDevModelDataService visualDevModelDataService;
	@Autowired
	private BaseVisualDevService visualDevService;
	@Autowired
	private ConfigValueUtil configValueUtil;
	@Autowired
	private RedisUtil redisUtil;


	@ApiOperation("获取数据列表")
	@GetMapping("/{modelId}/List")
	public R<PageListVO<Map<String, Object>>> list(@PathVariable("modelId") Long modelId, PaginationModel paginationModel) throws ParseException, IOException, SQLException, DataException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		List<Map<String, Object>> realList = visualDevModelDataService.getListResult(visualdevEntity, paginationModel);
		PaginationVO paginationVO = JsonUtil.getJsonToBean(paginationModel, PaginationVO.class);
		PageListVO<Map<String, Object>> vo = new PageListVO<>();
		vo.setList(realList);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	@ApiOperation("获取列表表单配置JSON")
	@GetMapping("/{modelId}/Config")
	public R<DataInfoVO> getData(@PathVariable("modelId") Long modelId) {
		BaseVisualDevEntity entity = visualDevService.getInfo(modelId);
		DataInfoVO vo = JsonUtil.getJsonToBean(entity, DataInfoVO.class);
		if (vo == null) {
			return R.failed("功能不存在");
		}
		return R.ok(vo);
	}


	@ApiOperation("获取列表配置JSON")
	@GetMapping("/{modelId}/ColumnData")
	public R<FormDataInfoVO> getColumnData(@PathVariable("modelId") Long modelId) {
		BaseVisualDevEntity entity = visualDevService.getInfo(modelId);
		FormDataInfoVO vo = JsonUtil.getJsonToBean(entity, FormDataInfoVO.class);
		return R.ok(vo);
	}


	@ApiOperation("获取表单配置JSON")
	@GetMapping("/{modelId}/FormData")
	public R<ColumnDataInfoVO> getFormData(@PathVariable("modelId") Long modelId) {
		BaseVisualDevEntity entity = visualDevService.getInfo(modelId);
		ColumnDataInfoVO vo = JsonUtil.getJsonToBean(entity, ColumnDataInfoVO.class);
		return R.ok(vo);
	}


	@ApiOperation("获取数据信息")
	@GetMapping("/{modelId}/{id}")
	public R<VisualdevModelDataInfoVO> info(@PathVariable("id") Long id, @PathVariable("modelId") Long modelId) throws DataException, ParseException, SQLException, IOException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		//有表
		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			VisualdevModelDataInfoVO vo = visualDevModelDataService.tableInfo(id, visualdevEntity);
			return R.ok(vo);
		}
		//无表
		BaseVisualDevModelDataEntity entity = visualDevModelDataService.getInfo(id);
		Map<String, Object> formData = JsonUtil.stringToMap(visualdevEntity.getFormData());
		List<FieLdsModel> modelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);
		//去除模板多级控件
		modelList = VisualUtils.deleteMore(modelList);
		String data = AutoFieldsUtil.autoFeilds(modelList, entity.getData());
		entity.setData(data);
		VisualdevModelDataInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, VisualdevModelDataInfoVO.class);
		return R.ok(vo);
	}

	@ApiOperation("获取数据信息(带转换数据)")
	@GetMapping("/{modelId}/{id}/DataChange")
	public R<VisualdevModelDataInfoVO> infoWithDataChange(@PathVariable("modelId") Long modelId, @PathVariable("id") Long id) throws DataException, ParseException, IOException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		if (redisUtil.exists(CacheKeyUtil.VISIUALDATA + modelId)) {
			redisUtil.remove(CacheKeyUtil.VISIUALDATA + modelId);
		}
		//有表
		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			VisualdevModelDataInfoVO vo = visualDevModelDataService.tableInfoDataChange(id, visualdevEntity);
			return R.ok(vo);
		}
		//无表
		VisualdevModelDataInfoVO vo = visualDevModelDataService.infoDataChange(id, visualdevEntity);
		return R.ok(vo);
	}


	@ApiOperation("添加数据")
	@PostMapping("/{modelId}")
	public R<Boolean> create(@PathVariable("modelId") Long modelId, @RequestBody VisualdevModelDataCrForm visualdevModelDataCrForm) throws DataException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);

		visualDevModelDataService.create(visualdevEntity, visualdevModelDataCrForm);
		return R.ok(null, "新建成功");
	}


	@ApiOperation("修改数据")
	@PutMapping("/{modelId}/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @PathVariable("modelId") Long modelId, @RequestBody VisualdevModelDataUpForm visualdevModelDataUpForm) throws DataException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		boolean flag = visualDevModelDataService.update(id, visualdevEntity, visualdevModelDataUpForm);
		if (flag) {
			return R.ok(null, "更新成功");
		}
		return R.failed("更新失败，数据不存在");
	}


	@ApiOperation("删除数据")
	@DeleteMapping("/{modelId}/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id, @PathVariable("modelId") Long modelId) throws DataException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			boolean result = visualDevModelDataService.tableDelete(id, visualdevEntity);
			if (result) {
				return R.ok(null, "删除成功");
			} else {
				return R.failed("删除失败，数据不存在");
			}
		}

		BaseVisualDevModelDataEntity entity = visualDevModelDataService.getInfo(id);
		if (entity != null) {
			visualDevModelDataService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	@ApiOperation("批量删除数据")
	@DeleteMapping("/{modelId}/{ids}/beachDelete")
	public R<Boolean> beachDelete(@PathVariable("ids") String ids, @PathVariable("modelId") Long modelId) throws DataException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		if (!StrUtil.isEmpty(visualdevEntity.getRefTables()) && !OnlineDevData.TABLE_CONST.equals(visualdevEntity.getRefTables())) {
			boolean result = visualDevModelDataService.tableDeleteMore(ids, visualdevEntity);
			if (result) {
				return R.ok(null, "删除成功");
			} else {
				return R.failed("删除失败，数据不存在");
			}
		}
		String[] idList = ids.split(",");
		if (visualDevModelDataService.removeByIds(Arrays.asList(idList))) {
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}


	@ApiOperation("导入")
	@PostMapping("/Model/{modelId}/Actions/Import")
	public R<Boolean> imports(@PathVariable("modelId") Long modelId) {
		BaseVisualDevModelDataEntity entity = visualDevModelDataService.getInfo(modelId);
		List<MultipartFile> list = UpUtil.getFileAll();
		MultipartFile file = list.get(0);
		if (Objects.requireNonNull(file.getOriginalFilename()).contains(".xlsx")) {
			String filePath = configValueUtil.getTemporaryFilePath();
			String fileName = RandomUtil.uuId() + "." + UpUtil.getFileType(file);
			//保存文件
			FileUtil.upFile(file, filePath, fileName);
			File temporary = new File(filePath + fileName);
			return R.ok(null, "导入成功");
		} else {
			return R.failed("选择文件不符合导入");
		}
	}

	@ApiOperation("导出")
	@PostMapping("/{modelId}/Actions/Export")
	public R<DownloadVO> export(@PathVariable("modelId") Long modelId, @RequestBody PaginationModelExport paginationModelExport) throws ParseException, IOException, SQLException, DataException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		String[] keys = !StrUtil.isEmpty(paginationModelExport.getSelectKey()) ? paginationModelExport.getSelectKey().split(",") : new String[0];
		//关键字过滤
		List<Map<String, Object>> realList = visualDevModelDataService.exportData(keys, paginationModelExport, visualdevEntity);
		BoosterUser userInfo = SecurityUtils.getUser();
		DownloadVO vo = VisualUtils.createModelExcel(visualdevEntity.getFormData(), configValueUtil.getTemporaryFilePath(), realList, keys, userInfo);
		return R.ok(vo);
	}


	/**
	 * 模板下载
	 *
	 * @return
	 */
	@ApiOperation("模板下载")
	@GetMapping("/TemplateDownload")
	public R<DownloadVO> templateDownload() {
		BoosterUser userInfo = SecurityUtils.getUser();
		DownloadVO vo = DownloadVO.builder().build();
		try {
			vo.setName("职员信息.xlsx");
			vo.setUrl(UploaderUtil.uploaderFile("/file/DownloadModel?encryption=", userInfo.getId() + "#" + "职员信息.xlsx" + "#" + "Temporary"));
		} catch (Exception e) {
			log.error("信息导出Excel错误:{}", e.getMessage());
		}
		return R.ok(vo);
	}

	/**
	 * 在线开发大写转小写
	 *
	 * @return
	 */
	@ApiOperation("在线开发大写转小写")
	@GetMapping("/changeTypeToLowOne")
	public void changeTypeToLow(Long modelId) {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(modelId);
		visualdevEntity = VisualUtils.changeType(visualdevEntity);
		visualDevService.update(visualdevEntity.getId(), visualdevEntity);
		List<BaseVisualDevModelDataEntity> list = visualDevModelDataService.getList(modelId);
		if (list != null && list.size() > 0) {
			List<Map<String, Object>> dataList = VisualUtils.toLowerKeyList(JsonUtil.getJsonToListMap(JsonUtilEx.getObjectToString(list)));
			list = JsonUtil.getJsonToList(dataList, BaseVisualDevModelDataEntity.class);
			visualDevModelDataService.saveBatch(list);
		}
	}

	/**
	 * 在线开发大写转小写
	 *
	 * @return
	 */
	@ApiOperation("全部在线开发大写转小写")
	@GetMapping("/changeTypeToLowBatch")
	public void changeTypeToLowBatch() {
		List<BaseVisualDevEntity> list = visualDevService.getList();
		for (BaseVisualDevEntity entity : list) {
			entity = VisualUtils.changeType(entity);
			visualDevService.update(entity.getId(), entity);
			List<BaseVisualDevModelDataEntity> modellist = visualDevModelDataService.getList(entity.getId());
			if (list != null && list.size() > 0) {
				List<Map<String, Object>> dataList = VisualUtils.toLowerKeyList(JsonUtil.getJsonToListMap(JsonUtilEx.getObjectToString(modellist)));
				modellist = JsonUtil.getJsonToList(dataList, BaseVisualDevModelDataEntity.class);
				visualDevModelDataService.updateBatchById(modellist);
			}
		}

	}

}

