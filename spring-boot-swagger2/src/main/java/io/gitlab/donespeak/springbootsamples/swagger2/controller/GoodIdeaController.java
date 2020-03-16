package io.gitlab.donespeak.springbootsamples.swagger2.controller;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVo;
import io.gitlab.donespeak.springbootsamples.swagger2.service.GoodIdeaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:19
 */
@RestController
@RequestMapping("/good-ideas")
public class GoodIdeaController {

	@Autowired
	private GoodIdeaService goodIdeaService;

	@GetMapping("")
	public List<IdeaVo> search(IdeaSearchOption option) {
		return goodIdeaService.search(option);
	}

	@PostMapping("")
	public IdeaVo create(IdeaVo vo) {
		return goodIdeaService.created(vo).orElse(null);
	}

	@GetMapping("/{id:\\d+}")
	public IdeaVo getById(@PathVariable long id) {
		return goodIdeaService.getById(id).orElse(null);
	}

	@PutMapping("/{id:\\d+}")
	public IdeaVo update(@PathVariable long id, @RequestBody IdeaVo ideaVo) {
		ideaVo.setId(id);
		return goodIdeaService.update(ideaVo).orElse(null);
	}
}
