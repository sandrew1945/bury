/**
 * 自动生成的PO,不要手动修改
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.bean.CommonPack;
import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.Pack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;

@TableName("tt_session")
public class Session extends PO
{

    public Session()
    {
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
            this.sessionId = new CommonPack<String>();
        }
        this.sessionId.setValue(sessionId);
    }

    public void setSession(String session)
    {
        if (null == this.session)
        {
            this.session = new CommonPack<String>();
        }
        this.session.setValue(session);
    }

    public void setCreateBy(Integer createBy)
    {
        if (null == this.createBy)
        {
            this.createBy = new CommonPack<Integer>();
        }
        this.createBy.setValue(createBy);
    }

    public void setCreateDate(Date createDate)
    {
        if (null == this.createDate)
        {
            this.createDate = new CommonPack<Date>();
        }
        this.createDate.setValue(createDate);
    }

    public void setUpdateBy(Integer updateBy)
    {
        if (null == this.updateBy)
        {
            this.updateBy = new CommonPack<Integer>();
        }
        this.updateBy.setValue(updateBy);
    }

    public void setUpdateDate(Date updateDate)
    {
        if (null == this.updateDate)
        {
            this.updateDate = new CommonPack<Date>();
        }
        this.updateDate.setValue(updateDate);
    }


    public void setSessionId(Pack<String> sessionId)
    {
        this.sessionId = sessionId;
    }

    public Pack<String> getSessionId()
    {
        return sessionId;
    }

    public Pack<String> getSession()
    {
        return session;
    }

    public void setSession(Pack<String> session)
    {
        this.session = session;
    }

    public Pack<Integer> getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(Pack<Integer> createBy)
    {
        this.createBy = createBy;
    }

    public Pack<Date> getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Pack<Date> createDate)
    {
        this.createDate = createDate;
    }

    public Pack<Integer> getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(Pack<Integer> updateBy)
    {
        this.updateBy = updateBy;
    }

    public Pack<Date> getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Pack<Date> updateDate)
    {
        this.updateDate = updateDate;
    }



    public static void main(String[] args)
    {
        CommonPack<String> foo = new CommonPack<String>(){};
        // 在类的外部这样获取
        Type type = ((ParameterizedType)foo.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(type);
        // 在类的内部这样获取
        //System.out.println(foo.getTClass());
    }
}