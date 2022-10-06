package com.lecheng.protectAnimals.realm;

import com.lecheng.protectAnimals.pojo.LoginUserVo;
import com.lecheng.protectAnimals.pojo.User;
import com.lecheng.protectAnimals.service.PermissionService;
import com.lecheng.protectAnimals.service.RoleService;
import com.lecheng.protectAnimals.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    //注入UserService
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 身份验证（为当前登录的用户进行身份验证）
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            //得到当前登录用户的信息
            String username = (String) token.getPrincipal();
            //调用根据用户名查询用户信息的方法
            User user = userService.getUser(username);
            //判断对象是否为空，不为空表示用户是存在的
            if(user!=null){
                //查询角色列表
                Set<String> roles = roleService.findRoleListByUserId(user.getUserid());
                //查询权限列表
                Set<String> permissions = permissionService.findPermissionListByUserId(user.getUserid());
                //创建登录用户对象
                LoginUserVo loginUserVo = new LoginUserVo(user,roles,permissions);
                //创建盐值(以用户名作为盐值)
                ByteSource salt = ByteSource.Util.bytes(user.getUname());
                //验证用户名及密码是否正确
                //参数1：用户名（登录对象）  参数2：密码  参数3:加密的盐值  参数4：域名（填写任意的字符串皆可）
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(loginUserVo,
                        user.getUpwd(),salt,this.getName());
                //如果info对象能够正常运行，表示登录成功
                return  info;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //登录失败
        return null;
    }

    /**
     * 授权（为当前登录的用户进行授权）
     * @param principals    当前登录对象（主体）
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录对象
        LoginUserVo loginUserVo = (LoginUserVo) principals.getPrimaryPrincipal();
        //获取当前登录用户拥有的角色列表
        Set<String> roles = loginUserVo.getRoles();
        //获取当前登录用户拥有哪些权限列表
        Set<String> permissions = loginUserVo.getPermissions();
        //创建授权对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //判断当前角色列表是否为空
        if(roles!=null && roles.size()>0){
            info.setRoles(roles);//将角色授权于当前用户
        }
        //判断当前权限列表是否为空
        if(permissions!=null && permissions.size()>0){
            info.setStringPermissions(permissions);
        }
        return info;
    }

}
