package dao;

import bean.Group;
import bean.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: YunHai
 * @Date: 2018/10/5 20:46
 * @Description:小组增删改查
 */
public interface GroupDao {
    /**
     * 通过小组id 获取小组数据
     * @param id: 小组id
     * @return: 小组数据
     */
    Group getGroupById(String id) throws IOException;

    /**
     * 通过小组id 查找并修改小组资料
     * @param group: 新的小组资料
     */
    void updateGroup(Group group) throws IOException;

    /**
     * 通过小组id 删除指定小组
     * @param id: 被删除的小组的id
     */
    void deleteGroup(String id) throws IOException;

    /**
     * 创建一个新的小组
     * @param group: 小组资料
     */
    void createGroup(Group group) throws IOException;

    /**
     * 获取所有小组信息
     * @return: 所有组的数据
     */
    List<Group> getAllGroup() throws IOException;

}
