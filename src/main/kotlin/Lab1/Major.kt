package com.example.Lab1

// Major class definition
class Major(val name: String) {
    val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun stats(): Triple<Double, Double, Double> {
        val averages = students.map { it.weightedAverage() }
        val minAverage = averages.minOrNull() ?: 0.0
        val maxAverage = averages.maxOrNull() ?: 0.0
        val avgOfAverages = if (averages.isNotEmpty()) averages.average() else 0.0
        return Triple(minAverage, maxAverage, avgOfAverages)
    }

    fun stats(courseName: String): Triple<Double, Double, Double> {
        val averages = students.mapNotNull { student ->
            val courseGrades = student.courses.filter { it.name == courseName }
            if (courseGrades.isNotEmpty()) {
                val totalWeightedGrades = courseGrades.sumOf { it.grade * it.credits }
                val totalCredits = courseGrades.sumOf { it.credits }
                if (totalCredits > 0) totalWeightedGrades / totalCredits else null
            } else {
                null
            }
        }
        val minAverage = averages.minOrNull() ?: 0.0
        val maxAverage = averages.maxOrNull() ?: 0.0
        val avgOfAverages = if (averages.isNotEmpty()) averages.average() else 0.0
        return Triple(minAverage, maxAverage, avgOfAverages)
    }
}