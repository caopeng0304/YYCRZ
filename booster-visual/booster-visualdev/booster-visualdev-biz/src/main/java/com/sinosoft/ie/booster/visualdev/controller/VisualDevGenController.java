package com.sinosoft.ie.booster.visualdev.controller;


import cn.hutool.core.util.StrUtil;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.model.DownloadVO;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.model.base.DownloadCodeForm;
import com.sinosoft.ie.booster.visualdev.service.VisualDevGenService;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.sinosoft.ie.booster.visualdev.util.base.genUtil.ReadFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 可视化开发功能表
 *
 * @author booster开发平台组
 * @since 2021-04-02
 */
@Api(tags = "代码生成器")
@RestController
@RequestMapping("/Generater" )
public class VisualDevGenController {

	@Autowired
	private ConfigValueUtil configValueUtil;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private VisualDevGenService visualDevGenService;


	/**
	 * 下载文件
	 *
	 * @return
	 */
	@ApiOperation("下载文件" )
	@GetMapping("/DownloadVisCode" )
	public void downloadCode() throws DataException {
		HttpServletRequest request = ServletUtil.getRequest();
		assert request != null;
		String reqJson = request.getParameter("encryption" );
		String fileNameAll = DesUtil.aesDecode(reqJson);
		if (!StrUtil.isEmpty(fileNameAll)) {
			String fileName = fileNameAll.split("#" )[1];
			String path = configValueUtil.getCodeTempPath() + fileName;
			if (FileUtil.fileIsExists(path)) {
				String zipFile = configValueUtil.getTemporaryFilePath() + fileName + ".zip";
				// 调用压缩方法
				FileUtil.toZip(zipFile, true, path);
				DownUtil.dowloadFile(zipFile, fileName + ".zip" );
			}

		} else {
			throw new DataException("文件不存在" );
		}
	}

	@ApiOperation("下载代码" )
	@PostMapping("/{id}/Actions/DownloadCode" )
	@Transactional
	public R<DownloadVO> downloadCode(@PathVariable("id" ) Long id, @RequestBody @Valid DownloadCodeForm downloadCodeForm) throws SQLException {
		BoosterUser userInfo = SecurityUtils.getUser();
		DownloadVO vo;
		String fileName = visualDevGenService.codeGenerate(id, downloadCodeForm);
		vo = DownloadVO.builder().name(fileName).url(UploaderUtil.uploaderVisualFile(userInfo.getId() + "#" + fileName)).build();
		if (vo == null) {
			return R.failed("下载失败，数据不存在" );
		}
		return R.ok(vo);
	}


	/**
	 * 输出移动开发模板
	 *
	 * @return
	 */
	@ApiOperation("预览代码" )
	@PostMapping("/{id}/Actions/CodePreview" )
	public R<ListVO<Map<String, Object>>> codePreview(@PathVariable("id" ) Long id, @RequestBody @Valid DownloadCodeForm downloadCodeForm) throws SQLException {
		String fileName = visualDevGenService.codeGenerate(id, downloadCodeForm);
		List<Map<String, Object>> dataList = ReadFile.priviewCode(configValueUtil.getCodeTempPath() + fileName);
		if (dataList == null && dataList.size() == 0) {
			return R.failed("预览失败，数据不存在" );
		}
		ListVO<Map<String, Object>> datas = new ListVO<>();
		datas.setList(dataList);
		return R.ok(datas);
	}

	/**
	 * App预览(后台APP表单设计)
	 *
	 * @return
	 */
	@ApiOperation("App预览(后台APP表单设计)" )
	@PostMapping("/App/Preview" )
	public R<String> appPreview(String data) {
		String id = RandomUtil.uuId();
		redisUtil.insert(id, data, 300);
		return R.ok(id);
	}

	/**
	 * App预览(后台APP表单设计)
	 *
	 * @return
	 */
	@ApiOperation("App预览查看" )
	@GetMapping("/App/{id}/Preview" )
	public R<Object> preview(@PathVariable("id" ) String id) {
		if (redisUtil.exists(id)) {
			Object object = redisUtil.getString(id);
			return R.ok(object);
		} else {
			return R.failed("已失效" );
		}
	}

}


