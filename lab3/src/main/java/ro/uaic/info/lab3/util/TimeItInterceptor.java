package ro.uaic.info.lab3.util;

import lombok.extern.slf4j.Slf4j;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Slf4j
public class TimeItInterceptor {
    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        final long beforeExecution = System.currentTimeMillis();
        final Object result = context.proceed();
        final long afterExecution = System.currentTimeMillis();
        log.info(String.format(
                        "[%s.%s] Execution took %d milliseconds",
                        context.getClass().getSimpleName(),
                        context.getMethod().getName(),
                        afterExecution - beforeExecution
                )
        );
        return result;
    }
}
