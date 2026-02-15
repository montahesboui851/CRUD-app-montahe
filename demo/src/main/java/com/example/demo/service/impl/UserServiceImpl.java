package com.example.demo.service.impl;

import com.example.demo.persistence.entity.User;
import com.example.demo.persistence.repository.BasicRepositoryUser;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BasicRepositoryUser basicRepositoryUser;

    @Override
    public List<UserVO> findAll() {
        List<UserVO> res = new ArrayList<>();

        List<User> userList = (List<User>) basicRepositoryUser.findAll();

        for (User u : userList) {
            UserVO newUser = new UserVO();
            newUser.setId(u.getId());
            newUser.setFirstName(u.getFirstName()); // correspond maintenant à UserVO
            newUser.setLastName(u.getLastName());   // correspond maintenant à UserVO
            res.add(newUser);
        }

        return res;
    }

    @Override
    public void createUser(UserVO userVO) {
        User newUser = new User();
        newUser.setFirstName(userVO.getFirstName());
        newUser.setLastName(userVO.getLastName());
        basicRepositoryUser.save(newUser);
    }

    @Override
    public void updateUser(Long id, UserVO userVO) {
        basicRepositoryUser.findById(id).ifPresent(u -> {
            u.setFirstName(userVO.getFirstName());
            u.setLastName(userVO.getLastName());
            basicRepositoryUser.save(u);
        });
    }

    @Override
    public void deleteUser(Long id) {
        if (basicRepositoryUser.existsById(id)) {
            basicRepositoryUser.deleteById(id);
        }
    }
}
