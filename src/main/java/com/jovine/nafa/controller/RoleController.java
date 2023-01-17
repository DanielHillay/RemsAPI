package com.jovine.nafa.controller;

import com.jovine.nafa.entity.Role;
import com.jovine.nafa.entity.StandardResponse;
import com.jovine.nafa.service.RoleService;
import com.jovine.nafa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @PostMapping({"/createnewrole"})
    public ResponseEntity<StandardResponse> createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }

    @GetMapping("/getallroles")
    public ResponseEntity<StandardResponse> getAllRoles(){
        return roleService.getAllRoles();
    }
    @PutMapping("/updaterole")
    public ResponseEntity<StandardResponse> updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    @PostMapping("/assignroletouser")
    public ResponseEntity<StandardResponse> assignRoleToUser(@RequestParam("userId") Long userId, @RequestParam("roleName") String roleName){
        return roleService.assignRoleToUser(userId, roleName);
    }
}
