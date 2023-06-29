# Studants IMC management ![coffee-lover-hot-coffee (1)](https://github.com/giovaniavila/Crud/assets/112128418/bff0a0b5-5381-4c4e-a3f9-22b2629376ed)



<br>

## About it
The student's BMI (Body Mass Index) calculation system is an application developed using Java, JavaFX, Scene Builder, and MySQL technologies. Here's a detailed description of the system:

1. The system is built in Java, a popular and widely-used programming language known for its robustness and scalability in application development. <br>
2. The graphical user interface (GUI) of the system is implemented using the JavaFX library, allowing the creation of interactive and visually appealing user interfaces. <br>
3. Scene Builder, a visual design tool, is used to create the system's user interface, enabling developers to drag and drop components to achieve the desired appearance of the application. <br>
4. The system makes use of MySQL, a relational database management system, to store and manage student data such as height, weight, and BMI results. <br>
5. Upon starting the system, students can enter their personal information including height and weight into input fields provided by the user interface. <br>
6. The system automatically performs the BMI calculation using the formula: BMI = weight (kg) / (height (m))^2. <br>
7. The BMI results are stored in the MySQL database, allowing for data retrieval whenever needed. <br>
8. The user interface displays the BMI results for the student along with a corresponding interpretation of their body composition. <br>
9. The system may also include additional features such as graphs or visualizations to show the BMI trend over time. <br>
10. The integration of Java, JavaFX, Scene Builder, and MySQL technologies provides an efficient and scalable solution for calculating the BMI of students, offering a user-friendly interface and secure data storage. <br>

In summary, the system developed using Java, JavaFX, Scene Builder, and MySQL allows students to input their height and weight data, performs the BMI calculation, and stores the results securely. The intuitive and visually appealing user interface provides students with valuable information about their body composition, encouraging healthy habits and a balanced lifestyle.

<br>
<br>

## Tech stack used


<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="50" /> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg" width="50" /> ![foi](https://github.com/giovaniavila/Crud/assets/112128418/50dd93ce-e954-4673-b29f-cc7f6a930071) ![foi2](https://github.com/giovaniavila/Crud/assets/112128418/81912f04-d7cb-41e8-98d9-eb1266c7818a)

<br>
<br>
          

## Script sql
```create database juliana;
use juliana;

create table if not exists alunos(
id_aluno int not null primary key auto_increment,
nome varchar(50) not null,
cpf int(20) not null,
altura float(10,2) not null,
peso float(10,2) not null,
dataNasc date not null,
imc float(10,2) not null
);
