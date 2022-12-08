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
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerPaginationExportModel;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerPagination;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerCrForm;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerInfoVO;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerListVO;
import com.sinosoft.ie.booster.mqroute.model.producer.ProducerUpForm;
import com.sinosoft.ie.booster.mqroute.entity.*;
import com.sinosoft.ie.booster.common.core.util.JsonUtil;
import com.sinosoft.ie.booster.common.core.model.ListVO;
import com.sinosoft.ie.booster.visualdev.util.GeneraterSwapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import com.sinosoft.ie.booster.mqroute.entity.ProducerEntity;
import com.sinosoft.ie.booster.mqroute.service.ProducerService;
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
 * producer
 * @版本： V1.0.0
 * @作者： booster开发平台组
 * @日期： 2022-03-16 15:53:23
 */
@Slf4j
@RestController
@Api(tags = "producer")
@RequestMapping("/Producer")
public class ProducerController {
    @Autowired
    private GeneraterSwapUtil generaterSwapUtil;
    @Autowired
    private ProducerService producerservice;


    /**
     * 列表
     *
     * @param producerPagination
     * @return
     */
    @GetMapping
    public R list( ProducerPagination producerPagination)throws IOException{
        List<ProducerEntity> list= producerservice.getList(producerPagination);
        //处理id字段转名称，若无需转或者为空可删除

        List<ProducerListVO> listVO=JsonUtil.getJsonToList(list,ProducerListVO.class);
        PageListVO vo=new PageListVO();
        vo.setList(listVO);
        PaginationVO page=JsonUtil.getJsonToBean(producerPagination,PaginationVO.class);
        vo.setPagination(page);
        return R.ok(vo);
    }

    /**
     * 创建
     *
     * @param producerCrForm
     * @return
     */
    @PostMapping
    @Transactional
    public R create(@RequestBody @Valid ProducerCrForm producerCrForm) throws DataException {
        BoosterUser userInfo=SecurityUtils.getUser();
        ProducerEntity entity=JsonUtil.getJsonToBean(producerCrForm, ProducerEntity.class);
        producerservice.create(entity);
        return R.ok(null, "新建成功");
}


    /**
    * 信息
    *
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public R<ProducerInfoVO> info(@PathVariable("id") Long id){
        ProducerEntity entity= producerservice.getInfo(id);
        ProducerInfoVO vo=JsonUtil.getJsonToBean(entity, ProducerInfoVO.class);
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
    public R update(@PathVariable("id") Long id,@RequestBody @Valid ProducerUpForm producerUpForm) throws DataException {
        ProducerEntity entity= producerservice.getInfo(id);
        if(entity!=null){
            producerservice.delete(entity);
            BoosterUser userInfo = SecurityUtils.getUser();
            entity=JsonUtil.getJsonToBean(producerUpForm, ProducerEntity.class);
            entity.setId(id);
            producerservice.create(entity);
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
        ProducerEntity entity= producerservice.getInfo(id);
        if(entity!=null){
            producerservice.delete(entity);
        }
        return R.ok(null, "删除成功");
    }

}
