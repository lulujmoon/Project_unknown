package com.mm.market.member;

import java.util.Enumeration;
import java.util.UUID;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	/*
	 * @GetMapping("error") public String error() { return "error/error"; }
	 */

	@GetMapping("memberLogin")
	public String getLogin()throws Exception{
		return "member/memberLogin";
	}
	
	
	  @PostMapping("memberLogin") public String getLogin(HttpServletRequest
	 request)throws Exception{
		  //포워딩된 어트리뷰트를 포스트형식으로 받아줌
	  System.out.println(request.getAttribute("message"));
	  
	  return "member/memberLogin"; 
	  }
	 

	@GetMapping("memberLoginFail")
	public String loginFail()throws Exception{
		return "redirect:/member/memberLogin";
	}

	@GetMapping("memberLoginResult")
	public String memberLoginResult(HttpSession session, Authentication auth2)throws Exception{

		Enumeration<String> en = session.getAttributeNames();
		MemberVO memberVO = new MemberVO();
		System.out.println(memberVO.getUsername());

		while(en.hasMoreElements()) {
			System.out.println("attribute Name:+"+en.nextElement());
		}

		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");

		SecurityContextImpl sc = (SecurityContextImpl)obj;
									//저장되는 session의 타입
		Authentication auth = sc.getAuthentication();

		System.out.println("obj : "+obj);

		System.out.println("login 성공");

		return "redirect:/";

	}

	@GetMapping("memberLogout")
	public String logout(HttpSession session)throws Exception{
		System.out.println("로그아웃");
		session.invalidate();

		return "redirect:../";
	}


	@GetMapping("memberJoin")
	public String setJoin(@ModelAttribute MemberVO memberVO) throws Exception {
		return "member/memberJoin";
	}

	@GetMapping("memberApprove")
	public void setApprove()throws Exception{
			
	}

	@PostMapping("memberApprove")
	public String setApprove(Model model)throws Exception{
			
		return "redirect:/member/memberJoin";
	}
	
	@PostMapping("memberJoin")
	public String setJoin(@Valid MemberVO memberVO,Errors errors,ModelAndView mv)throws Exception{
		System.out.println("Join process"+ memberVO.getName().length());

		  if(memberService.memberError(memberVO, errors)) { 
			  System.out.println("에러났어");
		  return"member/memberJoin"; 
		  
		  }

		int result = memberService.setJoin(memberVO); 

		return "redirect:../";

	}
	
	@GetMapping("information")
	public void infomation(MemberVO memberVO)throws Exception{
		
	}
	
	@PostMapping("update")
	public String setUpdate(MemberVO memberVO)throws Exception{
		int result = memberService.setJpdate(memberVO);
		
		
		return "redirect:./";
	}

		
	@GetMapping("auth/kakao/callback")
	public String kakaoCallback(String code) throws Exception {
		
		
		//post방식으로 key=value 데이터를 요청(카카오쪽으로)
		RestTemplate rt = new RestTemplate();
		
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "bdf85067bd67f89b950ae22189274a9c");
		params.add("redirect_uri", "http://localhost/member/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 Httpbody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params,headers);
		
		//Http 요청하기 - post방식으로 , response의 응답 받음
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
				
				);
		//Gson,JsonSimple,ObjectMapper....
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		try {
			oAuthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch (JsonMappingException e) {	
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println("카카오액세스토큰:"+oAuthToken.getAccess_token());
		
		
	
		RestTemplate rt2 = new RestTemplate();
			
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		//HttpHeader와 Httpbody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
				new HttpEntity<>(headers2);
		
		//Http 요청하기 - post방식으로 , response의 응답 받음
		ResponseEntity<String> response2 = rt2.exchange(
			"https://kapi.kakao.com//v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class
				
				);
		System.out.println(response2.getBody());
		
		
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {	
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//User 오브젝트 : username,password,email
		System.out.println("카카오 아이디(번호):"+kakaoProfile.getId());
		System.out.println("카카오 이메일(번호):"+kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("마켓서버 유저네임:" + kakaoProfile.getKakao_account().getEmail());
		System.out.println("마켓서버 이메일:" + kakaoProfile.getKakao_account().getEmail());
		
		System.out.println("마켓서버 패스워드:"+kakaoProfile.getId());

		MemberVO KakaomemberVO = new MemberVO();
		KakaomemberVO.setUsername(kakaoProfile.getKakao_account().getEmail());
		KakaomemberVO.setPassword(kakaoProfile.getId().toString());
		KakaomemberVO.setEmail(kakaoProfile.getKakao_account().getEmail());
		KakaomemberVO.setName(kakaoProfile.getProperties().getNickname());
		
		//가입자 혹은 비가입자 체크해서 처리
		MemberVO originmemberVO = memberService.findMember(KakaomemberVO);
		
		if(originmemberVO==null) {
			try {
				System.out.println("기존회원아님->회원가입진행");
				memberService.setJoin(KakaomemberVO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
				
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(KakaomemberVO.getUsername(),KakaomemberVO.getPassword() ));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return "redirect:/";
		
		
	}


}
