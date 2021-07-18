package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 1. @ModelAttribute
 *  1) @ModelAttribute 에 해당되는(?) 객체를 생성한다.
 *  2) 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다.
 *  3) 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩)한다.
 *
 * 2. @ModelAttribute
 *  1) 생략 가능하다.
 *  2) String, int, Integer 같은 단순 타입은 @RequestParam을 사용하면된다.
 *  3) 나머지 타입은 @ModelAttribute를 사용하면 된다.
 *  4) argument resolver로 지정해둔 타입은 해당되지 않는다.
 */

@Slf4j
@Controller
public class requestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username,
                                 @RequestParam("age") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //필수파라미터 여부 설정
    //required = true 가 디폴트
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {

        //주의
        ///request-param-required?user=
        //파라미터 이름만 있고 값이 없는 경우에는 빈문자로 인식하고 통과한다.
        //빈문자 "" != null.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //파람값이 안들어올경우 또는 빈문자로 들어오는 경우에 defaultValue로 설정된다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamDefault(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
//        HelloData helloData = new HelloData();
//
//        helloData.setUsername(username);
//        helloData.setAge(age);
//
//        log.info("username={}, age={}", username, age);
//        log.info("helloData={}", helloData);
//
//        return "ok";
//    }

    //위 코드와 같음
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }

}