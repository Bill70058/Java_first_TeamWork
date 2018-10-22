package service;

import bean.Group;
import bean.Student;

import java.util.List;

/**
 * @Auther: YunHai
 * @Date: 2018/10/5 21:30
 * @Description: 登录注册, 小组/成员增删改查
 */
public interface ZeroTimeService {
//    整合时, 记得将其赋值 改为 其实现类
//    final GroupDao groupDao = null;
//    final StudentDao studentDao = null;

    /**
     * 登录. 如果成功登录, 返回权限等级. 如果登录失败, 返回-1;
     * @param id: 登录id
     * @param password: 登录密码
     * @return: 返回权限
     */
    int login(String id, String password);

    /**
     * 注册
     * 判断对应的Student有没有password
     * 成功: 调用自己的updateGroup()方法 给对应的Student添加password 返回1;
     * 失败: 已经有账号返回0, id错误返回-1;
     * @param s: 封装的新成员对象
     * @return: 成功or失败
     */
    int register(Student s);





//    ----------------------------增删改查----------------------------------    //
//    判断后, 若合法 直接调用dao层同名方法即可

    /**
     * 工具方法: 判断输入是否合法, 用于增添和修改操作
     * @param group/student:  需判断的值
     * @return:    若合法, 返回null.
     *              若不合法, 返回不合法的变量名
     */
    String judgementGroup(Group group);
    String judgementStudent(Student student);


//    增----------------------------------------
    /**
     * 添加成员,需判断 值是否合法
     * 记得同时添加对应小组的成员id
     * @param student: 成员资料
     * @return:    返回judgementStudent(Student student)方法获得的值;
     */
    String insertStudent(Student student);

    /**
     * 创建一个新的小组, 需判断值是否合法
     * @param group: 小组资料
     * @return:    返回judgementGroup(Group group)方法获得的值;
     */
    String createGroup(Group group);


//    删----------------------------------------
    /**
     * 接收id, 删除成员资料, 记得同时删除对应小组的成员id
     * @param id: 成员id
     */
    void deleteStudentById(String id);

    /**
     * 通过小组id 删除指定小组
     * 先判断组员id是否还有值
     * @param id: 被删除的小组的id
     * @return: 如果组员id还有值 不删除 并返回false
     */
    boolean deleteGroup(String id);


//    改----------------------------------------
    /**
     * 通过传入的id, 查找并修改成员资料 需判断是否合法
     * 若传入参数的某个属性为null 则赋值之前的属性
     * @param student: 新的成员资料
     * @return:    返回judgementStudent(Student student)方法获得的值;
     */
    String updateStudent(Student student);

    /**
     * 通过小组id 查找并修改小组资料 需判断是否合法
     ** 若传入参数的某个属性为null 则赋值之前的属性
     * @param group: 新的小组资料
     * @return:    返回judgementStudent(Student student)方法获得的值;
     */
    String updateGroup(Group group);

//    查----------------------------------------
//    这些直接调用即可, 这tm要是有错误值 得把开发view的吊起来打

    /**
     * 接收id, 获取成员资料.
     * @param id: 成员id
     * @return: 如果没有这个成员, 返回null
     */
    Student getStudentById(String id);

    /**
     * 通过小组id 获取小组数据
     * @param id: 小组id
     * @return: 小组数据, 若没有 返回null
     */
    Group getGroupById(String id);

    /**
     * 通过小组id 获取所有成员的数据
     * @param id: 小组id
     * @return: 所有成员的数据, 若没有 返回null
     */
    List<Student> getAllStudentByGroupId(String id);

    /**
     * 获取所有小组信息
     * @return: 所有组的数据
     */
    List<Group> getAllGroup();

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
