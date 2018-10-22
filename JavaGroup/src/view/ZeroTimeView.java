package view;

import bean.Student;
import service.ZeroTimeService;

/**
 * @Auther: YunHai
 * @Date: 2018/10/5 23:12
 * @Description: 用于和用户交互
 */
public interface ZeroTimeView extends Runnable {
//    整合时, 记得将其赋值 改为 其实现类
    ZeroTimeService zero = null;

//  整合时, 记得将其final修饰去掉
    Student student = null;


    /**
     * 首页 用户选择登录或注册
     * @return 1登录, 2注册
     */
    int index();

    /**
     * 登录,调用service同名方法 (若登录成功会返回权限等级, 若登录失败会则返回-1);
     * 根据权限等级, 调用不同界面(1:组员, 2:组长, 3:老九君), 并为成员变量student赋值(调用service的getStudentById)
     * @return
     */
    void login();

    /**
     * 注册,调用service同名方法 (成功: 给对应的Student添加password 返回1; 失败: 该id已经有账号返回0, id错误(不存在)返回-1);
     * 进入权限等级(组员)界面
     */
    void register();

    /**
     * 组员界面: 可以查询本组的全部数据, 可修改自己的数据
     */
    void levelOne();

    /**
     * 组长界面: 可查询其他组的组数据, 可删改查本组的全部数据
     */
    void levelTwo();

    /**
     * 老九君: 请为所欲为
     */
    void root();



}





















