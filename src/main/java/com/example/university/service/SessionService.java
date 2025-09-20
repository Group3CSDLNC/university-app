package com.example.university.service;

import com.example.university.model.SessionUser;
import com.example.university.model.TaiKhoan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionService {

    private SessionUser currentUser;

    public void login(SessionUser user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public void setCurrentUser(SessionUser currentUser) {
        this.currentUser = currentUser;
    }

    public SessionUser getCurrentUser() {
        if (!isLoggedIn()) {
            throw new RuntimeException("Chua dang nhap!");
        }
        return currentUser;
    }
}

