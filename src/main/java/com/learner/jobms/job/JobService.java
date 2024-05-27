package com.learner.jobms.job;

import com.learner.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {

    List<JobWithCompanyDTO> findAll();
    void createjob(Job job);

    JobWithCompanyDTO findById(Long id);

    boolean deleteJobById(Long id);

    boolean updateById(Long id , Job updatedjob);
}
