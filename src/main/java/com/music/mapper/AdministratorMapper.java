package com.music.mapper;

import com.music.bean.Administrator;

/**
 * Created by 羊 on 2018/10/29.
 */
public interface AdministratorMapper {
    public void insertAdministrator(Administrator administrator);
    public Administrator selectAdmin(Administrator administrator);
}
