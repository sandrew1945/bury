/**
 * 自动生成的PO,不要手动修改
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.bean.CommonPack;
import com.sandrew.bury.bean.PO;

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
    private CommonPack<String> sessionId = new CommonPack<>();

    @ColumnName(value = "session", isPK = false, autoIncrement = false)
    private CommonPack<String> session = new CommonPack<>();

    @ColumnName(value = "create_by", isPK = false, autoIncrement = false)
    private CommonPack<Integer> createBy = new CommonPack<>();

    @ColumnName(value = "create_date", isPK = false, autoIncrement = false)
    private CommonPack<Date> createDate = new CommonPack<>();

    @ColumnName(value = "update_by", isPK = false, autoIncrement = false)
    private CommonPack<Integer> updateBy = new CommonPack<>();

    @ColumnName(value = "update_date", isPK = false, autoIncrement = false)
    private CommonPack<Date> updateDate = new CommonPack<>();


    public void setSessionId(String sessionId)
    {
        this.sessionId.setValue(sessionId);
    }

    public void setSession(String session)
    {
        this.session.setValue(session);
    }

    public void setCreateBy(Integer createBy)
    {
        this.createBy.setValue(createBy);
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate.setValue(createDate);
    }

    public void setUpdateBy(Integer updateBy)
    {
        this.updateBy.setValue(updateBy);
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate.setValue(updateDate);
    }

    public void setSessionId(CommonPack<String> sessionId)
    {
        this.sessionId = sessionId;
    }

    public CommonPack<String> getSessionId()
    {
        return sessionId;
    }

    public CommonPack<String> getSession()
    {
        return session;
    }

    public void setSession(CommonPack<String> session)
    {
        this.session = session;
    }

    public CommonPack<Integer> getCreateBy()
    {
        return createBy;
    }

    public void setCreateBy(CommonPack<Integer> createBy)
    {
        this.createBy = createBy;
    }

    public CommonPack<Date> getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(CommonPack<Date> createDate)
    {
        this.createDate = createDate;
    }

    public CommonPack<Integer> getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(CommonPack<Integer> updateBy)
    {
        this.updateBy = updateBy;
    }

    public CommonPack<Date> getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(CommonPack<Date> updateDate)
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