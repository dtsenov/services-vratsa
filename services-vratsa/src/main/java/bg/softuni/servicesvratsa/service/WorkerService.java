package bg.softuni.servicesvratsa.service;

import bg.softuni.servicesvratsa.model.view.WorkerViewModel;

import java.util.List;

public interface WorkerService {

    List<WorkerViewModel> findAllWorkers();
}
