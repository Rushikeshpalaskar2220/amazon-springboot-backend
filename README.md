
# 🛒 Amazon-Style Admin Panel (React + Spring Boot + JWT)

Welcome to my **Amazon-style Admin Panel** project — a full-stack application built using **React (Frontend)** and **Spring Boot (Backend)** with **secure JWT Authentication**, role-based access, and complete **Category → Subcategory → Product** management.

This project showcases a mini-ecommerce admin system where admins can manage the catalog, products, and user profiles securely.

---

## 🚀 **Current Features**

### 🔐 **Authentication & Security**

* User Registration & Login
* JWT Token Generation
* Role-Based Authorization (USER / ADMIN)
* Protected REST APIs
* Password encryption with BCrypt
* CORS-enabled connection with frontend

### 📦 **Category Management**

* Create, Update, Delete Categories
* List all categories
* View products inside a category
* Automatic nested data mapping (Category → Subcategory → Product)

### 🧩 **Subcategory Management**

* Create, Update, Delete Subcategories
* Fetch products by subcategory
* Category → Subcategory relationship maintained

### 📱 **Product Management**

* Create, Update, Delete Products
* Unique UUID product IDs
* View product details
* Description mapping
* Subcategory → Product mapping

### 👤 **User Profile Management**

* Update profile details
* Change password
* Live authenticated profile update via JWT
* Secure `/users/me` endpoint

### 📘 **Project History (current)**

* All CRUD operations are logged in backend
* Entities store:

  * `createdDate`
  * `updatedDate`
* History can be used later for analytics

---

## 🌟 **Upcoming Features (Planned)**

Here are the exciting features I will be adding soon 🔥:

### 🟢 **1. isActive Flag for Soft Delete**

* Add `isActive` field in Category, Subcategory, Product
* Instead of deleting, mark inactive
* Hide inactive items from UI
* Admin can *restore* items later

### 🖼️ **2. Image Upload for Products**

* Upload product image from React
* Store image in server / AWS S3
* Preview images on frontend
* New field: `imageUrl`

### 📊 **3. Activity History / Audit Log**

* Track *who created*, *who updated* items
* Store user id in DB
* Show “Last updated by…” on frontend

### 📦 **4. Pagination & Filtering**

* Implement filters (price, date, category)
* Add search functionality
* Backend + frontend search seamlessly

### 🛒 **5. Shopping-like Dashboard (For Demo)**

* A minimal user-facing catalog preview
* Category → Product browsing page

---

## 🧠 **Tech Stack**

### 🎨 **Frontend**

* React.js
* Vite
* Axios
* React Router
* Tailwind (optional)

### 🔥 **Backend**

* Java 17
* Spring Boot 3.5
* Spring Security
* JWT (jjwt 0.11.5)
* Hibernate / JPA
* MySQL
* H2 Database (Dev mode)

---

## 🔗 **How Frontend Connects to Backend**

### 📍 Base URL

```
http://localhost:8082
```

### ✨ Axios Example (Frontend)

```js
axios.post("http://localhost:8082/users/login", {
   username,
   password
});
```

### 🔐 Backend Security (SecurityConfig.java)

* `/users/login` → **permitAll**
* `/users/register` → **permitAll**
* `/api/v1/**` GET → USER & ADMIN
* `/api/v1/**` POST/PUT/DELETE → ADMIN only
* JWT filter checks token on every request

---

## 🧭 Project Architecture Overview

### 📁 **Frontend (React)**

```
/src
  /pages
    Login.jsx
    Categories.jsx
    Products.jsx
    Profile.jsx
  /components
  /services (axios API)
  /context (AuthContext)
```

### 🛠 **Backend (Spring Boot)**

```
/controller     → API endpoints
/service        → Business logic
/repository     → DB access
/model          → Entities
/security       → JWT + Auth
/exception      → Custom exceptions
/dto            → Transfer objects
```

---

##  Made By Rushi 👨‍💻 🚀



✔️ A **PPT for your project review**
✔️ A **diagram** of the architecture
✔️ A **video script** for explaining the project
✔️ A **GitHub wiki page**

Just say the word! 😄🔥
