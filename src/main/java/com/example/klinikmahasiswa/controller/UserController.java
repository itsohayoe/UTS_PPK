package com.example.klinikmahasiswa.controller;

import com.example.klinikmahasiswa.entity.User;
import com.example.klinikmahasiswa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam Long id, @RequestParam String password) {
        String response;
        // Validasi pengguna login
        if (userService.validateUser(id, password)) {
            response = "Login berhasil";
            return ResponseEntity.ok(response);
        } else {
            response = "Id atau Password salah";
            return ResponseEntity.status(401).body(response);
        }
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<String> editUser(@PathVariable Long id, @RequestBody User updatedUser) {
        userService.updateUser(id, updatedUser);
        return ResponseEntity.ok("Profil berhasil diperbarui.");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Akun berhasil dihapus.");
    }
}
