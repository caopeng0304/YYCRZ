package com.sinosoft.ie.booster.common.core.util.treeutil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author booster开发平台组
 * @since 2021/3/16 10:51
 */
public class ListToTreeUtil {

	/**
	 * 递归
	 *
	 * @param data
	 * @param parentId
	 */
	private static List<TreeViewModel> getChildNodeList(List<TreeViewModel> data, String parentId) {
		List<TreeViewModel> treeList = new ArrayList<>();
		List<TreeViewModel> childNodeList = data.stream().filter(t -> String.valueOf(t.getParentId()).equals(parentId)).collect(Collectors.toList());
		for (TreeViewModel entity : childNodeList) {
			TreeViewModel model = new TreeViewModel();
			model.setId(entity.getId());
			model.setText(entity.getText());
			model.setParentId(entity.getParentId());
			model.setIsexpand(entity.getIsexpand());
			model.setComplete(entity.getComplete());
			model.setHasChildren(entity.getHasChildren() == null && (data.stream().anyMatch(t -> String.valueOf(t.getParentId()).equals(String.valueOf(entity.getId())))));
			if (entity.getShowcheck()) {
				model.setCheckstate(entity.getCheckstate());
				model.setShowcheck(true);
			}
			if (entity.getImg() != null) {
				model.setImg(entity.getImg());
			}
			if (entity.getCssClass() != null) {
				model.setCssClass(entity.getCssClass());
			}
			if (entity.getClick() != null) {
				model.setClick(entity.getClick());
			}
			if (entity.getCode() != null) {
				model.setCode(entity.getCode());
			}
			if (entity.getTitle() != null) {
				model.setTitle(entity.getTitle());
			}
			if (entity.getHt() != null) {
				model.setHt(entity.getHt());
			}
			model.setChildNodes(getChildNodeList(data, entity.getId()));
			treeList.add(model);
		}
		return treeList;
	}


	/**
	 * 递归查询父节点
	 *
	 * @param data     条件的的数据
	 * @param dataAll  所有的数据
	 * @param id       id
	 * @param parentId parentId
	 * @param <T>
	 * @return
	 */
	public static <T> JSONArray treeWhere(List<T> data, List<T> dataAll, String id, String parentId) {
		JSONArray datas = JSONArray.parseArray(JSON.toJSONString(data));
		JSONArray result = new JSONArray();
		if (datas.size() == dataAll.size()) {
			return datas;
		}
		for (Object o : datas) {
			JSONObject json = (JSONObject) o;
			if (result.stream().noneMatch(t -> t.equals(json))) {
				result.add(json);
			}
			if (!"-1".equals(json.getString(parentId))) {
				ListToTreeUtil.result(dataAll, json, result, id, parentId);
			}
		}
		return result;
	}

	/**
	 * 递归查询父节点
	 *
	 * @param data    条件的的数据
	 * @param dataAll 所有的数据
	 * @param <T>
	 * @return
	 */
	public static <T> JSONArray treeWhere(List<T> data, List<T> dataAll) {
		String id = "id";
		String parentId = "parentId";
		JSONArray datas = JSONArray.parseArray(JSON.toJSONString(data));
		JSONArray result = new JSONArray();
		if (datas.size() == dataAll.size()) {
			return datas;
		}
		for (Object o : datas) {
			JSONObject json = (JSONObject) o;
			if (result.stream().noneMatch(t -> t.equals(json))) {
				result.add(json);
			}
			if (!"-1".equals(json.getString(parentId))) {
				ListToTreeUtil.result(dataAll, json, result, id, parentId);
			}
		}
		return result;
	}

	/**
	 * 递归查询父节点
	 *
	 * @param dataAll  所有数据
	 * @param json     当前对象
	 * @param result   结果数据
	 * @param id       id
	 * @param parentId parentId
	 * @param <T>
	 * @return
	 */
	private static <T> JSONArray result(List<T> dataAll, JSONObject json, JSONArray result, String id, String parentId) {
		JSONArray dataAlls = JSONArray.parseArray(JSON.toJSONString(dataAll));
		for (Object all : dataAlls) {
			JSONObject aVal = (JSONObject) all;
			String ids = aVal.getString(id);
			String parentIds = aVal.getString(parentId);
			if (json.getString(parentId).equals(ids)) {
				if (result.stream().noneMatch(t -> t.equals(aVal))) {
					result.add(aVal);
				}
				if ("-1".equals(parentIds)) {
					break;
				}
				ListToTreeUtil.result(dataAll, aVal, result, id, parentId);
			}
		}
		return result;
	}

	/**
	 * 递归查询子节点
	 *
	 * @param data     所有的数据
	 * @param id       id
	 * @param parentId parentId
	 * @param fid      查询的父亲节点
	 * @param <T>
	 * @return
	 */
	public static <T> JSONArray treeWhere(String fid, List<T> data, String id, String parentId) {
		JSONArray datas = JSONArray.parseArray(JSON.toJSONString(data));
		JSONArray result = new JSONArray();
		for (Object o : datas) {
			JSONObject json = (JSONObject) o;
			String fId = json.getString(id);
			String fParentId = json.getString(parentId);
			if (fid.equals(fParentId)) {
				result.add(json);
				ListToTreeUtil.result(fId, data, result, id, parentId);
			}
		}
		return result;
	}

	/**
	 * 递归查询子节点
	 *
	 * @param data 所有的数据
	 * @param fid  查询的父亲节点
	 * @param <T>
	 * @return
	 */
	public static <T> JSONArray treeWhere(String fid, List<T> data) {
		String id = "id";
		String parentId = "parentId";
		JSONArray datas = JSONArray.parseArray(JSON.toJSONString(data));
		JSONArray result = new JSONArray();
		for (Object o : datas) {
			JSONObject json = (JSONObject) o;
			String fId = json.getString(id);
			String fParentId = json.getString(parentId);
			if (fid.equals(fParentId)) {
				result.add(json);
				ListToTreeUtil.result(fId, data, result, id, parentId);
			}
		}
		return result;
	}

	/**
	 * 递归查询子节点
	 *
	 * @param data     所有的数据
	 * @param id       F_Id
	 * @param parentId F_ParentId
	 * @param fid      查询的父亲节点
	 * @param <T>
	 * @return
	 */
	public static <T> JSONArray result(String fid, List<T> data, JSONArray result, String id, String parentId) {
		JSONArray dataAll = JSONArray.parseArray(JSON.toJSONString(data));
		for (Object o : dataAll) {
			JSONObject aVal = (JSONObject) o;
			String fId = aVal.getString(id);
			String fParentId = aVal.getString(parentId);
			if (fid.equals(fParentId)) {
				result.add(aVal);
				ListToTreeUtil.result(fId, data, result, id, parentId);
			}
		}
		return result;
	}
}
