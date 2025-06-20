# OTP Email Service

This is a Spring Boot application that sends and validates One-Time Passwords (OTPs) via email using Gmail's SMTP service.

---

## 📦 Features

- ✅ Send 6-digit OTP to email
- 🔒 OTP expires in 5 minutes
- 📬 Uses Gmail SMTP for sending emails
- REST API with `/send` and `/validation` endpoints

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Mail (`JavaMailSender`)
- Jakarta Mail (Angus)
- Maven or Gradle (for dependency management)
- IntelliJ IDEA (recommended IDE)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/otp-service.git
cd otp-service
2. Configure Gmail SMTP
Important: Gmail blocks login from "less secure apps". To make it work:

🔑 Use App Passwords instead of your actual Gmail password.

✅ Steps to create a Gmail App Password:
Go to https://myaccount.google.com/security

Enable 2-Step Verification

Under "Signing in to Google", click App Passwords

Generate a password for "Mail" and "Other"

Replace your actual password with the generated App Password

3. Edit application.properties
properties
Copy
Edit
spring.application.name=OtpService
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

server.port=8085

📮 API Endpoints
1. POST /otp/send
Sends an OTP to the provided email.

Request:

json
Copy
Edit
{
  "email": "user@example.com"
}
Response:

nginx
Copy
Edit
Otp sent successfully
2. POST /otp/validation
Validates the OTP for the email.

Request:

json
Copy
Edit
{
  "email": "user@example.com",
  "otp": "123456"
}
Response:

✅ "Valid OTP" — if correct and within 5 minutes

❌ "Invalid or expired OTP" — otherwise

🧪 Testing
You can use Postman, curl, or any frontend to test these endpoints.

🧹 Clean Up
OTPs are stored in memory (ConcurrentHashMap)

Automatically expire after 5 minutes

Restarting the app will reset stored OTPs


🙋‍♂️ Author
Developed by NAGA RAMAKRISHNA – for learning purposes.

Let me know if you'd like to include build/run instructions (e.g. for `mvn spring-boot:run`), or if you're planning to Dockerize it.







