package com.wp.modules;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

  @Autowired
  private TopicService topicService;
  
  @Autowired
  private TopicMapper topicMapper;

  @GetMapping("/topic")
  public List<Topic> list() {
	  Topic topic = new Topic();
	  topic.setContent("123");
	  topic.setInTime(new Date());
	  topic.setTag("123");
	  topic.setTitle("123");
	  topicMapper.insert(topic);
	  
	  List<Topic> list = topicService.list();
	  
    return list;
  }
  
}
