package com.example.Lab1

class Student(name: String, age: Int) : Human(name, age) {
    val courses = mutableListOf<CourseRecord>()

    fun addCourse(course: CourseRecord) {
        courses.add(course)
    }

    fun weightedAverage(): Double {
        val totalWeightedGrades = courses.sumOf { it.grade * it.credits }
        val totalCredits = courses.sumOf { it.credits }
        return if (totalCredits > 0) totalWeightedGrades / totalCredits else 0.0
    }

    fun weightedAverage(year: Int): Double {
        val filteredCourses = courses.filter { it.yearCompleted == year }
        val totalWeightedGrades = filteredCourses.sumOf { it.grade * it.credits }
        val totalCredits = filteredCourses.sumOf { it.credits }
        return if (totalCredits > 0) totalWeightedGrades / totalCredits else 0.0
    }

    fun minMaxGrades(): Pair<Double, Double> {
        val minGrade = courses.minOfOrNull { it.grade } ?: 0.0
        val maxGrade = courses.maxOfOrNull { it.grade } ?: 0.0
        return Pair(minGrade, maxGrade)
    }
}


