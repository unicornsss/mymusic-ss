package com.music.service.impl;

import com.music.bean.Administrator;
import com.music.mapper.AdministratorMapper;
import com.music.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by 羊 on 2018/10/29.
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public void insertAdministrator(Administrator administrator) {
        administratorMapper.insertAdministrator(administrator);
    }

    @Override
    public Administrator selectAdmin(Administrator administrator) {
        return administratorMapper.selectAdmin(administrator);
    }
}
