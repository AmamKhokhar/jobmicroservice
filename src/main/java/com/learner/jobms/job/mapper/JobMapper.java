package com.learner.jobms.job.mapper;

import com.learner.jobms.job.Job;
import com.learner.jobms.job.dto.jobDTO;
import com.learner.jobms.job.external.Company;
import com.learner.jobms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static jobDTO mapToJobWithCompanyDTO(Job job , Company company , List<Review> reviews)
    {
     jobDTO jobDTO = new jobDTO();
     jobDTO.setId(job.getId());
     jobDTO.setDescription(job.getDescription());
     jobDTO.setTitle(job.getTitle());
     jobDTO.setLocation(job.getLocation());
     jobDTO.setMaxSalary(job.getMaxSalary());
     jobDTO.setMinSalary(job.getMinSalary());
     jobDTO.setCompany(company);
     jobDTO.setReviews(reviews);
     return jobDTO;
    }
}
