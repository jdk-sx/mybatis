package cn.mldn.mldnmybatis.base;

import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.mldn.mldnmybatis.util.MyBatisSessionFactory;
import cn.mldn.mldnmybatis.vo.Member;

public class TestMemberAdd {
	private static Random random = new Random() ;
	private static int rand ;
	static {
		rand = random.nextInt(Integer.MAX_VALUE) ;
	}
//	@Test
//	public void testAddMember1() throws Exception {
//		InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis.cfg.xml");
//		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession session = sessionFactory.openSession();   	
//		Member vo = new Member() ;
//		vo.setMid("mldn - " + rand);
//		vo.setName("你好 - " + rand);
//		vo.setBirthday(new Date());
//		vo.setSalary(8000.0);
//		vo.setNote("是一个不错的好人 - " + rand);
//		System.out.println(session.insert("cn.mldn.mapping.MemberNS.doCreate",vo));
//		session.commit(); // 提交更新事务
//		inputStream.close(); 
//	}
	@Test
	public void testAddMember() throws Exception{
		Member vo = new Member() ;
		vo.setMid("mldn - " + rand);
		vo.setName("你好 - " + rand);
		vo.setBirthday(new Date());
		vo.setSalary(8000.0);
		vo.setNote("是一个不错的好人 - " + rand);
		System.err.println(MyBatisSessionFactory.getSession().insert("cn.mldn.mapping.MemberNS.doCreate",vo));
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
	}
}
