package bg.softuni.servicesvratsa.service.impl;

import bg.softuni.servicesvratsa.model.entity.UserEntity;
import bg.softuni.servicesvratsa.model.enums.RoleNameEnum;
import bg.softuni.servicesvratsa.model.view.WorkerViewModel;
import bg.softuni.servicesvratsa.repository.RoleRepository;
import bg.softuni.servicesvratsa.repository.UserRepository;
import bg.softuni.servicesvratsa.service.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public WorkerServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WorkerViewModel> findAllWorkers() {

        List<UserEntity> allByRole = userRepository.findAllByRole(roleRepository.findByRole(RoleNameEnum.WORKER));
        List<WorkerViewModel> workers = new ArrayList<>();

        allByRole.forEach(userEntity -> {
            WorkerViewModel worker = modelMapper.map(userEntity, WorkerViewModel.class);
            workers.add(worker);
        });

        return workers;
    }
}

