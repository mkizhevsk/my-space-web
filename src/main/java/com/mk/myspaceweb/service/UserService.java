package com.mk.myspaceweb.service;

import com.mk.myspaceweb.data.entity.Authority;
import com.mk.myspaceweb.data.entity.AuthorityId;
import com.mk.myspaceweb.data.entity.User;
import com.mk.myspaceweb.data.repository.AuthorityRepository;
import com.mk.myspaceweb.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Value("${app.default-password}")
    private String defaultPassword;

    private final String BASIC_ROLE = "ROLE_BASIC";

    @Transactional
    public void checkUserNameAndCreate(String username) {
        var existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            var user = new User();
            user.setUsername(username);
            user.setPassword(defaultPassword);
            user.setEnabled(true);
            userRepository.save(user);

            this.addAuthorityToUser(user, BASIC_ROLE);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        // Remove the {noop} prefix for displaying purposes
        users.forEach(this::removeNoopPrefix);
        return users;
    }

    public Optional<User> getUserByUsername(String username) {
        Optional<User> user = userRepository.findById(username);
        // Remove the {noop} prefix for displaying purposes
        user.ifPresent(this::removeNoopPrefix);
        return user;
    }

    public User createUser(User user) {
        // Prefix the password with {noop}
        user.setPassword("{noop}" + user.getPassword());
        return userRepository.save(user);
    }

    public void updateUser(String username, User userFromForm) {
        var user = userRepository.findByUsername(username);
        user.setPassword("{noop}" + userFromForm.getPassword());
        user.setEnabled(userFromForm.isEnabled());
        user.setAuthorities(userFromForm.getAuthorities());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String username) {
        System.out.println(username);
        var user = userRepository.findByUsername(username);
        if (user != null) {
            System.out.println(user.getUsername() + " " + user.getAuthorities().size());
            for (Authority authority : user.getAuthorities()) {
                authorityRepository.delete(authority);
            }
            try {
                userRepository.delete(user);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void removeNoopPrefix(User user) {
        if (user.getPassword().startsWith("{noop}")) {
            user.setPassword(user.getPassword().substring(6));
        }
    }

    // Authority
    public void addAuthorityToUser(User user, String authorityName) {
        var authorityId = new AuthorityId(user.getUsername(), authorityName);
        Authority authority = new Authority(authorityId, user);
        authorityRepository.save(authority);
    }
}
