package com.shoppingcart.surveylistdao.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.DependsOn;
@DependsOn("surveyListService")

public interface SurveyListDao {
	
    @Select("select createnew from surveySearchDropDown")
	public List<String> getCreateNewSurveyList();
}
