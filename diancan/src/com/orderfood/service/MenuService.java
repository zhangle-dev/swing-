package com.orderfood.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.orderfood.mapper.MenuMapper;
import com.orderfood.pojo.Menu;
import com.orderfood.util.SqlSessionUtil;

public class MenuService {

	public List<Menu> findMenus() throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);
		List<Menu> list=mapper.findMenus();
		
		return list;
	}

	public void remove(Menu menu) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);
		if(menu!=null){
            mapper.deleteByPrimaryKey(menu.getId());
            sqlSession.commit();
        }
		sqlSession.close();
		
	}

	public int updateMenu(Menu menu) throws Exception {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);
		int id=0;
		if(null!=menu.getId()) {
			mapper.updateByPrimaryKey(menu);
            sqlSession.commit();

            id=menu.getId();
		}else {
            mapper.insert(menu);
            id = menu.getId();
            sqlSession.commit();
        }
        sqlSession.close();
		return id;
	}

}
