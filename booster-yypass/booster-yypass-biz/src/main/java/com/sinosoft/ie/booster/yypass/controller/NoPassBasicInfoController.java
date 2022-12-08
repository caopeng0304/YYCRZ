package com.sinosoft.ie.booster.yypass.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.NoPassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;
import com.sinosoft.ie.booster.yypass.model.nopassbasicinfo.NoPassBasicInfoVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.NoPassBasicInfoCrForm;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.NoPassBasicInfoPagination;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoUpForm;
import com.sinosoft.ie.booster.yypass.service.NoPassBasicInfoService;
import com.sinosoft.ie.booster.yypass.service.PassFileService;
import com.sinosoft.ie.booster.yypass.service.PassLogService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * aps_system
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-08-08 14:06:39
 */
@Slf4j
@RestController
@Api(tags = "no_pass_basic_info")
@RequestMapping("/NoPassBasicInfo")
public class NoPassBasicInfoController {

	@Autowired
	NoPassBasicInfoService nopassBasicInfoService;
	@Autowired
	PassLogService passLogService;
	@Autowired
	PassFileService passFileService;

	/**
	 * 创建
	 *
	 * @param apsSystemCrForm
	 * @return
	 */
	@PostMapping
	@Transactional
	public R create(@RequestBody @Valid NoPassBasicInfoCrForm apsSystemCrForm) throws DataException {
		String uuid = RandomUtil.uuId();
		BoosterUser userInfo= SecurityUtils.getUser();
		NoPassBasicInfoVO entity= JsonUtil.getJsonToBean(apsSystemCrForm, NoPassBasicInfoVO.class);

		List<String> files1 = entity.getFiles1();
		List<String> files2 = entity.getFiles2();
		// 添加表 pass_file
		if (files1 != null && files1.size() > 0){
           for (String s:files1){
			   PassFileEntity fileEntity = new PassFileEntity();
			   fileEntity.setId(RandomUtil.uuId());
			   fileEntity.setFileId(s);
			   fileEntity.setPassBasicInfoId(uuid);
			   fileEntity.setFileType("A");
			   this.passFileService.create(fileEntity);
		   }
		}

		if (files2 != null && files2.size() > 0){
			for (String s:files2){
				PassFileEntity fileEntity = new PassFileEntity();
				fileEntity.setId(RandomUtil.uuId());
				fileEntity.setFileId(s);
				fileEntity.setPassBasicInfoId(uuid);
				fileEntity.setFileType("B");
				this.passFileService.create(fileEntity);
			}
		}



		NoPassBasicInfoEntity vo = JsonUtil.getJsonToBean(entity, NoPassBasicInfoEntity.class);
		vo.setIsDelete("1");
		nopassBasicInfoService.create(vo);


		return R.ok(null, "新建成功");
	}


	/**
	 * 信息
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public R<NoPassBasicInfoVO> info(@PathVariable("id") String id){
		NoPassBasicInfoEntity entity= nopassBasicInfoService.getInfo(id);
		NoPassBasicInfoVO vo=JsonUtil.getJsonToBean(entity, NoPassBasicInfoVO.class);
		// 获取附件
		PassFileEntity passFileEntity = new PassFileEntity();
		passFileEntity.setPassBasicInfoId(id+"");
		passFileEntity.setFileType("A");
		List<FileEntity> file1 = this.passFileService.getFileList(passFileEntity);
		vo.setFile1(file1);

		passFileEntity.setFileType("B");
		List<FileEntity> file2 = this.passFileService.getFileList(passFileEntity);
		vo.setFile2(file2);

		return R.ok(vo);
	}

	/**
	 * 更新
	 * @return
	 */
	@PostMapping("/update")
	@Transactional
	public R update(@RequestBody @Valid NoPassBasicInfoCrForm apsSystemCrForm) throws DataException {
		NoPassBasicInfoEntity entity= nopassBasicInfoService.getInfo(apsSystemCrForm.getId());
		if(entity!=null){
			//BoosterUser userInfo = SecurityUtils.getUser();
			entity=JsonUtil.getJsonToBean(apsSystemCrForm, NoPassBasicInfoEntity.class);
			nopassBasicInfoService.update(apsSystemCrForm.getId(), entity);
			return R.ok(null, "更新成功");
		}else{
			return R.failed("更新失败，数据不存在");
		}
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	@Transactional
	public R delete(@PathVariable("id") String id){
		try {
			if (StringUtils.isNotEmpty(id)){
				String[] ids = id.split(",");
				for (String s :ids){
					NoPassBasicInfoEntity entity= nopassBasicInfoService.getInfo(s);
					entity.setIsDelete("0");
					nopassBasicInfoService.update(s, entity);
				}
			}
			return R.ok(null, "删除成功");
		}catch (Exception e){
			return R.failed(null, "删除失败");
		}
	}

	/**
	 * 请求参数为json格式
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getbody")
	public R<IPage<NoPassBasicInfoEntity>> selectParmer(@RequestBody @Valid NoPassBasicInfoPagination passBasicInfoPagination) throws Exception {
		NoPassBasicInfoPagination entity=JsonUtil.getJsonToBean(passBasicInfoPagination, NoPassBasicInfoPagination.class);
		IPage<NoPassBasicInfoEntity> apsSystemEntities = nopassBasicInfoService.selectParmer(entity);
		return R.ok(apsSystemEntities);
	}

}
