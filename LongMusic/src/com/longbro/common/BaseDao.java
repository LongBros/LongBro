package com.longbro.common;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * 
	 * Discription:查询多条数据
	 * 
	 * @param statement
	 *            SqlMapper�����ļ���insert SQL����Ӧ�ġ�����ռ�.sqlId��
	 * @return
	 */
	public List<?> selectList(String statement) {
		return sqlSession.selectList(statement);
	}

	/**
	 * 
	 * Discription:根据指定条件查询多条数据
	 * 
	 * @param statement
	 *            SqlMapper�����ļ���insert SQL����Ӧ�ġ�����ռ�.sqlId��
	 * @param parameter
	 *            �־û�����
	 * @return
	 */
	public List<?> selectList(String statement, Object parameter) {
		if(statement==null)
			System.out.println("statement is null");
		if(parameter==null)
			System.out.println("parameter is null");
		if(sqlSession==null)
			System.out.println("sqlsession is null");
		return sqlSession.selectList(statement, parameter);
	}

	/**
	 * 查询一条数据
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public Object selectOne(String statement, Object parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	/**
	 * 
	 * Discription:插入数据
	 * 
	 * @param statement
	 *            SqlMapper�����ļ���insert SQL����Ӧ�ġ�����ռ�.sqlId��
	 * @param parameter
	 * @return
	 */
	public <P> int insert(String statement, P parameter) {
		return sqlSession.insert(statement, parameter);
	}

	/**
	 * 
	 * Discription:修改数据
	 * 
	 * @param statement
	 *            SqlMapper�����ļ���update SQL����Ӧ�ġ�����ռ�.sqlId��
	 * @param parameter
	 *            �־û�����
	 * @return
	 */
	public int update(String statement, Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	/**
	 * 
	 * Discription:删除数据
	 *
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int delete(String statement, Object parameter) {
		return sqlSession.delete(statement, parameter);
	}

	/**
	 * 
	 * Discription:插入多条数据
	 *
	 * @param <P>
	 * @param statement
	 * @param list
	 * @return
	 */
	public <P> int insertMore(String statement, List<P> list) {
		// TODO Auto-generated method stub
		return 0;
	}

}
