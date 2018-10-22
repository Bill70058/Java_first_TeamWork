package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: YunHai
 * @Date: 2018/10/5 20:15
 * @Description:
 */
public class Student implements Serializable{
//    个人id
    private String id;
//    姓名
    private String name;
//    密码
    private String password;
//    生日
    private String birthday;
//    个人简介
    private String information;
//    账号创建日期
    private Date createDate;
//    最后一次登录时间
    private Date lastLoginTime;
//    积分
    private int point;
//    所属小组id
    private String groupId;
//    个人权限
    private int level;

    public Student() { }

    public Student(String id, String password, String name, String birthday, String information, Date createDate, Date lastLoginTime, int point, String groupId, int level) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.information = information;
        this.createDate = createDate;
        this.lastLoginTime = lastLoginTime;
        this.point = point;
        this.groupId = groupId;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", information='" + information + '\'' +
                ", createDate=" + createDate +
                ", lastLoginTime=" + lastLoginTime +
                ", point=" + point +
                ", groupId='" + groupId + '\'' +
                ", level=" + level +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
