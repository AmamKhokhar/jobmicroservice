package com.learner.jobms.job.jobimpl;

import com.learner.jobms.job.Job;
import com.learner.jobms.job.JobRepository;
import com.learner.jobms.job.JobService;
import com.learner.jobms.job.clients.CompanyClient;
import com.learner.jobms.job.clients.ReviewClient;
import com.learner.jobms.job.dto.JobDTO;
import com.learner.jobms.job.external.Company;
import com.learner.jobms.job.external.Review;
import com.learner.jobms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    CompanyClient companyClient;
    ReviewClient reviewClient;

    @Autowired
    RestTemplate restTemplate;

    public JobServiceImpl(JobRepository jobRepository ,
                          CompanyClient companyClient ,
                          ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

//    public  Long nextid = 1L;
    @Override
    @CircuitBreaker(name="companyBreaker")
    public List<JobDTO> findAll()
    {
        List<Job> jobs = jobRepository.findAll();

        return jobs.stream().map(this::convertToDTO).
                collect(Collectors.toList());
    }

    private JobDTO convertToDTO(Job job) {
//        RestTemplate restTemplate = new RestTemplate();
        Company company = companyClient.getCompany(job.getCompanyid());
        List<Review> reviews = reviewClient.getReviewsByCompanyId(job.getCompanyid());

        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job,company,reviews);
//        jobDTO.setCompany(company);
        return jobDTO;
    }

    @Override
    public void createjob(Job job) {
//        job.setId(nextid++);
        jobRepository.save(job);
    }

    @Override
    public JobDTO findById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateById(Long id, Job updatedjob) {
            Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent())
            {
                Job job = jobOptional.get();
                job.setTitle(updatedjob.getTitle());
                job.setDescription(updatedjob.getDescription());
                job.setMinSalary(updatedjob.getMinSalary());
                job.setMaxSalary(updatedjob.getMaxSalary());
                job.setLocation(updatedjob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}
