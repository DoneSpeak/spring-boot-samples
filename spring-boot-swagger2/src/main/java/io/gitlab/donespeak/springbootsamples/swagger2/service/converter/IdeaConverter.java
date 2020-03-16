package io.gitlab.donespeak.springbootsamples.swagger2.service.converter;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVo;
import io.gitlab.donespeak.springbootsamples.swagger2.repository.model.Idea;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 02:00
 */
public class IdeaConverter {

	public static IdeaVo toVo(Idea idea) {
		if(idea == null) {
			return null;
		}
		IdeaVo vo = new IdeaVo();
		BeanUtils.copyProperties(idea, vo);
		return vo;
	}

	public static List<IdeaVo> toVo(List<Idea> ideas) {
		if(ideas == null) {
			return null;
		}
		return ideas.stream().map(idea -> toVo(idea)).collect(Collectors.toList());
	}

	public static Idea toPo(IdeaVo vo) {
		if(vo == null) {
			return null;
		}
		Idea idea = new Idea();
		BeanUtils.copyProperties(idea, vo);
		return idea;
	}
}
