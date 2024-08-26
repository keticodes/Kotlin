package com.example
// Human class definition
class Human(var name: String, var age: Int) {
    fun getOlder() {
        age += 1
    }
}

// CourseRecord class definition
data class CourseRecord(val name: String, val yearCompleted: Int, val credits: Int, val grade: Double)

// Student class definition (inherits from Human)
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
