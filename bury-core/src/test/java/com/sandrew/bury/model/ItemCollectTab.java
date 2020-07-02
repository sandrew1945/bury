/**
 *   自动生成的PO,不要手动修改
 *
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.bean.PO;

import java.util.Date;

@TableName("nesc_item_collect_tab")
public class ItemCollectTab extends PO
{

    public ItemCollectTab()
    {
    }

                                                                                                                                                        
    public ItemCollectTab(Integer collentId)
    {
        this.collentId = collentId;
    }

    @ColumnName(value = "collent_id", isPK = true, autoIncrement = true)
    private Integer collentId;

    @ColumnName(value = "item_id", isPK = false, autoIncrement = false)
    private Integer itemId;

    @ColumnName(value = "item_name", isPK = false, autoIncrement = false)
    private String itemName;

    @ColumnName(value = "item_code", isPK = false, autoIncrement = false)
    private String itemCode;

    @ColumnName(value = "manager_id", isPK = false, autoIncrement = false)
    private Integer managerId;

    @ColumnName(value = "mamager_name", isPK = false, autoIncrement = false)
    private String mamagerName;

    @ColumnName(value = "department_id", isPK = false, autoIncrement = false)
    private Integer departmentId;

    @ColumnName(value = "department_name", isPK = false, autoIncrement = false)
    private String departmentName;

    @ColumnName(value = "resp_id", isPK = false, autoIncrement = false)
    private Integer respId;

    @ColumnName(value = "resp_name", isPK = false, autoIncrement = false)
    private String respName;

    @ColumnName(value = "line_id", isPK = false, autoIncrement = false)
    private Integer lineId;

    @ColumnName(value = "line_name", isPK = false, autoIncrement = false)
    private String lineName;

    @ColumnName(value = "source_base_id", isPK = false, autoIncrement = false)
    private String sourceBaseId;

    @ColumnName(value = "business_name", isPK = false, autoIncrement = false)
    private String businessName;

    @ColumnName(value = "item_type", isPK = false, autoIncrement = false)
    private Integer itemType;

    @ColumnName(value = "item_classify", isPK = false, autoIncrement = false)
    private Integer itemClassify;

    @ColumnName(value = "item_level", isPK = false, autoIncrement = false)
    private Integer itemLevel;

    @ColumnName(value = "formula", isPK = false, autoIncrement = false)
    private String formula;

    @ColumnName(value = "instructions", isPK = false, autoIncrement = false)
    private String instructions;

    @ColumnName(value = "begin_time", isPK = false, autoIncrement = false)
    private Date beginTime;

    @ColumnName(value = "end_time", isPK = false, autoIncrement = false)
    private Date endTime;

    @ColumnName(value = "times", isPK = false, autoIncrement = false)
    private Integer times;

    @ColumnName(value = "status", isPK = false, autoIncrement = false)
    private Integer status;

    @ColumnName(value = "resource", isPK = false, autoIncrement = false)
    private String resource;

    @ColumnName(value = "exception", isPK = false, autoIncrement = false)
    private String exception;

    @ColumnName(value = "from_business", isPK = false, autoIncrement = false)
    private String fromBusiness;

    @ColumnName(value = "from_fun", isPK = false, autoIncrement = false)
    private String fromFun;

    @ColumnName(value = "from_db", isPK = false, autoIncrement = false)
    private String fromDb;

    @ColumnName(value = "from_tab", isPK = false, autoIncrement = false)
    private String fromTab;

    @ColumnName(value = "from_field", isPK = false, autoIncrement = false)
    private String fromField;

    @ColumnName(value = "type", isPK = false, autoIncrement = false)
    private Integer type;

    @ColumnName(value = "describe", isPK = false, autoIncrement = false)
    private String describe;

    @ColumnName(value = "is_delete", isPK = false, autoIncrement = false)
    private Integer isDelete;

    @ColumnName(value = "create_by", isPK = false, autoIncrement = false)
    private Integer createBy;

    @ColumnName(value = "create_date", isPK = false, autoIncrement = false)
    private Date createDate;

    @ColumnName(value = "update_by", isPK = false, autoIncrement = false)
    private Integer updateBy;

    @ColumnName(value = "update_date", isPK = false, autoIncrement = false)
    private Date updateDate;


    public void setCollentId(Integer collentId)
    {
        this.collentId = collentId;
    }

    public Integer getCollentId()
    {
        return this.collentId;
    }
    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }
        
    public Integer getItemId()
    {
        return this.itemId;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
        
    public String getItemName()
    {
        return this.itemName;
    }
    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }
        
    public String getItemCode()
    {
        return this.itemCode;
    }
    public void setManagerId(Integer managerId)
    {
        this.managerId = managerId;
    }
        
    public Integer getManagerId()
    {
        return this.managerId;
    }
    public void setMamagerName(String mamagerName)
    {
        this.mamagerName = mamagerName;
    }
        
    public String getMamagerName()
    {
        return this.mamagerName;
    }
    public void setDepartmentId(Integer departmentId)
    {
        this.departmentId = departmentId;
    }
        
    public Integer getDepartmentId()
    {
        return this.departmentId;
    }
    public void setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
    }
        
    public String getDepartmentName()
    {
        return this.departmentName;
    }
    public void setRespId(Integer respId)
    {
        this.respId = respId;
    }
        
    public Integer getRespId()
    {
        return this.respId;
    }
    public void setRespName(String respName)
    {
        this.respName = respName;
    }
        
    public String getRespName()
    {
        return this.respName;
    }
    public void setLineId(Integer lineId)
    {
        this.lineId = lineId;
    }
        
    public Integer getLineId()
    {
        return this.lineId;
    }
    public void setLineName(String lineName)
    {
        this.lineName = lineName;
    }
        
    public String getLineName()
    {
        return this.lineName;
    }
    public void setSourceBaseId(String sourceBaseId)
    {
        this.sourceBaseId = sourceBaseId;
    }
        
    public String getSourceBaseId()
    {
        return this.sourceBaseId;
    }
    public void setBusinessName(String businessName)
    {
        this.businessName = businessName;
    }
        
    public String getBusinessName()
    {
        return this.businessName;
    }
    public void setItemType(Integer itemType)
    {
        this.itemType = itemType;
    }
        
    public Integer getItemType()
    {
        return this.itemType;
    }
    public void setItemClassify(Integer itemClassify)
    {
        this.itemClassify = itemClassify;
    }
        
    public Integer getItemClassify()
    {
        return this.itemClassify;
    }
    public void setItemLevel(Integer itemLevel)
    {
        this.itemLevel = itemLevel;
    }
        
    public Integer getItemLevel()
    {
        return this.itemLevel;
    }
    public void setFormula(String formula)
    {
        this.formula = formula;
    }
        
    public String getFormula()
    {
        return this.formula;
    }
    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    }
        
    public String getInstructions()
    {
        return this.instructions;
    }
    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }
        
    public Date getBeginTime()
    {
        return this.beginTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
        
    public Date getEndTime()
    {
        return this.endTime;
    }
    public void setTimes(Integer times)
    {
        this.times = times;
    }
        
    public Integer getTimes()
    {
        return this.times;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
        
    public Integer getStatus()
    {
        return this.status;
    }
    public void setResource(String resource)
    {
        this.resource = resource;
    }
        
    public String getResource()
    {
        return this.resource;
    }
    public void setException(String exception)
    {
        this.exception = exception;
    }
        
    public String getException()
    {
        return this.exception;
    }
    public void setFromBusiness(String fromBusiness)
    {
        this.fromBusiness = fromBusiness;
    }
        
    public String getFromBusiness()
    {
        return this.fromBusiness;
    }
    public void setFromFun(String fromFun)
    {
        this.fromFun = fromFun;
    }
        
    public String getFromFun()
    {
        return this.fromFun;
    }
    public void setFromDb(String fromDb)
    {
        this.fromDb = fromDb;
    }
        
    public String getFromDb()
    {
        return this.fromDb;
    }
    public void setFromTab(String fromTab)
    {
        this.fromTab = fromTab;
    }
        
    public String getFromTab()
    {
        return this.fromTab;
    }
    public void setFromField(String fromField)
    {
        this.fromField = fromField;
    }
        
    public String getFromField()
    {
        return this.fromField;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }
        
    public Integer getType()
    {
        return this.type;
    }
    public void setDescribe(String describe)
    {
        this.describe = describe;
    }
        
    public String getDescribe()
    {
        return this.describe;
    }
    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }
        
    public Integer getIsDelete()
    {
        return this.isDelete;
    }
    public void setCreateBy(Integer createBy)
    {
        this.createBy = createBy;
    }
        
    public Integer getCreateBy()
    {
        return this.createBy;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
        
    public Date getCreateDate()
    {
        return this.createDate;
    }
    public void setUpdateBy(Integer updateBy)
    {
        this.updateBy = updateBy;
    }
        
    public Integer getUpdateBy()
    {
        return this.updateBy;
    }
    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }
        
    public Date getUpdateDate()
    {
        return this.updateDate;
    }
}