package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: YunHai
 * @Date: 2018/10/5 20:31
 * @Description:
 */
public class Group {
//    成员id
    private List<String> memberId;
//    组长id
    private String captainId;

//    小组id
    private String id;
//    小组名
    private String name;
//    小组创建日期
    private Date createDate;
//    小组简介
    private String information;
//    小组口号
    private String slogan;
//    小组总积分
    private int allPoint;


    public Group() { }

    public Group(List<String> memberId, String captainId, String id, String name, Date createDate, String information, String slogan, int allPoint) {
        this.memberId = memberId;
        this.captainId = captainId;
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.information = information;
        this.slogan = slogan;
        this.allPoint = allPoint;
    }

    @Override
    public String toString() {
        return "Group{" +
                "memberId=" + memberId +
                ", captainId='" + captainId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", information='" + information + '\'' +
                ", slogan='" + slogan + '\'' +
                ", allPoint=" + allPoint +
                '}';
    }

    public List<String> getMemberId() {
        return memberId;
    }

    public void setMemberId(List<String> memberId) {
        this.memberId = memberId;
    }

    public String getCaptainId() {
        return captainId;
    }

    public void setCaptainId(String captainId) {
        this.captainId = captainId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getAllPoint() {
        return allPoint;
    }

    public void setAllPoint(int allPoint) {
        this.allPoint = allPoint;
    }
}
