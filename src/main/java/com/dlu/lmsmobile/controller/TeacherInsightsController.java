package com.dlu.lmsmobile.controller;

import com.dlu.lmsmobile.service.ClassInsightsService;
import com.dlu.lmsmobile.service.TeacherReminderService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/dev/teachers/{teacherId}/courses/{courseId}")
public class TeacherInsightsController {
    private final ClassInsightsService service;
    private final TeacherReminderService reminders;

    public TeacherInsightsController(ClassInsightsService service, TeacherReminderService reminders) {
        this.service = service;
        this.reminders = reminders;
    }

    @GetMapping("/class-insights")
    public Map<String, Object> classInsights(@PathVariable Long teacherId, @PathVariable Long courseId,
                                             @RequestParam(defaultValue = "8") int weeks) {
        return service.getClassInsights(teacherId, courseId, weeks);
    }

    /** Body: student_ids, title, message, and optionally source_type + source_id of the activity. */
    @PostMapping("/reminders")
    public Map<String, Object> sendReminders(@PathVariable Long teacherId, @PathVariable Long courseId,
                                             @RequestBody Map<String, Object> payload) {
        return reminders.send(teacherId, courseId, payload);
    }
}
