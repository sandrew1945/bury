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


@TableName("tt_test")
public class TtTest extends PO
{

    public TtTest()
    {
    }


                
    public TtTest(Integer id)
    {
        if (null == this.id)
        {
            this.id = new EqualPack<Integer>();
        }
        this.id.setValue(id);
    }

    @ColumnName(value = "id", isPK = true, autoIncrement = true)
    private Pack<Integer> id;

    @ColumnName(value = "sd", isPK = false, autoIncrement = false)
    private Pack<Integer> sd;

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
        
    public Pack<Integer> getId()
    {
        return this.id;
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
        
    public Pack<Integer> getSd()
    {
        return this.sd;
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
        
    public Pack<byte[]> getFile()
    {
        return this.file;
    }

}