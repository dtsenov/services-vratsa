package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.RoleEntity;
import bg.softuni.servicesvratsa.model.enums.RoleNameEnum;
import bg.softuni.servicesvratsa.repository.RoleRepository;
import bg.softuni.servicesvratsa.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {

        if (roleRepository.count() != 0) {
            return;
        }

        Arrays.stream(RoleNameEnum.values())
                .forEach(roleNameEnum -> {
                    RoleEntity role = new RoleEntity();
                    role.setRole(roleNameEnum);

                    switch (roleNameEnum) {
                        case BOSS:
                            role.setDescription("Шефът може всичко");
                            break;
                        case WORKER:
                            role.setDescription("Работникът изпълнява задачите от шефа");
                            break;
                        case CLIENT:
                            role.setDescription("Клиентът е цар");
                            break;
                    }

                    roleRepository.save(role);
                });

    }
}
