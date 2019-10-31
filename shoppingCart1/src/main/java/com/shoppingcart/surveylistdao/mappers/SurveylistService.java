package com.shoppingcart.surveylistdao.mappers;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.shoppingcart.util.MyBatisUtil;

public class SurveylistService implements SurveyListDao{
	 
	private SqlSession sqlSession;

	  public void setSqlSession(SqlSession sqlSession) {
	    this.sqlSession = sqlSession;
	  }
	 public List<String> getCreateNewSurveyList() {
		 sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		  try{
			  SurveyListDao SurveyListMapper = sqlSession.getMapper(SurveyListDao.class);
		  return SurveyListMapper.getCreateNewSurveyList();
		  }finally{
		   sqlSession.close();
		  }
		 }	
		  }
