package akoltovich.handler;

import akoltovich.entity.Employee;
import akoltovich.entity.Gender;
import akoltovich.service.EmployeeService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class EmployeeHandler {

    private final EmployeeService employeeService;

    public EmployeeHandler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Mono<ServerResponse> getEmployeeById(ServerRequest serverRequest) {
        Mono<Employee> employeeMono = employeeService.getEmployeeById(
                Long.parseLong(serverRequest.pathVariable("id")));
        return employeeMono.flatMap(employee -> ServerResponse.ok()
                        .body(fromValue(employee)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAllEmployees(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(employeeService.getAllEmployees(), Employee.class);
    }

    public Mono<ServerResponse> addEmployee(ServerRequest serverRequest) {
        Mono<Employee> employeeMono = serverRequest.bodyToMono(Employee.class);
        return employeeMono.flatMap(employee ->
                ServerResponse.status(OK)
                        .contentType(APPLICATION_JSON)
                        .body(employeeService.addEmployee(employee), Employee.class));
    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest serverRequest) {
        final long employeeId = Long.parseLong(serverRequest.pathVariable("employee_id"));
        return ServerResponse.noContent().build(employeeService.deleteEmployee(employeeId));
    }

    public Mono<ServerResponse> updateEmployee(ServerRequest serverRequest) {
        final long employeeId = Long.parseLong(serverRequest.pathVariable("employee_id"));
        Mono<Employee> employeeMono = serverRequest.bodyToMono(Employee.class);
        return employeeMono.flatMap(employee ->
                ServerResponse.status(OK)
                        .contentType(APPLICATION_JSON)
                        .body(employeeService.updateEmployee(employee, employeeId), Employee.class));
    }

    public Mono<ServerResponse> getEmployeesByFirstName(ServerRequest serverRequest) {
        String firstName = serverRequest.queryParam("first_name").orElse(null);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(employeeService.getEmployeesByFirstName(firstName), Employee.class);
    }

    public Mono<ServerResponse> getEmployeesByLastName(ServerRequest serverRequest) {
        String lastName = serverRequest.queryParam("last_name").orElse(null);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(employeeService.getEmployeesByLastName(lastName), Employee.class);
    }

    public Mono<ServerResponse> getEmployeesByDepartmentId(ServerRequest serverRequest) {
        Long departmentId = Long.parseLong(Objects.requireNonNull(serverRequest.queryParam("department_id").orElse(null)));
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(employeeService.getEmployeesByDepartmentId(departmentId), Employee.class);
    }

    public Mono<ServerResponse> getEmployeesByJobTitle(ServerRequest serverRequest) {
        String jobTitle = serverRequest.queryParam("job_title").orElse(null);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(employeeService.getEmployeesByJobTitle(jobTitle), Employee.class);
    }

    public Mono<ServerResponse> getEmployeesByGender(ServerRequest serverRequest) {
        Gender gender = Gender.valueOf(serverRequest.queryParam("gender").orElse(null));
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(employeeService.getEmployeesByGender(gender), Employee.class);
    }

    public Mono<ServerResponse> getEmployeesByBirthDate(ServerRequest serverRequest) {
        LocalDate dateOfBirth = LocalDate.parse(Objects.requireNonNull(serverRequest.queryParam("date_of_birth").orElse(null)));
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(employeeService.getEmployeesByDateOfBirth(dateOfBirth), Employee.class);
    }

}
