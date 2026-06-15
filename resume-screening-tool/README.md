# 📄 Resume Screening Tool
### AI-powered Resume Analysis with Spring Boot + NLP

---

## 🚀 Tech Stack
- **Backend:** Spring Boot 3.2, Java 17
- **Frontend:** Thymeleaf HTML (served by Spring Boot)
- **NLP:** Apache OpenNLP + Apache Tika
- **Database:** MySQL + Spring Data JPA
- **Build:** Maven

---

## ⚙️ Setup Instructions

### 1. Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8.0+
- IntelliJ IDEA (recommended)

### 2. Database Setup
```sql
CREATE DATABASE resumedb;
```

### 3. Configure Database
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

### 5. Open in Browser
```
http://localhost:8080
```

---

## 🌊 User Flow

```
http://localhost:8080 (Home)
   │
   ├── /applicant  → Upload resume → Submit → View profile summary
   │
   ├── /hr         → Upload resume + enter job requirements → View match score
   │
   └── /dashboard  → View all screened resumes ranked by score
```

---

## 📁 Project Structure

```
src/main/java/com/resumescreener/
├── ResumeScreeningApp.java          ← Main class
├── controller/
│   ├── PageController.java          ← Page routing
│   └── ResumeController.java        ← Form handling
├── service/
│   ├── ResumeParserService.java     ← PDF/DOCX text extraction
│   ├── NLPService.java              ← Skill & name extraction
│   └── ScoringService.java          ← Match score calculation
├── model/
│   └── ResumeData.java              ← Database entity
└── repository/
    └── ResumeRepository.java        ← JPA repository

src/main/resources/
├── templates/
│   ├── index.html       ← Home page
│   ├── applicant.html   ← Applicant upload form
│   ├── hr.html          ← HR screening form
│   ├── result.html      ← Result display
│   └── dashboard.html   ← HR dashboard
└── application.properties
```

---

## 📊 Scoring Logic

| Component   | Weight |
|-------------|--------|
| Skill Match | 70%    |
| Experience  | 30%    |

| Score    | Label          |
|----------|----------------|
| ≥ 70%    | ✅ Strong Match |
| 40–69%   | ⚠️ Partial Match |
| < 40%    | ❌ Low Match   |

---

## 🔮 Future Enhancements
- Export shortlisted candidates to Excel
- Email notification to selected candidates
- Login system for HR and Applicants
- LangChain4j + GPT for smarter extraction
- Multiple resume batch upload
