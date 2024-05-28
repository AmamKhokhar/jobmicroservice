package com.learner.jobms.job;

import com.learner.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {

    List<JobDTO> findAll();
    void createjob(Job job);

    JobDTO findById(Long id);

    boolean deleteJobById(Long id);

    boolean updateById(Long id , Job updatedjob);
}
