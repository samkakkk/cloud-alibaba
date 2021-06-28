package com.javadaily.account.controller;

import com.javadaily.account.po.BlogUser;
import com.javadaily.account.service.BlogUserService;
import com.javadaily.base.ResultData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * <code>BlogUserController</code>
 * </p>
 * Description:
 * restful 风格
 * @author jianzh5
 * @date 2021/1/12 13:47
 */
@RestController
@RequestMapping("/blog")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BlogUserController {

    private final BlogUserService blogUserService;

    @PostMapping("/user")
    public ResultData<String> add(@RequestBody BlogUser blogUser){
        log.info("insert blogUser:{}",blogUser);
        blogUserService.save(blogUser);
        return ResultData.success("insert blogUser succeed");
    }


    /**
     * 根据id查找用户
     */
    @GetMapping("/user/{id}")
    public ResultData<BlogUser> getUser(@PathVariable int id){
        BlogUser blogUser = blogUserService.getById(id);
        return ResultData.success(blogUser);
    }


    /**
     * 根据id删除
     */
    @DeleteMapping("/user/{id}")
    public ResultData<String> deleteUser(@PathVariable int id){
        blogUserService.removeById(id);
        return ResultData.success("delete blogUser succeed");
    }


    /**
     * 根据id修改
     */
    @PutMapping("/user/{id}")
    public ResultData<String> editUser(@PathVariable int id, @RequestBody BlogUser blogUser){
        blogUser.setId(id);
        blogUserService.updateById(blogUser);
        return ResultData.success("update blogUser succeed");
    }


}
