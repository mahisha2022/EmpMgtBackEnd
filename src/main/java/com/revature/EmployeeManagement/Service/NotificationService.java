package com.revature.EmployeeManagement.Service;

import com.revature.EmployeeManagement.Model.Employee;
import com.revature.EmployeeManagement.Model.Leave;
import com.revature.EmployeeManagement.Model.Notification;
import com.revature.EmployeeManagement.Repositoty.EmployeeRepository;
import com.revature.EmployeeManagement.Repositoty.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Notification sendNotification(Notification notification, Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findManagerByEmployee(employee.getId());
        Notification newNotification = new Notification();
        newNotification.setEmployee(notification.getEmployee());
        newNotification.setMessage(notification.getMessage());
        return notificationRepository.save(notification);
    }

    public Notification leaveRequestNotification(Employee manager, Employee employee, Leave leave) {

        String message = "Employee " + employee.getFirstName() + " has requested leave from " +
                " to " + leave.getStartDate() + leave.getEndDate();

        Notification notification = new Notification(message, employee);

        return notificationRepository.save(notification);
    }
}
