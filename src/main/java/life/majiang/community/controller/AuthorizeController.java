package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name="state")String state, HttpServletRequest request)
    {
        AccessTokenDTO accessTokenDTO =new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setRedirect_uri(redirectUri);

        accessTokenDTO.setClient_secret(clientSecret);
       String accesstoken= githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser=githubProvider.getUser(accesstoken);
       if(githubUser!=null){
           User user=new User();
           user.setToken(UUID.randomUUID().toString());
           user.setName(githubUser.getName());
           user.setAccountId(String.valueOf(githubUser.getId()));
           user.setGmt_create(System.currentTimeMillis());
           userMapper.insert(user);
           request.getSession().setAttribute("user",githubUser);//登陆成功,写cookie和session
           request.getSession().setAttribute("username",githubUser.getName());
           return "redirect:/";
       }
       else {
           return "redirect:/";//登陆失败
       }

    }

}
