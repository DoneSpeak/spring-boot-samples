package io.gitlab.donespeak.springbootsamples.swagger2.repository;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.repository.model.Idea;

import java.util.List;
import java.util.Optional;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:30
 */
public interface IdeaRepository {

	List<Idea> search(IdeaSearchOption option);

	Optional<Idea> getById(long id);

	Optional<Idea> insert(Idea goodIdea);

	int update(Idea goodIdea);

	int delete(long id);
}
