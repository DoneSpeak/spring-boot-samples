package io.gitlab.donespeak.springbootsamples.swagger2.service;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVo;

import java.util.List;
import java.util.Optional;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:24
 */
public interface GoodIdeaService {

	List<IdeaVo> search(IdeaSearchOption option);

	Optional<IdeaVo> getById(long id);

	Optional<IdeaVo> created(IdeaVo goodIdeaVo);

	Optional<IdeaVo> update(IdeaVo goodIdeaVo);

	void delete(long id);
}