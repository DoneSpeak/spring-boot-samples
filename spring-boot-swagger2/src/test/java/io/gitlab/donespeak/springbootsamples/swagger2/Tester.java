package io.gitlab.donespeak.springbootsamples.swagger2;

import org.apache.commons.lang3.StringUtils;

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
}
