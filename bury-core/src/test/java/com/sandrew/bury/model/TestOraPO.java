/**
 *   自动生成的PO,不要手动修改
 *
 */
package com.sandrew.bury.model;

import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import java.util.Date;

@TableName("TT_TEST_ORA")
public class TestOraPO extends PO
{

    @ColumnName(value = "ID", isPK = true, autoIncrement = false)
    private Integer id;

    @ColumnName(value = "NAME", isPK = false, autoIncrement = false)
    private String name;

    @ColumnName(value = "BIRTHDAY", isPK = false, autoIncrement = false)
    private Date birthday;

    @ColumnName(value = "ZIP_CODE", isPK = false, autoIncrement = false)
    private String zipCode;

    @ColumnName(value = "FILE_DATA", isPK = false, autoIncrement = false)
    private byte[] fileData;

    @ColumnName(value = "TEST_NUM", isPK = false, autoIncrement = false)
    private Integer testNum;


    public void setId(Integer id)
    {
        this.id = id;
    }
        
    public Integer getId()
    {
        return this.id;
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
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }
        
    public String getZipCode()
    {
        return this.zipCode;
    }
    public void setFileData(byte[] fileData)
    {
        this.fileData = fileData;
    }
        
    public byte[] getFileData()
    {
        return this.fileData;
    }
    public void setTestNum(Integer testNum)
    {
        this.testNum = testNum;
    }
        
    public Integer getTestNum()
    {
        return this.testNum;
    }
}