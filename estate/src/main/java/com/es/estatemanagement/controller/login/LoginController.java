package com.es.estatemanagement.controller.login;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.es.estatemanagement.domain.EstateManager;
import com.es.estatemanagement.domain.EstateRight;
import com.es.estatemanagement.service.BuildingService;
import com.es.estatemanagement.service.LoginLogService;
import com.es.estatemanagement.service.OwnerService;
import com.es.estatemanagement.service.impl.EstateManagerServiceImpl;
import com.es.estatemanagement.service.impl.EstateRightServiceImpl;
import com.es.estatemanagement.util.GetIp;
import com.es.estatemanagement.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    EstateManagerServiceImpl estateManagerService;
    @Autowired
    EstateRightServiceImpl estateRightService;
    @Autowired
    LoginLogService loginLogService;
    @Autowired
    BuildingService buildingService;
    @Autowired
    OwnerService ownerService;

    //cockpit.html

    @RequestMapping("/welcome.html")
    public String welcome(@RequestParam(defaultValue = "", required = false) String communityName, HttpSession session, Model model) {
        //获取请求头中的令牌
        //String token=request.getHeader("token");
        //暂时还未前后端分离，先将token存入到session中
        Object ob = session.getAttribute("estateManager");
        String token = ob.toString();
        DecodedJWT verify = JWTUtils.verify(token);
        String loginName = verify.getClaim("loginName").asString();
        String communityId = verify.getClaim("communityId").asString();
        model.addAttribute("loginName", loginName);
        System.out.println(verify.getClaim("communityName").asString());
        model.addAttribute("communityName", communityName);
        model.addAttribute("communityId", communityId);
        model.addAttribute("buildingCount", buildingService.findCount(communityName));
        model.addAttribute("buildingSum", buildingService.findSum(communityName));
        model.addAttribute("ownerCount", ownerService.findCount(communityName));
        model.addAttribute("ownerCounts", ownerService.findCounts(communityName));
        model.addAttribute("loginLogCount", loginLogService.loginLogCount());
        return "/welcome.html";
    }

    //跳转登录页面
    @RequestMapping("/")
    public String dr(HttpSession session) {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication());
        if (session.getAttribute("estateManager") != null || context.getAuthentication().getName() != "anonymousUser") {
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    //跳转登录页面
    @RequestMapping("/login")
    public String login() {
        return "/login.html";
    }

    //跳转到主页面
    @RequestMapping(value = "/main")
    public String main(@RequestParam(defaultValue = "", required = false) String communityName, HttpSession session, Model model) {
        Map map = new HashMap();
        SecurityContext context = SecurityContextHolder.getContext();
        if (session.getAttribute("estateManager") != null || context.getAuthentication().getName() != "anonymousUser") {
            map.put("username", context.getAuthentication().getName());
            Object ob = session.getAttribute("estateManager");
            String token = ob.toString();
            DecodedJWT verify = JWTUtils.verify(token);
            if (!verify.getClaim("communityId").asString().equals("0")) {
                communityName="";
            }
            if(verify.getClaim("communityName").asString().equals("全部")){
                communityName="化纤小区";
                System.out.println("随便123");
            }
            Map<String, String> maps = new HashMap<>();
            maps.put("loginName", verify.getClaim("loginName").asString());
            maps.put("communityName", (communityName.equals("")) ? verify.getClaim("communityName").asString() : communityName);
            maps.put("communityId", verify.getClaim("communityId").asString());
            String tokens = JWTUtils.getTokn(maps); //响应token
            session.setAttribute("estateManager", tokens);
            model.addAttribute("loginName", verify.getClaim("loginName").asString());
            model.addAttribute("communityName", (communityName.equals("")) ? verify.getClaim("communityName").asString() : communityName);
            model.addAttribute("communityId", verify.getClaim("communityId").asString());
            List<EstateRight> estateRight = estateRightService.getEstateManager(map);
            model.addAttribute("estateRight", estateRight);
            return "index.html";
        }
        return "redirect:/login";
    }

    //登入判断
    @PostMapping("/dologin")
    public String dologin(HttpSession session) throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.getAuthentication().getName());
        EstateManager login = estateManagerService.login(context.getAuthentication().getName());
        Map<String, String> map = new HashMap<>();
        map.put("loginName", login.getLoginName());
        map.put("communityName", login.getCommunityName());
        map.put("communityId", String.valueOf(login.getCommunityId()));
        String token = JWTUtils.getTokn(map); //响应token
        if (token != null) {
            session.setAttribute("estateManager", token);
            loginLogService.recordLoginLog(login.getLoginName(), 0, GetIp.getV4OrV6IP(), GetIp.getCityInfo(GetIp.getV4OrV6IP()), "登入成功");
            return "redirect:/main";
        } else {
            return "redirect:/";
        }
    }


    //admin：查看权限信息
    @GetMapping("/admin")
    public String admin() {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getPrincipal();
//        System.out.println("<<<==" + userDetails.getAuthorities() + "==>>>");
        return "/admin.html";
    }

    //403
    @GetMapping("/403")
    public String erre() {
        return "/403.html";
    }

    //session过期
    @RequestMapping("/timeout")
    public String timeout() throws IOException {
        System.out.println("会话已过期,请重新登入。。。。。。。。。。。");
        return "redirect:/";
    }

    //退出：清除token,session
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("estateManager");
        session.invalidate();
        return "redirect:/login";
    }


}
