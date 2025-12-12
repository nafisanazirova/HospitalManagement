package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query(value = """
        SELECT 
            a.appt_id,
            a.appt_date,
            a.appt_time,
            a.reason,
            a.appt_status,
            d.doctor_id,
            d.first_name AS doctor_first,
            d.last_name AS doctor_last,
            p.patient_id,
            p.first_name AS patient_first,
            p.last_name AS patient_last
        FROM appointment a
        JOIN doctor d ON a.doctor_id = d.doctor_id
        JOIN patient p ON a.patient_id = p.patient_id
        ORDER BY a.appt_id;
        """, nativeQuery = true)
    List<Map<String, Object>> getAppointmentsWithNames();
}
