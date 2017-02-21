package com.example2.janja.tas_project.listeners;

import com.example2.janja.tas_project.Entity.User;

import java.io.Serializable;

/**
 * Created by JanJa on 04.02.2017.
 */
public interface UserServiceListener extends Serializable{

     void loginSuccesful(User user);

     void loginError();
}
