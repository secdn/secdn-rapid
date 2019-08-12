package com.secdn.secdnrapid.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.secdn.secdnrapid.common.wrapper.WrapMapper;
import com.secdn.secdnrapid.common.wrapper.Wrapper;
import com.secdn.secdnrapid.modules.sys.entity.SysUserEntity;
import com.secdn.secdnrapid.modules.sys.form.SysLoginForm;
import com.secdn.secdnrapid.modules.sys.service.SysCaptchaService;
import com.secdn.secdnrapid.modules.sys.service.SysUserService;
import com.secdn.secdnrapid.modules.sys.service.TokenService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/** 登录相关 */
@RestController
public class SysLoginController extends AbstractController {
  @Autowired private SysUserService sysUserService;
  @Autowired private TokenService tokenService;
  @Autowired private SysCaptchaService sysCaptchaService;

  /** 验证码 */
  @GetMapping("captcha.jpg")
  public void captcha(HttpServletResponse response, String uuid)
      throws ServletException, IOException {
    response.setHeader("Cache-Control", "no-store, no-cache");
    response.setContentType("image/jpeg");

    // 获取图片验证码
    BufferedImage image = sysCaptchaService.getCaptcha(uuid);

    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(image, "jpg", out);
    IOUtils.closeQuietly(out);
  }

  /** 登录 */
  @PostMapping("/sys/login")
  public Wrapper<JSONObject> login(@RequestBody SysLoginForm form) throws IOException {
    //		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
    //		if(!captcha){
    //			return WrapMapper.error("验证码不正确");
    //		}

    // 用户信息
    SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

    // 账号不存在、密码错误
    if (user == null
        || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
      return WrapMapper.error("账号或密码不正确");
    }

    // 账号锁定
    if (user.getStatus() == 0) {
      return WrapMapper.error("账号已被锁定,请联系管理员");
    }

    // 生成token，并保存到数据库
    return tokenService.createToken(user);
  }

  /** 退出 */
  @PostMapping("/sys/logout")
  public Wrapper<Object> logout(HttpServletRequest httpRequest) {
    // 从header中获取token
    String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");

    // 如果header中不存在token，则从参数中获取token
    if (StringUtils.isBlank(token)) {
      token = httpRequest.getParameter("Authorization").replace("Bearer ", "");
    }
    tokenService.logout(token);
    return WrapMapper.ok();
  }
}
