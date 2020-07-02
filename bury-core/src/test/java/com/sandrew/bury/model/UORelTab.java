/**
 *   自动生成的PO,不要手动修改
 *
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.bean.PO;

import java.util.Date;

@TableName("nesc_uo_rel_tab")
public class UORelTab extends PO
{

    public UORelTab()
    {
    }

                            
    public UORelTab(Integer id)
    {
        this.id = id;
    }

    @ColumnName(value = "id", isPK = true, autoIncrement = false)
    private Integer id;

    @ColumnName(value = "item_id", isPK = false, autoIncrement = false)
    private Integer itemId;

    @ColumnName(value = "uo_id", isPK = false, autoIncrement = false)
    private Integer uoId;

    @ColumnName(value = "rel_type", isPK = false, autoIncrement = false)
    private Integer relType;

    @ColumnName(value = "create_by", isPK = false, autoIncrement = false)
    private Integer createBy;

    @ColumnName(value = "create_date", isPK = false, autoIncrement = false)
    private Date createDate;


    public void setId(Integer id)
    {
        this.id = id;
    }
        
    public Integer getId()
    {
        return this.id;
    }
    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }
        
    public Integer getItemId()
    {
        return this.itemId;
    }
    public void setUoId(Integer uoId)
    {
        this.uoId = uoId;
    }
        
    public Integer getUoId()
    {
        return this.uoId;
    }
    public void setRelType(Integer relType)
    {
        this.relType = relType;
    }
        
    public Integer getRelType()
    {
        return this.relType;
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
}