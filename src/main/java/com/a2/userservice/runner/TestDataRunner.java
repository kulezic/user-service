package com.a2.userservice.runner;

import com.a2.userservice.domain.Admin;
import com.a2.userservice.domain.Role;
import com.a2.userservice.domain.UserRank;
import com.a2.userservice.repository.AdminRepository;
import com.a2.userservice.repository.RoleRepository;
import com.a2.userservice.repository.UserRankRepository;
import com.a2.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private UserRankRepository userRankRepository;
    private AdminRepository adminRepository;

    public TestDataRunner(RoleRepository roleRepository, UserRepository userRepository, UserRankRepository userRankRepository, AdminRepository adminRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userRankRepository = userRankRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Insert roles
        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        //Insert admin
        Admin admin= new Admin();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);
        adminRepository.save(admin);
        //User statuses
        userRankRepository.save(new UserRank("Gold",10000,Integer.MAX_VALUE,0.8));
        userRankRepository.save(new UserRank("Silver",1000,9999,0.9));
        userRankRepository.save(new UserRank("Bronze",0,999,1.0));

    }
}
