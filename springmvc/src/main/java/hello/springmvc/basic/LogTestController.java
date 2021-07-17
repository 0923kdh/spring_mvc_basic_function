package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
1. @Controller
 1) 반환 값이 String이면 뷰 이름 으로 인식된다. 뷰를 찾고 뷰가 렌더링 된다.

2. @RestController
 1) HTTP 메시지 바디에 바로 입력된다.
 2) @ResponseBody와 관련있다.

3. 로그 출력 시 log.trace("trace log=" +  name); 와 같은 형식으로 출력하면 안된다.
 1) 자바 String + 을 쓰면 + 하면서 계속 연산이 일어남. 불필요한 리소스 낭비가 된다.

4. 로그 사용 장점
 1) 쓰레드 정보, 클래스 이름 같은 부가 정보를 볼 수 있고, 출력 모양 조절이 된다.
 2) 로그 레벨에 따라 상황에 맞에 로그를 조절 할 수 있다.
 3) 콘솔 출력뿐만 아니라 파일, 네트워크, 로그를 별도의 위치에 남길 수 있다. 특히 파일로 남길 때는 일별, 특정 용량에 따라 고르를 분할 하는 것도 가능하다.
 4) 성능도 일반 System.out 보다 좋다.(내부 버퍼링, 멀티 쓰레드 등)
*/

@Slf4j
@RestController
public class LogTestController {

    //@Slf4j 을 쓰면 아래 코드 안써도 된다.
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("log-test")
    public String logTest() {
        String name = "Spring";

        //로그 레벨 설정 가능.
        log.trace("trace log = {}", name);
        log.debug("trace log = {}", name); //개발 서버에서 필요한 정보
        log.info("trace log = {}", name); //비즈니스 정보, 운영시스템에서 봐야하는 정보
        log.warn("trace log = {}", name); //경고
        log.error("trace log = {}", name); //에러

        return "ok";
    }

}