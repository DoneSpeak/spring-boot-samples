package io.gitlab.donespeak.springbootsamples.swagger2;

import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVo;
import io.gitlab.donespeak.springbootsamples.swagger2.controller.vo.IdeaVoCreateUpdate;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author Yang Guanrong
 * @date 2020/03/17 09:07
 */
public class Tester {

	public static void main(String[] args) {
		System.out.println("1,2,3".matches("([0-9],)*[0-9]"));
		System.out.println("1,2,3,".matches("([0-9],)*[0-9]"));
		System.out.println("1,2,3,,".matches("([0-9],)*[0-9]"));
		System.out.println("1a8".matches("^[0-9]+(.[0-9]+)?$"));
		System.out.println("1.8".matches("^[0-9]+(\\.[0-9]+)?$"));
		System.out.println("567".matches("\\d+"));
		System.out.println("567".matches("[0-9]+"));
	}

	@Test
	public void convert() throws IllegalAccessException, NoSuchFieldException {
		IdeaVoCreateUpdate ideaVoCreateUpdate = new IdeaVoCreateUpdate();
		ideaVoCreateUpdate.setId(100);

		IdeaVo ideaVo = (IdeaVo) ideaVoCreateUpdate;

		System.out.println(ideaVoCreateUpdate);
		System.out.println(ideaVo);

		Field field = IdeaVo.class.getDeclaredField("id");
		field.setAccessible(true);
		System.out.println(field.get(ideaVo));

		field = IdeaVoCreateUpdate.class.getDeclaredField("id");
		field.setAccessible(true);
		System.out.println(field.get(ideaVoCreateUpdate));
	}

	@Test
	public void testThread() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		ConcurrentHashMap<Thread, String> map = new ConcurrentHashMap<>();
		IntStream.range(0, 10).forEach((i) -> {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					Thread thread = Thread.currentThread();
					System.out.println(i);
					map.putIfAbsent(thread, i + "");
					String value = map.get(thread);

					System.out.println(thread + ": " + value);
					System.out.println("(" + thread.getId() + ", " + thread.isAlive() + ", " + thread.isInterrupted() + ")" + ": " + value);
				}
			});
		});
	}
}
