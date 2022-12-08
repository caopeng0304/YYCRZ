package com.sinosoft.ie.booster.workflow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 差旅报销申请表
 *
 * @author booster code generator
 * @since 2021-09-26
 */
@Data
@TableName("form_travel_reimbursement")
@EqualsAndHashCode()
@ApiModel(value = "差旅报销申请表")
public class FormTravelReimbursementEntity implements Serializable {

	/**
	 * 自然主键
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "自然主键")
	private Long id;
	/**
	 * 流程主键
	 */
	@NotEmpty(message = "流程主键必须填写")
	@ApiModelProperty(value = "流程主键")
	private Long flowId;
	/**
	 * 流程标题
	 */
	@NotEmpty(message = "流程标题必须填写")
	@ApiModelProperty(value = "流程标题")
	private String flowTitle;
	/**
	 * 流程等级
	 */
	@NotNull(message = "流程等级必须填写")
	@ApiModelProperty(value = "流程等级")
	private Integer flowUrgent;
	/**
	 * 流程单据
	 */
	@NotEmpty(message = "流程单据必须填写")
	@ApiModelProperty(value = "流程单据")
	private String billNo;
	/**
	 * 申请人
	 */
	@NotEmpty(message = "申请人必须填写")
	@ApiModelProperty(value = "申请人")
	private String applyUser;
	/**
	 * 申请部门
	 */
	@NotEmpty(message = "申请部门必须填写")
	@ApiModelProperty(value = "申请部门")
	private String departmental;
	/**
	 * 票据数
	 */
	@NotEmpty(message = "票据数必须填写")
	@ApiModelProperty(value = "票据数")
	private String billsNum;
	/**
	 * 出差任务
	 */
	@NotEmpty(message = "出差任务必须填写")
	@ApiModelProperty(value = "出差任务")
	private String businessMission;
	/**
	 * 出发日期
	 */
	@NotNull(message = "出发日期必须填写")
	@ApiModelProperty(value = "出发日期")
	private LocalDateTime setoutDate;
	/**
	 * 回归日期
	 */
	@NotNull(message = "回归日期必须填写")
	@ApiModelProperty(value = "回归日期")
	private LocalDateTime returnDate;
	/**
	 * 到达地
	 */
	@NotEmpty(message = "到达地必须填写")
	@ApiModelProperty(value = "到达地")
	private String destination;
	/**
	 * 机票费
	 */
	@NotNull(message = "机票费必须填写")
	@ApiModelProperty(value = "机票费")
	private BigDecimal planeTicket;
	/**
	 * 车船费
	 */
	@NotNull(message = "车船费必须填写")
	@ApiModelProperty(value = "车船费")
	private BigDecimal fare;
	/**
	 * 住宿费用
	 */
	@NotNull(message = "住宿费用必须填写")
	@ApiModelProperty(value = "住宿费用")
	private BigDecimal getAccommodation;
	/**
	 * 出差补助
	 */
	@NotNull(message = "出差补助必须填写")
	@ApiModelProperty(value = "出差补助")
	private BigDecimal travelAllowance;
	/**
	 * 其他费用
	 */
	@NotNull(message = "其他费用必须填写")
	@ApiModelProperty(value = "其他费用")
	private BigDecimal other;
	/**
	 * 合计
	 */
	@NotNull(message = "合计必须填写")
	@ApiModelProperty(value = "合计")
	private BigDecimal total;
	/**
	 * 报销金额
	 */
	@NotNull(message = "报销金额必须填写")
	@ApiModelProperty(value = "报销金额")
	private BigDecimal reimbursementAmount;
	/**
	 * 借款金额
	 */
	@NotNull(message = "借款金额必须填写")
	@ApiModelProperty(value = "借款金额")
	private BigDecimal loanAmount;
	/**
	 * 补找金额
	 */
	@NotNull(message = "补找金额必须填写")
	@ApiModelProperty(value = "补找金额")
	private BigDecimal sumOfMoney;
	/**
	 * travelerUser
	 */
	@NotEmpty(message = "travelerUser必须填写")
	@ApiModelProperty(value = "travelerUser")
	private String travelerUser;
	/**
	 * 车辆里程
	 */
	@NotNull(message = "车辆里程必须填写")
	@ApiModelProperty(value = "车辆里程")
	private BigDecimal vehicleMileage;
	/**
	 * 过路费
	 */
	@NotNull(message = "过路费必须填写")
	@ApiModelProperty(value = "过路费")
	private BigDecimal roadFee;
	/**
	 * 停车费
	 */
	@NotNull(message = "停车费必须填写")
	@ApiModelProperty(value = "停车费")
	private BigDecimal parkingRate;
	/**
	 * 餐补费用
	 */
	@NotNull(message = "餐补费用必须填写")
	@ApiModelProperty(value = "餐补费用")
	private BigDecimal mealAllowance;
	/**
	 * 故障报修费
	 */
	@NotNull(message = "故障报修费必须填写")
	@ApiModelProperty(value = "故障报修费")
	private BigDecimal breakdownFee;
	/**
	 * 报销编号
	 */
	@NotEmpty(message = "报销编号必须填写")
	@ApiModelProperty(value = "报销编号")
	private String reimbursementId;
	/**
	 * 轨道交通费
	 */
	@NotNull(message = "轨道交通费必须填写")
	@ApiModelProperty(value = "轨道交通费")
	private BigDecimal railTransit;
	/**
	 * 申请时间
	 */
	@NotNull(message = "申请时间必须填写")
	@ApiModelProperty(value = "申请时间")
	private LocalDateTime applyDate;
}
