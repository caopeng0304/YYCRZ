
package com.sinosoft.ie.booster.yypass.util;

import com.sinosoft.ie.booster.admin.api.entity.SysDictItemEntity;
import com.sinosoft.ie.booster.common.core.constant.CommonConstants;
import com.sinosoft.ie.booster.common.minio.service.MinioTemplate;
import com.sinosoft.ie.booster.yypass.model.passbasicinfo.PassBasicInfoExcelVO;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ExportExcel {

    /**
     * 值班人员数据列表
     */
   /* public static void exportPersonInfo(HttpServletResponse response, ICache<LibraryCacheKVEntity> icache, PersonInfo personInfo) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //查询统计数据
        HSSFSheet sheet = workbook.createSheet("网军信息");
        // 设置列宽
        sheet.setDefaultColumnWidth(14);

        // ==================表头样式=================================
        /// 标题样式
        HSSFCellStyle erStyle = createCellStyle(workbook,(short)13,true,true);
        //内容样式
        HSSFCellStyle cellStyle = createCellStyle(workbook,(short)12,false,true);

        // 合并单元格
        HSSFRow headRow = sheet.createRow(0);
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 6); // 网军个人信息
        sheet.addMergedRegion(region);


        HSSFCell headCell = headRow.createCell(0);
        headCell.setCellValue("网军个人信息");
        headCell.setCellStyle(cellStyle);

        HSSFRow second = sheet.createRow(1);
        HSSFCell nameCell = second.createCell(0);
        nameCell.setCellValue("姓名");
        nameCell.setCellStyle(cellStyle);

        HSSFCell nameCell_ = second.createCell(1);
        nameCell_.setCellValue(personInfo.getName());
        nameCell_.setCellStyle(cellStyle);

        HSSFCell mobileCell = second.createCell(2);
        mobileCell.setCellValue("手机");
        mobileCell.setCellStyle(cellStyle);

        HSSFCell mobileCell_ = second.createCell(3);
        mobileCell_.setCellValue(personInfo.getMobileNum());
        mobileCell_.setCellStyle(cellStyle);


        HSSFCell typeCell = second.createCell(4);
        typeCell.setCellValue("类别");
        typeCell.setCellStyle(cellStyle);

        HSSFCell typeCell_ = second.createCell(5);
        String category_ = CacheUtil.getLibraryName(icache,"category",personInfo.getClasses());
        typeCell_.setCellValue(category_);
        typeCell_.setCellStyle(cellStyle);

        CellRangeAddress imageAddress = new CellRangeAddress(1, 5, 6, 6); // 头像
        sheet.addMergedRegion(imageAddress);
        HSSFCell imageAddressCell_ = second.createCell(6);
        imageAddressCell_.setCellValue("头像");
        imageAddressCell_.setCellStyle(cellStyle);
        if (StringUtils.isNotEmpty(personInfo.getImageAddress())){
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            BufferedImage bufferImg = null;
            URL url = new URL(personInfo.getImageAddress());
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            bufferImg = ImageIO.read(is);
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            //anchor主要用于设置图片的属性
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 70, 255,(short) 6, 1, (short) 7, 5);
            //anchor.setAnchorType(3);
            //插入图片
            patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
        }

        HSSFRow third = sheet.createRow(2);
        HSSFCell organCell = third.createCell(0);
        organCell.setCellValue("地区");
        organCell.setCellStyle(cellStyle);

        HSSFCell organCell_ = third.createCell(1);
        organCell_.setCellValue(personInfo.getOrgName());
        organCell_.setCellStyle(cellStyle);

        HSSFCell unitCell = third.createCell(2);
        unitCell.setCellValue("单位");
        unitCell.setCellStyle(cellStyle);

        HSSFCell unitCell_ = third.createCell(3);
        unitCell_.setCellValue(personInfo.getUnitName());
        unitCell_.setCellStyle(cellStyle);


        HSSFCell emailCell = third.createCell(4);
        emailCell.setCellValue("邮箱");
        emailCell.setCellStyle(cellStyle);

        HSSFCell emailCell_ = third.createCell(5);
        emailCell_.setCellValue(personInfo.getMail());
        emailCell_.setCellStyle(cellStyle);


        HSSFRow fourth = sheet.createRow(3);

        HSSFCell sexCell = fourth.createCell(0);
        sexCell.setCellValue("性别");
        sexCell.setCellStyle(cellStyle);

        HSSFCell sexCell_ = fourth.createCell(1);
        String gender = personInfo.getGender();
        String gender_ =  CacheUtil.getLibraryName(icache,"gender",gender);
        sexCell_.setCellValue(gender_);
        sexCell_.setCellStyle(cellStyle);

        HSSFCell nationCell = fourth.createCell(2);
        nationCell.setCellValue("民族");
        nationCell.setCellStyle(cellStyle);

        HSSFCell nationCell_ = fourth.createCell(3);
        String nation = personInfo.getNation();
        String nation_ = CacheUtil.getLibraryName(icache,"nation",nation);
        nationCell_.setCellValue(nation_);
        nationCell_.setCellStyle(cellStyle);


        HSSFCell birthCell = fourth.createCell(4);
        birthCell.setCellValue("出生年月");
        birthCell.setCellStyle(cellStyle);

        HSSFCell birthCell_ = fourth.createCell(5);
        birthCell_.setCellValue(personInfo.getBirth());
        birthCell_.setCellStyle(cellStyle);


        HSSFRow fifth = sheet.createRow(4);

        HSSFCell politicsCell = fifth.createCell(0);
        politicsCell.setCellValue("政治面貌");
        politicsCell.setCellStyle(cellStyle);

        HSSFCell politicsCell_ = fifth.createCell(1);
        String politics = personInfo.getPolitics();
        String politics_ = CacheUtil.getLibraryName(icache,"politics",politics);

        politicsCell_.setCellValue(politics_);
        politicsCell_.setCellStyle(cellStyle);

        *//*CellRangeAddress major = new CellRangeAddress(4, 4, 2, 2); // 专业
        sheet.addMergedRegion(major);*//*
        HSSFCell majorCell = fifth.createCell(2);
        majorCell.setCellValue("专业");
        majorCell.setCellStyle(cellStyle);

        *//*CellRangeAddress major_ = new CellRangeAddress(4, 4, 3, 3); // 专业
        sheet.addMergedRegion(major_);*//*
        HSSFCell majorCell_ = fifth.createCell(3);
        majorCell_.setCellValue(personInfo.getMajor());
        majorCell_.setCellStyle(cellStyle);


        *//*CellRangeAddress position = new CellRangeAddress(4, 4, 4, 4); // 职务
        sheet.addMergedRegion(position);*//*
        HSSFCell positionCell = fifth.createCell(4);
        positionCell.setCellValue("职务");
        positionCell.setCellStyle(cellStyle);

        HSSFCell positionCell_ = fifth.createCell(5);
        String position = personInfo.getPosition();
        String position_ =  CacheUtil.getLibraryName(icache,"position",position);

        positionCell_.setCellValue(position_);
        positionCell_.setCellStyle(cellStyle);


        HSSFRow sixth = sheet.createRow(5);
        *//*CellRangeAddress identityCard = new CellRangeAddress(5, 5, 0, 0); // 身份证号
        sheet.addMergedRegion(identityCard);*//*
        HSSFCell identityCardCell = sixth.createCell(0);
        identityCardCell.setCellValue("身份证号");
        identityCardCell.setCellStyle(cellStyle);

        *//*CellRangeAddress identityCard_ = new CellRangeAddress(5, 5, 1, 1); // 身份证号
        sheet.addMergedRegion(identityCard_);*//*
        HSSFCell identityCardCell_ = sixth.createCell(1);
        identityCardCell_.setCellValue(personInfo.getIdentityCard());
        identityCardCell_.setCellStyle(cellStyle);

        *//*CellRangeAddress QQ = new CellRangeAddress(5, 5, 2, 2); // QQ号
        sheet.addMergedRegion(QQ);*//*
        HSSFCell QQCell = sixth.createCell(2);
        QQCell.setCellValue("QQ");
        QQCell.setCellStyle(cellStyle);

       *//* CellRangeAddress QQ_ = new CellRangeAddress(5, 5, 3, 3); // QQ号
        sheet.addMergedRegion(QQ_);*//*
        HSSFCell QQCell_ = sixth.createCell(3);
        QQCell_.setCellValue(personInfo.getQqNum());
        QQCell_.setCellStyle(cellStyle);


       *//* CellRangeAddress workFeatures = new CellRangeAddress(5, 5, 4, 4); // 工作特长
        sheet.addMergedRegion(workFeatures);*//*
        HSSFCell workFeaturesCell = sixth.createCell(4);
        workFeaturesCell.setCellValue("工作特长");
        workFeaturesCell.setCellStyle(cellStyle);

        HSSFCell workFeaturesCell_ = sixth.createCell(5);
        String speciality = personInfo.getWorkFeatures();
        String speciality_ = CacheUtil.getLibraryName(icache,"speciality",speciality);
        workFeaturesCell_.setCellValue(speciality_);
        workFeaturesCell_.setCellStyle(cellStyle);

        *//*CellRangeAddress imageAddress = new CellRangeAddress(1, 5, 6, 6); // 头像
        sheet.addMergedRegion(imageAddress);
        HSSFCell imageAddressCell_ = sixth.createCell(6);
        imageAddressCell_.setCellValue("头像");
        imageAddressCell_.setCellStyle(cellStyle);*//*


       *//* HSSFRow seventh = sheet.createRow(6);
        CellRangeAddress account = new CellRangeAddress(6, 6, 0, 6); // 身份证号
        sheet.addMergedRegion(account);
        HSSFCell accountInfoCell = seventh.createCell(0);
        accountInfoCell.setCellValue("账号信息");
        accountInfoCell.setCellStyle(cellStyle);*//*





       *//* HSSFRow firstRow = sheet.createRow(1);
        CellRangeAddress firstregion = new CellRangeAddress(1, 1, 0, 5);
        sheet.addMergedRegion(firstregion);
        HSSFCell headCell_ = firstRow.createCell(0);
        String times = "导出时间: "+sdf.format(new Date());
        headCell_.setCellValue(times);
        headCell_.setCellStyle(cellStyleRight);


        HSSFRow row = sheet.createRow(2);
        HSSFCell headCell0 = row.createCell(0);
        headCell0.setCellValue("序号");
        headCell0.setCellStyle(erStyle);

        HSSFCell headCell00 = row.createCell(1);
        headCell00.setCellValue("县市");
        headCell00.setCellStyle(erStyle);

        HSSFCell createCell = row.createCell(2);
        createCell.setCellValue("单位");
        createCell.setCellStyle(erStyle);

        HSSFCell createCell1 = row.createCell(3);
        createCell1.setCellValue("网评员");
        createCell1.setCellStyle(erStyle);

        HSSFCell createCell2 = row.createCell(4);
        createCell2.setCellValue("联系方式");
        createCell2.setCellStyle(erStyle);

        HSSFCell createCell3 = row.createCell(5);
        createCell3.setCellValue("完成情况");
        createCell3.setCellStyle(erStyle);

        int rownums=3;
        //创建表格
        int i = 0;
        for (TaskPersonRel tpr  : list) {
            i ++;
            HSSFRow rows = sheet.createRow(rownums);// 创建行
            HSSFCell cells0 = rows.createCell(0);
            cells0.setCellStyle(cellStyle);
            cells0.setCellValue(new HSSFRichTextString(i+""));

            HSSFCell cells00 = rows.createCell(1);
            cells00.setCellStyle(cellStyle);
            String name = tpr.getOrganName();
            cells00.setCellValue(new HSSFRichTextString(name));


            HSSFCell cells1 = rows.createCell(2);
            cells1.setCellStyle(cellStyle);
            String Unid = tpr.getUnid();
            cells1.setCellValue(Unid);

            HSSFCell cells2 = rows.createCell(3);
            cells2.setCellStyle(cellStyle);
            String personName = tpr.getPersonInfoName();
            cells2.setCellValue(personName);

            HSSFCell cells3 = rows.createCell(4);
            cells3.setCellStyle(cellStyle);
            String tel = tpr.getTel();
            cells3.setCellValue(tel);

            HSSFCell cells4 = rows.createCell(5);
            cells4.setCellStyle(cellStyle);
            String status = tpr.getPersonTaskStatus();
            String status_ = "";
            if ("0".equals(status)){
                status_ = "待接受";
            }else if ("1".equals(status)){
                status_ = "已接收";
            }else if ("2".equals(status)){
                status_ = "已完成";
            }else if ("3".equals(status)){
                status_ = "退回";
            }
            cells4.setCellValue(status_);
            ++rownums;
        }*//*
        response.setContentType("application/vnd..ms-excel");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(personInfo.getName()+".xls", "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }*/


    public void personAllExport(MinioTemplate minioTemplate,HttpServletResponse response, List<SysDictItemEntity> personTypeList,
								List<SysDictItemEntity> modeList,
								List<SysDictItemEntity> cardTypeList,
								List<SysDictItemEntity> nationList,
								List<SysDictItemEntity> personStateList,
								List<PassBasicInfoExcelVO> list) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //查询统计数据
        HSSFSheet sheet = workbook.createSheet("出入证");
        // 设置列宽
        sheet.setColumnWidth(0, 2*1000); //序号
        sheet.setColumnWidth(1, 2*3000);  //人员身份
        sheet.setColumnWidth(2, 2*3000);  // 姓名
        sheet.setColumnWidth(3, 2*1500);  //性别
		sheet.setColumnWidth(4, 2*1500);  // 民族
        sheet.setColumnWidth(5, 2*3000);  // 电话
        sheet.setColumnWidth(6, 2*2000);  // 证件类型
        sheet.setColumnWidth(7, 2*3000);  //  证件号
        sheet.setColumnWidth(8, 2*3000);  //  出生日期
        sheet.setColumnWidth(9, 2*3000);  // 开始日期
        sheet.setColumnWidth(10, 2*3000);  // 结束日期
        sheet.setColumnWidth(11, 2*3000);  // 紧急联系人
        sheet.setColumnWidth(12, 2*3000);  // 紧急联系人电话

        sheet.setColumnWidth(13, 2*3000);  // 项目负责人单位
		sheet.setColumnWidth(14, 2*3000);  // 项目负责人
		sheet.setColumnWidth(15, 2*3000);  // 项目负责人电话

		sheet.setColumnWidth(16, 2*4000);  // 申请单位
		sheet.setColumnWidth(17, 2*3000);  // 承办人
		sheet.setColumnWidth(18, 2*3000);  // 承办人电话
		sheet.setColumnWidth(19, 2*1500);  // 出入校模式
		sheet.setColumnWidth(20, 2*3000);  // 事由
		sheet.setColumnWidth(21, 2*3000);  // 居住地点
		sheet.setColumnWidth(22, 2*3000);  // 工作地点
		sheet.setColumnWidth(23, 2*3000);  // 备注
		sheet.setColumnWidth(24, 2*3000);  // 状态
		sheet.setColumnWidth(25, 2*3000);  // 出入校权限
		sheet.setColumnWidth(26, 2*3000);  // 审核人
		sheet.setColumnWidth(27, 2*3000);  // 发证日期
		sheet.setColumnWidth(28, 2*3000);  // 卡号
		sheet.setColumnWidth(29, 2*4000);  // 图片


        // ==================表头样式=================================
        /// 标题样式
        HSSFCellStyle erStyle = createCellStyle(workbook,(short)13,true,true);
        //内容样式
        HSSFCellStyle cellStyle = createCellStyle(workbook,(short)12,false,true);

        HSSFCellStyle cellStyleRight = createCellStyleRight(workbook,(short)12,false,true);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


        HSSFRow row = sheet.createRow(0);
        HSSFCell headCell0 = row.createCell(0);
        headCell0.setCellValue("序号");
        headCell0.setCellStyle(erStyle);

		HSSFCell headCell1 = row.createCell(1);
		headCell1.setCellValue("人员身份");
		headCell1.setCellStyle(erStyle);

		HSSFCell headCell2 = row.createCell(2);
		headCell2.setCellValue("姓名");
		headCell2.setCellStyle(erStyle);

		HSSFCell headCell3 = row.createCell(3);
		headCell3.setCellValue("性别");
		headCell3.setCellStyle(erStyle);

		HSSFCell headCell4 = row.createCell(4);
		headCell4.setCellValue("民族");
		headCell4.setCellStyle(erStyle);

		HSSFCell headCell5 = row.createCell(5);
		headCell5.setCellValue("电话");
		headCell5.setCellStyle(erStyle);

		HSSFCell headCell6 = row.createCell(6);
		headCell6.setCellValue("证件类型");
		headCell6.setCellStyle(erStyle);

		HSSFCell headCell7 = row.createCell(7);
		headCell7.setCellValue("证件号");
		headCell7.setCellStyle(erStyle);

		HSSFCell headCell8 = row.createCell(8);
		headCell8.setCellValue("出生日期");
		headCell8.setCellStyle(erStyle);

		HSSFCell headCell9 = row.createCell(9);
		headCell9.setCellValue("开始日期");
		headCell9.setCellStyle(erStyle);

		HSSFCell headCell10 = row.createCell(10);
		headCell10.setCellValue("结束日期");
		headCell10.setCellStyle(erStyle);

		HSSFCell headCell11 = row.createCell(11);
		headCell11.setCellValue("紧急联系人");
		headCell11.setCellStyle(erStyle);

		HSSFCell headCell12 = row.createCell(12);
		headCell12.setCellValue("紧急联系人电话");
		headCell12.setCellStyle(erStyle);

		HSSFCell headCell13 = row.createCell(13);
		headCell13.setCellValue("项目负责人单位");
		headCell13.setCellStyle(erStyle);

		HSSFCell headCell14 = row.createCell(14);
		headCell14.setCellValue("项目负责人");
		headCell14.setCellStyle(erStyle);

		HSSFCell headCell15 = row.createCell(15);
		headCell15.setCellValue("项目负责人电话");
		headCell15.setCellStyle(erStyle);

		HSSFCell headCell16 = row.createCell(16);
		headCell16.setCellValue("承办单位");
		headCell16.setCellStyle(erStyle);

		HSSFCell headCell17 = row.createCell(17);
		headCell17.setCellValue("承办人");
		headCell17.setCellStyle(erStyle);

		HSSFCell headCell18 = row.createCell(18);
		headCell18.setCellValue("承办人电话");
		headCell18.setCellStyle(erStyle);

		HSSFCell headCell19 = row.createCell(19);
		headCell19.setCellValue("出入校模式");
		headCell19.setCellStyle(erStyle);

		HSSFCell headCell20 = row.createCell(20);
		headCell20.setCellValue("事由");
		headCell20.setCellStyle(erStyle);

		HSSFCell headCell21 = row.createCell(21);
		headCell21.setCellValue("现居住地点");
		headCell21.setCellStyle(erStyle);

		HSSFCell headCell22 = row.createCell(22);
		headCell22.setCellValue("工作地点");
		headCell22.setCellStyle(erStyle);

		HSSFCell headCell23 = row.createCell(23);
		headCell23.setCellValue("备注");
		headCell23.setCellStyle(erStyle);

		HSSFCell headCell24 = row.createCell(24);
		headCell24.setCellValue("状态");
		headCell24.setCellStyle(erStyle);

		HSSFCell headCell25 = row.createCell(25);
		headCell25.setCellValue("出入校权限");
		headCell25.setCellStyle(erStyle);

		HSSFCell headCell26 = row.createCell(26);
		headCell26.setCellValue("审核人");
		headCell26.setCellStyle(erStyle);

		HSSFCell headCell27 = row.createCell(27);
		headCell27.setCellValue("发证日期");
		headCell27.setCellStyle(erStyle);

		HSSFCell headCell28 = row.createCell(28);
		headCell28.setCellValue("卡号");
		headCell28.setCellStyle(erStyle);

		HSSFCell headCell29 = row.createCell(29);
		headCell29.setCellValue("照片");
		headCell29.setCellStyle(erStyle);


        int rownums=1;
        //创建表格
        int i = 0;
		HSSFPatriarch drawingPatriarch = sheet.createDrawingPatriarch(); // 插入图
        for (PassBasicInfoExcelVO personInfos  : list) {
            i ++;
            HSSFRow rows = sheet.createRow(rownums);// 创建行
			if (StringUtils.isNotEmpty(personInfos.getFile())){
				rows.setHeight((short)2000);
			}
            HSSFCell cells0 = rows.createCell(0);
            cells0.setCellStyle(cellStyle);
            cells0.setCellValue(new HSSFRichTextString(i+""));

            HSSFCell cells1 = rows.createCell(1);   // 人员身份
			cells1.setCellStyle(cellStyle);
            String PersonType = personInfos.getPersonType();
            if (StringUtils.isNotEmpty(PersonType) && personTypeList != null && personTypeList.size() >0){
				List<SysDictItemEntity> p_type = personTypeList.stream().filter(s-> PersonType.equals(s.getValue())).collect(Collectors.toList());
				if (p_type != null && p_type.size() > 0){
					cells1.setCellValue(p_type.get(0).getLabel());
				}
			}


            HSSFCell cells2 = rows.createCell(2);  // 姓名
			cells2.setCellStyle(cellStyle);
			cells2.setCellValue(personInfos.getNames());

            HSSFCell cells3 = rows.createCell(3);  // 性别
			cells3.setCellStyle(cellStyle);
			cells3.setCellValue(personInfos.getSex());

			HSSFCell cells4 = rows.createCell(4);  // 民族
			cells4.setCellStyle(cellStyle);
			String nation = personInfos.getNation();
			if (StringUtils.isNotEmpty(nation) && nationList != null && nationList.size() > 0){
				List<SysDictItemEntity> n_type = nationList.stream().filter(s-> nation.equals(s.getValue())).collect(Collectors.toList());
				if (n_type != null && n_type.size() > 0){
					cells4.setCellValue(n_type.get(0).getLabel());
				}
			}

            HSSFCell cells5 = rows.createCell(5); //5 电话
			cells5.setCellStyle(cellStyle);
			cells5.setCellValue(personInfos.getPhone());

            HSSFCell cells6 = rows.createCell(6);  // 6 证件类型
			cells6.setCellStyle(cellStyle);
			String cardType = personInfos.getCardType();
			if (StringUtils.isNotEmpty(cardType) && cardTypeList != null && cardTypeList.size() > 0){
				List<SysDictItemEntity> c_type = cardTypeList.stream().filter(s-> cardType.equals(s.getValue())).collect(Collectors.toList());
				if (c_type != null && c_type.size() > 0){
					cells6.setCellValue(c_type.get(0).getLabel());
				}
			}

            HSSFCell cells7 = rows.createCell(7);  //7 证件号
			cells7.setCellStyle(cellStyle);
			cells7.setCellValue(personInfos.getCardNumber());

            HSSFCell cells8 = rows.createCell(8); //8 出生日期
			cells8.setCellStyle(cellStyle);
            if (personInfos.getBirthday() != null){
				cells8.setCellValue(simpleDateFormat.format(personInfos.getBirthday()));
			}

            HSSFCell cells9 = rows.createCell(9);  //9 开始日期
            cells9.setCellStyle(cellStyle);
            if (personInfos.getStartTime() != null){
				cells9.setCellValue(simpleDateFormat.format(personInfos.getStartTime()));
			}

            HSSFCell cells10 = rows.createCell(10);  //10 结束日期
            cells10.setCellStyle(cellStyle);
            if (personInfos.getEndTime() != null){
				cells10.setCellValue(simpleDateFormat.format(personInfos.getEndTime()));
			}

            HSSFCell cells11 = rows.createCell(11);  // 11 紧急联系人
            cells11.setCellStyle(cellStyle);
            cells11.setCellValue(personInfos.getEmergencyName());

            HSSFCell cells12 = rows.createCell(12);  //12 紧急联系人电话
            cells12.setCellStyle(cellStyle);
            cells12.setCellValue(personInfos.getEmergencyPhone());

            HSSFCell cells13 = rows.createCell(13);  //13 项目负责人单位
            cells13.setCellStyle(cellStyle);
            cells13.setCellValue(personInfos.getYrUnit());

			HSSFCell cells14 = rows.createCell(14);  //14 项目负责人
			cells14.setCellStyle(cellStyle);
			cells14.setCellValue(personInfos.getYrName());

			HSSFCell cells15 = rows.createCell(15);  //15 项目负责人电话
			cells15.setCellStyle(cellStyle);
			cells15.setCellValue(personInfos.getYrPhone());

			HSSFCell cells16 = rows.createCell(16);  //16 申请单位
			cells16.setCellStyle(cellStyle);
			cells16.setCellValue(personInfos.getUnit());

			HSSFCell cells17 = rows.createCell(17);  //17 承办人
			cells17.setCellStyle(cellStyle);
			cells17.setCellValue(personInfos.getUnitName());

			HSSFCell cells18 = rows.createCell(18);  //18 承办人电话
			cells18.setCellStyle(cellStyle);
			cells18.setCellValue(personInfos.getUnitPhone());

			HSSFCell cells19 = rows.createCell(19);  //19 出入校模式
			cells19.setCellStyle(cellStyle);
			String modes = personInfos.getModes();
			if (StringUtils.isNotEmpty(modes) && modeList != null && modeList.size() > 0){
				List<SysDictItemEntity> m_type = modeList.stream().filter(s-> modes.equals(s.getValue())).collect(Collectors.toList());
				if (m_type != null && m_type.size() > 0){
					cells19.setCellValue(m_type.get(0).getLabel());
				}
			}

			HSSFCell cells20 = rows.createCell(20);  //20 事由
			cells20.setCellStyle(cellStyle);
			cells20.setCellValue(personInfos.getReason());

			HSSFCell cells21 = rows.createCell(21);  //21 居住地点
			cells21.setCellStyle(cellStyle);
			cells21.setCellValue(personInfos.getAddress());

			HSSFCell cells22 = rows.createCell(22);  //22 工作地点
			cells22.setCellStyle(cellStyle);
			cells22.setCellValue(personInfos.getWorkAddress());

			HSSFCell cells23 = rows.createCell(23);  //23 备注
			cells23.setCellStyle(cellStyle);
			cells23.setCellValue(personInfos.getDescs());

			HSSFCell cells24 = rows.createCell(24);  // 24 状态
			cells24.setCellStyle(cellStyle);
			String personState = personInfos.getPersonState();
			String isGrantFace = personInfos.getIsGrantFace();
			if (StringUtils.isNotEmpty(personState) && personStateList != null && personStateList.size() >0 ){
				List<SysDictItemEntity> ps_type = personStateList.stream().filter(s-> personState.equals(s.getValue())).collect(Collectors.toList());
				if ("4".equals(personState) || "10".equals(personState) || "19".equals(personState) ){
                    if ("0".equals(isGrantFace)){
						cells24.setCellValue("已授权");
					}else if ("1".equals(isGrantFace)){
						cells24.setCellValue("未授权");
					}else{
						cells24.setCellValue(ps_type.get(0).getLabel());
					}
				}else{
					if (ps_type != null && ps_type.size() > 0){
						cells24.setCellValue(ps_type.get(0).getLabel());
					}
				}

			}

			HSSFCell cells25 = rows.createCell(25);  //出入校权限
			cells25.setCellStyle(cellStyle);
			if ("0".equals(personInfos.getIsInout())){
				cells25.setCellValue("有");
			}else if ("1".equals(personInfos.getIsInout())){
				cells25.setCellValue("无");
			}else{
				cells25.setCellValue("无");
			}


			HSSFCell cells26 = rows.createCell(26);  //审核人
			cells26.setCellStyle(cellStyle);
			cells26.setCellValue(personInfos.getAuditName());

			HSSFCell cells27 = rows.createCell(27);  //发证日期
			cells27.setCellStyle(cellStyle);
			if (personInfos.getGetPassTime() != null){
				cells27.setCellValue(simpleDateFormat.format(personInfos.getGetPassTime()));
			}

			HSSFCell cells28 = rows.createCell(28);  //卡号
			cells28.setCellStyle(cellStyle);
			cells28.setCellValue(personInfos.getIdCard());

			HSSFCell cells29 = rows.createCell(29);  //照片
			cells29.setCellStyle(cellStyle);

			if (StringUtils.isNotEmpty(personInfos.getFile())){
				picture(drawingPatriarch,minioTemplate,workbook,sheet, personInfos.getFile(), ".png",i,29);
			}



			++rownums;
        }


        response.setContentType("application/vnd..ms-excel");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("出入证.xls", "utf-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


    private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize, boolean flag, boolean flag1) {
        // TODO Auto-generated method stub
        HSSFCellStyle style = workbook.createCellStyle();


        //是否水平居中
        if(flag1){
            style.setAlignment(HorizontalAlignment.CENTER);//水平居中

        }
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中 VERTICAL_CENTER
        //创建字体
        HSSFFont font = workbook.createFont();
        //是否加粗字体
        if(flag){
            //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            //font.setBold(HSSFFont.BOLDWEIGHT_BOLD);
        }
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);
        return style;
    }

    private static HSSFCellStyle createCellStyleRight(HSSFWorkbook workbook, short fontsize, boolean flag, boolean flag1) {
        // TODO Auto-generated method stub
        HSSFCellStyle style = workbook.createCellStyle();
        //是否水平居中
		if(flag1){
			style.setAlignment(HorizontalAlignment.CENTER);//水平居中

		}
		style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中 VERTICAL_CENTER
        //创建字体
        HSSFFont font = workbook.createFont();
        //是否加粗字体
        if(flag){
            //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//font.setBold(HSSFFont.BOLDWEIGHT_BOLD);
        }
        font.setFontHeightInPoints(fontsize);
        //加载字体
        style.setFont(font);
        return style;
    }

    //将图片插入到指定的单元格中 HSSFWorkbook
    public static void picture(HSSFPatriarch drawingPatriarch,MinioTemplate minioTemplate,HSSFWorkbook workbook,HSSFSheet sheet, String fileUrl, String fileType,int row, int col) {
        try {

			InputStream is = minioTemplate.getObject(CommonConstants.BUCKET_NAME, fileUrl);
			if(is != null ){
				ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
				BufferedImage bufferImg = ImageIO.read(is);
				if (bufferImg != null){
					if (fileType.equals(".jpg")) {
						ImageIO.write(bufferImg, "jpg", byteArrayOut);
					} else if (fileType.equals(".png")) {
						ImageIO.write(bufferImg, "png", byteArrayOut);
					}

					HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
					//anchor主要用于设置图片的属性
					HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 100, 90,(short) col, row, (short) (col + 1), row + 1);
					anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
					//插入图片
					patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

				}
			}

           } catch (Exception e) {
            e.printStackTrace();
        }
    }






}

