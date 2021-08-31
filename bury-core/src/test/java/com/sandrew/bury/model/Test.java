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
import java.math.BigDecimal;

@TableName("tt_test")
public class Test extends PO
{

    public Test()
    {
    }


                                    

    public Test(Integer id,Integer sd)
    {
        if (null == this.id)
        {
            this.id = new EqualPack<Integer>();
        }
        this.id.setValue(id);
        if (null == this.sd)
        {
            this.sd = new EqualPack<Integer>();
        }
        this.sd.setValue(sd);
    }

    @ColumnName(value = "id", isPK = true, autoIncrement = true)
    private Pack<Integer> id;

    @ColumnName(value = "sd", isPK = true, autoIncrement = false)
    private Pack<Integer> sd;

    @ColumnName(value = "name", isPK = false, autoIncrement = false)
    private Pack<String> name;

    @ColumnName(value = "birthday", isPK = false, autoIncrement = false)
    private Pack<Date> birthday;

    @ColumnName(value = "login_time", isPK = false, autoIncrement = false)
    private Pack<Date> loginTime;

    @ColumnName(value = "blance", isPK = false, autoIncrement = false)
    private Pack<BigDecimal> blance;

    @ColumnName(value = "file", isPK = false, autoIncrement = false)
    private Pack<byte[]> file;


    public void setId(Integer id)
    {
        if (null == this.id)
        {
            this.id = new EqualPack<Integer>();
        }
        this.id.setValue(id);
    }

    public void setId(Pack<Integer> id)
    {
        this.id = id;
    }
        
    public Integer getId()
    {
        return this.id == null ? null : this.id.getValue();
    }

    public void setSd(Integer sd)
    {
        if (null == this.sd)
        {
            this.sd = new EqualPack<Integer>();
        }
        this.sd.setValue(sd);
    }

    public void setSd(Pack<Integer> sd)
    {
        this.sd = sd;
    }
        
    public Integer getSd()
    {
        return this.sd == null ? null : this.sd.getValue();
    }

    public void setName(String name)
    {
        if (null == this.name)
        {
            this.name = new EqualPack<String>();
        }
        this.name.setValue(name);
    }

    public void setName(Pack<String> name)
    {
        this.name = name;
    }
        
    public String getName()
    {
        return this.name == null ? null : this.name.getValue();
    }

    public void setBirthday(Date birthday)
    {
        if (null == this.birthday)
        {
            this.birthday = new EqualPack<Date>();
        }
        this.birthday.setValue(birthday);
    }

    public void setBirthday(Pack<Date> birthday)
    {
        this.birthday = birthday;
    }
        
    public Date getBirthday()
    {
        return this.birthday == null ? null : this.birthday.getValue();
    }

    public void setLoginTime(Date loginTime)
    {
        if (null == this.loginTime)
        {
            this.loginTime = new EqualPack<Date>();
        }
        this.loginTime.setValue(loginTime);
    }

    public void setLoginTime(Pack<Date> loginTime)
    {
        this.loginTime = loginTime;
    }
        
    public Date getLoginTime()
    {
        return this.loginTime == null ? null : this.loginTime.getValue();
    }

    public void setBlance(BigDecimal blance)
    {
        if (null == this.blance)
        {
            this.blance = new EqualPack<BigDecimal>();
        }
        this.blance.setValue(blance);
    }

    public void setBlance(Pack<BigDecimal> blance)
    {
        this.blance = blance;
    }
        
    public BigDecimal getBlance()
    {
        return this.blance == null ? null : this.blance.getValue();
    }

    public void setFile(byte[] file)
    {
        if (null == this.file)
        {
            this.file = new EqualPack<byte[]>();
        }
        this.file.setValue(file);
    }

    public void setFile(Pack<byte[]> file)
    {
        this.file = file;
    }
        
    public byte[] getFile()
    {
        return this.file == null ? null : this.file.getValue();
    }

}