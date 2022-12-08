package com.sinosoft.ie.booster.yypass.model.passbasicinfo;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.sinosoft.ie.booster.yypass.service.DataTransferService;
import com.sinosoft.ie.booster.yypass.service.PassBasicInfoService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * UserImportListener
 *
 * @author haocy
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DataTransferImportListener extends AnalysisEventListener<DataTransferExcelVO> {

	/**
	 * 默认每隔3000条存储数据库
	 */
	private int batchCount = 3000;
	/**
	 * 缓存的数据列表
	 */
	private List<DataTransferExcelVO> list = new ArrayList<>();
	/**
	 * 用户service
	 */
	private final DataTransferService dataTransferService;

	@Override
	public void invoke(DataTransferExcelVO data, AnalysisContext context) {
		System.out.println("----------------------------------------------");
		System.out.println(data);
		list.add(data);
		// 达到BATCH_COUNT，则调用importer方法入库，防止数据几万条数据在内存，容易OOM
		if (list.size() >= batchCount) {
			// 调用importer方法
			dataTransferService.importPassBasicInfo(list);
			// 存储完成清理list
			list.clear();
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		// 调用importer方法
		dataTransferService.importPassBasicInfo(list);
		// 存储完成清理list
		list.clear();
	}

}
