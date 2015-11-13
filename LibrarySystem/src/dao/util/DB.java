package dao.util;

import javax.naming.NamingException;
import java.sql.*;
import java.util.*;

/**
 * Created by gao on 15/10/25.
 */
public class DB {

    public Connection conn;

    public DB(){
        try {
            setConnection();
        }  catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //事务开始
    public void beginTX() throws SQLException{
        if(conn!=null){
            conn.setAutoCommit(false);

        }
    }

    //提交
    public void commitTX() throws SQLException{
        if(conn!=null&&!conn.getAutoCommit()){
            conn.commit();
            conn.setAutoCommit(true);
        }
    }
    //回滚
    public void rollbackTX() throws SQLException{
        if(conn!=null&&!conn.getAutoCommit()){
            conn.rollback();
            conn.setAutoCommit(true);
        }

    }


    //»ñµÃÁ¬½Ó¶ÔÏó
    public void setConnection() throws SQLException, NamingException{
        conn=DBUtil.getConn();
//		return conn;
    }

    public void close(){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    //insert/update/delete
    public int execute(String sql,List args) throws SQLException{
        int result=0;
        //	  Connection conn=null;
        PreparedStatement ps=null;

        try {
            //		conn=DBUtil.getConnection();
            ps=conn.prepareStatement(sql);
            convert(ps,args);              //convert()Ìæ´úÁËps.setInt()µÄ²½Öè
            result=ps.executeUpdate();
        }
//			  catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        finally{
            DBUtil.close(null, ps,conn);
        }
        return result;

    }


    public  void convert (PreparedStatement ps,List args){
        if(args!=null){
            Iterator<Object> iter=args.iterator();
            int index=1;
            while(iter.hasNext()){
                Object o=iter.next();
                try {
                    ps.setObject(index, o);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                index++;
            }
        }

    }


    //select
    public List query(String sql,List args){
        List result=new ArrayList();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(sql);
            convert(ps,args);
            rs=ps.executeQuery();
            //resultset转化为list
            result=convertToList(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    public List convertToList(ResultSet rs){
        List result=new ArrayList();
        try {
            ResultSetMetaData data = rs.getMetaData();
            int count=data.getColumnCount();
            while(rs.next()){
                Map maps=new HashMap();
                for(int i=1;i<=count;i++){
                    Object o=rs.getObject(i);
                    String columnName=data.getColumnName(i);
                    maps.put(columnName, o);
                    //System.out.println("value:"+o);
                    //System.out.println("col:"+columnName);
                }
                result.add(maps);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

//	  public static void main(String[] args){
////		  int id=1;
////		  String name="zhangsan";
////		  String sex="male";
////	      String sql="insert into student values(?,?,?)";
////	      List params=new ArrayList();
////	      params.add(id);
////	      params.add(name);
////	      params.add(sex);
////	      execute(sql,params);
//
//		  String sql="select * from student where sex=?";
//		  List list=new ArrayList();
//		  list.add("male");
//		  List result=query(sql,list);
//		  Iterator iter=result.iterator();
//		  while(iter.hasNext()){
//			  Map map=(Map) iter.next();
//			  Object sid=(Object) map.get("SID");
//			  Object sname=(Object) map.get("SNAME");
//			  System.out.println(sid+","+sname);
//
//		  }
//	  }



}

