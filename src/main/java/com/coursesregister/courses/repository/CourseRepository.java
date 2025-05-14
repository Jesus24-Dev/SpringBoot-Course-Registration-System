
package com.coursesregister.courses.repository;

import com.coursesregister.courses.models.Course;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, UUID>{
    
}
