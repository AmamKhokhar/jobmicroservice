package com.learner.jobms.job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    void createjob(Job job);

    Job findById(Long id);

    boolean deleteJobById(Long id);

    boolean updateById(Long id , Job updatedjob);
}
