package io.shamanic.snakedb;

import static org.junit.Assert.*;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class DatabaseTest {

	@Test
	public void test() throws Exception {
		initSessionFactory();
		lookUpUserOldWay();
		lookUpUser();
		getSnakeGame();
	}

	private void getSnakeGame() throws Exception {
		SqlSession session = sessionFactory.openSession();
		try {
			Mapper mapper = session.getMapper(Mapper.class);
			int id = 0;
			SnakeGame game = mapper.getSnakeGame(id);
			if (game != null)
				System.out.println("game info: " + game.getSnakeGameId());
			
		} finally {
			session.close();
		}

	}

	private SqlSessionFactory sessionFactory;

	public void initSessionFactory() throws Exception {
		Reader rdr = Resources.getResourceAsReader("snakedbConfig.xml");
		sessionFactory = new SqlSessionFactoryBuilder().build(rdr);
		rdr.close();
	}

	// uses the old iBATIS style of lookup
	public void lookUpUserOldWay() throws Exception {
		SqlSession session = sessionFactory.openSession();
		try {
			SnakeUser user = (SnakeUser) session.selectOne(
					"io.shamanic.snakedb.Mapper.getSnakeUser",
					String.valueOf("davy"));
			System.out.println("result, old method: " + user.getSnakeuser()); // should
																				// print
																				// out
																				// "User1"

		} finally {
			session.close();
		}
	}

	// uses the new MyBatis style of lookup
	public void lookUpUser() throws Exception {
		SqlSession session = sessionFactory.openSession();

		try {
			Mapper mapper = session.getMapper(Mapper.class);
			SnakeUser user = mapper.getSnakeUser("davy");
			System.out.println("result, new method: " + user.getSnakeuser()); // should
																				// print
																				// out
																				// "User2"

		} finally {
			session.close();
		}
	}

}
