package com.sinosoft.ie.booster.mqroute.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinosoft.ie.booster.admin.api.feign.RemoteBillRuleService;
import com.sinosoft.ie.booster.common.security.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.sinosoft.ie.booster.common.core.util.R;
import com.sinosoft.ie.booster.common.core.model.PageListVO;
import com.sinosoft.ie.booster.common.core.model.PaginationVO;
import com.sinosoft.ie.booster.common.security.model.BoosterUser;
import com.sinosoft.ie.booster.common.core.model.DownloadVO;
import com.sinosoft.ie.booster.visualdev.util.*;
import com.sinosoft.ie.booster.common.core.exception.DataException;
import org.springframework.transaction.annotation.Transactional;
import com.sinosoft.ie.booster.mqroute.model.consumer.ConsumerPaginationExportModel;
import com.sinosoft.ie.booster.mqroute.model.consumer.ConsumerPagination;
import com.sinosoft.ie.booster.mqroute.model.consumer.ConsumerCrForm;
import com.sinosoft.ie.booster.mqroute.model.consumer.ConsumerInfoVO;
import com.sinosoft.ie.booster.mqroute.model.consumer.ConsumerListVO;
import com.sinosoft.ie.booster.mqroute.model.consumer.ConsumerUpForm;
import com.sinosoft.ie.booster.mqroute.entity.*;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.visualdev.util.GeneraterSwapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sinosoft.ie.booster.mqroute.entity.ConsumerEntity;
import com.sinosoft.ie.booster.mqroute.service.ConsumerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 * consumer
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-16 15:50:58
 */
@Slf4j
@RestController
@Api(tags = "consumer")
@RequestMapping("/Consumer")
public class ConsumerController {
    @Autowired
    private GeneraterSwapUtil generaterSwapUtil;
    @Autowired
    private ConsumerService consumerservice;


    /**
     * 列表
     *
     * @param consumerPagination
     * @return
     */
    @GetMapping
    public R list( ConsumerPagination consumerPagination)throws IOException{
        List<ConsumerEntity> list= consumerservice.getList(consumerPagination);
        //处理id字段转名称，若无需转或者为空可删除

        List<ConsumerListVO> listVO=JsonUtil.getJsonToList(list,ConsumerListVO.class);
        PageListVO vo=new PageListVO();
        vo.setList(listVO);
        PaginationVO page=JsonUtil.getJsonToBean(consumerPagination,PaginationVO.class);
        vo.setPagination(page);
        return R.ok(vo);
    }

    /**
     * 创建
     *
     * @param consumerCrForm
     * @return
     */
    @PostMapping
    @Transactional
    public R create(@RequestBody @Valid ConsumerCrForm consumerCrForm) throws DataException {
        BoosterUser userInfo=SecurityUtils.getUser();
        ConsumerEntity entity=JsonUtil.getJsonToBean(consumerCrForm, ConsumerEntity.class);
        consumerservice.create(entity);
        return R.ok(null, "新建成功");
}


    /**
    * 信息
    *
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public R<ConsumerInfoVO> info(@PathVariable("id") Long id){
        ConsumerEntity entity= consumerservice.getInfo(id);
        ConsumerInfoVO vo=JsonUtil.getJsonToBean(entity, ConsumerInfoVO.class);
        return R.ok(vo);
    }



   /**
    * 更新
    *
    * @param id
    * @return
    */
    @PutMapping("/{id}")
    @Transactional
    public R update(@PathVariable("id") Long id,@RequestBody @Valid ConsumerUpForm consumerUpForm) throws DataException {
        ConsumerEntity entity= consumerservice.getInfo(id);
        if(entity!=null){
            consumerservice.delete(entity);
            BoosterUser userInfo = SecurityUtils.getUser();
            entity=JsonUtil.getJsonToBean(consumerUpForm, ConsumerEntity.class);
            entity.setId(id);
            consumerservice.create(entity);
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
    @DeleteMapping("/{id}")
    @Transactional
    public R delete(@PathVariable("id") Long id){
        ConsumerEntity entity= consumerservice.getInfo(id);
        if(entity!=null){
            consumerservice.delete(entity);
        }
        return R.ok(null, "删除成功");
    }

}
