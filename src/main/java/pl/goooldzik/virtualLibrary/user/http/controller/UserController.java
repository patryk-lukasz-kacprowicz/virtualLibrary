package pl.goooldzik.virtualLibrary.user.http.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.goooldzik.virtualLibrary.user.dto.UserDTO;
import pl.goooldzik.virtualLibrary.user.http.request.UserStoreRequest;
import pl.goooldzik.virtualLibrary.user.http.request.UserUpdateRequest;
import pl.goooldzik.virtualLibrary.user.model.User;
import pl.goooldzik.virtualLibrary.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> index(@RequestParam(name = "showAll", required = false, defaultValue = "false") Boolean showAll) {
        return this.userService.index(showAll);
    }

    @GetMapping(path = "{userId}")
    public User show(@PathVariable Long userId) {
        return this.userService.show(userId);
    }

    @PostMapping
    public void store(@RequestBody UserStoreRequest request) {
        this.userService.store(request);
    }

    @PutMapping(path = "{userId}")
    public void update(@PathVariable("userId") Long userId,
                       @RequestBody UserUpdateRequest request) {
        this.userService.update(userId, request);
    }

    @DeleteMapping(path = "{userId}")
    public boolean destroy(@PathVariable Long userId) {
        return this.userService.destroy(userId);
    }

    @PatchMapping(path = "{userId}")
    public boolean recovery(@PathVariable Long userId) {
        return this.userService.recovery(userId);
    }

}
