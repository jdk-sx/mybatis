package cn.mldn.mldnmybatis.util;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisSessionFactory {
   private static final String MYBATIS_CONFIG_FILE = "mybatis/mybatis.cfg.xml";
   private static final ThreadLocal<SqlSession> sqlSessionThreadLocal = new ThreadLocal<SqlSession>();
   private static SqlSessionFactory sessionFactory = null;
   private static InputStream inputStream = null;
   static {
	   try {
		   inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_FILE);
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
   }
   public static SqlSessionFactory getSessionFactory() {
	   if(sessionFactory==null) {
		   createSessionFactory();   //创建连接对象
	   }
	   return sessionFactory;
   }
   public static void createSessionFactory() {
	   sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
   }
   public static SqlSession getSession() {
	   SqlSession session = sqlSessionThreadLocal.get();
	   if(session==null) {
		   if(sessionFactory==null) {
			   createSessionFactory();
		   }
		   session = sessionFactory.openSession();
		   sqlSessionThreadLocal.set(session);
	   }
	   return session;
   }
   public static void close() {
	   SqlSession session = sqlSessionThreadLocal.get();
	   if(session!=null) {
		   sqlSessionThreadLocal.remove();
		   session.close();
	   }
   }
}
