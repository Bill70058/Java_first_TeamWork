package service.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Group;
import bean.Student;
import dao.GroupDao;
import dao.StudentDao;
import dao.impl.GroupDaoImpl;
import dao.impl.StudentDaoImpl;
import org.junit.Test;
import service.ZeroTimeService;

public class ZeroTimeServiceImpl implements ZeroTimeService {
	StudentDao studentDao;
	GroupDao groupDao = new GroupDaoImpl();


	{
		try {
			studentDao = new StudentDaoImpl();
		}catch (Exception e){
			throw new RuntimeException("流数据错误, 请联系管理员");
		}
	}




	@Override
	public int login(String id, String password) {
		Student stu = studentDao.getStudentById(id);
		if (stu != null) {
//			System.out.println(id+stu.getId());
//			System.out.println(password+stu.getPassword());
			// 能够找到用户名并且用户名正确
			if (stu.getId().equals(id)) {
				if (stu.getPassword().equals(password)) {
					// 密码正确则直接返回权限
					stu.setLastLoginTime(new Date());
					return stu.getLevel();
				}
			}
		}
		// 默认为失败
		return -1;
	}
	/*@Test
	public void testLogin(){
		int i = login("10000","oldjun");
		System.out.println(i);
	}*/


	@Override
	public int register(Student s) {
		int flag = -1; // 用来接收方法返回值的变量
		Student stu = studentDao.getStudentById(s.getId()); // 获得数据库对应学生id的信息
		if (stu != null) { // 判断id对应的学生是否存在若是存在
			flag = 1;//改变标志位
			return flag;
		}
		this.insertStudent(s);//若是不存在该ID 调用方法添加学生
		return flag;//返回flag
	}

	@Override
	public String judgementGroup(Group group) {

		try {
			if (groupDao.getGroupById(group.getId()) != null) {
    //			如果Id重复的话
                return "Id";
            } else if (group.getCaptainId() == null) {
    //			如果组长的Id为空的话
                return "CaptainId";
            } else if (group.getSlogan() == null) {
    //			如果口号为空的话
                return "Slogan";
            } else if (group.getInformation() == null) {
    //			如果信息为空的话
                return "InforMation";
            }
		} catch (IOException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:if (groupDao.getGroupById(group.getId()) != null) {  ");
			e.printStackTrace();
		}
//			先查找集合中所有的存在的数据
		List<Group> groupList = null;
		try {
			groupList = groupDao.getAllGroup();
		} catch (IOException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:groupList = groupDao.getAllGroup(); ");
			e.printStackTrace();
		}
		if (groupList.isEmpty()) {
//			集合为空则什么都不干
		} else {
			String name = group.getName();
//				新循环遍历集合来判断名字是否重复了
			for (Group g : groupList) {
				if (g.getName().equals(name)) {
					return "Name";
				}
			}
		}
		return null;
	}

//	/*@Test
//	public void aaa() throws IOException {
//		int count = getStudentCount();
//		Student student = getStudentById("10000");
//		getStudentById("10000");
//		getStudentById("10000");
//		getStudentById("10000");
//		getStudentById("10000");
//		System.out.println(student);

//		int len = getStudentCount();
//		List<Student> list = getAllStudentLimit(0, len);
//		for(Student s : list){
//			System.out.println(s);
//		}



//		Student student1 = new Student("10000", student.getPassword(),"铁骨铮铮王境泽",student.getBirthday(),"我就算饿死, 从这里跳下去, 也绝对不会吃这口饭 ----真香!",student.getCreateDate(),student.getLastLoginTime(),400,"100000",2);
//		Student student2 = new Student("10003", student.getPassword(),"窃_格瓦拉",student.getBirthday(),"打工是不可能打工的, 这辈子都不可能打工!",student.getCreateDate(),student.getLastLoginTime(),2,"100000",1);
//		Student student3 = new Student("10001", student.getPassword(),"为所欲为梁永泰",student.getBirthday(),"不好意思, 有钱真的是可以为所欲为的",student.getCreateDate(),student.getLastLoginTime(),1000,"100000",1);
//		Student student4 = new Student("20002", student.getPassword(),"普通家庭马化腾",student.getBirthday(),"其实我们也是普通家庭, 顶多房子大一点!",student.getCreateDate(),student.getLastLoginTime(),0,"200000",1);
//
//		insertStudent(student1);
//		insertStudent(student2);
//		insertStudent(student3);
//		insertStudent(student4);
//		insertStudent(student7);
//		*//*insertStudent(student6);
//		insertStudent(student5);*//*
//		deleteStudentById("10001");
//	}*/


	@Test
	public void test(){
		int count = getStudentCount();
		List<Student> studentList = getAllStudentLimit(0,count);
		for(Student s : studentList) {
			System.out.println(s);
			if(s.getId().equals("10003")) {
				s.setLevel(1);
//				s.setGroupId("100000");
				Group group = getGroupById("100000");
//				group.getMemberId().add("10001");
//				Group group1 = getGroupById("1");
				group.setCaptainId("10000");
//				List<String> list =group1.getMemberId();
				/*for(int i = 0; i < list.size(); i++){
					if(list.get(i).equals("10001")){
						list.remove(i);
						break;
					}
				}*/

				updateGroup(group);
//				updateGroup(group1);
				updateStudent(s);
				break;
			}
		}

	}


	@Override
	public String judgementStudent(Student student) {
		String judgement = null;
		Field[] fieid = student.getClass().getDeclaredFields();// 获得对象所有属性
		try {
			for (Field f : fieid) {
				f.setAccessible(true); // 设置对象属性可见
				Object val = f.get(student); // 获取属性对应的属性值
				if (val == null) { // 如果属性值为空
					judgement = f.toString(); // 返回对应属性值的属性名称
					break;
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:e.printStackTrace();");
			e.printStackTrace();
		}
		return judgement;
	}

	@Override
	public String insertStudent(Student student) {
//		来判断获取是否有不合法的信息
		String result = judgementStudent(student);
//		如果信息全部都合法的话
		if (result == null) {
//			调用dao层方法
			try {
				studentDao.insertStudent(student);
			} catch (IOException e) {
				e.printStackTrace();

			}
//			获取成员的小组Id
			String groupId = student.getGroupId();
//			根据小组Id获取小组实例
			Group group = getGroupById(groupId);
//			获取实例中的具体集合
			List<String> memberList = group.getMemberId();
//			将Id放入到集合当中
			memberList.add(student.getId());
			updateGroup(group);
			return result;
		} else {
//		否则返回判断的信息
			return result;
		}
	}

	@Override
	public String createGroup(Group group) {
		String judgement = this.judgementGroup(group);
		if (judgement == null) {
			try {
				groupDao.createGroup(group);
			} catch (IOException e) {
				System.out.println("流对象错误, 请联系管理员 错误代码:groupDao.createGroup(group);");
				e.printStackTrace();

			}
		}
		return judgement;
	}

	@Override
	public void deleteStudentById(String id) {
//		获取实例
		Student s = getStudentById(id);
//		获取小组Id
		String groupId = s.getGroupId();
//		获取小组实例
		Group group = getGroupById(groupId);
//		获取实例中小组集合
		List<String> groupList = group.getMemberId();
//		将Id移除
		groupList.remove(id);
//		执行删除
		try {
			groupDao.updateGroup(group);
			studentDao.deleteStudentById(id);
		} catch (IOException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:studentDao.deleteStudentById(id);");
			e.printStackTrace();
		}

	}

	@Override
	public boolean deleteGroup(String id) {
		boolean flag = false;
		try {
			if (groupDao.getGroupById(id).getMemberId().isEmpty()) {
                // 判断小组成员的集合是否为空
                groupDao.deleteGroup(id);
                flag = true;
            }
		} catch (IOException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:if (groupDao.getGroupById(id).getMemberId().isEmpty()) {或 groupDao.deleteGroup(id);");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String updateStudent(Student student) {

		try {
			studentDao.updateStudent(student);
		} catch (IOException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:studentDao.updateStudent(stu);");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateGroup(Group group) {
		Group oldGroup = null;// 原来的对象
		try {
			oldGroup = groupDao.getGroupById(group.getId());
		} catch (IOException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:oldGroup = groupDao.getGroupById(group.getId());");
			e.printStackTrace();
		}
		Field[] fieid = group.getClass().getDeclaredFields();// 获得对象所有属性
		try {
			for (Field f : fieid) {
				f.setAccessible(true); // 设置对象属性可见
				Object val = f.get(group); // 获取属性对应的属性值
				if (val == null) { // 如果需要更新的对象属性为空
					f.set(group, f.get(oldGroup));// 则将原来对象的属性值赋给新对象
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			groupDao.updateGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public Student getStudentById(String id) {
		return studentDao.getStudentById(id);
	}

	@Override
	public Group getGroupById(String id) {
		try {
			return groupDao.getGroupById(id);
		} catch (IOException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:return groupDao.getGroupById(id);");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Student> getAllStudentByGroupId(String id) {
		return studentDao.getAllStudentByGroupId(id);
	}

	@Override
	public List<Group> getAllGroup() {
		try {
			return groupDao.getAllGroup();
		} catch (IOException e) {
			System.out.println("流对象错误, 请联系管理员 错误代码:return groupDao.getAllGroup();");
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Student> getAllStudentLimit(int startIndex, int count) {
		return studentDao.getAllStudentLimit(startIndex, count);
	}

	@Override
	public int getStudentCount() {
		return studentDao.getStudentCount();
	}

}
