package com.wp.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
@Transactional
public class TopicService {

  @Autowired
  private TopicMapper topicMapper;

  public List<Topic> list() {
	  IPage<Topic> page = new Page<>(1, 10);
	  IPage<Topic> page2 = topicMapper.selectPage(page, new QueryWrapper<Topic>().like("title", 1));
	  return topicMapper.selectList(new QueryWrapper<Topic>());
  }

}
