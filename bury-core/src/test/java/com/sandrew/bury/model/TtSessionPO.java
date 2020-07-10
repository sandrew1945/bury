package com.sandrew.bury.model;

import com.sandrew.bury.bean.PO;

import java.util.Date;

public class TtSessionPO extends PO
{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_session.session_id
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    private String sessionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_session.create_by
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    private Integer createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_session.create_date
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_session.update_by
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    private Integer updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_session.update_date
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tt_session.session
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    private String session;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_session.session_id
     *
     * @return the value of tt_session.session_id
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_session.session_id
     *
     * @param sessionId the value for tt_session.session_id
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_session.create_by
     *
     * @return the value of tt_session.create_by
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_session.create_by
     *
     * @param createBy the value for tt_session.create_by
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_session.create_date
     *
     * @return the value of tt_session.create_date
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_session.create_date
     *
     * @param createDate the value for tt_session.create_date
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_session.update_by
     *
     * @return the value of tt_session.update_by
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_session.update_by
     *
     * @param updateBy the value for tt_session.update_by
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_session.update_date
     *
     * @return the value of tt_session.update_date
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_session.update_date
     *
     * @param updateDate the value for tt_session.update_date
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tt_session.session
     *
     * @return the value of tt_session.session
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public String getSession() {
        return session;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tt_session.session
     *
     * @param session the value for tt_session.session
     *
     * @mbg.generated Tue Feb 20 15:10:16 CST 2018
     */
    public void setSession(String session) {
        this.session = session;
    }
}