show databases;
create database zoho;

use zoho;



/**
*  Activity 2
* Create table using the create table command for the above specified table
*/
create table StudentDetails(ID int primary key,FirstName varchar(50),LastName varchar(50),Gender enum('M','F','O'),Email varchar(100),YearOfBirth year);

truncate table StudentDetails;
drop table StudentDetails;

/**
*  Activity 3
* Alter above table add new Column 'Year Of Admission'
* Alter above table change Column Name 'Year Of Birth' to 'Date Of Birth'
* (hint ensure the data type of the same to be 'Date')
*
*/
alter table StudentDetails add column YearOfAdmission year;
alter table StudentDetails change column YearOfBirth DateOfBirth date;


/**
*  Activity 4
* Insert Data into the above table in such a way that there are minimum of
	- 2 students born in year 2000
	- 5 students with same year of admission
	- 2 students with same first name
	- 2 students with same last name
	- 2 students with same first and last name
*/
insert into StudentDetails values(1,'Eldhose','Saji','M','eldhosesaji@gmail.com','1999-08-13',2021);
insert into StudentDetails values(2,'Emmanu','George','M','emmanugeorge@gmail.com','1998-03-08',2019);
insert into StudentDetails values(3,'Eldhose','Saji','M','sajieldhose@gmail.com','1996-03-21',2018);
insert into StudentDetails values(4,'Basil','Saji','M','basilsaji@gmail.com','1995-12-31',2017);
insert into StudentDetails values(5,'Athul','Raj','M','athulraj@gmail.com','1998-01-21',2021);
insert into StudentDetails values(6,'Emmanu','Saju','M','emmanusaju@gmail.com','1999-01-26',2020);
insert into StudentDetails values(7,'Alen','George','M','alengeorge@gmail.com','1999-07-30',2022);
insert into StudentDetails values(8,'Ben','Tomy','M','bentomy@gmail.com','2000-02-27',2022);
insert into StudentDetails values(9,'Sethu','MS','F','sethulakshmi@gmail.com','2000-04-09',2021);
insert into StudentDetails values(10,'Fathima','Nazar','F','fathimanazar@gmail.com','2001-09-17',2021);
insert into StudentDetails values(11,'Manu','John','M','manujohn@gmail.com','1997-11-24',2019);
insert into StudentDetails values(12,'Biju','Skaria','M','bijuskaria@gmail.com','1990-05-05',2019);
insert into StudentDetails values(13,'Ajith','Jose','M','ajithjose@gmail.com','1990-12-11',2015);
insert into StudentDetails values(14,'Athul','Joseph','M','athuljoseph@gmail.com','1998-01-19',2015);
insert into StudentDetails values(15,'Fasil','KM','M','fasilkm@gmail.com','2000-07-06',2021);
insert into StudentDetails values(16,'Sethu','MS','F','sethums@gmail.com','1998-06-21',2018);


insert into StudentDetails values(1,'Eldhose','Saji','M','eldhosesaji@gmail.com','1999-08-13',2021);
insert into StudentDetails values(2,'Athul','Raj','M','athulraj@gmail.com','1998-01-21',2021);
insert into StudentDetails values(3,'Alen','George','M','alengeorge@gmail.com','1999-07-30',2022);
insert into StudentDetails values(4,'Sethu','MS','F','sethums@gmail.com','1998-06-21',2018);
insert into StudentDetails values(5,'Fasil','KM','M','fasilkm@gmail.com','2000-07-06',2021);
insert into StudentDetails values(6,'Fathima','Nazar','F','fathimanazar@gmail.com','2001-09-17',2021,'NA',7);

update StudentDetails set yearofadmission = 2022 where id = 6;
select * from StudentDetails;


/**
*  Activity 5
* Add New Column 'Branch' and 'Std' with appropriate data types and update entries for each column. 
* Branch for students below 10th std would be ‘NA’
*/
alter table StudentDetails add column Branch varchar(50);
alter table StudentDetails add column Std varchar(50);

SET SQL_SAFE_UPDATES=1;
update StudentDetails set Std = (Select floor(rand() * 6 + 7));
update StudentDetails set Branch = 'NA' where Std <= 10;
update StudentDetails set Branch = 'Science' where Std = 11;
update StudentDetails set Branch = 'Commerce' where Std = 12;

/**
*  Activity 6
* Find students count born in year 2000
*/
select count(*) from StudentDetails where year(DateOfBirth) = 2000; 


/**
*  Activity 7
* Group students based on year of admission along with number of students in each year
*/
select YearOfAdmission,count(*) as 'Students' from StudentDetails group by YearOfAdmission;


/**
*  Activity 8
* List number of students in each year of admission and also list them based on gender.
*/
select YearOfAdmission,count(*) as 'Students',gender from StudentDetails group by YearOfAdmission,gender order by YearOfAdmission;


/**
*  Activity 9
* List number of students with same first name, last name and first and last name
*/
select * from StudentDetails where (FirstName,LastName) in 
(Select FirstName,LastName from StudentDetails group by FirstName,LastName having count(*) > 1 );

select * from StudentDetails where FirstName in 
(Select FirstName from StudentDetails group by FirstName having count(FirstName) > 1 ) order by FirstName;

select * from StudentDetails where LastName in 
(Select LastName from StudentDetails group by LastName having count(LastName) > 1 ) order by LastName;

/**
*  Activity 10
* Find the year with the maximum and minimum number of students
*/
select YearOfAdmission,count(*) as count from StudentDetails group by YearOfAdmission order by count(*) desc limit 1;
select YearOfAdmission,count(*) as count from StudentDetails group by YearOfAdmission order by count(*) asc limit 1;


/**
*  Activity 11
* Order the students by DOB and list only the top 10 students.
*/
select * from StudentDetails order by DateOfBirth desc limit 10;