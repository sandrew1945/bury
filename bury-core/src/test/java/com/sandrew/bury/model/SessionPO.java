/**
 *   自动生成的PO,不要手动修改
 *
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import java.util.Date;

@TableName("tt_session")
public class SessionPO extends PO
{

    public SessionPO()
    {
    }

    @ColumnName(value = "session_id", isPK = true, autoIncrement = false)
    private String sessionId;

    @ColumnName(value = "session", isPK = false, autoIncrement = false)
    private String session;

    @ColumnName(value = "create_by", isPK = false, autoIncrement = false)
    private Integer createBy;

    @ColumnName(value = "create_date", isPK = false, autoIncrement = false)
    private Date createDate;

    @ColumnName(value = "update_by", isPK = false, autoIncrement = false)
    private Integer updateBy;

    @ColumnName(value = "update_date", isPK = false, autoIncrement = false)
    private Date updateDate;


    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }
        
    public String getSessionId()
    {
        return this.sessionId;
    }
    public void setSession(String session)
    {
        this.session = session;
    }
        
    public String getSession()
    {
        return this.session;
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