package com.sinosoft.ie.booster.visualdev.controller;

import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.Pagination;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.visualdev.model.dbtable.*;
import com.sinosoft.ie.booster.visualdev.service.BaseDbTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据建模
 *
 * @author booster开发平台组
 * @since 2021年9月27日 上午9:18
 */
@Api(tags = "数据建模", value = "DataModel")
@RestController
@RequestMapping("/Base/DataModel")
public class DataModelController {

	@Autowired
	private BaseDbTableService dbTableService;

	/**
	 * 列表
	 *
	 * @param id   连接id
	 * @param page 关键词
	 * @return
	 */
	@ApiOperation("获取数据库表列表")
	@GetMapping("/{id}/Tables")
	public R<ListVO<DbTableModel>> getList(@PathVariable("id") Long id, Pagination page) {
		List<DbTableModel> data = dbTableService.getList(id).stream().filter(
				t -> !StrUtil.isEmpty(page.getKeyword()) ? t.getDescription().toLowerCase().contains(page.getKeyword().toLowerCase())
						|| t.getTable().toLowerCase().contains(page.getKeyword().toLowerCase()) : t.getTable() != null
		).sorted(Comparator.comparing(DbTableModel::getTable)).collect(Collectors.toList());
		ListVO<DbTableModel> vo = new ListVO<>();
		vo.setList(data);
		return R.ok(vo);
	}

	/**
	 * 预览数据库表
	 *
	 * @param dbTableDataForm 查询条件
	 * @param dbId            连接Id
	 * @param tableName       表名
	 * @return
	 */
	@ApiOperation("预览数据库表")
	@GetMapping("/{DBId}/Table/{tableName}/Preview")
	public R<PageListVO<Map<String, Object>>> data(DbTableDataForm dbTableDataForm, @PathVariable("DBId") Long dbId, @PathVariable("tableName") String tableName) throws DataException, SQLException {
		List<Map<String, Object>> data = dbTableService.getData(dbTableDataForm, dbId, tableName);
		PaginationVO paginationVO = JsonUtil.getJsonToBeanEx(dbTableDataForm, PaginationVO.class);
		PageListVO<Map<String, Object>> vo = new PageListVO<>();
		vo.setList(data);
		vo.setPagination(paginationVO);
		return R.ok(vo);
	}

	/**
	 * 列表
	 *
	 * @return
	 */
	@GetMapping("/{DBId}/Tables/{tableName}/Fields/Selector")
	@ApiOperation("获取数据库表字段下拉框列表")
	public R<ListVO<DbTableFieldSeleVO>> selectorList(@PathVariable("DBId") Long dbId, @PathVariable("tableName") String tableName) {
		List<DbTableFieldModel> data = dbTableService.getFieldList(dbId, tableName);
		List<DbTableFieldSeleVO> vos = JsonUtil.getJsonToList(data, DbTableFieldSeleVO.class);
		ListVO<DbTableFieldSeleVO> vo = new ListVO<>();
		vo.setList(vos);
		return R.ok(vo);
	}

	/**
	 * 字段列表
	 *
	 * @param dbId      连接Id
	 * @param tableName 表名
	 * @return
	 */
	@ApiOperation("获取数据库表字段列表")
	@GetMapping("/{DBId}/Tables/{tableName}/Fields")
	public R<ListVO<DbTableFieldVO>> fieldList(@PathVariable("DBId") Long dbId, @PathVariable("tableName") String tableName) {
		List<DbTableFieldModel> data = dbTableService.getFieldList(dbId, tableName);
		List<DbTableFieldVO> vos = JsonUtil.getJsonToList(data, DbTableFieldVO.class);
		for (DbTableFieldVO vo : vos) {
			vo.setField(vo.getField().toLowerCase());
		}
		ListVO<DbTableFieldVO> vo = new ListVO<>();
		vo.setList(vos);
		return R.ok(vo);
	}

	/**
	 * 信息
	 *
	 * @param dbId      连接Id
	 * @param tableName 表名
	 * @return
	 */
	@ApiOperation("获取数据表")
	@GetMapping("/{DBId}/Table/{tableName}")
	public R<DbTableVO> get(@PathVariable("DBId") Long dbId, @PathVariable("tableName") String tableName) throws DataException, SQLException {
		DbTableModel dbTableModel = dbTableService.getList(dbId).stream().filter(m -> m.getTable().equals(tableName)).findFirst().get();
		//转换
		DbTableInfoVO tableInfo = JsonUtil.getJsonToBeanEx(dbTableModel, DbTableInfoVO.class);
		List<DbTableFieldModel> tableFieldList = dbTableService.getFieldList(dbId, tableName);
		List<DbTableFieldVO> fieldList = JsonUtil.getJsonToList(tableFieldList, DbTableFieldVO.class);
		DbTableVO vo = DbTableVO.builder().tableFieldList(fieldList).tableInfo(tableInfo).build();
		return R.ok(vo);
	}

	/**
	 * 删除
	 *
	 * @param dbId      连接Id
	 * @param tableName 连接Id
	 * @return
	 */
	@ApiOperation("删除")
	@DeleteMapping("/{DBId}/Table/{tableName}")
	public R<Boolean> delete(@PathVariable("DBId") Long dbId, @PathVariable("tableName") String tableName) throws DataException, SQLException {
		List<Map<String, Object>> dataList = dbTableService.getData(new DbTableDataForm(), dbId, tableName);
		if (dataList.isEmpty()) {
			String byoTable = "base_common_fields,base_data_interface,base_db_link,base_im_content,base_message,base_message_receive,base_portal,base_visual_data,base_visual_data_map,base_visual_dev,base_visual_dev_model_data,ext_email_config,ext_email_receive,ext_email_send";
			boolean exists = byoTable.contains(tableName.toLowerCase());
			if (exists) {
				return R.ok(null, "系统自带表,不允许被删除");
			}
			dbTableService.delete(dbId, tableName);
			return R.ok(null, "删除成功");
		} else {
			return R.failed("表已经被使用,不允许被删除");
		}
	}

	/**
	 * 新建
	 *
	 * @param dbId 连接Id
	 * @return
	 */
	@ApiOperation("新建")
	@PostMapping("{DBId}/Table")
	public R<Boolean> create(@PathVariable("DBId") Long dbId, @RequestBody @Valid DbTableCrForm dbTableCrForm) throws DataException, SQLException {
		DbTableModel dbTableModel = JsonUtil.getJsonToBean(dbTableCrForm.getTableInfo(), DbTableModel.class);
		List<DbTableFieldModel> list = JsonUtil.getJsonToList(dbTableCrForm.getTableFieldList(), DbTableFieldModel.class);
		if (dbTableService.isExistByFullName(dbId, dbTableCrForm.getTableInfo().getTable(), dbTableModel.getId())) {
			return R.failed("表名称不能重复");
		}
		return dbTableService.create(dbId, dbTableModel, list);
	}

	/**
	 * 更新
	 *
	 * @param dbId 连接Id
	 * @return
	 */
	@ApiOperation("更新")
	@PutMapping("/{DBId}/Table")
	public R<Boolean> update(@PathVariable("DBId") Long dbId, @RequestBody @Valid DbTableUpForm dbTableUpForm) throws DataException, SQLException {
		DbTableModel dbTableModel = JsonUtil.getJsonToBean(dbTableUpForm.getTableInfo(), DbTableModel.class);
		List<DbTableFieldModel> list = JsonUtil.getJsonToList(dbTableUpForm.getTableFieldList(), DbTableFieldModel.class);
		if (!dbTableUpForm.getTableInfo().getNewTable().equals(dbTableUpForm.getTableInfo().getTable())) {
			if (dbTableService.isExistByFullName(dbId, dbTableUpForm.getTableInfo().getNewTable(), dbTableUpForm.getTableInfo().getTable())) {
				return R.failed("表名称不能重复");
			}
		}

		String oldTable = dbTableModel.getTable();
		List<Map<String, Object>> dataList = dbTableService.getData(new DbTableDataForm(), dbId, oldTable);
		if (dataList.isEmpty()) {
			String byoTable = "base_common_fields,base_data_interface,base_db_link,base_im_content,base_message,base_message_receive,base_portal,base_visual_data,base_visual_data_map,base_visual_dev,base_visual_dev_model_data,ext_email_config,ext_email_receive,ext_email_send";
			String[] tables = byoTable.split(",");
			boolean exists;
			for (String table : tables) {
				exists = dbTableUpForm.getTableInfo().getNewTable().toLowerCase().equals(table);
				if (exists) {
					return R.failed("系统自带表,不允许被修改");
				}
			}
			dbTableService.update(dbId, dbTableModel, list);
			return R.ok(null, "修改成功");
		} else {
			return R.failed("表已经被使用,不允许被编辑");
		}
	}
}
