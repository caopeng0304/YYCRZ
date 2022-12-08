package com.sinosoft.ie.booster.yypass.util;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UtilExcel {


	/**  支持单个图片
	 * 获取Excel2003图片
	 * @param sheet 当前sheet对象
	 * @param workbook 工作簿对象
	 * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
	 * @throws IOException
	 */
	public static Map<String, PictureData> getSheetPictrues03(HSSFSheet sheet, HSSFWorkbook workbook) {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		List<HSSFPictureData> pictures = workbook.getAllPictures();
		if (pictures.size() != 0) {
			for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
				HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
				if (shape instanceof HSSFPicture) {
					HSSFPicture pic = (HSSFPicture) shape;
					int pictureIndex = pic.getPictureIndex() - 1;
					HSSFPictureData picData = pictures.get(pictureIndex);
					String picIndex = String.valueOf(anchor.getRow1()) + "_" + String.valueOf(anchor.getCol1());

					map.put(picIndex, picData);
				}
			}
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 获取Excel2007图片
	 * @param sheetNum 当前sheet编号
	 * @param sheet 当前sheet对象
	 * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
	 */
	public static Map<String, PictureData> getSheetPictrues07(int sheetNum, XSSFSheet sheet) {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		for (POIXMLDocumentPart dr : sheet.getRelations()) {
			if (dr instanceof XSSFDrawing) {
				XSSFDrawing drawing = (XSSFDrawing) dr;
				List<XSSFShape> shapes = drawing.getShapes();
				for (XSSFShape shape : shapes) {
					XSSFPicture pic = (XSSFPicture) shape;
					XSSFClientAnchor anchor = pic.getPreferredSize();
					CTMarker ctMarker = anchor.getFrom();
					String picIndex = ctMarker.getRow() + "_" + ctMarker.getCol();
					map.put(picIndex, pic.getPictureData());
				}
			}
		}
		return map;
	}





	/**  支持多个图片
	 * 获取Excel2003图片
	 * @param sheet 当前sheet对象
	 * @param workbook 工作簿对象
	 * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
	 * @throws IOException
	 */
	public static Map<String,  List<PictureData>> getSheetPictrues03s(HSSFSheet sheet, HSSFWorkbook workbook) {
		Map<String, PictureData> map = new HashMap<String, PictureData>();
		Map<String, List<PictureData>> maps = new HashMap<String, List<PictureData>>();
		List<HSSFPictureData> pictures = workbook.getAllPictures();
		//List<PictureData> list = new ArrayList<>();
		if (pictures.size() != 0) {
			String picIndex = "";
			System.out.println(sheet.getDrawingPatriarch().getChildren());
			for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
				List<PictureData> list = new ArrayList<>();
				HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
				if (shape instanceof HSSFPicture) {
					HSSFPicture pic = (HSSFPicture) shape;
					int pictureIndex = pic.getPictureIndex() - 1;
					HSSFPictureData picData = pictures.get(pictureIndex);
					picIndex = String.valueOf(anchor.getRow1()) + "_" + String.valueOf(anchor.getCol1());
					//list.add(picData );
					List<PictureData> lists = maps.get(picIndex);
					if (lists != null && lists.size() > 0){
						maps.get(picIndex).add(picData);
					}else{
						list.add(picData);
						maps.put(picIndex, list);
					}
				}
				//maps.put(picIndex, list);
			}

			return maps;
		} else {
			return null;
		}
	}

}
