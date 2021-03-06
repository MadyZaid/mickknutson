package com.baselogic.tutorials.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baselogic.tutorials.components.ParentalAdvisor;
import com.baselogic.tutorials.service.UsageTracked;

/**
 * ParentalAdvice
 *
 * Introductions (known as inter-type declarations in AspectJ) enable an
 * aspect to declare that advised objects implement a given interface,
 * and to provide an implementation of that interface on behalf of
 * those objects.
 *
 * <p>Spring Certification objective: 2.1 AOP Recommendations</p>
 * <p>Spring Certification objective: 2.2 AOP Pointcuts</p>
 * <p>Spring Certification objective: 2.3 AOP Advice</p>
 *
 * at_see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.1 AOP Recommendations</a>
 * at_see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.2 AOP Pointcuts</a>
 * at_see <a href="http://springcert.sourceforge.net/core-3/index.html#aop">Objective 2.3 AOP Advice</a>
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
@Aspect
public class ParentalAdvice {

	private final Logger logger = LoggerFactory.getLogger(ParentalAdvice.class);


	@DeclareParents(value = "com.baselogic.tutorials.service.OrderService*+",
			        defaultImpl = ParentalAdvisor.class)
	public static UsageTracked mixin;


    @Pointcut("execution(* com.baselogic.tutorials.service.*.placeOrder(..))")
    public void placeOrderService() {}


    @Before("placeOrderService() && this(usageTracked)")
	public void recordUsage(UsageTracked usageTracked) {
		logger.info(">>> ----------------------->>>");
		logger.info(">>> ----------------------->>>");
		logger.info(">>> ----------------------->>>");
		logger.info(">>> ----------------------->>>");
		logger.info(">>> ----------------------->>>");
		logger.info(">>> ----------------------->>>");
		logger.info(">>> ----- recordUsage ----->>>");
		logger.info(">>> Parental trackUsage: {}", usageTracked.trackUsage());
		logger.info(">>> Parental toString: {}", usageTracked.toString());

		//interact with the target class...

		logger.info(">>> ----- recordUsage ----->>>");
	}

}
