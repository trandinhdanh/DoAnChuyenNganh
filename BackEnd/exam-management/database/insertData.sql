use exam_management;

-- Create role
INSERT INTO role(name)
VALUES
    ('Admin'),
    ('Teacher'),
    ('Student');
INSERT INTO user(username,password,status)
    VALUES ('admin','$2a$10$lV7D/EEVAfqyhMAGlahe8O87.Ox/p29xJoJ00RBJMUw3He27ssp9K','active');
INSERT INTO user_role(user_id,role_id)
VALUES (1,1);
