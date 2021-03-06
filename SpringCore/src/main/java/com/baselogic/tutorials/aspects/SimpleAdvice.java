package com.baselogic.tutorials.aspects;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import com.baselogic.tutorials.service.ExampleServiceInitializingBeanImpl;

/**
 * SimpleAdvice
 *
 * <p>Spring Certification objective: 1.2 Lifecycle</p>
 *
 * at_see <a href="http://springcert.sourceforge.net/core-3/index.html#beans">Objective 1.2 Lifecycle</a>
 *
 * @author Mick Knutson
 * at_see <a href="http://www.baselogic.com">Blog: http://baselogic.com</a>
 * at_see <a href="http://linkedin.com/in/mickknutson">LinkedIN: http://linkedin.com/in/mickknutson</a>
 * at_see <a href="http://twitter.com/mickknutson">Twitter: http://twitter.com/mickknutson</a>
 * at_see <a href="http://github.com/mickknutson">Git hub: http://github.com/mickknutson</a>
 *
 * at_see <a href="http://www.packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book">JavaEE 6 Cookbook Packt</a>
 * at_see <a href="http://www.amazon.com/Cookbook-securing-extending-enterprise-applications/dp/1849683166">JavaEE 6 Cookbook Amazon</a>
 *
 * @since 2012
 *
 */
public class SimpleAdvice implements MethodBeforeAdvice {

	private final Logger logger = LoggerFactory.getLogger(SimpleAdvice.class);

	@Override
	public void before(Method method, Object[] objects, Object object)
			throws Throwable {
		logger.info(">>>------------------------------------------------->>>");
		logger.info(">>>>> SimpleAdvice >>>>> Currently Processing {} on Object {}",
				method,
				object);
		if( object instanceof ExampleServiceInitializingBeanImpl){
			((ExampleServiceInitializingBeanImpl)object).setMessage("SimpleAdvice.before advice");
		}
	}
}
