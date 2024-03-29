package org.example.examjee.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    @Column(name = "skills")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private Post post;

    @ManyToMany(targetEntity = Project.class)
    @JoinTable(name = "employe_project", joinColumns = { @JoinColumn(name = "employe_id") }, inverseJoinColumns = { @JoinColumn(name = "project_id") })
    private List<Project> projects;

    public Employe() {
        super();
    }

    public Employe(int id, String name, String email, List<String> skills, Post post) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.post = post;
    }

    public Employe(int id, String name, String email, List<String> skills, Post post, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.post = post;
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", skills=" + skills +
                ", post=" + post +
                ", projects=" + (long) projects.size() +
                '}';
    }
}
