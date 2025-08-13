package com.wipro.fooddeliveryapp.notification.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.fooddeliveryapp.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
