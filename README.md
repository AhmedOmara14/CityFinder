# 🌍 City Finder

A modern Android app built with Jetpack Compose that allows users to search and explore a large list of global cities efficiently.

---

## 🚀 Features

- Load over 100,000+ cities from a local JSON file
- Alphabetically grouped city list (A–Z)
- Expandable/collapsible sections per letter
- Offline-first architecture using Room

---

## 🛠 Tech Stack

- **Kotlin** & **Jetpack Compose**
- **Room** for local persistence
- **Moshi** for efficient JSON parsing
- **Hilt** for Dependency Injection
- **Clean Architecture** (Domain, Data, Presentation)

---

## 🤔 Why Room and Moshi Instead of Trie?

### ✅ Why I Used **Room**

- The JSON file contains 100,000+ cities, which **cannot be held in memory safely** on all devices.
- Initial attempts to parse and store everything in RAM caused `OutOfMemoryError`.
- Room allows **on-disk access**, efficient indexing, and memory-safe queries — ideal for large datasets.
- Enables scalability if search or filtering logic becomes more advanced.

### ✅ Why I Used **Moshi**

- Moshi is fast and memory-efficient, especially when used with `JsonReader` for streaming large JSON files.
- Works seamlessly with Kotlin data classes.
- Lightweight and optimized for Android compared to other parsers.

---

### ❌ Why I Didn’t Use **Trie** (Even Though It's Good for Search)

- Trie is indeed optimized for **prefix-based searches**, but it requires loading **all data into memory**.
- Given the large dataset, this isn't memory-safe on most mobile devices.
- Since the task explicitly states that **initial loading time doesn't matter**, I optimized for **runtime memory usage**, not just search speed.

---

## 📷 Screenshots

<img src="https://github.com/user-attachments/assets/5509b4ac-fcb6-4c88-841f-0235509b70e8" width="400"/>



