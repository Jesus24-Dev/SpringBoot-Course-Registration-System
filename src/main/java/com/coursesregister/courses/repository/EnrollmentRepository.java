
package com.coursesregister.courses.repository;

import com.coursesregister.courses.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
    
}
