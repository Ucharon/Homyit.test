package com.homyit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.homyit.entity.DO.Menu;
import com.homyit.service.MenuService;
import com.homyit.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author charon
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2022-10-05 16:06:30
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




