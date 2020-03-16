package io.gitlab.donespeak.springbootsamples.swagger2.service.impl;

import java.util.List;
import java.util.Optional;

import io.gitlab.donespeak.springbootsamples.swagger2.repository.IdeaRepository;
import io.gitlab.donespeak.springbootsamples.swagger2.repository.model.Idea;
import io.gitlab.donespeak.springbootsamples.swagger2.service.converter.IdeaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVo;
import io.gitlab.donespeak.springbootsamples.swagger2.service.GoodIdeaService;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:24
 */
@Service
public class GoodIdeaServiceImpl implements GoodIdeaService {

    @Autowired
    private IdeaRepository ideaRepository;

    @Override
    public List<IdeaVo> search(IdeaSearchOption option) {
        List<Idea> ideas = ideaRepository.search(option);
        return IdeaConverter.toVo(ideas);
    }

    @Override
    public Optional<IdeaVo> getById(long id) {
        Optional<Idea> ideaOp = ideaRepository.getById(id);
        Idea idea = ideaOp.orElse(null);
        return idea == null? Optional.empty(): Optional.of(IdeaConverter.toVo(idea));
    }

    @Override
    public Optional<IdeaVo> created(IdeaVo ideaVo) {
        Idea idea = IdeaConverter.toPo(ideaVo);
        ideaRepository.insert(idea);
        return Optional.of(IdeaConverter.toVo(idea));
    }

    @Override
    public Optional<IdeaVo> update(IdeaVo ideaVo) {
        Idea idea = IdeaConverter.toPo(ideaVo);
        ideaRepository.update(idea);
        return Optional.of(ideaVo);
    }

    @Override
    public void delete(long id) {
        ideaRepository.delete(id);
    }
}
