package com.ceiba.domain.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.repository.ParkingRecordRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingRecordTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ParkingRecordRepository repositoryParkingRecord;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Employee alex = new Employee("alex");
        entityManager.persist(alex);
        entityManager.flush();
     
        // when
        Employee found = employeeRepository.findByName(alex.getName());
     
        // then
        assertThat(found.getName())
          .isEqualTo(alex.getName());
    }

}
