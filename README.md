# 📄 Resume Screening Tool

> **An AI-powered resume analysis web application built with Spring Boot and NLP that helps HR teams instantly screen candidates and helps applicants submit resumes with ease.**

---

## 📌 Project Description

The **Resume Screening Tool** is a full-stack web application that automates the resume screening process using **Natural Language Processing (NLP)**. Traditional hiring is slow and manual — this tool solves that by instantly extracting key information from resumes (PDF/DOCX) and scoring candidates against job requirements.

### 🎯 Who Is This For?

| User | What They Can Do |
|------|-----------------|
| 👤 **Job Applicant** | Upload resume, get profile summary instantly |
| 🏢 **HR / Recruiter** | Screen resumes, set job requirements, get match score |

### 🔍 How It Works

1. **Upload** a resume in PDF or DOCX format
2. **NLP engine** automatically extracts — Name, Email, Phone, Skills, Experience, Education
3. **Scoring engine** compares resume skills against HR's required skills
4. **Match score** (0–100%) is calculated and displayed with color-coded result
5. All results are **saved to database** and viewable on HR Dashboard

---

## ✨ Key Features

- 📤 Upload resume in **PDF or DOCX** format
- 🤖 **Auto-extract** candidate name, email, phone, skills, experience & education
- 📊 **AI Match Score** — compares resume against job requirements
- 🎨 **Color-coded results** — Green (Strong), Yellow (Partial), Red (Low)
- 🏢 **HR Dashboard** — view all screened resumes ranked by score
- 👤 **Applicant Portal** — submit resume and view profile summary
- 💾 All data **saved to MySQL database**
- 🌐 Clean, responsive **Web UI** — no React/Angular needed

---

## 🛠️ Tech Stack

### Backend
| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Core programming language |
| **Spring Boot** | 3.2.0 | Backend framework, REST handling |
| **Spring MVC** | 3.2.0 | Web layer, routing, controllers |
| **Spring Data JPA** | 3.2.0 | Database ORM layer |
| **Hibernate** | 6.x | JPA implementation |
| **Maven** | 3.8+ | Build tool & dependency management |

### NLP & File Processing
| Technology | Version | Purpose |
|------------|---------|---------|
| **Apache Tika** | 2.9.1 | Extract text from PDF & DOCX files |
| **Apache OpenNLP** | 2.3.1 | Natural Language Processing (NER) |
| **Regex (Java)** | — | Extract email, phone, experience |

### Frontend
| Technology | Purpose |
|------------|---------|
| **Thymeleaf** | Server-side HTML templating engine |
| **HTML5 + CSS3** | UI structure and styling |
| **Vanilla JavaScript** | Minimal client-side interactions |

### Database
| Technology | Version | Purpose |
|------------|---------|---------|
| **MySQL** | 8.0+ | Relational database to store resume data |
| **MySQL Connector/J** | Latest | JDBC driver for MySQL |

### Developer Tools
| Tool | Purpose |
|------|---------|
| **Lombok** | Reduces boilerplate (getters, setters, constructors) |
| **IntelliJ IDEA** | Recommended IDE |
| **Postman** | API testing (optional) |

---

## 📁 Project Structure

```
resume-screening-tool/
├── pom.xml                                        ← Maven dependencies
├── README.md
└── src/
    └── main/
        ├── java/com/resumescreener/
        │   ├── ResumeScreeningApp.java            ← Main class (Entry point)
        │   ├── controller/
        │   │   ├── PageController.java            ← HTML page routing
        │   │   └── ResumeController.java          ← Form submission handling
        │   ├── service/
        │   │   ├── ResumeParserService.java       ← PDF/DOCX text extraction
        │   │   ├── NLPService.java                ← Skill & name extraction
        │   │   └── ScoringService.java            ← Match score calculation
        │   ├── model/
        │   │   └── ResumeData.java                ← Database entity
        │   └── repository/
        │       └── ResumeRepository.java          ← JPA database queries
        └── resources/
            ├── application.properties             ← DB config & server settings
            └── templates/
                ├── index.html                     ← Home / landing page
                ├── applicant.html                 ← Applicant upload form
                ├── hr.html                        ← HR screening form
                ├── result.html                    ← Analysis result page
                └── dashboard.html                 ← HR candidates dashboard
```

---

## ⚙️ Setup & Installation

### Prerequisites
Make sure you have the following installed:
- ✅ Java 17 or higher — [Download](https://www.oracle.com/java/technologies/downloads/)
- ✅ Maven 3.8+ — [Download](https://maven.apache.org/download.cgi)
- ✅ MySQL 8.0+ — [Download](https://dev.mysql.com/downloads/)
- ✅ IntelliJ IDEA — [Download](https://www.jetbrains.com/idea/)

---

### Step 1 — Create MySQL Database

Open MySQL and run:
```sql
CREATE DATABASE resumedb;
```

---

### Step 2 — Configure Database

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resumedb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD   ← Change this
```

---

### Step 3 — Open in IntelliJ

1. Extract the ZIP file
2. Open **IntelliJ IDEA** → `File → Open`
3. Select the `resume-screening-tool` folder
4. IntelliJ will auto-detect it as a **Maven project**
5. Click **"Trust Project"** when prompted
6. Wait for Maven to download all dependencies

---

### Step 4 — Run the Application

In IntelliJ, open `ResumeScreeningApp.java` and click the **▶ Run** button

Or via terminal:
```bash
mvn spring-boot:run
```

---

### Step 5 — Open in Browser

```
http://localhost:8080
```

---

## 🌐 Application Pages

| URL | Page | User |
|-----|------|------|
| `http://localhost:8080/` | 🏠 Home Page | Everyone |
| `http://localhost:8080/applicant` | 👤 Upload Resume | Applicant |
| `http://localhost:8080/hr` | 🏢 Screen Resume | HR |
| `http://localhost:8080/dashboard` | 📋 View All Results | HR |

---

## 📊 Scoring Algorithm

Match score is calculated out of **100%** using two factors:

```
Match Score = Skill Score (70%) + Experience Score (30%)
```

| Score Range | Label | Color |
|-------------|-------|-------|
| 70% – 100% | ✅ Strong Match | 🟢 Green |
| 40% – 69% | ⚠️ Partial Match | 🟡 Yellow |
| 0% – 39% | ❌ Low Match | 🔴 Red |

---

## 🔮 Future Enhancements

- [ ] Login system for HR and Applicants (Spring Security)
- [ ] Export shortlisted candidates to Excel
- [ ] Email notification to selected candidates
- [ ] Batch upload — screen multiple resumes at once
- [ ] LangChain4j + GPT integration for smarter extraction
- [ ] Resume ranking with detailed gap analysis

---

## 👨‍💻 Built With

**Spring Boot + Apache Tika + OpenNLP + Thymeleaf + MySQL**

---

> 💡 *This project demonstrates real-world use of NLP, file parsing, scoring logic, and full-stack Java web development — ideal for academic projects and portfolio.*
