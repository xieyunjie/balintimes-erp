package com.balintimes.erp.crm.aspectadvice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class CrmAdvice {

	private static Logger logger = LoggerFactory.getLogger(CrmAdvice.class
			.getSimpleName());

	public CrmAdvice() {
	}

	public Object CrmServiceAround(ProceedingJoinPoint joinPoint) {

		System.out
				.println("-------------------------华丽的分割线--------------------------");

		Object obj = null;
		try {
			System.out.println("start:" + joinPoint.toLongString());

			MDC.put("Exception", "Exception");
			MDC.put("Action", joinPoint.toString());
			MDC.put("Method", joinPoint.toString());
			MDC.put("Method", joinPoint.getSignature().getName());
			MDC.put("Application", "CRM");

			MDC.put("Params", com.balintimes.erp.util.json.JsonUtil
					.ToJson(joinPoint.getArgs()));

			obj = joinPoint.proceed();

			System.out.println("end:" + joinPoint.toShortString());

			logger.info("message");
		} catch (Throwable e) {
			String exMsg = "The method " + joinPoint.getSignature().getName()
					+ " occurs expection : " + e;

			System.out
					.println("-------------------------异常--------------------------");

			System.out.println(exMsg);

			logger.warn(exMsg);
			MDC.put("Exception", exMsg);

			throw new RuntimeException(e);
		}

		MDC.clear();
		return obj;
	}
}
