package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.JsonUtilEx;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.constant.BoosKeyConst;
import com.sinosoft.ie.booster.visualdev.constant.enums.DicTypeEnum;
import com.sinosoft.ie.booster.visualdev.entity.BaseVisualDevEntity;
import com.sinosoft.ie.booster.visualdev.model.base.*;
import com.sinosoft.ie.booster.visualdev.model.base.Template6.BtnData;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.PaginationModel;
import com.sinosoft.ie.booster.visualdev.model.onlinedev.fields.FieLdsModel;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevModelDataService;
import com.sinosoft.ie.booster.visualdev.service.BaseVisualDevService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 可视化基础模块
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "可视化基础模块")
@RestController
@RequestMapping("/Base")
public class VisualDevController {

	@Autowired
	private BaseVisualDevService visualDevService;
	@Autowired
	private RemoteDictService dictionaryDataService;
	@Autowired
	private BaseVisualDevModelDataService visualDevModelDataService;


	@ApiOperation("获取功能列表")
	@GetMapping
	public R<ListVO<VisualDevListVO>> list(PaginationVisualdev paginationVisualdev) {
		List<BaseVisualDevEntity> list = visualDevService.getList(paginationVisualdev);
		List<VisualDevListVO> data = JsonUtil.getJsonToList(list, VisualDevListVO.class);
		ListVO<VisualDevListVO> listVO = new ListVO<>();
		listVO.setList(data);
		return R.ok(listVO);
	}

	@ApiOperation("获取功能列表下拉框")
	@GetMapping("/Selector")
	public R<ListVO<VisualDevTreeVO>> selectorList(Integer type) {
		List<BaseVisualDevEntity> list = visualDevService.getList().stream().filter(t -> t.getState() == 1).collect(Collectors.toList());
		if (type != null) {
			list = list.stream().filter(t -> type.equals(t.getType())).collect(Collectors.toList());
		}
		List<VisualDevTreeVO> voList = new ArrayList<>();
		String dictType = DicTypeEnum.VISUADEV_WEB_DESIGN_CATEGORY.getType();
		if (type != null) {
			switch (type) {
				case 1:
					dictType = DicTypeEnum.VISUADEV_WEB_DESIGN_CATEGORY.getType();
					break;
				case 2:
					dictType = DicTypeEnum.VISUADEV_APP_DESIGN_CATEGORY.getType();
					break;
				case 3:
					dictType = DicTypeEnum.CODEGEN_FLOW_FORM_CATEGORY.getType();
					break;
				case 4:
					dictType = DicTypeEnum.CODEGEN_WEB_FORM_CATEGORY.getType();
					break;
				case 5:
					dictType = DicTypeEnum.CODEGEN_APP_FORM_CATEGORY.getType();
					break;
				default: break;
			}
		}
		R<List<SysDictItemEntity>> result = dictionaryDataService.getDictByType(dictType);
		for (SysDictItemEntity entity : result.getData()) {
			VisualDevTreeVO visualdevTreeVO = new VisualDevTreeVO();
			visualdevTreeVO.setId(entity.getValue());
			visualdevTreeVO.setFullName(entity.getLabel());
			visualdevTreeVO.setHasChildren(true);
			voList.add(visualdevTreeVO);
		}
		for (VisualDevTreeVO vo : voList) {
			List<VisualDevTreeChildModel> visualdevTreeChildModelList = new ArrayList<>();
			for (BaseVisualDevEntity entity : list) {
				if (vo.getId().equals(entity.getCategory())) {
					VisualDevTreeChildModel model = JsonUtil.getJsonToBean(entity, VisualDevTreeChildModel.class);
					visualdevTreeChildModelList.add(model);
				}
			}
			vo.setChildren(visualdevTreeChildModelList);
		}
		//去掉没有子节点的项
		voList.removeIf(visualdevTreeVO -> visualdevTreeVO.getChildren().size() == 0);
		ListVO<VisualDevTreeVO> listVO = new ListVO<>();
		listVO.setList(voList);
		return R.ok(listVO);
	}

	@ApiOperation("获取功能信息")
	@GetMapping("/{id}")
	public R<VisualDevInfoVO> info(@PathVariable("id") Long id) throws DataException {
		BaseVisualDevEntity entity = visualDevService.getInfo(id);
		VisualDevInfoVO vo = JsonUtilEx.getJsonToBeanEx(entity, VisualDevInfoVO.class);
		return R.ok(vo);
	}

	@ApiOperation("获取表单主表属性下拉框")
	@GetMapping("/{id}/FormDataFields")
	public R<ListVO<FormDataField>> getFormData(@PathVariable("id") Long id) {
		BaseVisualDevEntity entity = visualDevService.getInfo(id);
		Map<String, Object> formData = JsonUtil.stringToMap(entity.getFormData());
		List<FieLdsModel> fieLdsModelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);
		List<FormDataField> formDataFieldList = new ArrayList<>();
		for (FieLdsModel fieLdsModel : fieLdsModelList) {
			if (!"".equals(fieLdsModel.getVModel()) && !BoosKeyConst.CHILD_TABLE.equals(fieLdsModel.getConfig().getBoosKey()) && !"relationForm".equals(fieLdsModel.getConfig().getBoosKey())) {
				FormDataField formDataField = new FormDataField();
				formDataField.setLabel(fieLdsModel.getConfig().getLabel());
				formDataField.setVModel(fieLdsModel.getVModel());
				formDataFieldList.add(formDataField);
			}
		}
		ListVO<FormDataField> listVO = new ListVO<>();
		listVO.setList(formDataFieldList);
		return R.ok(listVO);
	}

	@ApiOperation("获取表单主表属性列表")
	@GetMapping("/{id}/FieldDataSelect")
	public R<ListVO<FieldDataSelectVO>> getFormData(@PathVariable("id") Long id, String field) throws ParseException, DataException, IOException, SQLException {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(id);
		PaginationModel paginationModel = new PaginationModel();
		Map<String, Object> formData = JsonUtil.stringToMap(visualdevEntity.getFormData());
		List<FieLdsModel> fieLdsModelList = JsonUtil.getJsonToList(formData.get("fields").toString(), FieLdsModel.class);
		List<FieLdsModel> newFieLdsModelList = new ArrayList<>();
		for (FieLdsModel fieLdsModel : fieLdsModelList) {
			if (field.equals(fieLdsModel.getVModel())) {
				newFieLdsModelList.add(fieLdsModel);
			}
		}
		formData.put("fields", JsonUtilEx.getObjectToString(newFieLdsModelList));
		visualdevEntity.setFormData(JsonUtil.getJsonToBean(formData, String.class));
		List<Map<String, Object>> realList = visualDevModelDataService.getListResult(visualdevEntity, paginationModel);
		List<FieldDataSelectVO> voList = new ArrayList<>();
		for (Map<String, Object> realMap : realList) {
			if (realMap.containsKey(field)) {
				FieldDataSelectVO fieldDataSelectVO = new FieldDataSelectVO();
				fieldDataSelectVO.setId(realMap.get("id").toString());
				fieldDataSelectVO.setFullName(realMap.get(field).toString());
				voList.add(fieldDataSelectVO);
			}
		}
		ListVO<FieldDataSelectVO> listVO = new ListVO<>();
		listVO.setList(voList);
		return R.ok(listVO);
	}


	/**
	 * 复制功能
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation("复制功能")
	@PostMapping("/{id}/Actions/Copy")
	public R<Boolean> copyInfo(@PathVariable("id") Long id) {
		BaseVisualDevEntity entity = visualDevService.getInfo(id);
		entity.setId(null);
		entity.setState(0);
		entity.setFullName(entity.getFullName() + "_副本");
		entity.setUpdateTime(null);
		entity.setUpdateBy(null);
		entity.setCreateTime(null);
		BaseVisualDevEntity entity1 = JsonUtil.getJsonToBean(entity, BaseVisualDevEntity.class);
		visualDevService.create(entity1);
		return R.ok(null, "新建成功");
	}


	/**
	 * 更新功能状态
	 *
	 * @param id 主键值
	 * @return
	 */
	@ApiOperation("更新功能状态")
	@PutMapping("/{id}/Actions/State")
	public R<Boolean> update(@PathVariable("id") Long id) {
		BaseVisualDevEntity entity = visualDevService.getInfo(id);
		if (entity != null) {
			if (entity.getState() == 1) {
				entity.setState(0);
			} else {
				entity.setState(1);
			}
			boolean flag = visualDevService.update(entity.getId(), entity);
			if (!flag) {
				return R.failed("更新失败，任务不存在");
			}
		}
		return R.ok(null, "更新成功");
	}


	@ApiOperation("新建功能")
	@PostMapping
	public R<Boolean> create(@RequestBody VisualDevCrForm visualDevCrForm) {
		BaseVisualDevEntity entity = JsonUtil.getJsonToBean(JsonUtilEx.getObjectToString(visualDevCrForm), BaseVisualDevEntity.class);
		visualDevService.create(entity);
		return R.ok(null, "新建成功");
	}


	@ApiOperation("修改功能")
	@PutMapping("/{id}")
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody VisualDevUpForm visualDevUpForm) {
		BaseVisualDevEntity entity = JsonUtil.getJsonToBean(JsonUtilEx.getObjectToString(visualDevUpForm), BaseVisualDevEntity.class);
		boolean flag = visualDevService.update(id, entity);
		if (!flag) {
			return R.failed("更新失败，数据不存在");
		}
		return R.ok(null, "更新成功");
	}


	@ApiOperation("删除功能")
	@DeleteMapping("/{id}")
	public R<Boolean> delete(@PathVariable("id") Long id) {
		BaseVisualDevEntity entity = visualDevService.getInfo(id);
		if (entity != null) {
			visualDevService.delete(entity);
			return R.ok(null, "删除成功");
		}
		return R.failed("删除失败，数据不存在");
	}

	@ApiOperation("获取模板按钮和列表字段")
	@GetMapping("/ModuleBtn")
	public R<List<BtnData>> getModuleBtn(Long moduleId) {
		BaseVisualDevEntity visualdevEntity = visualDevService.getInfo(moduleId);
		List<BtnData> btnData = new ArrayList<>();
		Map<String, Object> column = JsonUtil.stringToMap(visualdevEntity.getColumnData());
		if (column.get("columnBtnsList") != null) {
			btnData.addAll(JsonUtil.getJsonToList(JsonUtil.getJsonToListMap(column.get("columnBtnsList").toString()), BtnData.class));
		}
		if (column.get("btnsList") != null) {
			btnData.addAll(JsonUtil.getJsonToList(JsonUtil.getJsonToListMap(column.get("btnsList").toString()), BtnData.class));
		}
		return R.ok(btnData);
	}

}

