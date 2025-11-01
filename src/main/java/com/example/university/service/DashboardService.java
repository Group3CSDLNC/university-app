package com.example.university.service;

import com.example.university.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    public Map<String, Object> getDashboardSummary() {
        return dashboardRepository.getDashboardSummary();
    }

    public Map<String, Object> getGiangVienSummary(int maGV) {
        return dashboardRepository.getGiangVienSummary(maGV);
    }
    public Map<String, Object> getSinhVienDashboard(int maSV) {
        return dashboardRepository.getSinhVienDashboard(maSV);
    }
}

