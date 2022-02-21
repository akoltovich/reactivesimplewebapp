package akoltovich.service;

import akoltovich.entity.Employee;
import akoltovich.entity.Gender;
import akoltovich.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    public EmployeeService(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    public EmployeeService() {
    }

    public Mono<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Mono<Employee> addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Mono<Void> deleteEmployee(Long employeeId) {
        return employeeRepository.deleteById(employeeId);
    }

    public Mono<Employee> updateEmployee(Employee employee, Long employeeId) {
        return employeeRepository.findById(employeeId)
                .flatMap(e -> {
                    employee.setEmployeeId(e.getEmployeeId());
                    employee.setFirstName(e.getFirstName());
                    employee.setLastName(e.getLastName());
                    employee.setDepartmentId(e.getDepartmentId());
                    employee.setJobTitle(e.getJobTitle());
                    employee.setGender(e.getGender());
                    employee.setDateOfBirth(e.getDateOfBirth());
                    return employeeRepository.save(employee);
                });
    }

    public Flux<Employee> getEmployeesByFirstName(String firstName) {
        return (firstName != null) ? employeeRepository.getEmployeesByFirstName(firstName) : employeeRepository.findAll();
    }

    public Flux<Employee> getEmployeesByLastName(String lastName) {
        return (lastName != null) ? employeeRepository.getEmployeesByLastName(lastName) : employeeRepository.findAll();
    }

    public Flux<Employee> getEmployeesByDepartmentId(Long departmentId) {
        return (departmentId != 0) ? employeeRepository.getEmployeesByDepartmentId(departmentId) : employeeRepository.findAll();
    }

    public Flux<Employee> getEmployeesByJobTitle(String jobTitle) {
        return (jobTitle != null) ? employeeRepository.getEmployeesByJobTitle(jobTitle) : employeeRepository.findAll();
    }

    public Flux<Employee> getEmployeesByGender(Gender gender) {
        return (gender != null) ? employeeRepository.getEmployeesByGender(gender) : employeeRepository.findAll();
    }

    public Flux<Employee> getEmployeesByDateOfBirth(LocalDate birthDate) {
        return (birthDate != null) ? employeeRepository.getEmployeesByDateOfBirth(birthDate) : employeeRepository.findAll();
    }
}
