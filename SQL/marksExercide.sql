use zoho;

drop table marks;

truncate table marks;
/**
*  Activity 1
* Define PK for StudentDetails table. And try searching with ID and then Email ID 
* (Hint ask them to create an index and ask them to explain the need of index).
*/
alter table StudentDetails add primary key (ID);

create index id_index on StudentDetails (ID);
create index email_index on StudentDetails (Email);

Select * from StudentDetails where ID = 1;
Select * from StudentDetails where email = 'alengeorge@gmail.com';

/**
* Activity 2
* Design and Create New Table Marks 
*/ 

create table marks (MarkID int primary key, ID int,Subject varchar(50), Marks int, foreign key (ID) references StudentDetails(ID));



insert into marks values(1,1,'Maths',95);
insert into marks values(2,1,'English',75);
insert into marks values(3,1,'Science',82);
insert into marks values(4,2,'Maths',16);
insert into marks values(5,2,'English',45);
insert into marks values(6,2,'Science',60);
insert into marks values(7,3,'Maths',91);
insert into marks values(8,3,'English',13);
insert into marks values(9,3,'Science',65);
insert into marks values(10,4,'Maths',89);
insert into marks values(11,4,'English',55);
insert into marks values(12,4,'Science',99);
insert into marks values(13,6,'Maths',87);
insert into marks values(14,6,'English',23);
insert into marks values(15,6,'Science',99);
Select * from marks;
/**
* Activity 3
* List first name , subject, mark, Std of the students whose marks is greater than 90% in 
* any Subject of the current academic year. 
*/

Select students.FirstName,marks.Subject,marks.Marks,students.Std from Marks marks
join StudentDetails students on marks.ID = students.ID
where marks.Marks > 90 and students.YearOfAdmission = YEAR(now()) - 1;

/**
* Activity 4
* Implement the above using sub query (hint check for sub query if 3 is used via join or vice versa)
*/
Select students.FirstName,marks.Subject,marks.Marks,students.Std from Marks marks
join StudentDetails students on marks.ID = students.ID
where students.YearOfAdmission = YEAR(now()) - 1 and marks.markid in (
select markid from marks where marks > 90);


/**
* Activity 5
* Delete a student entry and ensure their marks are also deleted.
*/
alter table Marks
add constraint f_key
foreign key (ID) references StudentDetails(ID)
on delete cascade;

delete from StudentDetails where ID = 1;



/**
* Activity 6
* Design one or more extra tables for the Student Database 
* on your own and come up with PK FK and other relationships
*/
create table FacultyDesignation(DesignationID int primary key,Designation varchar(50));
create table faculty(ID int primary key,FacultyName varchar(50),Designation int references FacultyDesignation(DesignationID));

alter table  FacultyDesignation modify DesignationID int auto_increment;

insert into FacultyDesignation values(1,'Assistant Professor');
insert into FacultyDesignation values(2,'Associate Professor');

insert into faculty values(1,'Manu',1);
insert into faculty values(2,'Biju',2);

select faculty.facultyname,facultydesignation.designation from faculty
join facultydesignation on facultydesignation.DesignationID = faculty.designation;
/**
* Activity 7
* Find students for whom no marks have been entered.( hint left join)
*/
Select StudentDetails.* from StudentDetails
left join Marks on Marks.ID = StudentDetails.ID
where Marks.ID is null;


/**
* Activity 8
* List first name , subject, mark, Std of the students whose marks is greater than 90% in 
* one of the  Subjects in the current academic year and previous academic year.
*/

select FirstName,Subject,marks,Std,YearOfAdmission from StudentDetails
join marks on marks.id = StudentDetails.ID
where marks.marks > 90 and StudentDetails.YearOfAdmission >= year(now()) - 1
union
select FirstName,Subject,marks,Std,YearOfAdmission from StudentDetails
join marks on marks.id = StudentDetails.ID
where marks.marks > 90 and StudentDetails.YearOfAdmission >= year(now()) - 2;


/**
* Activity 9
* Add marks of every subject of each student and then,
   Find which student scored the highest total mark in the current academic year of std X
   Find which student scored the lowest total mark in the current academic year of std X
*/

select StudentDetails.FirstName,YearOfAdmission,Std,sum(marks.marks) as total_marks from StudentDetails
join marks on marks.ID = StudentDetails.ID
group by marks.ID
having StudentDetails.YearOfAdmission = year(now()) - 1 and Std = 7
order by total_marks desc limit 1;

select StudentDetails.FirstName,YearOfAdmission,Std,sum(marks.marks) as total_marks from StudentDetails
join marks on marks.ID = StudentDetails.ID
group by marks.ID
having StudentDetails.YearOfAdmission = year(now()) - 1 and Std = 7
order by total_marks limit 1;