package com.example.finally_project_boot.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq",sequenceName = "lesson_seq",allocationSize = 1)
    private Long id;
    @Column(name = "lesson_name")
    private String lessonName;

    public Lesson(String lessonName) {
        this.lessonName = lessonName;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lesson")
    @JsonIgnore
    private List<Task> taskList;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Video video;

    public void addTask(Task task){
        taskList.add(task);
    }
}
