# Advanced Sorting Algorithms in Java

This project explores performance-optimized sorting algorithms implemented in Java.  
It was developed as part of the "Algorithms & Data Structures" module at TUM.

## ✨ Features

### 🌀 Quicksort (Single & Dual Pivot)
- Multiple Pivot Strategies:
  - Last, Mid, Random
  - Median Front & Distributed
- Selectionsort threshold optimization
- DualPivotQuicksort with pluggable PivotPairFinder

### 🔗 Mergesort
- Early return for sorted subarrays
- Shared helper array (no memory overhead)
- Optional fallback to Selectionsort for small arrays

### 🔁 Selectionsort
- Used as an optimization for small array segments

### 🧪 SortingComparison Tool
- Integrated benchmarking of algorithms
- Configurable array generation (reversed, partially sorted, duplicates)
- Adjustable thresholds and array sizes (2.5 – 7.5 million)

## 🧠 Concepts Demonstrated
- Divide & Conquer
- Strategy Pattern (PivotFinder)
- Runtime optimization
- Memory-efficient recursion

## 🛠️ Technologies
- Java 17
- IntelliJ IDEA
- JUnit (optional for testing)

## 📚 Educational Context
Developed as part of TUM's ADS course – summer 2025

## 👤 Author
Philip Schmitz-Valckenberg  
[github.com/PSchmitz-Valckenberg](https://github.com/PSchmitz-Valckenberg)
