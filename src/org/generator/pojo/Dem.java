package org.generator.pojo;

public class Dem {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.id
     *
     * @mbg.generated Tue Dec 08 15:48:50 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_department.dept_name
     *
     * @mbg.generated Tue Dec 08 15:48:50 CST 2020
     */
    private String deptName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.id
     *
     * @return the value of tb_department.id
     *
     * @mbg.generated Tue Dec 08 15:48:50 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.id
     *
     * @param id the value for tb_department.id
     *
     * @mbg.generated Tue Dec 08 15:48:50 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_department.dept_name
     *
     * @return the value of tb_department.dept_name
     *
     * @mbg.generated Tue Dec 08 15:48:50 CST 2020
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_department.dept_name
     *
     * @param deptName the value for tb_department.dept_name
     *
     * @mbg.generated Tue Dec 08 15:48:50 CST 2020
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}