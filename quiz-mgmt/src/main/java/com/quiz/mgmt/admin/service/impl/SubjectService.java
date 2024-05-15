package com.quiz.mgmt.admin.service.impl;

import com.quiz.mgmt.admin.model.SubjectDto;
import com.quiz.mgmt.entity.Subject;
import com.quiz.mgmt.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

@Autowired
private SubjectRepository subjectRepository;

public List<SubjectDto> getAllSubjects() {
    return subjectRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
}

public SubjectDto getSubjectById(int id) {
    return convertToDto(subjectRepository.findById(id).orElse(null));
}

public SubjectDto createSubject(SubjectDto subjectDto) {
    Subject subject = convertToEntity(subjectDto);
    subject.setCreatedAt(new Date());
    return convertToDto(subjectRepository.save(subject));
}

public SubjectDto updateSubject(SubjectDto subjectDto) {
    Subject existingSubject = subjectRepository.findById(subjectDto.getId())
            .orElseThrow(() -> new RuntimeException("Subject not found"));

    existingSubject.setSubjectName(subjectDto.getSubjectName());
    existingSubject.setUpdatedAt(new Date());

    return convertToDto(subjectRepository.save(existingSubject));
}

public void deleteSubject(int id) {
    subjectRepository.deleteById(id);
}

private SubjectDto convertToDto(Subject subject) {
    SubjectDto subjectDto = new SubjectDto();
    subjectDto.setId(subject.getId());
    subjectDto.setSubjectName(subject.getSubjectName());
    subjectDto.setCreatedAt(subject.getCreatedAt());
    subjectDto.setUpdatedAt(subject.getUpdatedAt());
    return subjectDto;
}

private Subject convertToEntity(SubjectDto subjectDto) {
    Subject subject = new Subject();
    subject.setId(subjectDto.getId());
    subject.setSubjectName(subjectDto.getSubjectName());
    subject.setCreatedAt(subjectDto.getCreatedAt());
    subject.setUpdatedAt(subjectDto.getUpdatedAt());
    return subject;
}
}
