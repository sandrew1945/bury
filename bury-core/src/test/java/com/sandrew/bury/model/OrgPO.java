/**
 *   自动生成的PO,不要手动修改
 *
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import java.util.Date;

@TableName("TM_ORG")
public class OrgPO extends PO
{

    @ColumnName(value = "ORG_ID", isPK = true, autoIncrement = false)
    private Integer orgId;

    @ColumnName(value = "ORG_TYPE_ID", isPK = false, autoIncrement = false)
    private Integer orgTypeId;

    @ColumnName(value = "ORG_CODE", isPK = false, autoIncrement = false)
    private String orgCode;

    @ColumnName(value = "ORG_NAME", isPK = false, autoIncrement = false)
    private String orgName;

    @ColumnName(value = "PARENT_DEPT_ID", isPK = false, autoIncrement = false)
    private Integer parentDeptId;

    @ColumnName(value = "ORG_LEVEL", isPK = false, autoIncrement = false)
    private Integer orgLevel;

    @ColumnName(value = "STATUS", isPK = false, autoIncrement = false)
    private Integer status;

    @ColumnName(value = "REMARK", isPK = false, autoIncrement = false)
    private String remark;

    @ColumnName(value = "CREATE_BY", isPK = false, autoIncrement = false)
    private Integer createBy;

    @ColumnName(value = "CREATE_DATE", isPK = false, autoIncrement = false)
    private Date createDate;

    @ColumnName(value = "UPDATE_BY", isPK = false, autoIncrement = false)
    private Integer updateBy;

    @ColumnName(value = "UPDATE_DATE", isPK = false, autoIncrement = false)
    private Date updateDate;


    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }
        
    public Integer getOrgId()
    {
        return this.orgId;
    }
    public void setOrgTypeId(Integer orgTypeId)
    {
        this.orgTypeId = orgTypeId;
    }
        
    public Integer getOrgTypeId()
    {
        return this.orgTypeId;
    }
    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }
        
    public String getOrgCode()
    {
        return this.orgCode;
    }
    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }
        
    public String getOrgName()
    {
        return this.orgName;
    }
    public void setParentDeptId(Integer parentDeptId)
    {
        this.parentDeptId = parentDeptId;
    }
        
    public Integer getParentDeptId()
    {
        return this.parentDeptId;
    }
    public void setOrgLevel(Integer orgLevel)
    {
        this.orgLevel = orgLevel;
    }
        
    public Integer getOrgLevel()
    {
        return this.orgLevel;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
        
    public Integer getStatus()
    {
        return this.status;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
        
    public String getRemark()
    {
        return this.remark;
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