package dao.impl;

import bean.Group;
import dao.GroupDao;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: YunHai
 * @Date: 2018/10/6 20:30
 * @Description:
 */
public class GroupDaoImpl implements GroupDao {
    Properties properties = new Properties();
    String path = this.getClass().getClassLoader().getResource("").getPath();

//    封装居然会出现乱码???  我tm做什么了我  冤枉啊
    /*void propertiesLoad() throws IOException {
        //        获取读取流对象
        Reader reader = new FileReader(path+"dao\\Group.properties");
//        读取properties
        properties.load(reader);
        reader.close();
    }*/



    /**
     * 通过小组id 获取小组数据
     * @param id: 小组id
     * @return: 小组数据
     */
    @Override
    public Group getGroupById(String id) throws IOException {
//        获取读取流对象
        Reader reader = new FileReader(path+"dao\\Group.properties");
//        读取properties
        properties.load(reader);
        reader.close();

//        指定小组的json数据
        String value = properties.getProperty(id);

//        创建json对象
        JSONObject obj = new JSONObject().fromObject(value);
//        json对象转bean
        Group group = (Group) JSONObject.toBean(obj,Group.class);
        return group;
    }

    /*@Test
    public void testgetGroupById() throws IOException {
        String id = "200000";
        Reader reader = new FileReader(path+"dao\\Group.properties");
        properties.load(reader);
        reader.close();
        String value = properties.getProperty(id);
//        String dateStr = properties.getProperty(id+"createDate");
//        Long dateLong = new Long(dateStr);
        JSONObject obj = new JSONObject().fromObject(value);
        Group group = (Group) JSONObject.toBean(obj,Group.class);
//        group.setCreateDate(new Date(dateLong));
        System.out.println(group);
    }*/




    @Override
    public void updateGroup(Group group) throws IOException {
//        读取一套不解释  因为不知名的原因 无法封装成方法
        Reader reader = new FileReader(path+"dao\\Group.properties");
        properties.load(reader);
        reader.close();

//        将group转为json
        JSONObject obj = JSONObject.fromObject(group);
        String groupStr = obj.toString();

//        因为不知名的原因, day和timezoneOffset的值不正确并且会出现警告  所以把Date中除time外的所有元素全部删掉删掉删掉!!!!!!
//        获取毫秒值
        String re = groupStr.replaceAll("[\\s\\S]*\"time\"\\:([^\\:]*[\\d$])[\\s\\S]*","$1");
//        将毫秒值封装并替换createDate  全场最有技术亮点的地方!!!
        groupStr = groupStr.replaceAll("\"createDate\":[\\s\\S]*\"time\"\\:([^\\:]*[\\d$])[\\s\\S]*},\"id\"","\"createDate\":{\"time\":"+re+"},\"id\"");

//        将转换后的值写入配置文件
        properties.setProperty(group.getId(),groupStr);
        Writer writer = new FileWriter(path+"dao\\Group.properties");
        properties.store(writer,null);
//        告辞
        writer.close();
    }
    /*@Test
    public void testupdateGroup() throws IOException {
        String id = "200000";
        Reader reader = new FileReader(path+"dao\\Group.properties");
        properties.load(reader);
        reader.close();
        String value = properties.getProperty(id);


        JSONObject obj = new JSONObject().fromObject(value);
        Group group = (Group) JSONObject.toBean(obj,Group.class);



        obj = JSONObject.fromObject(group);
        String groupStr = obj.toString();
        String re = groupStr.replaceAll("[\\s\\S]*\"time\"\\:([^\\:]*[\\d$])[\\s\\S]*","$1");
        groupStr = groupStr.replaceAll("\"createDate\":[\\s\\S]*\"time\"\\:([^\\:]*[\\d$])[\\s\\S]*},\"id\"","\"createDate\":{\"time\":"+re+"},\"id\"");
        System.out.println(groupStr);

        System.out.println(group.getId());
        properties.setProperty(id,groupStr);
        Writer writer = new FileWriter(path+"dao\\Group.properties");
        properties.store(writer,null);
        writer.close();

    }*/

    @Override
    public void deleteGroup(String id) throws IOException {
//        读取一套不解释. (如果不读取 整个配置文件都会被删掉)
        Reader reader = new FileReader(path+"dao\\Group.properties");
        properties.load(reader);
        reader.close();

//        删除一套不解释 (什么? 看不懂???)
        Writer writer = new FileWriter(path+"dao\\Group.properties");
        properties.setProperty(id,"");
        properties.store(writer,null);

    }

    /*@Test
    public void testdeleteGroup() throws IOException {
        Reader reader = new FileReader(path+"dao\\Group.properties");
        properties.load(reader);
        reader.close();

        String id = "400000";
        Writer writer = new FileWriter(path+"dao\\Group.properties");
        properties.setProperty(id,"");
        properties.store(writer,null);

    }*/




    @Override
    public void createGroup(Group group) throws IOException {
//        这tm和update有区别???
        updateGroup(group);
    }

    /*@Test
    public void testCreateGroup() throws IOException {
        String id = "300000";
        Reader reader = new FileReader(path+"dao\\Group.properties");
        properties.load(reader);
        reader.close();
        String value = properties.getProperty(id);
//        String dateStr = properties.getProperty(id+"createDate");
//        Long dateLong = new Long(dateStr);
        JSONObject obj = new JSONObject().fromObject(value);
        Group group = (Group) JSONObject.toBean(obj,Group.class);
        group.setId("400000");
        group.setCreateDate(new Date());

        obj = JSONObject.fromObject(group);
        String groupStr = obj.toString();

        properties.setProperty(group.getId(), groupStr);
        Writer writer = new FileWriter(path+"dao\\Group.properties");
        properties.store(writer,null);
        writer.close();
    }*/

    @Override
    public List<Group> getAllGroup() throws IOException {
//        全场最让人放松的方法
//        读取一套不解释
        Reader reader = new FileReader(path+"dao\\Group.properties");
        properties.load(reader);
        reader.close();

//        获取所有的key
        Set<String> ids = properties.stringPropertyNames();
//        加载准备!
        List<Group> list = new LinkedList<Group>();

//        还需要我告诉你这个对象是什么吗?
        JSONObject obj;
//        遍历唰唰唰!!
        for(String s : ids){
//            节省变量名(然而并没有什么卵用)
            s = properties.getProperty(s);
            obj = new JSONObject().fromObject(s);
            list.add((Group)JSONObject.toBean(obj,Group.class));
        }


        return list;
    }

   /* @Test
    public void testGetAllGroup() throws IOException {


        Reader reader = new FileReader(path+"dao\\Group.properties");
        properties.load(reader);
        reader.close();


        Set<String> ids = properties.stringPropertyNames();

        List<Group> list = new LinkedList<Group>();
        JSONObject obj;
        for(String s : ids){
            s = properties.getProperty(s);
            obj = new JSONObject().fromObject(s);
            list.add((Group)JSONObject.toBean(obj,Group.class));
        }

        for(Group g : list)
            System.out.println(g);

    }*/

}
