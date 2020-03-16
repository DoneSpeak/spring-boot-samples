package io.gitlab.donespeak.springbootsamples.swagger2.repository.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import io.gitlab.donespeak.springbootsamples.swagger2.repository.model.Idea;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaSearchOption;
import io.gitlab.donespeak.springbootsamples.swagger2.repository.IdeaRepository;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 01:36
 */
@Repository
public class IdeaRepositoryImpl implements IdeaRepository {

	private final Map<Long, Idea> ideaStore = new ConcurrentHashMap<>();
	private static long currentId = 0L;

    @Override
    public List<Idea> search(IdeaSearchOption option) {
    	assert option != null;

	    return ideaStore.values().stream().filter(idea -> match(idea, option))
		    .sorted(Comparator.comparing(Idea::getId))
		    .collect(Collectors.toList());
    }

    private boolean match(Idea idea, IdeaSearchOption option) {
    	if(option == null) {
    		return true;
	    }
    	if(option.getId() != null && option.getId() != idea.getId()) {
			return false;
	    }
    	if(!StringUtils.isBlank(option.getName()) && !StringUtils.equals(option.getName(), idea.getName())) {
			return false;
	    }
    	return true;
    }

    @Override
    public Optional<Idea> getById(long id) {
    	Idea idea = ideaStore.get(id);
        return idea == null? Optional.empty(): Optional.of(idea);
    }

    @Override
    public Optional<Idea> insert(Idea idea) {
	    assert idea != null;

    	long id = generateId();
    	idea.setId(id);
    	ideaStore.put(id, idea);
        return Optional.of(idea);
    }

    @Override
    public int update(Idea idea) {
    	if(ideaStore.containsKey(idea.getId())) {
    		return 0;
	    }
		ideaStore.put(idea.getId(), idea);
        return 1;
    }

    @Override
    public int delete(long id) {
    	if(ideaStore.containsKey(id)) {
    		return 0;
	    }
	    ideaStore.remove(id);
    	return 1;
    }

    private synchronized static long generateId() {
	    return ++ currentId;
    }
}
