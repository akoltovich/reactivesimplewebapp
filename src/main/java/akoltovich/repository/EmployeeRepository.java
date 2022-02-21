package akoltovich.repository;

import akoltovich.entity.Employee;
import akoltovich.entity.Gender;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {

    Flux<Employee> getEmployeesByFirstName(String firstName);

    Flux<Employee> getEmployeesByLastName(String lastName);

    Flux<Employee> getEmployeesByDepartmentId(Long departmentId);

    Flux<Employee> getEmployeesByJobTitle(String jobTitle);

    Flux<Employee> getEmployeesByGender(Gender gender);

    Flux<Employee> getEmployeesByDateOfBirth(LocalDate birthDate);
}
