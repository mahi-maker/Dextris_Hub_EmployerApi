-- Sample data for testing purposes

-- Insert employers
INSERT INTO employers (id, name, email, company_name, company_description, location, website, industry) 
VALUES (1, 'John Smith', 'john@techcorp.com', 'TechCorp', 'Leading technology solutions provider', 'New York, NY', 'https://techcorp.com', 'Technology');

INSERT INTO employers (id, name, email, company_name, company_description, location, website, industry) 
VALUES (2, 'Sarah Johnson', 'sarah@healthplus.com', 'Health Plus', 'Healthcare services company', 'Boston, MA', 'https://healthplus.com', 'Healthcare');

-- Insert jobs
INSERT INTO jobs (id, title, description, location, employment_type, salary_min, salary_max, currency, experience, skills, active, posted_date, employer_id) 
VALUES (1, 'Senior Java Developer', 'Looking for an experienced Java developer with Spring Boot expertise.', 'New York, NY', 'Full-time', 120000, 150000, 'USD', '5+ years', 'Java, Spring Boot, Microservices, REST API', true, CURRENT_TIMESTAMP(), 1);

INSERT INTO jobs (id, title, description, location, employment_type, salary_min, salary_max, currency, experience, skills, active, posted_date, employer_id) 
VALUES (2, 'Frontend Developer', 'Seeking a frontend developer with React experience.', 'Remote', 'Full-time', 90000, 120000, 'USD', '3+ years', 'JavaScript, React, TypeScript, CSS', true, CURRENT_TIMESTAMP(), 1);

INSERT INTO jobs (id, title, description, location, employment_type, salary_min, salary_max, currency, experience, skills, active, posted_date, employer_id) 
VALUES (3, 'Healthcare Administrator', 'Managing daily operations of healthcare facility.', 'Boston, MA', 'Full-time', 80000, 100000, 'USD', '2+ years', 'Healthcare Administration, Management, Communication', true, CURRENT_TIMESTAMP(), 2);

-- Insert applications
INSERT INTO applications (id, job_id, applicant_name, applicant_email, resume_url, cover_letter, status, submission_date) 
VALUES (1, 1, 'Mike Johnson', 'mike@example.com', 'https://resume-bucket.s3.amazonaws.com/mike-resume.pdf', 'I am excited to apply for this position...', 'SUBMITTED', CURRENT_TIMESTAMP());

INSERT INTO applications (id, job_id, applicant_name, applicant_email, resume_url, cover_letter, status, submission_date) 
VALUES (2, 1, 'Emily Davis', 'emily@example.com', 'https://resume-bucket.s3.amazonaws.com/emily-resume.pdf', 'I believe my experience makes me a great fit...', 'UNDER_REVIEW', CURRENT_TIMESTAMP());

INSERT INTO applications (id, job_id, applicant_name, applicant_email, resume_url, cover_letter, status, submission_date) 
VALUES (3, 2, 'David Wilson', 'david@example.com', 'https://resume-bucket.s3.amazonaws.com/david-resume.pdf', 'I have been working with React for 4 years...', 'SHORTLISTED', CURRENT_TIMESTAMP());

INSERT INTO applications (id, job_id, applicant_name, applicant_email, resume_url, status, submission_date) 
VALUES (4, 3, 'Lisa Brown', 'lisa@example.com', 'https://resume-bucket.s3.amazonaws.com/lisa-resume.pdf', 'SUBMITTED', CURRENT_TIMESTAMP());