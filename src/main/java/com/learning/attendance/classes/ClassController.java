package com.learning.attendance.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @GetMapping
    public List<Class> getAllPackages() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable UUID id) {
        return classService.getClassById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Class createClass(@RequestBody Class aClass) {
        return classService.createClass(aClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable UUID id, @RequestBody Class classDetails) {
        return ResponseEntity.ok(classService.updateClass(id, classDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable UUID id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
