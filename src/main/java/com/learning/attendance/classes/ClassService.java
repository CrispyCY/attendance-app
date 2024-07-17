package com.learning.attendance.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<Class> getClassById(UUID id) {
        return classRepository.findById(id);
    }

    public Class createClass(Class aClass) {
        return classRepository.save(aClass);
    }

    public Class updateClass(UUID id, Class classDetails) {
        Class aClass = classRepository.findById(id).orElseThrow(() -> new RuntimeException("Class not found"));

        aClass.setName(classDetails.getName());

        return classRepository.save(aClass);
    }

    public void deleteClass(UUID id) {
        Class aClass = classRepository.findById(id).orElseThrow(() -> new RuntimeException("Package not found"));
        classRepository.delete(aClass);
    }
}
