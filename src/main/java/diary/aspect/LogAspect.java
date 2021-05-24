package diary.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAspect {

    @Around("@annotation(LogTime)")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object ret = joinPoint.proceed();
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        return ret;
    }
}
