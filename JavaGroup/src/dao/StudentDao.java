package dao;

import bean.Student;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: YunHai
 * @Date: 2018/10/5 20:46
 * @Description:成员增删改查
 */
public interface StudentDao {
    /**
     * 接收id, 获取成员资料.
     * @param id: 成员id
     * @return: 如果没有这个成员, 返回null
     */
    Student getStudentById(String id);

    /**
     * 通过传入资料的id, 修改成员资料
     * @param student: 新的成员资料
     */
    void updateStudent(Student student) throws IOException;

    /**
     * 接收id, 删除成员资料
     * @param id: 成员id
     */
    void deleteStudentById(String id) throws IOException;

    /**
     * 添加组员
     * @param student: 新的成员资料
     */
    void insertStudent(Student student) throws IOException;

    /**
     * 通过小组id 获取本组所有成员的数据
     * @param id: 小组id
     * @return: 本小组所有成员的数据
     */
    List<Student> getAllStudentByGroupId(String id);

    /**
     * 分页查询所有成员
     * @param startIndex: 从startIndex条数据开始查询 最小值为0
     * @param count: 查询的条数
     * @return
     */
    List<Student> getAllStudentLimit(int startIndex, int count);

    /**
     * 获取所有成员数量
     * @return: 所有成员的数量
     */
    int getStudentCount();
}
