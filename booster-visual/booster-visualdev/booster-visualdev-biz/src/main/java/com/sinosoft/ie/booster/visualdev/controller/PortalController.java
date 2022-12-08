package com.sinosoft.ie.booster.visualdev.controller;

import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.entity.BasePortalEntity;
import com.sinosoft.ie.booster.visualdev.model.portal.*;
import com.sinosoft.ie.booster.visualdev.service.BasePortalService;
import com.sinosoft.ie.booster.visualdev.constant.enums.DicTypeEnum;
import com.sinosoft.ie.booster.common.core.util.treeutil.SumTree;
import com.sinosoft.ie.booster.common.core.util.treeutil.TreeDotUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 可视化门户
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Slf4j
@RestController
@Api(tags = "可视化门户")
@RequestMapping("/Portal")
public class PortalController {

	@Autowired
	private BasePortalService portalService;
	@Autowired
	private RemoteDictService dictionaryDataApi;


	@GetMapping
	public R<PageListVO<PortalListVO>> list(PortalPagination portalPagination) {
		List<BasePortalEntity> list = portalService.getList(portalPagination);
		List<PortalListVO> listVO = JsonUtil.getJsonToList(list, PortalListVO.class);
		PageListVO<PortalListVO> vo = new PageListVO<>();
		vo.setList(listVO);
		PaginationVO page = JsonUtil.getJsonToBean(portalPagination, PaginationVO.class);
		vo.setPagination(page);
		return R.ok(vo);
	}

	@GetMapping("/Selector")
	public R<ListVO<PortalSelectVO>> listSelcet(String type) {
		List<BasePortalEntity> list = portalService.getList().stream().filter(t -> "1".equals(String.valueOf(t.getEnabledFlag()))).collect(Collectors.toList());
		List<PortalSelectModel> modelList = JsonUtil.getJsonToList(list, PortalSelectModel.class);

		R<List<SysDictItemEntity>> result = dictionaryDataApi.getDictByType(DicTypeEnum.VISUADEV_PORTAL_DESIGN_CATEGORY.getType());
		for (SysDictItemEntity entity : result.getData()) {
			PortalSelectModel model = new PortalSelectModel();
			model.setId(entity.getValue());
			model.setFullName(entity.getLabel());
			model.setParentId("0");
			if (!modelList.contains(model)) {
				modelList.add(model);
			}
		}

		List<SumTree<PortalSelectModel>> sumTrees = TreeDotUtils.convertListToTreeDot(modelList);
		List<PortalSelectVO> listVO = JsonUtil.getJsonToList(sumTrees, PortalSelectVO.class);
		ListVO<PortalSelectVO> treeVo = new ListVO<>();
		treeVo.setList(listVO);
		return R.ok(treeVo);
	}


	@GetMapping("/{id}")
	public R<PortalInfoVO> info(@PathVariable("id") Long id) {
		BasePortalEntity entity = portalService.getInfo(id);
		PortalInfoVO vo = JsonUtil.getJsonToBean(JsonUtil.getObjectToStringDateFormat(entity, "yyyy-MM-dd HH:mm:ss"), PortalInfoVO.class);

		return R.ok(vo);
	}

	@GetMapping("/{id}/auth")
	public R<PortalInfoAuthVO> infoAuth(@PathVariable("id") Long id) {
		BasePortalEntity entity = portalService.getInfo(id);
		PortalInfoAuthVO vo = JsonUtil.getJsonToBean(JsonUtil.getObjectToStringDateFormat(entity, "yyyy-MM-dd HH:mm:ss"), PortalInfoAuthVO.class);
		return R.ok(vo);
	}


	@DeleteMapping("/{id}")
	@Transactional
	public R<Boolean> delete(@PathVariable("id") Long id) {
		BasePortalEntity entity = portalService.getInfo(id);
		if (entity != null) {
			portalService.delete(entity);
		}
		return R.ok(null, "删除成功");
	}

	@PostMapping()
	@Transactional
	public R<Boolean> create(@RequestBody @Valid PortalCrForm portalCrForm) {
		BasePortalEntity entity = JsonUtil.getJsonToBean(portalCrForm, BasePortalEntity.class);
		portalService.create(entity);
		return R.ok(null, "新建成功");
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
		BasePortalEntity entity = portalService.getInfo(id);
		entity.setId(null);
		entity.setEnabledFlag("0");
		entity.setFullName(entity.getFullName() + "_副本");
		entity.setUpdateTime(null);
		entity.setUpdateBy(null);
		BasePortalEntity entity1 = JsonUtil.getJsonToBean(entity, BasePortalEntity.class);
		portalService.create(entity1);
		return R.ok(null, "新建成功");
	}


	@PutMapping("/{id}")
	@Transactional
	public R<Boolean> update(@PathVariable("id") Long id, @RequestBody @Valid PortalUpForm portalUpForm) {
		BasePortalEntity entity = JsonUtil.getJsonToBean(portalUpForm, BasePortalEntity.class);
		boolean flag = portalService.update(id, entity);
		if (!flag) {
			return R.failed("更新失败，数据不存在");
		}
		return R.ok(null, "更新成功");

	}

	/**
	 * 门户权限列表
	 *
	 * @param id 对象主键
	 * @return
	 */
	@Transactional
	@ApiOperation("设置默认门户")
	@PutMapping("/{id}/Actions/SetDefault")
	public R<Boolean> SetDefault(@PathVariable("id") Long id) {
		return R.ok(null, "设置成功");
	}

}
