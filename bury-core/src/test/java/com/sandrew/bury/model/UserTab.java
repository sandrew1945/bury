/**
 *   自动生成的PO,不要手动修改
 *
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.bean.PO;

import java.util.Date;

@TableName("nesc_user_tab")
public class UserTab extends PO
{

    public UserTab()
    {
    }

                                            
    public UserTab(Integer userId)
    {
        this.userId = userId;
    }

    @ColumnName(value = "user_id", isPK = true, autoIncrement = true)
    private Integer userId;

    @ColumnName(value = "name", isPK = false, autoIncrement = false)
    private String name;

    @ColumnName(value = "code", isPK = false, autoIncrement = false)
    private String code;

    @ColumnName(value = "department_id", isPK = false, autoIncrement = false)
    private String departmentId;

    @ColumnName(value = "department_name", isPK = false, autoIncrement = false)
    private String departmentName;

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


    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
        
    public Integer getUserId()
    {
        return this.userId;
    }
    public void setName(String name)
    {
        this.name = name;
    }
        
    public String getName()
    {
        return this.name;
    }
    public void setCode(String code)
    {
        this.code = code;
    }
        
    public String getCode()
    {
        return this.code;
    }
    public void setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
    }
        
    public String getDepartmentId()
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