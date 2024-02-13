package com.api.eventdrivenuserservice.web;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid User user) {

    }
}
