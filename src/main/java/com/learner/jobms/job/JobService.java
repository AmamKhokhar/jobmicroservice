package com.learner.jobms.job;

import com.learner.jobms.job.dto.jobDTO;

import java.util.List;

public interface JobService {

    List<jobDTO> findAll();
    void createjob(Job job);

    jobDTO findById(Long id);

    boolean deleteJobById(Long id);

    boolean updateById(Long id , Job updatedjob);
}
