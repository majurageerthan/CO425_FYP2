#!/usr/bin/env bash

mysql -u user -ppassword students_log_data_fyp < e14_gender.sql
mysql -u user -ppassword students_log_data_fyp < e15_gender.sql
mysql -u user -ppassword students_log_data_fyp < e16_gender.sql
mysql -u user -ppassword students_log_data_fyp < e14_grades.sql
mysql -u user -ppassword students_log_data_fyp < e15_grades.sql
mysql -u user -ppassword students_log_data_fyp < e16_grades.sql
mysql -u user -ppassword students_log_data_fyp < e14_logs.sql
mysql -u user -ppassword students_log_data_fyp < e15_logs.sql
mysql -u user -ppassword students_log_data_fyp < e16_logs.sql