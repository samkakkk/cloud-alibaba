package com.javadaily.valid;

import javax.validation.groups.Default;
/**
 * Description:
 *
 * @author Jam
 * @date 2021/5/18 16:23
 */
public interface ValidGroup extends Default{
    interface Crud extends ValidGroup{
        /**
         * 添加入参分组
         */
        interface Create extends Crud{

        }

        /**
         * 修改入参分组
         */
        interface Update extends Crud{

        }

        /**
         * 查询入参分组
         */
        interface Query extends Crud{

        }

        /**
         * 删除入参分组
         */
        interface Delete extends Crud{

        }
    }
}
