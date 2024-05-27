package com.learner.jobms.job.mapper;

import com.learner.jobms.job.Job;
import com.learner.jobms.job.dto.JobWithCompanyDTO;
import com.learner.jobms.job.external.Company;

public class JobMapper {
    public static JobWithCompanyDTO mapToJobWithCompanyDTO(Job job , Company company)
    {
     JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
     jobWithCompanyDTO.setId(job.getId());
     jobWithCompanyDTO.setDescription(job.getDescription());
     jobWithCompanyDTO.setTitle(job.getTitle());
     jobWithCompanyDTO.setLocation(job.getLocation());
     jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
     jobWithCompanyDTO.setMinSalary(job.getMinSalary());
     jobWithCompanyDTO.setCompany(company);

     return jobWithCompanyDTO;
    }
}
