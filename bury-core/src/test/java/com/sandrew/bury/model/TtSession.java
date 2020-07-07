/**
 *   自动生成的PO,不要手动修改
 *
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.Pack;
import com.sandrew.bury.bean.EqualPack;

import java.util.Date;

@TableName("tt_session")
public class TtSession extends PO
{

    public TtSession()
    {
    }


                            
    public TtSession(String sessionId)
    {
        if (null == this.sessionId)
        {
            this.sessionId = new EqualPack<String>();
        }
        this.sessionId.setValue(sessionId);
    }

    @ColumnName(value = "session_id", isPK = true, autoIncrement = false)
    private Pack<String> sessionId;

    @ColumnName(value = "session", isPK = false, autoIncrement = false)
    private Pack<String> session;

    @ColumnName(value = "create_by", isPK = false, autoIncrement = false)
    private Pack<Integer> createBy;

    @ColumnName(value = "create_date", isPK = false, autoIncrement = false)
    private Pack<Date> createDate;

    @ColumnName(value = "update_by", isPK = false, autoIncrement = false)
    private Pack<Integer> updateBy;

    @ColumnName(value = "update_date", isPK = false, autoIncrement = false)
    private Pack<Date> updateDate;


    public void setSessionId(String sessionId)
    {
        if (null == this.sessionId)
        {
            this.sessionId = new EqualPack<String>();
        }
        this.sessionId.setValue(sessionId);
    }

    public void setSessionId(Pack<String> sessionId)
    {
        this.sessionId = sessionId;
    }
        
    public Pack<String> getSessionId()
    {
        return this.sessionId;
    }

    public void setSession(String session)
    {
        if (null == this.session)
        {
            this.session = new EqualPack<String>();
        }
        this.session.setValue(session);
    }

    public void setSession(Pack<String> session)
    {
        this.session = session;
    }
        
    public Pack<String> getSession()
    {
        return this.session;
    }

    public void setCreateBy(Integer createBy)
    {
        if (null == this.createBy)
        {
            this.createBy = new EqualPack<Integer>();
        }
        this.createBy.setValue(createBy);
    }

    public void setCreateBy(Pack<Integer> createBy)
    {
        this.createBy = createBy;
    }
        
    public Pack<Integer> getCreateBy()
    {
        return this.createBy;
    }

    public void setCreateDate(Date createDate)
    {
        if (null == this.createDate)
        {
            this.createDate = new EqualPack<Date>();
        }
        this.createDate.setValue(createDate);
    }

    public void setCreateDate(Pack<Date> createDate)
    {
        this.createDate = createDate;
    }
        
    public Pack<Date> getCreateDate()
    {
        return this.createDate;
    }

    public void setUpdateBy(Integer updateBy)
    {
        if (null == this.updateBy)
        {
            this.updateBy = new EqualPack<Integer>();
        }
        this.updateBy.setValue(updateBy);
    }

    public void setUpdateBy(Pack<Integer> updateBy)
    {
        this.updateBy = updateBy;
    }
        
    public Pack<Integer> getUpdateBy()
    {
        return this.updateBy;
    }

    public void setUpdateDate(Date updateDate)
    {
        if (null == this.updateDate)
        {
            this.updateDate = new EqualPack<Date>();
        }
        this.updateDate.setValue(updateDate);
    }

    public void setUpdateDate(Pack<Date> updateDate)
    {
        this.updateDate = updateDate;
    }
        
    public Pack<Date> getUpdateDate()
    {
        return this.updateDate;
    }

}