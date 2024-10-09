package com.diypeter.service.sys.service.impl;

import com.diypeter.framework.entity.PageR;
import com.diypeter.framework.enums.AccountStatusCode;
import com.diypeter.service.sys.mapper.SysUserMapper;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserAddDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserDeleteDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserEditDto;
import com.diypeter.service.sys.pojo.dto.sysuser.SysUserSelectDto;
import com.diypeter.service.sys.pojo.po.SysUser;
import com.diypeter.service.sys.service.SysUserService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.diypeter.service.sys.pojo.po.table.SysUserTableDef.SYS_USER;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public PageR<SysUser> pageList(SysUserSelectDto sysUserSelectDto) {

        Page<SysUser> page = this.page(
                new Page<>(sysUserSelectDto.getPage(), sysUserSelectDto.getLimit()),
                QueryWrapper.create()
                        .where(SYS_USER.ACCOUNT_NAME.like(sysUserSelectDto.getAccountName(), If::hasText))
                        .and(SYS_USER.PHONE.like(sysUserSelectDto.getPhone(), If::hasText))
        );

        return new PageR<>(page.getPageNumber(), page.getPageSize(), page.getTotalRow(), page.getRecords());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addSysUser(SysUserAddDto sysUserAddDto) {

        SysUser sysUser = new SysUser();
        sysUser.setAccountName(sysUserAddDto.getAccountName());
        sysUser.setPassword(sysUserAddDto.getPassword());
        sysUser.setPhone(sysUserAddDto.getPhone());
        sysUser.setAccountStatusCd(AccountStatusCode.ENABLE.code);

        return save(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editSysUser(SysUserEditDto sysUserEditDto) {

        SysUser sysUser = new SysUser();
        sysUser.setId(sysUserEditDto.getId());
        sysUser.setAccountName(sysUserEditDto.getAccountName());
        sysUser.setPhone(sysUserEditDto.getPhone());

        return updateById(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteSysUser(SysUserDeleteDto sysUserDeleteDto) {
        return removeByIds(sysUserDeleteDto.getIds());
    }
}
