package akoltovich.router;

import akoltovich.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class EmployeeRouter {

    @Bean
    public RouterFunction<ServerResponse> route(EmployeeHandler employeeHandler) {
        return RouterFunctions
                .route(
                        GET("/employees")
                                .and(accept(APPLICATION_JSON)), employeeHandler::getAllEmployees)
                .andRoute(
                        GET("/employees/{id}")
                                .and(accept(APPLICATION_JSON)), employeeHandler::getEmployeeById)
                .andRoute(
                        POST("/employees")
                                .and(accept(APPLICATION_JSON)), employeeHandler::addEmployee)
                .andRoute(
                        PUT("/employees/{id}")
                                .and(accept(APPLICATION_JSON)), employeeHandler::updateEmployee)
                .andRoute(
                        DELETE("/employees/{id}")
                                .and(accept(APPLICATION_JSON)), employeeHandler::deleteEmployee)
                .andRoute(
                        GET("/employees/firstName")
                                .and(accept(APPLICATION_JSON)), employeeHandler::getEmployeesByFirstName)
                .andRoute(
                        GET("/employees/lastName")
                                .and(accept(APPLICATION_JSON)), employeeHandler::getEmployeesByLastName)
                .andRoute(
                        GET("/employees/departmentId")
                                .and(accept(APPLICATION_JSON)), employeeHandler::getEmployeesByDepartmentId)
                .andRoute(
                        GET("/employees/jobTitle")
                                .and(accept(APPLICATION_JSON)), employeeHandler::getEmployeesByJobTitle)
                .andRoute(
                        GET("/employees/gender")
                                .and(accept(APPLICATION_JSON)), employeeHandler::getEmployeesByGender)
                .andRoute(
                        GET("/employees/birthDate")
                                .and(accept(APPLICATION_JSON)), employeeHandler::getEmployeesByBirthDate);
    }
}
