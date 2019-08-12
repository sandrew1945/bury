/**
 *   自动生成的PO,不要手动修改
 *
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import java.util.Date;
import java.math.BigDecimal;

@TableName("tt_test")
public class TestPO extends PO
{

    public TestPO()
    {
    }

                                    
    public TestPO(Integer id,Integer sd)
    {
        this.id = id;
        this.sd = sd;
    }

    @ColumnName(value = "id", isPK = true, autoIncrement = true)
    private Integer id;

    @ColumnName(value = "sd", isPK = true, autoIncrement = false)
    private Integer sd;

    @ColumnName(value = "name", isPK = false, autoIncrement = false)
    private String name;

    @ColumnName(value = "birthday", isPK = false, autoIncrement = false)
    private Date birthday;

    @ColumnName(value = "login_time", isPK = false, autoIncrement = false)
    private Date loginTime;

    @ColumnName(value = "blance", isPK = false, autoIncrement = false)
    private BigDecimal blance;

    @ColumnName(value = "file", isPK = false, autoIncrement = false)
    private byte[] file;


    public void setId(Integer id)
    {
        this.id = id;
    }
        
    public Integer getId()
    {
        return this.id;
    }
    public void setSd(Integer sd)
    {
        this.sd = sd;
    }
        
    public Integer getSd()
    {
        return this.sd;
    }
    public void setName(String name)
    {
        this.name = name;
    }
        
    public String getName()
    {
        return this.name;
    }
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }
        
    public Date getBirthday()
    {
        return this.birthday;
    }
    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }
        
    public Date getLoginTime()
    {
        return this.loginTime;
    }
    public void setBlance(BigDecimal blance)
    {
        this.blance = blance;
    }
        
    public BigDecimal getBlance()
    {
        return this.blance;
    }
    public void setFile(byte[] file)
    {
        this.file = file;
    }
        
    public byte[] getFile()
    {
        return this.file;
    }
}