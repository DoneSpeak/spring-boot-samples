package io.gitlab.donespeak.springbootsamples.swagger2.controller;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVo;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVoCreateUpdate;
import io.gitlab.donespeak.springbootsamples.swagger2.service.GoodIdeaService;
import io.gitlab.donespeak.springbootsamples.swagger2.swagger2.SwaggerApiTags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:19
 */
@Validated
@Api(value = "推荐的Api设计方法", tags = {SwaggerApiTags.GOOD_IDEA})
@RestController
@RequestMapping("/good-ideas")
public class GoodIdeaController {

	@Autowired
	private GoodIdeaService goodIdeaService;

	@GetMapping("")
	public @NotEmpty List<IdeaVo> list(@RequestParam(name = "ideaName") @Validated @NotBlank String name) {
		return new ArrayList<>();
	}

	@GetMapping("/search")
	public List<IdeaVo> search(@Valid IdeaSearchOption option) {
		return goodIdeaService.search(option);
	}

	@PostMapping("/string")
	public List<String> postString(@RequestBody List<@NotBlank String> vos) {
		return vos;
	}

	@PostMapping("/bulk")
	public List<IdeaVo> create(@Validated @RequestBody List<@Valid IdeaVoCreateUpdate> vos) {
		return vos.stream().collect(Collectors.toList());
	}

	@PostMapping("")
	public IdeaVo create(@Valid @RequestBody IdeaVoCreateUpdate vo) {
		return goodIdeaService.created(vo).orElse(null);
	}

	@GetMapping("/{id:\\d+}")
	public IdeaVo getById(@PathVariable long id) {
		return goodIdeaService.getById(id).orElse(null);
	}

	@PutMapping("/{id:\\d+}")
	public IdeaVo update(@PathVariable long id, @RequestBody IdeaVoCreateUpdate ideaVo) {
		ideaVo.setId(id);
		return goodIdeaService.update(ideaVo).orElse(null);
	}
}
