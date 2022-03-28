package com.restonov.task.service.impl

import com.restonov.task.dao.EmployeeCategoryRepository
import com.restonov.task.dao.EmployeeRepository
import com.restonov.task.domain.Employee
import com.restonov.task.domain.EmployeeCategory
import com.restonov.task.exception.ServiceException
import spock.lang.Specification

class EmployeeCategoryServiceSpec extends Specification {

    EmployeeCategoryService categoryService
    EmployeeCategoryRepository categoryRepository = Mock()

    EmployeeCategory category
    List<EmployeeCategory> categories

    long id = 1L

    def 'setup'() {
        categoryService = new EmployeeCategoryService(categoryRepository)

        category = new EmployeeCategory(id:1, name:'admin')
        categories = [category]
    }

    def "test create"() {
        when:
        EmployeeCategory result = categoryService.create(category)

        then:
        1 * categoryRepository.findByName('admin') >> Optional.empty()
        1 * categoryRepository.save(category) >> category
        result == category
    }

    def "test findAll"() {
        when:
        List<EmployeeCategory> result = categoryService.findAll()

        then:
        1 * categoryRepository.findAll() >> categories
        result == categories
    }

    def "test findById -- correct execution"() {
        when:
        EmployeeCategory result = categoryService.findById(id)

        then:
        1 * categoryRepository.findById(id) >> Optional.of(category)
        result == category
    }

    def "test findById -- category not found"() {
        when:
        categoryService.findById(id)

        then:
        1 * categoryRepository.findById(id) >> Optional.empty()
        thrown(ServiceException)
    }

    def "test update -- correct execution"() {
        when:
        EmployeeCategory result = categoryService.update(id, category)

        then:
        1 * categoryRepository.findById(id) >> Optional.of(category)
        1 * categoryRepository.save(category) >> category
        result == category
    }

    def "test update -- category not found"() {
        when:
        categoryService.update(id, category)

        then:
        1 * categoryRepository.findById(id) >> Optional.empty()
        thrown(ServiceException)
    }

    def "test delete -- correct execution"() {
        when:
        categoryService.delete(id)

        then:
        1 * categoryRepository.findById(id) >> Optional.of(category)
        1 * categoryRepository.delete(category)
    }

    def "test delete -- employee not found"() {
        when:
        categoryService.delete(id)

        then:
        1 * categoryRepository.findById(id) >> Optional.empty()
        thrown(ServiceException)
    }
}
