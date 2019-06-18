package com.wp.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

  @Autowired
  private TopicService topicService;

  @GetMapping("/topic")
  public List<Topic> list() {
	  List<Topic> list = topicService.list();
	  
    return list;
  }
}
