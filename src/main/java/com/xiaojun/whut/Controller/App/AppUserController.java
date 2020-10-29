package com.xiaojun.whut.Controller.App;


import com.xiaojun.whut.Entity.User;
import com.xiaojun.whut.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/app/user")
public class AppUserController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;


    @PostMapping(path = "/register")
    public String addNewUser(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping(path = "/login")
    public String loginUser(@RequestParam String userName,
                            @RequestParam String password){
        return userService.login(userName,password);
    }
//    @GetMapping(path = "/all")
//    public @ResponseBody Iterable<User> getAllUsers(){
//        return userRepository.findAll();
//    }
//    @PostMapping(path = "/login")
//    public @ResponseBody User userLogin(@RequestBody User user){
//        String message="";
//        try {
//            if(!userRepository.existsByUserName(user.getUserName())){
//                message="用户不存在";
//                throw new Exception();
//            }
//            if(!passwordEncoder.matches(user.getPassword(),
//                    userRepository.findByUserName(user.getUserName()).getPassword()
//            )){
//                message="密码不正确";
//                throw new Exception();
//            }
//            return userRepository.findByUserName(user.getUserName());
//        }catch (Exception e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,message,e);
//        }
//
//    }

    /*
    @GetMapping(path = "/userName/{userName}")
    public @ResponseBody String searchUserName(@PathVariable("userName") String userName)  {
        System.out.println(userName);
        try {
            if(userRepository.existsByUserName(userName)){
                throw new SQLIntegrityConstraintViolationException();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"username already exist",e);
        }
        return "注册失败";
    }

    @GetMapping(path = "/phoneNumber/{phoneNumber}")
    public @ResponseBody String searchPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        try {
            if(userRepository.existsByPhoneNumber(phoneNumber)){
                throw new SQLIntegrityConstraintViolationException();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"phoneNumber already exist",e);
        }
        return "注册成功";
    }

     */



}

