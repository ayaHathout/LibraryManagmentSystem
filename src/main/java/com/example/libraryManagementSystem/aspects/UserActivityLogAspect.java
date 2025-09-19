package com.example.libraryManagementSystem.aspects;

import com.example.libraryManagementSystem.dtos.UserActivityLogDTO;
import com.example.libraryManagementSystem.dtos.UserDTO;
import com.example.libraryManagementSystem.dtos.UserResponseDTO;
import com.example.libraryManagementSystem.entities.User;
import com.example.libraryManagementSystem.enums.Action;
import com.example.libraryManagementSystem.enums.EntityType;
import com.example.libraryManagementSystem.mappers.UserActivityLogMapper;
import com.example.libraryManagementSystem.services.interfaces.UserActivityLogService;
import com.example.libraryManagementSystem.services.interfaces.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActivityLogAspect {
    @Autowired
    private UserService userService;

    @Autowired
    private UserActivityLogService userActivityLogService;

    @Pointcut("within(com.example.libraryManagementSystem.controllers..*)")
    public void allControllerMethods() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMappingMethods() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void httpMethodAnnotations() {}

    // all pointcuts
    @Pointcut("allControllerMethods() && (requestMappingMethods() || httpMethodAnnotations())")
    public void allEndpoints() {}

    // After returning advice
    @AfterReturning(pointcut = "allEndpoints()", returning = "result")
    public void logAfterEndpointExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        UserResponseDTO currentUser = getCurrentUser();
        String details = String.format("Method: %s, Class: %s", methodName, className);

        System.out.println("Method: " + methodName);
        System.out.println("Class: " + className);
        System.out.println("User: " + currentUser);
        System.out.println("------------------------");

        // Create log
        UserActivityLogDTO logDTO = new UserActivityLogDTO(
                determineAction(methodName),
                determineEntity(className),
                details,
                currentUser.id()
        );
        userActivityLogService.createUserActivityLog(logDTO);
    }

    private UserResponseDTO getCurrentUser() {
        String username = getCurrentUsername();
        return userService.getByUserName(username).orElse(null);
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return "Anonymous";
        Object principal = auth.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        } else if (principal instanceof String username) {
            return username;
        } else {
            return principal.toString();
        }
    }

    private Action determineAction(String methodName) {
        if (methodName.startsWith("create"))  return Action.CREATE;
        else if (methodName.startsWith("update"))  return Action.UPDATE;
        else if (methodName.startsWith("delete"))  return Action.DELETE;
        else if (methodName.startsWith("borrow"))  return Action.BORROW;
        else if (methodName.startsWith("return"))  return Action.RETURN;
        else if (methodName.startsWith("login"))  return Action.LOGIN;
        else if (methodName.startsWith("get"))  return Action.GET;
        return null;
    }

    private EntityType determineEntity(String className) {
        if (className.contains("Book"))  return EntityType.books;
        else if (className.contains("User"))  return EntityType.users;
        else if (className.contains("Publisher"))  return EntityType.publishers;
        else if (className.contains("Author"))  return EntityType.authors;
        else if (className.contains("Category"))  return EntityType.categories;
        else if (className.contains("Borrow"))  return EntityType.borrow_records;
        return null;
    }
}
