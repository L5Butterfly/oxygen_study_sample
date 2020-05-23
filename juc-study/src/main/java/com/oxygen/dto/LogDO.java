package com.oxygen.dto;


import java.util.Date;

/**
 * @author: empathy
 * @description:  日志记录
 * @date: 2019/11/6 15:42
 **/
public class LogDO {

    /**
     * 数据ID
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 业务日志编码
     */
    private String businessCode;

    /**
     * 记录ID
     */
    private String logId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 状态
     */
    private String status;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 操作说明
     */
    private String title;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 类型
     */
    private String type;


    // =============get and set method ============//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status ==null ? null : status.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title ==null ? null : title.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy==null ? null : createBy.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type==null ? null : type.trim();
    }
}
