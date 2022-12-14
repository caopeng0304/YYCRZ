package com.sinosoft.ie.booster.yypass.controller;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.ie.booster.admin.api.entity.SysDeptEntity;
import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDeptService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteDictService;
import com.sinosoft.ie.booster.admin.api.feign.RemoteUserService;
import com.sinosoft.ie.booster.admin.api.model.UserInfoModel;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.minio.service.MinioTemplate;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import com.sinosoft.ie.booster.visualdev.util.RandomUtil;
import com.sinosoft.ie.booster.yypass.entity.FileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassBasicInfoEntity;
import com.sinosoft.ie.booster.yypass.entity.PassFileEntity;
import com.sinosoft.ie.booster.yypass.entity.PassLogEntity;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoCrForm;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoExcelVO;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoPagination;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoVO;
import com.sinosoft.ie.booster.yypass.service.*;
import com.sinosoft.ie.booster.yypass.util.ExportExcel;
import com.sinosoft.ie.booster.yypass.util.UtilExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@Api(tags = "?????????????????????")
@RequestMapping("/PassBasicInfo")
public class ExcelFileController {

	@Autowired
	private FileService fileService;
	@Autowired
	private PassBasicInfoService passBasicInfoService;
	@Autowired
	private PassFileService passFileService;
	@Autowired
	private RemoteDictService remoteDictService;
	@Autowired
	private MinioTemplate minioTemplate;
	@Autowired
	private RemoteDeptService remoteDeptService;
	@Autowired
	RemoteUserService remoteUserService;

	@Autowired
	PassLogService passLogService;

	@SneakyThrows
	@PostMapping("import-passbasicinfo")
	@ApiOperation(value = "????????????", notes = "?????? passBasicInfo")
	public R<Object> selectExcelName(MultipartFile file) {
		String filename = file.getOriginalFilename();
		BoosterUser userInfo= SecurityUtils.getUser();
		// ??????
		R<UserInfoModel> rr = remoteUserService.info(userInfo.getUsername(),"Y");
		UserInfoModel vov = rr.getData();
		String personName = "";
		if (vov != null){
			personName = vov.getSysUser().getManager();
		}

		if (org.springframework.util.StringUtils.isEmpty(filename)) {
			throw new RuntimeException("???????????????!");
		}
		if ((!org.springframework.util.StringUtils.endsWithIgnoreCase(filename, ".xls") && !org.springframework.util.StringUtils.endsWithIgnoreCase(filename, ".xlsx"))) {
			throw new RuntimeException("??????????????????excel??????!");
		}

		List<SysDictItemEntity> personTypeList = remoteDictService.getDictByType("personType").getData();
		List<SysDictItemEntity> modeList = remoteDictService.getDictByType("mode").getData();
		List<SysDictItemEntity> cardTypeList = remoteDictService.getDictByType("cardType").getData();
		List<SysDictItemEntity> nationList = remoteDictService.getDictByType("nation").getData();

		List<Map<String, Object>> mapList = getMap();
		List<Map<String, Object>> list = new ArrayList<>();
		InputStream inputStream = null; //???????????????
		Workbook wb = null;
		try {
			inputStream = file.getInputStream();//???????????????
			wb = new HSSFWorkbook(inputStream);//???????????????
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<Integer, String> map1 = new HashMap<>();//???????????????????????????????????????
		Sheet sheetAt = wb.getSheetAt(0);
		// ??????????????????
		//Map<String, PictureData> sheetPictrues03Map_ = UtilExcel.getSheetPictrues03( (HSSFSheet) sheetAt, (HSSFWorkbook) wb);
		/*String rowAndCellkey = "";
		if(null != sheetPictrues03Map  && sheetPictrues03Map.size() > 0){
			for (Map.Entry<String, PictureData> entry : sheetPictrues03Map.entrySet()) {
				rowAndCellkey = entry.getKey();
			}

		}*/

		//  ???????????????????????????
		Map<String, List<PictureData>> sheetPictrues03Map = UtilExcel.getSheetPictrues03s( (HSSFSheet) sheetAt, (HSSFWorkbook) wb);
		String rowAndCellkey = "";
		if(null != sheetPictrues03Map  && sheetPictrues03Map.size() > 0){
			for (Map.Entry<String, List<PictureData>> entry : sheetPictrues03Map.entrySet()) {
				rowAndCellkey = entry.getKey();
			}
		}

		// ??????????????????????????? ???
		String cellString = rowAndCellkey.substring(rowAndCellkey.indexOf("_")+1, rowAndCellkey.length());
		Map<String, List<Map<String,String>>> pathMap = null;
		try {
			//???????????????????????????????????????key??????????????????value???????????????
			//pathMap = printImg(sheetPictrues03Map);// ??????????????????
			pathMap = printImgs(sheetPictrues03Map);// ??????????????????
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		int firstRowNum = sheetAt.getFirstRowNum();
		int lastRowNum = sheetAt.getLastRowNum();
		for (int i = firstRowNum; i <= lastRowNum; i++) { //?????????
			Map<String, Object> maps = new HashMap<>();
			Row row = sheetAt.getRow(i);
			int firstCellNum = row.getFirstCellNum();
			int lastCellNum = row.getLastCellNum();
			for (int i1 = firstCellNum; i1 < lastCellNum; i1++) { //?????????
				if (i == 0 && row.getCell(i1) != null) { //??????????????????
					for (Map<String, Object> map : mapList) {  // ???????????????put??????
						if (row.getCell(i1).toString().equals(map.get("name"))) {
							map1.put(i1, map.get("java_field").toString());
							break;
						}
					}
				} else {   // ??????
					Cell cell = row.getCell(i1);
					if (cell == null) {
						maps.put(map1.get(i1), "");
					} else {
                        String val;
						if (cell.getCellType().getCode() == CellType.NUMERIC.getCode() && !DateUtil.isCellDateFormatted(cell)) {  // ?????????
							val = new DecimalFormat("#").format(cell.getNumericCellValue());
						} else if (cell.getCellType().getCode() == CellType.NUMERIC.getCode() && DateUtil.isCellDateFormatted(cell)) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							double value = cell.getNumericCellValue();
							Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
							val = sdf.format(date);
						} else {
							val = cell.getStringCellValue();
						}
						//maps.put(map1.get(i1),cell.toString() );
						maps.put(map1.get(i1),val );
					}
				}
				if (i > 0) {// ???????????????????????????????????????
					List<Map<String,String>> path = pathMap.get(i + "_" +cellString);
					maps.put(map1.get(Integer.parseInt(cellString)), path);
				}
			}
			if (i != 0) {
				list.add(maps);
			}
		}

		try {
			for (Map<String, Object> stringObjectMap : list) {

				PassBasicInfoVO passBasicInfoVO = JsonUtil.getJsonToBean(stringObjectMap, PassBasicInfoVO.class);

				Object o = stringObjectMap.get("file");
				List<FileEntity> files1 = JsonUtil.getJsonToList(o, FileEntity.class);

				String uuid = RandomUtil.uuId();
				PassBasicInfoEntity vo = JsonUtil.getJsonToBean(passBasicInfoVO, PassBasicInfoEntity.class);
				//??????????????????
				String nation = vo.getNation();
				if (StringUtils.isNotEmpty(nation) && nationList != null && nationList.size() > 0){
					List<SysDictItemEntity> n_type = nationList.stream().filter(s-> nation.equals(s.getLabel())).collect(Collectors.toList());
					if (n_type != null && n_type.size() > 0){
						vo.setNation(n_type.get(0).getValue());
					}else{
						vo.setNation(null);
					}
				}

				String modes = vo.getModes();
				if (StringUtils.isNotEmpty(modes) && modeList != null && modeList.size() > 0){
					List<SysDictItemEntity> m_type = modeList.stream().filter(s-> modes.equals(s.getLabel())).collect(Collectors.toList());
					if (m_type != null && m_type.size() > 0){
						vo.setModes(m_type.get(0).getValue());
					}else{
						vo.setModes(null);
					}
				}

				String cardType = vo.getCardType();
				if (StringUtils.isNotEmpty(cardType) && cardTypeList != null && cardTypeList.size() > 0){
					List<SysDictItemEntity> c_type = cardTypeList.stream().filter(s-> cardType.equals(s.getLabel())).collect(Collectors.toList());
					if (c_type != null && c_type.size() > 0){
						vo.setCardType(c_type.get(0).getValue());
					}else{
						vo.setCardType(null);
					}
				}

				String PersonType = vo.getPersonType();
				if (StringUtils.isNotEmpty(PersonType) && personTypeList != null && personTypeList.size() >0){
					List<SysDictItemEntity> p_type = personTypeList.stream().filter(s-> PersonType.equals(s.getLabel())).collect(Collectors.toList());
					if (p_type != null && p_type.size() > 0){
						vo.setPersonType(p_type.get(0).getValue());
					}else{
						vo.setPersonType(null);
					}
				}


				vo.setId(uuid);
				vo.setPersonState("-1");
				vo.setIsDelete("1");
				Long sno = passBasicInfoService.getSNo();
				if (sno == null){
					sno = 1L;
				}
				vo.setSNo(sno+1);
				vo.setAddPersonId(userInfo.getId()+"");
				vo.setAddPersonName(userInfo.getUsername());
				vo.setAddTime(new Date());
				// ????????????ID
				R<SysDeptEntity> r = remoteDeptService.infoByName(vo.getUnit());
				SysDeptEntity deptEntity = r.getData();
				if (deptEntity != null){
					vo.setUnitId(deptEntity.getDeptId()+"");
				}
				// ???????????????ID
				R<SysDeptEntity> yrunit = remoteDeptService.infoByName(vo.getYrUnit());
				SysDeptEntity yrDept = yrunit.getData();
				if (yrDept != null){
					if(yrDept.getDeptId().equals(userInfo.getDeptId())){
						vo.setYrUnitArr(yrDept.getDeptId()+"");
					}else{
						vo.setYrUnitArr(yrDept.getParentId()+","+yrDept.getDeptId()+"");
					}
					//vo.setYrUnitArr(yrDept.getDeptId()+"");
				}
				vo.setOldStartTime(vo.getStartTime());
				vo.setOldEndTime(vo.getEndTime());
				passBasicInfoService.create(vo);
				// ????????? pass_file
				if (files1 != null && files1.size() > 0){
					for (FileEntity s:files1){
						PassFileEntity fileEntity = new PassFileEntity();
						fileEntity.setId(RandomUtil.uuId());
						fileEntity.setFileId(s.getId());
						fileEntity.setPassBasicInfoId(uuid);
						fileEntity.setFileType("A");
						fileEntity.setIsDelete("1");
						passFileService.create(fileEntity);
					}
				}
				// ????????????
				PassLogEntity passLogEntity = new PassLogEntity();
				passLogEntity.setId(RandomUtil.uuId());
				passLogEntity.setPassBasicInfoId(vo.getId());
				passLogEntity.setState("????????????");
				passLogEntity.setPersonId(userInfo.getId()+"");
				passLogEntity.setPersonName(personName);
				passLogEntity.setAddTime(new Date());
				passLogEntity.setDescs("");
				passLogEntity.setIsDelete("1");
				passLogService.create(passLogEntity);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.failed(null, "????????????");
		}
		return R.ok(null, "????????????");
	}

	private List<Map<String,Object>> getMap(){
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map1 = new HashMap<>();
		map1.put("name","????????????");
		map1.put("java_field","personType");
		list.add(map1);

		Map<String,Object> map2 = new HashMap<>();
		map2.put("name","??????");
		map2.put("java_field","names");
		list.add(map2);

		Map<String,Object> map3 = new HashMap<>();
		map3.put("name","??????");
		map3.put("java_field","sex");
		list.add(map3);

		Map<String,Object> map4 = new HashMap<>();
		map4.put("name","??????");
		map4.put("java_field","phone");
		list.add(map4);

		/*Map<String,Object> map5 = new HashMap<>();
		map5.put("name","??????");
		map5.put("java_field","phone");
		list.add(map5);*/

		Map<String,Object> map6 = new HashMap<>();
		map6.put("name","????????????");
		map6.put("java_field","cardType");
		list.add(map6);

		Map<String,Object> map7 = new HashMap<>();
		map7.put("name","?????????");
		map7.put("java_field","cardNumber");
		list.add(map7);

		Map<String,Object> map8 = new HashMap<>();
		map8.put("name","????????????");
		map8.put("java_field","birthday");
		list.add(map8);

		Map<String,Object> map9 = new HashMap<>();
		map9.put("name","????????????");
		map9.put("java_field","workAddress");
		list.add(map9);

		Map<String,Object> map10 = new HashMap<>();
		map10.put("name","????????????");
		map10.put("java_field","startTime");
		list.add(map10);

		Map<String,Object> map11 = new HashMap<>();
		map11.put("name","????????????");
		map11.put("java_field","endTime");
		list.add(map11);

		Map<String,Object> map12 = new HashMap<>();
		map12.put("name","????????????");
		map12.put("java_field","unit");
		list.add(map12);

		Map<String,Object> map13 = new HashMap<>();
		map13.put("name","?????????");
		map13.put("java_field","unitName");
		list.add(map13);

		Map<String,Object> map14 = new HashMap<>();
		map14.put("name","??????");
		map14.put("java_field","reason");
		list.add(map14);

		Map<String,Object> map15 = new HashMap<>();
		map15.put("name","???????????????");
		map15.put("java_field","modes");
		list.add(map15);

		Map<String,Object> map16 = new HashMap<>();
		map16.put("name","???????????????");
		map16.put("java_field","address");
		list.add(map16);

		/*Map<String,Object> map17 = new HashMap<>();
		map17.put("name","????????????");
		map17.put("java_field","workAddress");
		list.add(map17);*/

		Map<String,Object> map18 = new HashMap<>();
		map18.put("name","??????");
		map18.put("java_field","descs");
		list.add(map18);

		Map<String,Object> map19 = new HashMap<>();
		map19.put("name","??????");
		map19.put("java_field","nation");
		list.add(map19);

		Map<String,Object> map20 = new HashMap<>();
		map20.put("name","?????????????????????");
		map20.put("java_field","yrUnit");
		list.add(map20);

		Map<String,Object> map21 = new HashMap<>();
		map21.put("name","???????????????");
		map21.put("java_field","yrName");
		list.add(map21);

		Map<String,Object> map22 = new HashMap<>();
		map22.put("name","?????????????????????");
		map22.put("java_field","yrPhone");
		list.add(map22);

		Map<String,Object> map23 = new HashMap<>();
		map23.put("name","???????????????");
		map23.put("java_field","unitPhone");
		list.add(map23);

		Map<String,Object> map24 = new HashMap<>();
		map24.put("name","???????????????");
		map24.put("java_field","emergencyName");
		list.add(map24);

		Map<String,Object> map25 = new HashMap<>();
		map25.put("name","?????????????????????");
		map25.put("java_field","emergencyPhone");
		list.add(map25);

		Map<String,Object> map26 = new HashMap<>();
		map26.put("name","??????");
		map26.put("java_field","file");
		list.add(map26);

		return list;
	}



	//???????????????????????????????????????key??????????????????value???????????????( ?????????????????? ?????? ????????????,????????????????????????)
	private  Map<String, List<Map<String, String>>> printImgs(Map<String, List<PictureData>> sheetList) throws IOException {
		Map<String, List<Map<String, String>>> pathMap = new HashMap();
		Object[] key = sheetList.keySet().toArray();
		for (int i = 0; i < sheetList.size(); i++) {
			List<Map<String, String>> list = new ArrayList<>();
			// ??????????????????
			String picName = key[i].toString();
			// ???????????????
			List<PictureData> pics = sheetList.get(key[i]);
			if( null != pics && pics.size() > 0){
				for (PictureData pic : pics) {
					InputStream sbs = new ByteArrayInputStream(pic.getData());
					Map<String, String> map = fileService.ExceluploadFile(pic.getData(),sbs);
					if(null !=  map){
						list.add(map);
						pathMap.put(picName,list);
					}
				}
				pathMap.put(picName,list);
			}
		}
		System.out.println(pathMap);
		return pathMap;
	}


	//???????????????????????????????????????key??????????????????value???????????????  ( ??????????????????)
	private  Map<String, List<Map<String, String>>> printImg(Map<String, PictureData> sheetList) throws Exception {
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, List<Map<String, String>>> pathMap = new HashMap();
		Object[] key = sheetList.keySet().toArray();

		for (int i = 0; i < sheetList.size(); i++) {
			// ???????????????
			PictureData pic = sheetList.get(key[i]);
			// ??????????????????
			String picName = key[i].toString();
			InputStream sbs = new ByteArrayInputStream(pic.getData());
			Map<String, String> map = fileService.ExceluploadFile(pic.getData(),sbs);

			if(null !=  map){
				list.add(map);
				pathMap.put(picName,list);
			}
		}
		System.out.println(pathMap);
		return pathMap;
	}


	@SneakyThrows
	@GetMapping("export-passbasicinfo")
	@ApiOperation(value = "????????????", notes = "?????? passBasicInfo")
	public void export(PassBasicInfoPagination passBasicInfo, HttpServletResponse response) {
		try{
			PassBasicInfoPagination entity= JsonUtil.getJsonToBean(passBasicInfo, PassBasicInfoPagination.class);
			List<PassBasicInfoExcelVO> list = passBasicInfoService.exportPassBasicInfo(entity);
			ExportExcel exportExcel = new ExportExcel();
			// ????????????
			List<SysDictItemEntity> personTypeList = remoteDictService.getDictByType("personType").getData();
			List<SysDictItemEntity> modeList = remoteDictService.getDictByType("mode").getData();
			List<SysDictItemEntity> cardTypeList = remoteDictService.getDictByType("cardType").getData();
			List<SysDictItemEntity> nationList = remoteDictService.getDictByType("nation").getData();
			List<SysDictItemEntity> personStateList = remoteDictService.getDictByType("passStatus").getData();
			exportExcel.personAllExport(minioTemplate,response,personTypeList,modeList,cardTypeList,nationList,personStateList,list);
		}catch (Exception e){
			System.out.println(e);
		}

	}

}
