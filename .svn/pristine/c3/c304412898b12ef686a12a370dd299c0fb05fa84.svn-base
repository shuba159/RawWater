/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.entity.YsUser;
import com.jeeplus.modules.onlinetime.dao.YsOnlinetimeDao;
import com.jeeplus.modules.onlinetime.entity.YsOnlinetime;
import com.jeeplus.modules.project.dao.YsUserDao;
import com.jeeplus.modules.project.service.YsUserService;
import com.jeeplus.modules.user.entity.WebToken;


import redis.clients.jedis.Jedis;

/**
 * 用户信息Controller
 * @author wdy
 * @version 2018-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/user/ysUser")
public class YsUserController extends BaseController {

	@Autowired
	private YsUserService ysUserService;
	@Autowired
	private YsUserDao ysUserdao;
	@Autowired
	private YsOnlinetimeDao ysOnlinetimeDao;
	@RequestMapping(value = "/123",method=RequestMethod.POST)
	public String login(HttpServletRequest request,
		
			HttpServletResponse response) {
		return "Login.jsp";
		
	}

	
	@ResponseBody
	@RequestMapping(value = "/submitlogin",method=RequestMethod.POST)
	public Map<String, Object> login(String userName, String userPwd, HttpServletRequest request,
		
			HttpServletResponse response) {
		System.out.println("用户名"+userName+"密码"+userPwd);
		
		YsUser user = ysUserdao.login(userName, userPwd);
		Map<String, Object> loginInfo = new HashMap<>();
		if (user == null) {
			loginInfo.put("code", 400);
			loginInfo.put("msg", "登陆失败");	
			return loginInfo;
		}
		// 把用户登录信息加密
		// token密钥加入用户id
		loginInfo.put("userId", user.getId());
		String sessionId = com.jeeplus.modules.user.entity.WebToken.createJavaWebToken(loginInfo);		
		Map<String, Object> jwtClaims = WebToken.verifyJavaWebToken(sessionId);
		System.out.println("用户id" + jwtClaims.get("userId"));
		System.out.println("token" + sessionId);
		loginInfo.put("code", 200);
		loginInfo.put("message", "登录成功");
		loginInfo.put("token", sessionId);
		loginInfo.put("roleid", user.getRole());
		Jedis jedis = new Jedis("127.0.0.1");
		String userid = user.getId().toString();
		
		jedis.append(userid, sessionId);
		//jedis.append(xinkey, token);
		jedis.set(userid, sessionId);
		System.out.println("设置 userid1的过期时间为120秒:" + jedis.expire(userid, 120));
		// System.out.println("移除key001的生存时间："+jedis.persist("key001"));
		System.out.println("查看 userid1的剩余生存时间：" + jedis.ttl("userid"));		
		return loginInfo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/xintiao",method=RequestMethod.POST)
	public Map<String, Object> login2(String token, HttpServletRequest request, HttpServletResponse response) {
		Jedis jedis = new Jedis("127.0.0.1");
		System.out.println("传的token" + token);
		Map<String, Object> loginInfo = new HashMap<>();
		Map<String, Object> jwtClaims = WebToken.verifyJavaWebToken(token);
		System.out.println("这是什么" + jwtClaims);
		
		if(jwtClaims==null){
			loginInfo.put("code", 400);
			loginInfo.put("msg", "更新失败");
			return loginInfo;
		}else{
		String userid = String.valueOf(jwtClaims.get("userId"));		
		System.out.println("传入的token uid" + userid);
		String ridkey = jedis.get(userid);
	
		boolean d = token.equals(ridkey);
		if (d == true) {
			String xinkey = userid;
			jedis.append(xinkey, token);
			jedis.set(xinkey, token);
			System.out.println("设置 userid1的过期时间为120秒:" + jedis.expire(xinkey, 120));
			System.out.println("查看 userid1的剩余生存时间：" + jedis.ttl("userid"));
			int userids = Integer.parseInt(xinkey);
			YsOnlinetime one = ysOnlinetimeDao.selectByPrimaryKey(userids);
			if (one == null) {
				YsOnlinetime two=new YsOnlinetime();
				System.out.println("新的心跳"+userids);
				two.setUserid(userids);
				//one.setUserid(userids);
				two.setTimenumber(1);
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(df.format(day));
				two.setLasetime(df.format(day));
				ysOnlinetimeDao.insertOnline(two);
			} else {
				//one.setId(userids);
				one.setId(xinkey);
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(df.format(day));
				one.setLasetime(df.format(day));
				int number = one.getTimenumber();
				one.setTimenumber(number + 1);
				int a = ysOnlinetimeDao.updateByPrimaryKey(one);
				if (a > 0) {
					System.out.println("个人学习时长更改成功");
				} else {
					System.out.println("个人学习时长更改失败");
				}
			}
		} else {
			loginInfo.put("code", 400);
			loginInfo.put("msg", "更新失败");
			return loginInfo;
		}
		loginInfo.put("code", 200);
		loginInfo.put("msg", "更新成功");
		return loginInfo;
	}
	}
	@ResponseBody
	@RequestMapping(value = "/tuichu",method=RequestMethod.POST)
	public Map<String, Object> tuichu(String token, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> loginInfo = new HashMap<>();
		Map<String, Object> jwtClaims = WebToken.verifyJavaWebToken(token);
	    if(token==null){
	    	loginInfo.put("code", 400);
			loginInfo.put("msg", "退出成功!");
			return loginInfo;
	    }
		Jedis jedis = new Jedis("127.0.0.1");
		System.out.println("传的token" + token);
	
		System.out.println("这是什么" + jwtClaims);
		if(jwtClaims==null){
			loginInfo.put("code", 400);
			loginInfo.put("msg", "退出成功!");
			return loginInfo;
		}
		String userid = String.valueOf(jwtClaims.get("userId"));
		
		jedis.del(userid);
		loginInfo.put("code", 200);
		loginInfo.put("msg", "退出成功!");
		return loginInfo;
	}
	
	@ModelAttribute
	public YsUser get(@RequestParam(required=false) String id) {
		YsUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ysUserService.get(id);
		}
		if (entity == null){
			entity = new YsUser();
		}
		return entity;
	}
	
	/**
	 * 用户信息列表页面
	 */
//	@RequiresPermissions("user:ysUser:list")
	@RequestMapping(value = {"list", ""})
	public String list(YsUser ysUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YsUser> page = ysUserService.findPage(new Page<YsUser>(request, response), ysUser); 
		model.addAttribute("page", page);
		return "modules/user/ysUserList";
	}

	/**
	 * 查看，增加，编辑用户信息表单页面
	 */
//	@RequiresPermissions(value={"user:ysUser:view","user:ysUser:add","user:ysUser:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(YsUser ysUser, Model model) {
		model.addAttribute("ysUser", ysUser);
		return "modules/user/ysUserForm";
	}

	/**
	 * 保存用户信息
	 */
	@RequiresPermissions(value={"user:ysUser:add","user:ysUser:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(YsUser ysUser, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, ysUser)){
			return form(ysUser, model);
		}
		if(!ysUser.getIsNewRecord()){//编辑表单保存
			YsUser t = ysUserService.get(ysUser.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(ysUser, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			ysUserService.save(t);//保存
		}else{//新增表单保存
			ysUserService.save(ysUser);//保存
		}
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/user/ysUser/?repage";
	}
	
	/**
	 * 删除用户信息
	 */
	@RequiresPermissions("user:ysUser:del")
	@RequestMapping(value = "delete")
	public String delete(YsUser ysUser, RedirectAttributes redirectAttributes) {
		ysUserService.delete(ysUser);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/user/ysUser/?repage";
	}
	
	/**
	 * 批量删除用户信息
	 */
	@RequiresPermissions("user:ysUser:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			ysUserService.delete(ysUserService.get(id));
		}
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/user/ysUser/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("user:ysUser:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(YsUser ysUser, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<YsUser> page = ysUserService.findPage(new Page<YsUser>(request, response, -1), ysUser);
    		new ExportExcel("用户信息", YsUser.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户信息记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/user/ysUser/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("user:ysUser:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<YsUser> list = ei.getDataList(YsUser.class);
			for (YsUser ysUser : list){
				try{
					ysUserService.save(ysUser);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户信息记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户信息记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/user/ysUser/?repage";
    }
	
	/**
	 * 下载导入用户信息数据模板
	 */
	@RequiresPermissions("user:ysUser:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户信息数据导入模板.xlsx";
    		List<YsUser> list = Lists.newArrayList(); 
    		new ExportExcel("用户信息数据", YsUser.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/user/ysUser/?repage";
    }
	
	
	

}