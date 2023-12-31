package com.jovine.rems.service;

import com.jovine.rems.entity.Role;
import com.jovine.rems.entity.StandardResponse;
import com.jovine.rems.entity.User;
import com.jovine.rems.repository.RoleRepository;
import com.jovine.rems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<StandardResponse> createNewRole(Role role) {
        try {
            return StandardResponse.sendHttpResponse(true, "Operation was successful",
                    roleRepo.save(role), "200");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not create role");
        }
    }

    public ResponseEntity<StandardResponse> getAllRoles() {
        try {

            List<Role> roles = roleRepo.findAll();
            return StandardResponse.sendHttpResponse(true, "Operation was successful",
                    roles, "200");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not get roles");
        }
    }

    public ResponseEntity<StandardResponse> assignRoleToUser(Long userId, String roleName) {
        try {
            Role role = roleRepo.findByRoleName(roleName).get();
            User user;
            Optional<User> present = userRepo.findById(userId);
            if(present.isPresent()) {
                user = present.get();
                Set<Role> roles = user.getRole();
                roles.add(role);
                user.setRole(roles);
                user.setTag(roleName);

                userRepo.save(user);
                return StandardResponse.sendHttpResponse(true, "Successful");
            }else{
                return StandardResponse.sendHttpResponse(true, "User does not exist");
            }
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not assign role to user");
        }
    }

    public ResponseEntity<StandardResponse> updateRole(Role role) {
        try {
            roleRepo.save(role);
            return StandardResponse.sendHttpResponse(true, "Successful");
        } catch (Exception e) {
            return StandardResponse.sendHttpResponse(false, "Could not update role");
        }
        
    }
}
