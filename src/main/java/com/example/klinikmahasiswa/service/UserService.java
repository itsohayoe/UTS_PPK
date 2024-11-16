package com.example.klinikmahasiswa.service;

import com.example.klinikmahasiswa.entity.User;
import com.example.klinikmahasiswa.entity.User.Gender;
import com.example.klinikmahasiswa.entity.User.Prodi;
import com.example.klinikmahasiswa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String KODE_PERAWAT = "ksrst1s"; // Kode untuk mengatur role PERAWAT

    public User registerUser(User user) {
        // Validasi role perawat
        if (user.getKode() != null && user.getKode().equals(KODE_PERAWAT)) {
            user.setRole(User.Role.PERAWAT);
        } else
            user.setRole(User.Role.PASIEN);

        // Validasi jenis kelamin (jk) - hanya boleh 'L' atau 'P'
        if (user.getJk() != Gender.L && user.getJk() != Gender.P) {
            throw new IllegalArgumentException("Isikan jenis kelamin dengan 'L' atau 'P'.");
        }

        // Validasi prodi - hanya boleh 'D4_KS', 'D3_ST', atau 'D4_ST'
        if (user.getProdi() != Prodi.D3_ST && user.getProdi() != Prodi.D4_ST && user.getProdi() != Prodi.D4_KS) {
            throw new IllegalArgumentException("Isikan prodi dengan 'D3_ST', 'D4_ST', atau 'D4_KS'.");
        }

        // Meng-enkripsi password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Menyimpan user ke repository
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean validateUser(Long id, String password) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false; // Pengguna tidak ditemukan
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setWhatsapp(updatedUser.getWhatsapp());
        existingUser.setBerat(updatedUser.getBerat());
        existingUser.setTinggi(updatedUser.getTinggi());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        if (updatedUser.getJk() != null) {
            if (updatedUser.getJk() != Gender.L && updatedUser.getJk() != Gender.P) {
                throw new IllegalArgumentException("Invalid gender. Allowed values are 'L' or 'P'.");
            }
            existingUser.setJk(updatedUser.getJk());
        }
        if (updatedUser.getProdi() != null) {
            if (updatedUser.getProdi() != Prodi.D3_ST && updatedUser.getProdi() != Prodi.D4_ST
                    && updatedUser.getProdi() != Prodi.D4_KS) {
                throw new IllegalArgumentException("Invalid prodi. Allowed values are 'D3_ST', 'D4_ST', or 'D4_KS'.");
            }
            existingUser.setProdi(updatedUser.getProdi());
        }
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
