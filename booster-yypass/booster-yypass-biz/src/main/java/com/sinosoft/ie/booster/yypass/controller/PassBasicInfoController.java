package com.sinosoft.ie.booster.yypass.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.sinosoft.ie.booster.admin.api.entity.SysUserEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserDTO;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.admin.api.model.UserVO;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.Base64Util;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.yypass.entity.*;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.*;
import com.sinosoft.ie.booster.yypass.service.*;
import com.sinosoft.ie.booster.yypass.util.SmsSendAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.beans.binding.LongExpression;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.pig4cloud.plugin.excel.annotation.RequestExcel;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.vo.ErrorMessage;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Error;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import net.sf.json.JSONObject;

/**
 *
 * aps_system
 * @????????? V1.0.0
 * @????????? booster???????????????
 * @????????? 2022-08-08 14:06:39
 */
@Slf4j
@RestController
@Api(tags = "pass_basic_info")
@RequestMapping("/PassBasicInfo")
public class PassBasicInfoController {

	@Autowired
	PassBasicInfoService passBasicInfoService;
	@Autowired
	PassLogService passLogService;
	@Autowired
	PassDelayService passDelayService;
	@Autowired
	PassFileService passFileService;
	@Autowired
	RemoteUserService remoteUserService;
	@Autowired
	NoPassBasicInfoService noPassBasicInfoService;
	@Autowired
	FileService fileService;
	@Autowired
	SendMsgLogService sendMsgLogService;
	@Autowired
	PassLogNoPowerService passLogNoPowerService;
	@Autowired
	PassBasicInfoStatisticsService passBasicInfoStatisticsService;


	@Value("${roles}")
	private String roles;
	@Value("${deptId}")
	private String deptId;
	@Value("${manager}")
	private String manager;
	@Value("${sms_url}")
	private String SMS_URL;
	@Value("${sms_username}")
	private String SMS_USERNAME;
	@Value("${sms_password}")
	private String SMS_PASSWORD;
	@Value("${sms_epid}")
	private String SMS_EPID;
	@Value("${is_sms}")
	private String IS_SMS;

	@Value("${syn_person_add_url}")
	private String syn_person_add_url;
	@Value("${empty_png_base}")
	private String empty_png_base;

	@Value("${queryByUserGrooupCc}")
	private String queryByUserGrooupCc;
	@Value("${deleteByUserGrooup}")
	private String deleteByUserGrooup;
	@Value("${queryGroupId}")
	private String queryGroupId;
	@Value("${addPerson}")
	private String addPerson;
	@Value("${queryPersonId}")
	private String queryPersonId;
	@Value("${Config_host}")
	private String Config_host;
	@Value("${Config_appKey}")
	private String Config_appKey;
	@Value("${Config_appSecret}")
	private String Config_appSecret;
	@Value("${updateface}")
	private String updateface;
	@Value("${addface}")
	private String addface;
	@Value("${updatePerson}")
	private String updatePerson;
	@Value("${deleteface}")
	private String deleteface;
	@Value("${deletePerson}")
	private String deletePerson;
	@Value("${groupName}")
	private String groupName;

	/**
	 * ??????
	 *
	 * @param apsSystemCrForm
	 * @return
	 */
	@PostMapping
	@Transactional
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	public R create(@RequestBody @Valid PassBasicInfoCrForm apsSystemCrForm) throws DataException {
		BoosterUser userInfo= SecurityUtils.getUser();
		PassBasicInfoVO entity= JsonUtil.getJsonToBean(apsSystemCrForm, PassBasicInfoVO.class);
		String uuid = RandomUtil.uuId();
		PassBasicInfoEntity vo = JsonUtil.getJsonToBean(entity, PassBasicInfoEntity.class);
		vo.setId(uuid);
		vo.setIsDelete("1");
		// ????????????
		NoPassBasicInfoEntity no_entity = new NoPassBasicInfoEntity();
		no_entity.setCardNumber(vo.getCardNumber());
		List<NoPassBasicInfoEntity> list = noPassBasicInfoService.getList(no_entity);
		if (list != null && list.size()> 0){
			return R.failed(null, vo.getCardNumber()+",?????????????????????????????????????????????");
		}

		List<String> files1 = entity.getFiles1();
		List<String> files2 = entity.getFiles2();
		// ????????? pass_file
		if (files1 != null && files1.size() > 0){
           for (String s:files1){
			   PassFileEntity fileEntity = new PassFileEntity();
			   fileEntity.setId(RandomUtil.uuId());
			   fileEntity.setFileId(s);
			   fileEntity.setPassBasicInfoId(uuid);
			   fileEntity.setFileType("A");
			   fileEntity.setIsDelete("1");
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
				fileEntity.setIsDelete("1");
				this.passFileService.create(fileEntity);
			}
		}
        //  ???????????????SNO
		Long sno = passBasicInfoService.getSNo();
		if (sno == null){
			sno = 1L;
		}
		vo.setSNo(sno+1);
		vo.setAddPersonId(userInfo.getId()+"");
		vo.setAddPersonName(userInfo.getUsername());
		vo.setAddTime(new Date());
		vo.setIsGrantPass("1");
		vo.setOldStartTime(vo.getStartTime());
		vo.setOldEndTime(vo.getEndTime());
		passBasicInfoService.create(vo);




		PassLogEntity passLogEntity = new PassLogEntity();
		passLogEntity.setId(RandomUtil.uuId());
		passLogEntity.setPassBasicInfoId(uuid);
		passLogEntity.setState(vo.getPersonState());
		passLogEntity.setPersonId(userInfo.getId()+"");
		passLogEntity.setUnitId(userInfo.getDeptId()+"");
		// ??????
		R<UserInfoModel> r = remoteUserService.info(userInfo.getUsername(),"Y");
		UserInfoModel vo2 = r.getData();
		if (vo2 != null){
			passLogEntity.setPersonName(vo2.getSysUser().getManager());
		}else{
			passLogEntity.setPersonName(userInfo.getUsername());
		}
		passLogEntity.setAddTime(new Date());
		passLogEntity.setDescs("");
		passLogEntity.setIsDelete("1");
		passLogService.create(passLogEntity);

		return R.ok(null, "????????????");
	}


	/**
	 * ??????
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	public R<PassBasicInfoVO> info(@PathVariable("id") String id){
		PassBasicInfoEntity entity= passBasicInfoService.getInfo(id);
		PassBasicInfoVO vo=JsonUtil.getJsonToBean(entity, PassBasicInfoVO.class);
		// ????????????
		PassFileEntity passFileEntity = new PassFileEntity();
		passFileEntity.setPassBasicInfoId(id);
		passFileEntity.setFileType("A");
		List<FileEntity> file1 = this.passFileService.getFileList(passFileEntity);
		if (file1 != null && file1.size() > 0){
			for (FileEntity fe:file1){
				String url = fileService.getURL(fe.getFileName());
				fe.setUrl(url);
			}
		}
		vo.setFile1(file1);

		passFileEntity.setFileType("B");
		List<FileEntity> file2 = this.passFileService.getFileList(passFileEntity);
		if (file2 != null && file2.size() > 0){
			for (FileEntity fe:file2){
				String url = fileService.getURL(fe.getFileName());
				fe.setUrl(url);
			}
		}

		vo.setFile2(file2);

		// ??????????????????
		PassLogEntity logEntity = new PassLogEntity();
		logEntity.setPassBasicInfoId(id);
        List<PassLogEntity> list = passLogService.getList(logEntity);
        vo.setList(list);

		return R.ok(vo);
	}




	@PostMapping("/updateFile")
	@Transactional
	@ApiOperation(value = "????????????????????????????????????", notes = "????????????????????????????????????")
	public R updateFile(@RequestBody @Valid PassBasicInfoUpForm apsSystemUpForm) throws DataException {
       try{
		   PassBasicInfoEntity entity2= passBasicInfoService.getInfo(apsSystemUpForm.getId());
		   BoosterUser userInfo= SecurityUtils.getUser();
		   PassBasicInfoVO entity= JsonUtil.getJsonToBean(apsSystemUpForm, PassBasicInfoVO.class);
		   PassBasicInfoEntity vo = JsonUtil.getJsonToBean(entity, PassBasicInfoEntity.class);

		   List<String> files1 = entity.getFiles1();
		   List<String> files2 = entity.getFiles2();
		   // ????????? pass_file
		   if (files1 != null && files1.size() > 0){
			   // ???????????????
			   this.passFileService.logicDelete(apsSystemUpForm.getId(),"A");
			   for (String s:files1){
				   PassFileEntity fileEntity = new PassFileEntity();
				   fileEntity.setId(RandomUtil.uuId());
				   fileEntity.setFileId(s);
				   fileEntity.setPassBasicInfoId(apsSystemUpForm.getId());
				   fileEntity.setFileType("A");
				   fileEntity.setIsDelete("1");
				   this.passFileService.create(fileEntity);
			   }
		   }else{
			   // ???????????????
			   this.passFileService.logicDelete(apsSystemUpForm.getId(),"A");
		   }

		   if (files2 != null && files2.size() > 0){
			   // ???????????????
			   this.passFileService.logicDelete(apsSystemUpForm.getId(),"B");
			   for (String s:files2){
				   PassFileEntity fileEntity = new PassFileEntity();
				   fileEntity.setId(RandomUtil.uuId());
				   fileEntity.setFileId(s);
				   fileEntity.setPassBasicInfoId(apsSystemUpForm.getId());
				   fileEntity.setFileType("B");
				   fileEntity.setIsDelete("1");
				   this.passFileService.create(fileEntity);
			   }
		   }else{
			   // ???????????????
			   this.passFileService.logicDelete(apsSystemUpForm.getId(),"B");
		   }
		   // ????????????
		   PassLogEntity passLogEntity = new PassLogEntity();
		   passLogEntity.setId(RandomUtil.uuId());
		   passLogEntity.setPassBasicInfoId(apsSystemUpForm.getId());
		   passLogEntity.setState(vo.getPersonState());
		   passLogEntity.setPersonId(userInfo.getId()+"");
// ??????
		   R<UserInfoModel> r = remoteUserService.info(userInfo.getUsername(),"Y");
		   UserInfoModel vo2 = r.getData();
		   if (vo2 != null){
			   passLogEntity.setPersonName(vo2.getSysUser().getManager());
		   }else{
			   passLogEntity.setPersonName(userInfo.getUsername());
		   }
		   passLogEntity.setAddTime(new Date());
		   passLogEntity.setDescs("");
		   passLogEntity.setIsDelete("1");
		   passLogService.create(passLogEntity);
		   vo.setOldEndTime(vo.getEndTime());
		   vo.setOldStartTime(vo.getStartTime());
		   passBasicInfoService.update(apsSystemUpForm.getId(), vo);
		   return R.ok(null, "????????????");
	   }catch (Exception e){
       	    System.out.println(e);
		   return R.failed(null, "????????????");
	   }

	}


	@PostMapping("/updateToDoorpost")
	@Transactional
	@ApiOperation(value = "????????????????????????????????????????????????", notes = "????????????????????????????????????????????????")
	public R updateToDoorpost(@RequestBody @Valid PassBasicInfoUpForm apsSystemUpForm) throws DataException {
		  try {
			  PassBasicInfoEntity synEntity = passBasicInfoService.getInfo(apsSystemUpForm.getId());
			  if (StringUtils.isNotEmpty(synEntity.getDoorPersonId())) {  // ??????????????????????????????
				  // ????????????
				  if ("0".equals(apsSystemUpForm.getIsInout())) {  // ?????????
					  personNumberOpen(synEntity);
				  } else if ("1".equals(apsSystemUpForm.getIsInout())){  // ?????????
					  personNumberClose(synEntity);
				  }
			  }
			  BoosterUser userInfo = SecurityUtils.getUser();
			  PassBasicInfoEntity entity = JsonUtil.getJsonToBean(apsSystemUpForm, PassBasicInfoEntity.class);
			  passBasicInfoService.update(apsSystemUpForm.getId(), entity);
			  saveLog(entity);
			  // ???????????????????????????
			  return R.ok(null, "????????????");
		  }catch (Exception e){
			  return R.failed(null, "????????????");
		  }
	}



	@PostMapping("/synToDoorpost")
	@Transactional
	@ApiOperation(value = "??????????????????,?????????????????????", notes = "??????????????????,?????????????????????")
	public R synToDoorpost(@RequestBody @Valid PassBasicInfoUpForm apsSystemUpForm) throws DataException {
		try {
			PassBasicInfoEntity synEntity = passBasicInfoService.getInfo(apsSystemUpForm.getId());
			synEntity.setGrantFaceTime(apsSystemUpForm.getGrantFaceTime());
			PassFileEntity passFileEntity = new PassFileEntity();
			passFileEntity.setPassBasicInfoId(synEntity.getId());
			passFileEntity.setFileType("A");
			List<FileEntity> file1 = this.passFileService.getFileList(passFileEntity);

            if ("0".equals(apsSystemUpForm.getIsGrantFace())){  // 0 ????????????
            	if (StringUtils.isNotEmpty(synEntity.getDoorPersonId())){  // ??????
					Boolean bb = updatePassBasicInfoToDoor(synEntity);   // ??????????????????
					if (!bb){
						return R.failed(null, "????????????");
					}
					String encodeString = fileService.encodeBase64String(file1.get(0));
					if (StringUtils.isNotEmpty(synEntity.getDoorFaceId())){
						synEntity.setIsGrantFace("0");

						Boolean b = UpdatePngToDoorpost(synEntity,encodeString);    // ????????????
						if (!b){
							return R.failed(null, "????????????");
						}
					}else{
						synEntity.setIsGrantFace("0");
						Boolean b = SynPngToDoorpost(synEntity,encodeString);  // ????????????
						if (!b){
							return R.failed(null, "????????????");
						}
					}

				}else{
					synEntity.setIsGrantFace("0");
					Boolean msg = SynToDoorpost(synEntity);
					if (!msg){
						return R.failed(null, "????????????");
					}
				}
			}else if ("1".equals(apsSystemUpForm.getIsGrantFace())){  // 1 ???????????????
				if (StringUtils.isNotEmpty(synEntity.getDoorPersonId())){  // ??????
					synEntity.setIsGrantFace("1");
					Boolean b = updatePassBasicInfoToDoor(synEntity);  // ????????????????????????
					if (!b){
						return R.failed(null, "????????????");
					}
				}else{
					synEntity.setIsGrantFace("1");
					Boolean b = addPassBasicInfoToDoor(synEntity);
					if (!b){
						return R.failed(null, "????????????");
					}
				}
				if (StringUtils.isNotEmpty(synEntity.getDoorFaceId())){
					synEntity.setIsGrantFace("1");
					Boolean b = deletePngToDoorpost(synEntity);
					if (!b){
						return R.failed(null, "????????????");
					}
				}
			}
			PassBasicInfoEntity entity = JsonUtil.getJsonToBean(apsSystemUpForm, PassBasicInfoEntity.class);
			saveLog(entity);
			return R.ok(null, "????????????");

		}catch (Exception e){
			return R.failed(null, "????????????");
		}
	}



	/**
	 * ??????
	 *
	 * @param
	 * @return
	 */
	@PostMapping("/update")
	@Transactional
	@ApiOperation(value = "???????????????????????????", notes = "???????????????????????????")
	public R update(@RequestBody @Valid PassBasicInfoUpForm apsSystemUpForm) throws DataException {
		try{
			PassBasicInfoEntity entity= passBasicInfoService.getInfo(apsSystemUpForm.getId());
			if(entity!=null){
				//BoosterUser userInfo = SecurityUtils.getUser();
				entity=JsonUtil.getJsonToBean(apsSystemUpForm, PassBasicInfoEntity.class);
				saveLog(entity);
				passBasicInfoService.update(apsSystemUpForm.getId(), entity);
				return R.ok(null, "????????????");
			}else{
				return R.failed("??????????????????????????????");
			}
		}catch (Exception e){
			return R.failed(null,"????????????");
		}

	}

	@PostMapping("/logout")
	@ApiOperation(value = "??????", notes = "??????")
	public R<List<PassBasicInfoEntity>> logout(@RequestBody @Valid PassBasicInfoLogoutForm passBasicInfoLogoutForm) throws Exception {
		try {
		PassBasicInfoLogoutForm entity=JsonUtil.getJsonToBean(passBasicInfoLogoutForm, PassBasicInfoLogoutForm.class);
		List<String> ids = entity.getIds();
		BoosterUser userInfo = SecurityUtils.getUser();
		// ??????
		String AuditName = "";
		R<UserInfoModel> r = remoteUserService.info(userInfo.getUsername(),"Y");
		UserInfoModel vo = r.getData();
		if (vo != null){
			AuditName = vo.getSysUser().getManager();
		}
		if (ids != null && ids.size() > 0){
			List<PassBasicInfoEntity> list = new ArrayList<>();
			List<PassLogEntity> passLogList = new ArrayList<>();
			List<PassDelayEntity> passDelayList = new ArrayList<>();
			for (String id : ids){
				PassBasicInfoEntity passBasicInfoEntity= passBasicInfoService.getInfo(id);
				//passBasicInfoEntity.setId(id);
				passBasicInfoEntity.setPersonState(entity.getPersonState());
				passBasicInfoEntity.setLogoutTime(new Date());

				list.add(passBasicInfoEntity);

				PassLogEntity passLogEntity = new PassLogEntity();
				passLogEntity.setId(RandomUtil.uuId());
				passLogEntity.setPassBasicInfoId(id+"");
				passLogEntity.setState(entity.getPersonState());
				passLogEntity.setPersonId(userInfo.getId()+"");
				passLogEntity.setPersonName(AuditName);
				passLogEntity.setAddTime(new Date());
				passLogEntity.setDescs(entity.getDesc());
				passLogEntity.setUnitId(userInfo.getDeptId()+"");
				passLogEntity.setIsDelete("1");
				passLogList.add(passLogEntity);
			}
			this.passBasicInfoService.updateBatchById(list);
			this.passLogService.saveBatch(passLogList);
		}
		return R.ok(null, "????????????");
		}catch (Exception e){
			return R.failed(null, "????????????");
		}
	}



	/**
	 * ??????
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	@ApiOperation(value = "???????????????", notes = "???????????????")
	public R delete(@PathVariable("id") String id){
		try {
			if (StringUtils.isNotEmpty(id)){
				List<String> deleteList = new ArrayList<>();
				String[] ids = id.split(",");
				for (String s :ids){
					PassBasicInfoEntity entity= passBasicInfoService.getInfo(s);
					if (StringUtils.isNotEmpty(entity.getDoorPersonId())){
						deleteList.add(entity.getDoorPersonId());
					}
					// ????????????????????????
					if (StringUtils.isNotEmpty(entity.getIdCard())){
						UserDTO userDTO = new UserDTO();
						userDTO.setUsername(entity.getIdCard());
						remoteUserService.syndeleteUser(userDTO);
					}
					entity.setIsDelete("0");
					passBasicInfoService.update(s, entity);
					// ??????????????????
					deletePassLog(s);
				}
				if (deleteList != null && deleteList.size() > 0){   // ??????????????????
                     deleteToDoorpost(deleteList);
				}
			}
			return R.ok(null, "????????????");
		}catch (Exception e){
			return R.failed(null, "????????????");
		}
	}

	public void deletePassLog(String passBasicInfoId){
		QueryWrapper<PassLogEntity> queryWrapper=new QueryWrapper<>();
		queryWrapper.lambda().and(a->a.eq(PassLogEntity::getPassBasicInfoId,passBasicInfoId));
		List<PassLogEntity> list = passLogService.list(queryWrapper);
		if (list != null && list.size() > 0){
			for (PassLogEntity passLogEntity :list){
				passLogEntity.setIsDelete("0");
				passLogService.update(passLogEntity.getId(),passLogEntity);
			}
		}
	}


	@PostMapping("/getEjglyjsToDo")
	@ApiOperation(value = "???????????????-????????????-????????????", notes = "???????????????-????????????-????????????")
	public R<IPage<PassBasicInfoEntity>> getEjglyjsToDo(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		try {
			PassBasicInfoPagination entity=JsonUtil.getJsonToBean(passBasicInfoPagination, PassBasicInfoPagination.class);
			IPage<PassBasicInfoEntity> apsSystemEntities = passBasicInfoService.getEjglyjsToDo(entity);
			return R.ok(apsSystemEntities);
		}catch (Exception e){
			System.out.println(e);
			return R.failed(null,"????????????");
		}
	}

	@PostMapping("/getToDo")
	@ApiOperation(value = "????????????", notes = "????????????")
	public R<IPage<PassBasicInfoEntity>> getToDo(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		try {
			PassBasicInfoPagination entity=JsonUtil.getJsonToBean(passBasicInfoPagination, PassBasicInfoPagination.class);
			IPage<PassBasicInfoEntity> apsSystemEntities = passBasicInfoService.getToDo(entity);
			return R.ok(apsSystemEntities);
		}catch (Exception e){
			System.out.println(e);
			return R.failed(null,"????????????");
		}
	}

	@PostMapping("/getYrffzrList")
	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????")
	public R<IPage<PassBasicInfoEntity>> getYrffzrList(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		try {
			PassBasicInfoPagination entity=JsonUtil.getJsonToBean(passBasicInfoPagination, PassBasicInfoPagination.class);
			IPage<PassBasicInfoEntity> apsSystemEntities = passBasicInfoService.getYrffzrList(entity);
			return R.ok(apsSystemEntities);
		}catch (Exception e){
			System.out.println(e);
			return R.failed(null,"????????????");
		}
	}

	@PostMapping("/getYrffzrDelay")
	@ApiOperation(value = "??????????????????????????????", notes = "??????????????????????????????")
	public R<IPage<PassBasicInfoEntity>> getYrffzrDelay(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		try {
			PassBasicInfoPagination entity=JsonUtil.getJsonToBean(passBasicInfoPagination, PassBasicInfoPagination.class);
			IPage<PassBasicInfoEntity> apsSystemEntities = passBasicInfoService.getYrffzrDelay(entity);
			return R.ok(apsSystemEntities);
		}catch (Exception e){
			System.out.println(e);
			return R.failed(null,"????????????");
		}
	}

	/**
	 * ???????????????json??????
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getbody")
	@ApiOperation(value = "????????????", notes = "????????????")
	public R<IPage<PassBasicInfoEntity>> selectParmer(@RequestBody @Valid PassBasicInfoPagination passBasicInfoPagination) throws Exception {
		try {
			PassBasicInfoPagination entity=JsonUtil.getJsonToBean(passBasicInfoPagination, PassBasicInfoPagination.class);
			IPage<PassBasicInfoEntity> apsSystemEntities = passBasicInfoService.selectParmer(entity);
			return R.ok(apsSystemEntities);
		}catch (Exception e){
			System.out.println(e);
			return R.failed(null,"????????????");
		}
	}

	public void changePower(List<PassBasicInfoEntity> list,PassBasicInfoPagination passBasicInfoPagination){
		BoosterUser userInfo = SecurityUtils.getUser();
		String userId = userInfo.getId()+"";
		for (PassBasicInfoEntity entity :list){
			Date date = new Date();
			QueryWrapper<PassLogEntity> queryWrapper=new QueryWrapper<>();
			queryWrapper.lambda().and(a->a.eq(PassLogEntity::getPassBasicInfoId,entity.getId()));
			List<PassLogEntity> lists = passLogService.list(queryWrapper);
			if (lists != null && lists.size() > 0){
                for (PassLogEntity passLogEntity1 : lists){
                	String personId = passLogEntity1.getPersonId();
					String transferPersonId = passLogEntity1.getTransferPersonId();
					if (userId.equals(personId) || userId.equals(transferPersonId)){
						entity.setIsPower("0");
						break;
					}
				}
			}else{
				/// ?????????
                 entity.setIsPower("0");
			}
		}
	}


	@PostMapping("/approvalEjglyjsBatch")
	@ApiOperation(value = "?????????????????????--??????????????????????????????????????????????????????????????????", notes = "?????????????????????--??????????????????????????????????????????????????????????????????")
	public R<Errorss> approvalEjglyjsBatch(@RequestBody @Valid PassBasicInfoEjglyjsBatchApprovalForm passBasicInfoBatchApprovalForm) throws Exception {
		try {
			PassBasicInfoEjglyjsBatchApprovalForm entity=JsonUtil.getJsonToBean(passBasicInfoBatchApprovalForm, PassBasicInfoEjglyjsBatchApprovalForm.class);
			List<Map<String,String>> delay = entity.getDelay();
			BoosterUser userInfo = SecurityUtils.getUser();
			// ??????
			String AuditName = "";
			R<UserInfoModel> r = remoteUserService.info(userInfo.getUsername(),"Y");
			UserInfoModel vo = r.getData();
			if (vo != null){
				AuditName = vo.getSysUser().getManager();
			}
			if (delay != null && delay.size() > 0){
				List<PassBasicInfoEntity> list = new ArrayList<>();
				List<PassLogEntity> passLogList = new ArrayList<>();
				for (Map<String,String> map :delay){
					String id = map.get("id");
					String personState = map.get("personState");
					PassBasicInfoEntity passBasicInfoEntity= passBasicInfoService.getInfo(id);
					if (personState.equals(passBasicInfoEntity.getPersonState())){
						Errorss f = new Errorss();
						f.setState(false);
						return R.ok(f,"\" "+passBasicInfoEntity.getNames()+" \""+"????????????????????????????????????");
					}else{
						List<String> topState = passLogService.getTopState(passBasicInfoEntity.getId());
						StringBuffer stringBuffer = new StringBuffer();
						stringBuffer.append(personState);
						if (topState != null && topState.size() > 0){
							stringBuffer.append(","+topState.get(0));
						}
						List<String> repeat = passLogService.getRepeatList(stringBuffer.toString());
						if (repeat != null && repeat.size() == 2){
							String a = repeat.get(0);
							String b = repeat.get(1);
							if (a.equals(b)){
								Errorss f = new Errorss();
                                f.setState(false);
								return R.ok(f,"\" "+passBasicInfoEntity.getNames()+" \""+"????????????????????????????????????");
							}
						}
					}

					passBasicInfoEntity.setPersonState(personState);
					passBasicInfoEntity.setUnitName(entity.getUnitName());
					passBasicInfoEntity.setUnitPhone(entity.getUnitPhone());
					passBasicInfoEntity.setUnitId(entity.getUnitId());
					String state = passBasicInfoEntity.getPersonState();
					if ("1".equals(state) || "2".equals(state) || "7".equals(state) || "8".equals(state)
							|| "16".equals(state) || "17".equals(state)
							|| "160".equals(state) || "170".equals(state)
							|| "260".equals(state) || "270".equals(state)){
						if (StringUtils.isNotEmpty(passBasicInfoBatchApprovalForm.getPersonName())){
							passBasicInfoEntity.setAuditName(passBasicInfoBatchApprovalForm.getPersonName());
						}else{
							passBasicInfoEntity.setAuditName(AuditName);
						}
					}

					if ("290".equals(passBasicInfoEntity.getPersonState())){  //  ????????????????????????
						Date delayToTime = passBasicInfoEntity.getDelayToTime();
						if (delayToTime != null){
							passBasicInfoEntity.setEndTime(delayToTime);
							passBasicInfoEntity.setOldEndTime(delayToTime);
							passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
						}
						String userName = passBasicInfoEntity.getIdCard();
						//??????????????????????????????
						R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						//??????????????????????????????
						if(info!=null){
							//??????????????????????????????
							remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						}

					}

					if ("19".equals(passBasicInfoEntity.getPersonState())){  //  ????????????????????????
						Date delayToTime = passBasicInfoEntity.getDelayToTime();
						if (delayToTime != null){
							passBasicInfoEntity.setEndTime(delayToTime);
							passBasicInfoEntity.setOldEndTime(delayToTime);
							passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
						}
						String userName = passBasicInfoEntity.getIdCard();
						//??????????????????????????????
						R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						//??????????????????????????????
						if(info!=null){
							//??????????????????????????????
							remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						}
					}

					if ("4".equals(passBasicInfoEntity.getPersonState())){  //  ??????????????????????????????
						// ?????????????????????????????????
						createIdCard(passBasicInfoEntity);
						sendMsg(passBasicInfoEntity);
						//??????????????????????????????
						syn(passBasicInfoEntity);
					}

					if ("190".equals(passBasicInfoEntity.getPersonState())){  //  ?????????????????????????????????????????????
						// ?????????????????????????????????
						createIdCard(passBasicInfoEntity);
						sendMsg(passBasicInfoEntity);
						//??????????????????????????????
						syn(passBasicInfoEntity);
					}

					if ("10".equals(passBasicInfoEntity.getPersonState())){  //  ??????????????????????????????
						// ?????????????????????????????????
						//createIdCard(passBasicInfoEntity);
						//sendMsg(passBasicInfoEntity);
						//??????????????????????????????
						Date delayToTime = passBasicInfoEntity.getDelayToTime();
						if (delayToTime != null){
							passBasicInfoEntity.setEndTime(delayToTime);
							passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
							passBasicInfoEntity.setOldEndTime(passBasicInfoEntity.getDelayToTime());
						}
						syn(passBasicInfoEntity);
						//           //?????????????????????????????????
						String userName = passBasicInfoEntity.getIdCard();
						//??????????????????????????????
						R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						//??????????????????????????????
						if(info!=null){
							//??????????????????????????????
							remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						}
					}
					list.add(passBasicInfoEntity);

					PassLogEntity passLogEntity = new PassLogEntity();
					passLogEntity.setId(RandomUtil.uuId());
					passLogEntity.setPassBasicInfoId(id+"");
					passLogEntity.setState(personState);
					if (StringUtils.isNotEmpty(passBasicInfoBatchApprovalForm.getPersonId())){
						passLogEntity.setPersonId(passBasicInfoBatchApprovalForm.getPersonId());
					}else{
						passLogEntity.setPersonId(userInfo.getId()+"");
					}
					if (StringUtils.isNotEmpty(passBasicInfoBatchApprovalForm.getPersonName())){
						passLogEntity.setPersonName(passBasicInfoBatchApprovalForm.getPersonName());
					}else{
						passLogEntity.setPersonName(AuditName);
					}
					passLogEntity.setAddTime(new Date());
					passLogEntity.setDescs(passBasicInfoBatchApprovalForm.getDesc());
					passLogEntity.setIsDelete("1");
					passLogEntity.setUnitId(userInfo.getDeptId()+"");
					passLogList.add(passLogEntity);

					if ("7".equals(passBasicInfoEntity.getPersonState()) || "11".equals(passBasicInfoEntity.getPersonState())
							|| "230".equals(passBasicInfoEntity.getPersonState())){
						passBasicInfoEntity.setStartTime(passBasicInfoEntity.getOldStartTime());
						passBasicInfoEntity.setEndTime(passBasicInfoEntity.getOldEndTime());
					}
					this.passBasicInfoService.update(passBasicInfoEntity.getId(),passBasicInfoEntity);
					this.passLogService.save(passLogEntity);
				}
			}


			return R.ok(null, "????????????");
		}catch (Exception e){
			System.out.println(e);
			return R.failed(null, "????????????");
		}

	}




	@PostMapping("/approvalbatch")
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	public R<Errorss> approvalbatch(@RequestBody @Valid PassBasicInfoBatchApprovalForm passBasicInfoBatchApprovalForm) throws Exception {
		try {
			PassBasicInfoBatchApprovalForm entity=JsonUtil.getJsonToBean(passBasicInfoBatchApprovalForm, PassBasicInfoBatchApprovalForm.class);
            List<Map<String,String>> delay = entity.getDelay();
			BoosterUser userInfo = SecurityUtils.getUser();
			// ??????
			String AuditName = "";
			R<UserInfoModel> r = remoteUserService.info(userInfo.getUsername(),"Y");
			UserInfoModel vo = r.getData();
			if (vo != null){
				AuditName = vo.getSysUser().getManager();
			}
            if (delay != null && delay.size() > 0){
				List<PassBasicInfoEntity> list = new ArrayList<>();
				List<PassLogEntity> passLogList = new ArrayList<>();
            	for (Map<String,String> map :delay){
            		String id = map.get("id");
            		String personState = map.get("personState");
					PassBasicInfoEntity passBasicInfoEntity= passBasicInfoService.getInfo(id);
					if (personState.equals(passBasicInfoEntity.getPersonState())){
						Errorss f = new Errorss();
						f.setState(false);
						return R.ok(f,"\" "+passBasicInfoEntity.getNames()+" \""+"????????????????????????????????????");
					}else{
						List<String> topState = passLogService.getTopState(passBasicInfoEntity.getId());
						StringBuffer stringBuffer = new StringBuffer();
						stringBuffer.append(personState);
						if (topState != null && topState.size() > 0){
							stringBuffer.append(","+topState.get(0));
						}
						List<String> repeat = passLogService.getRepeatList(stringBuffer.toString());
						if (repeat != null && repeat.size() == 2){
							String a = repeat.get(0);
							String b = repeat.get(1);
							if (a.equals(b)){
								Errorss f = new Errorss();
								f.setState(false);
								return R.ok(f,"\" "+passBasicInfoEntity.getNames()+" \""+"????????????????????????????????????");
							}
						}

					}
					passBasicInfoEntity.setPersonState(personState);
					String state = passBasicInfoEntity.getPersonState();
					if ("1".equals(state) || "2".equals(state) || "7".equals(state) || "8".equals(state)
							|| "16".equals(state) || "17".equals(state)
							|| "160".equals(state) || "170".equals(state)
							|| "260".equals(state) || "270".equals(state)){
						if (StringUtils.isNotEmpty(passBasicInfoBatchApprovalForm.getPersonName())){
							passBasicInfoEntity.setAuditName(passBasicInfoBatchApprovalForm.getPersonName());
						}else{
							passBasicInfoEntity.setAuditName(AuditName);
						}
					}

					if ("290".equals(passBasicInfoEntity.getPersonState())){  //  ????????????????????????
						Date delayToTime = passBasicInfoEntity.getDelayToTime();
						if (delayToTime != null){
							passBasicInfoEntity.setEndTime(delayToTime);
							passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
							passBasicInfoEntity.setOldEndTime(delayToTime);
						}
						String userName = passBasicInfoEntity.getIdCard();
						//??????????????????????????????
						R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						//??????????????????????????????
						if(info!=null){
							//??????????????????????????????
							remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						}
					}

					if ("19".equals(passBasicInfoEntity.getPersonState())){  //  ????????????????????????
						Date delayToTime = passBasicInfoEntity.getDelayToTime();
						if (delayToTime != null){
							passBasicInfoEntity.setEndTime(delayToTime);
							passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
							passBasicInfoEntity.setOldEndTime(delayToTime);
						}
						String userName = passBasicInfoEntity.getIdCard();
						//??????????????????????????????
						R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						//??????????????????????????????
						if(info!=null){
							//??????????????????????????????
							remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						}
					}

					if ("4".equals(passBasicInfoEntity.getPersonState())){  //  ??????????????????????????????
						// ?????????????????????????????????
						createIdCard(passBasicInfoEntity);
						sendMsg(passBasicInfoEntity);
						//??????????????????????????????
						syn(passBasicInfoEntity);
					}

					if ("190".equals(passBasicInfoEntity.getPersonState())){  //  ?????????????????????????????????????????????
						// ?????????????????????????????????
						createIdCard(passBasicInfoEntity);
						sendMsg(passBasicInfoEntity);
						//??????????????????????????????
						syn(passBasicInfoEntity);
					}

					if ("10".equals(passBasicInfoEntity.getPersonState())){  //  ??????????????????????????????
						// ?????????????????????????????????
						//createIdCard(passBasicInfoEntity);
						//sendMsg(passBasicInfoEntity);
						//??????????????????????????????
						Date delayToTime = passBasicInfoEntity.getDelayToTime();
						if (delayToTime != null){
							passBasicInfoEntity.setEndTime(delayToTime);
							passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
							passBasicInfoEntity.setOldEndTime(delayToTime);
						}
						syn(passBasicInfoEntity);
						//           //?????????????????????????????????
						String userName = passBasicInfoEntity.getIdCard();
						//??????????????????????????????
						R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						//??????????????????????????????
						if(info!=null){
							//??????????????????????????????
							remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						}
					}
					list.add(passBasicInfoEntity);
					PassLogEntity passLogEntity = new PassLogEntity();
					passLogEntity.setId(RandomUtil.uuId());
					passLogEntity.setPassBasicInfoId(id+"");
					passLogEntity.setState(personState);
					if (StringUtils.isNotEmpty(passBasicInfoBatchApprovalForm.getPersonId())){
						passLogEntity.setPersonId(passBasicInfoBatchApprovalForm.getPersonId());
					}else{
						passLogEntity.setPersonId(userInfo.getId()+"");
					}
					if (StringUtils.isNotEmpty(passBasicInfoBatchApprovalForm.getPersonName())){
						passLogEntity.setPersonName(passBasicInfoBatchApprovalForm.getPersonName());
					}else{
						passLogEntity.setPersonName(AuditName);
					}

					passLogEntity.setAddTime(new Date());
					passLogEntity.setDescs(passBasicInfoBatchApprovalForm.getDesc());
					passLogEntity.setIsDelete("1");
					passLogEntity.setUnitId(userInfo.getDeptId()+"");
					passLogList.add(passLogEntity);
					if ("7".equals(passBasicInfoEntity.getPersonState()) || "11".equals(passBasicInfoEntity.getPersonState())
							|| "230".equals(passBasicInfoEntity.getPersonState())){
						passBasicInfoEntity.setStartTime(passBasicInfoEntity.getOldStartTime());
						passBasicInfoEntity.setEndTime(passBasicInfoEntity.getOldEndTime());
					}
					this.passBasicInfoService.update(passBasicInfoEntity.getId(),passBasicInfoEntity);
					this.passLogService.save(passLogEntity);
				}
				/*this.passBasicInfoService.updateBatchById(list);
				this.passLogService.saveBatch(passLogList);
*/
			}


			return R.ok(null, "????????????");
		}catch (Exception e){
			System.out.println(e);
			return R.failed(null, "????????????");
		}

	}



	/**
	 *  ??????
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/approval")
	@ApiOperation(value = "???????????????", notes = "???????????????")
	public R<Errorss> approval(@RequestBody @Valid PassBasicInfoApprovalForm passBasicInfoApprovalForm) throws Exception {

		try{
		PassBasicInfoApprovalForm entity=JsonUtil.getJsonToBean(passBasicInfoApprovalForm, PassBasicInfoApprovalForm.class);
		List<String> ids = entity.getIds();
			BoosterUser userInfo = SecurityUtils.getUser();
		// ??????
			String AuditName = "";
			R<UserInfoModel> r = remoteUserService.info(userInfo.getUsername(),"Y");
			UserInfoModel vo = r.getData();
			if (vo != null){
				AuditName = vo.getSysUser().getManager();
			}
		if (ids != null && ids.size() > 0){
			List<PassBasicInfoEntity> list = new ArrayList<>();
			List<PassLogEntity> passLogList = new ArrayList<>();
			for (String id : ids){
				     PassBasicInfoEntity passBasicInfoEntity= passBasicInfoService.getInfo(id);
					if (entity.getPersonState().equals(passBasicInfoEntity.getPersonState())){
						Errorss f = new Errorss();
						f.setState(false);
						return R.ok(f,"\" "+passBasicInfoEntity.getNames()+" \""+"????????????????????????????????????");
					}else{
						List<String> topState = passLogService.getTopState(passBasicInfoEntity.getId());
						StringBuffer stringBuffer = new StringBuffer();
						stringBuffer.append(entity.getPersonState());
						if (topState != null && topState.size() > 0){
							stringBuffer.append(","+topState.get(0));
						}
						List<String> repeat = passLogService.getRepeatList(stringBuffer.toString());
						if (repeat != null && repeat.size() == 2){
							String a = repeat.get(0);
							String b = repeat.get(1);
							if (a.equals(b)){
								Errorss f = new Errorss();
								f.setState(false);
								return R.ok(f,"\" "+passBasicInfoEntity.getNames()+" \""+"????????????????????????????????????");
							}
						}

					}
				     passBasicInfoEntity.setPersonState(entity.getPersonState());
				     String state = passBasicInfoEntity.getPersonState();
				     if ("1".equals(state) || "2".equals(state) || "7".equals(state) || "8".equals(state)
					     || "16".equals(state) || "17".equals(state)
							 || "160".equals(state) || "170".equals(state)
							 || "260".equals(state) || "270".equals(state)){
						 if (StringUtils.isNotEmpty(entity.getPersonName())){
							 passBasicInfoEntity.setAuditName(entity.getPersonName());
						 }else{
							 passBasicInfoEntity.setAuditName(AuditName);
						 }
					 }

					if ("290".equals(passBasicInfoEntity.getPersonState())){  //  ????????????????????????
						Date delayToTime = passBasicInfoEntity.getDelayToTime();
						if (delayToTime != null){
							passBasicInfoEntity.setEndTime(delayToTime);
							passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
							passBasicInfoEntity.setOldEndTime(delayToTime);

						}
						String userName = passBasicInfoEntity.getIdCard();
						//??????????????????????????????
						R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						//??????????????????????????????
						if(info!=null){
							//??????????????????????????????
							remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						}
					}

				     if ("19".equals(passBasicInfoEntity.getPersonState())){  //  ????????????????????????
						 Date delayToTime = passBasicInfoEntity.getDelayToTime();
						 if (delayToTime != null){
							 passBasicInfoEntity.setEndTime(delayToTime);
							 passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
							 passBasicInfoEntity.setOldEndTime(delayToTime);
						 }
						 String userName = passBasicInfoEntity.getIdCard();
						 //??????????????????????????????
						 R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						 //??????????????????????????????
						 if(info!=null){
							 //??????????????????????????????
							 remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						 }
					 }

					if ("4".equals(passBasicInfoEntity.getPersonState())){  //  ??????????????????????????????
						// ?????????????????????????????????
						createIdCard(passBasicInfoEntity);
						sendMsg(passBasicInfoEntity);
						//??????????????????????????????
						syn(passBasicInfoEntity);
					}

				if ("190".equals(passBasicInfoEntity.getPersonState())){  //  ?????????????????????????????????????????????
					// ?????????????????????????????????
					createIdCard(passBasicInfoEntity);
					sendMsg(passBasicInfoEntity);
					//??????????????????????????????
					syn(passBasicInfoEntity);
				}

					if ("10".equals(passBasicInfoEntity.getPersonState())){  //  ??????????????????????????????
						// ?????????????????????????????????
						//createIdCard(passBasicInfoEntity);
						//sendMsg(passBasicInfoEntity);
						//??????????????????????????????
						Date delayToTime = passBasicInfoEntity.getDelayToTime();
						if (delayToTime != null){
							passBasicInfoEntity.setEndTime(delayToTime);
							passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getDelayStartTime());
							passBasicInfoEntity.setOldEndTime(delayToTime);
						}
						syn(passBasicInfoEntity);
						//           //?????????????????????????????????
						  String userName = passBasicInfoEntity.getIdCard();
						  //??????????????????????????????
						  R<UserInfoModel> info = remoteUserService.info(userName, CommonConstants.STATUS_LOCK);
						  //??????????????????????????????
						  if(info!=null){
							 //??????????????????????????????
							 remoteUserService.lockUser(userName, CommonConstants.STATUS_NORMAL);
						  }
					}

				     list.add(passBasicInfoEntity);

				     PassLogEntity passLogEntity = new PassLogEntity();
				     passLogEntity.setId(RandomUtil.uuId());
				     passLogEntity.setPassBasicInfoId(id+"");
				     passLogEntity.setState(entity.getPersonState());
					if (StringUtils.isNotEmpty(passBasicInfoApprovalForm.getPersonId())){
						passLogEntity.setPersonId(passBasicInfoApprovalForm.getPersonId());
					}else{
						passLogEntity.setPersonId(userInfo.getId()+"");
					}
					if (StringUtils.isNotEmpty(passBasicInfoApprovalForm.getPersonName())){
						passLogEntity.setPersonName(passBasicInfoApprovalForm.getPersonName());
					}else{
						passLogEntity.setPersonName(AuditName);
					}
				     passLogEntity.setAddTime(new Date());
				     passLogEntity.setDescs(passBasicInfoApprovalForm.getDesc());
				     passLogEntity.setIsDelete("1");
				     passLogEntity.setUnitId(userInfo.getDeptId()+"");
				     passLogList.add(passLogEntity);
				if ("7".equals(passBasicInfoEntity.getPersonState()) || "11".equals(passBasicInfoEntity.getPersonState())
						|| "230".equals(passBasicInfoEntity.getPersonState())){
					passBasicInfoEntity.setStartTime(passBasicInfoEntity.getOldStartTime());
					passBasicInfoEntity.setEndTime(passBasicInfoEntity.getOldEndTime());
				}
				this.passBasicInfoService.update(passBasicInfoEntity.getId(),passBasicInfoEntity);
				this.passLogService.save(passLogEntity);
			}

		}

		return R.ok(null, "????????????");
		}catch (Exception e){
			System.out.println(e);
			return R.failed(null, "????????????");
		}
	}

	public void saveNoRoleLog(BoosterUser userInfo,PassBasicInfoEntity passBasicInfoEntity){
		String state = passBasicInfoEntity.getPersonState();
		String roleCode = passLogService.getRoleCode(state);
		if (roleCode != null){
			UserDTO userDTO = new UserDTO();
			userDTO.setDeptId(userInfo.getDeptId());
			userDTO.setUserId(userInfo.getId());
			userDTO.setOscId(roleCode);
			R<List<SysUserEntity>> r = remoteUserService.getNoPowerUser(userDTO);
            List<SysUserEntity> list = r.getData();
            if (list != null && list.size() > 0){
            	List<PassLogNoPowerEntity> passLogNoPowerList = new ArrayList<>();
            	for (SysUserEntity sysUserEntity :list){
            		//
					PassLogNoPowerEntity passLogEntity = new PassLogNoPowerEntity();
					passLogEntity.setId(RandomUtil.uuId());
					passLogEntity.setPassBasicInfoId(passBasicInfoEntity.getId()+"");
					passLogEntity.setState(passBasicInfoEntity.getPersonState());
					passLogEntity.setPersonId(sysUserEntity.getUserId()+"");
					passLogEntity.setPersonName(sysUserEntity.getManager());
					passLogEntity.setAddTime(new Date());
					passLogEntity.setIsDelete("1");
					passLogEntity.setUnitId(userInfo.getDeptId()+"");
					passLogNoPowerList.add(passLogEntity);
				}
            	this.passLogNoPowerService.saveBatch(passLogNoPowerList);
			}
		}
	}


	public static void main(String[] args){
		PassBasicInfoEntity passBasicInfoEntity = new PassBasicInfoEntity();
		passBasicInfoEntity.setSNo(2000000L);
		passBasicInfoEntity.setPersonType("A");
		//createIdCard(passBasicInfoEntity);
	}

	public void createIdCard(PassBasicInfoEntity passBasicInfoEntity){
		String snos = "";
		String no = "";
		Long sno = passBasicInfoEntity.getSNo();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(new Date()).substring(2,4);
		snos = sno+"";
		if (snos.length() >= 6){
			no = snos.substring(snos.length()-6,snos.length());
		}else if (snos.length() < 2 ){
			no = "00000"+snos;
		}else if (snos.length() < 3 ){
			no = "0000"+snos;
		}else if (snos.length() < 4 ){
			no = "000"+snos;
		}else if (snos.length() < 5 ){
			no = "00"+snos;
		}else if (snos.length() < 6 ){
			no = "0"+snos;
		}
        String IdCard = date +"07"+passBasicInfoEntity.getPersonType()+no;
		passBasicInfoEntity.setIdCard(IdCard);
	}

	/**
	 *  ??????
	 * @param
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/delay")
	@ApiOperation(value = "?????????????????????", notes = "?????????????????????")
	public R<List<PassBasicInfoEntity>> delay(@RequestBody @Valid PassBasicInfoApprovalForm passBasicInfoApprovalForm) throws Exception {

		PassBasicInfoApprovalForm entity=JsonUtil.getJsonToBean(passBasicInfoApprovalForm, PassBasicInfoApprovalForm.class);
		List<String> ids = entity.getIds();
		BoosterUser userInfo = SecurityUtils.getUser();
		// ??????
		String AuditName = "";
		R<UserInfoModel> r = remoteUserService.info(userInfo.getUsername(),"Y");
		UserInfoModel vo = r.getData();
		if (vo != null){
			AuditName = vo.getSysUser().getManager();
		}
		if (ids != null && ids.size() > 0){
			List<PassBasicInfoEntity> list = new ArrayList<>();
			List<PassLogEntity> passLogList = new ArrayList<>();
			List<PassDelayEntity> passDelayList = new ArrayList<>();
			for (String id : ids){
				PassBasicInfoEntity passBasicInfoEntity= passBasicInfoService.getInfo(id);
				passBasicInfoEntity.setPersonState(entity.getPersonState());
				// ????????????
				passBasicInfoEntity.setDelayDesc(entity.getDesc());
				passBasicInfoEntity.setOldStartTime(passBasicInfoEntity.getStartTime());
				passBasicInfoEntity.setOldEndTime(passBasicInfoEntity.getEndTime());
				passBasicInfoEntity.setStartTime(entity.getDelayStartTime());
				passBasicInfoEntity.setEndTime(entity.getDelayToTime());
				passBasicInfoEntity.setDelayStartTime(entity.getDelayStartTime());
				passBasicInfoEntity.setDelayToTime(entity.getDelayToTime());
				Integer delayNumber = passBasicInfoEntity.getDelayNumber();
				if (delayNumber == null){
					passBasicInfoEntity.setDelayNumber(1);
				}else{
					passBasicInfoEntity.setDelayNumber(delayNumber + 1);
				}

				list.add(passBasicInfoEntity);

				PassLogEntity passLogEntity = new PassLogEntity();
				passLogEntity.setId(RandomUtil.uuId());
				passLogEntity.setPassBasicInfoId(id+"");
				passLogEntity.setState(entity.getPersonState());
				passLogEntity.setPersonId(userInfo.getId()+"");
				passLogEntity.setPersonName(AuditName);
				passLogEntity.setAddTime(new Date());
				passLogEntity.setDescs(entity.getDesc());
				passLogEntity.setIsDelete("1");
				passLogEntity.setUnitId(userInfo.getDeptId()+"");
				passLogList.add(passLogEntity);

				PassDelayEntity passDelayEntity = new PassDelayEntity();
				passDelayEntity.setId(RandomUtil.uuId());
				passDelayEntity.setDelayState(entity.getPersonState());
				passDelayEntity.setDelayToTime(entity.getDelayToTime());
				passDelayEntity.setDelayStartTime(entity.getDelayStartTime());
				passDelayEntity.setIsDelete("1");
				passDelayEntity.setPassBasicInfoId(id);
				passDelayEntity.setReason(entity.getDesc());
				passDelayEntity.setAddPersonId(userInfo.getId()+"");
				passDelayEntity.setAddPersonName(AuditName);
				passDelayEntity.setAddTime(new Date());
				passDelayList.add(passDelayEntity);

			}
			this.passBasicInfoService.updateBatchById(list);
			this.passLogService.saveBatch(passLogList);
			this.passDelayService.saveBatch(passDelayList);

		}

		return R.ok(null, "????????????");
	}

	/**
	 * ????????????
	 * @return R
	 */
	@PostMapping("/syn")
	public Boolean syn(PassBasicInfoEntity apsSystemCrForm) {
		try {
			List<Long> role = new ArrayList<>();
			if (StringUtils.isNotEmpty(roles)){
				String[] s = roles.split(",");
				for (String ss : s){
					role.add(Long.parseLong(ss));
				}
			}
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(apsSystemCrForm.getIdCard());
			userDTO.setPhone(apsSystemCrForm.getPhone());
			userDTO.setRole(role);
			if ("190".equals(apsSystemCrForm.getPersonState())){
				if (StringUtils.isNotEmpty(apsSystemCrForm.getYrUnitArr())){
					String a = apsSystemCrForm.getYrUnitArr();
					String[] b = a.split(",");
					userDTO.setDeptId(Long.parseLong(b[b.length-1]));
				}else{
					userDTO.setDeptId(Long.parseLong(apsSystemCrForm.getUnitId()));
				}
			}else{
				userDTO.setDeptId(Long.parseLong(apsSystemCrForm.getUnitId()));
			}

            userDTO.setManager(apsSystemCrForm.getNames());
			R<Boolean> r = remoteUserService.synUser(userDTO);
			return true;
		}catch (Exception e){
			return false;
		}
	}


	@GetMapping("/send")
	public Boolean send(String phone) {
		try {
			String msg = SmsSendAPI.smsNoticeFuntion(SMS_URL,SMS_USERNAME,SMS_PASSWORD,SMS_EPID,phone,"??????");
			System.out.println("??????"+msg);
			return true;
		}catch (Exception e){
			System.out.println(e);
			return false;
		}
	}

	public void sendMsg(PassBasicInfoEntity entity){
		String phone = entity.getPhone();
		if ("0".equals(IS_SMS) && StringUtils.isNotEmpty(phone)){
			String message = entity.getNames()+"???????????????????????????????????????http://www.bwb.pku.edu.cn?????????????????????????????????????????????????????????????????????????????????????????????"+entity.getIdCard()+"???????????????:q1w2e3r4";
			String msg = SmsSendAPI.smsNoticeFuntion(SMS_URL,SMS_USERNAME,SMS_PASSWORD,SMS_EPID,phone,message);
			// ???????????????
			SendMsgLogEntity sendMsgLogEntity = new SendMsgLogEntity();
			sendMsgLogEntity.setAddTime(new Date());
			sendMsgLogEntity.setId(RandomUtil.uuId());
			sendMsgLogEntity.setMsg(msg);
			sendMsgLogEntity.setPhone(phone);
			sendMsgLogService.create(sendMsgLogEntity);
		}
	}


	public Boolean updatePassBasicInfoToDoor(PassBasicInfoEntity entity){
		try{

			JSONObject content = new JSONObject();
			content.put("personName",entity.getNames());

			if ("???".equals(entity.getSex())){
				content.put("gender","1");
			}else if ("???".equals(entity.getSex())){
				content.put("gender","2");
			}else{
				content.put("gender","0");
			}
			content.put("orgIndexCode","04f15c10-57e9-484f-9b29-8fc94fb11fbd");
			if (entity.getBirthday() != null){
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				content.put("birthday",simpleDateFormat.format(entity.getBirthday()));
			}
			content.put("phoneNo",entity.getPhone());
			content.put("personId",entity.getDoorPersonId());
			content.put("email",null);
			content.put("certificateType","111");
			content.put("certificateNo",entity.getCardNumber());
			content.put("personType","4");
			content.put("jobNo",entity.getIdCard());
			JSONArray jsonArray = new JSONArray();
			content.put("faces",jsonArray);

			Map<String, String> path = new HashMap<String, String>(2) {
				{
					put("https://", updatePerson);//?????????????????????????????????http??????https
				}
			};
			ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			String resultl = null;

				resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

			System.out.println("???????????????????????????????????????"+resultl);
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
			String code = jsonObject1.getString("code");
			if ("0".equals(code)){
				passBasicInfoService.update(entity.getId(), entity);
				return true;
			}
			return true;
		}catch (Exception e){
			System.out.println("?????????????????????????????????????????????"+e.getMessage());
			return false;
		}
	}

	public Boolean addPassBasicInfoToDoor(PassBasicInfoEntity entity){
		try{

			JSONObject content = new JSONObject();
			content.put("personName",entity.getNames());
			if ("???".equals(entity.getSex())){
				content.put("gender","1");
			}else if ("???".equals(entity.getSex())){
				content.put("gender","2");
			}else{
				content.put("gender","0");
			}
			content.put("orgIndexCode","04f15c10-57e9-484f-9b29-8fc94fb11fbd");
			if (entity.getBirthday() != null){
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				content.put("birthday",simpleDateFormat.format(entity.getBirthday()));
			}
			content.put("phoneNo",entity.getPhone());
			content.put("email",null);
			content.put("certificateType","111");
			content.put("certificateNo",entity.getCardNumber());
			content.put("personType","4");
			content.put("jobNo",entity.getIdCard());
			JSONArray jsonArray = new JSONArray();
			content.put("faces",jsonArray);

			Map<String, String> path = new HashMap<String, String>(2) {
				{
					put("https://", syn_person_add_url);//?????????????????????????????????http??????https
				}
			};
			ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			String resultl = null;

				resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

			System.out.println("???????????????????????????????????????"+resultl);
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
			String code = jsonObject1.getString("code");
			if ("0".equals(code)){
				JSONObject data = jsonObject1.getJSONObject("data");
				if (data != null){
					String personId = data.getString("personId");
					String faceId = data.getString("faceId");
					entity.setDoorPersonId(personId);
					if ("null".equals(faceId)){
						entity.setDoorFaceId(null);
					}else{
						System.out.println(faceId);
					}
					passBasicInfoService.update(entity.getId(), entity);
				}
			}
			return true;
		}catch (Exception e){
			System.out.println("?????????????????????????????????????????????"+e.getMessage());
			return false;
		}
	}

	public Boolean SynToDoorpost(PassBasicInfoEntity entity){
      try{

		  JSONObject content = new JSONObject();
		  content.put("personName",entity.getNames());
		  if ("???".equals(entity.getSex())){
			  content.put("gender","1");
		  }else if ("???".equals(entity.getSex())){
			  content.put("gender","2");
		  }else{
			  content.put("gender","0");
		  }
		  content.put("orgIndexCode","04f15c10-57e9-484f-9b29-8fc94fb11fbd");
		  if (entity.getBirthday() != null){
			  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			  content.put("birthday",simpleDateFormat.format(entity.getBirthday()));
		  }
		  content.put("phoneNo",entity.getPhone());
		  content.put("email",null);
		  content.put("certificateType","111");
		  content.put("certificateNo",entity.getCardNumber());
		  content.put("personType","4");
		  content.put("jobNo",entity.getIdCard());
		  JSONArray jsonArray = new JSONArray();
		  PassFileEntity passFileEntity = new PassFileEntity();
		  passFileEntity.setPassBasicInfoId(entity.getId());
		  passFileEntity.setFileType("A");
		  List<FileEntity> file1 = this.passFileService.getFileList(passFileEntity);
		  if (file1 != null && file1.size() > 0){
			  for (FileEntity fe:file1){
				  String encodeString = fileService.encodeBase64String(fe);
				  JSONObject faceData = new JSONObject();
				  faceData.put("faceData",encodeString);
				  jsonArray.add(faceData);
			  }
		  }
		  content.put("faces",jsonArray);

		  Map<String, String> path = new HashMap<String, String>(2) {
			  {
				  put("https://", syn_person_add_url);//?????????????????????????????????http??????https
			  }
		  };
		  ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
		  String resultl = null;

			  resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

		  System.out.println("??????????????????????????????????????????????????????"+resultl);
		  net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
		  String code = jsonObject1.getString("code");
		  if ("0".equals(code)){
			  JSONObject data = jsonObject1.getJSONObject("data");
			  if (data != null){
				  String personId = data.getString("personId");
				  String faceId = data.getString("faceId");
				  entity.setDoorPersonId(personId);
				  if ("null".equals(faceId)){
					  entity.setDoorFaceId(null);
				  }else{
					  entity.setDoorFaceId(faceId);
				  }
				  passBasicInfoService.update(entity.getId(), entity);
			  }
		  }
		  return true;
	  }catch (Exception e){
      	System.out.println("????????????????????????????????????????????????????????????"+e.getMessage());
      	return false;
	  }
	}

	public Boolean SynPngToDoorpost(PassBasicInfoEntity entity,String faceData){
		try{

			JSONObject content = new JSONObject();
			content.put("personId",entity.getDoorPersonId());
			content.put("faceData",faceData);
			Map<String, String> path = new HashMap<String, String>(2) {
				{
					put("https://", addface);//?????????????????????????????????http??????https
				}
			};
			ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			String resultl = null;

				resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

			System.out.println("???????????????????????????????????????"+resultl);
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
			String code = jsonObject1.getString("code");
			if ("0".equals(code)){
				JSONObject data = jsonObject1.getJSONObject("data");
				if (data != null){
					String personId = data.getString("personId");
					String faceId = data.getString("faceId");
					entity.setDoorPersonId(personId);
					if ("null".equals(faceId)){
						entity.setDoorFaceId(null);
					}else{
						entity.setDoorFaceId(faceId);
					}
					passBasicInfoService.update(entity.getId(), entity);
				}
			}
			return true;
		}catch (Exception e){
			System.out.println("???????????????????????????????????????"+e.getMessage());
			return false;
		}
	}

	public Boolean deletePngToDoorpost(PassBasicInfoEntity entity){
		try{

			JSONObject content = new JSONObject();
			content.put("faceId",entity.getDoorFaceId());
			Map<String, String> path = new HashMap<String, String>(2) {
				{
					put("https://", deleteface);//?????????????????????????????????http??????https
				}
			};
			ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			String resultl = null;

				resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

			System.out.println("???????????????????????????????????????"+resultl);
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
			String code = jsonObject1.getString("code");
			if ("0".equals(code)){
				entity.setDoorFaceId("");
				passBasicInfoService.update(entity.getId(), entity);
				return true;
			}
			return true;
		}catch (Exception e){
			System.out.println("???????????????????????????????????????"+e.getMessage());
			return false;
		}
	}

	public Boolean UpdatePngToDoorpost(PassBasicInfoEntity entity,String faceData){
		try{

			JSONObject content = new JSONObject();
			content.put("faceId",entity.getDoorFaceId());
			content.put("faceData",faceData);
			Map<String, String> path = new HashMap<String, String>(2) {
				{
					put("https://", updateface);//?????????????????????????????????http??????https
				}
			};
			ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			String resultl = null;

				resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

			System.out.println("???????????????????????????????????????"+resultl);
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
			String code = jsonObject1.getString("code");
			if ("0".equals(code)){
				passBasicInfoService.update(entity.getId(), entity);
				return true;
			}
			return true;
		}catch (Exception e){
			System.out.println("???????????????????????????????????????"+e.getMessage());
			return false;
		}
	}



	public Boolean getInfoToDoorpost(PassBasicInfoEntity entity,String faceData){
		try{

			JSONObject content = new JSONObject();
			content.put("paramName","jobNo");
			List<String> jobNo = new ArrayList<>();
			jobNo.add("2207E000050");
			content.put("paramValue",jobNo);
			System.out.println(content.toString());
			Map<String, String> path = new HashMap<String, String>(2) {
				{
					put("https://", "/artemis/api/resource/v1/person/condition/personInfo");//?????????????????????????????????http??????https
				}
			};
			ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			String resultl = null;

			resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

			System.out.println("???????????????????????????????????????"+resultl);
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
			String code = jsonObject1.getString("code");
			if ("0".equals(code)){
				passBasicInfoService.update(entity.getId(), entity);
				return true;
			}
			return true;
		}catch (Exception e){
			System.out.println("??????????????????????????????????????????"+e.getMessage());
			return false;
		}
	}



	@PostMapping ("/personNumberClose")
	@Transactional
	public String personNumberClose(@RequestBody @Valid PassBasicInfoEntity passBasicInfoEntity){


		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", queryByUserGrooupCc);//?????????????????????????????????http??????https
			}
		};
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("jobNo",passBasicInfoEntity.getIdCard());
		jsonBody.put("pageNo",1);
		jsonBody.put("pageSize",1000);
		String body = jsonBody.toString();
		ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
		String resultl = null;
		try {
			resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, "application/json", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
		net.sf.json.JSONObject data = jsonObject1.getJSONObject("data");
		List<net.sf.json.JSONObject> groupList = (List<net.sf.json.JSONObject>) data.get("personGroupList");
		//????????????
		for (net.sf.json.JSONObject jsonObject : groupList) {
			System.out.println(passBasicInfoEntity.getIdCard() + jsonObject.get("personGroupId")+"?????????????????????????????????????????????????");
			path = new HashMap<String, String>(2) {
				{
					put("https://", deleteByUserGrooup);//?????????????????????????????????http??????https
				}
			};
			String[] jobNos = new String[]{passBasicInfoEntity.getIdCard()};
			jsonBody = new JSONObject();
			jsonBody.put("personGroupId",jsonObject.get("personGroupId"));
			jsonBody.put("jobNos",jobNos);
			jsonBody.put("pageSize",1000);
			body = jsonBody.toString();
			artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			resultl = null;
			try {
				resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, "application/json", null);
				System.out.println("resultl + " + resultl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultl;
	}


	@PostMapping ("/personNumberOpen")
	@Transactional
	public String personNumberOpen(@RequestBody @Valid PassBasicInfoEntity passBasicInfoEntity){
		//String groupName = "?????????002";
		String groupId = "";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", queryGroupId);//?????????????????????????????????http??????https
			}
		};
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("personGroupName",groupName);
		jsonBody.put("pageNo",1);
		jsonBody.put("pageSize",1000);
		String body = jsonBody.toString();
		ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
		String resultl = null;
		try {
			resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, "application/json", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
		System.out.println(resultl);
		net.sf.json.JSONObject data = jsonObject1.getJSONObject("data");
		List<net.sf.json.JSONObject> groupList = (List<net.sf.json.JSONObject>) data.get("list");
		for (net.sf.json.JSONObject jsonObject : groupList) {
			groupId = (String) jsonObject.get("personGroupId");
		}
		//????????????ID
		path = new HashMap<String, String>(2) {
			{
				put("https://", queryPersonId);//?????????????????????????????????http??????https
			}
		};
		jsonBody = new JSONObject();
		jsonBody.put("jobNo",passBasicInfoEntity.getIdCard());
		body = jsonBody.toString();
		artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
		resultl = null;
		try {
			resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, "application/json", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
		System.out.println(jsonObject1);
		String personId = (String) jsonObject1.get("personId");
		//????????????
		path = new HashMap<String, String>(2) {
			{
				put("https://", addPerson);//?????????????????????????????????http??????https
			}
		};
		jsonBody = new JSONObject();
		jsonBody.put("personId",personId);
		jsonBody.put("groupId",groupId);
		body = jsonBody.toString();
		artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
		resultl = null;
		try {
			resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, body, null, null, "application/json", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
		return resultl;
	}


	public Boolean deleteToDoorpost(List<String> list){
		try{

			JSONObject content = new JSONObject();
			content.put("personIds",list);
			System.out.println(content.toString());
			Map<String, String> path = new HashMap<String, String>(2) {
				{
					put("https://", deletePerson);//?????????????????????????????????http??????https
				}
			};
			ArtemisConfig artemisConfig = new ArtemisConfig(Config_host, Config_appKey, Config_appSecret);
			String resultl = null;

			resultl = ArtemisHttpUtil.doPostStringArtemis(artemisConfig, path, content.toString(), null, null, "application/json", null);

			System.out.println("???????????????????????????????????????"+resultl);
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(resultl);
			String code = jsonObject1.getString("code");
			if ("0".equals(code)){
				return true;
			}
			return true;
		}catch (Exception e){
			System.out.println("??????????????????????????????????????????"+e.getMessage());
			return false;
		}
	}

	public void saveLog(PassBasicInfoEntity entity){
		BoosterUser userInfo= SecurityUtils.getUser();
		PassLogEntity passLogEntity = new PassLogEntity();
		passLogEntity.setId(RandomUtil.uuId());
		passLogEntity.setPassBasicInfoId(entity.getId());
		if (StringUtils.isNotEmpty(entity.getPersonState())){
			passLogEntity.setState(entity.getPersonState());
		}else if (StringUtils.isNotEmpty(entity.getIsInout())){
			if ("0".equals(entity.getIsInout())){
				passLogEntity.setState("????????????????????????");
			}else if ("1".equals(entity.getIsInout())){
				passLogEntity.setState("????????????????????????");
			}
		}else if (StringUtils.isNotEmpty(entity.getIsGrantFace())){
			if ("0".equals(entity.getIsGrantFace())){
				passLogEntity.setState("?????????????????????");
			}else if ("1".equals(entity.getIsGrantFace())){
				passLogEntity.setState("?????????????????????");
			}
		}else if (StringUtils.isNotEmpty(entity.getIsGrantPass())){
			if ("0".equals(entity.getIsGrantPass())){
				passLogEntity.setState("???????????????");
			}else if ("1".equals(entity.getIsGrantFace())){
				passLogEntity.setState("?????????????????????");
			}
		}else if (StringUtils.isNotEmpty(entity.getIsPromise())){
			if ("0".equals(entity.getIsPromise())){
				passLogEntity.setState("???????????????");
			}else if ("1".equals(entity.getIsPromise())){
				passLogEntity.setState("?????????????????????");
			}
		}

		passLogEntity.setPersonId(userInfo.getId()+"");
		// ??????
		R<UserInfoModel> r = remoteUserService.info(userInfo.getUsername(),"Y");
		UserInfoModel vo = r.getData();
		if (vo != null){
			passLogEntity.setPersonName(vo.getSysUser().getManager());
		}
		passLogEntity.setAddTime(new Date());
		passLogEntity.setDescs("");
		passLogEntity.setIsDelete("1");
		passLogService.create(passLogEntity);
	}



}
