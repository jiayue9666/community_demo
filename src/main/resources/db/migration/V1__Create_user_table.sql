create TABLE PUBLIC.user
(id INT AUTO_INCREMENT PRIMARY KEY,account_id VARCHAR(100),name VARCHAR(50),token char(36),gmt_create bigint,gmt_modified bigint);