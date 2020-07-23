package io.gitlab.donespeak.springbootsamples.swagger2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.dto.ValidationBody;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.ValidationSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.entity.ResourceInfo;
import io.gitlab.donespeak.springbootsamples.swagger2.support.restapi.exception.ResourceNotFoundApiException;
import org.hibernate.validator.constraints.Length;
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

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVo;
import io.gitlab.donespeak.springbootsamples.swagger2.service.GoodIdeaService;
import io.gitlab.donespeak.springbootsamples.swagger2.swagger2.SwaggerApiTags;
import io.swagger.annotations.Api;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:19
 */
@Validated
@Api(value = "推荐的Api设计方法", tags = {SwaggerApiTags.GOOD_IDEA})
@RestController
@RequestMapping("/valid")
public class ValidationController {

	@Autowired
	private GoodIdeaService goodIdeaService;

	/**
	 * 数据校验时拿不到@RequestParam注解定义的字段名，取得的字段名还是name
	 * GET /valid?ages=1,2,3 -> 无法设置数值
	 * GET /valid?statues=1,2,3 -> 可以设置数据
	 */
	@GetMapping("")
	public List<IdeaVo> list(@RequestParam("ideaName") @Validated @NotBlank String name, @RequestParam int status,
		@RequestParam List<Integer> statuses, int[] ages, @RequestParam(required = true) Long id) {
		return new ArrayList<>();
	}

	@GetMapping("set")
	public Set<Integer> set(@Size(max = 10) Set<Integer> set) {
		System.out.println(set);
		return set;
	}

	// 校验不通过抛出异常: MethodArgumentNotValidException
	@PostMapping("/body")
	public ValidationBody set(@RequestBody @Valid ValidationBody body) {
		System.out.println(body);
		return body;
	}

	@GetMapping("/search")
	public List<IdeaVo> search(@Valid ValidationSearchOption option) {
		System.out.println(option);
		return null;
	}

	@PostMapping("/string")
	public List<String> postString(@RequestBody List<@NotBlank String> vos) {
		return vos;
	}

	@PostMapping("/bulk")
	public List<IdeaVo> create(@RequestParam("ideas") @Validated @RequestBody List<@Valid @NotNull IdeaVo> vos) {
		return vos;
	}

	@PostMapping("")
	public IdeaVo create(@Valid @RequestBody IdeaVo vo) {
		return goodIdeaService.created(vo).orElse(null);
	}

	@PostMapping("map")
	public Map<String, String> postMap(@Valid @RequestBody Map<@NotBlank @Length(min = 3) String, @NotBlank String> vo) {
		return vo;
	}

	@PostMapping("map-idea")
	public Map<String, IdeaVo> postMapIdea(@Valid @RequestBody Map<@NotBlank String, @NotNull @Valid IdeaVo> vo) {
		return vo;
	}

	@GetMapping("map")
	public Map<String, String> getMap(@Valid Map<@NotBlank @Length(min = 3) String, @NotBlank String> vo) {
		return vo;
	}

	@PostMapping("set")
	public Map<String, String> post(@Valid Map<@NotBlank @Length(min = 3) String, @NotBlank String> vo) {
		return vo;
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

	@GetMapping("/notfound")
	public void notFound(@RequestParam boolean resource) {
		if(resource) {
			throw new ResourceNotFoundApiException(new ResourceInfo("Project", "12345678"), "dfdfddd");
		} else {
			throw new ResourceNotFoundApiException("dffd");
		}
	}
}
