package view;

import java.util.List;
import java.util.Scanner;
import service.ZeroTimeService;
import bean.Group;
import bean.Student;
import dao.StudentDao;

public class ZeroTimeViewimpl implements ZeroTimeService{
	
//  整合时, 记得将其赋值 改为 其实现类
  final ZeroTimeService zero = null;
  
//整合时, 记得将其final修饰去掉
  Student student = null;
	int index() {
	System.out.println("用户选择登录或注册(1登录, 2注册)");
	Scanner ipt = new Scanner(System.in);
	int 选择 = ipt.nextInt();
	while (true) {
	if(选择 == 1) {//选择登陆时
		return 1;	
	}
	else if(选择 == 2){//选择注册时
		return 2;
	}else {
		continue;//输入有误时重新while循环（再输入）
		}
	}
	}
	 /**
     * 登录,调用service同名方法 (若登录成功会返回权限等级, 若登录失败会则返回-1);
     * 根据权限等级, 调用不同界面(1:组员, 2:组长, 3:老九君), 并为成员变量student赋值(调用service的getStudentById)
     * @return
     */
	
	void login() {
		String id = "0";
		String password = "0";
		int level =0;
		if(index() == 1) {//判断登陆 可以时
		if( zero.login(id, password) ==1) {//判断登陆权限组员
			student = zero.getStudentById(id);//为登陆对象赋值输入的判定ID
			levelOne();//对应权限登陆界面
		}else if(zero.login(id, password) == 2) {//组长
			student = zero.getStudentById(id);//为登陆对象赋值输入的判定ID

		levelTwo();//对应权限登陆界面
		}else if(zero.login(id, password) == 3) {//老九
			student = zero.getStudentById(id);//为登陆对象赋值输入的判定ID

			root();//对应权限登陆界面
		}
		}else {//判断登陆 不可以时
			System.out.println("账号/密码输入有误");
		}
	}
	/**
     * 注册,调用service同名方法 (成功: 给对应的Student添加password 返回1; 失败: 该id已经有账号返回0, id错误(不存在)返回-1);
     * 进入权限等级(组员)界面
     */
	 void register() {
		 String id = "0";
			String password = "0";
		if(zero.register(id, password)==1) {
			login();
		}else if(zero.register(id, password)==0) {
			System.out.println("ID已被使用");
		}else {
			System.out.println("ID不存在");
		}
	}
	   
	
	
	 /**
     * 组员界面: 可以查询本组的全部数据, 可修改自己的数据
     */
//    void levelOne();
    public void levelOne() {
    	Student student = null;
    	Group group = null;
    	int choice;
    	Scanner input = new Scanner(System.in);
    	System.out.println("——————————————欢迎使用零时系统——————————————————！");
    	System.out.println("你可以通过使用本工具实现以下功能：");
    	System.out.println("1.查询本小组的信息");
    	System.out.println("2.修改自己的数据");
    	System.out.print("请输入你的选择：");
    	choice = input.nextInt();
    	
    	switch(choice) {
    	case 1:
    		//传入方法1
    		//这个null自动补全的不知道干啥的
    		ZeroTimeService zero = null;
			zero.getGroupById(group.getId());
			System.out.println("*****************************************************");
			System.out.println("姓名:"+ student.getName());
			System.out.println("ID:" + student.getId());
			System.out.println("生日:" + student.getBirthday());
			System.out.println("权限:" + student.getLevel());
			System.out.println("积分:" + student.getPoint());
			System.out.println("小组id:" + student.getGroupId());
			System.out.println("个人简介:" + student.getInformation());
			System.out.println("*****************************************************");
    		break;
    	case 2:
    		//传入方法2   		
    		ZeroTimeService zero2 = null;
    		//调用查询方法
    		Student studentId = zero2.getStudentById(student.getId());
    		//查询完之后将获得的资料传入更改方法
			zero2.updateStudent(studentId);
    		break;
    	default:
    		System.out.println("请输入规定功能选项(1-2)");
    		break;
    	}
    }

    /**
     * 组长界面: 可查询其他组的组数据, 可删改查本组的全部数据
     */
    //void levelTwo();
    public void levelTwo(String id) {
    	int choice;
    	Student student2;
    	Scanner input = new Scanner(System.in);
    	System.out.println("——————————————欢迎使用零时系统——————————————————！");
    	System.out.println("你可以通过使用本工具实现以下功能：");
    	System.out.println("1.查询小组信息(所有小组都可以哦)");
    	System.out.println("2.修改本小组所有成员的信息");
    	System.out.print("请输入功能选项");
    	
    	choice = input.nextInt();
    	switch(choice) {
    	case 1:
    		//传入方法1
    		ZeroTimeService zero3 = null;
    		Scanner scan = new Scanner(System.in);
    		char select;
    		do {
    		System.out.println("请输入你想要查询的小组的id：");
    		id = input.next();
			
//			System.out.println("请问从第几个成员开始查询：");
//			int startIndex = getNum();			
//			System.out.println("请问查询几条信息：");
//			int count = input.nextInt();
//			System.out.println("*****************************************************");
//			System.out.println(zero3.getAllStudentLimit(startIndex, count));
//			System.out.println("*****************************************************");
			System.out.println("*****************************************************");
			System.out.println(zero3.getGroupById(id));
			System.out.println("*****************************************************");
    		System.out.println("是否继续查询：(y/n)");
    		select = input.next().charAt(0);
    		}while(select == 'y');
    		break;
    	case 2:
    		//传入方法2
    		char select1 = 0;
    		ZeroTimeService zero4 = null;
    		do {
    			zero4.updateGroup(zero4.getGroupById(id));
    			System.out.println();
    			System.out.println("请问是否需要继续查询：(y/n)");
    			input.next().charAt(0);
    		}while(select1 == 'y');
    		break;
    	default:
    		System.out.println("请输入规定功能选项(1-2)");
    		break;
    	}
    }

    private static int getNum() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
     * 老九君: 请为所欲为
     */
    //void root();
    public void root(String id) {
    	
    	ZeroTimeService zero5 = null;
    	int choice;
    	Scanner input = new Scanner(System.in);
    	System.out.println("——————————————欢迎使用零时系统——————————————————！");
    	System.out.println("欢迎老九君使用本系统。你的权力滔天，所以可以为所欲为。");
    	System.out.println("你具有以下选项：");
    	System.out.println("修改查询任意成员及小组信息");
    	System.out.println("*****************************************************");		
			char select2;
			//传入方法1
    		do {
    			System.out.println(zero5.getAllGroup());
    			System.out.println("*****************************************");
	       		System.out.println("请选择增,删,改,查的任意功能:");
	       		System.out.println("1.增 \n2.删\n3.改\n4.查");
	    		 int choice1 = input.nextInt();
	    		 if(choice1 <= 0 || choice1 > 4) {
	    			 System.out.println("请输入1-4之间的整数");
	    			 break;
	    		 }
	    		 switch(choice1) {
	    		 case 1://增
	    			 System.out.println("请选择添加一个小组还是一个成员:");
	    			 System.out.println("1.小组\n2.成员");
	    			 int choice2 = input.nextInt();
	    			 if(choice2 == 1) {//小组
	    				 System.out.println("请输入小组的名字:");
	    				 String id1 = input.next();
	    				 zero5.createGroup(zero5.getGroupById(id1));
	    				 System.out.println(zero5.getAllGroup());
	    			 }else if(choice2 == 2) {//成员
	    				 System.out.println("请输入要增加的成员名字:");
	    				 String id2 = input.next();
	    				 //这里添加成员的信息
	    				 zero5.insertStudent(student.getId(id2));
	    			 }else {
	    				 System.out.println("请输入功能选项:(1-2)");
	    			 }
	    			 break;
	    		 case 2://删
	    			 System.out.println("请选择想要删除小组还是成员:");
	    			 System.out.println("1.删除小组\n2.删除成员");
	    			 int choice3 = input.nextInt();
	    			 switch(choice3) {
	    			 case 1://删除小组
	    				 System.out.println("请输入想要删除的小组的ID:");
	    				 String id2 = input.next();
	    				 zero5.deleteGroup(id2);
	    				 System.out.println(zero5.getAllGroup());
	    				 break;
	    			 case 2://成员
	    				 System.out.println("请输入想要删除的成员的ID:");
	    				 String id3 = input.next();
	    				 zero5.deleteStudentById(id3);
	    				 break;
	    				 default:
	    					 System.out.println("请输入功能项(1-2)");
	    					 break;
	    			 }
	    			 break;
	    		 case 3://改
	    			 break;
	    		 case 4://查
	    			 System.out.println("请输入想要查询小组或成员:");
	    			 System.out.println("1.小组\n2.成员");
	    			 int choice4 = input.nextInt();
	    			 if(choice4 == 1) {
	    				 System.out.println("请输入想要查找的小组ID:");
	    				 String id3 = input.next();
	    				 System.out.println(zero.getGroupById(id3));
	    			 }else if(choice4 == 2) {
	    				 System.out.println("请输入想要查找的成员ID:");
	    				 String id3 = input.next();
	    				 System.out.println(zero.getStudentById(id3));
	    			 }
	    			 break;
	    			 default:
	    				 break;
	    		 }
	    		 
	    		
	    		select2 = input.next().charAt(0);
    		}while(select2 == 'y');

   
    
    }

	@Override
	public int login(String id, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int register(String id, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String judgementGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String judgementStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudentById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteGroup(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String updateStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group getGroupById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllStudentByGroupId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> getAllGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllStudentLimit(int startIndex, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStudentCount() {
		// TODO Auto-generated method stub
		return 0;
	}
}
