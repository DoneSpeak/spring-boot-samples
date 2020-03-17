package io.gitlab.donespeak.springbootsamples.swagger2.controller;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVo;
import io.gitlab.donespeak.springbootsamples.swagger2.service.BadIdeaService;
import io.gitlab.donespeak.springbootsamples.swagger2.swagger2.SwaggerApiTags;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:20
 */
@Api(value = "不推荐的Api设计方法", tags = {SwaggerApiTags.BAD_IDEA})
@RestController
@RequestMapping("/bad-ideas")
public class BadIdeaController {

	@Autowired
	private BadIdeaService badIdeaService;

	@GetMapping("")
	public List<IdeaVo> search(Map<String, Object> option) {
		return null;
	}
}
