package com.pd.eweltol.taskmanager.model;


import com.pd.eweltol.taskmanager.model.types.TaskStatus;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="TASKS")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User contractor;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    private TaskStatus status;

    private Date openDate;

    private Date changeStatusDate;



    public Task(){

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getContractorId() {
        return contractor;
    }

    public void setContractorId(User contractor) {
        this.contractor = contractor;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getChangeStatusDate() {
        return changeStatusDate;
    }

    public void setChangeStatusDate(Date changeStatusDate) {
        this.changeStatusDate = changeStatusDate;
    }
}
