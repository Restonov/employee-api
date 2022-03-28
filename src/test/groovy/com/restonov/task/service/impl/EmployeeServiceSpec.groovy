package com.restonov.task.service.impl

import com.restonov.task.dao.EmployeeRepository
import com.restonov.task.domain.Employee
import com.restonov.task.domain.EmployeeCategory
import com.restonov.task.exception.ServiceException
import spock.lang.Specification

class EmployeeServiceSpec extends Specification {

    EmployeeService employeeService
    EmployeeRepository employeeRepository = Mock()

    Employee employee
    List<Employee> employees

    long id = 1L

    def 'setup'() {
        employeeService = new EmployeeService(employeeRepository)

        employee = new Employee(id: 1, name: "John", category: new EmployeeCategory())
        employees = [employee]
    }

    def "test create"() {
        when:
        Employee result = employeeService.create(employee)

        then:
        1 * employeeRepository.save(employee) >> employee
        result == employee
    }

    def "test findAll"() {
        when:
        List<Employee> result = employeeService.findAll()

        then:
        1 * employeeRepository.findAll() >> employees
        result == employees
    }

    def "test findById -- correct execution"() {
        when:
        Employee result = employeeService.findById(id)

        then:
        1 * employeeRepository.findById(id) >> Optional.of(employee)
        result == employee
    }

    def "test findById -- employee not found"() {
        when:
        employeeService.findById(id)

        then:
        1 * employeeRepository.findById(id) >> Optional.empty()
        thrown(ServiceException)
    }

    def "test update -- correct execution"() {
        when:
        Employee result = employeeService.update(id, employee)

        then:
        1 * employeeRepository.findById(id) >> Optional.of(employee)
        1 * employeeRepository.save(employee) >> employee
        result == employee
    }

    def "test update -- employee not found"() {
        when:
        employeeService.update(id, employee)

        then:
        1 * employeeRepository.findById(id) >> Optional.empty()
        thrown(ServiceException)
    }

    def "test delete -- correct execution"() {
        when:
        employeeService.delete(id)

        then:
        1 * employeeRepository.findById(id) >> Optional.of(employee)
        1 * employeeRepository.delete(employee)
    }

    def "test delete -- employee not found"() {
        when:
        employeeService.delete(id)

        then:
        1 * employeeRepository.findById(id) >> Optional.empty()
        thrown(ServiceException)
    }

    def "LoadUserByUsername"() {
        given:
        String name = 'John'

        when:
        Employee result = employeeService.loadUserByUsername(name)

        then:
        1 * employeeRepository.findByName(name) >> Optional.of(employee)
        result == employee
    }
}
