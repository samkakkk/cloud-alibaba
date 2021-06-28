package com.javadaily.dubbo.api;

import com.javadaily.dubbo.dto.AccountDTO;

/**
 * <p>
 * <code>AccountService</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/12/7 9:48
 */
public interface AccountService {

    AccountDTO getByCode(String accountCode);

}
