package com.javadaily.account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javadaily.account.mapper.BlogUserMapper;
import com.javadaily.account.po.BlogUser;
import com.javadaily.account.service.BlogUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * BlogUser Service 接口层
 * @author JAVA日知录
 * @date 2021/1/12 15:09
 */
@Service
@Log4j2
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {


}
