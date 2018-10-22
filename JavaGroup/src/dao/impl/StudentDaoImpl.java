package dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.*;
import dao.StudentDao;
import bean.Student;

public class StudentDaoImpl implements StudentDao {

    ArrayList<Student> studentList = new ArrayList<>();//存放用户的信息
    ObjectInputStream ois = null;//序列输入流
    ObjectOutputStream oos = null;//序列输出流

    /*
     * 构造方法用于初始化数据仓库 读取到内存中
     */
    @SuppressWarnings("unchecked")
	public StudentDaoImpl() throws  NumberFormatException, ParseException, ClassNotFoundException{
    	//通过序列流将存储用户的集合读取出来。并加载到内存中
    	try {
			ois = new ObjectInputStream(new FileInputStream("./src/dao/student.properties"));
			studentList =	(ArrayList<Student>) ois.readObject();
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//关流释放资源
		}
        System.out.println(studentList);//打开注释，查看集合中元素


    }
    /**
     * 通过用户id获取到小组成员，若是没有此成员则返回Null
     */
    @Override
    public Student getStudentById(String id) {
        for (int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getId().equals(id)) {
                return studentList.get(i);
            }
        }
        return null;
    }


    /**
     * 根据用户传入的student对象修改集合中原本对象的值
     */
    @Override
    public void updateStudent(Student student) throws IOException {
    	for (int i = 0; i < studentList.size(); i++) {
			if(student.getId() .equals(studentList.get(i).getId())) {//判断id是否相等 若是相等则代表同一个对象
				studentList.set(i, student);//将修改过后的信息保存到集合中
			}
		}
        saveProperties();//调用saveProperties()方法将修改过的文件保存到集合中
        //System.out.println("修改成功");
    }
    /**
     * 根据用户传入的id删除对应的小组成员
     */
    @Override
    public void deleteStudentById(String id) throws IOException {
        for (int i = 0; i < studentList.size(); i++) {//遍历集合
            if(id .equals(studentList.get(i).getId())) {//获取到相同id的小组成员
                studentList.remove(studentList.get(i));//删除成员
                break;
            }
        }
        saveProperties();//删除后将信息进行保存到配置文件
//		System.out.println("删除成功");
    }

    /**
     * 根据传递的用户添加新用户进入数据仓库即配置文件
     */
    @Override
    public void insertStudent(Student student) throws IOException  {
        studentList.add(student);//将新用户添加进集合
        saveProperties();//将添加后的信息进行保存到配置文件
//		System.out.println("添加成功");

    }
    /**
     * 通过小组id 获取本组所有成员的数据
     * @param id: 小组id
     * @return: 本小组所有成员的数据
     */
    @Override
    public List<Student> getAllStudentByGroupId(String id) {
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            if(studentList.get(i).getGroupId().equals(id)) {
                list.add(studentList.get(i));//将小组id为传进来参数的 id加入到list集合中
            }
        }

        return list;//返回list集合
    }

    /**
     * 分页查询所有成员
     * @param startIndex: 从startIndex条数据开始查询 最小值为0
     * @param count: 查询的条数
     * @return
     */
    @Override
    public List<Student> getAllStudentLimit(int startIndex, int count) {
        ArrayList<Student> list = new ArrayList<>();
        for (int i = startIndex; i < startIndex+count; i++) {
            list.add(studentList.get(i));//将要查询的数据加入集合中
        }
        return list;
    }
    /**
     * 获取所有成员数量
     * @return: 所有成员的数量
     */
    @Override
    public int getStudentCount() {
        return studentList.size();
    }

    /**
     * 将集合写到配置文件中
     *
     */
    public  void  saveProperties()  {
    	try {
    		//创建序列流对象
    		oos = new ObjectOutputStream(new FileOutputStream("./src/dao/student.properties"));
			oos.writeObject(studentList);//写出集合到配置文件

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
}
