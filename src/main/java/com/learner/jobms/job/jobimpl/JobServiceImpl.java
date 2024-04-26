package com.learner.jobms.job.jobimpl;

import com.learner.jobms.job.Job;
import com.learner.jobms.job.JobRepository;
import com.learner.jobms.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

//    public  Long nextid = 1L;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createjob(Job job) {
//        job.setId(nextid++);
        jobRepository.save(job);
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
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
