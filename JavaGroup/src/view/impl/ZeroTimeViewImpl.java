package view.impl;

import bean.Group;
import bean.Student;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;
import service.ZeroTimeService;
import service.impl.ZeroTimeServiceImpl;
import utils.KeyBoardInput;
import view.ZeroTimeView;

import java.text.ParseException;
import java.util.*;

/**
 * @Auther: YunHai
 * @Date: 2018/10/9 00:52
 * @Description:
 */
public class ZeroTimeViewImpl implements ZeroTimeView {
    //    用户输入专用对象  模拟c语言getch()
    KeyBoardInput userInput;

//    用户输入值的临时存储
    char inputChar;
    //    用户对象
    Student student;
    //    小组对象
    Group group;

    ZeroTimeService zero = new ZeroTimeServiceImpl();


    Scanner scan = new Scanner(System.in);


    public ZeroTimeViewImpl(KeyBoardInput userInput){
        this.userInput = userInput;
    }

    public ZeroTimeViewImpl() {}

    public int r() {
        synchronized (userInput) {
            try {
                userInput.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        如果按下Caps键, 修改大小写
        if(userInput.code == 20){
            if (userInput.Caps_Lock) userInput.Caps_Lock = false;
            else userInput.Caps_Lock = true;

//            修改后再次调用自己 以避免20被输出
            return r();
        }

//        如果关掉大写  则所有为字母的值 +32(改为小写)
        if(!userInput.Caps_Lock && userInput.code>=65 && userInput.code <= 90)
            userInput.code += 32;

//        返回键盘输入键的ASCII码
        return userInput.code;
    }





    /**
     * 工具方法 判断输入是否合法
     *
     * @param min: 允许输入的最小值
     * @param max: 允许输入的最大值
     * @return
     */
    int getNum(int min, int max) {
        int Num ;

        while (true) {

            try {
                Num = scan.nextInt();
//                如果输入的不是数字 则抛异常
            } catch (InputMismatchException exception) {
                System.out.println("错误:" + exception + "\n请不要输入数字以外的字符,请重新输入");
                scan.next();
                continue;
            }

            if (Num >= min && Num <= max)
                return Num;
            else
                System.out.println("输入值错误,请重新输入.(要求值在" + min + "-" + max + "之间");
        }

    }



    @Override
    public void run() {
        while(true) {
            switch (index()) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
            }
        }
    }


    @Override
    public int index() {
        int choice = 1;

        System.out.println("输入1登录, 输入2注册, 输入3结束程序 ");
        inputChar = '1';
        while (inputChar != 10) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|       首页       |");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       --------");
            if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ->|");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   |");
            System.out.println("登录|");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       --------");
            if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ->|");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   |");
            System.out.println("注册|");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    ---------------");
            if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   ->|");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   |");
            System.out.println("退出程序|");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t--------------------");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            inputChar = (char) r();

            if (inputChar == 37 || inputChar == 38) --choice;
            else if (inputChar == 39 || inputChar == 40) ++choice;

            if (choice < 1) choice = 3;
            else if (choice > 3) choice = 1;
        }

            if (choice == 3) System.exit(0);
            return choice;
    }


    @Override
    public void login() {
//        List<Character> list = new ArrayList<Character>();
        int level = 0;
        String studentId;
        String password;
        System.out.println("登录页面");
//        登录页面

//        账号输入
        while (true) {
            studentId = "";
            password = "";
            System.out.print("请输入账号:");
            while(true){
                inputChar = (char)r();
                if(inputChar == 10) break;
                studentId += inputChar;
                System.out.print(inputChar);
            }
//            list.clear();

            System.out.print("\n请输入密码:");
            while(true){
                inputChar = (char)r();
//                如果是回车键 跳出
                if(inputChar == 10){
                    System.out.println();
                    break;
                }

                password += inputChar;
//                System.out.print("*");
                System.out.print( inputChar);
            }



//            System.out.println(password+"||"+studentId);

            level = zero.login(studentId, password);
//            登录失败
            if (level == -1) {
                System.out.println("账号或密码错误,若想重新输入 输入y; 输入其他退出登录页面");
                inputChar = (char)r();
                if (inputChar =='y' || inputChar == 'Y') continue;
                else return;

            } else break;//登录成功 跳出循环
        }
//        为成员变量赋值
        student = zero.getStudentById(studentId);
        group = zero.getGroupById(student.getGroupId());
//        System.out.println(student.getGroupId()+level);
//        判断权限 进行不同的登录页面
        switch (level) {
            case 1:
                levelOne();
                break;
            case 2:
                levelTwo();
                break;
            case 3:
                root();
                break;
            default:
                throw new RuntimeException("把开发service的人给我找出来!!!");
        }

    }


    @Override
    /**
     * 注册方法
     */
    public void register() {
        String name;
        String password;
        String passwordTemp;
        String birthday;
        String birthdayTemp;//存放用户输入的日期的临时变量
        String information;
        ArrayList<String> groupIds=new ArrayList<>();//存放所有小组的ID集合
        String groupId = "1";
        String key;//保存用户输入的选项
        System.out.println("本系统随机生成用户账号请妥善保管~~~~");

        //判断用户名用户是否满意
        System.out.print("请输入用户名:");
        while(true) {
            name = scan.nextLine();
            System.out.println("确定用户名为:"+name+"吗? (输入y进行下一步操作,其他任意键重新设置用户名)");
            key = scan.next();
            if("y".equalsIgnoreCase(key)) {
                break;
            }
            System.out.print("请重新输入用户名:");
            continue;
        }


        System.out.print("请输入密码:");
        //判断用户密码是否满意
        while(true) {
            password = "";
            passwordTemp = "";
            while(true){
                inputChar = (char)r();
//                如果是回车键 跳出
                if(inputChar == 10) break;
                password += inputChar;
                System.out.print("*");
//                System.out.print( inputChar);
            }
            System.out.print("\n请再次输入密码:");
            while(true){
                inputChar = (char)r();
//                如果是回车键 跳出
                if(inputChar == 10) break;
                passwordTemp += inputChar;
                System.out.print("*");
//                System.out.print( inputChar);
            }
            if(password.equals(passwordTemp)) break;
            System.out.print("\n两次输入不同, 请重新设置密码:");

        }


        //判断用户的生日是否合法
        System.out.println("请输入您的生日");
        int year, month, day;
        System.out.print("请输入出生年:");
        year = getNum(1900,2018);
        System.out.print("请输入出生月:");
        month = getNum(1,12);
        System.out.print("请输入出生日:");
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = getNum(1,31);
                break;
            case 2:
//                                判断闰年
                if((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0) ) day = getNum(1,29);
                else day = getNum(1,28);
                break;
            default:
                day = getNum(1,30);
                break;
        }
        birthday = ""+year+"/"+month+"/"+day;

        System.out.println("您的生日为"+birthday);


        //接收用户的个人描述
        System.out.println("请输入个人描述,50个字以内");
        while(true) {
            information = scan.next();
            if(information.length() > 50) {
                System.out.println("输入的字符长度大于50，请重新输入");
                continue;
            }
            break;
        }

        /**
         * 让用户选择要加入的小组
         */
        System.out.println("请选择你要加入的小组");
        while(true) {
            groupId = showGroupList(zero.getAllGroup());
            System.out.print("确定加入<<" + zero.getGroupById(groupId).getName() + ">>小组吗? 输入y确认, 其他任意键重新选择:");
            inputChar = (char)r();
            if (inputChar == 'y' || inputChar == 'Y') {
                break;
            }
            System.out.println("请重新选择要加入的小组:");
            continue;
        }

        /**
         * 获取随机生成的账号
         */
        String id ;
        Student s =null;
        while(true) {
            id =Integer.toString((int) (Math.random()*100000));

            s = new Student(id, password, name, birthday, information, new Date(), new Date(), 0, groupId, 1);
            int flag =zero.register(s);//调用servicec层的方法
            if(flag == 1) {//如果传递回来的标志位为1证明id已经存在重新生成一个新的id
                System.out.println("账号已经存在请重新输入");
                continue;
            }
            break;
        }
        System.out.println("注册成功");
        System.out.println("请记住您的ID:"+id);
        System.out.println("按下任意键进入登录页面");
        r();
    }

    @Override
    public void levelOne() {

        while (true) {
            switch (levelOneShow()) {
//                显示小组成员信息
                case 1:
                    List<Student> allStudentInGroup = zero.getAllStudentByGroupId(student.getGroupId());
                    showStudentList(allStudentInGroup);
                    break;
//                    显示所有小组信息
                case 2:
                    List<Group> allGroup = zero.getAllGroup();
                    showGroupList(allGroup);
                    break;
//                    修改个人信息
                case 3:
                    updateSelfStudent();
//                    System.out.println("修改成功");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("输入错误 请重新输入");
                    continue;
            }
        }
    }

    @Override
    public void levelTwo() {
        while (true) {
            switch (levelTwoShow()) {
//                    显示信息
                case 1:
                   showStudentList( zero.getAllStudentByGroupId(showGroupList(zero.getAllGroup())));
                    break;
//                    修改成员信息
                case 2:
                    List<Student> allStudentInGroup = zero.getAllStudentByGroupId(student.getGroupId());
                    Student updateStudent = showStudentList(allStudentInGroup);
                    updateStudentTwo(updateStudent);
                    if(student.getLevel() == 1) return;
                    break;

//                    修改小组信息:
                case 3:
                    updateGroupTwo();
                    break;
//                   个人信息修改
                case 4:
                    updateSelfStudent();
                    break;
//                    退出登录
                case 5:
                    return;
                default:
                    System.out.println("输入错误 请重新输入");
                    continue;
            }
        }

    }

    @Override
    public void root() {
        while(true){
            switch(levelMaxShow()){
                case 1:
//                查询
                    showStudentList( zero.getAllStudentByGroupId(showGroupList(zero.getAllGroup())));
                    break;
                case 2:
//                    修改成员
                    Student updateStudent = showStudentList( zero.getAllStudentByGroupId(showGroupList(zero.getAllGroup())));
                    updateStudentMax(updateStudent);
                    break;
                case 3:
//                    修改小组信息
                    showGroupList(zero.getAllGroup());
                    break;
                case 4:
//                    退出登录
                    return;

            }
        }
    }

    /**
     * 显示权限等级为1的选择页面
     * 1:查询本小组成员
     * 2:查询所有小组信息
     * 3:修改个人信息
     * 4:退出登录
     *
     * 组员:可修改自己的资料(创建日期和id和权限不能修改)
     * 查询本组所有成员
     * 查询所有小组

     */
    int levelOneShow(){
        int choice = 1;
        System.out.println("尊敬的"+student.getName()+" 欢迎进入成员操作界面");
        inputChar = '1';
            while (inputChar != 10) {
                if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("查询成员信息");
                if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("查询小组信息");
                if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("修改个人信息");
                if (choice == 4) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("  退出登录");
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                inputChar = (char) r();

                if (inputChar == 37 || inputChar == 38) --choice;
                else if (inputChar == 39 || inputChar == 40) ++choice;

                if (choice < 1) choice = 4;
                else if (choice > 4) choice = 1;


            }
            return choice;
    }

    /**
     * 显示权限等级为2的选择页面
     * 组长: 修改本组所有成员资料(创建日期和id不能修改, 权限只能以转让的形式)
     * 查询所有小组
     * 查询所有小组的所有成员
     */
    int levelTwoShow(){
        int choice = 1;
        System.out.println("尊敬的"+student.getName()+" 欢迎进入组长操作界面");
        inputChar = 1;
        while (inputChar != 10) {
            if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("  查询信息");
            if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("修改成员信息");
            if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("修改小组信息");
            if (choice == 4) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("转让组长权限");
            if (choice == 5) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("  退出登录");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            inputChar = (char) r();

            if (inputChar == 37 || inputChar == 38) --choice;
            else if (inputChar == 39 || inputChar == 40) ++choice;

            if (choice < 1) choice = 4;
            else if (choice > 4) choice = 1;
        }
        return choice;

    }

    /**
     * 显示权限等级为3的选择页面
     *
     * 老九: 修改所有小组的所有成员
     * 查询所有小组
     * 查询所有小组的所有成员
     */
    int levelMaxShow(){
        int choice = 1;
        System.out.println("尊敬的"+student.getName()+" 欢迎进入管理员操作界面");
        inputChar = 1;
        while (inputChar != 10) {
            if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("  查询信息");
            if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("修改成员信息");
            if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("修改小组信息");
            if (choice == 4) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
            else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
            System.out.println("  退出登录");

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            inputChar = (char) r();

            if (inputChar == 37 || inputChar == 38) --choice;
            else if (inputChar == 39 || inputChar == 40) ++choice;

            if (choice < 1) choice = 4;
            else if (choice > 4) choice = 1;
        }
        return choice;
    }


    /**
     * 显示本小组的成员的信息
     * @param list
     */
    Student showStudentList(List<Student> list){
        Group group = zero.getGroupById(list.get(0).getGroupId());
        inputChar = 0;
        int choice = 0;
        int len = list.size();
        Student studentChoice = null;
        while(inputChar != 10) {
            System.out.println("-------------------------------------");
            System.out.println(group.getName() + "的成员信息");
            studentChoice = list.get(choice);
            System.out.println("成员"+(choice+1)+"的信息");
            System.out.println("ID: "+ studentChoice.getId());
            System.out.println("姓名: "+studentChoice.getName());
            System.out.println("出生日期: "+studentChoice.getBirthday());
            System.out.println("个人简介: "+studentChoice.getInformation());
            System.out.println("积分: "+studentChoice.getPoint());
            System.out.println("权限: "+studentChoice.getLevel());

            inputChar = (char) r();

            if (inputChar == 37 || inputChar == 38) --choice;
            else if (inputChar == 39 || inputChar == 40) ++choice;

            if (choice < 0) choice = len-1;
            else if (choice >= len) choice = 0;

        }
        return studentChoice;
    }



    /**
     * 显示所有小组的信息
     * @param list
     */
    String showGroupList(List<Group> list){
        inputChar = 0;
        int choice = 0;
        int len = list.size();
        Group groupTemp;
        while(inputChar != 10) {
//            获取该小组的学生集合

            groupTemp = list.get(choice);
            List<Student> studentList = zero.getAllStudentByGroupId(groupTemp.getId());

            System.out.println("---------------------------------------------");
            System.out.println("小组<<"+groupTemp.getName()+">>信息列表");
            System.out.println("小组ID: "+groupTemp.getId()+"");
            System.out.print("小组组长: ");
            for(Student s : studentList){
                if(s.getLevel() == 2){
                    System.out.print(s.getName());
                    break;
                }
            }
            System.out.print("\n小组成员:\t");
            for(Student s : studentList){
                System.out.print(s.getName()+"\t");
            }
            System.out.println("\n小组创建日期: "+groupTemp.getCreateDate());
            System.out.println("小组简介: "+ groupTemp.getInformation());
            System.out.println("小组口号: "+ groupTemp.getSlogan());
            System.out.println("小组总积分: "+ groupTemp.getAllPoint());

            inputChar = (char) r();

            if (inputChar == 37 || inputChar == 38) --choice;
            else if (inputChar == 39 || inputChar == 40) ++choice;

            if (choice < 0) choice = len-1;
            else if (choice >= len) choice = 0;

        }
        return list.get(choice).getId();
    }

    /**
     * 传入信息 修改
     */
    void updateSelfStudent(){
        Student studentTemp = new Student(student.getId(), student.getPassword(), student.getName(),student.getBirthday(),student.getInformation(),
                student.getCreateDate(),student.getLastLoginTime(),student.getPoint(),student.getGroupId(),student.getLevel());
        int choice = 1 ;
        Group noviceGroup = null;
        while(true){
            inputChar = 0;
            while (inputChar != 10) {
                if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("姓名: "+student.getName());
                if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("密码");
                if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("生日: "+student.getBirthday());
                if (choice == 4) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("个人简介: "+student.getInformation());
                if (choice == 5) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("退出小组: "+group.getName());
                if (choice == 6) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("退出修改界面");
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                inputChar = (char) r();

                if (inputChar == 37 || inputChar == 38) --choice;
                else if (inputChar == 39 || inputChar == 40) ++choice;

                if (choice < 1) choice = 6;
                else if (choice > 6) choice = 1;
            }
            switch(choice){
                case 1:
                    System.out.print("请输入新的姓名:");
                        student.setName(scan.next());
                    break;
                case 2:
                    while(true) {
                        String passwordTemp = "";
                        String passwordSet = "";
                        System.out.println("请输入新的密码:");
                        while(true){
                            inputChar = (char)r();
//                如果是回车键 跳出
                            if(inputChar == 10) break;
                            passwordTemp += inputChar;

//                System.out.print("*");
//                            System.out.print((char) inputChar);
                        }

//                        passwordTemp = scan.next();
                        System.out.println("请再次输入新的密码");
                        while(true){
                            inputChar = (char)r();
//                如果是回车键 跳出
                            if(inputChar == 10) break;
                            passwordSet += inputChar;

//                System.out.print("*");
//                            System.out.print((char) inputChar);
                        }
                        if(passwordTemp.equals(passwordSet)) {
                            student.setPassword(passwordTemp);
                            break;
                        }else System.out.println("两次输入不一致, 请重新输入");
                    }
                    break;
                case 3:
                    int year, month, day;
                    System.out.println("请输入出生年");
                    year = getNum(1900,2018);
                    System.out.println("请输入出生月");
                    month = getNum(1,12);
                    System.out.println("请输入出生日");
                    switch (month){
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            day = getNum(1,31);
                            break;
                        case 2:
//                                判断闰年
                            if((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0) ) day = getNum(1,29);
                                else day = getNum(1,28);
                            break;
                        default:
                            day = getNum(1,30);
                            break;
                    }
                    student.setBirthday(""+year+"/"+month+"/"+day);


                    break;
                case 4:
                    System.out.println("请开始你的表演:");
                    student.setInformation(scan.nextLine());
                    break;
                case 5:
                    if(student.getLevel() == 2){
                        System.out.println("组长无法退出自己的小组");
                        break;
                    }
                    System.out.println("该操作将退出当前小组<<"+group.getName()+">>, 请输入密码验证");
                    String passwordTemp = "";
                    while(true){
                        inputChar = (char)r();
//                如果是回车键 跳出
                        if(inputChar == 10) break;
                        passwordTemp += inputChar;
//                System.out.print("*");
//                            System.out.print((char) inputChar);
                    }
                    if(!passwordTemp.equals(student.getPassword())) {
                        System.out.println("密码错误, 退出失败");
                        break;
                    }
                    student.setGroupId("1");
                    List<String> groupMemberId = group.getMemberId();
                    int len = groupMemberId.size();
                    for(int i = 0; i <len; i ++ ){
                        if(groupMemberId.get(i).equals(student.getId())) groupMemberId.remove(i);
                    }
                    noviceGroup = zero.getGroupById("1");
                    noviceGroup.getMemberId().add(student.getId());
                    System.out.println("操作成功, 将在保存后退出小组");
                    break;
                case 6:
                    System.out.println("您还没有保存, 请问是否需要保存?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(noviceGroup);
                        zero.updateGroup(group);
                        zero.updateStudent(student);
                        System.out.println("修改成功!");
                    }else{
                        group = zero.getGroupById(group.getId());
                        zero.updateStudent(studentTemp);
                        student = zero.getStudentById(student.getId());
                    }
                    return;
            }


        }


    }

    void updateStudentTwo(Student oldStudent){
        Student studentTemp = new Student(oldStudent.getId(), oldStudent.getPassword(), oldStudent.getName(),oldStudent.getBirthday(),oldStudent.getInformation(),
                oldStudent.getCreateDate(),oldStudent.getLastLoginTime(),oldStudent.getPoint(),oldStudent.getGroupId(),oldStudent.getLevel());
        int choice = 1 ;
        String tempStr = null;
        while(true){
            inputChar = 0;
            while (inputChar != 10) {
                if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改姓名: "+oldStudent.getName());
                if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改生日: "+oldStudent.getBirthday());
                if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改简介: "+oldStudent.getInformation());
                if (choice == 4) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("分配积分: "+oldStudent.getPoint());
                if (choice == 5) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("移交组长");
                if (choice == 6) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("移出小组");
                if (choice == 7) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("退出修改界面");
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                inputChar = (char) r();

                if (inputChar == 37 || inputChar == 38) --choice;
                else if (inputChar == 39 || inputChar == 40) ++choice;

                if (choice < 1) choice = 7;
                else if (choice > 7) choice = 1;
            }

            switch(choice){
                case 1:
                    System.out.print("请输入新的姓名:");
                    oldStudent.setName(scan.next());
                    break;
                case 2:
                    int year, month, day;
                    System.out.println("请输入出生年");
                    year = getNum(1900,2018);
                    System.out.println("请输入出生月");
                    month = getNum(1,12);
                    System.out.println("请输入出生日");
                    switch (month){
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            day = getNum(1,31);
                            break;
                        case 2:
//                                判断闰年
                            if((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0) ) day = getNum(1,29);
                            else day = getNum(1,28);
                            break;
                        default:
                            day = getNum(1,30);
                            break;
                    }
                    oldStudent.setBirthday(""+year+"/"+month+"/"+day);
                    break;
                case 3:
                    System.out.println("请输入个人简介:");
                    oldStudent.setInformation(scan.nextLine());
                    break;
                case 4:
                    int groupPoint = group.getAllPoint();
                    int studentPoint = oldStudent.getPoint();
                    System.out.println("该成员的目前积分: " + studentPoint);
                    System.out.println("请输入为该成员添加的积分(当前小组积分: " + groupPoint + "):");
                    int point = getNum(0,group.getAllPoint());
//                    System.out.println("修改后, 该成员的积分为: " + (studentPoint + +point));
                    oldStudent.setPoint(studentPoint+point);
                    group.setAllPoint(group.getAllPoint()-point);
                    break;
                case 5:
                    if(student.getId().equals(oldStudent.getId())){
                        System.out.println("你不能将权限转让给自己");
                        break;
                    }
                    if(oldStudent.getLevel() != 1){
                        System.out.println("该成员权限为组长或管理员, 无法转让");
                        break;
                    }
                    System.out.println("将转让组长权限, 需要验证密码. 请输入你的密码:");
                    String passwordTemp = "";
                    while(true){
                        inputChar = (char)r();
//                如果是回车键 跳出
                        if(inputChar == 10) break;
                        passwordTemp += inputChar;
//                System.out.print("*");
//                            System.out.print((char) inputChar);
                    }
                    if(!passwordTemp.equals(student.getPassword())) {
                        System.out.println("密码错误, 转让失败");
                        break;
                    }
                    group.setCaptainId(oldStudent.getId());
                    student.setLevel(1);
                    oldStudent.setLevel(2);
                    System.out.println("转让成功, 保存更改后将自动退出登录. 请问是否保存更改?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(group);
                        zero.updateStudent(oldStudent);
                        zero.updateStudent(student);
                        System.out.println("修改成功!");
                        return;
                    }else{
                        student.setLevel(2);
                        zero.updateStudent(studentTemp);
//                        System.out.println("temp权限:"+studentTemp.getLevel()+"tempId:"+studentTemp.getId());

                        oldStudent = zero.getStudentById(studentTemp.getId());
//                        System.out.println("old权限: "+oldStudent.getLevel());
                        group = zero.getGroupById(group.getId());
                    }
                    break;
                case 6:
                    if(oldStudent.getLevel() != 1){
                        System.out.println("该成员的权限等级为组长或管理员, 无法将其移出小组/");
                        break;
                    }
                    System.out.println("将成员"+oldStudent.getName()+" 移出小组, 需要验证密码, 请输入你的密码");
                    passwordTemp = "";
                    while(true){
                        inputChar = (char)r();
//                如果是回车键 跳出
                        if(inputChar == 10) break;
                        passwordTemp += inputChar;
//                System.out.print("*");
//                            System.out.print((char) inputChar);
                    }
                    if(!passwordTemp.equals(student.getPassword())) {
                        System.out.println("密码错误, 移出失败");
                        break;
                    }
                    List<String> studentIdList = group.getMemberId();
                    int len = studentIdList.size();
                    for(int i = 0; i < len; i ++){
                        if(studentIdList.get(i).equals(oldStudent.getId())){
                            studentIdList.remove(i);
                            break;
                        }
                    }
                    oldStudent.setGroupId("1");
                    Group noviceGroup = zero.getGroupById("1");
                    noviceGroup.getMemberId().add(oldStudent.getId());

                    System.out.println("成功移出, 保存更改后将退出修改操作. 请问是否保存更改?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(group);
                        zero.updateGroup(noviceGroup);
                        zero.updateStudent(oldStudent);
                        System.out.println("修改成功!");
                        return;
                    }else{
                        zero.updateStudent(studentTemp);
                        oldStudent = zero.getStudentById(oldStudent.getId());
                        group = zero.getGroupById(group.getId());
                    }
                    break;
                case 7:
                    System.out.println("您还没有保存, 请问是否需要保存?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(group);
                        zero.updateStudent(oldStudent);
                        System.out.println("修改成功!");
                    }else{
                        group = zero.getGroupById(group.getId());
                        zero.updateStudent(studentTemp);
                        oldStudent = zero.getStudentById(oldStudent.getId());
                    }
                    return;
            }


        }
    }

    void updateStudentMax(Student oldStudent){
        Student studentTemp = new Student(oldStudent.getId(), oldStudent.getPassword(), oldStudent.getName(),oldStudent.getBirthday(),oldStudent.getInformation(),
                oldStudent.getCreateDate(),oldStudent.getLastLoginTime(),oldStudent.getPoint(),oldStudent.getGroupId(),oldStudent.getLevel());
        int choice = 1 ;
        String tempStr = null;
        Group group = zero.getGroupById(oldStudent.getGroupId());

        while(true){
            inputChar = 0;
            while (inputChar != 10) {
                if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改姓名: "+oldStudent.getName());
                if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改生日: "+oldStudent.getBirthday());
                if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改简介: "+oldStudent.getInformation());
                if (choice == 4) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改积分: "+oldStudent.getPoint());
                if (choice == 5) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t  ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("设为组长");
                if (choice == 6) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("修改所属小组");
                if (choice == 7) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println(" 删除此会员");
                if (choice == 8) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("退出修改界面");
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                inputChar = (char) r();

                if (inputChar == 37 || inputChar == 38) --choice;
                else if (inputChar == 39 || inputChar == 40) ++choice;

                if (choice < 1) choice = 7;
                else if (choice > 7) choice = 1;
            }

            switch(choice){
                case 1:
                    System.out.print("请输入新的姓名:");
                    oldStudent.setName(scan.next());
                    break;
                case 2:
                    int year, month, day;
                    System.out.println("请输入出生年");
                    year = getNum(1900,2018);
                    System.out.println("请输入出生月");
                    month = getNum(1,12);
                    System.out.println("请输入出生日");
                    switch (month){
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            day = getNum(1,31);
                            break;
                        case 2:
//                                判断闰年
                            if((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0) ) day = getNum(1,29);
                            else day = getNum(1,28);
                            break;
                        default:
                            day = getNum(1,30);
                            break;
                    }
                    oldStudent.setBirthday(""+year+"/"+month+"/"+day);
                    break;
                case 3:
                    System.out.println("请输入个人简介:");
                    oldStudent.setInformation(scan.nextLine());
                    break;
                case 4:
                    int groupPoint = group.getAllPoint();
                    int studentPoint = oldStudent.getPoint();
                    System.out.println("该成员的目前积分: " + studentPoint);
                    System.out.print("请输入为该成员添加的积分:");
                    int point = getNum(-studentPoint,Integer.MAX_VALUE);
//                    System.out.println("修改后, 该成员的积分为: " + (studentPoint + +point));
                    oldStudent.setPoint(studentPoint+point);
                    break;
                case 5:
                    if(oldStudent.getLevel() != 1 ){
                        System.out.println("无法将组长权限转让给组长或管理员");
                        break;
                    }
                    System.out.println("将转让组长权限, 需要验证密码. 请输入你的密码:");
                    String passwordTemp = "";
                    while(true){
                        inputChar = (char)r();
//                如果是回车键 跳出
                        if(inputChar == 10) break;
                        passwordTemp += inputChar;
//                System.out.print("*");
//                            System.out.print((char) inputChar);
                    }
                    if(!passwordTemp.equals(student.getPassword())) {
                        System.out.println("密码错误, 转让失败");
                        break;
                    }

//                    该成员所属小组
//                    小组组长
                    Student captainStudent = zero.getStudentById(group.getCaptainId());
//                    设置小组中的组长id
                    group.setCaptainId(oldStudent.getId());
                    captainStudent.setLevel(1);
                    oldStudent.setLevel(2);
                    System.out.println("转让成功, 保存更改后将退出修改界面. 请问是否保存更改?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(group);
                        zero.updateStudent(oldStudent);
                        zero.updateStudent(captainStudent);
                        System.out.println("oldLevel:"+oldStudent.getLevel());
                        System.out.println("captaionLevel:"+captainStudent.getLevel());
                        System.out.println("修改成功!");
                    }else{
                        captainStudent.setLevel(2);
                        zero.updateStudent(studentTemp);
                        System.out.println("temp权限:"+studentTemp.getLevel()+"tempId:"+studentTemp.getId());

                        oldStudent = zero.getStudentById(studentTemp.getId());
                        System.out.println("old权限: "+oldStudent.getLevel());
                        group = zero.getGroupById(group.getId());
                    }
                    return;
                case 6:
//                    所属小组
                    if(oldStudent.getLevel() != 1){
                        System.out.println("该成员是组长或管理员, 无法移至其他小组");
                        break;
                    }
                    System.out.println("将成员"+oldStudent.getName()+" 移至其他小组, 需要验证密码, 请输入你的密码");
                    passwordTemp = "";
                    while(true){
                        inputChar = (char)r();
//                如果是回车键 跳出
                        if(inputChar == 10) break;
                        passwordTemp += inputChar;
//                System.out.print("*");
//                            System.out.print((char) inputChar);
                    }
                    if(!passwordTemp.equals(student.getPassword())) {
                        System.out.println("密码错误, 操作失败");
                        break;
                    }
                    Group newGroup = zero.getGroupById(showGroupList(zero.getAllGroup()));

//                    删除旧小组的指定成员id
                    List<String> studentIdList = group.getMemberId();
                    int len = studentIdList.size();
                    for(int i = 0; i < len; i ++){
                        if(studentIdList.get(i).equals(oldStudent.getId())){
                            studentIdList.remove(i);
                            break;
                        }
                    }
//                    新小组添加成员id
                    newGroup.getMemberId().add(oldStudent.getId());

//                    成员所属小组id设置
                    oldStudent.setGroupId(newGroup.getId());

                    System.out.println("成功转移, 保存更改后将退出修改操作. 请问是否保存更改?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(group);
                        zero.updateGroup(newGroup);
                        zero.updateStudent(oldStudent);
                        System.out.println("修改成功!");
                        return;
                    }else{
                                     zero.updateStudent(studentTemp);
                        oldStudent = zero.getStudentById(oldStudent.getId());
                        group      = zero.getGroupById(group.getId());
                        newGroup   = zero.getGroupById(newGroup.getId());
                    }
                    break;
                case 7:
//                    删除会员
                    System.out.println("该操作将会删除会员. 需要验证您的密码:");
                    if(oldStudent.getLevel() != 1){
                        System.out.println("该成员是组长或管理员, 无法删除");
                        break;
                    }
                    passwordTemp = "";
                    while(true){
                        inputChar = (char)r();
//                如果是回车键 跳出
                        if(inputChar == 10) break;
                        passwordTemp += inputChar;
//                System.out.print("*");
//                            System.out.print((char) inputChar);
                    }
                    if(!passwordTemp.equals(student.getPassword())) {
                        System.out.println("密码错误, 操作失败");
                        break;
                    }
                    System.out.println("成功删除, 保存更改后将退出修改操作. 请问是否保存更改?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.deleteStudentById(oldStudent.getId());
                        return;
                    }

                        break;
                case 8:
                    System.out.println("您还没有保存, 请问是否需要保存?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(group);
                        zero.updateStudent(oldStudent);
                        System.out.println("修改成功!");
                    }else{
                        group = zero.getGroupById(group.getId());
                        zero.updateStudent(studentTemp);
                        oldStudent = zero.getStudentById(oldStudent.getId());
                    }
                    return;
            }


        }
    }

    void updateGroupTwo(){
//        小组名
//        小组简介
//        小组口号
        int choice = 1 ;
        while(true){
            inputChar = 0;
            while (inputChar != 10) {
                if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t ");
                System.out.println("修改小组名: "+group.getName());
                if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("修改小组简介: "+group.getInformation());
                if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改小组口号: "+group.getSlogan());
                if (choice == 4) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("退出修改界面 ");

                if (inputChar == 37 || inputChar == 38) --choice;
                else if (inputChar == 39 || inputChar == 40) ++choice;

                if (choice < 1) choice = 4;
                else if (choice > 4) choice = 1;
            }

            switch(choice){
                case 1:
//                    修改小组名
                    System.out.print("请输入新的小组名:");
                    group.setName(scan.nextLine());
                    break;
                case 2:
//                    修改小组简介
                    System.out.print("请输入新的小组简介:");
                    group.setInformation(scan.nextLine());
                    break;
                case 3:
//                    修改小组口号
                    System.out.print("请输入新的小组口号:");
                    group.setSlogan(scan.nextLine());
                    break;
                case 4:
//                    退出修改界面
                    System.out.println("您还没有保存, 请问是否需要保存?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(group);
                    }else{
                        group = zero.getGroupById(group.getId());
                    }
                    return;
            }


        }
    }

    void updateGroupRoot(Group group){

        int choice = 1 ;
        while(true){
            inputChar = 0;
            while (inputChar != 10) {
                if (choice == 1) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t ");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t ");
                System.out.println("修改小组名: "+group.getName());
                if (choice == 2) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("修改小组简介: "+group.getInformation());
                if (choice == 3) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改小组口号: "+group.getSlogan());
                if (choice == 4) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  ");
                System.out.println("修改小组积分: "+group.getAllPoint());
                if (choice == 5) System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t->\t");
                else System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t");
                System.out.println("退出修改界面 ");

                if (inputChar == 37 || inputChar == 38) --choice;
                else if (inputChar == 39 || inputChar == 40) ++choice;

                if (choice < 1) choice = 5;
                else if (choice > 5) choice = 1;
            }

            switch(choice){
                case 1:
//                    修改小组名
                    System.out.print("请输入新的小组名:");
                    group.setName(scan.nextLine());
                    break;
                case 2:
//                    修改小组简介
                    System.out.print("请输入新的小组简介:");
                    group.setInformation(scan.nextLine());
                    break;
                case 3:
//                    修改小组口号
                    System.out.print("请输入新的小组口号:");
                    group.setSlogan(scan.nextLine());
                    break;
                case 4:
//                    修改小组积分
                    System.out.print("请输入增添的小组积分:");
                    group.setAllPoint(getNum(-group.getAllPoint(),Integer.MAX_VALUE));
                    break;
                case 5:
//                    退出修改界面
                    System.out.println("您还没有保存, 请问是否需要保存?(输入y保存, 其他任意键不保存)");
                    inputChar = (char)r();
                    if(inputChar == 'y' || inputChar == 'Y'){
                        zero.updateGroup(group);
                    }
                    return;
            }


        }

    }

}
